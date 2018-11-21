package com.pkty.controller;

import com.pkty.application.EntityManager;
import com.pkty.domain.User;
import com.pkty.shared.ManagerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * A simple servlet to sign in the user.
 * @author pnorby
 */

@WebServlet(
        urlPatterns = {"/keyController"}
)

public class keyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager<User> entityManager = ManagerFactory.getManager(User.class);
            HttpSession session = req.getSession();
            User currentUser = (User)session.getAttribute("currentUser");
            System.out.println(currentUser.getFirstName());
            String userApiKey = getNewApiKey();
            currentUser.setApiKey(userApiKey);
            entityManager.update(currentUser);
            Boolean loggedIn = true;
            String url = "/signinController?userName=" + currentUser.getUsername() + "&password=" + currentUser.getPassword();


            req.setAttribute("signedIn", loggedIn);
            req.setAttribute("userKey", userApiKey);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(req, resp);

    }

    private String getNewApiKey(){
        String theKey = null;

        theKey = UUID.randomUUID().toString().replace("-","");

        System.out.println(theKey);
        return theKey;
    }
}
