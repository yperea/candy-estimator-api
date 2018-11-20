package com.pkty.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * This class is used to interact with our own CandyCalculator api.
 */
public class CandyCalculatorUser {

    /**
     * Utilizes a given url with the candy calculator api. The response is returned as a formatted json string.
     *
     * @param url the url being used to access the service
     * @return response the json response
     * @throws IOException
     */
    public String returnFormattedJsonResponse(String url) throws IOException {
        String response = "";

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(url);

        String jsonResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();

        Object json = mapper.readValue(jsonResponse, Object.class);

        response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

        return response;
    }


}
