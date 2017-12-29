padaApp.controller('productsController', ['$scope' ,'restClient', function(scope, restClient) {

	scope.editing = false;
	scope.editElemID = null;
	
	//Valida que un producto siendo tipeado no exista ya en la lista cuando su nombre y cantidad combinada existan
	scope.productAlreadyExists = function(){
		
		if(scope.displayedCollection == null || scope.newProd == null 
				|| scope.newProd.quantity == null || scope.newProd.name == null ) return false;
		
		for(var i=0; i < scope.displayedCollection.length; i++){
			if ( scope.displayedCollection[i].name.toUpperCase() === scope.newProd.name.toUpperCase() &&  
			     scope.displayedCollection[i].quantity === scope.newProd.quantity
			   ) return true;
		}
		
		return false;
	};
	
	scope.removeProduct = function(prod){
		var data = {"name": prod.name,
					"quantity" : prod.quantity
				   };
		scope.removeOk = function(response) {
			restClient.sendGetWithoutErrorCallback(scope.getAllOk, '/products/all');
		};
		restClient.sendPostWithoutErrorCallback(scope.removeOk, data, '/products/remove');
	};
	
	scope.startEditing = function(p){
		scope.editing = true;
		scope.editElemID = p.id;
		
		scope.newProd = [];
		scope.newProd.name = p.name;
		scope.newProd.price = p.price;
		scope.newProd.quantity = p.quantity;
		
		scope.newProd.unit = scope.units.find( function(u){return u === p.unit});
	};
	
	scope.cancelEdition = function(){
		scope.editing = false;
		scope.clearForm();
	};
	
	scope.save = function(){
		
		data = {
				id : scope.editElemID,
				name : scope.newProd.name,
				price : scope.newProd.price,
				quantity : scope.newProd.quantity,
				unit : scope.newProd.unit
		};
		
		scope.saveOk = function(response) {
			restClient.sendGetWithoutErrorCallback(scope.getAllOk, '/products/all');
			scope.cancelEdition();
		};		
					
		restClient.sendPostWithoutErrorCallback(scope.saveOk, data, '/products/save');
	};
	
	scope.clearForm = function(){
		scope.editElemID = null;
		scope.newProdForm.$setPristine();
		scope.newProd.name = "";
		scope.newProd.price = "";
		scope.newProd.quantity = "";
		scope.newProd.unit = "";
	};
	
	
	scope.getAllOk = function(response){	
		scope.products = response;
		scope.displayedCollection = [].concat(scope.products);
	};
	scope.getUnitsOk = function(response){
		scope.units = response
	};
	
	restClient.sendGetWithoutErrorCallback(scope.getUnitsOk, '/products/units');
	restClient.sendGetWithoutErrorCallback(scope.getAllOk, '/products/all');
	
	
	
}]);	