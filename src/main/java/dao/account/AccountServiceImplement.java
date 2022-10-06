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

public class AccountServiceImplement implements IAccountService {
    private Connection connection = ConnectMySQL.getConnection();

//    public static void main(String[] args) {
//        new AccountServiceImplement().findAll();
//    }

    @Override
    public List<Account> findAll(){
        String sqlFindAll = "SELECT * FROM case3.account";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlFindAll);

            ArrayList<Account> result = new ArrayList<>();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String fullname = resultSet.getString("fullname");
                String password = resultSet.getString("password");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                int role = resultSet.getInt("role");
                int status = resultSet.getInt("status");
                Account account = new Account(username, email,fullname, password, phoneNumber, address, role, status);
                result.add(account);
            }
            return result;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Account account) {

    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public Account findById(int id) {
        return null;
    }

    @Override
    public List<Account> findByName(String name) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void updateById(int id, Account account) {

    }

    @Override
    public void deleteById(int id, Account account) {

    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void updateById(int id, Object o) {

    }

    @Override
    public void deleteById(int id, Object o) {

    }

}
