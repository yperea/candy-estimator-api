package com.pkty.controller;

import com.pkty.application.EntityManager;
import com.pkty.domain.User;
import com.pkty.persistance.EntityDAO;
import com.pkty.shared.ManagerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

/**
 * A simple servlet to register the user.
 * @author pnorby
 */

@WebServlet(
        urlPatterns = {"/signinController"}
)
public class signinController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //add user
        String uName = req.getParameter("userName");
        String pWord = req.getParameter("password");
        HttpSession session = req.getSession();
        EntityManager<User> userDao = ManagerFactory.getManager(User.class);
        LocalDate today = LocalDate.now();
        List<User> theUsers;
        User currentUser = null;
        String apiKey;
        Boolean userOkay = false;
        String url = "/public/home.jsp";
        String responseMessage = null;
        Boolean hasKey = false;
        try {

            if (uName.isEmpty() || pWord.isEmpty()) {
                responseMessage = "You must enter a username and password";
            } else {
                theUsers = userDao.getListEquals("username", uName);

                if(theUsers.size() == 1){
                    for (User u : theUsers){
                        currentUser = u;
                        if (u.getPassword().equals(pWord)) {
                            userOkay = true;
                        } else {
                            responseMessage = "Username and password did not match";
                        }

                    }
                } else {
                    responseMessage = "There was a problem signing in, please contact an administrator";
                }
            }

            if (userOkay) {
                session.setAttribute("loggedIn", userOkay);
                session.setAttribute("currentUser", currentUser);
                apiKey = currentUser.getApiKey();


                if (apiKey != null && !apiKey.isEmpty()){
                    hasKey = true;
                    req.setAttribute("hasKey", hasKey);
                    req.setAttribute("userKey", apiKey);

                } else {
                    req.setAttribute("hasKey", hasKey);
                }

                req.setAttribute("signedIn", userOkay);

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(req, resp);

            } else {
                resp.setHeader("Refresh", "3; URL=signin.jsp");
                resp.setContentType("text/html");
                PrintWriter out  = resp.getWriter();
                out.print("<h1>" + responseMessage + "</h1>");
                out.close();
            }


        }
        catch(Exception e){
            e.printStackTrace();
            //LOG MESSAGE
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //add user
        String uName = req.getParameter("userName");
        String pWord = req.getParameter("password");
        HttpSession session = req.getSession();
        EntityManager<User> userDao = ManagerFactory.getManager(User.class);
        LocalDate today = LocalDate.now();
        List<User> theUsers;
        User currentUser = null;
        String apiKey;
        Boolean userOkay = false;
        String url = "/public/home.jsp";
        String responseMessage = null;
        Boolean hasKey = false;
        try {

            if (uName.isEmpty() || pWord.isEmpty()) {
                responseMessage = "You must enter a username and password";
            } else {
                theUsers = userDao.getListEquals("username", uName);

                if(theUsers.size() == 1){
                    for (User u : theUsers){
                        currentUser = u;
                        if (u.getPassword().equals(pWord)) {
                            userOkay = true;
                        } else {
                            responseMessage = "Username and password did not match";
                        }

                    }
                } else {
                    responseMessage = "There was a problem signing in, please contact an administrator";
                }
            }

            if (userOkay) {
                session.setAttribute("loggedIn", userOkay);
                session.setAttribute("currentUser", currentUser);
                apiKey = currentUser.getApiKey();


                if (apiKey != null && !apiKey.isEmpty()){
                    hasKey = true;
                    req.setAttribute("hasKey", hasKey);
                    req.setAttribute("userKey", apiKey);

                } else {
                    req.setAttribute("hasKey", hasKey);
                }

                req.setAttribute("signedIn", userOkay);

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(req, resp);

            } else {
                resp.setHeader("Refresh", "3; URL=signin.jsp");
                resp.setContentType("text/html");
                PrintWriter out  = resp.getWriter();
                out.print("<h1>" + responseMessage + "</h1>");
                out.close();
            }


        }
        catch(Exception e){
            e.printStackTrace();
            //LOG MESSAGE
        }
    }


}
