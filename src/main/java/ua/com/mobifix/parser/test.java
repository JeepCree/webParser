package ua.com.mobifix.parser;

import org.apache.commons.collections.map.HashedMap;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) throws IOException {
        Document page = Jsoup.connect("https://all-spares.ua/ru/lcd-compatible-with-xiaomi-redmi-9a-black-with-touchscreen-original-prc-m2006c3lg/").get();

        String el = page.select("div.gallery-main-wrap > div > picture > img").attr("src").replace("/480/", "/799/");

        System.out.println(el);

        String originalString = "Это пример строки, которую нужно изменить.";
        String[] array = new String[2];

        // Добавляем значения в массив
        array[0] = "Значение1";
        array[1] = "Значение2";


        // Создаем список (List) массивов и добавляем в него наш массив
        List<String[]> listOfArrays = new ArrayList<>();
        listOfArrays.add(array);
        System.out.println("Elements: " + listOfArrays.size());
        for (String[] arr : listOfArrays) {
            if (arr.length > 0) {
                String firstElement = arr[0];
                String secondElement = arr[1];
                originalString = originalString.replace(firstElement, secondElement);
            }
        }
        System.out.println(originalString);
    }
}