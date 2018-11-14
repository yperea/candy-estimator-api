<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../shared/head.jsp"%>
</head>

<body class="bg-light">
    <!-- Navigation -->
    <%@include file="../shared/topbar.jsp" %>

    <div class="container">
        <div class="py-5 text-center">
            <img class="d-block mx-auto mb-4" src="${root}/style/img/candyestimatorIcon75x75.png" alt="" width="72" height="72">
            <h2>Sign Up</h2>
            <p class="lead">Candy Estimator makes it easy for you to track your Halloween expenses.</p>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-8">
                <!--<h4 class="mb-3">Your person</h4>-->
                <form class="needs-validation"
                      action="signupController"
                      method="POST"
                      novalidate>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="firstName">First name</label>
                            <input type="text"
                                   class="form-control"
                                   id="firstName"
                                   name="firstName"
                                   placeholder=""
                                   value="${firstName}"
                                   required />
                            <div class="invalid-feedback">
                                Valid first name is required.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="lastName">Last name</label>
                            <input type="text"
                                   class="form-control"
                                   id="lastName"
                                   name="lastName"
                                   placeholder=""
                                   value="${lastName}"
                                   required />
                            <div class="invalid-feedback">
                                Valid last name is required.
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="userName">Username <span class="text-muted"></span></label>
                        <input type="text"
                               class="form-control"
                               id="userName"
                               name="userName"
                               placeholder="Username"
                               value="${userName}"
                               required />
                        <div class="invalid-feedback">
                            Your username is required.
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="password">Password</label>
                            <input type="password"
                                   class="form-control"
                                   id="password"
                                   name="password"
                                   placeholder=""
                                   value=""
                                   required />
                            <div class="invalid-feedback">
                                Valid password is required.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="confirmation">Confirmation</label>
                            <input type="password"
                                   class="form-control"
                                   id="confirmation"
                                   name="confirmation"
                                   placeholder="Password Confirmation"
                                   value=""
                                   required />
                            <div class="invalid-feedback">
                                Valid password is required.
                            </div>
                        </div>
                    </div>

                    <hr class="mb-4">

                    <div class="checkbox mb-3">
                        <label>
                            <input type="checkbox" value="i-agree"> I agree to these <a href="#">Terms of Use.</a>
                        </label>
                    </div>
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Sign Up</button>
                </form>
            </div>

        </div>
    </div>
    <%@include file="../shared/cdn-jss.jsp"%>
    <%@include file="../shared/footer.jsp"%>
</body>
</html>



