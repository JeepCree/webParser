package ua.com.mobifix.service;

public final class Color {

    private Color() {
        // Приватный конструктор, чтобы нельзя было создать экземпляр класса
    }

    public static String Reset() {
        return "\u001B[0m";
    }

    public static String Black() {
        return "\u001B[30m";
    }

    public static String Red() {
        return "\u001B[31m";
    }

    public static String Green() {
        return "\u001B[32m";
    }

    public static String Yellow() {
        return "\u001B[33m";
    }

    public static String Blue() {
        return "\u001B[34m";
    }

    public static String Purple() {
        return "\u001B[35m";
    }

    public static String Cyan() {
        return "\u001B[36m";
    }

    public static String White() {
        return "\u001B[37m";
    }

    public static String BgBlack() {
        return "\u001B[40m";
    }

    public static String BgRed() {
        return "\u001B[41m";
    }

    public static String BgGreen() {
        return "\u001B[42m";
    }

    public static String BgYellow() {
        return "\u001B[43m";
    }

    public static String BgBlue() {
        return "\u001B[44m";
    }

    public static String BgPurple() {
        return "\u001B[45m";
    }

    public static String BgCyan() {
        return "\u001B[46m";
    }

    public static String BgWhite() {
        return "\u001B[47m";
    }

    public static String Bold() {
        return "\u001B[1m";
    }

    public static String Underline() {
        return "\u001B[4m";
    }

    public static String Reversed() {
        return "\u001B[7m";
    }
}

