package com.unq.estip.pada.rest;

public class ConversionUtilities {

	/**
	 * Intenta convertir un String a Double, devuelve null en caso de numberFormatException
	 */
	public static Double parseDouble(String str) {
		try{ return Double.parseDouble(str); }
		catch(NumberFormatException e){ return null; }
	}

}
