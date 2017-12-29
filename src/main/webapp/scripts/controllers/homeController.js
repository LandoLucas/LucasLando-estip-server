padaApp.controller('homeController',['$scope', 'AuthenticationService' , function(scope, AuthenticationService) {
	
	scope.logout = function() {
		AuthenticationService.ClearCredentials();
	}
	
}]);	