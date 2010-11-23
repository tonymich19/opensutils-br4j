/*
 * 	 @(#)Minimizer.java	0.2 10/11/22
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

package org.opensutils.web.javascript;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 
 * <code>Minimizer</code> interface for use to minimize a JavaScript.
 * 
 * @author Felipe Priuli
 * @version 0.2
 *
 */
public interface Minimizer {
	
	/**
	 * Pinching a JavaScript file, removing spaces, comments and changing names of variables in
	 * order to decrease the size of a JavaScript file
	 * @param in - The JavaScript File 
	 * @param out - The out of JavaScript minimized
	 * @throws IOException - If a problem to minimize the file.
	 */
	public void compress(InputStream in, OutputStream out) throws IOException;
	
	/**
	 * Pinching a JavaScript file, removing spaces, comments and changing names of variables in
	 * order to decrease the size of a JavaScript file
	 * @param in - The JavaScript File 
	 * @param out - The new JavaScript minimized File
	 * @throws IOException - If a problem to minimize the file.
	 */
	public void compress(File in, File out) throws IOException;
	
}
