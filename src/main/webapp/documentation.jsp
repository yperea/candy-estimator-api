<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="shared/head.jsp"%>
</head>

<body>
<div class="documentationContainer">
<!-- Navigation -->
<%@include file="shared/topbar.jsp" %>
<div class="text-center d-flex w-100 p-3 mx-auto flex-column">
    <br /><br /><br /><br />
    <main role="main" class="inner cover">
        <!-- Description -->
        <div id="documentationDescription">
            <p>
                This service returns an estimation of the number of candy you'll want this halloween based off the number
                of children (under the age of 14) in your neighborhood.
            </p>
        </div>

        <div id="documentationMenu" class="col-md-2">
            <div>
                <ul>
                    <li><a href="#sampleURL">Sample URL</a></li>
                    <li><a href="#parameters">Parameters</a></li>
                    <li><a href="#sampleResponses">Sample Responses</a></li>
                    <li><a href="#sampleImplementation">Sample Implementation</a></li>
                </ul>
            </div>
        </div>

        <!-- Example URL -->
        <div id="sampleURL">
            <h3>Sample URL</h3>
            <pre>http://18.191.31.27:8080/candy-estimator/service/candycalculator?username=tdombrowski&apikey=supersecret1&avgcandy=2&country=USA&address=2935%20Broadbridge%20Ave,%20Stratford,%20CT</pre>
        </div>

        <!-- Parameters -->
        <div id="parameters">
            <h3>Parameters</h3>
            <table id="parameterTable" class="table table-dark table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Parameter</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            Username
                        </td>
                        <td>
                            username
                        </td>
                        <td>
                            The username you registered with us.
                        </td>
                    </tr>
                    <tr>
                        <td>
                            API Key
                        </td>
                        <td>
                            apikey
                        </td>
                        <td>
                            The API key that was generated with your account.
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Address
                        </td>
                        <td>
                            address
                        </td>
                        <td>
                            A string of text in the format: Street, City, State, Zip Code
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Number of Candy
                        </td>
                        <td>
                            avgcandy
                        </td>
                        <td>
                            The number of candy you plan to hand out to each child.
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- Responses -->
        <div id="sampleResponses">
            <h3>Sample Responses</h3>
            <div>
                <h4>Normal Response</h4>
                <pre class="pre-scrollable">${functionalResponse}</pre>
            </div>
            <div>
                <h4>Failed Response: Missing a Parameter</h4>
                <pre class="pre-scrollable" >${missingUsernameResponse}</pre>
            </div>
            <div>
                <h4>Failed Response: Wrong API Key</h4>
                <pre class="pre-scrollable" >${wrongApiKeyResponse}</pre>
            </div>
        </div>

        <!-- Implementation -->
        <div id="sampleImplementation">
            <h3>Sample Implementation</h3>
            <p>Here's an example of how to implement this API using Java.</p>
            <pre class="pre-scrollable" >
                import com.fasterxml.jackson.databind.ObjectMapper;

                import javax.ws.rs.client.Client;
                import javax.ws.rs.client.ClientBuilder;
                import javax.ws.rs.client.WebTarget;
                import javax.ws.rs.core.MediaType;
                import java.io.IOException;

                public class CandyCalculatorUser {

                    private static final String API_KEY = "yourapikey";
                    private static final String USERNAME = "yourusername";

                    private static final String WEB_URL = "http://18.191.31.27:8080/candy-estimator/service/candycalculator/?";

                    /**
                     * Constructs the url used to make the call.
                     *
                     * @param address the address
                     * @param country the country
                     * @param candyPerChild the candy per child
                     * @return url the url
                     */
                    private String constructApiAUrl(String address, String country, String candyPerChild) {
                        String url = WEB_URL + "username=" + USERNAME + "&apikey=" + API_KEY + "&country=" + country + "&address=" + address
                                + "&avgcandy=" + candyPerChild;

                        return url;
                    }

                    /**
                     * Utilizes a given url with the candy calculator api. The response is returned as a formatted json string.
                     *
                     * @param address the address
                     * @param country the country
                     * @param candyPerChild the candy per child
                     * @return response the json response
                     * @throws IOException
                     */
                    public String retrieveResponse(String address, String country, String candyPerChild) throws IOException {
                        String url = constructApiAUrl(address, country, candyPerChild);
                        String response = "";

                        Client client = ClientBuilder.newClient();

                        WebTarget target = client.target(url);

                        String jsonResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);

                        ObjectMapper mapper = new ObjectMapper();

                        Object json = mapper.readValue(jsonResponse, Object.class);

                        response = mapper.writeValueAsString(json);

                        return response;
                    }

                }
            </pre>
        </div>


        <footer class="mastfoot mt-auto">
            <div class="inner">
                <p>&copy; Halloween Candy Estimator 2018 &sdot; Java Enterprise by Team PKTY <br /> All Rights Reserved.</p>
            </div>
        </footer>
    </main>
</div>
<%@include file="shared/cdn-jss.jsp"%>
<%--<%@include file="shared/footer.jsp"%>--%>
</div>
</body>
</html>
