package optionpickers;

public enum NumberOfGpus {
    _0("0"),
    _1("1"),
    _2("2"),
    _4("4");

    private final String optionText;

    NumberOfGpus(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
