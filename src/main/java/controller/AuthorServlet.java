package controller;

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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet (urlPatterns = "/author")

public class AuthorServlet extends HttpServlet {
    private BlogServiceImplement blogService = new BlogServiceImplement();
    private AccountServiceImplement accountService = new AccountServiceImplement();
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
        }
    }

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

    private void goToAuthorPage(HttpServletRequest req, HttpServletResponse resp) {
        Account account = (Account) req.getAttribute("currentUser");
        if (account == null) {
            account = accountService.findByUsernameOrEmail(req.getParameter("currentUser"));
            req.setAttribute("currentUser", account);
        }
        List<Blog> listBlog = blogService.findByAuthorId(account.getAccountID());
        List<CustomPair<Blog, Account>> listBlogAuthor = new ArrayList<CustomPair<Blog, Account>>();
        for (int i = 0; i < listBlog.size(); i++) {
            Account a = accountService.findById(listBlog.get(i).getAccountID());
            listBlogAuthor.add(new CustomPair<Blog, Account>(listBlog.get(i), a));
        }
        req.setAttribute("blogAuthor", listBlogAuthor);
        redirectPage(req, resp, "author.jsp");
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
