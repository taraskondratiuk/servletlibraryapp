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
                <%--<img class="logo-img" src="${pageContext.request.contextPath}/static/sources/book-logo.png" alt="logo">--%>
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
                        <a class="nav-link" href="#"><h3 class="translate" data-args="message.mainpage"></h3></a>
                    </div>
                    <div class="col-lg-2  menu-item center-text">
                        <a class="nav-link" href="${pageContext.request.contextPath}/reader/takes"><h3 class="translate" data-args="message.stats">stats</h3></a>
                    </div>

                </div>
            </nav>
        </div>
    </header>
</section>

<section class="page-wrapper container">


    <div class="row books-filter-wrapper books-wrapper addbook-wrapper">

        <div class="filter col-lg-4 mgtop-container">
            <div class="form-horizontal filter-form col-lg-12">
                <fieldset>

                    <form action="${pageContext.request.contextPath}/reader/books" method="get">

                        <div class="form-group col-lg-16 input-field-my">
                            <h3 class="center-text translate" data-args="message.filter"></h3>
                            <div class="">
                                <input id="filter-author" type="search" placeholder="Author" name="author" class="form-control input-md">

                            </div>
                        </div>

                        <div class="form-group col-lg-16 input-field-my">
                            <div class="">
                                <input id="line"  type="search" placeholder="Name or text" name="line" class="form-control input-md">

                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-12 col-lg-offset-2 filter-attributes">

                                <c:forEach var="a" items="${attributes}">


                                    <div class="checkbox">
                                        <label>
                                            <input class="filterBookAttribute" type="checkbox" data-id="{{id}}" name="attributes" value="${a.name}">
                                                ${a.name}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-4 control-label" for="filter-button"></label>
                            <div class="col-lg-4">

                                <button type="submit" id="filter-button" class="btn btn-info translate" data-args="message.confirm"></button>
                            </div>
                        </div>
                    </form>
                </fieldset>
            </div>

        </div>
        <div class="col-lg-12">
            <div id="books" class="row">
                <c:if test="${empty page}">
                    <fmt:message key="message.nothingfound"/>
                </c:if>
                <c:forEach var="b" items="${page.pageContent}">
                    <div class="book_container col-lg-4">
                        <img src="${b.picUrl}" alt="book_pic">
                        <div class="book_info">
                            <h2 class="author_name">${b.author}</h2>
                            <p class="book_name">${b.name}</p>
                            <p class="book_attributes"><c:forEach var="a" items="${b.attributes}">${a} </c:forEach>
                            </p>
                            <p class="book_days_to_return translate" data-args="message.daystoreturn,{{daysToReturn}}">Days to return: ${b.daysToReturn}</p>
                        </div>
                        <form action="${pageContext.request.contextPath}/reader/takeBook" method="post">
                            <div class="book-button">
                                <input type="hidden" name="bookId" value="${b.id}">
                                <button type="submit" class="take-book" data-id="{{id}}"><p class="translate" data-args="message.remove">Take book</p></button>
                            </div>
                        </form>
                    </div>
                </c:forEach>

            </div>
            <div id="pager">

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
                                    <button class="page-link page-button pageBtn" url=""><a href="?page=${p}&line=${param.line}&author=${param.author}<c:forEach items="${paramValues.attributes}" var="a">&attributes=${a}</c:forEach>">${p}</a></button>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
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