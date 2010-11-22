/*
 * 	 @(#)FTP.java	0.2 10/11/22
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

package org.opensutils.io.ftp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Interface for methods used to connect to an ftp.
 * @author Felipe Priuli
 * @version 0.2
 */
public interface FTP {
	/**
	 * The file type used to copy files in ftp.
	 * @author Felipe Priuli
	 * @vesion 0.1
	 */
	public static enum FILE_TYPE{
		BINARY ,
		ASCII
	}
	
	/**
	 * Set the type of file for save in the ftp-host
	 * @see 	FTP.FILE_TYPE.BINARY
	 * @see 	FTP.FILE_TYPE.ASCII
	 * @param 	fileType
	 */
	void setFileType(FILE_TYPE fileType);

	/**
	 * Return the type of used for saves file in ftp-host<br>
	  *@see 	FTP.FILE_TYPE.BINARY
	 * @see 	FTP.FILE_TYPE.ASCII
	 * @return	the type used for copy file in ftp
	 */
	FILE_TYPE getFileType();

	/**
	 * Number of attempts if there is an error when copying a file to the ftp host
	 * @return 	int, max count of number of attempts<br>
	 */
	int getAttemptsCount();

	/**
	 * Set number of attempts if there is an error when copying a file to the ftp host.<br> Defalut is 2 <br>
	 * @param 	attemptsCount - int, count of attemptsCount<br>
	 */
	void setAttemptsCount(int quantidadeTentativas);

	/**
	 * Get the file from ftp, get files from directory root.
	 * @see 	org.opensutils.io.ftp.FTPApacheCommonsNetImpl.getFile(String, String ) 
	 * @param 	fileName - file name to get from ftp<br>
	 * @return 	the file or null if not exist
	 * @throws 	IOException
	 */
	File getFile(String fileName) throws IOException;

	/**
	 * Get the file from ftp, get files from directory root. 
	 * @param 	dirHost - directory where the file is<br>
	 * @param 	fileName - file name to get from ftp<br>
	 * @return 	the file or null if not exist
	 * @throws 	IOException
	 */
	File getFile(String dirHost, String fileName) throws IOException;

	/**
	 * Copy a file to FTP.
	 * @see 	org.opensutils.io.ftp.FTP.FTP.FILE_TYPE
	 * @param 	fileName - Absolute path name to find and copy file to ftp.
	 * @param 	dirHost - host diretory to copy a file 
	 * @return 	true - if the copy was successful. <br>false - if unable to copy the file for some problem
	 * @throws 	IOException
	 */
	boolean copyFileToHost(String fileName, String dirHost)	throws IOException;

	/**
	 * Copy a file to FTP.
	 * @see 	org.opensutils.io.ftp.FTP.FTP.FILE_TYPE
	 * @param 	file - file to copy in ftp
	 * @param 	fileName - file name used to save file in ftp
	 * @param 	dirHost - host diretory to copy a file 
	 * @return 	true - if the copy was successful. <br>false - if unable to copy the file for some problem
	 * @throws 	IOException
	 */
	boolean copyFileToHost(File file, String fileName, String dirHost) throws IOException;

	/**
	 * Copy a file to FTP.
	 * @see 	org.opensutils.io.ftp.FTP.FTP.FILE_TYPE
	 * @param 	file - file to copy in ftp
	 * @param 	dirHost - host diretory to copy a file 
	 * @return 	true - if the copy was successful. <br>false - if unable to copy the file for some problem
	 * @throws 	IOException
	 */
	boolean copyFileToHost(File file, String dirHost) throws IOException;

	/**
	 * Copy a file to FTP.
	 * @see 	org.opensutils.io.ftp.FTP.FTP.FILE_TYPE
	 * @param 	inputStream - file to copy in ftp
	 * @param 	fileName - file name used to save file in ftp
	 * @param 	dirHost - host diretory to copy a file 
	 * @return 	true - if the copy was successful. <br>false - if unable to copy the file for some problem
	 * @throws 	IOException
	 */
	boolean copyFileToHost(InputStream inputStream, String fileName,String dirHost) throws IOException;

	/**
	 * Deletes a file from ftp.
	 * @param dirHost - host diretory to delete file 
	 * @param fileName - file name used to deleted file from ftp
	 * @return true - if the delete was successful. <br>false - if unable to delete the file for some problem
	 * @throws IOException
	 */
	boolean deleteFile(String dirHost, String fileName) throws IOException;

	/**
	 * Create a new directory in ftp.
	 * @param	newDir - name of directory
	 * @return 	true - directory as been created<br>
	 * 			false - for a problem
	 * @throws 	IOException
	 */
	boolean createDirectory(String newDir) throws IOException;

	/**
	 * Delete a exist directory from ftp.
	 * @param	dir - name of directory to exclude
	 * @return 	true - directory as been deleted<br>
	 * 			false - for a problem, not deleted
	 * @throws 	IOException
	 */
	boolean deleteDirectory(String dir) throws IOException;
	
	/**
	 * List the files in a directory
	 * @param 	dir - name of directory
	 * @return 	the names of files in the directory
	 * @throws 	IOException
	 */
	String[] listFilesFromHost(String dir) throws IOException;


}
