/*
 * 	 @(#)SimpleFileWriter.java	0.1 2011/01/17
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
import java.io.FileWriter;
import java.io.IOException;
/**
 * Class responsible for writer data in file.
 * @author Felipe Priuli
 * @version 0.1 2011/01/17
 */
public class SimpleFileWriter implements org.opensutils.io.FileWriter {

	private File file;
	private FileWriter fileWriter; 
	private boolean open;
	private boolean flushAll;

	public SimpleFileWriter(){
		this(false);
	}

	public SimpleFileWriter(boolean flushAll){
		this.clean();
		this.flushAll = flushAll;
	}
	
	public SimpleFileWriter(String fileName){	
		this(fileName,false);
	}
	
	public SimpleFileWriter(String fileName, boolean flushAll){	
		this.clean();
		this.file = new File(fileName);
		this.flushAll = false;
	}
	
	public SimpleFileWriter(File file){
		this(file, false);
	}
	
	public SimpleFileWriter(File file, boolean flushAll){
		this.clean();
		this.file = file;
		this.flushAll = flushAll;
	}
	/**
	 * Limpa os recursos utilizados.
	 */
	protected void clean(){
		this.fileWriter = null;
		this.open = false;
	}
	
	public void setFile(String fileName){
		if(fileName!= null)
			this.file = new File(fileName); 
	}

	
	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * Verifica se o arquivo esta aberto para escrita.
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * Escreve um texto no arquivo.
	 * @param data - conteudo para escrever no arquivo.
	 * @throws IOException - Se ocorrer algum erro ao escrever no arquivo.
	 */
	public void write(String data) throws IOException {
		if(file != null && open){			
			if(data != null)
				fileWriter.write( data );
		}
	}

	/**
	 * Escreve um texto no arquivo e faz uma quebra de linha.
	 * @param data - conteudo para escrever no arquivo.
	 * @throws IOException - Se ocorrer algum erro ao escrever no arquivo.
	 */
	public void writeLine(String data)throws IOException {
		this.write(data);
		this.breakLine();	
	}

	/**
	 * Adiciona uma quebra de linha no arquivo.
	 */
	public void breakLine() throws IOException{
		this.fileWriter.write( System.getProperty("line.separator") );
	}

	/**
	 * Finaliza os recursos utilizados. Fecha todas as dependencias.
	 * @throws IOException - Se ocorrer algum erro ao escrever no arquivo.
	 */
	public void close() throws IOException{
		if( open == true){
				fileWriter.flush();
				fileWriter.close();
				this.clean();
		}
	}

	/**
	 * Inicia o processo para escrita no arquivo, aloca os recursos necessarios.
	 * Deve ser utilizado este métodos antes do método writer.
	 * @throws IOException - Se ocorrer algum erro ao escrever no arquivo.
	 */
	public void open() throws IOException {
		if(file != null){
			fileWriter = new FileWriter(file);
			open = true;
		}
	}

	/**
	 * Get the file
	 * @return file
	 */
	public File getFile() {
		return file;
	}

	public boolean isFlushAll() {
		return flushAll;
	}

	public void setFlushAll(boolean flushAll) {
		this.flushAll = flushAll;
	}

	/**
	 * Força a liberação de memoria e gravação no arquivo.
	 */
	public void flush() throws IOException {
		if(isOpen()){
			fileWriter.flush();
		}
	}
	
}
