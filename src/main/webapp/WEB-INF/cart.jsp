<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
    <title>Bicycleshop Bootstarp Website Template | About us :: w3layouts</title>
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="css/component.css" rel='stylesheet' type='text/css'/>
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
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib prefix="functions" uri="/WEB-INF/custom_functions.tld" %>
    <%@ taglib prefix="ex" tagdir="/WEB-INF/tags" %>
    <%@ page isELIgnored="false" %>
    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Doppio+One' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:100,200,300,400,500,600,700,800,900' rel='stylesheet'
          type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/etalage.css">
    <script src="js/jquery.etalage.min.js"></script>
    <script src="js/orderSupport.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <!-- Include the Etalage files -->
    <script>
        jQuery(document).ready(function ($) {

            $('#etalage').etalage({
                thumb_image_width: 400,
                thumb_image_height: 350,

                show_hint: true,
                click_callback: function (image_anchor, instance_id) {
                    alert('Callback example:\nYou clicked on an image with the anchor: "' + image_anchor + '"\n(in Etalage instance: "' + instance_id + '")');
                }
            });
            // This is for the dropdown list example:
            $('.dropdownlist').change(function () {
                etalage_show($(this).find('option:selected').attr('class'));
            });
        });


    </script>
    <!----//details-product-slider--->
</head>
<body>
<!-- Header Starts Here -->
<div class="header">
    <div class="container">
        <jsp:include page="header_content.jsp"/>
        <form name="products" id="products" action="<c:url value='products'/>" method="get">
            <div class="about">
                <div class="product">
                    <div class="product-listy">
                        <div style="margin-left: 10px">
                            <ex:userProfile/>
                        </div>
                    </div>
                </div>
                <div class="new-product">
                    <div class="mens-toolbar">
                        <div class="clearfix"></div>
                    </div>
                    <div id="cbp-vm" class="cbp-vm-switcher cbp-vm-view-list">
                        <div class="clearfix"></div>
                        <table class="cart_table" border="1" align="center" , width="100%">
                            <tr>
                                <td>Product</td>
                                <td>Price</td>
                                <td>Quantity</td>
                                <td>Remove</td>
                                <td>Total</td>
                            </tr>
                            <c:set var="shoppingCart" value="${sessionScope.shoppingCart}"/>
                            <c:forEach items="${shoppingCart.products}" var="product">
                                <tr>
                                    <td>
                                        <img src="${pageContext.request.contextPath}/${product.imageSource}"
                                             width="150" height="100"
                                             alt="image"/>
                                        <p class="title">${product.manufacturer.name} ${product.name}</p>
                                    </td>
                                    <td>
                                        $${product.price}
                                    </td>
                                    <td>
                                        <input id="productQuantity_${product.id}" type="number" name="productQuantity"
                                               value="${shoppingCart.getProductCount(product)}" min="1"/><br>
                                        <button class="buttons" type="button"
                                                onclick="incrementQuantity(${product.id})">+
                                        </button>
                                        <br>
                                        <button id="decrementButton_${product.id}" class="buttons" type="button"
                                                onclick="decrementQuantity(${product.id})">-
                                        </button>
                                    </td>
                                    <td>
                                        <button type="button" onclick="removeProduct(${product.id})">Remove</button>
                                    </td>
                                    <td>
                                        <div id="productSum_${product.id}">${shoppingCart.getSumForProduct(product)}</div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <br>
                        <div align="right" id="totalSum"> Total: $${shoppingCart.getTotalSum()}
                        </div>
                        <div align="right">
                            <form method="post" action="order/makeOrder.do">
                                <input type="submit" value="Make order">
                            </form>
                        </div>


                        <script src="js/cbpViewModeSwitch.js" type="text/javascript"></script>
                        <script src="js/classie.js" type="text/javascript"></script>
                        <script type="text/javascript" src="js/jquery.flexisel.js"></script>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </form>
        <!--- fOOTER Starts Here --->
        <jsp:include page="footer_content.jsp"/>
        <p class="copyright">2014 Template By <a href="http://w3layouts.com/">W3layouts</a></p>
    </div>
</div>
<!--- fOOTER Starts Here --->
</body>
</html>