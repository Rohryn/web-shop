<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="ex" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false" %>

<div class="product-listy">
    <div style="margin-left: 10px">
        <ex:userProfile/>
    </div>
    <h3>Our Products</h3>
    <ul class="product-list">
        <li><a href="">New Products</a></li>
        <li><a href="">Old Products</a></li>
        <li><a href="">Sports</a></li>
        <li><a href="">Clasical</a></li>
        <li><a href="">New trend</a></li>
        <li><a href="">New Release</a></li>
        <li><a href="login.do">Log In</a></li>
        <li><a href="registration.do">Register</a></li>
    </ul>
</div>