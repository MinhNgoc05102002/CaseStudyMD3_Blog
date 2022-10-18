package controller;

import dao.account.AccountServiceImplement;
import dao.blog.BlogServiceImplement;
import dao.category.CategoryServiceImplement;
import dao.categoryBlog.CategoryBlogServiceImplement;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet (urlPatterns = "/author")

public class AuthorServlet extends HttpServlet {
    private BlogServiceImplement blogService = new BlogServiceImplement();
    private CategoryServiceImplement categoryService = new CategoryServiceImplement();
    private AccountServiceImplement accountService = new AccountServiceImplement();
    private CategoryBlogServiceImplement categoryBlogService = new CategoryBlogServiceImplement();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "authorPage":
                goToAuthorPage(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "authorPage":
                goToAuthorPage(req, resp);
                break;
            case "postBlog":
                postBlog(req, resp);
                break;
            case "deleteBlog":
                deleteBlog(req, resp);
                break;
        }
    }

    private void deleteBlog(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        this.categoryBlogService.deleteBlogById(id);
        blogService.deleteById(id);
        goToAuthorPage(req, resp);
    }

    private void postBlog(HttpServletRequest req, HttpServletResponse resp) {
        String imageSource = req.getParameter("imageSource");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String blogID = req.getParameter("blogID");
        HttpSession session = req.getSession();
        int newBlogID;

        if (blogID.equals("") || blogService.findById(Integer.parseInt(blogID)) == null) {
            Blog a =  new Blog(title, content, 1, null, (Integer) session.getAttribute("accountID"), imageSource);
            //    title,content,status,createAt,accountID,image
            blogService.save(a);
//            for (int i = 0; i < categoryService.findAll().size(); i++) {
//                if(req.getParameter(String.valueOf(categoryService.findAll().get(i).getCategoryID())) != null) {
//                    CategoryBlog categoryBlog = new CategoryBlog(categoryService.findAll().get(i).getCategoryID(), blogService.getMaxBlogId());
//                    categoryBlogService.save(categoryBlog);
//                }
//            }
            newBlogID = blogService.getMaxBlogId();
        } else {
            newBlogID = Integer.parseInt(blogID);
            Blog a =  new Blog(title, content, 1, null, (Integer) session.getAttribute("accountID"), imageSource);
            blogService.updateById(Integer.parseInt(blogID), a);
        }
        categoryBlogService.deleteBlogById(newBlogID);
        for (int i = 0; i < categoryService.findAll().size(); i++) {
            if(req.getParameter(String.valueOf(categoryService.findAll().get(i).getCategoryID())) != null) {
                CategoryBlog categoryBlog = new CategoryBlog(categoryService.findAll().get(i).getCategoryID(), newBlogID);
                categoryBlogService.save(categoryBlog);
            }
        }
        goToAuthorPage(req, resp);
    }

    private void goToAuthorPage(HttpServletRequest req, HttpServletResponse resp) {
        Account account = (Account) req.getAttribute("currentUser");

        if (account == null) {
            if (req.getParameter("currentUser") != null) {
                account = accountService.findByUsernameOrEmail(req.getParameter("currentUser"));
            }
            else {
                HttpSession session = req.getSession();
                if (session.getAttribute("username") != null) {
                    account = accountService.findByUsernameOrEmail((String) session.getAttribute("username"));
                }
                else {
                    redirectPage(req, resp, "/");
                }
            }
            req.setAttribute("currentUser", account);
        }
        if (account==null) {
            try {
                resp.sendRedirect("/");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        List<Category> listAllCategory = categoryService.findAll();
        for(Category c : listAllCategory) System.out.println(c.getCategoryID());
        req.setAttribute("listAllCategory", listAllCategory);



        List<Blog> listBlog = blogService.findByAuthorId(account.getAccountID());

//        List<CustomPair<Blog, Account>> listBlogAuthor = new ArrayList<CustomPair<Blog, Account>>();
        List<CustomPair<Blog, String>>  listBlogCategory = new ArrayList<CustomPair<Blog, String>>();
        String categoryString = "";
        for (int i = 0; i < listBlog.size(); i++) {
            List<Category> categoryList = categoryBlogService.findCategoryByBlogId(listBlog.get(i).getBlogID());
            categoryString = "";
            if(categoryList.size() > 0) categoryString = String.valueOf(categoryList.get(0).getCategoryID());

            for(int j=1; j<categoryList.size(); j++)
                categoryString += "," + String.valueOf(categoryList.get(j).getCategoryID());
            System.out.println(categoryString);
            listBlogCategory.add(new CustomPair<Blog, String>(listBlog.get(i), categoryString));

        }

//        req.setAttribute("blogAuthor", listBlogAuthor);
        req.setAttribute("listBlogCategory", listBlogCategory);

        if (account.getRole() == 0){
            redirectPage(req, resp, "author.jsp");
        }
        else {
            try {
                resp.sendRedirect("/admin");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void redirectPage(HttpServletRequest request, HttpServletResponse response, String url){
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
