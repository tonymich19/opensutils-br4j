/*
 * 	 @(#)Logradouro.java	1.2 10/11/22
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
package org.opensutils.location;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The class <code>Logradouro</code> is used for validade, convert and parse 
 * the street names and type names of streets, is a Utility for Logradouro
 * 
 * 
 * @see org.opensutils.location.TipoLogradouro
 * @version 1.2
 * @author Felipe Priuli
 */
public class Logradouro {

	public final String FILE_NAME_CONF_TIPOS_LOGRADOUROS ="tipos_logradouro.xml";
	protected transient List<TipoLogradouro> tipoLogradouroList;
	
	public Logradouro(){
		//@author Felipe Priuli
	}
	
	/**
	 * Re-Load the configuration of types of neighborhoods, 
	 * present in configuration file the name tipos_logradouro.xml
	 */
	protected void loadTipoLogradouro(){
		if(tipoLogradouroList == null || tipoLogradouroList.size() == 0){
			
			InputStream in = Logradouro.class.getResourceAsStream(FILE_NAME_CONF_TIPOS_LOGRADOUROS);
			this.tipoLogradouroList = parseXmlTipoLogradouro(in);
			
			try{ in.close() ;}catch(Exception e){e.printStackTrace();};
		}
	}
	/**
	 * Given the type of street and street name. 
	 * @param	typeStreet - value to return in position 1
	 * @param	streetName - value to return in position 2
	 * @return	vector the String with 2 positions.
	 * 					<br>position 1 - The type of Street
	 * 					<br>position 2 - The name of Street
	 * 
	 */
	private static String[] getLogradouroArray(	String typeStreet, 
												String streetName){
		String[] result = new String[2];
		result[0] = typeStreet;
		result[1] = streetName;
		
		return result;
	}
	/**
	 * Clean all field for this class.
	 */
	public void clean(){
		this.tipoLogradouroList = null;
	}
	/**
	 * Parses the type contained in a street adress separating the 'type
	 * of street' of the street adress.  Unifying the type of street based 
	 * on the configuration file.
	 * 
	 * @param	streetName - the name of address with the king of street
	 * @return	array of String with two positions<br>
	 * 		   		Position [0] - The type of street <br>
	 * 				Position [1] - The param 'streetName' without The type of street.<br>
	 * 
	 */
	public String[] parseTipoLogradouro(final String streetName){
		this.loadTipoLogradouro();
		
		return Logradouro.parseTipoLogradouro(streetName, tipoLogradouroList);
	}
	/**
	 * Parses the type contained in a street adress separating the 'type
	 * of street' of the street adress.  Unifying the type of street based 
	 * on the configuration file.
	 * 
	 * @param	streetName - the name of address with the king of street
	 * @param	tipoLogradouroList - the List rules for matches with 'streetName' param
	 * @see org.opensutils.location.TipoLogradouro
	 * @return	array of String with two positions<br>
	 * 		   		Position [0] - The type of street <br>
	 * 				Position [1] - The param 'streetName' without The type of street.<br>
	 * 
	 */
	public static String[] parseTipoLogradouro(	final String streetName,
												final List<TipoLogradouro> tipoLogradouroList){
		
		Matcher m = null;
		String[] result = null;
		String defaultLabel = null;
		String value = streetName.trim();

		LOOPSTREET:
			for (org.opensutils.location.TipoLogradouro tipo : tipoLogradouroList) {
				for(String regex : tipo.getRegexList()){
					if((m = (Pattern.compile(regex)).matcher(value)).find()){
						result = getLogradouroArray(tipo.getLabel() ,m.group(2));
						break LOOPSTREET;
					}else if(tipo.getId().equals("default")){
						defaultLabel = tipo.getLabel();
					}
				}
			}

		if(result == null){
			if(defaultLabel != null){
				result = getLogradouroArray(defaultLabel, value);
			}else{
				result = getLogradouroArray(null, value);
			}
		}
		
		return result;

	}
	
	/**
	 * Removes the type contained in a street adress
	 * 
	 * @param	streetName - the name of address with the king of street
	 * @return	The param 'streetName' without the type of street.
	 */
	public String removeTipoLogradouro(	final String streetName ){
		this.loadTipoLogradouro();
	
		return Logradouro.removeTipoLogradouro(	streetName,
												tipoLogradouroList);
	}
	/**
	 * Removes the type contained in a street adress
	 * 
	 * @param	streetName - the name of address with the king of street
	 * @param	tipoLogradouroList - the List rules for matches with 'streetName' param
	 * @see org.opensutils.location.TipoLogradouro
	 * @return	The param 'streetName' without the type of street.
	 */
	public static String removeTipoLogradouro(	final String streetName,
												final List<TipoLogradouro> tipoLogradouroList){

		Matcher m = null;
		String result = null;
		String value = streetName.trim();

		LOOPSTREET:
			for (org.opensutils.location.TipoLogradouro tipo : tipoLogradouroList) {
				for(String regex : tipo.getRegexList()){
					if((m = (Pattern.compile(regex)).matcher(value)).find()){
						result = m.group(2);
						break LOOPSTREET;
					}
				}
			}

		if(result == null){
			result = value;
		}
		
		return result;

	}

	/**
	 * Parser the xml file to a List of TipoLogradouro.
	 * @param	in - the configuration in InputStream
	 * @see org.opensutils.location.TipoLogradouro  
	 * @return	list of TipoLogradouro
	 */
	public static List<TipoLogradouro> parseXmlTipoLogradouro(InputStream in){
		List<TipoLogradouro> list = new LinkedList<TipoLogradouro>();

		try{
			Document XMLDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);
			Element xmlElement = XMLDoc.getDocumentElement();

			////////////////////////////////////////////////////////////////
			// PARSER DEFAULT
			////////////////////////////////////////////////////////////////
			NodeList nl = xmlElement.getElementsByTagName("default");
			NodeList nodeList = nl.item(0).getChildNodes();

			for(int i = 0; i <  nodeList.getLength(); i++){
				Node nd = nodeList.item(i);
				try{
					if(nd.getNodeName().equals("id")){
						TipoLogradouro tipo = new TipoLogradouro();
						tipo.setId("default");
						tipo.setLabel(nd.getTextContent());
						break;
					}
				}catch(Exception e){
					continue;
				}
			}

			////////////////////////////////////////////////////////////////
			// PARSER TIPOS LOGRADOURO
			////////////////////////////////////////////////////////////////
			nl = xmlElement.getElementsByTagName("tipo_logradouro");
			if(nl != null){
				for(int _typesCount = 0; _typesCount <  nl.getLength(); _typesCount++){
					Node nd = nl.item( _typesCount );

					if(nd != null){
						nodeList = nd.getChildNodes();

						if(nodeList != null){
							TipoLogradouro tipo = new TipoLogradouro();

							for(int i = 0; i <  nodeList.getLength(); i++){
								Node typeNode = nodeList.item(i);

								if(typeNode.getNodeName().equals("id")){
									tipo.setId(typeNode.getTextContent());
								}else if(typeNode.getNodeName().equals("label")){
									tipo.setLabel(typeNode.getTextContent());
								}else if(typeNode.getNodeName().equals("regex")){
									tipo.getRegexList().add(typeNode.getTextContent());
								}
							}

							list.add(tipo);
						}
					}
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
