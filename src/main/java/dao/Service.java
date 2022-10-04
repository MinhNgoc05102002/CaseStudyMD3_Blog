package dao;

import model.Account;

import java.util.List;

public interface Service {
    List<Account> findAll();
    void save(Account account);
    int findIndexById(int id);
    Account findById(int id);
    List<Account> findByName(String name);
    void deleteById(int id);
    void updateById(int id, Account account);
    void deleteById(int id, Account account);
}
