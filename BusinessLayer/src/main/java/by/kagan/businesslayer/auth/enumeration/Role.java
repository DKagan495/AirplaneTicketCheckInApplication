package by.kagan.businesslayer.auth.enumeration;

public enum Role {
    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private final String role;
    Role(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
