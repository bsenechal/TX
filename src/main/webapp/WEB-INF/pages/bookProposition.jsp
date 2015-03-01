<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page session="true"%>

<jsp:include page="header.jsp" />

<div class="content">
	<div class="wrap">
		<div class="single-page">
			<jsp:include page="messages.jsp" />

			<div class="page-header">
				<h1>Suggestion de lecture</h1>
			</div>
			
			<div class="row">
			  <div class="col-md-2">
			    <a class="thumbnail" href="detail/${book.idBook}"> 
			      <img src="${pageContext.request.contextPath}/imageDisplay/${book.idBook}">
			    </a>
			  </div>
			  <div class="col-md-10">
			     <h3 class="media-heading">${book.title}</h3>
				  <br/>
	              <h4 class="media-heading">Type : ${book.type}</h4>
	              ${book.description}
			  </div>
            </div>
		</div>
	</div>
</div>

