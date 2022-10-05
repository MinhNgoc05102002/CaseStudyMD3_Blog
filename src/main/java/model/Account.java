package model;

public class Account {
    private String username;
    private String password;
    private boolean type;

    public Account() {
    }

    public Account(String username, String password, boolean type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isType() {
        return type;
    }
}
