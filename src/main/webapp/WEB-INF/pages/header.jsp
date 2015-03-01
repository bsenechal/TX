<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="fr">
	<head>
		<title>Le Book</title>
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/fav-icon.png" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<!----webfonts---->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
		<!----//webfonts---->
		<!-- Global CSS for the page and tiles -->
  		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
  		<!-- //Global CSS for the page and tiles -->
		<!---start-click-drop-down-menu----->
		<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/additional-methods.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/messages_fr.min.js"></script>
		
        <!----start-dropdown--->
         <script type="text/javascript">
			var $ = jQuery.noConflict();
				$(function() {
					$('#activator').click(function(){
						$('#box').animate({'top':'0px'},500);
					});
					$('#boxclose').click(function(){
					$('#box').animate({'top':'-700px'},500);
					});
				});
				$(document).ready(function(){
				//Hide (Collapse) the toggle containers on load
				$(".toggle_container").hide(); 
				//Switch the "Open" and "Close" state per click then slide up/down (depending on open/close state)
				$(".trigger").click(function(){
					$(this).toggleClass("active").next().slideToggle("slow");
						return false; //Prevent the browser jump to the link anchor
				});
									
			});
		</script>
        <!----//End-dropdown--->
		<!---//End-click-drop-down-menu----->
		
		<LINK rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap/css/bootstrap.min.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/js/bootstrap.min.js"></script>
	</head>
<body>
			<!---start-wrap---->
			<!---start-header---->
			<div class="header">
				<div class="wrap">
				
				<div class="logo">
					<a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/images/logo.png" title="Le book" /></a>
				</div>
				<div class="nav-icon">
					 <a href="#" class="right_bt" id="activator"><span> </span> </a>
				</div>
				 <div class="box" id="box">
					 <div class="box_content">        					                         
						<div class="box_content_center">
						 	<div class="form_content">
								<div class="menu_box_list">
									<ul>
										<li><a href="${pageContext.request.contextPath}/">Accueil</a></li>
										<c:if test="${pageContext['request'].userPrincipal != null}">
										  <li><a href="${pageContext.request.contextPath}/book/listing">Livres</a></li>
										  <li><a href="${pageContext.request.contextPath}/book/myBook">Mes evaluations</a></li>
										  <li><a href="${pageContext.request.contextPath}/book/match">Suggestions</a></li>
										</c:if>
										<sec:authorize access="hasRole('ROLE_ADMIN')"><li><a href="${pageContext.request.contextPath}/admin">Administration</a></li></sec:authorize>
										<c:if test="${pageContext['request'].userPrincipal != null}">
										<li>
											<c:url var="logoutUrl" value="/j_spring_security_logout"/>
											<form action="${logoutUrl}" method="post">
											  <input type="submit" class="btn btn-primary" value="Deconnexion" />
											  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											</form>
										</li></c:if>
									</ul>
								</div>
								<a class="boxclose" id="boxclose"> <span> </span></a>
							</div>                                  
						</div> 	
					</div> 
				</div>       	  
				<div class="top-searchbar">
					<form method="get" action="${pageContext.request.contextPath}/book/found">
						<input type="text" name="title" id="title"/>
						<input type="submit" value=""/>
					</form>
				</div>
					<div class="userinfo">
						<div class="user">
							<ul>
								<li>
								<c:choose>
									<c:when test="${pageContext['request'].userPrincipal != null}"><a href="${pageContext.request.contextPath}/monCompte"><img src="${pageContext.request.contextPath}/avatarDisplay/"><span><security:authentication property="principal.username"></security:authentication></span></a></c:when>
									<c:otherwise><button type="submit" class="btn btn-primary" onclick='location.href ="${pageContext.request.contextPath}/login"'>Connexion</button></c:otherwise>
								</c:choose>
								</li>
							</ul>
						</div>
					</div>
				
		
				<div class="clear"> </div>
			</div>
		</div>
		
		
