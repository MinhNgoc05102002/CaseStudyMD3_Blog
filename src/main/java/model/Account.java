package model;

public class Account {
    private int accountID;
    private String username;
    private String email;
    private String fullname;
    private String password;
    private String phoneNumber;
    private String address;
    private int role;
    private int status;
    public Account() {
    }

    public Account(int accountID, String username, String email, String fullname, String password, String phoneNumber, String address, int role, int status) {
        this.accountID = accountID;
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.status = status;
    }

    public Account(String username, String email, String fullname, String password, String phoneNumber, String address, int role, int status) {
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.status = status;
    }

    public Account(String username, String email, String fullname, String password) {
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.status = 1;
        this.role = 0;
        this.address = "";
        this.phoneNumber = "";
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
