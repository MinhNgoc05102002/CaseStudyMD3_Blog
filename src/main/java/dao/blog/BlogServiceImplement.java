package dao.blog;

import dao.ConnectMySQL;
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

    public List<Blog> findFeatureBlog() {
        String sqlFindAll = "SELECT blog.* FROM case3.blog JOIN case3.category_blog ON blog.blogId = category_blog.blogId " +
                            "WHERE category_blog.categoryId = 8 LIMIT 4";

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
        String insert = "INSERT INTO `case3`.`blog` (`title`, `content`, `status`, `createAt`, `accountId`, `image`) " +
                "VALUES (?, ?, b?, now(), ?, ?);";
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
//        statement.setString(4, String.valueOf(blog.getCreateAt()));
        statement.setString(4, String.valueOf(blog.getAccountID()));
        statement.setString(5, blog.getImage());

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
        String sqlFindAll = "SELECT * FROM case3.blog where upper(blog.title) like  '%" + title.toUpperCase() + "%';";

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
        String deleteSQL = "DELETE FROM blog where blogId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(deleteSQL);
            statement.setString(1, String.valueOf(id));
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(int id, Blog blog) {
        String sqlUpdate = "UPDATE blog set title = ?, content = ?, status = ?, image = ?  WHERE blog.id = " + id + ";";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlUpdate);
            statement.setString(1, blog.getTitle());
            statement.setString(2, blog.getContent());
            statement.setString(3, String.valueOf(blog.getStatus()));
            statement.setString(4, blog.getImage());

            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Blog> findByAuthorId(int accountID) {
        String sqlFindAll = "SELECT * FROM case3.blog where accountID = " + accountID + ";";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlFindAll);

            List<Blog> result = new ArrayList<>();
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

    public String findAuthorNameById(int blogId) {
        String sqlfindAuthorNameById = "SELECT username FROM case3.account JOIN Blog ON account.accountId = blog.accountId " +
                                       "WHERE blogId = " + blogId + ";";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlfindAuthorNameById);

            String result = resultSet.getString("username");;
            return result;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
