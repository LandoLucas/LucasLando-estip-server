padaApp.controller('salesController', ['$scope' ,'restClient', function(scope, restClient) {
	
	scope.hideDelivered = true;
	
	scope.sortDate = function(sale) {
	    return new Date(sale.date);
	};
	
	scope.saleOrder = "date";
	
	scope.selectedProducts = [];
	scope.totalPriceSeletedProducts = 0;
	
	//Time picker
	scope.newSale = [];
	scope.newSale.time = new Date(1970, 0, 0, 17, 0, 0);
	scope.hstep = 1;
	scope.mstep = 15;
	
	//Date picker
	scope.format = 'shortDate';
	scope.options = {
	    minDate: new Date(),
	    showWeeks: false
	};
	
    scope.open = function(event) {
    	scope.opened = true;
	};
	
	scope.isCollapsed = true;
	
	scope.getOrder = function(sale){
		if(scope.saleOrder == "id") return sale.id;
		if(scope.saleOrder == "firstName") return sale.client.firstName;
		if(scope.saleOrder == "lastName") return sale.client.lastName;
		if(scope.saleOrder == "date") return sale.date;
		if(scope.saleOrder == "price") return sale.price;
	}
		
	//Adds products in new sale
	scope.addProduct = function(){
		
		scope.totalPriceSeletedProducts = scope.totalPriceSeletedProducts + scope.newSale.prod.price * scope.newSale.quantity;
		
		scope.selectedProducts.push({
			id: scope.newSale.prod.id,
			name: scope.newSale.prod.name,	
			price: scope.newSale.prod.price * scope.newSale.quantity,
			quantity : scope.newSale.quantity
		});
		
		scope.newSale.prod = null;
		scope.newSale.quantity = null;
	};
	
	//Removes the selected product for a new sale
	scope.removeSelectedProd = function(selectedProduct){
		for(var i = 0; i < scope.selectedProducts.length; i++){
		    if(scope.selectedProducts[i].name == selectedProduct.name){
		    	scope.totalPriceSeletedProducts = scope.totalPriceSeletedProducts - scope.selectedProducts[i].price;
		    	scope.selectedProducts.splice(i,1);
		    }
		}
	};
	
	var changedStateOK = function(response){
		restClient.sendGetWithoutErrorCallback(scope.getAllSalesOk, '/sales/all');
	}
	
	scope.delivered = function(sale){
		restClient.sendPostWithoutErrorCallback(changedStateOK, {id: sale.id}, '/sales/delivered');
	}
	
	scope.prepared = function(sale){
		restClient.sendPostWithoutErrorCallback(changedStateOK, {id: sale.id}, '/sales/prepared');
	}
	
	scope.requested = function(sale){
		restClient.sendPostWithoutErrorCallback(changedStateOK, {id: sale.id}, '/sales/requested');
	}
	
	scope.inPreparation = function(sale){
		restClient.sendPostWithoutErrorCallback(changedStateOK, {id: sale.id}, '/sales/inPreparation');
	}
	
	
	
	scope.removeSale = function(sale){
		
		var removeSaleOk = function(response){
			restClient.sendGetWithoutErrorCallback(scope.getAllSalesOk, '/sales/all');
		}	
		
		restClient.sendPostWithoutErrorCallback(removeSaleOk, {id: sale.id}, '/sales/remove');
	}
	
	scope.cancelEdition = function(){
		scope.editing = false;
		
		scope.selectedProducts = [];
		scope.totalPriceSeletedProducts = 0;
		
		scope.newSale.client = "";
		scope.newSale.date = "";
		scope.newSale.time = "";
		scope.newSale.comment = "";
		scope.newSaleForm.$setPristine();
		
	}
	
	scope.editSale = function(sale){
		
		scope.editing = true;
		
		scope.newSale.client = sale.client;
		scope.newSale.date = new Date(sale.date);
		scope.newSale.time = new Date(sale.date);
		scope.newSale.id = sale.id;
		scope.newSale.comment = sale.comment
		
		for(var i = 0; i < sale.products.length; i++){
			scope.selectedProducts.push({
				id: sale.products[i].product.id,
				name : sale.products[i].product.name,
				price : sale.products[i].product.price * sale.products[i].quantity,
				quantity : sale.products[i].quantity
			});
			scope.totalPriceSeletedProducts = scope.totalPriceSeletedProducts + sale.products[i].product.price * sale.products[i].quantity;
		}
		
		for(var i = 0; i < scope.clients.length; i++){
			if( scope.clients[i].firstName.toUpperCase() === sale.client.firstName.toUpperCase() 
				&& scope.clients[i].lastName.toUpperCase() === sale.client.lastName.toUpperCase()	
			  ){
				scope.newSale.client = scope.clients[i];
				break;
			}
		}

	}
	
	scope.executeEdition = function(){
		var data = {
				id : scope.newSale.id,
				clientID : scope.newSale.client.id,
				products : scope.selectedProducts,
				price : scope.totalPriceSeletedProducts,
				date : scope.newSale.date, 	
				time : scope.newSale.time,
				comment : scope.newSale.comment
			};
		
		var editSaleOk = function(response){
			scope.editing = false;
			scope.selectedProducts = [];
			scope.totalPriceSeletedProducts = 0;
			restClient.sendGetWithoutErrorCallback(scope.getAllSalesOk, '/sales/all');
			
			scope.newSale.client = "";
			scope.newSale.date = "";
			scope.newSale.comment = "";
			scope.newSaleForm.$setPristine();	
			restClient.sendGetWithoutErrorCallback(scope.getAllSalesOk, '/sales/all');
		}
		
		restClient.sendPostAsJsonWithoutErrorCallback(editSaleOk, data, '/sales/update');
	}
	
	scope.saveSaleOk = function(response){
		scope.selectedProducts = [];
		scope.totalPriceSeletedProducts = 0;
		restClient.sendGetWithoutErrorCallback(scope.getAllSalesOk, '/sales/all');
		
		scope.newSale.client = "";
		scope.newSale.date = "";
		scope.newSale.comment = "";
		scope.newSaleForm.$setPristine();	
	}
	
	//Saves a new sale
	scope.save = function(){
		
		var data = {
			clientID : scope.newSale.client.id,
			products : scope.selectedProducts,
			price : scope.totalPriceSeletedProducts,
			date : scope.newSale.date, 	
			time : scope.newSale.time,
			comment: scope.newSale.comment
		};
		console.log(data.comment)
		
		restClient.sendPostAsJsonWithoutErrorCallback(scope.saveSaleOk, data, '/sales/save');
	};
	
	
	//Loading needed data
	scope.getAllProductsOk = function(response){
		scope.allProducts = response;
	};
	restClient.sendGetWithoutErrorCallback(scope.getAllProductsOk, '/products/all');
	
	scope.getAllSalesOk = function(response){
		scope.sales = response;
		scope.years = [];
		scope.months = ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
		for(var i=0; i < scope.sales.length; i ++) {
			var d = new Date(scope.sales[i].date);
			var year = d.getFullYear()
			var month = d.getMonth()
			if(scope.years.indexOf(year) <= -1){
				scope.years.push(year)
			}
			scope.sales[i].year = year // augment sale with year and month
			switch(month) {
			  case 0: m = "Enero"; break; case 1: m = "Febrero"; break; case 2: m = "Marzo"; break; case 3: m = "Abril"; break; 
			  case 4: m = "Mayo"; break; case 5: m = "Junio"; break; case 6: m = "Julio"; break; case 7: m = "Agosto"; break;
			  case 8: m = "Septiembre"; break; case 9: m = "Octubre"; break; case 10: m = "Noviembre"; break; case 11: m = "Diciembre"; break;
			}
			scope.sales[i].month = m
		}
	}
	restClient.sendGetWithoutErrorCallback(scope.getAllSalesOk, '/sales/all');
	
	scope.getAllClientsOk = function(response){
		scope.clients = response;
	};
	restClient.sendGetWithoutErrorCallback(scope.getAllClientsOk, '/clients/all');
}]);	