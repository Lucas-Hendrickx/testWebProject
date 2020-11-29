package domain.model;

public enum Role {
    ADMIN("Admin"), GUARDIAN("Guardian");
    private String stringValue;

    private Role(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
