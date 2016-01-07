<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<!-- Modal -->
<div class="modal fade" id="vehiclesList" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><fmt:message key="order.vehicle.modal.header"/></h4>
            </div>
            <div class="modal-body">
                <c:if test="${not empty manufacturers}">
                    <c:forEach var="manufacturer" items="${manufacturers}">
                        <div class="vehicle-filter-block">
                            <div class="checkbox">
                                <label class="has-damage">
                                    <input type="checkbox" checked value="${manufacturer.getId()}">${manufacturer.getTitle()}
                                </label>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary do-filter"><fmt:message key="order.vehicle.modal.filter"/></button>
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="order.vehicle.modal.close"/></button>
            </div>
        </div>
    </div>
</div>