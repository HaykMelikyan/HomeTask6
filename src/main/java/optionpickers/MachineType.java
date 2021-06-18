package optionpickers;

public enum MachineType {
    E2_STANDARD_2("e2-standard-2 (vCPUs: 2, RAM: 8GB)"),
    E2_STANDARD_4("e2-standard-4 (vCPUs: 4, RAM: 16GB)"),
    E2_STANDARD_8("e2-standard-8 (vCPUs: 8, RAM: 32GB)"),
    E2_STANDARD_16("e2-standard-16 (vCPUs: 16, RAM: 64GB)"),
    E2_STANDARD_32("e2-standard-32 (vCPUs: 32, RAM: 128GB)");

    private final String optionText;

    MachineType(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionText() {
        return optionText;
    }
}
