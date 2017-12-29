padaApp.controller('calendarController', ['$scope' ,'restClient', '$uibModal', function(scope, restClient, uibModal) {
	
	scope.events = [];
	
	scope.eventSources = {
		    events: scope.events,
	        color: 'orange',   
	        textColor: 'black',
	};
	
	scope.alertOnEventClick = function( date, jsEvent, view){
		scope.sale = scope.allSales.find( function(s){return s.id === date.id} );
		
		var prices = scope.sale.products.map(function(p){return p.product.price * p.quantity});
		scope.saleTotal = prices.reduce( function(x,y){ return x+y},0);
		
		var modalInstance = uibModal.open({
			animation: scope.animationsEnabled,
			scope: scope,
			template:"	<div id='calendarModal'>{{sale.client.firstName}} {{sale.client.lastName}} 														" +
					 "  	<ul ng-repeat='prod in sale.products'> 																						" +
					 "  		<li>{{prod.product.name}} x {{prod.quantity}} {{prod.product.unit}} - {{prod.product.price * prod.quantity | currency}} </li>	" +
					 "		</ul>																														" +
					 "		<p>Total: {{saleTotal | currency}}</p>																				" +
					 "	</div>																															",
			controller: function(){}
		});
    };
	
	scope.getAllSalesOk = function(response){
		
		scope.allSales = response;
		var toEvent = function(sale){
			var event = {
				id: sale.id,
				title: sale.client.firstName + ' ' + sale.client.lastName,
				start: moment(sale.date),
				end: moment(sale.date).add(30, 'minutes'),
				allDay: false
			};
			scope.events.push(event);
		};
		
		response.map( toEvent );
		
		scope.uiConfig = {
			      calendar:{
			        height: 550,
			        editable: true,
			        header:{
//			          left: 'month agendaWeek agendaDay',
//			          right: 'today prev,next'
			        	  
			          left: 'title',
			          center: 'month agendaWeek agendaDay',
			          right: 'today prev,next'
			        	  
			        },
			        monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
			        monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
			        dayNames: ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sabado'],
			        dayNamesShort: ['Dom','Lun','Mar','Mie','Jue','Vie','Sab'],
			        buttonText: {
			            today: 'Hoy',
			            month: 'Mes',
			            week: 'Semana',
			            day: 'Dia'
			           },
			        eventClick: scope.alertOnEventClick,
			        eventDrop: scope.alertOnDrop,
			        eventResize: scope.alertOnResize
			      }
			    };
	}
	
	restClient.sendGetWithoutErrorCallback(scope.getAllSalesOk, '/sales/all');
}]);	