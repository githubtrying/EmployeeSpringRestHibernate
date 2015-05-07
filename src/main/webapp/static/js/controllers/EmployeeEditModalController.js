(function() {
    var employeeApp = angular.module('employeeApp');

    employeeApp.controller('EmployeeEditModalController', ['$rootScope', '$scope', '$modalInstance', '$modalStack', 'employee', 'Upload', 'EmployeeService',
        function(rootScope, scope, modalInstance, $modalStack, employee, Upload, EmployeeService) {
    		scope.employee = employee.plain();
    		scope.employeeList = [scope.employee];
            console.log(scope.employee);
            
            scope.addEmployee = function() {
            	scope.employeeList.push({
            		birthDate: new Date().getMilliseconds(),
            		hireDate: new Date().getMilliseconds(),
            		firstName: null,
            		lastName: null,
            		empNo: null
            	});
            };
            
            scope.save = function() {
            	EmployeeService.saveOrUpdateEmployees(scope.employeeList).then(function(response) {
            		// close all modal pop ups
            		rootScope.$broadcast('showNotification', 'Employee ' + employee.empNo + ' updated succefully');
            		rootScope.$broadcast('refreshGrid');
            		$modalStack.dismissAll();
            	});
            };

            scope.close = function() {
                modalInstance.dismiss('cancel');
            };
            
            scope.upload = function (files) {
                if (files && files.length) {
                    for (var i = 0; i < files.length; i++) {
                        var file = files[i];
                        Upload.upload({
                            url: 'upload',
                            fields: {'username': "test"},
                            file: file
                        }).progress(function (evt) {
                            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                            console.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
                        }).success(function (data, status, headers, config) {
                            console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
                        });
                    }
                }
            };
        }
    ]);
})();