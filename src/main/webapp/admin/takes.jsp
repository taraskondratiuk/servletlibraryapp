<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
    <%@ include file="/static/css/bootstrap.css" %>
    <%@ include file="/static/css/styles.css" %>
</style>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="properties/messages"/>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Library</title>
</head>
<body>
<section class="section-1 head_wrapper">
    <header class="header container">
        <div class="headline row ">
            <div class="logo col-lg-2">
<%--                <img class="logo-img" src="../static/sources/book-logo.png" alt="logo">--%>
            </div>
            <div class="head_info col-lg-12">
                <h1 class="translate" data-args="message.title"></h1>
            </div>
            <div class="col-lg-2 langs">
                <div class="switch-locale lang-images">
<%--                    <a href="#" data-locale="en"><img src="../static/sources/us-flag.png" alt="en"></a>--%>
<%--                    <a href="#" data-locale="ua"><img src="../static/sources/ua-flag.png" alt="ua"></a></div>--%>
                <a href="/logout">
                    <button type="button" class="btn btn-info btn-sm logout-button translate"
                            data-args="message.logout">

                    </button>
                </a>
            </div>
        </div>
        <nav class="navigation">

            <div class="row menu_row">

                <div class="col-lg-offset-6 col-lg-2 menu-item center-text">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/books">books<h3 class="translate" data-args="message.mainpage"></h3></a>
                </div>
                <div class="col-lg-2  menu-item center-text">
                    <a class="nav-link" href="#"><h3 class="translate" data-args="message.stats"></h3></a>
                </div>

            </div>
        </nav>
        </div>
    </header>
</section>

<section class="page-wrapper container">

    <div class="row mgtop-container">
        <div class="takes-filter col-lg-4 mgtop-container">
            <div class="form-horizontal filter-form col-lg-12">
                <fieldset>

                    <form action="${pageContext.request.contextPath}/admin/takes" method="get">
                    <h3 class="center-text translate" data-args="message.filter"></h3>
                    <div class="form-group col-lg-16 input-field-my">
                        <select name="returned" id="returned" class="takes-select form-control">
                            <option name="returned" value="" class="form-control translate" data-args="message.all"></option>
                            <option name="returned" value="1" class="form-control translate" data-args="message.returned">1</option>
                            <option name="returned" value="0" class="form-control translate" data-args="message.unreturned">0</option>
                        </select>


                    </div>

                    <div class="form-group col-lg-16 input-field-my">
                        <div class="">
                            <input id="email" name="email" type="search" class="form-control input-md" placeholder="Reader email">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-4 control-label" for="filter-button"></label>
                        <div class="col-lg-4">
                            <button type="submit" id="filter-button"  class="btn btn-info">confirm</button>
                        </div>
                    </div>

                    </form>
                </fieldset>
            </div>
        </div>
        <div class="col-lg-8">
            <div id="takes" class="row">

                <c:if test="${empty page.pageContent}">
                    <fmt:message key="message.nothingfound"/>
                </c:if>

                <jsp:useBean id="now" class="java.util.Date" />
                <fmt:formatDate var="date" value="${now}" pattern="yyyy-mm-dd" />
                <c:forEach var="b" items="${page.pageContent}">
                <div class="take-container
                <c:choose>
                    <c:when test="${b.isReturned and b.returnDate > b.returnDeadline}">returned-after-deadline</c:when>
                    <c:when test="${b.isReturned and b.returnDate <= b.returnDeadline}">returned</c:when>
                    <c:when test="${!b.isReturned and b.returnDeadline < date}">not-returned-deadline</c:when>
                    <c:otherwise>not-returned</c:otherwise>
                </c:choose>

                {{colorClass}}
                col-lg-16">
                <div class="book-take-pic col-lg-6">
                    <img src="${b.bookPicUrl}" alt="book_pic">
                    </div>
                <div class="col-lg-10 take-info">
                    <h3 class="translate" data-args="message.nameauthor,{{book.name}},{{book.author}}">Book name: ${b.bookName}<br>Author: ${b.bookAuthor}</h3>
                    <h3 class="translate" data-args="message.takedead,{{takeDate}},{{returnDate}}">Take date: ${b.takeDate}<br>Return deadline: ${b.returnDeadline}</h3>

                    <c:if test="${b.isReturned}">
                        <h3 class="translate" data-args="messagae.returndate,{{returnDeadline}}">Return date: ${b.returnDate}</h3>
                    </c:if>



                    </div>
                </div>
                </c:forEach>

            </div>

        </div>
    </div>
    <div id="pager" class="col-lg-offset-4">
        <div class="pages row <c:if test="${empty page.pageArr}">
                    hide
                </c:if>">

        <div class="col-lg-8">
            <div class="page-item col-lg-3 disabled">
                <button class="disabled translate" data-args="message.page">Page</button>
            </div>

            <c:forEach items="${page.pageArr}" var="p">
                <c:if test="${p == page.pageNumber}">
                    <div class="page-item col-lg-1">
                        <button class="page-link page-button active" url="${url}?page=${p}">${p}</button>
                    </div>
                </c:if>
                <c:if test="${p != page.pageNumber}">
                    <div class="page-item col-lg-1">
                        <button class="page-link page-button pageBtn" url=""><a href="?page=${p}&email=${param.email}&returned=${param.returned}">${p}</a></button>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
    </div>








</section>

<footer class="footer">
    <div class="foot container">
        <div class="footer_info col-lg-4 ">
            <ul>
                <li class="translate bold" data-args="message.address"></li>
                <li class="translate" data-args="message.kyiv"></li>
                <li class="translate" data-args="message.street"></li>
            </ul>
        </div>
        <div class="footer_info col-lg-4 ">
            <ul>
                <li class="translate bold" data-args="message.cont"></li>
                <li class="translate" data-args="message.phone1"></li>
                <li class="translate" data-args="message.phone2"></li>
            </ul>
        </div>
        <div class="footer_info col-lg-4 ">
            <ul>
                <li class="translate bold" data-args="message.sponsors"></li>
                <li class="translate" data-args="message.sponsor1"></li>
                <li class="translate" data-args="message.sponsor2"></li>
            </ul>
        </div>
        <div class="footer_info col-lg-4 ">
            <ul>

                <li class="translate bold" data-args="message.inc"></li>
                <li class="translate bold" data-args="message.allrr"></li>
            </ul>
        </div>
    </div>
</footer>
</body>



</html>