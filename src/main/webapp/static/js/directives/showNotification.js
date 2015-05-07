(function() {
    var employeeApp = angular.module('employeeApp');

    employeeApp.directive('showNotification', [ '$timeout', function($timeout) {
    		var linkFn = function(scope, element, attrs) {
    			element.addClass("hidden showNotification");
    			
    			var hideUI = function() {
    				element.addClass("hidden");
    			};
    			
    			scope.$on('showNotification', function(event, message) {
    				scope.message = message;
    				element.removeClass("hidden");
    				//timeoutId = $timeout(hideUI, 1000);
    			});
    		};
    		return {
    			restrict: "EA",
    			link: linkFn,
    			templateUrl: 'static/js/views/show-notification.html'
    		};
        }
    ]);
})();