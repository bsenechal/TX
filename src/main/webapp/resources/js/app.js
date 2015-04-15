'use strict';

/* App Module */

var lebondealApp = angular.module('lebondealApp', [ 'ngRoute',
		'lebondealControllers', 'lebondealServices']);

lebondealApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/admin', {
		templateUrl : 'resources/partials/admin/adminMain.html',
		controller : 'AdminCtrl'
	}).when('/admin/user/list', {
		templateUrl : 'resources/partials/admin/adminUser.html',
		controller : 'AdminUserCtrl'
	}).when('/admin/user/addUser', {
		templateUrl : 'resources/partials/admin/adminEditUser.html',
		controller : 'AdminEditUserCtrl'
	});

} ]);
