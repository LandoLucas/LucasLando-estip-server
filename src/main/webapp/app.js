var padaApp = angular.module('padaApp', ['ngRoute','smart-table','ngAnimate', 'ui.bootstrap','restClient', 'ui.calendar', 'chart.js', 'ngCookies', 'AuthenticationService'], function($routeProvider, $locationProvider) {
 
	$routeProvider
		.when('/', {
			templateUrl : 'views/calendar.html',
            controller: 'calendarController'
    	})
		.when('/login', {
			templateUrl: 'views/login.html',
	        controller: 'LoginController',
	        hideMenus: true
	    })
		.when('/sales', {
        	templateUrl : 'views/sales.html',
        	controller: 'salesController'
		})
	    .when('/products', {
            templateUrl : 'views/products.html',
            controller: 'productsController'
        })
        .when('/clients', {
            templateUrl : 'views/clients.html',
            controller: 'clientController'
        })
        .when('/stores', {
            templateUrl : 'views/stores.html',
            controller: 'storesController'
        })
        .when('/purchases', {
            templateUrl : 'views/purchases.html',
            controller: 'purchasesController'
        })
        .when('/calendar', {
            templateUrl : 'views/calendar.html',
            controller: 'calendarController'
        })
        .when('/statistics', {
            templateUrl : 'views/statistics.html',
            controller: 'statisticsController'
        })
        
        .otherwise({ redirectTo: '/login' });
});

padaApp.run(['$rootScope', '$location', '$cookieStore', '$http', function ($rootScope, $location, $cookieStore, $http) {
          // keep user logged in after page refresh
          $rootScope.globals = $cookieStore.get('globals') || {};
          if ($rootScope.globals.currentUser) {
              $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
          }
    
          $rootScope.$on('$locationChangeStart', function (event, next, current) {
              // redirect to login page if not logged in
              if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                  $location.path('/login');
              }
          });
      }]);

padaApp.run(function($rootScope, $location) {
    $rootScope.location = $location;
});
