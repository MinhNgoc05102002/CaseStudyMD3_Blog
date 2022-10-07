package model;

import java.util.Date;

public class Blog {
    private int blogID;
    private String title;
    private String content;
    private int status;
    private Date createAt;
    private int accountID;

    public Blog() {
    }

    public Blog(int blogID, String title, String content, int status, Date createAt, int accountID) {
        this.blogID = blogID;
        this.title = title;
        this.content = content;
        this.status = status;
        this.createAt = createAt;
        this.accountID = accountID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

}
