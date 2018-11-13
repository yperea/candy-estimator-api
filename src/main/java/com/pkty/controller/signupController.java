package com.pkty.controller;

import com.pkty.domain.User;
import com.pkty.persistance.EntityDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.io.*;

/**
 * A simple servlet to sign in the user.
 * @author pnorby
 */

@WebServlet(
        urlPatterns = {"/public/signupController"}
)

public class signupController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //add user
        String fName = req.getParameter("firstName");
        String lName = req.getParameter("lastName");
        String uName = req.getParameter("userName");
        String pWord = req.getParameter("password");
        String confirm = req.getParameter("confirmation");
        EntityDAO<User> userDao = new EntityDAO<>(User.class);
        LocalDate today = LocalDate.now();
        User newUser = null;
        Boolean userOkay = false;
        String responseMessage = "Sorry, Please Try Again";
        int newUserId;

        try {
            //Check any fields are empty
            if (fName.isEmpty() || lName.isEmpty() || uName.isEmpty() || pWord.isEmpty() || confirm.isEmpty()) {

            } else if (!(pWord.equals(confirm))) {

            } else {
                userOkay = true;
            }

            if (userOkay) {
                newUser = new User(fName, lName, uName, pWord);
                newUserId = userDao.insert(newUser);

                responseMessage = "SUCCESS!";

            }

        }
        catch(Exception e){
            e.printStackTrace();
            //LOG MESSAGE
        }
        resp.setHeader("Refresh", "3; URL=signin.jsp");
        resp.setContentType("text/html");
        PrintWriter  out  = resp.getWriter();
        out.print("<h1>" + responseMessage + "</h1>");
        out.close();

    }
}
