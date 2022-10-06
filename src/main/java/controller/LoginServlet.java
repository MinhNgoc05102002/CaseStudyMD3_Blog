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
    AccountServiceImplement accountServiceImplement = new AccountServiceImplement();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "login":
                checkLogin(request, response);
                break;
            case "create_account":
                createAnAccount(request,response);
                break;
            default:
                returnHomePage(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null) {
            action = "";
        }

        switch (action) {
            case "login":
                checkLogin(request, response);
                break;
            case "create_account":
                createAnAccount(request,response);
                break;
            default:
                returnHomePage(request,response);
        }
    }


    private void returnHomePage(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        try {
            dispatcher.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Account account = accountServiceImplement.findByUsernameOrEmail(email);
        if (account == null || !account.getPassword().equals(password)) {

        }
        else {
            request.setAttribute("account", account);
            returnHomePage(request,response);
        }
    }

    private void createAnAccount(HttpServletRequest request, HttpServletResponse response) {
    }
}
