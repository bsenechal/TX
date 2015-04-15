'use strict';

/* Services */
var lebondealServices = angular.module('lebondealServices', [ 'ngResource' ]);

lebondealServices.factory('AdminService', [ '$resource', function($resource) {
	return $resource('http://localhost:8080/TX/admin/list/:call', {}, {
		listUser : { method : 'GET', params : {call : 'user'} ,isArray : true },
		listRole : { method : 'GET', params : { call : 'role'} ,isArray : true }
	});
} ]);
