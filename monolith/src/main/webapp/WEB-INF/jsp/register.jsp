<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="ex" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false" %>
<html>

<head>
    <title>Bicycleshop Bootstarp Website Template | Register:: w3layouts</title>
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-1.11.0.min.js"></script>

    <!--Validation scripts-->
    <!--<script src="js/jQueryRegisterValidation.js"></script>-->
    <!-- Custom Theme files -->
    <link href="css/style.css" rel='stylesheet' type='text/css'/>
    <!-- Custom Theme files -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Doppio+One' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900' rel='stylesheet'
          type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>
    <script src="js/jquery.etalage.min.js"></script>
    <!==
    <script src="js/registerValidation.js"></script>
    ==>
    <link href="css/component.css" rel='stylesheet' type='text/css'/>
    <!-- Include the Etalage files -->
    <!--//details-product-slider-->
    <script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#horizontalTab').easyResponsiveTabs({
                type: 'default', //Types: default, vertical, accordion
                width: 'auto', //auto or any width like 600px
                fit: true // 100% fit in a container
            });
        });
    </script>
</head>

<body>
<!-- Header Starts Here -->
<div class="header">
    <div class="container">
        <jsp:include page="header_content.jsp"/>
        <div class="about">
            <div class="prody">
                <div class="new-product-top">
                    <ul class="product-top-list">
                        <li><a href="index.html">Home</a>&nbsp;<span>&gt;</span></li>
                        <li><a href="#">Account</a>&nbsp;<span>&gt;</span></li>
                        <li><span class="act"><a href="register.html">Register</a></span>&nbsp;</li>
                    </ul>
                    <p class="back"><a href="index.html">Back to Previous</a></p>
                    <div class="clearfix"></div>
                </div>
                <h3 class="new-models">For New Customers</h3>
                <div class="register">
                    <form name="registerForm" id="regForm" enctype="multipart/form-data"
                          action="<c:url value='registration.do'/>" method="post">
                        <div class="register-top-grid">
                            <h3>PERSONAL INFORMATION</h3>
                            <div>
                                <span>First Name<label>*</label></span>
                                <input type="text" id="firstName" name="firstName" placeholder="Ivan"
                                       value="${sessionScope.formData.firstName}">
                                <div id="nameError" class="errorMessage">${sessionScope.errors.firstName}</div>
                            </div>

                            <div>
                                <span>Last Name<label>*</label></span>
                                <input type="text" id="lastName" name="lastName" placeholder="Ivanov"
                                       value="${sessionScope.formData.lastName}">
                                <div id="lastNameError" class="errorMessage">${sessionScope.errors.lastName}</div>
                            </div>

                            <div>
                                <span>Email Address<label>*</label></span>
                                <input type="text" id="email" name="email" placeholder="myEmail@example.com"
                                       value="${sessionScope.formData.email}">
                                <div id="emailError" class="errorMessage">${sessionScope.errors.email}</div>
                            </div>
                            <div class="clearfix"></div>
                            <a class="news-letter" href="#">
                                <label class="checkbox"><input type="checkbox" name="newSubscription"><i> </i>Sign Up
                                    for Newsletter</label>
                            </a>
                        </div>
                        <div class="register-bottom-grid">
                            <h3>LOGIN INFORMATION</h3>
                            <div>
                                <span>Password<label>*</label></span>
                                <input type="password" id="password" name="password"
                                       placeholder="8-14 characters or numbers">
                                <div id="passwordError" class="errorMessage">${sessionScope.errors.password}</div>
                            </div>
                            <div>
                                <span>Confirm Password<label>*</label></span>
                                <input type="password" id="confirmedPassword" name="confirmedPassword"
                                       placeholder="Repeat password">
                                <div id="confirmedPasswordError"
                                     class="errorMessage">${sessionScope.errors.confirmedPassword}</div>
                            </div>
                            <div>
                                <span>Your avatar </span>
                                <input type="file" name="userAvatar">

                                <div>
                                    <ex:captcha captchaSource="CaptchaServlet" captchaId="${requestScope.captchaId}"/>
                                    <input type="text" name="captcha">
                                    <div id="captchaError" class="errorMessage">${sessionScope.errors.captcha}</div>
                                </div>
                            </div>

                            <div class="clearfix"></div>
                            <div class="register-but">
                                <input type="submit" value="submit" id="submit">
                                <div class="clearfix"></div>
                            </div>


                    </form>


                </div>
                <div class="clearfix"></div>
                <!--- fOOTER Starts Here --->
                <div class="footer-top abt-ft">
                    <ul class="bottom-list">
                        <li><a href="#">terms & conditions</a></li>
                        <li><a href="#">return policy</a></li>
                        <li><a href="#">reviews</a></li>
                        <li><a href="#">about shop</a></li>
                        <li><a href="#">secure payment</a></li>
                    </ul>
                </div>
                <ul class="payment-list">
                    <li><i class="paypal"></i></li>
                    <li><i class="wi"></i></li>
                    <li><i class="visa"></i></li>
                    <li><i class="amazon"></i></li>
                    <li><i class="sm"></i></li>
                </ul>
                <p class="copyright">2014 Template By <a href="http://w3layouts.com/">W3layouts</a></p>
            </div>
        </div>
    </div>
</div>
<!--- fOOTER Starts Here --->
</body>

</html>
