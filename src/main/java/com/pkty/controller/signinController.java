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

/**
 * A simple servlet to register the user.
 * @author pnorby
 */

@WebServlet(
        urlPatterns = {"/signinController"}
)
public class signinController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //add user
        String uName = req.getParameter("userName");
        String pWord = req.getParameter("password");

        EntityDAO<User> userDao = new EntityDAO<>(User.class);
        LocalDate today = LocalDate.now();
        User newUser = null;
        Boolean userOkay = false;

        try {

            if (uName.isEmpty() || pWord.isEmpty()) {

            }else
            {
                userOkay = true;
            }

            if (userOkay) {

            }
        }
        catch(Exception e){
            e.printStackTrace();
            //LOG MESSAGE
        }

    }
}
