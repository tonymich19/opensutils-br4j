/*
 * 	 @(#)FileCompression.java	0.1 2011/01/07
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
import java.io.FileInputStream;
import java.io.IOException;

public interface FileCompression {

	/**
	 * Adds a file to be compressed.
	 * @param in - FileInputStream
	 * @param fileName - Name of file
	 * @throws IOException - If a problem I/O occurs
	 */
	public void addFile(FileInputStream in, String fileName) throws IOException;
	
	/**
	 * Adds a directory to be compressed.
	 * @param dir -the directory
	 * @throws IOException - If a problem I/O occurs
	 */
	public void addDirectory(File dir) throws IOException;
	
	/**
	 * Compress all files that have been added
	 * @return the file compressed
	 * @throws IOException - If a problem I/O occurs
	 */
	public File compress() throws IOException;
	
	/**
	 * Close compression.
	 * @throws IOException - If a problem I/O occurs
	 */
	public void close() throws IOException;
	
	/**
	 * Open compression.
	 * @throws IOException - If a problem I/O occurs
	 */
	public void open() throws IOException;
}
