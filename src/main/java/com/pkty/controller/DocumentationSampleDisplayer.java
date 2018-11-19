package com.pkty.controller;

import com.pkty.domain.CandyCalculatorUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Used to retrieve the json responses for the documentation page.
 * @author tdombrowski
 */

@WebServlet(
        urlPatterns = {"/documentation"}
)
public class DocumentationSampleDisplayer extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String functionalCall = "http://18.191.31.27:8080/candy-estimator/service/candycalculator?username=tdombrowski&"
            + "apikey=supersecret1&avgcandy=2&country=USA&address=2935%20Broadbridge%20Ave,%20Stratford,%20CT";
        String missingUsernameCall = "http://18.191.31.27:8080/candy-estimator/service/candycalculator?"
                            + "apikey=supersecret1&avgcandy=2&country=USA&address=2935%20Broadbridge%20Ave,%20Stratford,%20CT";
        String wrongApiKeyCall = "http://18.191.31.27:8080/candy-estimator/service/candycalculator?username=tdombrowski&"
                            + "apikey=supersecret3&avgcandy=2&country=USA&address=2935%20Broadbridge%20Ave,%20Stratford,%20CT";

        CandyCalculatorUser candyCalculatorUser = new CandyCalculatorUser();

        req.setAttribute("functionalResponse", candyCalculatorUser.returnFormattedJsonResponse(functionalCall));
        req.setAttribute("missingUsernameResponse", candyCalculatorUser.returnFormattedJsonResponse(missingUsernameCall));
        req.setAttribute("wrongApiKeyResponse", candyCalculatorUser.returnFormattedJsonResponse(wrongApiKeyCall));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/documentation.jsp");
        dispatcher.forward(req, resp);
    }
}
