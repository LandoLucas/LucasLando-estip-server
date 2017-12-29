padaApp.controller('statisticsController', ['$scope' ,'restClient', function(scope, restClient) {
	
	scope.salesObtained = false;
	scope.purchasesObtained = false;
	scope.monthlabels = ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
	
	var maxYear = new Date().getFullYear();
	var minYear = 2015;
	scope.selectedYear = 2016;
	
	scope.years = [];
	for(var i=minYear; i <= maxYear; i++){
		scope.years.push(i);
	}
	
	scope.update = function() { scope.drawCharts() }

	scope.drawCharts = function(){
		if( scope.salesObtained && scope.purchasesObtained){
			scope.thisYearSales = scope.filterByYear(scope.selectedYear, scope.allSales);
			scope.thisYearPurchases = scope.filterByYear(scope.selectedYear, scope.allPurchases);
			
			scope.chartOne();
			scope.chartTwo();
			scope.chartThree();
		}
	}
	
	scope.filterByYear = function(fullYear, data){
		return data.filter( function(d){return fullYear.toString() === new Date(d.date).getFullYear().toString()} )
	}
	
	
	scope.chartThree = function(){
		scope.igseries = ['Ingresos', 'Gastos'];
		
		var incomes = [];
		for (var month = 0; month < 12; month++) {
			var monthlySales = scope.thisYearSales.filter( function(sale){
				return month === new Date(sale.date).getMonth();
			} )
			
			var add = function(total, sale){ 
				return sale.price + total
			};
			var monthlyTotal = monthlySales.reduce( add, 0);
			incomes.push(monthlyTotal);
		}
		
		var outcomes = [];
		for (var month = 0; month < 12; month++) {
			var monthlyPurchases = scope.thisYearPurchases.filter( function(p){
				return month === new Date(p.date).getMonth();
			} )
			
			var add = function(total, p){ 
				return p.price + total
			};
			var monthlyTotal = monthlyPurchases.reduce( add, 0);
			outcomes.push(monthlyTotal);
		}
		
		
		scope.igdata = [incomes, outcomes];
		
		scope.options = {
			    scales: {
			      yAxes: [
			        {
			          id: 'y-axis-1',
			          type: 'linear',
			          display: true,
			          position: 'left'
			        }	
			      ]
			    }
		};
	};
	
	scope.chartTwo = function(){
		
		scope.vm2016data = [];
		for (var month = 0; month < 12; month++) {
			
			var monthlySales = scope.thisYearSales.filter( function(sale){
				return month === new Date(sale.date).getMonth();
			} )

			scope.vm2016data.push(monthlySales.length)
		}
		
	}
	
	scope.chartOne = function(){
		var thisYearProducts = scope.thisYearSales.map( function(sale){ return sale.products} )
		
		var allProducts = [];
		thisYearProducts.map( function(a){ 
			a.map(function(b){ allProducts.push(b)})
		} )
		
		var allIDs = allProducts.map( function(p){
			return p.product.id
		} );
		
		var onlyUnique = function(value, index, self) { 
			return self.indexOf(value) === index;
		};
		
		var uniqueIDs = allIDs.filter( onlyUnique);
		
		scope.pv2016labels = [];
		scope.pv2016data = [];
		
		uniqueIDs.map( function(id){
				var ps = allProducts.filter( function(p){ 
							return p.product.id === id
					})
				
				var add = function(x,p){ 
					return p.quantity + x
				};
				var sold = ps.reduce(add , 0)
				
				scope.pv2016labels.push(ps[0].product.name);
				scope.pv2016data.push(sold);
			}
		);
	};
	
	
	
	
	scope.getAllSalesOk = function(response){
		scope.allSales = response;
		scope.salesObtained = true;
		scope.drawCharts();
	}
	
	scope.getAllPuchasesOk = function(response){
		scope.allPurchases = response;
		scope.purchasesObtained = true;
		scope.drawCharts();
	}
	
	restClient.sendGetWithoutErrorCallback(scope.getAllPuchasesOk, '/purchase/all');
	restClient.sendGetWithoutErrorCallback(scope.getAllSalesOk, '/sales/all');
}]);	