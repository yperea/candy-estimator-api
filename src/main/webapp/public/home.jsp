<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="/candy-estimator" scope="session" />
<c:set var="title" value="Halloween Candy Estimator" scope="session" />

<!DOCTYPE html><%--
  Created by IntelliJ IDEA.
  User: student
  Date: 11/12/18
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../shared/meta.jsp"%>

    <link rel="icon" href="${root}/style/img/candyestimatorIcon32x32.png">

    <title>${title}</title>

    <%@include file="../shared/cdn-css.jsp"%>

    <!-- Bootstrap validator CSS -->
    <link href="${root}/style/css/form-validation.css" rel="stylesheet" />
    <!-- Bootstrap Carousel CSS -->
    <%--<link href="${root}/style/css/carousel.css" rel="stylesheet" />--%>
    <!-- Bootstrap Dashboard CSS -->
    <link href="${root}/style/css/dashboard.css" rel="stylesheet" />
    <!-- Bootstrap Cover CSS -->
    <%--<link href="${root}/style/css/cover.css" rel="stylesheet" />--%>
    <!-- Candy Estimator Style Sheet -->
    <link rel="stylesheet" href="${root}/style/css/main.css" />
</head>

<body>
<%@include file="../shared/topbar.jsp"%>
<br />
<br />
<br />
<br />

<c:choose>
<c:when test="${hasKey}">
    <h1 class = "text-center" style="margin-top:10px">Your API Key: ${userKey}</h1>
</c:when>
<c:otherwise>
    <h1 class = "text-center"  style="margin-top:10px">Your API Key:</h1>
    <h3>Need a key? Click <a href="keyController">here</a> to get one!</h3>
</c:otherwise>
</c:choose>

<%--
<br />
<br />

<h2>Test Our Service!</h2>

<div class="col-lg-2"></div>
<div class="col-lg-6">
    <form method="">
        <div class="form-group">
            <label for="apiKey">API Key</label>
            <input type="text" class="form-control" id="apiKey" name="theAPI" placeholder="Enter API Key">

        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" class="form-control" id="address" name="userAddress" placeholder="Enter Address">
        </div>
        <div class="form-group">
            <label for="candyPerChild">Candies per Child</label>
            <input type="password" class="form-control" id="candyPerChild" name="numberCandies" placeholder="#">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
--%>

</body>
</html>
