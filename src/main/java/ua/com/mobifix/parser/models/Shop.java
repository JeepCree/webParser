package ua.com.mobifix.parser.models;

public class Shop {
    String name = "mobifix";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductPrefix(){
        return this.name;
    }
}
