package dao.account;

import dao.ConnectMySQL;
import dao.IService;
import model.Account;

import java.sql.Connection;
import java.util.List;

public class AccountDAO implements IService {
    private Connection connection = ConnectMySQL.getConnection();
    @Override
    public List<Account> findAll() {
         String sqlGetAll = "SELECT * FROM account";
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
