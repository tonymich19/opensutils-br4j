/*
 * 	 @(#)StringUtils.java	1.4 10/11/22
 * 
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
 * The class <code>StringUtils</code> is utility for working with text values.

 * @version 1.4
 * @author Felipe Priuli
 */
public final class StringUtils {
	
	private StringUtils(){}
	
	/**
	 * Abrevia um nome que for maior do que o tamanho passado, sempre o primeiro e ultimo nome são mantidos.
	 * Abrevia apenas o nome do meio e se o este nome for maior que o tamanho recebido, caso contrario o mesmo nome 
	 * é retornado sem abreviação. Caso o nome continuar maior que o parametro 'size', o nome será truncado.
	 * @param 	name - String, nome que será abreviado.
	 * @param 	size - tamanho que deverá conter o nome completo abreviado
	 * @return 	String (name abreviado).
	 */
	public static String abbreviateName(final String name, int size){
		if( name.length() > size){

			//Verificando se o nome não está abreviado - se tiver pelo menos 1 unico nome inicial continua senão trunca o nome */
			if(name.matches("^(.(\\s|\\.)\\s?)+(.|(\\w(\\s|\\.)\\s?))+$"))
				return name.substring(0,size);
				
			String patern = "(^[\\w,Á,É,Í,Ó,Ú,á,é,í,ó,ú,ã,õ,Ã,Õ,ç,Ç,â,ê,î,ô,ü,Â,Ê,Î,Ô,Ü]+)\\b\\s(.*)(\\b\\s[\\w,Á,É,Í,Ó,Ú,á,é,í,ó,ú,ã,õ,Ã,Õ,ç,Ç,â,ê,î,ô,ü,Â,Ê,Î,Ô,Ü]+$)";  
			String value = name.replace(",", "");
			Pattern pattern = Pattern.compile(patern); 
			Matcher matcher = pattern.matcher(value);		

			String palavraInicio = ""; String palavraMeio = ""; String palavraFinal = "";

			if(matcher.find()){
				palavraInicio = matcher.group(1);
				palavraMeio = matcher.group(2) + " " ;
				palavraFinal = matcher.group(3).trim();

				String palavraMeioAuxiliar = palavraMeio.replaceAll("\\b(([Dd][aeoAEO]\\s))|\\b(([Dd][aoAO][Ss]\\s))|\\b[eE]\\s","");//Troca dos da,das,do,dos,de,e por uma string vazia
				String resp = palavraInicio + " " + palavraMeioAuxiliar + palavraFinal;
				if( resp.length() <= size ){
					return resp;
				}
						
				//Diminui as palavras, deixa o primeiro caracter de uma palavra e adiciona um '.'após, o resto dos outros caracteres são removidos.
				palavraMeioAuxiliar = palavraMeioAuxiliar.replaceAll("(([A-Za-z]|)([\\w]|[Á,É,Í,Ó,Ú,á,é,í,ó,ú,ã,õ,Ã,Õ,ç,Ç,â,ê,î,ô,ü,Â,Ê,Î,Ô,Ü])+)\\b","$2.");
				palavraMeioAuxiliar = palavraMeioAuxiliar.replace("..", "").replace(" . ", " "); //Corrigindo abreviações vazias.
				palavraMeioAuxiliar = palavraMeioAuxiliar.replace("  ", " ");//Corrigindo espaços duplicados.
				resp = palavraInicio + " " + palavraMeioAuxiliar + palavraFinal;
				if(resp.length() <= size){
					return resp ;
				}else{
					resp = palavraInicio + " " + palavraFinal;
					if( resp.length() <= size)
						return resp;
					else //Truncando não foi possivel abreviar
						return resp.substring(0, size);
				}
			}else{//Não é possivel diminuir o nome completo.
				return value.substring(0, size);
			}
		}else{
			return name;
		}
	}
	
	/**
	 * Adds pad on the left side to complete the size
	 * @param	value - text to add pad
	 * @param 	size - size to completed pad
	 * @param 	pad - valur to add
	 * @return	string with pad
	 */
	public static String leftPad(	String value, 
									int size,
									String pad){
		
		if(value.length() < size && !pad.isEmpty()){
			return leftPad( pad.concat(value),  size, pad);
		}
		return value;
	}
	/**
	 * Adds pad on the right side to complete the size
	 * @param 	value - text to add pad
	 * @param 	size - size to completed pad
	 * @param 	pad - valur to add
	 * @return 	string with pad
	 */
	public static String rightPad(	String value,
									int tamanho, 
									String pad){
		if(value.length() < tamanho && !pad.isEmpty()){
			return rightPad( value.concat(pad),tamanho, pad);
		}
		return value;
	}
	
	/**
	 * Remove acentuation, stress
	 * @param 	value
	 * @return	value no acentuation.
	 */ 
	public static String removeAccentuation(final String value){  
		if(value == null) return null;
		if(value.isEmpty()) return value;
		
		String _return = value.replaceAll("[ÂÀÁÄÃ]","A");  
		_return = _return.replaceAll("[âãàáä]","a");  
		_return = _return.replaceAll("[ÊÈÉË]","E");  
		_return = _return.replaceAll("[êèéë]","e");  
		_return = _return.replaceAll("ÎÍÌÏ","I");  
		_return = _return.replaceAll("îíìï","i");  
		_return = _return.replaceAll("[ÔÕÒÓÖ]","O");  
		_return = _return.replaceAll("[ôõòóö]","o");  
		_return = _return.replaceAll("[ÛÙÚÜ]","U");  
		_return = _return.replaceAll("[ûúùü]","u");  
		_return = _return.replaceAll("Ç","C");  
		_return = _return.replaceAll("ç","c");   
		_return = _return.replaceAll("[ýÿ]","y");  
		_return = _return.replaceAll("Ý","Y");  
		_return = _return.replaceAll("ñ","n");  
		_return = _return.replaceAll("Ñ","N"); 
		return _return;  
	}  
	

	/**
	 * Parser name of a file by removing the directory and keeping the filename.
	 * Example: c:\test\file\file1.txt -> file1.txt
	 * @param path - the diretory + name of file.
	 * @return filename - only simple name file.
	 */
	public static String parseNameFile(final String path){
		//remove o diretorio mantendo apenas o nome do arquivo.
		Matcher matcher = Pattern.compile("(.*\\\\)?(.+)$").matcher(path);
		if(matcher.find() && path.contains("\\")){
			String name = matcher.group(2);
			if(name!= null ){
				return name;
			}
		}else{
			//remove o diretorio mantendo apenas o nome do arquivo.
			matcher = Pattern.compile("(.*\\/)?(.+)$").matcher(path);
			if(matcher.find()){
				String name = matcher.group(2);
				if(matcher.group(2)!= null ){
					return name;
				}
			}
		}
		return path;
	}
	/**
	 * Remove all character symbols of param1
	 * @param value - the value for a remove caracteres.
	 * @see org.opensutils.CharSymbols.LIGHT_SYMBOLS
	 * @return the new text without the characters
	 */
	public static String removeSymbols(	final String value){
		return remove(value, CharSymbols.LIGHT_SYMBOLS);
	}
	
	/**
	 * Remove all character of param1
	 * @param value - the value for a remove caracteres.
	 * @param charSymbols - the rule of characters to remove from a parameter 1
	 * @see org.opensutils.CharSymbols
	 * @return the new text without the characters
	 */
	public static String remove(	final String value,
									char... charSymbols){
		if(value == null)return null;
		if(value.trim().isEmpty())return value;
		
		String _returnValue = value;
		for(char c : charSymbols){
			_returnValue = _returnValue.replace(String.valueOf(c),  "");
		}
		
		return _returnValue;
	}
	
	
	
	/**
	 * Transforms only the first letter in UpperCase (Capitalized)
	 * @param value - value to capitalized
	 * @return the value capitalized
	 */
	public static String capitalize(final String value){
		if(value == null)return null;
		if(value.trim().isEmpty())return value;
		
		char[] arr = value.toCharArray();
		LOOP:
		for (int i= 0 ; i < arr.length;i++) {
			char c = arr[i];
			switch (c) {
				case 13: case 32: case 10:
					continue;
				default:
					arr[i] = Character.toUpperCase(c);
					break LOOP;
			}
		}
		return new String(arr);
	}

	/**
	 * Transforms all the first letters of words in UpperCase (Capitalized)
	 * @param value - value to capitalized
	 * @return the value capitalized
	 */
	public static String capitalizeAll(final String value){
		if(value == null)return null;
		if(value.trim().isEmpty())return value;
		
		StringBuilder sb = new StringBuilder(value.length());
		
		char[] arr = value.toCharArray();
		boolean flagUpper = false;
		for (int i= 0 ; i < arr.length;i++) {
			char c  = arr[i];
			switch (c) {
				case 13: case 32: case 10:
					flagUpper = true;
					sb.append(c);
				break;
				default:
					if(flagUpper){
						sb.append(Character.toUpperCase(c));
						flagUpper = false;
					}else
						sb.append(c);
				break;
			}
		}
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}
	/**
	 * This method ignores empty values returning null for this cases.
	 * <br>
	 * if(value == null || value.trim().isEmpty())
			return null;
			
	 * @param value
	 * @return value 
	 */
	public static String ignoreEmpty(final String value){
		if(value == null || value.trim().isEmpty())
			return null;
		
		return value;
	}
}
