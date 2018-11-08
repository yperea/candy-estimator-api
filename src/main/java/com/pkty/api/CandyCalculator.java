package com.pkty.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkty.application.EntityManager;
import com.pkty.domain.User;
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

@Path("/candycalculator")
public class CandyCalculator {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("")
    public Response getUsersList(@Context HttpServletRequest request)
            throws JsonProcessingException {

        String username = request.getParameter("username");
        String apikey = request.getParameter("apikey");
        String address = request.getParameter("address");
        int avgcandy = Integer.parseInt(request.getParameter("avgcandy"));

        ObjectMapper objectMapper = new ObjectMapper();
        //EntityDAO userDao = new EntityDAO("Put the class Yesid makes here!");


        //Validate User with apikey

        //Call yesids methods to pass on address and avgcandy

        //Store the info in the database with the Entity Dao

//        EstimateHistory estimateHistory = new EstimateHistory();
//        estimateHistory.set
//        EntityManager<EstimateHistory> historyManager = ManagerFactory.getManager(EstimateHistory.class);
//        EstimateHistory.create();

        String json = objectMapper.writeValueAsString("here is the calculation i want to return");
        return Response.status(200).entity(json).build();
    }

}
