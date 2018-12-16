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
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <fmt:setLocale value="${pageContext.request.locale}"/>
    <fmt:setBundle basename="messages" var="mes"/>
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
                            <label><fmt:message bundle="${mes}" key="selectPriceRange"/></label><br>
                            <fmt:message bundle="${mes}" key="From"/>:<br>
                            <input type="number" name="minPrice" value="${param.minPrice}" min="0"/><br>
                            <fmt:message bundle="${mes}" key="to"/>:<br>
                            <input type="number" name="maxPrice" value="${param.maxPrice}" min="0"/>
                            <br>
                            <br>
                            <ul class="product-list">
                                <label><fmt:message bundle="${mes}" key="selectManufacturers"/></label>
                                <c:set var="manufacturers" value="${requestScope.manufacturers}"/>
                                <c:forEach items="${manufacturers}" var="manufacturer">
                                    <li><a><label>
                                        <input type="checkbox" name="manufacturers" value="${manufacturer.name}"
                                                <c:if test="${functions:contains(paramValues.manufacturers, manufacturer.name)}"> checked</c:if>/>
                                            ${manufacturer.name}
                                    </label></a>
                                    </li>
                                </c:forEach>
                                <label><fmt:message bundle="${mes}" key="selectCategories"/></label>
                                <c:forEach items="${requestScope.categories}" var="category">
                                    <li><a><label><input type="checkbox" name="categories" value="${category.name}"
                                            <c:if test="${functions:contains(paramValues.categories, category.name)}"> checked</c:if>/>
                                            ${category.name}</label></a></li>
                                </c:forEach>
                            </ul>
                            <br>
                            <div style="margin-bottom:5px" align="center">
                                <input type="submit" value="Filter"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="new-product">
                    <div class="mens-toolbar">
                        <div class="sort">
                            <div class="sort-by">
                                <label>Sort By</label>
                                <select name="sort">
                                    <option value="unsorted"
                                            <c:if test="${empty param.sort}"> selected</c:if>
                                    ></option>
                                    <option value="price asc"
                                            <c:if test="${param.sort == 'price asc'}"> selected</c:if>
                                    ><fmt:message bundle="${mes}" key="priceAscending"/>:<br>
                                    </option>
                                    <option value="price desc"
                                            <c:if test="${param.sort == 'price desc'}"> selected</c:if>
                                    ><fmt:message bundle="${mes}" key="priceDescending"/>
                                    </option>
                                    <option value="manufacturer_name asc"
                                            <c:if test="${param.sort == 'manufacturer_name asc'}"> selected</c:if>
                                    ><fmt:message bundle="${mes}" key="manufacturerAscending"/>
                                    </option>
                                    <option value="manufacturer_name desc"
                                            <c:if test="${param.sort == 'manufacturer_name desc'}"> selected</c:if>
                                    ><fmt:message bundle="${mes}" key="manufacturerDescending"/>
                                    </option>
                                </select>
                            </div>
                        </div>
                        <ex:pagenation currentPage="${requestScope.currentPage}"></ex:pagenation>
                        <div class="clearfix"></div>
                    </div>
                    <div id="cbp-vm" class="cbp-vm-switcher cbp-vm-view-grid">
                        <div class="cbp-vm-options">
                            <a href="#" class="cbp-vm-icon cbp-vm-grid cbp-vm-selected" data-view="cbp-vm-view-grid"
                               title="grid">Grid View</a>
                            <a href="#" class="cbp-vm-icon cbp-vm-list" data-view="cbp-vm-view-list" title="list">List
                                View</a>
                        </div>
                        <div class="pages">
                            <div class="limiter visible-desktop">
                                <label><fmt:message bundle="${mes}" key="show"/></label>
                                <select name="productsPerPage">
                                    <option value="9"
                                            <c:if test="${param.productsPerPage == 9}"> selected</c:if>>
                                        9
                                    </option>
                                    <option value="15"
                                            <c:if test="${param.productsPerPage == 15}"> selected</c:if>
                                    > 15
                                    </option>
                                    <option value="30"
                                            <c:if test="${param.productsPerPage == 30}"> selected</c:if>
                                    > 30
                                    </option>
                                </select> <fmt:message bundle="${mes}" key="perPage"/>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <ul>
                            <c:forEach items="${requestScope.products}" var="product">
                                <li>
                                    <a class="cbp-vm-image" href="${pageContext.request.contextPath}/single.html">
                                        <div class="view view-first">
                                            <div class="inner_content clearfix">
                                                <div class="product_image">
                                                    <img src="${pageContext.request.contextPath}/${product.imageSource}"
                                                         class="img-responsive"
                                                         alt="image"/>
                                                    <div class="mask">
                                                        <div class="info">Quick View</div>
                                                    </div>
                                                    <div class="product_container">
                                                        <div class="cart-left">
                                                            <p class="title">${product.manufacturer.name}
                                                                    ${product.name}</p>
                                                        </div>
                                                        <br>
                                                        <div class="pricey">$${product.price}</div>
                                                        <div class="clearfix"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                    <div class="cbp-vm-details">
                                            ${product.description}
                                    </div>
                                    <button type="button" class="cbp-vm-icon cbp-vm-add"
                                            onclick="addToCart(${product.id})"><fmt:message bundle="${mes}" key="addToCart"/>
                                    </button>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <ex:pagenation currentPage="${requestScope.currentPage}"/>
                    <script src="js/cbpViewModeSwitch.js" type="text/javascript"></script>
                    <script src="js/classie.js" type="text/javascript"></script>
                    <script type="text/javascript" src="js/jquery.flexisel.js"></script>
                </div>
                <div class="clearfix"></div>
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