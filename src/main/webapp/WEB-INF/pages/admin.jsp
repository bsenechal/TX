<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<jsp:include page="header.jsp"/>

<body>
<div class="content">
	<div class="wrap">
		<div class="single-page">
		
			<jsp:include page="messages.jsp"/>
			
			<div class="page-header">
				<h1>Page d'administration</h1>
			</div>

			<ul class="list-group">
			  <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/user/list">Gestion des utilisateurs</a></li>
			  <li><br/></li>
			  <li class="list-group-item"><a href="${pageContext.request.contextPath}/book/listing">Gestion des livres</a></li>
			  <li><br/></li>
			  <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/evaluations">Gestion des évaluations</a></li>
			  <li><br/></li>
			  <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/questions">Gestion des questions des évaluations</a></li>
			</ul>
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
</div>
</body>

</html>