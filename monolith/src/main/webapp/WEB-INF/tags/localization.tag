<%@tag isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="functions" uri="/WEB-INF/custom_functions.tld" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:set var="url"
       value="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.getQueryString()}">
</c:set>
<c:choose>
    <c:when test="${fn:contains(url, 'lang')}">
        <table>
            <tr>
                <td><a href="${functions:replaceAll(url,'lang=\\w+','lang=en')}"><img src="${root}/images/english.png"></a></td>
                <td><a href="${functions:replaceAll(url,'lang=\\w+','lang=ru')}"><img src="${root}/images/russian.png"></a></td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <td><a href="${url}&lang=en"><img src="${root}/images/english.png"></a></td>
                <td><a href="${url}&lang=ru"><img src="${root}/images/russian.png"></a></td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>



