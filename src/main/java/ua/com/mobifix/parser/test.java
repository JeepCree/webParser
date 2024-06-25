package ua.com.mobifix.parser;

import org.jsoup.Jsoup;
import org.jsoup.helper.RequestAuthenticator;
import org.jsoup.nodes.Document;
import ua.com.mobifix.service.SHA3;

import javax.print.Doc;
import java.io.IOException;
import java.net.PasswordAuthentication;

public class test {
    public static void main(String[] args) throws IOException {
        System.out.println(Jsoup.connect("https://all-spares.ua/")
                .get());

    }
}
