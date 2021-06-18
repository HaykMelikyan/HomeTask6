package optionpickers;

public enum GpuType {
    NVIDIA_TESLA_P100("NVIDIA_TESLA_P100"),
    NVIDIA_TESLA_P4("NVIDIA_TESLA_P4"),
    NVIDIA_TESLA_V100("NVIDIA_TESLA_V100"),
    NVIDIA_TESLA_T4("NVIDIA_TESLA_T4");

    private final String optionText;

    GpuType(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
