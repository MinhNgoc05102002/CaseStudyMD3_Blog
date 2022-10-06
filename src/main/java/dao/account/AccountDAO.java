package dao.account;

import dao.ConnectMySQL;
import dao.IService;
import model.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements IService {
    private Connection connection = ConnectMySQL.getConnection();
    @Override
    public List<Account> findAll() throws SQLException {
        String sqlFindAll = "SELECT * FROM account";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlFindAll);

            ArrayList<Account> result = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("username");
                String password = resultSet.getString("password");
                int type = resultSet.getInt("role");
                Account account = new Account();
                result.add(account);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public List findByName(String name) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void updateById(int id, Object o) {

    }

    @Override
    public void deleteById(int id, Object o) {

    }
}
