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
				<h1>Gestion des evaluations</h1>
			</div>	
							
			<c:if test="${!empty listEvals}">
				<div class="table-responsive">
                    <table data-toggle="table" data-query-params="queryParams" data-pagination="true" data-search="true" data-height="519">
                        <thead>
						    <tr>
								<th><b>Status</b></th>
								<th><b>Livre</b></th>
								<th><b>Utilisateur</b></th>
								<th></th>
								<th></th>
							</tr>
					   </thead>
					<c:forEach items="${listEvals}" var="eval">
						<tr>
							<td>${eval.status}</td>
							<td>${eval.book.title}</td>
							<td>${eval.user.email}</td>
							<td><a href="<c:url value='/admin/evaluation/edit/${eval.book.idBook}' />" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
							<td><a href="<c:url value='/evaluation/remove/${eval.idEval}' />" ><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></a></td>
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