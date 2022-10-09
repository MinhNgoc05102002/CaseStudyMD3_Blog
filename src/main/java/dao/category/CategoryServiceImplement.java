package dao.category;

import dao.ConnectMySQL;
import model.Account;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImplement implements ICategoryService {
    private Connection connection = ConnectMySQL.getConnection();

    @Override
    public List<Category> findAll(){
        String sqlFindAll = "SELECT * FROM case3.category";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlFindAll);

            ArrayList<Category> listResult = new ArrayList<>();
            while (resultSet.next()) {
                Category category = getCategoryByResultSet(resultSet);
                listResult.add(category);
            }
            return listResult;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List findByName(String name) {
        String findQuery = "SELECT * FROM category where name = " +name;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(findQuery);
            ArrayList<Category> listResult = new ArrayList<>();
            while (resultSet.next()) {
                Category category = getCategoryByResultSet(resultSet);
                listResult.add(category);
            }
            return listResult;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        String deleteSQL = "DELETE FROM category where categoryID = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.execute(deleteSQL);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(int id, Category category) {
        String sqlUpdate = "UPDATE category SET name = " + category.getName() +
                ", description = " + category.getDescription() + " WHERE categoryID = " + id + ";";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sqlUpdate);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Category category) {
        String insert = "INSERT INTO `case3`.`category` (`name`, `description`) " +
                "VALUES (?, ?);";
        try (Connection connection = ConnectMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
             preparedStatement.setString(1, category.getName());
             preparedStatement.setString(2, category.getDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category findById(int id) {
        String sqlFindByID = "SELECT * FROM category WHERE categoryID = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlFindByID);
            while (resultSet.next()) {
                int categoryID = resultSet.getInt("categoryID");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                return new Category(categoryID, name, description);
            }
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

    public static void main(String[] args) {
        CategoryServiceImplement categoryServiceImplement = new CategoryServiceImplement();

        Category category = categoryServiceImplement.findById(7);
        category.setName("Health1");
        categoryServiceImplement.updateById(7, category);


    }
}
