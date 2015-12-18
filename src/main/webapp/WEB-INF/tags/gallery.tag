<%@tag %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="messages/messages"/>
<%@ attribute name="gallery" type="com.epam.ag.model.Gallery" required="true" %>

<c:if test="${gallery.size() > 0}">
    <br>
    <div class="img-thumbnail-div">
        <img alt="140x140" class="img-thumbnail" data-src="holder.js/140x140" style="width: 140px; height: 140px;" src="/i?uid=${gallery.id}" data-holder-rendered="true">
        <div>Gallery ID: ${gallery.id}</div>
    </div>
</c:if>