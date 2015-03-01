<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<jsp:include page="header.jsp"/>

<div class="content">
	<div class="wrap">
		<div class="single-page">
			<jsp:include page="messages.jsp"/>
			
			<div class="page-header">
				<h1>
					<c:choose>
						<c:when test="${user.idUser == 0}">Ajout d'un utilisateur</c:when>
						<c:otherwise>Modification d'un utilisateur</c:otherwise>
					</c:choose>
				</h1>
			</div>	
			
   			<form:form commandName="user" id="user-form" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/user/save?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
		   		<form:input path="idUser" type="hidden" name="idUser" value="${user.idUser}" />
		   		<form:input path="creationDate" type="hidden" name="creationDate" value="${user.creationDate}" />
					<div id="emailDiv" class="form-group">
						<label for="labelEmail" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-10">
							<form:input path="email" type="email" class="form-control" id="email" placeholder="Email"/>
							<form:errors path="email" cssClass="error"/>
						</div>
					</div>
					<div id="nomDiv" class="form-group">
						<label for="labelNom" class="col-sm-2 control-label">Nom</label>
						<div class="col-sm-10">
							<form:input path="lastname" type="text" class="form-control" id="nom" placeholder="Nom"/>
							<form:errors path="lastname" cssClass="error"/>
						</div>
					</div>
					<div id="prenomDiv" class="form-group">
						<label for="labelPrenom" class="col-sm-2 control-label">Prénom</label>
						<div class="col-sm-10">
							<form:input path="firstname" type="text" class="form-control" id="prenom" placeholder="Prénom"/>
							<form:errors path="firstname" cssClass="error"/>
						</div>
					</div>
					<div id="telephoneDiv" class="form-group">
						<label for="labelTelephone" class="col-sm-2 control-label">Téléphone</label>
						<div class="col-sm-10">
							<form:input path="telephone" type="number" class="form-control" id="telephone" placeholder="Téléphone"/>
						    <form:errors path="telephone" cssClass="error"/>
						</div>
					</div>
					<div id="roleDiv" class="form-group">
						<label for="labelRole" class="col-sm-2 control-label">Role</label>
						<div class="col-sm-10">
							<select name="roleUser" class="form-control">
								<c:forEach items="${listRole}" var="role">
									<c:choose>
										<c:when test="${role.idRole == user.role.idRole}"><option value="${role.idRole}" selected>${role.libelle}</option></c:when>
										<c:otherwise><option value="${role.idRole}">${role.libelle}</option></c:otherwise>
									</c:choose>
										
								</c:forEach>
							</select>
						</div>
					</div>		
					<div id="avatarDiv" class="form-group">              
                        <label for="avatar" class="col-sm-2 control-label">Avatar</label>
                        <div class="col-sm-10">
                            <input class="filestyle" data-buttonBefore="true" data-buttonName="btn-primary" type="file" name="file" id="file" placeholder="${user.avatar}"/>
                        </div>
                    </div>
					<div id="passwordDiv" class="form-group">
						<label for="labelPass" class="col-sm-2 control-label">Mot de passe</label>
						<div class="col-sm-10">
							<form:input path="password" type="password" class="form-control" id="password" placeholder="Mot de passe" />
						    <form:errors path="password" cssClass="error"/>
						</div>
					</div>
					<div id="confirmationDiv" class="form-group">
						<label for="labelConfirm" class="col-sm-2 control-label">Confirmation</label>
						<div class="col-sm-10">
							<form:input path="confirmation" type="password" class="form-control" id="confirmation" placeholder="Confirmation"/>
						    <form:errors path="confirmation" cssClass="error"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">
							<c:choose>
								<c:when test="${user.idUser == 0}">Ajouter</c:when>
								<c:otherwise>Sauvegarder</c:otherwise>
							</c:choose>
							</button>
						</div>
					</div>
			</form:form>
			<nav>
			  <ul class="pager">
			    <li><a href="${pageContext.request.contextPath}/admin/user/list">Précédent</a></li>
			  </ul>
			</nav>
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/editUser.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-filestyle.min.js"></script>
</html>
