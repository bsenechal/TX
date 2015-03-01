x<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>

<jsp:include page="header.jsp"/>

<div class="content">
	<div class="wrap">
		<div class="single-page">
			<jsp:include page="messages.jsp"/>
			
			<div class="page-header">
				<h1>Mon compte</h1>
			</div>
			<div class="row">
              <div class="col-md-2">
                <div class="thumbnail"> 
                  <img src="${pageContext.request.contextPath}/avatarDisplay/">
                </div>
              </div>
              <div class="col-md-10">
				<ul class="list-group">
				  <li class="list-group-item"><a href="#"><b>Email : </b></a>  ${user.email}</li>
	              <li class="list-group-item"><a href="#"><b>Nom : </b></a>  ${user.lastname}</li>
	              <li class="list-group-item"><a href="#"><b>Prénom : </b></a>  ${user.firstname}</li>
	              <li class="list-group-item"><a href="#"><b>Téléphone : </b></a>  ${user.telephone}</li>
	              <li class="list-group-item"><a href="#"><b>Date de création : </b></a>  ${user.creationDate}</li>
	              <li class="list-group-item"><a href="#"><b>Rôle : </b></a>  ${user.role.libelle}</li>
	            </ul>	
	            <c:url var="logoutUrl" value="/j_spring_security_logout"/>
	                 <form action="${logoutUrl}" method="post">
	                      <input type="submit" class="btn btn-primary" value="Deconnexion" />
	                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	                 </form>
                </div>
            </div>
		</div>	
	</div>
</div>			