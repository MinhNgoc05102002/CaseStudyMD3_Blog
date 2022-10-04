package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/log-in")
public class LoginServlet extends HttpServlet {
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("dispatcher");
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("dispatcher");

    }

    private void createAnAccount(HttpServletRequest request, HttpServletResponse response) {
    }
}
