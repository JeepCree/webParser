package ua.com.mobifix.parser;

public class ReplaceString {
    private String replace;
    private String replacement;

    ReplaceString(String replace, String replacement){
        this.replace = replace;
        this.replacement = replacement;
    }

    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }

    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }
}
