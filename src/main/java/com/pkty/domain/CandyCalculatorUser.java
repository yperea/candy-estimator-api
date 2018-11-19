package com.pkty.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

public class CandyCalculatorUser {

    public String returnFormattedJsonResponse(String url) throws IOException {
        String response = "";

        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target(url);
        String jsonResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();

        Object json = mapper.readValue(jsonResponse, Object.class);

        String functionalResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

        return response;
    }
}
