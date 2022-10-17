package controller;

//import javafx.*;
import dao.account.AccountServiceImplement;
import dao.blog.BlogServiceImplement;
import model.Account;
import model.Blog;
import model.CustomPair;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "")
public class HomeServlet extends HttpServlet {
    BlogServiceImplement blogService = new BlogServiceImplement();
    AccountServiceImplement accountService = new AccountServiceImplement();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "login":
                break;
            case "create_account":
                break;
            case "searchBlog":
                searchBlog(request, response, request.getParameter("title"));
                break;
            default:
                returnHomePage(request,response);
        }
    }

    private void searchBlog(HttpServletRequest request, HttpServletResponse response, String title) {
        List<Blog> blogList = blogService.findLikeTitle(title);
        List<CustomPair<Blog, Account>> listBlogAuthor = new ArrayList<>();
        for (int i = 0; i < blogList.size(); i++) {
            Account account = accountService.findById(blogList.get(i).getAccountID());
            listBlogAuthor.add(new CustomPair<Blog, Account>(blogList.get(i), account));
        }
        request.setAttribute("searchBlog", "true");
        request.setAttribute("searchBlogAuthor", listBlogAuthor);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void returnHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> listAllBlog = blogService.findAll();
        List<CustomPair<Blog, Account>> listBlogAuthor = new ArrayList<>();
        for (int i = 0; i < listAllBlog.size(); i++) {
            Account a = accountService.findById(listAllBlog.get(i).getAccountID());
            listBlogAuthor.add(new CustomPair<Blog, Account>(listAllBlog.get(i), a));
        }
        request.setAttribute("blogAuthor", listBlogAuthor);


        List<Blog> listFeatureBlog = this.blogService.findFeatureBlog();
        List<CustomPair<Blog, Account>> listFeatureBlogAuthor = new ArrayList<>();
        for (int i = 0; i < listFeatureBlog.size(); i++) {
            Account a = accountService.findById(listFeatureBlog.get(i).getAccountID());
            listFeatureBlogAuthor.add(new CustomPair<Blog, Account>(listFeatureBlog.get(i), a));
        }
        request.setAttribute("FeatureBlogAuthor", listFeatureBlogAuthor);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}