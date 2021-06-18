package optionpickers;

public enum LocalSsd {
    _0("0"),
    _24x375GB("24");

    private final String optionText;

    LocalSsd(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
