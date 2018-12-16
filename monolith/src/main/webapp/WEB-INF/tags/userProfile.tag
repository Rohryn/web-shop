<%@ tag isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:choose>
    <c:when test="${empty sessionScope.userName}">
        <div align="center">Login</div>
        <div>
            <form id="loginForm" action="<c:url value='login.do'/>" method="post">
                <div>
                    <span>Email</span><br>
                    <input type="text" name="email" value="${requestScope.email}" placeholder="email@example.com"
                           form="loginForm">
                    <div id="emailError">${sessionScope.errors.email}</div>
                </div>
                <br>
                <div>
                    <span>Password</span><br>
                    <input type="password" name="password" form="loginForm">
                    <div id="passwordError">${sessionScope.errors.password}</div>
                </div>
                <br>
                <div style="margin-bottom:5px" align="center">
                    <input type="submit" value="Login">
                </div>
            </form>
        </div>
        <div class="clearfix"></div>
    </c:when>
    <c:otherwise>
        <div>
            <form action="<c:url value='logout.do'/>" method="get">
                <div align="center">
                    <c:choose>
                        <c:when test="${not empty sessionScope.userAvatar}">
                            <br>
                            <img src="${root}/receiveAvatar" width="200" height="200">
                        </c:when>
                    </c:choose>

                    <span>${sessionScope.userName}</span>
                </div>
                <br>
                <div style="margin-bottom:5px" align="center">
                    <input type="submit" value="Logout">
                </div>
            </form>
            <div class="clearfix"></div>
        </div>
    </c:otherwise>
</c:choose>