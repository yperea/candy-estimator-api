package com.pkty.application;

import com.pkty.domain.EstimateHistory;
import com.pkty.domain.User;
import com.pkty.shared.ManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.util.DatabaseManager;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Estimate history manager Test.
 */
public class EstimateHistoryManagerTest {

    EntityManager<User> userManager;
    EntityManager<EstimateHistory> estimateHistoryManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        userManager = ManagerFactory.getManager(User.class);
        estimateHistoryManager = ManagerFactory.getManager(EstimateHistory.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get estimate history by id.
     */
    @Test
    void testGetEstimateHistoryById() {
        EstimateHistory estimateHistory = estimateHistoryManager.get(1);
        int candyPerChild = estimateHistory.getCandyPerChild();
        int childrenPopulation = estimateHistory.getChildrenPopulation();
        double latitude = estimateHistory.getLatitude();
        double longitude = estimateHistory.getLongitude();
        String address = estimateHistory.getAddress();
        String country = estimateHistory.getCountry();

        assertEquals(5, candyPerChild );
        assertEquals(274, childrenPopulation );
        assertEquals(41.0473112, latitude );
        assertEquals(29.0077011, longitude );
        assertEquals("2935 Broadbridge Ave, Stratford, CT", address );
        assertEquals("USA", country );
    }

    /**
     * Test the get all history.
     */
    @Test
    void testGetAllEstimatesHistory() {
        List<EstimateHistory> estimatesHistory = estimateHistoryManager.getList();
        assertEquals(4, estimatesHistory.size());
    }

    /**
     * Test the estimate history creation.
     */
    @Test
    void testCreateEstimateHistory() {

        int candyPerChildren = 3;
        int childrenPopulation = 15;
        String address = "3591 Anderson Street, Madison, WI 53704";
        String country = "USA";

        User user = userManager.get(1);
        EstimateHistory newEstimate = new EstimateHistory(candyPerChildren, childrenPopulation, address, country, user);
        EstimateHistory insertedEstimate = estimateHistoryManager.create(newEstimate);

        assertNotNull(insertedEstimate);
        assertEquals(candyPerChildren, insertedEstimate.getCandyPerChild());
        assertEquals(childrenPopulation, insertedEstimate.getChildrenPopulation());
        assertEquals(address, insertedEstimate.getAddress());
        assertEquals(country, insertedEstimate.getCountry());
    }

    /**
     * Test the get estimate history by user.
     */
    @Test
    void testGetEstimatesHistoryByUser() {
        int userId = 1;
        String userName = "tdombrowski";
        String address = "2935 Broadbridge Ave, Stratford, CT";

        Stream<EstimateHistory> historyStream = estimateHistoryManager.getList().stream();
        List<EstimateHistory> userHistory = historyStream
                .filter(e -> Integer.valueOf(e.getUser().getId()).equals(userId))
                .collect(Collectors.toList());

        assertEquals(1, userHistory.size());
        assertEquals(userName, userHistory.get(0).getUser().getUsername());
        assertEquals(address, userHistory.get(0).getAddress());
    }
}