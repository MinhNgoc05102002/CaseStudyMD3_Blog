package dao.blog;

import dao.IService;
import model.Account;
import model.Blog;

import java.util.List;

public interface IBlogService extends IService<Blog> {
    public List findAll();
    void save(Blog blog);
    Blog findById(int id);
    List<Blog> findByName(String title);
    void deleteById(int id);
    void updateById(int id, Blog blog);

    List findByAuthorId(int authorId);
}
