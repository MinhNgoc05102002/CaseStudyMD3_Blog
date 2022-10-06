package dao.account;

import dao.ConnectMySQL;
import dao.IService;
import model.Account;

import java.sql.*;
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
                Account account = getAccountByResultSet(resultSet);
                result.add(account);
            }
            return result;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private PreparedStatement setPreparedStatement(PreparedStatement pre, Account account) throws SQLException {
        pre.setString(1, account.getUsername());
        pre.setString(2, account.getEmail());
        pre.setString(3, account.getFullname());
        pre.setString(4, account.getPassword());
        pre.setString(5, account.getPhoneNumber());
        pre.setString(6, account.getAddress());
        pre.setInt(7, account.getRole());
        pre.setInt(8, account.getRole());
        return pre;
    }

    @Override
    public void save(Account account) {
        String insert = "INSERT INTO account (full_name, username, password, address, email, phone_number, role_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = ConnectMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
             setPreparedStatement(preparedStatement, account).executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findByUsernameOrEmail(String nameEmail) {
            String selectAccount = "select * from case3.account where username = ? or email = ?; ";
            Account account = null;
            try(Connection con = ConnectMySQL.getConnection();
                PreparedStatement pre = con.prepareStatement(selectAccount)){
                pre.setString(1, nameEmail);
                pre.setString(2, nameEmail);
                ResultSet resultSet =pre.executeQuery();

                while (resultSet.next()){
                    account = getAccountByResultSet(resultSet);
                }
                if(account != null){
                    return account;
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            return account;
    }

    private Account getAccountByResultSet(ResultSet resultSet) throws SQLException {
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
        return new Account(username, email, fullname, password, phoneNumber, address, role, status);
    }

    @Override
    public void updateByUsernameOrEmail(Account account) {

    }

    @Override
    public void deleteByUsernameOrEmail(String nameEmail, Account account) {

    }
}
