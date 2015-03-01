<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>


<jsp:include page="header.jsp"/>

<LINK rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap-table.css">

<div class="content">
	<div class="wrap">
		<div class="single-page">
			<jsp:include page="messages.jsp"/>
			
			<div class="page-header">
				<h1>Gestion des livres</h1>
			</div>	
			
			<ul class="list-group">
			  <li class="list-group-item"><a href="${pageContext.request.contextPath}/book/new"><span class="glyphicon glyphicon-plus"></span> Ajouter un livre</a></li>
			</ul>	
			
			<c:if test="${!empty listBooks}">
				<div class="table-responsive">
					<table data-toggle="table" data-query-params="queryParams" data-pagination="true" data-search="true" data-height="519">
						<thead>
							<tr>
								<th><b>Titre</b></th>
								<th><b>Auteur</b></th>
								<th><b>Type</b></th>
								<th></th>
								<sec:authorize access="hasRole('ROLE_ADMIN')"><th></th></sec:authorize>
							</tr>
						</thead>
						<c:forEach items="${listBooks}" var="book">
							<tr>
								<td>${book.title}</td>
								<td>${book.autor}</td>
								<td>${book.type}</td>
								<td><a href="<c:url value='/book/edit/${book.idBook}' />" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
								<sec:authorize access="hasRole('ROLE_ADMIN')"><td><a href="<c:url value='/admin/book/remove/${book.idBook}' />" ><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></a></td></sec:authorize>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<nav>
	              <ul class="pager">
	                <li><a href="${pageContext.request.contextPath}/admin">Précédent</a></li>
	              </ul>
	            </nav> 
            </sec:authorize>
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap-table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap-table-fr-FR.min.js"></script>