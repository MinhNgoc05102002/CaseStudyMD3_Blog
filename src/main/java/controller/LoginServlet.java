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
    AccountServiceImplement accountServiceImplement = new AccountServiceImplement();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action = request.getParameter("action");
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
                goAdminPage(request,response);
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
            case "login":
                checkLogin(request, response);
                break;
            case "create_account":
                createAnAccount(request,response);
                break;
            default:
                goAdminPage(request,response);
        }
    }


    private void goAdminPage(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            response.sendRedirect("admin.jsp");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Account account = accountServiceImplement.findByUsernameOrEmail(email);
        if (account == null || !account.getPassword().equals(password)) {
            response.sendRedirect("login.jsp");
        }
        else {
            request.setAttribute("account", account);
            if (account.getRole()==0) {
                response.sendRedirect("admin.jsp");
            }else {
                response.sendRedirect("login");
            }
        }
    }

    private void createAnAccount(HttpServletRequest request, HttpServletResponse response) {
    }
}
