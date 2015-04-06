<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty error}">
	<div class="alert alert-danger" role="alert">${error}</div>
</c:if>

<c:if test="${not empty warning}">
	<div class="alert alert-warning" role="alert">${warning}</div>
</c:if>

<c:if test="${not empty msg}">
	<div class="alert alert-success" role="alert">${msg}</div>
</c:if>


<script>
	$('.alert').fadeIn().delay(3000).fadeOut();
</script>