<!doctype html>
<html lang="fr" ng-app="lebondealApp">
<head>
  <meta charset="utf-8">
  <title>Le Bon Deal</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap/css/bootstrap.min.css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angular/angular.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angular/angular-animate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angular/angular-route.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angular/angular-resource.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/animations.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/controllers.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/filters.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/services.js"></script>
</head>
<body>

  <div class="view-container">
    <div ng-view class="view-frame"></div>
  </div>

</body>
</html>