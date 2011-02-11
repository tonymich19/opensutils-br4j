/*
 * 	 @(#)Converter.java	0.1 2010/12/14
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
 * Converter of Object.
 * @author Felipe Priuli
 */
public interface Converter {
	
	/**
	 * Convert the String to as Object.
	 * @param value - value to convert to a Object
	 * @return Object
	 */
	public Object getAsObject(String value) throws ConverterException;
	
	/**
	 * Convert the Object to as String.
	 * @param obj - Object to convert to a String
	 * @return String
	 */
	public String getAsString(Object obj) throws ConverterException;
}
