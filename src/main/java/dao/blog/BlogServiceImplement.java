package dao.blog;

import dao.ConnectMySQL;
import model.Account;
import model.Blog;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogServiceImplement implements IBlogService {
    private Connection connection = ConnectMySQL.getConnection();

    @Override
    public List<Blog> findAll() {
        String sqlFindAll = "SELECT * FROM case3.blog";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlFindAll);

            ArrayList<Blog> result = new ArrayList<>();
            while (resultSet.next()) {
                Blog blog = getBlogByResultSet(resultSet);
                result.add(blog);
            }
            return result;
        }catch (Exception e) {
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
    public void save(Blog blog) {
        String insert = "INSERT INTO `case3`.`blog` (`title`, `content`, `status`, `createAt`, `accountId`) " +
                "VALUES (?, ?, b?, ?, ?);";
        try (Connection connection = ConnectMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            setPreparedStatement(preparedStatement, blog).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement setPreparedStatement(PreparedStatement statement, Blog blog) throws SQLException {
        statement.setString(1, blog.getTitle());
        statement.setString(2, blog.getContent());
        statement.setString(3, String.valueOf(blog.getStatus()));
        statement.setString(4, String.valueOf(blog.getCreateAt()));
        statement.setString(5, String.valueOf(blog.getAccountID()));

        return statement;
    }

    @Override
    public Blog findById(int blogId) {
        String selectBlog = "select * from case3.blog where blogId = ?;";

        Blog blog = null;
        try(Connection con = ConnectMySQL.getConnection();
            PreparedStatement statement = con.prepareStatement(selectBlog)){
            statement.setInt(1, blogId);
            ResultSet resultSet =statement.executeQuery();

            while (resultSet.next()){
                blog = getBlogByResultSet(resultSet);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return blog;
    }

    @Override
    public List<Blog> findByName(String title) {
        String sqlFindAll = "SELECT * FROM case3.blog where blog.title like  "";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlFindAll);

            ArrayList<Blog> result = new ArrayList<>();
            while (resultSet.next()) {
                Blog blog = getBlogByResultSet(resultSet);
                result.add(blog);
            }
            return result;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void updateById(int id, Blog blog) {

    }

    @Override
    public List<Blog> findByAuthorId(int authorId) {

    }
}
