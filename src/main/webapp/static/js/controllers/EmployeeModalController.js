(function() {
    var employeeApp = angular.module('employeeApp');

    employeeApp.controller('EmployeeModalController', ['$scope', '$modal', '$modalInstance', 'employee',
        function(scope, $modal, modalInstance, employee) {
    		scope.employee = employee.plain();
            console.log(scope.employee);
            
            scope.edit = function() {
            	var modalInstance = $modal.open({
                    templateUrl: 'static/js/views/modals/edit-employee.html',
                    controller: 'EmployeeEditModalController',
                    resolve: {
                        employee: function() {
                            return employee;
                        }
                    }
                });
            };

            scope.close = function() {
                modalInstance.dismiss('cancel');
            };
        }
    ]);
})();