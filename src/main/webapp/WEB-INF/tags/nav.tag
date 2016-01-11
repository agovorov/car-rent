<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag %>
<%@attribute name="title" type="java.lang.String" required="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="messages/messages"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<c:set var="current_action" value="${param.action}"/>

<nav class="navbar navbar-default">
    <div class="container inner-container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" rel="home" href="${prefix}/" title="Car rent service">
                <img style="max-width:100px;"
                     src="${prefix}/img/logo2.png">
            </a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                       aria-expanded="false">! <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${prefix}/controller?action=user-tmp">User login</a></li>
                        <li><a href="${prefix}/controller?action=admin-tmp">Admin login</a></li>
                    </ul>
                </li>

                <li class="${current_action == null ? 'active' : ''}"><a href="${prefix}/"><fmt:message
                        key="nav.main"/></a></li>
                <li class="${current_action == 'rules' ? 'active' : ''}"><a
                        href="${prefix}/controller?action=rules"><fmt:message key="nav.rent.rules"/></a></li>
                <li class="${current_action == 'contact' ? 'active' : ''}"><a
                        href="${prefix}/controller?action=contact"><fmt:message key="nav.contact"/></a></li>

                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                       aria-expanded="false"><fmt:message key="nav.lang"/><span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${prefix}/controller?action=locale&lang=ru">RU</a></li>
                        <li><a href="${prefix}/controller?action=locale&lang=en">EN</a></li>
                    </ul>
                </li>

                <c:if test="${sessionScope.user != null}">
                    <li class="${current_action == 'cabinet' ? 'active' : ''}"><a
                            href="${prefix}/controller?action=cabinet"><fmt:message key="nav.cabinet"/></a></li>
                    <li>
                        <a href="${prefix}/controller?action=logout">${sessionScope.user.getFirstName()} (<fmt:message key="nav.logout"/>)</a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.user == null}">
                    <li class="${current_action == 'register' ? 'active' : ''}"><a href="${prefix}/controller?action=register"><fmt:message key="nav.register"/></a></li>
                    <li class="login-block"><a href="${prefix}/controller?action=login"><fmt:message
                            key="nav.login"/></a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
