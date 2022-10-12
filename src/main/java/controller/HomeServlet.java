package controller;

//import javafx.*;
import dao.account.AccountServiceImplement;
import dao.blog.BlogServiceImplement;
import model.Account;
import model.Blog;

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
    BlogServiceImplement blogServiceImplement = new BlogServiceImplement();
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
            default:
                returnHomePage(request,response);
        }
    }

    private void returnHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> listAllBlog = this.blogServiceImplement.findAll();

        AccountServiceImplement accountServiceImp = new AccountServiceImplement();

//        List<Pair<Blog, Account>> listAuthorBlog = new ArrayList<>();

//        for (int i = 0; i < listAllBlog.size(); i++) {
//            Account account = accountServiceImp.findById(listAllBlog.get(i).getAccountID());
//            listAuthorBlog.add(new Pair<>(listAllBlog.get(i), account));
//        }
//
//        request.setAttribute("listAuthorBlog", listAuthorBlog);

        request.setAttribute("listAllBlog", listAllBlog);

        List<Blog> listFeatureBlog = this.blogServiceImplement.findFeatureBlog();
        request.setAttribute("listFeatureBlog", listFeatureBlog);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

        dispatcher.forward(request, response);
    }
}