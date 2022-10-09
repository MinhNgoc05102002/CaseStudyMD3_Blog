package dao.category;

import dao.IService;
import model.Account;
import model.Category;

import java.util.List;

public interface ICategoryService extends IService<Category> {

    public List findAll();
    void save(Category category);
    Category findById(int id);
    List<Category> findByName(String name);
    void deleteById(int id);
}
