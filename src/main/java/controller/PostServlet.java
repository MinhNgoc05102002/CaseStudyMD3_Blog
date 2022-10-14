package controller;

import dao.blog.BlogServiceImplement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PostServlet", value = "/post")
public class PostServlet extends HttpServlet {

    private BlogServiceImplement blogService = new BlogServiceImplement();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        String action = request.getParameter("action");
////        if (action == null) {
////            action = "";
////        }
////        switch (action) {
////            default:
////                goToAdminPage(request, response);
////        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        if (action == null) {
////            action = "";
////        }
////        switch (action) {
////            default:
////                goToAdminPage(request, response);
////        }
//    }
}
