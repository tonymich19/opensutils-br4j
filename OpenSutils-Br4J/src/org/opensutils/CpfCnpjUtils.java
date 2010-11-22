/*
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class <code>CpfCnpjUtils</code> is used for validade, convert and parse 
 * the cpf or cnpj values, is a Utility for CPFCNPJ
 * 
 * @version 1.0
 * @author Felipe Priuli
 */
public abstract class CpfCnpjUtils {
 
	/**
	 * Given value of CNPJ. 
	 * <br>Not verify whether a valid CNPJ
	 * @param String cnpj - the cnpj 
	 * @return the CNPJ String value (99999999999999)
	 */
	public static String cnpjToString(final long cnpj) {
		return CpfCnpjUtils.cnpjToString(String.valueOf(cnpj));
	}	
	
	/**
	 * Given value of CNPJ. 
	 * <br>Not verify whether a valid CNPJ
	 * @param String cnpj - the cnpj 
	 * @return the CNPJ String value (99999999999999)
	 */
	public static String cnpjToString(final String cnpj) {
		String value = cnpj.trim();

		if(value.length() < 14){
			return StringUtils.leftPad(value, 14, "0");
		}else{
			return value;
		}
	}	
	
	/**
	 * Given value of CPF. 
	 * <br>Not verify whether a valid CPF
	 * @param String cpf - the cpf 
	 * @return the CNPJ String value (99999999999)
	 */
	public static String cpfToString(final long cpf) {
		return CpfCnpjUtils.cpfToString(String.valueOf(cpf));
	}	
	
	/**
	 * Given value of CNPJ. 
	 * <br>Not verify whether a valid CNPJ
	 * @param String cnpj - the cnpj 
	 * @return the CNPJ String value (99999999999999)
	 */
	public static String cpfToString(final String cpf) {
		String value = cpf.trim();

		if(value.length() < 11){
			return StringUtils.leftPad(value, 11, "0");
		}else{
			return value;
		}
	}	
	
	/**
	 * Given value of CNPJ or CPF. 
	 * <br>This method verify whether a valid CPF or CNPJ for build de Formated String.
	 * @param String cnpj - the cnpj 
	 * @return the CPF(99999999999) or CNPJ(99999999999999) String value 
	 */
	public static String cpfCnpjToString(final long cpfCnpj) {
		return CpfCnpjUtils.cpfCnpjToString(String.valueOf(cpfCnpj));
	}	
	
	/**
	 * Given value of CNPJ or CPF. 
	 * <br>This method verify whether a valid CPF or CNPJ for build de Formated String.
	 * @param String cnpj - the cnpj 
	 * @return the CPF(99999999999) or CNPJ(99999999999999) String value 
	 */
	public static String cpfCnpjToString(final String cpfCnpj) {
		String value = cpfCnpj.trim();

		if(CpfCnpjUtils.isValidCpf(value)){
			return CpfCnpjUtils.cpfToString(value);
		}else
			return CpfCnpjUtils.cnpjToString(value);
	}	
	
	/**
	 * Returns a formatted string to a CNPJ
	 * <br>Not verify whether a valid CNPJ
	 * @param String cnpj - the cnpj 
	 * @return the CNPJ formated (99.999.999/9999-99)
	 */
	public static String formatCnpj(final String cnpj) {
		String value = cnpj.trim();

		if(value.length() > 14)
			return value;

		value = StringUtils.leftPad(value, 14, "0");

		Matcher m = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})$").matcher(value);
		return m.replaceAll("$1.$2.$3/$4-$5");
	}	

	/**
	 * Returns a formatted string to a CNPJ
	 * <br>Not verify whether a valid CNPJ
	 * @param String cnpj - the cnpj 
	 * @return the CNPJ formated (99.999.999/9999-99)
	 */
	public static String formatCnpj(final long cnpj) {
		return CpfCnpjUtils.formatCnpj(String.valueOf(cnpj));
	}

	/**
	 * Returns a formatted string to a CPF
	 * <br>Not verify whether a valid CPF
	 * @param 	cpf - the cnpj 
	 * @return 	the CPF formated (999.999.999-99)
	 */
	public static String formatCpf(final String cpf) {
		String value =  cpf.trim();
		if(value.length() > 11)
			return value;

		value =  StringUtils.leftPad(value,11,"0");
		Matcher m = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})$").matcher(value);

		return m.replaceAll("$1.$2.$3-$4");
	}
	/**
	 * Returns a formatted string to a CPF
	 * <br>Not verify whether a valid CPF
	 * @param	cpf - the cnpj 
	 * @return	the CPF formated (999.999.999-99)
	 */
	public static String formatCpf(final long cpf) {
		return CpfCnpjUtils.formatCpf(String.valueOf(cpf));
	}

	/**
	 * Returns a formatted string to a CPF or CNPJ
	 * <br>This method verify whether a valid CPF or CNPJ for build de Formated String.
	 * @param	cpf - the cnpj 
	 * @return	the CPF formated (999.999.999-99)
	 */
	public static String formatCpfOrCnpj(final long cpfCnpj){
		String value = Long.toString(cpfCnpj);
		if(CpfCnpjUtils.isValidCpf(value)){
			return CpfCnpjUtils.formatCpf(value);
		}else
			return CpfCnpjUtils.formatCnpj(value);
	}
	
	/**
	 * Checks whether the CPF is valid. 
	 * @param cpf - the CPF number
	 * @return true if valid else return false.
	 */
	public static boolean isValidCpf(final long cpf){
		return isValidCpf(String.valueOf(cpf));
	}
		
	/**
	 * Checks whether the CPF is valid. 
	 * @param cpf - the CPF number
	 * @return true if valid else return false.
	 */
	public static boolean isValidCpf(final String cpf){
		String value = cpf.trim();
		if(value.length() < 11){
			value = StringUtils.leftPad(value, 11, "0");
		}else if(value.length() > 11){
			return false;
		}
		
		if(!value.equals("00000000000")){
			int d1 = 0, d2 = 0;
			int digit1 = 0, digit2 =0, rest=0;
			int digit=0;

			try{
				for (int n_Count = 1; n_Count < value.length() -1; n_Count++){
					digit = Integer.valueOf (value.substring(n_Count -1, n_Count)).intValue();
					d1 = d1 + ( 11 - n_Count ) * digit;
					d2 = d2 + ( 12 - n_Count ) * digit;
				}
				//Primeiro resto divisão por 11.
				rest = (d1 % 11);
				if (rest < 2){
					digit1 = 0;
				}else{
					digit1 = 11 - rest;
				}

				d2 += 2 * digit1;
				//Segundo resto divisão por 11.
				rest = (d2 % 11);
				if (rest < 2){
					digit2 = 0;
				}else{
					digit2 = 11 - rest;
				}

				String digitoValidador = value.substring (value.length()-2, value.length());

				return digitoValidador.equals(String.valueOf(digit1).concat(String.valueOf(digit2)));
			}catch(Exception e){	
				return false;
			}
		}
		return false;
	}

	/**
	 * Checks whether the CNPJ is valid. 
	 * 
	 * @param cnpj - the CNPJ number
	 * @return true if valid else return false.
	 */
	public static boolean isValidCnpj(long cnpj){
		return isValidCnpj(String.valueOf(cnpj));
	}

	/**
	 * Checks whether the CNPJ is valid. 
	 * 
	 * @param cnpj - the CNPJ number
	 * @return true if valid else return false.
	 */
	public static boolean isValidCnpj(final String cnpj){
		String value = cnpj.trim();
		if(value.length() < 14){
			value = StringUtils.leftPad(value, 14, "0");
		}else if(value.length() > 14){
			return false;
		}
		
		try{
			if (!value.equals("00000000000000")){
				int sum = 0, digit=0;
				String calc = value.substring(0,12);
				char[] chrvalue = value.toCharArray();

				//Parte 1
				for( int i = 0; i < 4; i++ )
					if ( chrvalue[i]-48 >=0 && chrvalue[i]-48 <=9 )
						sum += (chrvalue[i] - 48) * (6 - (i + 1));

				for( int i = 0; i < 8; i++ )
					if ( chrvalue[i+4]-48 >=0 && chrvalue[i+4]-48 <=9 )
						sum += (chrvalue[i+4] - 48) * (10 - (i + 1));

				digit = 11 - (sum % 11);
				calc += ( digit == 10 || digit == 11 ) ? "0" : Integer.toString(digit);

				//Parte 2
				sum = 0;
				for ( int i = 0; i < 5; i++ )
					if ( chrvalue[i]-48 >=0 && chrvalue[i]-48 <=9 )
						sum += (chrvalue[i] - 48) * (7 - (i + 1));

				for ( int i = 0; i < 8; i++ )
					if ( chrvalue[i+5]-48 >=0 && chrvalue[i+5]-48 <=9 )
						sum += (chrvalue[i+5] - 48) * (10 - (i + 1));

				digit = 11 - (sum % 11);
				calc += ( digit == 10 || digit == 11 ) ? "0" : Integer.toString(digit);
				return value.equals(calc);
			}
			return false;
		}catch(Exception e){	
			return false;
		}
	}

	/**
	 *	Check if this is a validCPF or CNPJ. 
	 * @param cpfCnpj - the CNPJ number or the CPF number
	 * @return true if valid else return false.
	 */
	public static boolean isValidCpfOrCnpj(final String cpfCnpj){
		if(cpfCnpj.length() <= 11){
			if(isValidCpf(cpfCnpj)){
				return true;
			}else{
				return isValidCnpj(cpfCnpj);
			}
		}else if(cpfCnpj.length() > 11){
			return isValidCnpj(cpfCnpj);
		}else{		
			return false;
		}
	}
	
	/**
	 *	Check if this is a validCPF or CNPJ. 
	 * @param cpfCnpj - the CNPJ number or the CPF number
	 * @return true if valid else return false.
	 */
	public static boolean isValidCpfOrCnpj(final long cpfCnpj){
		return CpfCnpjUtils.isValidCpfOrCnpj(String.valueOf(cpfCnpj));
	}

}
