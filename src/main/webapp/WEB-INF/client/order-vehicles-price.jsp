<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<!-- Modal -->
<div class="modal fade" id="priceModel" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><fmt:message key="order.vehicle.modal.price.header"/></h4>
            </div>
            <div class="modal-body">
                <div class="form-inline">
                    <div class="form-group">
                        <label class="sr-only" for="PriceStart"><fmt:message key="order.vehicle.modal.price.start"/></label>
                        <input type="type" class="form-control currency" value="1000" maxlength="10" id="PriceStart" placeholder="<fmt:message key="order.vehicle.modal.price.start"/>">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="PriceEnd"><fmt:message key="order.vehicle.modal.price.end"/></label>
                        <input type="type" class="form-control currency" value="100000" maxlength="10" id="PriceEnd" placeholder="<fmt:message key="order.vehicle.modal.price.end"/>">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary do-filter"><fmt:message key="order.vehicle.modal.filter"/></button>
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="order.vehicle.modal.close"/></button>
            </div>
        </div>
    </div>
</div>