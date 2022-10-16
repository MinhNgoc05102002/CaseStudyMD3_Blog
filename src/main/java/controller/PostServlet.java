package controller;

import dao.account.AccountServiceImplement;
import dao.blog.BlogServiceImplement;
import dao.category.CategoryServiceImplement;
import dao.categoryBlog.CategoryBlogServiceImplement;
import model.Account;
import model.Blog;
import model.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
