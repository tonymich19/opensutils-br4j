/*
 * 	 @(#)XMLElementError.java	10/11/22
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


package org.opensutils.xml;

import org.xml.sax.SAXParseException;

/**
 * <code>ElementError</code> is a class to represent an error that occurred during validation of an xml
 * @author Felipe
 *
 */
public class XMLElementError {
	private int row;
	private int column;
	private String error;
	private SAXParseException origin;

	public XMLElementError( int row,
			int column, 
			String error){
		this.row = row;
		this.column = column;
		this.error = error;
	}
			
	public XMLElementError( SAXParseException origin) {
		this(origin.getLineNumber() , origin.getColumnNumber(),origin.getMessage());
		this.origin = origin;
	}
	
	/**
	 * Get the line number that is in error
	 * @return
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Set the line number that is in error
	 * @return
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * The message of error
	 */
	public String getError() {
		return error;
	}
	/**
	 * The message of error
	 */
	public void setError(String error) {
		this.error = error;
	}	
	public String toString(){
		return row + " - " + error;
	}
	/**
	 * Get the column number that is in error
	 * @return number column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * Set the column number that is in error
	 * @return
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	/**
	 * @return the origin
	 */
	public SAXParseException getOrigin() {
		return origin;
	}
	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(SAXParseException origin) {
		this.origin = origin;
	}
	
}
