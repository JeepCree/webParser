package ua.com.mobifix.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ua.com.mobifix.service.SHA3;

import javax.print.Doc;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        System.out.println(Jsoup.connect("https://all-spares.ua/uk/spares/batteries/").get());
    }
}
