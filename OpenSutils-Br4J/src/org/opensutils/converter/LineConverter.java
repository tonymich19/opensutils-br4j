/*
 * 	 @(#)LineConverter.java	0.1 2010/11/30
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
package org.opensutils.converter;

/**
 * Conversor of String line to a Object 
 * @author Felipe Priuli
 */
public interface LineConverter {
	
	/**
	 * Convert a line to as String array
	 * @param line - Row to convert into a String[]
	 * @return String[] - array
	 * @throws ConverterException - If a error to convert or parse fields to array
	 * @throws UnknownConverterException - If not identify the Converter to use.
	 */
	public String[] convertToArray(String line) throws ConverterException, UnknownConverterException;

	/**
	 * Convert a line to as Object
	 * @param line - Row to convert into a Object[]
	 * @return Object
	 * @throws ConverterException - If a error to convert or parse fields to array
	 * @throws UnknownConverterException - If not identify the Converter to use.
	 */
	public Object convertToObject(String line) throws ConverterException, UnknownConverterException;
	
	/**
	 * Convert a Object to as line
	 * @param Object - object to convert into string line
	 * @return line - String
	 * @throws ConverterException - If a error to convert or parse fields to array
	 * @throws UnknownConverterException - If not identify the Converter to use.
	 */
	public String convertToLine(Object obj) throws ConverterException, UnknownConverterException;
	
	/**
	 * Convert a Object to as line
	 * @param String[] - Array to convert into string line
	 * @return line - String
	 * @throws ConverterException - If a error to convert or parse fields to array
	 * @throws UnknownConverterException - If not identify the Converter to use.
	 */
	public String convertToLine(String[] arr) throws ConverterException, UnknownConverterException;
	
}
