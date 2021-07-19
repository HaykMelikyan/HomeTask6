package optionpickers;

public enum NumberOfGpus {
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    FOUR("4");

    private final String optionText;

    NumberOfGpus(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
