<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>

<jsp:include page="header.jsp"/>

<div class="content">
	<div class="wrap">
		<div class="single-page">
			<jsp:include page="messages.jsp"/>
			
			<div class="page-header">
				<h1>
					<c:choose>
						<c:when test="${book.idBook == 0}">Ajout d'un livre</c:when>
						<c:otherwise>Modification d'un livre</c:otherwise>
					</c:choose>
				</h1>	
			</div>	

			<div class="container-fluid">
				<form:form commandName="book" id="book-form" class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/book/save?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
					<div id="titleDiv" class="form-group">
						<label for="title" class="col-sm-2 control-label">Title</label>
						<div class="col-sm-10">
							<form:input path="title" type="text" class="form-control" id="title" placeholder="${book.title}"/>
						    <form:errors path="title" cssClass="error"/>
						</div>
					</div>
					<div id="autorDiv" class="form-group">			    
						<label  for="autor" class="col-sm-2 control-label">Auteur</label>
						<div class="col-sm-10">
							<form:input path="autor" type="text" class="form-control" id="autor" placeholder="${book.autor}"/>
							<form:errors path="autor" cssClass="error"/>
						</div>
					</div>
					<div id="typeDiv" class="form-group">			    
						<label for="type" class="col-sm-2 control-label">Type</label>
						<div class="col-sm-10">
							<form:input path="type" type="text" class="form-control" id="type" placeholder="${book.type}"/>
							<form:errors path="type" cssClass="error"/>
						</div>
					</div>
					<div id="imageDiv" class="form-group">			    
						<label for="image" class="col-sm-2 control-label">Image</label>
						<div class="col-sm-10">
							<input type="file" class="filestyle" data-buttonBefore="true" data-buttonName="btn-primary" name="file" id="file" placeholder="${book.image}"/>
						</div>
					</div>
					<div id="descriptionDiv" class="form-group">			    
						<label for="description" class="col-sm-2 control-label">Description</label>
						<div class="col-sm-10">
							<form:textarea path="description" class="form-control" id="description" placeholder="${book.type}"/>
							<form:errors path="description" cssClass="error"/>
						</div>
					</div>
					
					<form:hidden path="idBook"/>
										
					
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">
							<c:choose>
								<c:when test="${book.idBook == 0}">Ajouter</c:when>
								<c:otherwise>Sauvegarder</c:otherwise>
							</c:choose>
						</button>
					</div>
				</form:form>
			</div>
			<nav>
			  <ul class="pager">
			    <li><a href="${pageContext.request.contextPath}/book/listing">Précédent</a></li>
			  </ul>
			</nav>
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/editBook.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-filestyle.min.js"></script>
</html>