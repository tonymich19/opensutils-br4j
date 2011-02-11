/*
 * 	 @(#)FileWriterConverter.java	0.1 2010/01/17
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
package org.opensutils.io;

import java.io.IOException;

import org.opensutils.converter.ConverterException;
import org.opensutils.converter.LineConverter;

/**
 * <code>FileWriterConverter</code>
 * Interface to represent the reading of a file and format the fields of a file through a layout
 * @author Felipe Priuli
 * @version 0.1 01/01/2011
 */
public interface FileWriterConverter extends FileWriter {
	
	/**
	 * Writer the object in a file.
	 * @throws IOException - If a problem occurs when reading the file set
	 * @throws ConverterException - If there any problem to convert the object into a String
	 */
	public void writer(Object object) throws IOException, ConverterException;
	
	/**
	 * Writer the array in a file.
	 * @throws IOException - If a problem occurs when reading the file set
	 * @throws ConverterException - If there any problem to convert the object into a String
	 */
	public void writer(String[] array) throws IOException, ConverterException;
	
	
	/**
	 * Set a layout to format, convert the line in fields
	 * @param converter - the layout to use to parse fields
	 */
	public void setLineConverter(LineConverter converter);
	
	/**
	 * Get the layout used to convert line in fields
	 * @return layout - the layout to use to parse fields
	 */
	public LineConverter getLineConverter();
	
}
