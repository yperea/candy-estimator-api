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

@Path("/users")
public class Test {

    /**
     * Returns a Users json-list for a HTTP GET request when no user Id has been specified.
     *
     * @return the message
     * @throws JsonProcessingException the json processing exception
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/")
    public Response getUsersList() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = ManagerFactory.getManager(User.class).getList();
        String json = objectMapper.writeValueAsString(users);

        return Response.status(200)
                .entity(json)
                .build();
    }
}