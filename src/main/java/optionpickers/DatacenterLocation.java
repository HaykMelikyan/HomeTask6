package optionpickers;

public enum DatacenterLocation {
    BELGIUM("europe-west1"),
    LONDON("europe-west2"),
    FRANKFURT("europe-west3");

    private final String optionText;

    DatacenterLocation(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
