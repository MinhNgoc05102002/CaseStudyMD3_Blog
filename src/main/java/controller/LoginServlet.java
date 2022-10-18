package controller;

import dao.account.AccountServiceImplement;
import model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        HttpSession session = request.getSession();
        switch (action) {
            case "login":
                session.removeAttribute("fullName");
                redirectPage(request, response, "login/login.jsp");
                break;
            case "repass":
                session.removeAttribute("fullName");
                redirectPage(request, response, "login/repass.jsp");
                break;
            case "register":
                session.removeAttribute("fullName");
                redirectPage(request, response, "login/register.jsp");
                break;
            default:
                redirectPage(request, response,"login/login.jsp");
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
            case "submitRepass":
                handleRepass(request,response);
                break;
            default:
                redirectPage(request, response,"login/login.jsp");
        }
    }

    private void handleRepass(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String phoneNumber = request.getParameter("phoneNumber");
        String newPassword = request.getParameter("newpass");
        //check validate
        if (accountServiceImplement.findByUsernameOrEmail(username).getPhoneNumber().equals(phoneNumber)) {

            Account account = accountServiceImplement.findByUsernameOrEmail(username);
            account.setPassword(newPassword);
            System.out.println(account.getPassword());
            accountServiceImplement.updateById(accountServiceImplement.findByUsernameOrEmail(username).getAccountID(),account);
            redirectPage(request, response, "login/login.jsp");
        }
        else {
            redirectPage(request, response, "login/repass.jsp");
        }
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Account account = accountServiceImplement.findByUsernameOrEmail(email);

        updateSessionData(request, account);

        if (account == null || !account.getPassword().equals(password)) {
            redirectPage(request, response, "login/login.jsp");
        }
        else {
            switch (account.getRole()) {
                case 0:
                    request.setAttribute("currentUser",account);
                    redirectPage(request, response, "/author?action=authorPage");
                    break;
                case 1:
                    redirectPage(request, response, "/admin");
                    System.out.println("sag admin");
                    break;
                default:
                    redirectPage(request, response, "");
            }
        }
    }

    private void updateSessionData(HttpServletRequest request, Account account) {
        HttpSession session = request.getSession();
        if (account == null) {
            session.invalidate();
        }
        else {
            session.setAttribute("fullName", account.getFullname());
            session.setAttribute("accountID", account.getAccountID());
            session.setAttribute("username", account.getUsername());
            session.setAttribute("password", account.getPassword());
            session.setAttribute("phoneNumber", account.getPhoneNumber());
            session.setAttribute("role", account.getRole());
            session.setAttribute("status", account.getStatus());
            session.setMaxInactiveInterval(40000);
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
