/*
 * 	@(#)RomanNumber.java	0.1 2010/11/29
 *	Copyright (c) 2010 Felipe Priuli
 *
 *	This file is part of OpenSutils-Br4J.
 *
 *	OpenSutils-Br4J is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU Lesser General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, any later version.
 *
 *	OpenSutils-Br4J is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU Lesser General Public License for more details.
 *
 *	You should have received a copy of the GNU Lesser General Public License
 *	along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.opensutils;

/**
 * This class <code>RomanNumber</code> represents the values are in Roman numerals, it contains functionality to convert
 * numbers into Roman numerals.
 * @author Felipe Priuli
 * @version 0.1 - 29/11/2010
 */
public class RomanNumber {
	/**
	 * The integer value in decimal base.
	 */
	private int value;
	
	/**
	 * Represents the numerical codes for the representation of Roman numerals,
	 * is used to format, parse the numbers in Roman in this class.
	 */
	public enum RomanNumberCode{
		
		M(1000), CM(900), D(500), CD(400), C(100), XC(90), L(50),XL(40), X(10), IX(9), V(5), IV(4), I(1);
		
		private int value;
		private RomanNumberCode(int value){
			this.value = value;
		}
		public int getValue(){
			return value;
		}
	}
	/**
	 * Contructor for Roman numerals
	 * @param decimal - The integer value in decimal base for this object representation of Roman numerals
	 */
	public RomanNumber(int decimal){
		if(decimal == 0 ){
			throw new ArithmeticException("Zero is not allowed. There is no representation for Roman numeral");
		}
		if(decimal < 0 ){
			throw new ArithmeticException("There are no negative numbers to Roman");
		}
		
		this.value = decimal;
	}
	/**
	 * Contructor for Roman numerals
	 * @param romanNumber - The String value in Roman numerals
	 * @throws IllegalArgumentException - if not valid roman number.
	 */
	public RomanNumber(String romanNumber){
		if(!isValid(romanNumber)){
			throw new IllegalArgumentException("Is not a valid roman number");
		}else{
			setValue( parse(romanNumber) );
		}
	}
	/**
	 * Contructor for Roman numerals
	 * @param romanNumber - Other Roman numerals to get a decimal value
	 */
	public RomanNumber(RomanNumber romanNumber){
		this(romanNumber.getValue());
	}
	
	/**
	 * Get a value of this roman numerals
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Set a value of this roman numerals
	 * @param decimal - The integer value in decimal base for this object representation of Roman numerals
	 */
	public void setValue(int decimal) {
		this.value = decimal;
	}
	
	/**
	 * Return String value of Roman numerals
	 * @return the roman code
	 */
	public String toString(){
		return format(value);
	}

	/**
	 * Formats decimal integer numbers in Roman numerals
	 * @param decimal - The integer value in decimal base to convert in String Raimn numeral
	 * @return the roman code
	 * @throws ArithmeticException - to negative values or zero values in param.
	 */
	public static String format(final int _decimal){
		if(_decimal == 0 ){
			throw new ArithmeticException("Zero is not allowed. There is no representation for Roman numeral");
		}
		if(_decimal < 0 ){
			throw new ArithmeticException("There are no negative numbers to Roman");
		}
		
		
		StringBuilder sb = new StringBuilder();
		
		int decimal = new Integer(_decimal);
		RomanNumberCode[] codes = RomanNumberCode.values();
		for (int i = 0; i < codes.length; i++) {
			 while(decimal >= codes[i].getValue()){
				 decimal -= codes[i].getValue();
				 sb.append(codes[i].name());
			 }
		}
		codes = null;
		
		return sb.toString();
	}
	
	/**
	 * Parse roman numerals to a decimal integer numbers
	 * @param romanNumber - the roman  
	 * @return decimal integer numbers
	 */
	public static int parse(final String _romanNumber){
		String romanNumber = _romanNumber.trim();
		int _return = 0;
		RomanNumberCode[] codes = RomanNumberCode.values();
		
		String previous = "";
		LOOPSEARCH:
		for (int i = 0; i < romanNumber.length(); i++) {
			if(i+2 <= romanNumber.length())
				previous = romanNumber.substring(i,i+2);
			else
				previous =romanNumber.substring(i,i+1);
			

			for(int j = 0; j < codes.length; j++){
				if(previous.equals(codes[j].name())){
					i++;
					_return += codes[j].getValue();
					continue LOOPSEARCH ;
				}
			}
			
			previous =romanNumber.substring(i,i+1);
			for(int y = 0; y < codes.length; y++){
				if(previous.equals(codes[y].name())){
					_return += codes[y].getValue();
					continue LOOPSEARCH;
				}
			}
		}
		
		return _return;
	}

	/**
	 * Check if a valid roman number.
	 * @param romanNumber - the string with roman numeral.
	 * @return true if a valid roman number, false if not a valid roman number.
	 */
	public static boolean isValid(final String romanNumber) {
		if(romanNumber == null || romanNumber.trim().isEmpty())
			return false;
		
		char[] chars = romanNumber.trim().toCharArray();  

		for(char c :chars){
			if (Character.isLowerCase(c)){
				return false; 
			}else if (c != 'I' && c != 'V' && c != 'X' && c != 'L' && c != 'C' && c != 'D' && c != 'M'){
				return false;  
			}
		}

		return true;  
	}  

}



