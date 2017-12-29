angular.module('restClient', []).factory('restClient', ['$http', '$location', '$uibModal', function(http, location, uibModal) {

	var baseUrl = location.host();
	
	var apiUrl = "http://52.11.222.208:8080/pada-server/rest";
	
	if( baseUrl === "localhost"){
		apiUrl = "http://localhost:8081/pada/rest";
	}

	var defaultErrorCallback = function(response){
		
		var msg = "El recurso que intenta eliminar esta siendo utilizado por una venta o compra.";
		
		if(response === "PRODUCT_IN_USE"){
			msg = "El producto que intenta eliminar esta siendo utilizado por una o mas ventas. Debe eliminar esas ventas primero y luego eliminar el producto.";
		}
		
		if(response === "CLIENT_IN_USE"){
			msg = "El cliente que intenta eliminar esta siendo utilizado por una o mas ventas. Debe eliminar esas ventas primero y luego eliminar el cliente."
		}
		
		if(response === "STORE_IN_USE"){
			msg = "El mercado que intenta eliminar esta siendo utilizado por una o mas compras. Debe eliminar esas compras primero y luego eliminar el mercado."
		}
		

		var modalInstance = uibModal.open({
			template:"<p id='errorMessage'>" + msg +"</p>",
			controller: function(){}
		})
	
	};
	
	var sendGetWithoutErrorCallback = function(successCallback, path){
		sendGet(successCallback, defaultErrorCallback, path);
	}
	
	var sendPostWithoutErrorCallback = function(successCallback, data, path){
		sendPost(successCallback, defaultErrorCallback, data, path);
	}
	
	var sendPostAsJsonWithoutErrorCallback = function(successCallback, data, path){
		sendPostAsJson(successCallback, defaultErrorCallback, data, path);
	}
	
	var sendGet = function(successCallback, errorCallback, path){
		
		http(
				{
					method : 'GET',
					url : apiUrl + path,
					headers: {'Content-Type' : 'application/x-www-form-urlencoded; charset=utf-8'},
					transformRequest : function(obj) {
						var str = [];
						for ( var p in obj)
							str.push(encodeURIComponent(p) + "="
									+ encodeURIComponent(obj[p]));
						return str.join("&");
					}
				}
				).success(function(response) {
					successCallback(response);
					
				}).error(function(response){
					console.log(response)
					errorCallback(response);
				}); 
		
		
		
	};
	
	var sendPost = function(successCallback, errorCallback, data, path){
		
		http(
				{
					method : 'POST',
					url : apiUrl + path, 
					data : data,
					headers: {'Content-Type' : 'application/x-www-form-urlencoded; charset=utf-8'},
					transformRequest : function(obj) {
						var str = [];
						for ( var p in obj)
							str.push(encodeURIComponent(p) + "="
									+ encodeURIComponent(obj[p]));
						return str.join("&");
					}
				}
		).success(function(response) {
			successCallback(response);
		}).error(function(response) {
			errorCallback(response);
		});
		
		
	};
	
	var sendPostAsJson = function(successCallback, errorCallback, data, path){
		
		http(
				{
					method : 'POST',
					url : apiUrl + path,
					data : data,
					headers: {'Content-Type' : 'application/json; charset=utf-8'},
				}
		).success(function(response) {
			successCallback(response);
		}).error(function(response) {
			console.log(response)
			errorCallback(response);
		});
		
		
	};
	
	
  return {
	sendGetWithoutErrorCallback: sendGetWithoutErrorCallback,
	sendPostWithoutErrorCallback: sendPostWithoutErrorCallback,
	sendPostAsJsonWithoutErrorCallback: sendPostAsJsonWithoutErrorCallback,
	sendGet: sendGet,
    sendPost: sendPost,
    sendPostAsJson: sendPostAsJson
  };
}]);