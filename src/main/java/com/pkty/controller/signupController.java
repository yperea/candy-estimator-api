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
        int newUserId;

        try {
            System.out.println("made it to servlet");
            if (fName.isEmpty() || lName.isEmpty() || uName.isEmpty() || pWord.isEmpty() || confirm.isEmpty()) {

            } else if (!(pWord.equals(confirm))) {

            } else {
                userOkay = true;
            }

            if (userOkay) {
                newUser = new User(fName, lName, uName, pWord);
                newUserId = userDao.insert(newUser);

            }
        }
        catch(Exception e){
            e.printStackTrace();
            //LOG MESSAGE
        }

        resp.setHeader("Refresh", "3; URL=signin.jsp");

    }
}
