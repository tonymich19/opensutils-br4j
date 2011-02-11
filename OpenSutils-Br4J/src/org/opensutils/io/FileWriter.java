/*
 * 	 @(#)FileWriter.java	0.1 2011/01/17
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

import java.io.File;
import java.io.IOException;

/**
 * <code>FileWriter</code>
 * Interface to represent a feature for file writer
 * @author Felipe Priuli
 * @version 0.1 17/01/2011
 */
public interface FileWriter {

	/**
	 * Open the file set to start writer the file
	 * @throws IOException - If a problem occurs when opening the file set
	 */
	public void open() throws IOException;
	/**
	 * Closes a file that was opened by the method 'open()'
	 * @throws IOException - If a problem occurs when closing the file set
	 */
	public void close() throws IOException;
	public void flush() throws IOException;
	/**
	 * Checks if a FileWriter is open to use.
	 * @return true - if open, false - to not open
	 */
	public boolean isOpen();
	
	/**
	 * Set the file to writer.
	 * @param fileName - the absolute file name for build File
	 */
	public void setFile(String fileName);
	/**
	 * Set the file to read.
	 * @param file - the file to set
	 */
	public void setFile(File file);
	
	/**
	 * Get the file.
	 * @return file
	 */
	public File getFile();
	/**
	 * Writer the String value in File, adding a line break in the file
	 * @param data - String value to writer in the file
	 * @throws IOException - If a problem occurs when writing the file set
	 */
	public void writeLine(String data) throws IOException;
	/**
	 * Writer the String value in File
	 * @param data - String value to writer in the file
	 * @throws IOException - If a problem occurs when writing the file set
	 */
	public void write(String data) throws IOException;	
	
	/**
	 * Adds a line break in the file
	 * @throws IOException - If a problem occurs when writing the file set
	 */
	public void breakLine() throws IOException;	


	
}
