/*
 * 	 @(#)UnknownConverterException.java	0.1 2010/11/30
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
 * <code>UnknownConverterException</code>
 * @author Felipe Priuli
 * @version 0.1 14/12/2010
 *
 */
public class UnknownConverterException extends ConverterException {

	private static final long serialVersionUID = 1L;

	public UnknownConverterException(){
		super("Unknown converter");
	}
	
	public UnknownConverterException(String invalidRow) {
		super(invalidRow);
	}

	public UnknownConverterException(String row,String message) {
		super(row, message);
	}

	public UnknownConverterException(String row,Throwable cause) {
		super(row, cause);
	}
	
	public UnknownConverterException(String invalidRow, String message, Throwable cause) {
		super(invalidRow, message, cause);
	}

}
