package dao.account;

import dao.IService;
import model.Account;

import java.sql.SQLException;
import java.util.List;

public interface IAccountService extends IService{

    public List findAll();
    Account findByUsernameOrEmail(String nameEmail);
    void updateByUsernameOrEmail(Account account);
    void deleteByUsernameOrEmail(String nameEmail);
    void save(Account account);
    Account findById(int id);
    List<Account> findByName(String name);
    void deleteById(int id);
}
