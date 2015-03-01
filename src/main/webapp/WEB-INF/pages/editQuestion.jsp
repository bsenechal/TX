<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page session="true"%>

<jsp:include page="header.jsp"/>

<div class="content">
	<div class="wrap">
		<div class="single-page">
			<jsp:include page="messages.jsp"/>
			
			<div class="page-header">
				<h1>
					<c:choose>
						<c:when test="${question.idQuestions == 0}">Ajout d'une question</c:when>
						<c:otherwise>Modification d'une question</c:otherwise>
					</c:choose>
				</h1>	
			</div>	

			<div class="container-fluid">
				<form:form commandName="question" id="question-form" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/questions/save">
					<div id="titleDiv" class="form-group">
						<label for="title" class="col-sm-2 control-label">Titre</label>
						<div class="col-sm-10">
							<form:input path="libelle" type="text" class="form-control" id="title" placeholder="Titre"/>
							<form:errors path="libelle" cssClass="error"/>
						</div>
					</div>
					<div id="noteDiv" class="form-group">			    
						<label  for="note" class="col-sm-2 control-label">Note max</label>
						<div class="col-sm-10">
						  <form:input path="valMax" type="number" class="form-control" id="note" placeholder="Note max"/>
						  <form:errors path="valMax" cssClass="error"/>
						</div>
					</div>
					<div id="importanceDiv" class="form-group">			    
						<label  for="note" class="col-sm-2 control-label">Importance</label>
						<div class="col-sm-10">
						  <form:input path="ponderation" type="number" class="form-control" id="importance" placeholder="Importance de la question"/>
						  <form:errors path="ponderation" cssClass="error"/>
						</div>
					</div>
					
					<form:hidden path="idQuestions"/>
					
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">
							<c:choose>
								<c:when test="${question.idQuestions == 0}">Ajouter</c:when>
								<c:otherwise>Sauvegarder</c:otherwise>
							</c:choose>
						</button>
					</div>
				</form:form>
			</div>
			<nav>
			  <ul class="pager">
			    <li><a href="${pageContext.request.contextPath}/admin/questions">Précédent</a></li>
			  </ul>
			</nav>
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/editQuestion.js"></script>
</html>