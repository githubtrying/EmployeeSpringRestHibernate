(function() {
	var employeeApp = angular.module('employeeApp');

	employeeApp.factory('EmployeeService', ['RestService', function(RestService) {
		var EmployeeService = {
			getAllEmployees: function() {
				return RestService.getAll('/employees/');
			},
			
			getEmployeeById: function(id) {
				return RestService.getOne('/employees/', id);
			},
			
			saveOrUpdateEmployees: function(list) {
				return RestService.post('/employees/', list);
			}
		};
		return EmployeeService;
	}]);
})();