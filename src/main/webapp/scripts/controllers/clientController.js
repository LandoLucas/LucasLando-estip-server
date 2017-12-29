padaApp.controller('clientController', ['$scope' ,'restClient', function(scope, restClient) {
	
	scope.clientAlreadyExists = function(){
		
		if(scope.displayedCollection == null || scope.newClient == null 
			 || scope.newClient.firstName == null 
		  ) 
		  return false;
		
		for(var i=0; i < scope.displayedCollection.length; i++){
			
			var rowLastName = scope.displayedCollection[i].lastName;
			var rowFirstName = scope.displayedCollection[i].firstName;
			
			var equalFirstName = rowFirstName.toUpperCase() === scope.newClient.firstName.toUpperCase();
			
			var equalLastName = rowLastName == null || rowLastName == "";
			if( scope.newClient.lastName != null && rowLastName != null  ){
				equalLastName = rowLastName.toUpperCase()  
									=== scope.newClient.lastName.toUpperCase(); 
			}
			
			if( scope.previousFirstName != null && scope.previousLastName != null){
				if( rowFirstName.toUpperCase() === scope.previousFirstName.toUpperCase() &&
					rowLastName.toUpperCase() === scope.previousLastName.toUpperCase()		
				){
					return false;
				}
			}
			
			if ( equalFirstName && equalLastName ) return true;
		}
		
		return false;
	};
	
	scope.removeClient = function(client){
		
		var data = {'firstName' : client.firstName,
					'lastName': client.lastName
				   };
		
		scope.removeOk = function(response) {
			restClient.sendGetWithoutErrorCallback(scope.getAllOk, '/clients/all');
			resetForm();
		};
		restClient.sendPostWithoutErrorCallback(scope.removeOk, data, '/clients/remove');
	};
	
	scope.update = function(){

		data = buildDataForUpdateOrSave(scope.clientID);
		
		scope.updateOk = function(response) {
			resetForm();
			restClient.sendGetWithoutErrorCallback(scope.getAllOk, '/clients/all');
		};		
					
		restClient.sendPostWithoutErrorCallback(scope.updateOk, data, '/clients/save');
		
	};
	
	buildDataForUpdateOrSave = function(id){
		
		if( scope.newClient.telephone == null) scope.newClient.telephone = "";
		if( scope.newClient.cellphone == null) scope.newClient.cellphone = "";
		if( scope.newClient.address == null) scope.newClient.address = "";
		if( scope.newClient.lastName == null) scope.newClient.lastName = "";
		
		return {'id' : id,
				'firstName' : scope.newClient.firstName,
				'lastName': scope.newClient.lastName,
				'telephone' : scope.newClient.telephone,
				'cellphone' : scope.newClient.cellphone,
				'address' : scope.newClient.address
				};
		
	}
	
	scope.save = function(){
		
		data = buildDataForUpdateOrSave("");
		
		scope.saveOk = function(response) {
			restClient.sendGetWithoutErrorCallback(scope.getAllOk, '/clients/all');	
			resetForm();
		};		
					
		restClient.sendPostWithoutErrorCallback(scope.saveOk, data, '/clients/save');
	};
	
	resetForm = function(){
		scope.newClientForm.$setPristine();
		
		if(scope.newClient != null){
			scope.newClient.firstName = "";
			scope.newClient.lastName = "";
			scope.newClient.telephone = "";
			scope.newClient.cellphone = "";
			scope.newClient.address = "";
			scope.newClient = null;
		}
		scope.clientID = "";
		scope.rowSelected = false;
		scope.previousFirstName = null;
		scope.previousLastName = null;
	}
	
	scope.getAllOk = function(response){
		scope.clients = response;
		scope.displayedCollection = [].concat(scope.clients);
	};
	
	restClient.sendGetWithoutErrorCallback(scope.getAllOk, '/clients/all');
	
	scope.selected = function(client){
		scope.rowSelected = client.isSelected;
		if( client.isSelected){
			  scope.newClient = [];
			  scope.newClient.firstName = client.firstName;
			  scope.newClient.lastName = client.lastName;
			  scope.newClient.telephone = client.telephone;
			  scope.newClient.cellphone = client.cellphone;
			  scope.newClient.address = client.address;
			  
			  scope.clientID = client.id;
			  
			  scope.previousFirstName = client.firstName;
			  scope.previousLastName = client.lastName;
		}else{
			  scope.newClient = [];
			  scope.newClient.firstName = null;
			  scope.newClient.lastName = null;
			  scope.newClient.telephone = null;
			  scope.newClient.cellphone = null;
			  scope.newClient.address = null;
		}
		
	};
	
	
}]);	