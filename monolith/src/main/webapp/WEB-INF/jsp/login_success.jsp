<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Bicycleshop Bootstarp Website Template | Home :: w3layouts</title>
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
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>
</head>
<body>
<!-- Header Starts Here -->
<div class="header">
    <div class="container">
        <jsp:include page="header_content.jsp"/>
    </div>
    <script type="text/javascript" src="js/jquery.flexisel.js"></script>
</div>
<div align="center">
    <h1>Welcome ${sessionScope.userName}</h1>
</div>
</div>
<!--- fOOTER Starts Here --->
<div class="container">
    <div class="footer">
        <a href="">Browse shop</a>
    </div>
    <div class="footer-top">
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