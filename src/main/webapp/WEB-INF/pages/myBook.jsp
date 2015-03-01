<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page session="true"%>

<jsp:include page="header.jsp"/>

<div class="content">
    <div class="wrap">
        <div class="single-page">
            <jsp:include page="messages.jsp"/>
            
            <div class="page-header">
                <h1>Mes évaluations</h1>
            </div>  

		    <c:if test="${!empty notes}">
		       <div class="table-responsive">
		       <c:set var="prec" value="init"/>
		             <c:forEach items="${notes}" var="note">
	                            <c:if test="${prec != note.evaluation.book.title}">
	                               <div class="media">
			                            <br/>
			                            <a class="media-left media-top" href="detail/${note.evaluation.book.idBook}">
			                                <img src="${pageContext.request.contextPath}/imageDisplay/${note.evaluation.book.idBook}">
			                            </a>
			                            <div class="media-body">
			                            
			                               <h3 class="media-heading">${note.evaluation.book.title}</h3>
			                               ${fn:substring(note.evaluation.book.description, 0, 400)}... <a href="detail/${note.evaluation.book.idBook}">Plus</a>
		
			                            </div>
	                               </div>
                                
                               <c:set var="prec" value="${note.evaluation.book.title}"/>
                             <br />
                             </c:if>
                             <ul class="list-group">
                                  <li class="list-group-item">
                                     <a href="#"><strong>${note.question.libelle}    </strong></a><c:forEach begin="1" end="${note.note}" varStatus="loop"><span class="glyphicon glyphicon-star"></span> </c:forEach>
                                  </li>
                             </ul>
					</c:forEach>
		       </div>
		   </c:if>  
        </div>
   </div>
</div>
</body>
</html>