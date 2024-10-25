package ua.com.mobifix.matchers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelProductMatcher {

    public static void main(String[] args) {
        String inputFilePath = "C:\\Книга1.xlsx";
        String outputFilePath = "output.xlsx";
        Set<String> commonWords = Set.of("дисплей");

        try {
            FileInputStream fileInputStream = new FileInputStream(inputFilePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            List<String> allSpares = new ArrayList<>();
            List<String> atrMobile = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Пропустить заголовок

                Cell cell1 = row.getCell(0);
                String allSparesItem = (cell1 != null) ? cleanText(cell1.getStringCellValue(), commonWords) : "";

                Cell cell2 = row.getCell(1);
                String atrMobileItem = (cell2 != null) ? cleanText(cell2.getStringCellValue(), commonWords) : "";

                allSpares.add(allSparesItem);
                atrMobile.add(atrMobileItem);
            }

            // Использование алгоритма Левенштейна для улучшения точности
            LevenshteinDistance levenshtein = new LevenshteinDistance();
            List<String> matchedAtrMobile = new ArrayList<>();

            for (String template : allSpares) {
                String bestMatch = "";
                int bestScore = Integer.MAX_VALUE; // Минимальное расстояние

                for (String choice : atrMobile) {
                    int distance = levenshtein.apply(template, choice);
                    if (distance < bestScore) {
                        bestScore = distance;
                        bestMatch = choice;
                    }
                }

                matchedAtrMobile.add(bestScore > 10 ? "Нет совпадений" : bestMatch); // Используем порог
            }

            // Запись результатов в новый файл
            Sheet outputSheet = workbook.createSheet("Results");
            for (int i = 0; i < allSpares.size(); i++) {
                Row row = outputSheet.createRow(i);
                row.createCell(0).setCellValue(allSpares.get(i));
                row.createCell(1).setCellValue(matchedAtrMobile.get(i));
            }

            FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
            workbook.write(fileOutputStream);
            workbook.close();
            fileInputStream.close();
            fileOutputStream.close();

            System.out.println("Сопоставление завершено и результаты сохранены в " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Удаление общих слов, пробелов, приведение к нижнему регистру и удаление знаков препинания
    private static String cleanText(String text, Set<String> commonWords) {
        return Arrays.stream(text.toLowerCase() // Приведение к нижнему регистру
                        .replaceAll("[^a-zа-я0-9\\s]", "") // Удаление знаков препинания
                        .trim() // Удаление лишних пробелов
                        .split("\\s+")) // Разбиение строки по пробелам
                .filter(word -> !commonWords.contains(word)) // Фильтрация общих слов
                .collect(Collectors.joining(" ")); // Соединение обратно в строку
    }
}
