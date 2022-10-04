package dao;

import java.sql.Connection;

public class AccountDAO {
    private Connection connection = ConnectMySQL.getConnection();
}
