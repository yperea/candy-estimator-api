package com.pkty.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkty.application.EntityManager;
import com.pkty.application.GeoLifeManager;
import com.pkty.domain.User;
import com.pkty.domain.ChildrenPopulation;
import com.pkty.entity.CandyToBuy;
import com.pkty.persistance.EntityDAO;
import com.pkty.shared.ManagerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.io.IOException;

@Path("/candycalculator")
public class CandyCalculator {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("")
    public Response getUsersList(@Context HttpServletRequest request)
            throws JsonProcessingException, IOException{

        String username = request.getParameter("username");
        String apikey = request.getParameter("apikey");
        String address = request.getParameter("address");
        String countryAbberv = request.getParameter("country");
        Integer avgcandy = Integer.parseInt(request.getParameter("avgcandy"));
        String candyToBuyJSON;
        String errorMessage;

        if (username == null || username.equals("")) {
            errorMessage = "{\"error\":\"no given username\"}";
            return Response.status(200).entity(errorMessage).build();
        }
        if (apikey == null || apikey.equals("")) {
            errorMessage = "{\"error\":\"no given apikey\"}";
            return Response.status(200).entity(errorMessage).build();
        }
        if (address == null || address.equals("")) {
            errorMessage = "{\"error\":\"no given address\"}";
            return Response.status(200).entity(errorMessage).build();
        }
        if (countryAbberv == null || countryAbberv.equals("")) {
            errorMessage = "{\"error\":\"no given country\"}";
            return Response.status(200).entity(errorMessage).build();
        }
        if (avgcandy == null || avgcandy == 0) {
            errorMessage = "{\"error\":\"no given avgcandy\"}";
            return Response.status(200).entity(errorMessage).build();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        EntityDAO userDao = new EntityDAO(User.class);

        List<User> users = userDao.getByPropertyEqual("username", username);
        User user = users.get(0);
        //User user = (User) ManagerFactory.getManager(User.class).get(username);

        //Validate User with apikey
        if (user.getApiKey().equals(apikey)) {
            //Call yesids methods to pass on address and avgcandy
            GeoLifeManager geoLifeManager = new GeoLifeManager("/geoLife.properties");
            ChildrenPopulation childrenPopulation =
                    geoLifeManager.getChildrenPopulationByAddress(address, countryAbberv);
            CandyToBuy candyToBuy = new CandyToBuy(childrenPopulation.getCount(), avgcandy);
            candyToBuy.CalculateCandyToBuy();
            candyToBuyJSON = objectMapper.writeValueAsString(candyToBuy);
        } else {
            candyToBuyJSON = objectMapper.writeValueAsString("Incorrect api key");
        }

//        Store the info in the database with the Entity Dao
//        EstimateHistory estimateHistory = new EstimateHistory();
//        estimateHistory.set
//        EntityManager<EstimateHistory> historyManager = ManagerFactory.getManager(EstimateHistory.class);
//        EstimateHistory.create();
//        String candyToBuyJSON = objectMapper.writeValueAsString("here is the calculation i want to return");

        return Response.status(200).entity(candyToBuyJSON).build();
    }

}
