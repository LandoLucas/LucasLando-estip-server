package com.unq.estip.pada.utils;

public class Utilities {

	/**
	 * Intenta convertir un String a Double, devuelve null en caso de numberFormatException
	 */
	public static Double parseDouble(String str) {
		try{ return Double.parseDouble(str); }
		catch(NumberFormatException e){ return null; }
	}

	public static boolean isVariableSet(Object o){
		return o != null && !o.equals("") && !o.equals(0); 
	}
}
