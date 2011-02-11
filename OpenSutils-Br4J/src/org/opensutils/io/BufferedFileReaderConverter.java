/*
 * 	 @(#)BufferedFileReaderConverter.java	0.1 2010/12/01
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

import java.io.IOException;

import org.opensutils.converter.ConverterException;
import org.opensutils.converter.LineConverter;
/**
 * Class responsible for making a file read using java.io.BufferedReader
 * @author Felipe Priuli
 * @version 0.1 2010/12/01
 */
public class BufferedFileReaderConverter extends BufferedFileReader implements org.opensutils.io.FileReaderConverter {

	protected LineConverter converter;

	@Override
	public String[] nextLineInArray() throws IOException, ConverterException {
		String row = super.nextLine() ;
		
		if( row != null )
			return this.converter.convertToArray(row);
		
		return null;
	}

	@Override
	public Object nextLineInObject() throws IOException,ConverterException {
		String row = super.nextLine() ;
		
		if( row != null )
			return converter != null ? this.converter.convertToObject(row) : row;
		
		return null;
	}


	@Override
	public LineConverter getLineConverter() {
		return converter;
	}

	@Override
	public void setLineConverter(LineConverter converter) {
		this.converter = converter;
		
	}

}
