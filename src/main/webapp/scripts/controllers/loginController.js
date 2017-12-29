padaApp.controller('LoginController', ['$scope', '$rootScope', '$location', 'AuthenticationService', '$timeout', function ($scope, $rootScope, $location, AuthenticationService,  $timeout) {
    
	$rootScope.bodyClass = "loginForm"
	
	$scope.loginAlertMessage = true;
	
    $scope.login = function () {
    	$scope.dataLoading = true;
        AuthenticationService.Login($scope.username, $scope.password, function(response) {
        	if(response.success) {
        		AuthenticationService.SetCredentials($scope.username, $scope.password);
        		$rootScope.bodyClass = ""
                $location.path('/');
            } else {
            	$scope.loginAlertMessage=false; 
                $timeout(function () { $scope.loginAlertMessage = true; }, 2000);
                $scope.error = response.message;
                $scope.dataLoading = false;
            }
        });
    };
}]);