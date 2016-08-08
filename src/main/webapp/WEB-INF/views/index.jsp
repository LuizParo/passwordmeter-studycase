<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="passwordmeter">
	<head>
		<meta charset="UTF-8" />
		<title>Password Meter</title>
		<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"/>
		<link href="<c:url value='/css/bootstrap-theme.min.css'/>" rel="stylesheet"/>
		<script src="<c:url value='/js/jquery-3.1.0.min.js'/>"></script>
		<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
		<script src="<c:url value='/js/angular.min.js'/>"></script>
		<script src="<c:url value='/js/angular-animate.min.js'/>"></script>
		<script src="<c:url value='/js/angular-resource.min.js'/>"></script>
		<script src="<c:url value='/js/main.js'/>"></script>
		<script src="<c:url value='/js/controller/PasswordMeterController.js'/>"></script>
	</head>
	<body ng-controller="PasswordMeterController">
		<form>
			<div class="container">
				<div class="col-xs-6">
					<h3>Avaliador de Segurança de Senha</h3>
					<input class="form-control" id="password" type="text" placeholder="Senha"
						ng-keyup="keyUp()" ng-model="password" ng-trim="false">
					<div class="row">
					    <div class="col-sm-2">
					         <div class="square-responsive">
					              <div class="content" style="background-color: grey; text-align: center;">
					                   <p style="color: white;"><b>{{score.strength}}%</b></p>
					              </div>
					         </div>
					    </div>
					    <div class="col-sm-3">
					         <div class="square-responsive">
					              <div class="content" style="text-align: center;" ng-style="score.complexity === 'Too Short' && {'background-color': 'red'} ||
												                 score.complexity === 'Very Weak' && {'background-color': 'orange'} ||
												                 score.complexity === 'Weak' && {'background-color': '#ffcc00'} ||
												                 score.complexity === 'Good' && {'background-color': 'yellow'} ||
												                 score.complexity === 'Strong' && {'background-color': '#66ff33'} ||
												                 score.complexity === 'Very Strong' && {'background-color': '#33cc33'}">
					                   <p style="color: white;"><b>{{score.complexity}}</b></p>
					              </div>
					         </div>
					    </div>
		  			</div>
				</div>
			</div>
		</form>
	</body>
</html>