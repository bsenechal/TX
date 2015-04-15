'use strict';

/* Controllers */

var lebondealControllers = angular.module('lebondealControllers', []);

lebondealControllers.controller('AdminCtrl', [ '$scope', function($scope) {
	$scope.menu = [ {
		'title' : 'Gestion des utilisateurs',
		'url' : '#/admin/user/list'
	}, {
		'title' : 'Gestion des annonces',
	} ];
} ]);

lebondealControllers.controller('AdminUserCtrl', [ '$scope', 'AdminService',
		function($scope, AdminService) {
			$scope.tableheader = [ {
				'name' : 'Email'
			}, {
				'name' : 'Nom'
			}, {
				'name' : 'Prénom'
			}, {
				'name' : 'Téléphone'
			}, {
				'name' : 'Date de création'
			}, {
				'name' : 'Role'
			} ];
			$scope.Users = AdminService.listUser();
		} ]);

lebondealControllers.controller('AdminEditUserCtrl', [ '$scope', 'AdminService', '$http',
		function($scope, AdminService, $http) {
			$scope.listRole = AdminService.listRole();
			
			// Marche pas
//			$scope.submit = function() {
//				var formData = {
//						"email" : $scope.email,
//						"lastName" : $scope.lastname,
//						"firstName" : $scope.firstname,
//						"phoneNumber" : $scope.telephone,
//						"password" : $scope.password
//				};
//					
//				//Empty list data after process
//				$scope.list = [];
//				
//			};
		} ]);
