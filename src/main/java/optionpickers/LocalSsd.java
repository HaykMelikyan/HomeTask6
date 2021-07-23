package optionpickers;

public enum LocalSsd {
    STORAGE_0("0"),
    STORAGE_24X375GB("24");

    private final String optionText;

    LocalSsd(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
