/*
 * 	 @(#)XMLUtils.java 0.6	10/11/22
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

import java.io.File;
import java.io.InputStream;

import org.w3c.dom.Document;
/**
 * The class <code>XMLUtils</code> 
 * 
 * @version 0.6
 * @author Felipe Priuli
 */
public final class XMLUtils {
	
	private XMLUtils(){}

	/**
	 * Verifica se um xml está dentro do padrão do Schema e se este xml está formatado corretamente.
	 *
	 * @param urlSchema - URL onde se encontra.
	 * @param xmlSource - conteudo de um arquivo xml, codigo xml.
	 * @return Document - xml valido. - null para xml invalido, e uma Exception ValidaXMLWithSchemaException
	 * @throws XMLException
	 */
	public static Document validateXML(String xmlSource, String urlSchema) throws XMLException{
		XMLSchemaValidate validator =	new XMLSchemaValidate();
		return validator.validateXML(xmlSource,urlSchema);		
	}
	
	/**
	 * Verifica se um xml está dentro do padrão do Schema e se este xml está formatado corretamente.
	 *
	 * @param urlSchema  - URLonde se encontra schema.
	 * @param xmlFile - Um arquivo do tipo java.io.File que tenha em seu conteudo um xml.
	 * @return Document - xml valido. - null para xml invalido, e uma Exception ValidaXMLWithSchemaException
	 * @throws XMLException
	 */
	public static Document validateXML(File xmlFile, String urlSchema ) throws XMLException{
		XMLSchemaValidate validator =	new XMLSchemaValidate();
		return validator.validateXML(xmlFile , urlSchema);		
	}
	
	/**
	 * Verifica se um xml está dentro do padrão do Schema e se este xml está formatado corretamente.
	 *
	 * @param urlSchema  - URLonde se encontra schema.
	 * @param xmlFile - Um arquivo do tipo java.io.File que tenha em seu conteudo um xml.
	 * @return Document - xml valido. - null para xml invalido, e uma Exception ValidaXMLWithSchemaException
	 * @throws XMLException
	 */
	public static Document validateXML(InputStream inputStream, String urlSchema ) throws XMLException{
		XMLSchemaValidate validator =	new XMLSchemaValidate();
		return validator.validateXML(inputStream , urlSchema);		
	}
	
	/**
	 * Get the new instance of XMLWriter
	 * @param tagElement
	 * @return new XMLWriter
	 */
	public static XMLWriter buildXMLWriter(String tagElement){
		return new XMLWriter(tagElement);
	}
}
