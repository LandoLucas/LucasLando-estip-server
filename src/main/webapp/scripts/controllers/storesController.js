padaApp.controller('storesController', ['$scope','$filter','restClient', function (scope,filter,restClient) {
	
	scope.editing = false;
	scope.newStore = [];
	scope.displayedCollection = [];
	
	scope.storeAlreadyExists = function(){
		
		if(scope.displayedCollection == null || scope.newStore == null || scope.newStore.name == null) return false;
		for(var i=0; i < scope.displayedCollection.length; i++){
			if (scope.displayedCollection[i].name.toUpperCase() === scope.newStore.name.toUpperCase()) return true;
		}
		
		return false;
	};
	
	
	scope.removeStore = function(store){
		
		var data = {'id' : store.id };
		
		scope.removeOk = function(response) {
			restClient.sendGetWithoutErrorCallback(scope.getAllOk, '/stores/all');
			resetForm();
		};
		restClient.sendPostWithoutErrorCallback(scope.removeOk, data, '/stores/remove');
	};
	
	
	scope.editOk = function(response){
		scope.editing = false;
		scope.saveOk(response);
	}
	
	scope.saveOk = function(response) {
		restClient.sendGetWithoutErrorCallback(scope.getAllOk, '/stores/all');	
		resetForm();
	};	
	
	scope.save = function(){
		data = buildDataForUpdateOrSave("");
		restClient.sendPostWithoutErrorCallback(scope.saveOk, data, '/stores/save');
	};
	
	buildDataForUpdateOrSave = function(id){
		
		if( scope.newStore.telephone == null) scope.newStore.telephone = "";
		if( scope.newStore.cellphone == null) scope.newStore.cellphone = "";
		if( scope.newStore.address == null) scope.newStore.address = "";
		
		return {'id' : id,
				'name' : scope.newStore.name,
				'address': scope.newStore.address,
				'telephone' : scope.newStore.telephone,
				'cellphone' : scope.newStore.cellphone
				};
		
	}
	
	resetForm = function(){
		scope.newStoreForm.$setPristine();
		
		if(scope.newStore != null){
			scope.newStore.name = "";
			scope.newStore.address = "";
			scope.newStore.telephone = "";
			scope.newStore.cellphone = "";
			scope.newStore = null;
		}
		scope.rowSelected = false;
	}
	
	scope.executeEdition = function(){
		data = buildDataForUpdateOrSave(scope.newStore.id);
		restClient.sendPostWithoutErrorCallback(scope.editOk, data, '/stores/save');
	}
	
	scope.edit = function(store){
		scope.editing = true;
		
		scope.newStore = [];
		scope.newStore.id = store.id;
		scope.newStore.name = store.name;
		scope.newStore.address = store.address;
		scope.newStore.telephone = store.telephone;
		scope.newStore.cellphone = store.cellphone;
	}
	
	scope.cancelEdition = function(){
		scope.editing = false;
		if(scope.newStore != null){
			scope.newStore.name = "";
			scope.newStore.address = "";
			scope.newStore.telephone = "";
			scope.newStore.cellphone = "";
			scope.newStore = null;
		}
	}
	
	scope.getAllOk = function(response){
		scope.stores = response;
		scope.displayedCollection = [].concat(scope.stores);
	};
	
	restClient.sendGetWithoutErrorCallback(scope.getAllOk, '/stores/all');
}])
