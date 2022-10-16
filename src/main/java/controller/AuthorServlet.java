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
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
//        System.out.println(id);
        blogService.deleteById(id);
        goToAuthorPage(req, resp);
    }

    private void postBlog(HttpServletRequest req, HttpServletResponse resp) {
        String imageSource = req.getParameter("imageSource");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String blogID = req.getParameter("blogID");
        HttpSession session = req.getSession();
        if (blogID.equals("") || blogService.findById(Integer.parseInt(blogID)) == null) {
            Blog a =  new Blog(title, content, 1, null, (Integer) session.getAttribute("accountID"), imageSource);
            //    title,content,status,createAt,accountID,image
            blogService.save(a);
        } else {
            Blog a =  new Blog(title, content, 1, null, (Integer) session.getAttribute("accountID"), imageSource);
            blogService.updateById(Integer.parseInt(blogID), a);
        }
        goToAuthorPage(req, resp);
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
        List<Blog> listBlog = blogService.findByAuthorId(account.getAccountID());
        List<CustomPair<Blog, Account>> listBlogAuthor = new ArrayList<CustomPair<Blog, Account>>();
        for (int i = 0; i < listBlog.size(); i++) {
            Account a = accountService.findById(listBlog.get(i).getAccountID());
            listBlogAuthor.add(new CustomPair<Blog, Account>(listBlog.get(i), a));
        }
        req.setAttribute("blogAuthor", listBlogAuthor);
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
