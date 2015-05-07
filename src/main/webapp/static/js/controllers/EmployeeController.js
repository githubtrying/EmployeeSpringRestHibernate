(function() {
    var employeeApp = angular.module('employeeApp');

    employeeApp.controller('EmployeeController', ['$scope', 'EmployeeService', '$modal', function(scope, EmployeeService, $modal) {
        scope.displayEmployee = function(row) {
            var empNo = row.entity.empNo;
            console.log("Displaying " + empNo);

            var modalInstance = $modal.open({
                templateUrl: 'static/js/views/modals/display-employee.html',
                controller: 'EmployeeModalController',
                resolve: {
                    employee: function() {
                        return EmployeeService.getEmployeeById(empNo);
                    }
                }
            });
        };
        
        scope.deleteEmployee = function(row) {
        	var empNo = row.entity.empNo;
            console.log("Deleting " + empNo);
            var modalInstance = $modal.open({
                templateUrl: 'static/js/views/modals/delete-employee.html',
                controller: 'EmployeeDeleteModalController',
                resolve: {
                	employee: function() {
                        return _.find(scope.restangularEmployees, function(employee) {
                            return employee.empNo === empNo;
                          });
                    }
                }
            });
        };

        scope.gridOptions = {
            enableRowHeaderSelection: false, // to hide the checkbox
            multiSelect: false, // to disable multi selection
            selectionRowHeaderWidth: 35,
            rowHeight: 35,
            showGridFooter: true,

            columnDefs: [{
                name: 'firstName'
            }, {
                name: 'lastName'
            }, {
                name: 'gender'
            }, {
                name: 'Actions',
                cellTemplate: '<div class="grid-actions"><button ng-click="grid.appScope.displayEmployee(row)">View</button><button class="btn-danger" ng-click="grid.appScope.deleteEmployee(row)">Delete</button></div>'
            }]
        };

        scope.gridOptions.onRegisterApi = function(gridApi) {
            //set gridApi on scope
            scope.gridApi = gridApi;
            gridApi.selection.on.rowSelectionChanged(scope, function(row) {
                var msg = 'row selected ' + row.isSelected;
                console.log(msg, row);
                console.log(scope.gridApi.selection.getSelectedRows());
            });

            gridApi.selection.on.rowSelectionChangedBatch(scope, function(rows) {
                var msg = 'rows changed ' + rows.length;
                console.log(msg, rows);
            });
        };

        scope.getAllEmployees = function() {
            EmployeeService.getAllEmployees().then(function(response) {
            	scope.restangularEmployees = response;
                scope.employees = response.plain();
                scope.gridOptions.data = scope.employees;
            });
        };

        // initial load
        scope.getAllEmployees();
        
        scope.$on('refreshGrid', function() {
        	// reload on the update
        	scope.getAllEmployees();
        });
    }]);
})();