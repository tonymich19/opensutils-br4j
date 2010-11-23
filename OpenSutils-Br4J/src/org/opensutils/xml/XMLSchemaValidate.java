/*
 * 	 @(#)XMLSchemaValidate.java	10/11/22
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 * XMLValidate checks if xml is a valid with XSD Schema
 * @author Felipe Priuli
 * @version 0.5
 *
 */
public class XMLSchemaValidate {

	/**
	 * Verifica se um xml está dentro do padrão do Schema e se este xml está formatado corretamente.
	 *
	 * @param 	urlSchema - URL onde se encontra.
	 * @param 	inputStream - InputStream de um arquivo xml.
	 * @return 	Document - xml valido. - null para xml invalido, e uma Exception ValidaXMLWithSchemaException
	 * @throws 	XMLException
	 */
	public Document validateXML(InputStream inputStream,
								String urlSchema) throws XMLException{
		
		XMLErrorHandler errorHandler = new XMLErrorHandler();

		Document document = null;
		DocumentBuilder builder = null;  
		
		try{  
			builder = this.buildDocumentBuilder(urlSchema);
			builder.setErrorHandler( errorHandler ); 

			document = builder.parse(inputStream);
			inputStream.close();
			if( errorHandler.getErros().size() == 0 ){
				return document;
			}else{
				throw new XMLException("XML INVALID",errorHandler.getErros());
			}
		} catch (IOException ex) {  
			throw new XMLException(ex);
		} catch (SAXException ex) {  
			throw new XMLException(ex);
		}	catch(ParserConfigurationException ex){  
			throw new XMLException(ex);
		}  
		
	}
	
	/**
	 * Verifica se um xml está dentro do padrão do Schema e se este xml está formatado corretamente.
	 *
	 * @param urlSchema - URL onde se encontra.
	 * @param xmlSource - conteudo de um arquivo xml, codigo xml.
	 * @param lineSeparator - String para separar os erros obtidos, null para utilizar o System.getPropety("line.separator").
	 * @return Document - xml valido. - null para xml invalido, e uma Exception ValidaXMLWithSchemaException
	 * @throws XMLException
	 */
	public Document validateXML(String xmlSource, 
								String urlSchema) throws XMLException{
		
		XMLErrorHandler errorHandler = new XMLErrorHandler();

		Document document = null;
		DocumentBuilder builder = null;  
		
		try{  
			builder = this.buildDocumentBuilder(urlSchema);
			builder.setErrorHandler( errorHandler ); 

			InputSource inSource = new InputSource(new StringReader(xmlSource));
			document = builder.parse(inSource);
			
			if( errorHandler.getErros().size() == 0 ){
				return document;
			}else{
				throw new XMLException("XML INVALID",errorHandler.getErros());
			}
		} catch (IOException ex) {  
			throw new XMLException(ex);
		} catch (SAXException ex) {  
			throw new XMLException(ex);
		}catch(ParserConfigurationException ex){  
			throw new XMLException(ex);
		}  
	}
	
	/**
	 * Verifica se um xml está dentro do padrão do Schema e se este xml está formatado corretamente.
	 *
	 * @param 	urlSchema  - URLonde se encontra schema.
	 * @param 	xmlFile - Um arquivo do tipo java.io.File que tenha em seu conteudo um xml.
	 * @return	Document - xml valido. - null para xml invalido, e uma Exception ValidaXMLWithSchemaException
	 * @throws 	XMLException
	 */
	public Document validateXML(	File xmlFile,
									String urlSchema) throws XMLException{
		try {
			return this.validateXML( new FileInputStream(xmlFile), urlSchema);
		} catch (FileNotFoundException fnE) {
			throw new XMLException(fnE);
		}
	}
	
	/**
	 * Constroi um objeto do tipo javax.xml.parsers.DocumentBuilder configurado para validar o xml
	 * a partir de um schema, encontrado na url passada no parametro deste método.
	 * @param urlSchema - URL onde se encontra o schema.
	 * @return DocumentBuilder - Configuradao para validar um xml.
	 * @throws ParserConfigurationException
	 */
	private DocumentBuilder buildDocumentBuilder(String urlSchema) throws ParserConfigurationException{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();	
		factory.setNamespaceAware(true);  
		factory.setValidating(true);  	
		factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");  
		factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", urlSchema );
		
		return factory.newDocumentBuilder();
	}

}
