padaApp.controller('purchasesController', ['$scope' ,'restClient', function(scope, restClient) {
	
	scope.totalPrice = 0;	
	scope.editing = false;
	scope.editElemID = null;
	
	//Mercados
	scope.getAllStoresOk = function(response){
		scope.stores = response;
	};
	restClient.sendGetWithoutErrorCallback(scope.getAllStoresOk, '/stores/all');

	//Date picker
	scope.format = 'shortDate';
	scope.options = {
	    showWeeks: false,
	    maxDate: new Date()
	};
	
	//Guardar compra
	scope.save = function(){
		
		data = {
			id : scope.editElemID,
			store : scope.newPurchase.store.id,
			date : scope.newPurchase.date,
			price : scope.newPurchase.price
		};
		
		var success = function(response){
			restClient.sendGetWithoutErrorCallback(scope.getAllOk, '/purchase/all');
		}
		
		restClient.sendPostAsJsonWithoutErrorCallback(success, data, '/purchase/save');
		scope.cancelEdition();
	}
	
	//Reset formulario
	scope.clearForm = function(){
		scope.newPurchase.store = null;
		scope.newPurchase.date = null;
		scope.newPurchaseForm.$setPristine();
		scope.newPurchase.price = null;
	}
	
	//Callback obteniendo las compras
	scope.getAllOk = function(response){
		scope.allPurchases = response;
		
		console.log(scope.allPurchases);
		
		var price = function(e){return e.price};
		var add = function(x,y){ return x + y};
		var prices = response.map( price );
		scope.totalPrice = prices.reduce(add , 0);
	};
	restClient.sendGetWithoutErrorCallback(scope.getAllOk, '/purchase/all');
	
	//Borrar compra
	scope.remove = function(p){
		
		var success = function(response){
			restClient.sendGetWithoutErrorCallback(scope.getAllOk, '/purchase/all');
		}

		restClient.sendPostWithoutErrorCallback(success, {id: p.id}, '/purchase/delete');
	};	
	
	scope.startEditing = function(p){
		scope.editing = true;
		scope.editElemID = p.id;
		scope.newPurchase = [];
		scope.newPurchase.price = p.price;
		scope.newPurchase.date = new Date(p.date);
		scope.newPurchase.store = p.store;
		
		var match = function(store){ return store.id === p.store.id };
		scope.newPurchase.store = scope.stores.filter( match )[0];
	}
	
	scope.cancelEdition = function(){
		scope.editing = false;
		scope.editElemID = null;
		scope.clearForm();
	}
	
}]);	