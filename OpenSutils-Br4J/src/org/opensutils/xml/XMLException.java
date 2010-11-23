/*
 * 	 @(#)XMLException.java	10/11/22
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

import java.util.List;

public class XMLException extends Exception {

	private List<XMLElementError> listErro;
	private static final long serialVersionUID = 1153745384550128972L;

	public XMLException(String message, List<XMLElementError> listErro) {
		this(message);
		this.listErro = listErro;
	}

	public XMLException(String message) {
		super(message);
	}
	
	public XMLException(Throwable source) {
		super(source);
	}

	public XMLException(String message, Throwable source) {
		super(message, source);
	}

	public List<XMLElementError> getListErro() {
		return listErro;
	}

	public void setListErro(List<XMLElementError> listErro) {
		this.listErro = listErro;
	}
		
}
