
package com.pkty.application;

import com.pkty.domain.ChildrenPopulation;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * GeoLife Manager tester.
 */
public class GeoLifeManagerTest {

    /**
     * Test get demographic by address.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testGetChildrenPopulationByAddress() throws IOException {

        GeoLifeManager geoLifeManager = new GeoLifeManager("/geoLife.properties");
        String address = "201 S Gammon Rd, Madison, WI 53717";
        String country = "USA";

        ChildrenPopulation childrenPopulation = geoLifeManager.getChildrenPopulationByAddress(address, country);
        assertEquals(240, childrenPopulation.getCount());
    }
}

