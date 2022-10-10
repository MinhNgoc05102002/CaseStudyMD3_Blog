package dao.categoryBlog;

import dao.ConnectMySQL;
import model.Blog;
import model.Category;
import model.CategoryBlog;

import java.sql.*;
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
//        String sqlFindBlogByCategoryID = "SELECT blog.* FROM category_blog join blog" +
//                                         "ON category_blog.blogID = blog.blogID" +
//                                         "WHERE categoryID = " + categoryID;
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sqlFindBlogByCategoryID);
//            while (resultSet.next()) {
//                int categoryID = resultSet.getInt("categoryID");
//                String name = resultSet.getString("name");
//                String description = resultSet.getString("description");
//                return new Category(categoryID, name, description);
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public List<Category> findCategoryByBlogId(int id) {
        return null;
    }

    @Override
    public void deleteBlogById(int blogId) {

    }

    @Override
    public void deleteCategoryById(int id) {

    }
}
