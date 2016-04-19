package com.unq.estip.pada.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.model.Ingredient;
import com.unq.estip.pada.model.Unit;
import com.unq.estip.pada.persistence.IngredientDAO;

public class IngredientService {

	private IngredientDAO ingredientDAO;

	public IngredientDAO getIngredientDAO() {
		return ingredientDAO;
	}

	public void setIngredientDAO(IngredientDAO ingredientDAO) {
		this.ingredientDAO = ingredientDAO;
	}
	
	@Transactional
	public void save(String name, Double price, Double quantity, String brand, Unit unit){
		Ingredient i = new Ingredient(name, price, quantity, brand, unit);
		this.ingredientDAO.save(i);
	}
	
	public List<Ingredient> findAll(){
		return this.ingredientDAO.findAll();
	}

	public Ingredient findById(int id) {
		return this.ingredientDAO.findById(id);
	}
	
}
