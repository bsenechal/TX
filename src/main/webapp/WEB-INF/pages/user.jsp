<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<jsp:include page="header.jsp"/>

<LINK rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap-table.css">

<div class="content">
	<div class="wrap">
		<div class="single-page">
			
			<jsp:include page="messages.jsp"/>
			
			<div class="page-header">
				<h1>Gestion des utilisateurs</h1>
			</div>	
						
			<ul class="list-group">
			  <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/user/addUser"><span class="glyphicon glyphicon-plus"></span> Ajouter un utilisateur</a></li>
			</ul>	
			
			<c:if test="${!empty listUsers}">
				<div class="table-responsive">
				    <table data-toggle="table" data-query-params="queryParams" data-pagination="true" data-search="true" data-height="519">
                        <thead>
						    <tr>
								<th><b>Email</b></th>
								<th><b>Nom</b></th>
								<th><b>Prénom</b></th>
								<th><b>Téléphone</b></th>
								<th><b>Date de création</b></th>
								<th><b>Rôle</b></th>
								<th></th>
								<th></th>
							</tr>
						</thead>	
							<c:forEach items="${listUsers}" var="user">
								<tr>
									<td>${user.email}</td>
									<td>${user.firstname}</td>
									<td>${user.lastname}</td>
									<td>${user.telephone}</td>
									<td>${user.creationDate}</td>
									<td>${user.role.libelle}</td>
									<td><a href="<c:url value='/admin/user/edit/${user.idUser}' />" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
									<td><a href="<c:url value='/admin/user/remove/${user.idUser}' />" ><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></a></td>
								</tr>
					</c:forEach>
				  </table>
				</div>
			</c:if>
			<nav>
              <ul class="pager">
                <li><a href="${pageContext.request.contextPath}/admin">Précédent</a></li>
              </ul>
            </nav>     
    	</div>
    	<jsp:include page="footer.jsp"/>
  	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap-table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap-table-fr-FR.min.js"></script>	