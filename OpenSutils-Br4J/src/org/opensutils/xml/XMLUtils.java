package org.opensutils.xml;

import java.io.File;
import java.io.InputStream;

import org.w3c.dom.Document;
/**
 * The class <code>XMLUtils</code> 
 * 
 * @version 0.4
 * @author Felipe Priuli
 */
public abstract class XMLUtils {

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
