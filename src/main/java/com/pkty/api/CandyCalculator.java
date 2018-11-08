package com.pkty.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkty.domain.User;
import com.pkty.shared.ManagerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/service")
public class CandyCalculator {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/candycalculator?username={username}&apikey={apikey}&address={address}&avgcandy={avgcandy}")
    public Response getUsersList() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();


        String json = objectMapper.writeValueAsString("here is the calculation i want to return");
        return Response.status(200).entity(json).build();
    }

}
