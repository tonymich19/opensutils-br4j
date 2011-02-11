/*
 * 	 @(#)BufferedFileReader.java	0.1 2010/12/01
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * Class responsible for making a file read using java.io.BufferedReader
 * @author Felipe Priuli
 * @version 0.1 2010/12/01
 */
public class BufferedFileReader implements org.opensutils.io.FileReader {

	protected File file;
	protected FileReader fileReader; 
	protected BufferedReader bufferReader;

	protected int linhasLidas;

	/**
	 * METODO CONSTRUTOR PADRÃO 
	 * 
	 * @param Arquivo não instanciado.
	 * @see setFile(File file) PARA INSTANCIAR UM ARQUIVO 
	 * 
	 */
	public BufferedFileReader(){
		this.clean();
	}

	/**
	 * Metodo construtor recebe um nome e instancia um arquivo.
	 *
	 *@param String Recebe um nome de arquivo.
	 */
	public BufferedFileReader(String fileName){	
		this.setFile( new File(fileName));
	}

	/**
	 * Metodo construtor receve e instancia um arquivo. 
	 * 
	 * @param file Arquivo a ser lido.
	 */
	public BufferedFileReader(File file){
		this.setFile( file );
	}

	public void clean(){
		this.file = null;
		this.fileReader = null; 
		this.bufferReader = null;
		this.linhasLidas = 0;
	}

	@Override
	/**
	 * Fecha o arquivo que foi aberto pelo metodo 'open()'.
	 * 
	 * @return <b>True</b> se fechado, <b>False</b> se não foi possível fechar
	 * ou se não tiver um arquivo.
	 * @see this.open()
	 */
	public void close() throws IOException {
		if(bufferReader != null){
			bufferReader.close();
		}
		if(fileReader != null){
			fileReader.close();
		}
		this.clean();
	}

	@Override
	/**
	 * Abre o arquivo para leitura.
	 * 
	 * @return <b>True</b> se aberto, <b>False</b> se não foi possível abrir
	 * ou se não tiver um arquivo instanciado.
	 * @see this.close()
	 */
	public void open() throws FileNotFoundException {
		if(file != null){
			fileReader = new FileReader(file);
			bufferReader = new BufferedReader(fileReader);
			linhasLidas = 0;	    
		}
	}

	@Override
	/**
	 * Retorna um vetor de String contendo os dados lidos em um arquivom, formatado de acordo com o tipo de layout fornecido.
	 * @return String[] - Vetor de String com os dados na ordem do Layout 
	 * Retorna nulo se não tiver um arquivo instanciado ou um Layout. 
	 * @throws IOException 
	 */	
	public String nextLine() throws IOException {
		if(file != null && bufferReader != null & fileReader !=  null ){
			String row = bufferReader.readLine();
			if(row!= null){
				linhasLidas++;
			}
			return row;			
		}else{
			throw new FileNotFoundException("No files were found for reading. Set a file before reading the file!");
		}
	}

	@Override
	/**
	 * Inicia a leitura do arquivo e retorna o conteudo dentro do arquivo.
	 * @return String - conteudo do arquivo, null se ocorreu algum erro. 
	 */
	public StringBuilder readAll() throws IOException{
		linhasLidas = 0;
		if(file != null && bufferReader != null & fileReader !=  null ){
			StringBuilder conteudoArquivo = new StringBuilder();
			String row = null;
			while( (row = bufferReader.readLine()) != null){
				linhasLidas++;
				conteudoArquivo.append(row);
			}
			return conteudoArquivo;

		}else{
			throw new FileNotFoundException("No files were found for reading. Set a file before reading the file!");
		}
	}

	@Override
	/**
	 *  Seta um arquivo, para ser lido.
	 */
	public void setFile(File file){
		if (this.file == null)	
			this.file = file;		
	}

	@Override
	/**
	 *  Seta um arquivo, para ser lido.
	 */
	public void setFile(String fileName) {
		if (file == null)
			file = new File(fileName);		
	}

	@Override
	public int getCountReadLine() {
		return linhasLidas;
	}

}
