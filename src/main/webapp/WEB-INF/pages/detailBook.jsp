<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page session="true"%>

<jsp:include page="header.jsp"/>
		<!---start-content---->
		<div class="content">
			<div class="wrap">
			<div class="single-page">
				<jsp:include page="messages.jsp"/>
							<div class="single-page-artical">
								<div class="artical-content">
									<img src="${pageContext.request.contextPath}/imageDisplay/${book.idBook}">
									<h3>${book.title}</h3>
									<h5><a href="${pageContext.request.contextPath}/evaluation/new/${book.idBook}"><span class="glyphicon glyphicon-star"></span> Noter ce livre <span class="glyphicon glyphicon-star"></span></a></h5>
									<p>${book.description}</p>
									</div>
		  						 <div class="clear"> </div>
							</div>
							 <c:if test="${!empty notes}">
								<div class="panel panel-default">
								  <div class="panel-heading"><h4>Evaluations par les autres lecteurs</h4></div>
								  <div class="panel-body">
								     
					               <c:set var="prec" value="init"/>
					                     <c:forEach items="${notes}" var="note">
					                                <c:if test="${prec != note.evaluation.user.email}">
					                                   <div class="row">
					                                        <br/>
					                                        <div class="col-xs-6 col-md-1">
						                                        <a href="detail/${note.evaluation.book.idBook}">
						                                            <img  src="${pageContext.request.contextPath}/avatarDisplayById/${note.evaluation.user.idUser}" class="img-circle">
						                                        </a>
					                                        </div>
					                                        <div class="col-xs-6 col-md-4">
					                                           <h4>${note.evaluation.user.email}</h4>
					                                        </div>
					                                   </div>
					                                
					                               <c:set var="prec" value="${note.evaluation.user.email}"/>
					                             <br />
					                             </c:if>
					                             <ul class="list-group">
					                                  <li class="list-group-item">
					                                     <a href="#"><strong>${note.question.libelle}    </strong></a><c:forEach begin="1" end="${note.note}" varStatus="loop"><span class="glyphicon glyphicon-star"></span> </c:forEach>
					                                  </li>
					                             </ul>
					                    </c:forEach>
					               
								  </div>
							</div>
				    </c:if>
                </div>
            </div>
		</div>
		<jsp:include page="footer.jsp"/>
		<!---//End-wrap---->
	</body>
</html>

