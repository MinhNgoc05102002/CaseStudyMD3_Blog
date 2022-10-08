package controller;

import dao.account.AccountServiceImplement;
import model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/log-in")
public class LoginServlet extends HttpServlet {
    private String action;
    private AccountServiceImplement accountServiceImplement = new AccountServiceImplement();
    private RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "login":
                redirectPage(request, response, "login/login.jsp");
                break;
            case "register":
                redirectPage(request, response, "login/register.jsp");
                break;
            default:
                redirectPage(request, response,"index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        action = request.getParameter("action");
        if (action==null) {
            action = "";
        }

        switch (action) {
            case "submitLogin":
                checkLogin(request, response);
                break;
            case "registration":
                createAnAccount(request,response);
                break;
            default:
                redirectPage(request, response,"index.jsp");
        }
    }

    private void showRegisterPage(HttpServletRequest request, HttpServletResponse response) {
        redirectPage(request, response, "register.jsp");
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Account account = accountServiceImplement.findByUsernameOrEmail(email);

        request.setAttribute("account", account);
        if (account == null || !account.getPassword().equals(password)) {
            redirectPage(request, response, "login.jsp");
        }
        else {
            switch (account.getRole()) {
                case 0:
                    redirectPage(request, response, "author.jsp");
                    break;
                case 1:
                    redirectPage(request, response, "admin.jsp");
                    break;
                default:
                    redirectPage(request, response, "index.jsp");
            }
        }
    }

    private void redirectPage(HttpServletRequest request, HttpServletResponse response, String url){
        try {
            dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createAnAccount(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //check validate

        if (accountServiceImplement.findByUsernameOrEmail(username) == null &&
                accountServiceImplement.findByUsernameOrEmail(email) == null) {
            Account account = new Account(username, email, fullName, password);
            accountServiceImplement.save(account);
            redirectPage(request, response, "login/login.jsp");
        }
    }
}
