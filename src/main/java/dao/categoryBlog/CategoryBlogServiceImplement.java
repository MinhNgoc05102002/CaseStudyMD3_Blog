package dao.categoryBlog;

import dao.ConnectMySQL;
import model.Blog;
import model.Category;
import model.CategoryBlog;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryBlogServiceImplement implements ICategoryBlogService{
    private Connection connection = ConnectMySQL.getConnection();
    @Override
    public void save(CategoryBlog categoryBlog) {
        String insert = "INSERT INTO `case3`.`category_blog` (`categoryID`, `blogID`) " +
                "VALUES (?, ?);";
        try (Connection connection = ConnectMySQL.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, String.valueOf(categoryBlog.getCategoryID()));
            preparedStatement.setString(2, String.valueOf(categoryBlog.getBlogID()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Blog> findBlogByCategoryId(int categoryID) {
        String sqlFindBlogByCategoryID = "SELECT blog.* FROM category_blog join blog" +
                                         "ON category_blog.blogID = blog.blogID" +
                                         "WHERE categoryID = " + categoryID;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlFindBlogByCategoryID);

            ArrayList<Blog> listResult = new ArrayList<>();

            while (resultSet.next()) {
                Blog blog = getBlogByResultSet(resultSet);
                listResult.add(blog);
            }
            return listResult;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Blog getBlogByResultSet(ResultSet resultSet) throws SQLException {
        int blogId = resultSet.getInt("blogId");

        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        int status = resultSet.getInt("status");
        Date createAt = resultSet.getDate("createAt");
        int accountId = resultSet.getInt("accountId");
        String image = resultSet.getString("image");

        return new Blog(blogId, title, content, status, createAt, accountId, image);
    }

    @Override
    public List<Category> findCategoryByBlogId(int blogId) {
        String sqlFindCategoryByBlogID = "SELECT category.* FROM category_blog join category" +
                                         "ON category_blog.categoryId = category.categoryId" +
                                         "WHERE blogID = " + blogId + ";";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlFindCategoryByBlogID);

            ArrayList<Category> listResult = new ArrayList<>();

            while (resultSet.next()) {
                Category category = getCategoryByResultSet(resultSet);
                listResult.add(category);
            }
            return listResult;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    private Category getCategoryByResultSet(ResultSet resultSet) throws SQLException {
        int categoryID = resultSet.getInt("categoryID");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        return new Category(categoryID, name, description);
    }

    @Override
    public void deleteBlogById(int blogId) {
        String deleteSQL = "DELETE FROM category_blog where blogId = " + blogId + ";";
        try {
            Statement statement = connection.createStatement();
            statement.execute(deleteSQL);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategoryById(int categoryId) {
        String deleteSQL = "DELETE FROM category_blog where categoryId = " + categoryId + ";";
        try {
            Statement statement = connection.createStatement();
            statement.execute(deleteSQL);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CategoryBlogServiceImplement categoryServiceImplement = new CategoryBlogServiceImplement();

        List<Category> category = categoryServiceImplement.findCategoryByBlogId(1);
        for(Category c:category) System.out.println(c.getCategoryID());
    }
}
