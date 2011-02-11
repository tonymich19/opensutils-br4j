/*
 * 	 @(#)IOUtils.java	0.1 2011/01/18
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

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opensutils.StringUtils;
/**
 * The class <code>IOUtils</code> is utility for working with called I/O.
 * @author Felipe Priuli
 * @version 0.1 2011/01/18
 */
public final class IOUtils {
	
	 /**
     * The default line separator string.
     */
	 public static final String LINE_SEPARATOR = System.getProperty("line.separator"); 
    /**
     * The Unix line separator string.
     */
    public static final String LINE_SEPARATOR_UNIX = "\n";
    /**
     * The Windows line separator string.
     */
    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";
    /**
     * The system directory separator character.
     */
    public static final String DIR_SEPARATOR = File.separator;
    /**
     * The Unix directory separator character.
     */
    public static final String DIR_SEPARATOR_UNIX = "/";
    /**
     * The Windows directory separator character.
     */
    public static final String DIR_SEPARATOR_WINDOWS = "\\";
	 
	
	/**
	 * Invoke the close method of the class ignoring exceptions that may occur
	 * @param r - All classes parent of java.io.Closeable
	 */
	public static void closeIgnore(Closeable c){
		try{
			c.close();
		}catch(Throwable t){
			//IGNORING
		}
	}
	/**
	 * Invoke the close method of the class ignoring exceptions that may occur
	 * @param s - All classes parent of java.io.Writer
	 */
	public static void closeIgnore(Socket s){
		try{
			s.close();
		}catch(Throwable t){
			//IGNORING
		}
	}

	 /**
     * Copy a data from InputStream to a OutputStream.
     * @param inputStream  the inputStream to read
     * @param outputStream  the outputStream to write to
     * @param bufferSize -  the buffer size to use for copy Stream
     * @return number of characters copied
     * @throws IOException - If a problem I/O occurs during the copy.
     */
	public static long copy(	InputStream inputStream, 
								OutputStream outputStream,
								int bufferSize
							) throws IOException {
		
		if(bufferSize <= 0 ) bufferSize = 1024;

		byte[] buffer = new byte[bufferSize];
		long count = 0;
		int n = 0;
		while (-1 != (n = inputStream.read(buffer))) {
			outputStream.write(buffer, 0, n);
			count += n;
		}
		
		return count;
	}
	
	 /**
     * Copy a data from Reader to a Writer.
     * @param input  the input to read
     * @param output  the output to write to
     * @param bufferSize -  the buffer size to use for copy Stream
     * @return number of characters copied
     * @throws IOException - If a problem I/O occurs during the copy.
     */
	 public static long copy(	Reader input,
								Writer output,
								int bufferSize
							  ) throws IOException {

		 if(bufferSize <= 0 ) bufferSize = 1024;

		 char[] buffer = new char[bufferSize];
		 long count = 0;
		 int n = 0;
		 while (-1 != (n = input.read(buffer))) {
			 output.write(buffer, 0, n);
			 count += n;
		 }
		 return count;
	 }
	
	 /**
     * Get the contents of an InputStream as a String.
     * @param InputStream -  the InputStream to read from
     * @param bufferSize -  the buffer size to use for copy Stream
     * @return the contents as a String
     * @throws IOException - If a problem I/O occurs during the copy.
     */
    public static String toString(	InputStream InputStream,
    								int bufferSize
    							  ) throws IOException {
    	
    	StringWriter sw = new StringWriter();
    	copy( new InputStreamReader(InputStream),sw, bufferSize);
        return sw.toString();
    }
    /**
     * Get the contents of an Reader as a String.
     * @param input -  the Reader to read from
     * @param bufferSize -  the buffer size to use for copy Stream
     * @return the contents as a String
     * @throws IOException - If a problem I/O occurs during the copy.
     */
    public static String toString(	Reader input,
    								int bufferSize
    							  ) throws IOException {
    	
    	StringWriter sw = new StringWriter();
        copy(input, sw, bufferSize);
        return sw.toString();
    }
    /**
	 * Parser name of a file by removing the directory and keeping the filename.
	 * <pre>
	 * 		c:\test\file\file1.txt -> file1.txt
	 * </pre>
	 * @param path - the diretory + name of file.
	 * @return filename - only simple name file.
	 */
    public static String getNameFile(final String path){
    	return StringUtils.parseNameFile(path);
    }
    /**
     * Get the name of extension of file
     * <pre>
     * 			file1.txt    		   --> "txt"
     * 			c:\teste\priuli.jpeg   --> "jpeg"
     * </pre>
     * @param filename the full file name
     * @return extension name 
     */
    public static String getExtension(final String filename){
    	if(filename == null){ return filename;}
    	Matcher matcher = Pattern.compile("^.*\\.(.+)$").matcher(filename);
		if(matcher.find()){
			return matcher.group(1);
		}
		return null;	
    }
    /**
     * Remove the extension file name
     * <pre>
     * 			file1.txt    		   --> "file1"
     * 			c:\teste\priuli.jpeg   --> "c:\teste\priuli"
     * </pre>
     * @param filename the full file name
     * @return extension name 
     */
    public static String removeExtension(final String filename){
    	if(filename == null){ return filename;}
    	Matcher matcher = Pattern.compile("(^.*)\\.(.+)$").matcher(filename);
		if(matcher.find()){
			return matcher.group(1);
		}else{
			return filename;
		}
    }
}
