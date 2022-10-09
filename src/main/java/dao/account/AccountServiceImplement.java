package dao.account;

import dao.ConnectMySQL;
import model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceImplement implements IAccountService {
    private Connection connection = ConnectMySQL.getConnection();

    @Override
    public List<Account> findAll(){
        String sqlFindAll = "SELECT * FROM case3.account";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlFindAll);

            ArrayList<Account> result = new ArrayList<>();
            while (resultSet.next()) {
                Account account = getAccountByResultSet(resultSet);
                result.add(account);
            }
            return result;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    private PreparedStatement setPreparedStatement(PreparedStatement statement, Account account) throws SQLException {

        //username, email, fullname, password, phoneNumber, address, role, status

        statement.setString(1, account.getUsername());
        statement.setString(2, account.getEmail());
        statement.setString(3, account.getFullname());
        statement.setString(4, account.getPassword());
        statement.setString(5, account.getPhoneNumber());
        statement.setString(6, account.getAddress());

        statement.setInt(7, account.getRole());
        statement.setInt(8, account.getRole());

        statement.setString(7, String.valueOf(account.getRole()));
        statement.setString(8, String.valueOf(account.getStatus()));

        return statement;
    }

    @Override
    public Account findById(int id) {
        return null;
    }

    @Override
    public List findByName(String name) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        String deleteSQL = "DELETE FROM account where accountID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(deleteSQL);
            statement.setInt(1, id);
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(int id, Object o) {
    }
    @Override
    public void save(Object o) {

    }
    @Override
    public void save(Account account) {
        String insert = "INSERT INTO `case3`.`account` (`username`, `email`, `fullname`, `password`, `phoneNumber`, `address`, `role`, `status`) " +
                "VALUES (?, ?, ?, ?, ?, ?, b?, b?);";
        try (Connection connection = ConnectMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
             setPreparedStatement(preparedStatement, account).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        String insert = "INSERT INTO account (username, email, fullname, password, phoneNumber, address, role, status) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
//        try (Connection connection = ConnectMySQL.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
//             setPreparedStatement(preparedStatement, account).execute();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public Account findByUsernameOrEmail(String nameEmail) {
            String selectAccount = "select * from case3.account where username = ? or email = ?; ";
            Account account = null;
            try(Connection con = ConnectMySQL.getConnection();
                PreparedStatement statement = con.prepareStatement(selectAccount)){
                statement.setString(1, nameEmail);
                statement.setString(2, nameEmail);
                ResultSet resultSet =statement.executeQuery();

                while (resultSet.next()){
                    account = getAccountByResultSet(resultSet);
                }
                if(account != null){
                    return account;
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            return null;
    }

    private Account getAccountByResultSet(ResultSet resultSet) throws SQLException {
        int accountID = resultSet.getInt("accountID");
        String username = resultSet.getString("username");
        if (username == null || username.equals("")) {
            return null;
        }
        String email = resultSet.getString("email");
        String fullname = resultSet.getString("fullname");
        String password = resultSet.getString("password");
        String phoneNumber = resultSet.getString("phoneNumber");
        String address = resultSet.getString("address");
        int role = resultSet.getInt("role");
        int status = resultSet.getInt("status");
        return new Account(accountID, username, email, fullname, password, phoneNumber, address, role, status);
    }

    @Override
    public void updateByUsernameOrEmail(Account account) {

    }

    @Override
    public void deleteByUsernameOrEmail(String nameEmail) {
        String deleteSQL = "DELETE FROM username where accountID = ? OR email = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(deleteSQL);
            statement.setString(1, nameEmail);
            statement.setString(2, nameEmail);
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
