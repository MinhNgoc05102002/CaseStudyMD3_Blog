package dao.account;

import dao.IService;
import model.Account;

import java.sql.SQLException;
import java.util.List;

public interface IAccountService extends IService{

    @Override
    public List findAll();
    void save(Account account);
    int findIndexById(int id);
    Account findById(int id);
    List<Account> findByName(String name);
    void deleteById(int id);
    void updateById(int id, Account account);
    void deleteById(int id, Account account);
}
