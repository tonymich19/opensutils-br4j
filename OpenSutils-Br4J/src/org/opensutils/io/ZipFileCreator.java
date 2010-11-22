/*
 * 	 @(#)ZipFileCreator.java	0.9 10/11/22
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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <code>ZipFileCreator</code>
 * Class to compress files in zip format.
 * @author Felipe Priuli
 * @version 0.9
 */
public class ZipFileCreator {
	private FileOutputStream out;
	private ZipOutputStream zipOut;  
	private BufferedInputStream bufferIn;
	private ZipEntry zipEntry;
	private String absoluteDirArquivo;
	
	/**
	 * Constructor
	 * @param absoluteDirArquivo - java.lang.String, absolute directory name to out files
	 * @throws FileNotFoundException
	 */
	public ZipFileCreator(String absoluteDirArquivo) throws FileNotFoundException{
		this.absoluteDirArquivo = absoluteDirArquivo;
		this.out = new FileOutputStream(absoluteDirArquivo);
		this.zipOut = new ZipOutputStream(new BufferedOutputStream(this.out));
		this.zipEntry = null;
		this.bufferIn = null;
	}
	
	/**
	 * Add a file to compress to zip.
	 * @param in - FileInputStream
	 * @param fileName - java.lang.String
	 * @throws IOException
	 */
	public void addFile(FileInputStream in, String fileName) throws IOException{
		if( this.out != null && zipOut != null && in != null && fileName!=null){
			this.bufferIn = new BufferedInputStream(in);
			this.zipEntry = new ZipEntry(fileName);
			this.zipOut.putNextEntry(this.zipEntry);
			
			int i;
			while((i = this.bufferIn.read()) != -1) {   
				this.zipOut.write(i);   
			 }   
			in.close();
			this.bufferIn.close();
			this.bufferIn = null;
			this.zipEntry = null;
		}
	}
	
	/**
	 * Compress files to zip
	 * @return the file-zip
	 * @throws IOException 
	 */
	public File compress() throws IOException{
		File file = null;
		if( this.out != null && zipOut != null){
			zipOut.flush();
			file = new File(absoluteDirArquivo);
		}	
		return file;
	}
	
	/**
	 * Close all files that were manipulated to compression.
	 * @throws IOException 
	 */
	public void close() throws IOException{
		this.zipOut.finish();
		this.zipOut.close();
		this.out.close();
	}
	
	
}
