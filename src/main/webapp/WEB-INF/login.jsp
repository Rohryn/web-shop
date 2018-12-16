<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Bicycleshop Bootstarp Website Template | Login :: w3layouts</title>
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-1.11.0.min.js"></script>
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
    <link href="css/component.css" rel='stylesheet' type='text/css'/>
    <!-- Include the Etalage files -->
    <!----//details-product-slider--->
    <script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#horizontalTab').easyResponsiveTabs({
                type: 'default', //Types: default, vertical, accordion
                width: 'auto', //auto or any width like 600px
                fit: true   // 100% fit in a container
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
            <div class="product">
                <div class="product-listy">
                    <h3>Our Products</h3>
                    <ul class="product-list">
                        <li><a href="">New Products</a></li>
                        <li><a href="">Old Products</a></li>
                        <li><a href="">Sports</a></li>
                        <li><a href="">Clasical</a></li>
                        <li><a href="">New trend</a></li>
                        <li><a href="">New Release</a></li>
                        <li><a href="login">Log In</a></li>
                        <li><a href="registration">Register</a></li>
                    </ul>
                </div>
                <div class="latest-bis">
                    <img src="images/offer.jpg" class="img-responsive">
                    <div class="offer">
                        <p>40%</p>
                        <small>Top Offer</small>
                    </div>
                </div>
                <div class="tags">
                    <h4 class="tag_head">Tags Widget</h4>
                    <ul class="tags_links">
                        <li><a href="#">Kitesurf</a></li>
                        <li><a href="#">Super</a></li>
                        <li><a href="#">Duper</a></li>
                        <li><a href="#">Theme</a></li>
                        <li><a href="#">Men</a></li>
                        <li><a href="#">Women</a></li>
                        <li><a href="#">Equipment</a></li>
                        <li><a href="#">Best</a></li>
                        <li><a href="#">Accessories</a></li>
                        <li><a href="#">Men</a></li>
                        <li><a href="#">Apparel</a></li>
                        <li><a href="#">Super</a></li>
                        <li><a href="#">Duper</a></li>
                        <li><a href="#">Theme</a></li>
                        <li><a href="#">Responsiv</a></li>
                        <li><a href="#">Women</a></li>
                        <li><a href="#">Equipment</a></li>
                    </ul>
                    <a href="#" class="link1">View all tags</a>
                </div>

            </div>
            <div class="new-product prodys">
                <div class="new-product-top">
                    <ul class="product-top-list">
                        <li><a href="index.html">Home</a>&nbsp;<span>&gt;</span></li>
                        <li><a href="#">Account</a>&nbsp;<span>&gt;</span></li>
                        <li><span class="act"><a href="Login.html">Log In</a></span>&nbsp;</li>
                    </ul>
                    <p class="back"><a href="index.html">Back to Previous</a></p>
                    <div class="clearfix"></div>
                </div>
                <div class="account_grid">
                    <div class="col-md-6 login-right">
                        <h3>REGISTERED CUSTOMERS</h3>
                        <p>If you have an account with us, please log in.</p>
                        <form action="<c:url value='login.do'/>" method="post">
                            <div>
                                <span>Email Address<label>*</label></span>
                                <input type="text" name="email" value="${sessionScope.email}"
                                       placeholder="email@example.com">
                                <div id="emailError" class="errorMessage">${sessionScope.errors.email}</div>
                            </div>
                            <div>
                                <span>Password<label>*</label></span>
                                <input type="password" name="password">
                                <div id="passwordError" class="errorMessage">${sessionScope.errors.password}</div>
                            </div>
                            <a class="forgot" href="#">Forgot Your Password?</a>
                            <input type="submit" value="Login">
                        </form>
                    </div>
                    <div class="col-md-6 login-left">
                        <h3>NEW CUSTOMERS</h3>
                        <p>By creating an account with our store, you will be able to move through the checkout process
                            faster, store multiple shipping addresses, view and track your orders in your account and
                            more.</p>
                        <a class="acount-btn" href="registration">Create an Account</a>
                    </div>
                    <div class="clearfix"></div>
                </div>
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
        <!--- fOOTER Starts Here --->
</body>
</html>
