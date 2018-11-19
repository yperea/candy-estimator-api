package com.pkty.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkty.domain.User;
import com.pkty.persistance.EntityDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

/**
 * A simple servlet to register the user.
 * @author pnorby
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
        String missingUsernameCall = "";
        String wrongApiKeyCall = "";

        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target(functionalCall);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();

        Object json = mapper.readValue(response, Object.class);

        String functionalResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

        req.setAttribute("functionalResponse", functionalResponse);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/documentation.jsp");
        dispatcher.forward(req, resp);
    }
}
