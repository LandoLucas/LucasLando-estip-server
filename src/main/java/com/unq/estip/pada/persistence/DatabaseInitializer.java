package com.unq.estip.pada.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;

import com.unq.estip.pada.model.Unit;
import com.unq.estip.pada.service.ClientService;
import com.unq.estip.pada.service.IngredientService;
import com.unq.estip.pada.service.ProductService;
import com.unq.estip.pada.service.SaleService;

public class DatabaseInitializer {

	private IngredientService ingredientService;
	private ProductService productService;
	private ClientService clientService;
	private SaleService saleService;
	
	public SaleService getSaleService() {
		return saleService;
	}

	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public IngredientService getIngredientService() {
		return ingredientService;
	}

	public void setIngredientService(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	@PostConstruct
	public void populateDatabase(){
		System.out.println("Populating database");
		
		this.ingredientService.save("Harina", 20d, 1d, "Blancaflor", Unit.kg);
		this.ingredientService.save("Azucar impalpable", 14.29d, 1d, "", Unit.kg);
		this.ingredientService.save("Esencia de vainilla", 24.99d, 1d, "", Unit.lt);
		this.ingredientService.save("Manteca", 12.99d, 200d, "Sancor", Unit.gr);		
		
		this.productService.save("Brownies", 25d, 1d, Unit.unidad);
		this.productService.save("Lemon Pie mediana", 180d, 1d, Unit.unidad);
		this.productService.save("Cheesecake", 180d, 1d, Unit.unidad);
		this.productService.save("Empanadas arabes", 120d, 12d, Unit.unidad);
		
		this.clientService.save(null, "Lalo", "Landa", "4111-1212", "15-6565-4545", "direccion 1");
		this.clientService.save(null, "Frank", "Grimes", "", "5411-1111-2222", "");
		this.clientService.save(null, "Otto", "", "4222-1212", "", "");
		this.clientService.save(null, "Lionel", "Hutz", "", "", "direccion 4 piso 3");
		
		Map<Integer, Double> products = new HashMap<Integer, Double>();
		products.put( 1 , 1d );
		
		this.saleService.save(null, 1, products, 25d, new DateTime().plusMonths(1));
		
		products = new HashMap<Integer, Double>();
		products.put( 1 , 2d );
		products.put( 2 , 1d );
		
		this.saleService.save(null, 2, products, 205d, new DateTime().plusMonths(2));
		
		products = new HashMap<Integer, Double>();
		products.put( 1 , 3d );
		products.put( 2 , 2d );
		products.put( 3 , 1d );
		this.saleService.save(null, 3, products, 385d, new DateTime().plusDays(5));
		
		products = new HashMap<Integer, Double>();
		products.put( 1 , 4d );
		products.put( 2 , 3d );
		products.put( 3 , 2d );
		products.put( 4 , 1d );
		this.saleService.save(null, 4, products, 500d, new DateTime().plusMinutes(50));
		
		System.out.println("Database populated");
	}
	
}
