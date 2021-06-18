package optionpickers;

public enum Highlighting {
    BASH("Bash"),
    C("C"),
    C_SHARP("C#"),
    C_PLUS_PLUS("C++"),
    CSS("CSS"),
    HTML("HTML"),
    JSON("JSON"),
    JAVA("Java"),
    JAVASCRIPT("JavaScript"),
    LUA("Lua");

    private final String optionText;

    Highlighting(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }

}
