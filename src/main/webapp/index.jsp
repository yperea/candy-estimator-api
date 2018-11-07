<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="shared/head.jsp"%>
</head>

<body>
    <!-- Navigation -->
    <%@include file="shared/topbar.jsp" %>
    <div class="text-center cover-container d-flex w-100 p-3 mx-auto flex-column">
        <br /><br /><br /><br />
        <main role="main" class="inner cover">
            <img class="mb-4" src="style/img/candyestimatorIcon75x75.png" alt="" width="72" height="72">
            <br />
            <h1 class="cover-heading">Halloween Candy Estimator API</h1>
            <br />
<%--
            <p class="lead">Cover is a one-page template for building simple and beautiful home pages. Download, edit the text, and add your own fullscreen background photo to make it your own.</p>
--%>
            <p class="lead">
                <a href="#" class="btn btn-lg btn-secondary">Learn more</a>
            </p>
            <br/>
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
