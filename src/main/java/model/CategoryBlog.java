package model;

public class CategoryBlog {
    private int categoryID;
    private int blogID;

    public CategoryBlog() {
    }

    public CategoryBlog(int categoryID, int blogID) {
        this.categoryID = categoryID;
        this.blogID = blogID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }
}
