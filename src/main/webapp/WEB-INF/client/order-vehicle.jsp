<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="select.date.pageheader" var="pageHeader"/>

<fmt:formatDate var="orderDateStart" type="date" pattern="dd.MM.yyyy" value="${order.getStartDate()}"/>
<fmt:formatDate var="orderDateEnd" type="date" pattern="dd.MM.yyyy" value="${order.getEndDate()}"/>

<c:set var="prefix" value="${pageContext.request.contextPath}"/>


<a:two_column title="${pageHeader}" jsitems="/js/order.js; /webjars/underscorejs/1.8.3/underscore-min.js"
              cssitems="/css/order.css"
              breadcrumbItems="${breadcrumbItems}">
    <jsp:attribute name="left">
        <form>
            <div class="form-group">
                <label for="dStart"><fmt:message key="order.vehicle.datestart"/></label>
                <input type="text" class="form-control" id="dStart" value="${orderDateStart}" disabled>
            </div>
            <div class="form-group">
                <label for="dEnd"><fmt:message key="order.vehicle.dateend"/></label>
                <input type="text" class="form-control" id="dEnd" value="${orderDateEnd}" disabled>
            </div>

            <div class="form-group">
                <label for="dDays"><fmt:message key="order.vehicle.days"/></label>
                <input type="text" class="form-control" id="dDays" value="${order.countDays()}" disabled>
            </div>
        </form>
    </jsp:attribute>

    <jsp:body>
        <c:if test="${not empty vehicles}">
            <div class="filter-block">
                <a href="#" data-toggle="modal" data-target="#vehiclesList" data-default="<fmt:message
                        key="order.vehicle.choose.all"/>"><fmt:message
                        key="order.vehicle.choose.href"/><span class="caret"></span></a>
            </div>

            <div class="filter-block">
                <a href="#" data-toggle="modal" data-target="#priceModel" data-default="1000-100000"><fmt:message
                        key="order.vehicle.filter.price"/><span class="caret"></span></a>
            </div>

            <jsp:include page="order-vehicles-list.jsp">
                <jsp:param name="manufacturers" value="${manufacturers}"/>
            </jsp:include>

            <jsp:include page="order-vehicles-price.jsp"/>

            <div class="vehicles-list">
                <c:forEach var="vehicle" items="${vehicles}">
                    <a:vehicleBlock vehicle="${vehicle}" order="${order}"/>
                </c:forEach>
            </div>

            <!-- vehicle template for filter -->
            <script type="text/template" id="vehicleBlockTmpl">
                <div class="vehicle-block shadow_block">
                    <h2>{{item.name}}</h2>
                    <img src="/img/px.png" style="background-image: url({{item.img_src}});" width="204" height="101" />
                    <div class="price-a-day">
                        <span class="money">{{item.price}}</span>
                        <span class="currency-class"><fmt:message key="order.tenge.a.day"/></span>
                    </div>

                    <div class="vehicle-options row">
                        <div class="col-xs-4 option-block gearshift">
                            <div class="option-label">{{item.gearshift}}</div>
                        </div>
                        <div class="col-xs-4 option-block">
                            <span>{{item.volume}}</span>
                            <div class="option-label"><fmt:message key="order.vehicle.volume"/></div>
                        </div>
                        <div class="col-xs-4 option-block">
                            <span>{{item.consumption}}</span>
                            <div class="option-label"><fmt:message key="order.vehicle.consump"/></div>
                        </div>
                    </div>

                    <div class="bottom-block row">
                        <div class="col-xs-6 total-price-class">{{item.total_price}}</div>
                        <div class="col-xs-6 order-block"><a
                                href="/controller?action=order-verify&id={{item.id}}"><fmt:message
                                key="order.vehicle.choose"/></a></div>
                    </div>
                </div>
            </script>
        </c:if>

        <c:if test="${empty vehicles}">
            Nothing found!
        </c:if>

    </jsp:body>

</a:two_column>