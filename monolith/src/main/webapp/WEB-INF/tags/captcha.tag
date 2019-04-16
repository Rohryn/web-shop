<%@ tag isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="captchaId" required="true" %>
<%@attribute name="captchaSource" required="true" %>
<c:choose>
    <c:when test="${initParam.parameterName} eq 'hidden'}">
        <img src="${captchaSource}?captchaId=${captchaId}">
        <input type="hidden" name="captchaId" value="${captchaId}" hidden>
    </c:when>
    <c:otherwise>
        <img src="${captchaSource}?captchaId=${captchaId}">
    </c:otherwise>
</c:choose>

