package com.unq.estip.pada.model;

/**
 * Represents weights and measures for ingredients, products, and recipes.
 * @author lucas
 */
public enum Unit {

	unidad,
	mm, cm, dm, m,
	kg, hg, dag, gr, dg, cg, mg,
	kl, hl, dal, lt, dl, cl, ml,
	m2, dm2, cm2, mm2,
	m3, dm3, cm3, mm3;
	

	/** Intenta obtener el enum correspondiente a un valor, devuelve null si no lo encuentra*/
	public static Unit getEnum(String unit) {
		try{ return Unit.valueOf(unit); }
		catch(IllegalArgumentException e) {return null;}
	}
	
}
