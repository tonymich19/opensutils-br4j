/*
 * 	 @(#)XMLErrorHandler.java	10/11/22
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


package org.opensutils.xml;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class XMLErrorHandler implements ErrorHandler {

	private LinkedList <XMLElementError> listErro;

	public XMLErrorHandler(){
		listErro = new LinkedList <XMLElementError>();
	}
	
    public void error(SAXParseException sxpE) {
    	this.listErro.add( new XMLElementError(sxpE));
    }
         
    public void fatalError(SAXParseException sxpE) {
    	this.listErro.add( new XMLElementError(sxpE));
    }
         
    public void warning(SAXParseException sxpE) {
    	this.listErro.add( new XMLElementError(sxpE ));
    }

	public List<XMLElementError> getErros() {
		return this.listErro;
	}
    
}
