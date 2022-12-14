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
            case "blockAccount":
                handleBlockAccount(request, response);
                break;
            case "hideBlog":
                handleHideBlog(request, response);
                break;
            case "deleteBlog":
                handleDelBlog(request, response);
                break;
            default:
                goToAdminPage(request, response);
        }
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

    private void handleDelBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("blogId"));
        this.categoryBlogService.deleteBlogById(id);
        this.blogService.deleteById(id);
        goToAdminPage(request, response);
    }

    private void handleHideBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("blogId"));
        Blog blog = blogService.findById(id);
        if(this.blogService.findById(id).getStatus() == 1)
            blog.setStatus(0);
        else
            blog.setStatus(1);

        this.blogService.updateById(id, blog);

        goToAdminPage(request, response);
    }

    private void handleBlockAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("accId"));
        Account account = accountService.findById(id);
        if(this.accountService.findById(id).getStatus() == 1 && account.getRole() == 0)
            account.setStatus(0);
        else
            account.setStatus(1);

        this.accountService.updateById(id, account);

        goToAdminPage(request, response);
    }

    private void goToAdminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> listAllBlog = blogService.findAll();
        List<Account> listAllAccounts = accountService.findAll();
        List<Category> listAllCategory = categoryService.findAll();
        request.setAttribute("listAllAccounts", listAllAccounts);
        request.setAttribute("listAllCategory", listAllCategory);

        List<CustomPair<Blog, String>> listBlogCategory = new ArrayList<>();
        for(int i=0; i<listAllBlog.size(); i++){
            List<Category> listCategory = categoryBlogService.findCategoryByBlogId(listAllBlog.get(i).getBlogID());
            String categoryString = "nothing";
            if(listCategory.size() > 0){
                categoryString = listCategory.get(0).getName();
            }
            for(int j=1; j<listCategory.size(); j++) {
                categoryString += ", " + listCategory.get(j).getName();
            }
            listBlogCategory.add(new CustomPair<Blog, String>(listAllBlog.get(i), categoryString));
        }
        request.setAttribute("listBlogCategory", listBlogCategory);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    }
}
