/*
 * 	 @(#)ConverterException.java	0.1 2010/11/30
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
 * <code>ConverterException</code>
 * @author Felipe Priuli
 * @version 0.1 30/11/2010
 *
 */
public class ConverterException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	private String row ;
	private Object object ;

	public ConverterException(String invalidRow) {
		this.row = invalidRow;
	}

	public ConverterException(String row,String message) {
		super(message);
		this.row = row;
	}

	public ConverterException(String row,Throwable cause) {
		super(cause);
		this.row = row;
	}
	
	public ConverterException(Object obj,Throwable cause) {
		super(cause);
		this.object = obj;
	}

	public ConverterException(String invalidRow, String message, Throwable cause) {
		super(message, cause);
		this.row = invalidRow;
	}

	/**
	 * Get the row that has failed for the parser LayoutReader.
	 * @return the invalidRow
	 */
	public String getInvalidRow() {
		return row;
	}

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}
	

	
}
