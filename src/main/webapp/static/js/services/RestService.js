(function() {
	var employeeApp = angular.module('employeeApp');

	employeeApp.factory('RestService', ['Restangular', function(Restangular) {
		var RestService = {
			getAll: function(path) {
				Restangular.setBaseUrl('.');
				return Restangular.all(path).getList();
			}, 
			getOne: function(path, id) {
				Restangular.setBaseUrl('.');
				return Restangular.one(path, id).get();
			}, 
			post: function(path, data) {
				Restangular.setBaseUrl('.');
				return Restangular.service(path).post(data);
			}, 
		};
		return RestService;
	}]);
})();