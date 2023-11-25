package ua.com.mobifix.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvParser {
    public static List<String[]> parseCsv(String filePath) throws IOException, CsvException {
        // Укажите нужный разделитель (запятая, точка с запятой и т.д.)
        char separator = ';'; // Например, точка с запятой

        // Создайте CSVParser с нужными настройками
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(separator)
                .withIgnoreQuotations(true)
                .build();

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(csvParser)
                .withSkipLines(1) // пропустить первую строку с заголовками
                .build()) {
            return reader.readAll();
        }
    }
}
