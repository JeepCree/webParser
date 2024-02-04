package ua.com.mobifix.service;

import org.bouncycastle.crypto.digests.SHAKEDigest;
import java.nio.charset.StandardCharsets;

public class SHA3 {
    public static String generateSHA3Hash(String input) {
        try {
            // Создаем объект хэш-функции SHA-3 с длиной хэша 256 бит
            SHAKEDigest digest = new SHAKEDigest(256);

            // Кодируем входную строку в байтовый массив
            byte[] bytes = input.getBytes(StandardCharsets.UTF_8);

            // Обновляем хэш с использованием байтового массива
            digest.update(bytes, 0, bytes.length);

            // Вычисляем окончательное значение хэша
            byte[] hash = new byte[digest.getDigestSize()];
            digest.doFinal(hash, 0);

            // Преобразуем байтовый массив хэша в шестнадцатеричную строку
            StringBuilder hexHash = new StringBuilder();
            for (byte b : hash) {
                hexHash.append(String.format("%02x", b));
            }

            // Возвращаем шестнадцатеричную строку хэша
            return hexHash.toString();
        } catch (Exception e) {
            // Обработка возможных исключений
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String input = "Hello, SHA-3!";
        String hash = generateSHA3Hash(input);
        System.out.println("SHA-3 хэш для строки '" + input + "': " + hash);
    }
}
