package controller;

import dao.account.AccountServiceImplement;
import dao.blog.BlogServiceImplement;
import dao.category.CategoryServiceImplement;
import dao.categoryBlog.CategoryBlogServiceImplement;
import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
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
                goToAdminPage(request, response);
        }
    }

    private void goToAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> listAllBlog = blogService.findAll();
        List<Account> listAllAccounts = accountService.findAll();
        List<Category> listAllCategory = categoryService.findAll();
//        List<CategoryBlog> categoryBlog = categoryBlogService.findAll();

        request.setAttribute("listAllBlog", listAllBlog);
        request.setAttribute("listAllAccounts", listAllAccounts);
        request.setAttribute("listAllCategory", listAllCategory);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
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
                goToAdminPage(request, response);
        }
    }
}