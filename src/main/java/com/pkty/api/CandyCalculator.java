package com.pkty.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkty.application.EntityManager;
import com.pkty.application.GeoLifeManager;
import com.pkty.domain.User;
import com.pkty.domain.ChildrenPopulation;
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
        //String countryAbberv = request.getParameter("country");
        int avgcandy = Integer.parseInt(request.getParameter("avgcandy"));
        String candyToBuyJSON;

        ObjectMapper objectMapper = new ObjectMapper();
        EntityDAO userDao = new EntityDAO(User.class);

        List<User> users = userDao.getByPropertyLike("username", username);
        User user = users.get(0);
        //User user = (User) ManagerFactory.getManager(User.class).get(username);

        //Validate User with apikey
        if (user.getApiKey().equals(apikey)) {
            //Call yesids methods to pass on address and avgcandy
            GeoLifeManager geoLifeManager = new GeoLifeManager("/geoLife.properties");
            ChildrenPopulation childrenPopulation = geoLifeManager.getChildrenPopulationByAddress(address, "USA");
            candyToBuyJSON = objectMapper.writeValueAsString(childrenPopulation.getCount() * avgcandy);
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
