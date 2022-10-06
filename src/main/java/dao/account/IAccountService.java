package dao.account;

import dao.IService;
import model.Account;

import java.sql.SQLException;
import java.util.List;

public interface IAccountService{

    public List findAll();
    void save(Account account);
    Account findByUsernameOrEmail(String nameEmail);
    void updateByUsernameOrEmail(Account account);
    void deleteByUsernameOrEmail(String nameEmail, Account account);
}
