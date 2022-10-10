package dao.categoryBlog;

import dao.IService;
import model.Blog;
import model.Category;
import model.CategoryBlog;

import java.util.List;

public interface ICategoryBlogService {
    void save(CategoryBlog categoryBlog);
    List<Blog> findBlogByCategoryId(int id);
    List<Category> findCategoryByBlogId(int id);

    void deleteBlogById(int blogId);

    void deleteCategoryById(int id);
}
