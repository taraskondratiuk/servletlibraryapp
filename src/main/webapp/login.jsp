

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
    <%@ include file="/static/css/bootstrap.css" %>
    <%@ include file="/static/css/styles.css" %>
</style>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="properties/messages"/>


<html lang="${param.lang}">
<head>
    <title>Library</title>


</head>
<body>

<section class="section-1 head_wrapper">
    <header class="header container">
        <div class="headline row ">
            <div class="logo col-lg-3">
                <img class="logo-img" src="static/sources/taxi-logo.png" alt="logo">
            </div>
            <div class="head_info center-text col-lg-10">
                <h1><fmt:message key="message.title"/></h1>
            </div>
            <div class="col-lg-3 langs">
                <div class="  lang-images">
                    <input type="image" src="static/sources/ua-flag.png" alt="UA" onclick="switchUA();"/>
                    <input type="image" src="static/sources/us-flag.png" alt="US" onclick="switchUS();"/>
                </div>
                <a href="/logout">
                    <button type="button" class="btn btn-info btn-sm logout-button"><fmt:message
                            key="message.logout"/></button>
                </a>
            </div>
        </div>
    </header>
</section>

<section class="page-wrapper container">

    <div class="takes-filter col-lg-4 mgtop-container">
        <div class="form-horizontal filter-form col-lg-12">
            <fieldset>

                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-group col-lg-16 input-field-my">
                        <div class="">
                            <input id="email" name="email" type="text" class="form-control input-md" placeholder="Reader email">
                        </div>
                    </div>
                    <div class="form-group col-lg-16 input-field-my">
                        <div class="">
                            <input id="pq" name="password" type="password" class="form-control input-md" placeholder="Reader email">
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

</section>
<footer class="footer">
    <div class="foot container">
        <div class="footer_info col-lg-2 ">
            <ul>
                <li><b><fmt:message key="message.address"/></b></li>
                <li><fmt:message key="message.kyiv"/></li>
                <li><fmt:message key="message.street"/></li>
            </ul>
        </div>
        <div class="footer_info col-lg-push-1 col-lg-2 ">
            <ul>
                <li><b><fmt:message key="message.cont"/></b></li>
                <li><fmt:message key="message.phone1"/></li>
                <li><fmt:message key="message.phone2"/></li>
            </ul>
        </div>
        <div class="footer_info col-lg-push-2 col-lg-2 ">
            <ul>
                <li><b><fmt:message key="message.sponsors"/></b></li>
                <li><fmt:message key="message.sponsor1"/></li>
                <li><fmt:message key="message.sponsor2"/></li>
            </ul>
        </div>
        <div class="footer_info col-lg-push-3 col-lg-3 ">
            <ul>

                <li><b><fmt:message key="message.inc"/></b></li>
                <li><b><fmt:message key="message.allrr"/></b></li>
            </ul>
        </div>
    </div>
</footer>
</body>
</html>