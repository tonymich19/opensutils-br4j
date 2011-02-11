/*
 * 	 @(#)FileReaderConverter.java	0.1 2010/11/30
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
 * <code>FileReaderConverter</code>
 * Interface to represent the reading of a file and format the fields of a file through a layout
 * @author Felipe Priuli
 * @version 0.1 30/11/2010
 */
public interface FileReaderConverter extends FileReader {
	/**
	 * Reads the next line of the file set. 
	 * @return array of data formated by LayoutReader.
	 * @throws IOException - If a problem occurs when reading the file set
	 * @throws ConverterException - Is there any problem to convert the line read in accordance with the rule of the layout used
	 */
	public String [] nextLineInArray() throws IOException, ConverterException;
	
	/**
	 * Reads the next line of the file set and convert to <T> (Object)
	 * @return the object
	 * @throws IOException - If a problem occurs when reading the file set
	 * @throws ConverterException - Is there any problem to convert the line read in accordance with the rule of the layout used
	 */
	public Object nextLineInObject() throws IOException, ConverterException;
	
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
