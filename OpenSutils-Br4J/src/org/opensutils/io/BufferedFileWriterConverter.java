/*
 * 	 @(#)BufferedFileWriterConverter.java	0.1 2011/01/17
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

import org.opensutils.converter.ConverterException;
import org.opensutils.converter.LineConverter;
import org.opensutils.converter.UnknownConverterException;
/**
 * Class used to translate java objects to a String, for writing files using java.io.BufferedWriter
 * @author Felipe Priuli
 * @version 0.1 2011/01/17
 */
public class BufferedFileWriterConverter extends BufferedFileWriter implements org.opensutils.io.FileWriterConverter {

	protected LineConverter converter;

	public BufferedFileWriterConverter(File file, LineConverter converter){
		super.setFile(file);
		this.converter = converter;
	}
	
	@Override
	public void writer(Object data) throws IOException,ConverterException {
		if(converter != null){
			super.writeLine(this.converter.convertToLine(data));	
		}else{
			throw new UnknownConverterException();
		}
	}

	@Override
	public LineConverter getLineConverter() {
		return converter;
	}

	@Override
	public void setLineConverter(LineConverter converter) {
		this.converter = converter;
		
	}

	@Override
	public void writer(String[] array) throws IOException, ConverterException {
		if(converter != null){
			this.converter.convertToLine(array);	
		}else{
			throw new UnknownConverterException();
		}
	}
	
		
}