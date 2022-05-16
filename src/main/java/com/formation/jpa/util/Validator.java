package com.formation.jpa.util;

public class Validator {
	
	public static boolean  isValidString(String s) {
		
		if(s.isBlank()||s.isEmpty()) {
			return false;
		}
		return true;
		
		
	}
	
public static boolean  isValidPrice(float f) {
		
		if(f< 0) {
			return false;
		}
		return true;
		
		
	}
public static boolean  isValidStock(int q) {
	
	if(q<0) {
		return false;
	}
	return true;
	
	
}

}
