package controller;

import dao.account.AccountServiceImplement;
import dao.blog.BlogServiceImplement;
import dao.category.CategoryServiceImplement;
import dao.categoryBlog.CategoryBlogServiceImplement;
import model.Account;
import model.Blog;
import model.Category;
import model.CustomPair;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PostServlet", value = "/post")
public class PostServlet extends HttpServlet {

    private BlogServiceImplement blogService = new BlogServiceImplement();
    private AccountServiceImplement accountService = new AccountServiceImplement();
    private CategoryServiceImplement categoryService = new CategoryServiceImplement();
    private CategoryBlogServiceImplement categoryBlogService = new CategoryBlogServiceImplement();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                goToPostPage(request, response);
        }
    }

    private void goToPostPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String blogID = request.getParameter("blogID");
        String accountID = request.getParameter("accountID");

        Blog blog = blogService.findById(Integer.parseInt(blogID));
        Account account = accountService.findById(Integer.parseInt(accountID));
        List<Category> categoryList = categoryBlogService.findCategoryByBlogId(Integer.parseInt(blogID));

        request.setAttribute("blog", blog);
        request.setAttribute("author", account);
        request.setAttribute("categoryList", categoryList);

//        List related blog
        List<Blog> blogList = blogService.findByAuthorId(blog.getAccountID());

        for (Category category : categoryList) {
            blogList.addAll(categoryBlogService.findBlogByCategoryId(category.getCategoryID()));
        }

        List<CustomPair<Blog, Account>> relatedBlogAuthorList = new ArrayList<>();
        for (int i = 0; i < blogList.size() && i < 3; i++) {
            if (blogList.get(i).getStatus() == 0) {
                i--;
                continue;
            }
            Account a = accountService.findById(blogList.get(i).getAccountID());
            relatedBlogAuthorList.add(new CustomPair<Blog, Account>(blogList.get(i),a));
        }

        request.setAttribute("relatedBlogAuthorList", relatedBlogAuthorList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("post.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                goToPostPage(request, response);
        }
    }
}
