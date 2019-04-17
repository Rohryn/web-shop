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
        <div class="about">
            <div class="product">
                <jsp:include page="sidebar_content.jsp"/>
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
            <div class="new-product">
                <div class="new-product-top">
                    <ul class="product-top-list">
                        <li><a href="index.html">Home</a>&nbsp;<span>&gt;</span></li>
                        <li><a href="bikes.jpg">Bikes</a>&nbsp;<span>&gt;</span></li>
                        <li><span class="act">New Products</span>&nbsp;</li>
                    </ul>
                    <p class="back"><a href="index.html">Back to Previous</a></p>
                    <div class="clearfix"></div>
                </div>
                <div class="new-topday">
                    <h3 class="new-models">Today Releases</h3>
                    <a href="single.html">
                        <div class="today-new-left">
                            <img src="images/offer.jpg" class="img-responsive">
                            <div class="sale-box">
                                <span class="on_sale title_shop">New</span>
                            </div>
                        </div>
                    </a>

                    <a href="single.html">
                        <div class="today-new-left">
                            <img src="images/offer2.jpg" class="img-responsive">
                            <div class="sale-box">
                                <span class="on_sale title_shop">New</span>
                            </div>
                        </div>
                    </a>
                    <a href="single.html">
                        <div class="today-new-left">
                            <img src="images/offer3.jpg" class="img-responsive">
                            <div class="sale-box">
                                <span class="on_sale title_shop">New</span>
                            </div>
                        </div>
                    </a>
                    <a href="single.html">
                        <div class="today-new-left">
                            <img src="images/bb1.jpg" class="img-responsive">
                            <div class="sale-box">
                                <span class="on_sale title_shop">New</span>
                            </div>
                        </div>
                    </a>
                    <a href="single.html">
                        <div class="today-new-left">
                            <img src="images/bb2.jpg" class="img-responsive">
                            <div class="sale-box">
                                <span class="on_sale title_shop">New</span>
                            </div>
                        </div>
                    </a>
                    <a href="single.html">
                        <div class="today-new-left">
                            <img src="images/bb3.png" class="img-responsive">
                            <div class="sale-box">
                                <span class="on_sale title_shop">New</span>
                            </div>
                        </div>
                    </a>
                    <div class="clearfix"></div>
                </div>
                <h3 class="new-models">New Models</h3>
                <div class="best-seller">
                    <div class="biseller-info">
                        <ul id="flexiselDemo3">
                            <li>
                                <div class="biseller-column">
                                    <img src="images/sm1.jpg" alt="">
                                </div>
                            </li>
                            <li>
                                <div class="biseller-column">
                                    <img src="images/sm3.jpg" alt="">
                                </div>
                            </li>
                            <li>
                                <div class="biseller-column">
                                    <img src="images/sm4.jpg" alt="">
                                </div>
                            </li>
                            <li>
                                <div class="biseller-column">
                                    <img src="images/sm1.jpg" alt="">
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <script type="text/javascript">
                    $(window).load(function () {
                        $("#flexiselDemo3").flexisel({
                            visibleItems: 3,
                            animationSpeed: 1000,
                            autoPlay: true,
                            autoPlaySpeed: 3000,
                            pauseOnHover: true,
                            enableResponsiveBreakpoints: true,
                            responsiveBreakpoints: {
                                portrait: {
                                    changePoint: 480,
                                    visibleItems: 1
                                },
                                landscape: {
                                    changePoint: 640,
                                    visibleItems: 2
                                },
                                tablet: {
                                    changePoint: 768,
                                    visibleItems: 3
                                }
                            }
                        });

                    });


                </script>
                <h3 class="new-models">Sales Models</h3>
                <div class="best-seller">
                    <div class="biseller-info">
                        <ul id="flexiselDemo1">
                            <li>
                                <div class="biseller-column">
                                    <img src="images/sm1.jpg" alt="">
                                </div>
                            </li>
                            <li>
                                <div class="biseller-column">
                                    <img src="images/sm3.jpg" alt="">
                                </div>
                            </li>
                            <li>
                                <div class="biseller-column">
                                    <img src="images/sm4.jpg" alt="">
                                </div>
                            </li>
                            <li>
                                <div class="biseller-column">
                                    <img src="images/sm1.jpg" alt="">
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <script type="text/javascript">
                    $(window).load(function () {
                        $("#flexiselDemo1").flexisel({
                            visibleItems: 3,
                            animationSpeed: 1000,
                            autoPlay: true,
                            autoPlaySpeed: 3000,
                            pauseOnHover: true,
                            enableResponsiveBreakpoints: true,
                            responsiveBreakpoints: {
                                portrait: {
                                    changePoint: 480,
                                    visibleItems: 1
                                },
                                landscape: {
                                    changePoint: 640,
                                    visibleItems: 2
                                },
                                tablet: {
                                    changePoint: 768,
                                    visibleItems: 3
                                }
                            }
                        });

                    });


                </script>
                <script type="text/javascript" src="js/jquery.flexisel.js"></script>
            </div>
            <div class="clearfix"></div>
        </div>
        <!-- Banner Slide Starts Here -->
        <div class="slider">
            <!-- Slideshow 3 -->
            <script src="js/responsiveslides.min.js"></script>
            <script>
                // You can also use "$(window).load(function() {"
                $(function () {
                    // Slideshow 3
                    $("#slider3").responsiveSlides({
                        manualControls: '#slider3-pager',
                    });
                });

            </script>
            <ul class="rslides" id="slider3">
                <li>
                    <div class="banner">
                        <h1>What a beautiful bike</h1>
                        <h2>timeless, atmospheric<br>& uncredible bikes!</h2>
                    </div>
                </li>
                <li>
                    <div class="banner banner2">
                        <h1>What a beautiful bike</h1>
                        <h2>timeless, atmospheric<br>& uncredible bikes!</h2>
                    </div>
                </li>
                <li>
                    <div class="banner banner1">
                        <h1>What a beautiful bike</h1>
                        <h2>timeless, atmospheric<br>& uncredible bikes!</h2>
                    </div>
                </li>
            </ul>
            <ul id="slider3-pager">
                <li><a href="#"><img src="images/device3.jpg" alt=""></a></li>
                <li><a href="#"><img src="images/device2.jpg" alt=""></a></li>
                <li><a href="#"><img src="images/device1.jpg" alt=""></a></li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <!-- Banner Slide Ends Here -->
        <!-- Best Seller Starts Here -->
        <div class="best-seller">
            <div class="best-seller-row">
                <div class="seller-column">
                    <div class="sale-box">
                        <span class="on_sale title_shop">bestseller</span>
                    </div>
                    <img src="images/biscyle1.jpg" alt="" class="seller-img">
                </div>
                <div class="seller-column1">
                    <h3>Sale</h3>
                    <span class="sale-nip"></span>
                    <h4>Bicycle RetroSyperb Vii #1</h4>
                    <small>by Rodriguez Else</small>
                    <p>299.99$</p>
                    <div class="price">
                        <a href="single.html">Add to Shopping bag</a>
                        <span class="rating">Rating: 5.0 <i class="ratings"></i></span>
                    </div>
                    <p class="customer">Ask the Customer a Question</p>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="biseller-info">
                <ul id="flexiselDemo3">
                    <li>
                        <div class="biseller-column">
                            <img src="images/sm1.jpg" alt="" class="veiw-img">
                            <div class="veiw-img-mark">
                                <a href="single.html">Add to Cart</a>
                            </div>
                            <div class="biseller-name">
                                <h4>Sport Bicycle 988</h4>
                                <small>by Enrique Salmo</small>
                            </div>
                            <div class="biseller-name1">
                                <p>139.99$</p>
                            </div>
                            <div class="clearfix"></div>
                            <div class="price-s">
                                <a href="single.html">Add to Shopping bag</a>
                            </div>

                        </div>
                    </li>
                    <li>
                        <div class="biseller-column">
                            <img src="images/sm3.jpg" alt="" class="veiw-img">
                            <div class="veiw-img-mark">
                                <a href="single.html">Add to Cart</a>
                            </div>
                            <div class="biseller-name">
                                <h4>Classic Bicycle </h4>
                                <small>by Ankino Frique</small>
                            </div>
                            <div class="biseller-name1">
                                <p>1 219.99$</p>
                            </div>
                            <div class="clearfix"></div>
                            <div class="price-s">
                                <a href="single.html">Add to Shopping bag</a>
                            </div>

                        </div>
                    </li>
                    <li>
                        <div class="biseller-column">
                            <img src="images/sm4.jpg" alt="" class="veiw-img">
                            <div class="veiw-img-mark">
                                <a href="single.html">Add to Cart</a>
                            </div>
                            <div class="biseller-name">
                                <h4>Retro Bicycle #2</h4>
                                <small>by Enrique Salmo</small>
                            </div>
                            <div class="biseller-name1">
                                <p>899.99$</p>
                            </div>
                            <div class="clearfix"></div>
                            <div class="price-s">
                                <a href="single.html">Add to Shopping bag</a>
                            </div>

                        </div>
                    </li>
                    <li>
                        <div class="biseller-column">
                            <img src="images/biscyle1.jpg" alt="" class="veiw-img">
                            <div class="veiw-img-mark">
                                <a href="single.html">Add to Cart</a>
                            </div>
                            <div class="biseller-name">
                                <h4>Classic Bicycle </h4>
                                <small>by Marco Spielmann</small>
                            </div>
                            <div class="biseller-name1">
                                <p>1 219.99$</p>
                            </div>
                            <div class="clearfix"></div>
                            <div class="price-s">
                                <a href="single.html">Add to Shopping bag</a>
                            </div>

                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <script type="text/javascript">
            $(window).load(function () {
                $("#flexiselDemo3").flexisel({
                    visibleItems: 3,
                    animationSpeed: 1000,
                    autoPlay: true,
                    autoPlaySpeed: 3000,
                    pauseOnHover: true,
                    enableResponsiveBreakpoints: true,
                    responsiveBreakpoints: {
                        portrait: {
                            changePoint: 480,
                            visibleItems: 1
                        },
                        landscape: {
                            changePoint: 640,
                            visibleItems: 2
                        },
                        tablet: {
                            changePoint: 768,
                            visibleItems: 3
                        }
                    }
                });

            });

        </script>
        <script type="text/javascript" src="js/jquery.flexisel.js"></script>
    </div>
</div>
<!--- fOOTER Starts Here --->
<jsp:include page="footer_content.jsp"/>
<!--- fOOTER Starts Here --->
</body>
</html>
