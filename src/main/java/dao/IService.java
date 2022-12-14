package dao;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    List<T> findAll();
    void save(T t);
    T findById(int id);
    List<T> findByName(String name);
    void deleteById(int id);
    void updateById(int id, T t);
}
