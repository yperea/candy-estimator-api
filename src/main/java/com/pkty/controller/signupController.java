package com.pkty.controller;

import com.pkty.application.EntityManager;
import com.pkty.domain.User;
import com.pkty.persistance.EntityDAO;
import com.pkty.shared.ManagerFactory;

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
import java.util.List;

/**
 * A simple servlet to sign in the user.
 * @author pnorby
 */

@WebServlet(
        urlPatterns = {"/signupController"}
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
        EntityManager<User> userEntityMgr = ManagerFactory.getManager(User.class);

        List<User> users;
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

                users = userEntityMgr.getListEquals("username", uName);

                if (users.size() == 0) {
                    newUser = new User(fName, lName, uName, pWord);
                    userEntityMgr.create(newUser);

                    responseMessage = "SUCCESS!";
                }

            }

        }
        catch(Exception e){
            e.printStackTrace();
            //LOG MESSAGE
        }
        resp.setHeader("Refresh", "3; URL=signInDirector");
        resp.setContentType("text/html");
        PrintWriter  out  = resp.getWriter();
        out.print("<h1>" + responseMessage + "</h1>");
        out.close();

    }
}
