<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="ex" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
<fmt:setBundle basename="messages" var="mes"/>
<div class="header-top">
    <div class="logo">
    <ex:localization/>
        <a href="${root}/index.do"><img src="${root}/images/logo.png"></a>
    </div>
    <span class="menu"></span>
    <div class="clear"></div>
    <div class="navigation">
        <ul class="navig">
            <li><a href="<c:url value='products.do'/>"><fmt:message bundle="${mes}" key="header.products"/> </a></li>
            <li><a href="<c:url value='login.do'/>"><fmt:message bundle="${mes}" key="header.logIn"/></a></li>
            <li><a href="<c:url value='registration.do'/>"><fmt:message bundle="${mes}" key="header.register"/></a></li>
            <a href="<c:url value='shoppingCart.do'/>"><img src="${root}/images/cart.png"></a>
            <label id="productsInCard"></label>

        </ul>
        <script>
            $("span.menu").click(function () {
                $(".navigation ul.navig").slideToggle("slow", function () {
                    // Animation complete.
                });
            });

        </script>
    </div>
    <div class="clearfix"></div>
</div>