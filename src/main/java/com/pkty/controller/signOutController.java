package com.pkty.controller;

import com.pkty.domain.User;
import com.pkty.persistance.EntityDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 * A simple servlet to sign in the user.
 * @author pnorby
 */

@WebServlet(
        urlPatterns = {"/signOut"}
)

public class signOutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Boolean signOut = false;
        String url;

        try {
            req.setAttribute("signedIn", signOut);
            url = "/index.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(req, resp);


        }
        catch(Exception e){
            e.printStackTrace();
            //LOG MESSAGE
        }


    }
}
