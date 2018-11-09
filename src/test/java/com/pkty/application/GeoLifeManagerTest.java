
package com.pkty.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkty.domain.ChildrenPopulation;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoLifeManagerTest {

    @Test
    public void testGetDemographicByAddress() throws IOException {

        GeoLifeManager geoLifeManager = new GeoLifeManager("/geoLife.properties");

        String address = "201 S Gammon Rd, Madison, WI 53717";

        String country = "USA";
        String format = "Both";
        String level = "All";
        String profile = null;
        String filter = null;
        boolean responseTypeIsXml = false;

        geoLifeManager.setFormat(format);
        geoLifeManager.setLevel(level);

        ChildrenPopulation childrenPopulation = geoLifeManager.getChildrenPopulation(address, country);

        assertEquals(240, childrenPopulation.getCount());

    }

}

