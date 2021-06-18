package optionpickers;

public enum CommitedUsage {
    NONE("0"),
    ONE_YEAR("1"),
    THREE_YEARS("3");

    private final String optionText;

    CommitedUsage(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
