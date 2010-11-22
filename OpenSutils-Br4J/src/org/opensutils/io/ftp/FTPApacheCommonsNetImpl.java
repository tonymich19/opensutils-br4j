/*
 * 	 @(#)FTPApacheCommonsNetImpl.java	0.4 10/11/22
 * 
 *	Copyright (c) 2010 Felipe Priuli
 *
 *	This file is part of OpenSutils-Br4J and uses libraries distributed by Apache.
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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


/**
 * Class <code>FTPApacheCommonsNetImpl</code>
 * Uses the commons lib-apache-FtpClient for communicating with an FTP.
 * This class is façaide to facilitate the use of the commons - ftp.
 * Not Thread-Safe
 *
 * @author Felipe Priuli
 * @version 1.4
 */
public class FTPApacheCommonsNetImpl implements FTP {

	private FTPClient ftp;
	private FTP.FILE_TYPE fileType;
	private String hostName;
	private String user;
	private String password;
	private int attemptsCount ;

	/**
	 * Construtor<br>
	 * @param host - ip or name the ftp-host for been connect
	 * @param user - name user por login to ftp
	 * @param password - password for login in ftp
	 * @author Felipe Priuli
	 */
	public FTPApacheCommonsNetImpl(String host, String user, String password ){
		this.ftp = new FTPClient();
		this.fileType = FTP.FILE_TYPE.BINARY;
		this.hostName = host;
		this.user = user;
		this.password = password;
		this.attemptsCount = 2;

	}
	
	@Override
	/**
	 * Set the Type of file for save in the ftp-host<br>
	 * Default FTP.FILE_TYPE.ASCII<br>
	  *@see 	FTP.FILE_TYPE.BINARY
	 * @see 	FTP.FILE_TYPE.ASCII
	 * @param 	typeFile
	 */
	public void setFileType(FILE_TYPE fileType){
		this.fileType = fileType;
	}
	
	@Override
	/**
	 * Return the type of used for saves file in ftp-host<br>
	  *@see 	FTP.FILE_TYPE.BINARY
	 * @see 	FTP.FILE_TYPE.ASCII
	 * @return	the type used for copy file in ftp
	 */
	public FILE_TYPE getFileType(){
		return fileType;
	}
	
	@Override
	/**
	 * Number of attempts if there is an error when copying a file to the ftp host
	 * @return int, max count of number of attempts<br>
	 */
	public int getAttemptsCount() {
		return attemptsCount;
	}
	
	@Override
	/**
	 * Set number of attempts if there is an error when copying a file to the ftp host.<br> Defalut is 2 <br>
	 * @param attemptsCount - int, count of attemptsCount (max attemptsCount == 7)<br>
	 */
	public void setAttemptsCount(int attemptsCount) {
		if(attemptsCount <= 0)
			attemptsCount = 1;
		else if(attemptsCount > 7)
			attemptsCount = 7;
			
		this.attemptsCount = attemptsCount;
	}

	@Override
	/**
	 * Get the file from ftp, get files from directory root.
	 * @see org.opensutils.io.ftp.FTPApacheCommonsNetImpl.getFile(String, String ) 
	 * @param fileName - file name to get from ftp<br>
	 * @return the file or null if not exist
	 * @throws IOException
	 */
	public File getFile(String fileName) throws IOException{
		return this.getFile(null, fileName);
	}
	
	@Override
	/**
	 * List the files in a directory
	 * @param 	dir - name of directory
	 * @return 	the names of files in the directory
	 * @throws 	IOException
	 */
	public String[] listFilesFromHost(String dirHost)throws IOException{
		String[] listFileNames = null;
		if( this.connectFtp()){
			try {

				if(dirHost != null)
					if(!ftp.changeWorkingDirectory(dirHost))
						throw new IOException("Directory: '"+dirHost+"' not find in ftp.");

				listFileNames = ftp.listNames();
				this.closeConectionHost();
				
			}catch(IOException ioE){
				this.closeConectionHost();
				throw new IOException(ioE);	
			}
		}else
			throw new IOException("Could not connect to the host to list files.");	
		return listFileNames;
	}

	/**
	 * Fecha a coneção com o host caso esteja conectado.
	 */
	private void closeConectionHost(){
		if(ftp.isConnected()){
			try {
				ftp.disconnect();
			} catch (IOException e) {}
		}
	}
	
	@Override
	/**
	 * Get the file from ftp, get files from directory root. 
	 * @param 	dirHost - directory where the file is<br>
	 * @param 	fileName - file name to get from ftp<br>
	 * @return 	the file or null if not exist
	 * @throws 	IOException
	 */
	public File getFile(String dirHost,
						String fileName) throws IOException{
		
		File file = null;
		if( this.connectFtp()){
			try {
				String nomeArquivoNoHost = null;
				if(dirHost != null)
					if(!ftp.changeWorkingDirectory(dirHost))
						throw new IOException("Directory: '"+dirHost+"' not find in ftp. Not possible change directory to find file.");

				String[] listFileNames = ftp.listNames();
				if( listFileNames != null){
					for(String nomeArq : listFileNames){
						if( nomeArq.equals(fileName)){
							nomeArquivoNoHost = nomeArq;
							break;
						}	
					}
				}else
					file = null;

				if(nomeArquivoNoHost != null){//Tem o arquivo no host então fazer download do mesmo.
					file = File.createTempFile(fileName, ".tmp");  
					OutputStream outStreamFile = new FileOutputStream(file); //OutputStream 
					BufferedOutputStream bufferArquivo = null;
					int countTentativas = 0;
					this.updateTypeFile();
					ftp.enterLocalActiveMode(); 
					boolean download = false;
					do{
						bufferArquivo = new BufferedOutputStream(outStreamFile);
						download =  ftp.retrieveFile(nomeArquivoNoHost, bufferArquivo);           		
						bufferArquivo.flush();	
						bufferArquivo.close();

						if(++countTentativas > attemptsCount){
							throw new IOException("Could not get the file on the ftp, ATTEMPTS LIMIT EXCEEDED");
						}
					}while(!download);
					try{
						outStreamFile.close();	   
					}catch(IOException ioEx) {
						//log.error("(getFileFromHost) Nao foi possivel fechar o arquivo: "+nomeArquivo);
					}
				}else{
					file = null;
				}
				
			}catch(IOException ioE){
				this.closeConectionHost();
				throw ioE;	
			}finally{
				this.closeConectionHost();
			}
		}
		return file;
	}
	
	@Override

	/**
	 * Copy a file to FTP.<br>
	 * @see 	br.opensutils.io.ftp.FTP.FTP.FILE_TYPE
	 * @param 	fileName - Absolute path name to find and copy file to ftp.
	 * @param 	dirHost - host diretory to copy a file 
	 * @return 	true - if the copy was successful. <br>false - if unable to copy the file for some problem
	 * @throws 	IOException
	 */
	public boolean copyFileToHost(	String pathFile,
									String dirHost) throws IOException{
		
		return this.copyFileToHost( new File(pathFile), dirHost);
	}
	
	@Override
	/**
	 * Copy a file to FTP.<br>
	 * @see 	br.opensutils.io.ftp.FTP.FTP.FILE_TYPE
	 * @param 	file - file to copy in ftp
	 * @param 	fileName - file name used to save file in ftp
	 * @param 	dirHost - host diretory to copy a file 
	 * @return 	true - if the copy was successful. <br>false - if unable to copy the file for some problem
	 * @throws 	IOException
	 */
	public boolean copyFileToHost(	File file,
									String fileName, 
									String dirHost) throws IOException{
		
		boolean resp = false;
		boolean erroEnvio = true;
		int tentativas = 1;
		do {
			
			try{
				if( this.connectFtp()){
					this.updateTypeFile();
	
					if(dirHost != null)
						if(!ftp.changeWorkingDirectory(dirHost))
							throw new IOException("Directory: "+dirHost+"  not find in ftp.");
					
					InputStream inputStream = new FileInputStream(file);
					if(ftp.storeFile( fileName , inputStream )){//enviando o arquivo
						resp = true;
						erroEnvio = false;
					}
					inputStream.close();				
				}
			}catch(IOException ioE){
				
				this.closeConectionHost();
				if( tentativas >= attemptsCount){
					erroEnvio = false;
					throw new IOException(ioE);
				}
				tentativas++;
				erroEnvio = true;
			}finally{
				this.closeConectionHost();
			}
		} while (erroEnvio);
		return resp;
	}
	
	@Override
	/**
	 * Copy a file to FTP.
	 * @see 	br.opensutils.io.ftp.FTP.FTP.FILE_TYPE
	 * *@param 	file - file to copy in ftp
	 * @param 	dirHost - host diretory to copy a file 
	 * @return 	true - if the copy was successful. <br>false - if unable to copy the file for some problem
	 * @throws 	IOException
	 */
	public boolean copyFileToHost(File file,String dirHost) throws IOException{
		return this.copyFileToHost(file, file.getName(),dirHost);
	}

	@Override
	/**
	 * Copy a file to FTP.
	 * @see 	br.opensutils.io.ftp.FTP.FTP.FILE_TYPE
	 * @param 	inputStream - file to copy in ftp
	 * @param 	fileName - file name used to save file in ftp
	 * @param 	dirHost - host diretory to copy a file 
	 * @return 	true - if the copy was successful. <br>false - if unable to copy the file for some problem
	 * @throws 	IOException
	 */
	public boolean copyFileToHost(InputStream inputStream, String fileName, String dirHost) throws IOException{
		boolean resp = false;
		boolean erroEnvio = true;
		int tentativas = 1;
		do {
			try {
				if( this.connectFtp()){	//verificando se conecta com o host

					this.updateTypeFile();

					if(dirHost != null)
						if(ftp.changeWorkingDirectory(dirHost)){
							if(ftp.storeFile( fileName , inputStream )){//enviando o arquivo
								resp = true;
								erroEnvio = false;
							}
						}else
							throw new IOException("Directory: "+dirHost+"  not find in ftp.");
					inputStream.close();

				}
			} catch (IOException ioE) {
				this.closeConectionHost();
				if( tentativas >= attemptsCount){
					throw new IOException(ioE);
				}
				tentativas++;
				erroEnvio = true;
			}finally{
				this.closeConectionHost();
			}
		} while (erroEnvio);
		return resp;
	}

	private void updateTypeFile() throws IOException{
		try{
			switch(this.fileType){
				case BINARY:
					ftp.setFileType( FTPClient.BINARY_FILE_TYPE );
				break;	
				default:
					ftp.setFileType( FTPClient.ASCII_FILE_TYPE );
				break;	
			}
		}catch(IOException ioE){
			throw new IOException(ioE);	
		}
	}
	
	@Override
	/**
	 * Deletes a file from ftp.
	 * @param dirHost - host diretory to delete file 
	 * @param fileName - file name used to deleted file from ftp
	 * @return true - if the delete was successful. <br>false - if unable to delete the file for some problem
	 * @throws IOException
	 */
	public boolean deleteFile(String dirHost, String fileName )throws IOException{
		boolean resp = false;
		try {
			if(this.connectFtp()){
				if(dirHost != null)
					if(!ftp.changeWorkingDirectory(dirHost))
						throw new IOException("Directory: "+dirHost+"  not find in ftp. Cannot find directory");
				
				resp = ftp.deleteFile(fileName);
			}
		}catch(IOException ioE){
			this.closeConectionHost();
			throw new IOException(ioE);	
		}finally{	
			this.closeConectionHost();
		}
		return resp;
	}
		
	/**
	 * Conecta no ftp e faz o login no mesmo.
	 * @return true - se conectado false - se não conectado
	 * @throws IOException
	 */
	private boolean connectFtp() throws IOException{
		try {
			ftp.connect(hostName);
			if( FTPReply.isPositiveCompletion(ftp.getReplyCode())){	//verificando se conecta com o host
				if(ftp.login( user , password )){
					return true;
				}else{
					this.closeConectionHost();
					throw new IOException("Connection Refused, check user and password.");
				}
			}else {//não é possivel conectar. Conexão recusada
				this.closeConectionHost();
				throw new IOException("Could not connect to the host: \""+hostName+"\"");
			}
		}catch(SocketException sE){
			this.closeConectionHost();
			//Conexão reset, tentando novamente.
			try{
				ftp.connect(hostName);
				if( FTPReply.isPositiveCompletion(ftp.getReplyCode())){	//verificando se conecta com o host
					if(ftp.login( user , password )){
						return true;
					}
				}
				throw new IOException(sE);
			}catch(Exception e){
				throw new IOException(sE);
			}
		}catch(IOException ioE){
			this.closeConectionHost();
			throw new IOException(ioE);	
		}
	}
	
	@Override
	/**
	 * Create a new directory in ftp-host.
	 * @param	 newDir - name of directory 
	 * @return 	true - directory as been created
	 * 			false - for a problem
	 * @throws 	IOException
	 */
	public boolean createDirectory(String newDir) throws IOException{
		boolean resp = false;
		try{
			if( this.connectFtp() ){				
				if(ftp.makeDirectory(newDir)){
					resp = true;
				}else{
					resp = false;
				}			

			}
		}catch (IOException ioE) {
			this.closeConectionHost();
			throw new IOException(ioE);	
		}finally{
			this.closeConectionHost();
		}
		return resp;		
	}
	
	@Override
	/**
	 * Delete a exist directory from ftp.
	 * @param	dir - name of directory to exclude
	 * @return 	true - directory as been deleted<br>
	 * 			false - for a problem, not deleted
	 * @throws 	IOException
	 */
	public boolean deleteDirectory(String dir)throws IOException{
		boolean resp = false;
		try{
			if( this.connectFtp() ){		
				if(ftp.removeDirectory(dir)){
					resp = true;
				}else{
					resp = false;
				}		
			}
		}catch (IOException ioE) {
			this.closeConectionHost();
			throw new IOException(ioE);	
		}finally{
			this.closeConectionHost();
		}
		return resp;
	}

}
