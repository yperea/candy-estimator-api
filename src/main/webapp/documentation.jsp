<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="shared/head.jsp"%>
</head>

<body>
<!-- Navigation -->
<%@include file="shared/topbar.jsp" %>
<div class="text-center d-flex w-100 p-3 mx-auto flex-column">
    <br /><br /><br /><br />
    <main role="main" class="inner cover">
        <img class="mb-4" src="style/img/candyestimatorIcon75x75.png" alt="" width="72" height="72">
        <br />

        <!-- Description -->
        <div>
            <p>
                This service returns an estimation of the number of candy you'll want this halloween based off the number
                of children (under the age of 14) in your neighborhood.
            </p>
        </div>

        <!-- Example URL -->
        <div>
            <h3>Example URL</h3>
            <pre>http://18.191.31.27:8080/candy-estimator/service/candycalculator?username=tdombrowski&apikey=supersecret1&avgcandy=2&country=USA&address=2935%20Broadbridge%20Ave,%20Stratford,%20CT</pre>
        </div>

        <!-- Parameters -->
        <div>
            <h3>Parameters</h3>
            <table class="table table-dark table-striped">
                <thead>
                    <tr>
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
                            The username you registered with us.
                        </td>
                    </tr>
                    <tr>
                        <td>
                            API Key
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
                            A string of text in the format: Street, City, State, Zip Code
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Number of Candy
                        </td>
                        <td>
                            The number of candy you plan to hand out to each child.
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- Responses -->
        <div>
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
        <div>
            <h3>Sample Implementation</h3>
            <p>Description</p>
            <pre class="pre-scrollable" >Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut commodi eligendi iste labore
                    laboriosam nemo non quaerat repellat voluptates! Dolore earum harum illum minima quidem quis?
                    Corporis eligendi nisi unde.</pre>
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
</body>
</html>
