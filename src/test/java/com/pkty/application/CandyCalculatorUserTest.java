package com.pkty.application;

import com.pkty.domain.CandyCalculatorUser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CandyCalculatorUserTest {

    @Test
    public void testCandyCalculatorUserResults() throws IOException {
        CandyCalculatorUser candyCalculatorUser = new CandyCalculatorUser();

        String url = "http://18.191.31.27:8080/candy-estimator/service/candycalculator?username=tdombrowski&apikey=supersecret1"
                + "&avgcandy=2&country=USA&address=2935%20Broadbridge%20Ave,%20Stratford,%20CT";

        String jsonResponse = candyCalculatorUser.returnFormattedJsonResponse(url);

        String expectedResponse = "{\n" +
                "  \"kidCount\" : 274,\n" +
                "  \"candyPerKid\" : 2,\n" +
                "  \"candyToBuy\" : 548\n" +
                "}";

        assertEquals(expectedResponse, jsonResponse);
    }

}