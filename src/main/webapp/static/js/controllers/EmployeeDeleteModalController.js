(function() {
    var employeeApp = angular.module('employeeApp');

    employeeApp.controller('EmployeeDeleteModalController', ['$rootScope', '$scope', '$modal', '$modalInstance', 'employee',
        function(rootScope, scope, $modal, modalInstance, employee) {
    		scope.employee = employee;
            console.log(scope.employee);
            
            scope.deleteEmployee = function() {
            	var empNo = employee.empNo;
            	employee.customDELETE(empNo).then(function() {
            		rootScope.$broadcast('showNotification', 'Employee ' + empNo + ' deleted succefully');
            		rootScope.$broadcast('refreshGrid');
            		modalInstance.dismiss('cancel');
            	});
            };

            scope.cancel = function() {
                modalInstance.dismiss('cancel');
            };
        }
    ]);
})();