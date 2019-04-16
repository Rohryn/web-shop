<%@tag isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="currentPage" required="true" %>
<ul class="women_pagenation dc_paginationA dc_paginationA06">
    <c:set var="url"
           value="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.getQueryString()}">
    </c:set>
    <c:choose>
        <c:when test="${fn:contains(url, 'currentPage')}">
            <c:set var="previousPageParameter" value="currentPage=${currentPage-1}"></c:set>
            <c:set var="currentPageParameter" value="currentPage=${currentPage}"></c:set>
            <c:set var="nextPageParameter" value="currentPage=${currentPage+1}"></c:set>
            <li>
                <c:choose>
                    <c:when test="${currentPage>1}">
                        <a href="${fn:replace(url,currentPageParameter,previousPageParameter)}">${currentPage-1}</a>
                    </c:when>
                </c:choose>
            </li>
            <li class="active">
                <a href="${url}">${currentPage}</a>
            </li>
            <li>
                <c:choose>
                    <c:when test="${currentPage<requestScope.numberOfPages}">
                        <a href="${fn:replace(url,currentPageParameter,nextPageParameter)}">${currentPage+1}</a>
                    </c:when>
                </c:choose>
            </li>
        </c:when>
        <c:otherwise>
            <li>
                <c:choose>
                    <c:when test="${currentPage>1}">
                        <a href="${url}&currentPage=${currentPage-1}">${currentPage-1}</a>
                    </c:when>
                </c:choose>
            </li>
            <li class="active">
                <a href="${url}">${currentPage}</a>
            </li>
            <li>
                <a href="${url}&currentPage=${currentPage+1}">${currentPage+1}</a>
            </li>
        </c:otherwise>
    </c:choose>
</ul>
