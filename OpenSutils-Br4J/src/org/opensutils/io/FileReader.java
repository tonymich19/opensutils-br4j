/*
 * 	 @(#)FileReader.java	0.1 2010/11/30
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
 * <code>FileReader</code>
 * Interface to represent a feature file read
 * @author Felipe Priuli
 * @version 0.1 30/11/2010
 */
public interface FileReader {
	/**
	 * Open the file set to start reading the file
	 * @throws IOException - If a problem occurs when opening the file set
	 */
	public void open() throws IOException;
	/**
	 * Closes a file that was opened by the method 'open()'
	 * @throws IOException - If a problem occurs when closing the file set
	 */
	public void close() throws IOException;
	/**
	 * Releases the resources used
	 */
	public void clean();
	/**
	 * Reads the next line of the file set.
	 * @return the next line.
	 * @throws IOException - If a problem occurs when reading the file set
	 */
	public String nextLine() throws IOException;
	/**
	 * Read all file
	 * @return the file in Text
	 * @throws IOException - If a problem occurs when reading the file set
	 */
	public StringBuilder readAll()  throws IOException;
	
	/**
	 * Set the file to read.
	 * @param fileName - the absolute file name for build File
	 */
	public void setFile(String fileName);
	/**
	 * Set the file to read.
	 * @param file - the file to set
	 */
	public void setFile(File file);
	/**
	 * Get total of read line
	 * @return total read line
	 */
	public int getCountReadLine();

}
