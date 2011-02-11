package org.opensutils.xml;


import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ValidaXMLWithSchemaTest {

	private XMLSchemaValidate validadorTest;
	@Before
	public void setUp() throws Exception {
		validadorTest = new XMLSchemaValidate();
	}

	@Test
	public void deveValidarSemErro(){

		try {
			StringBuffer xml = new StringBuffer();
			xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			xml.append("<resultSet xmlns=\"file:///c:/OpenSutilsBr4J\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"file:///c:/OpenSutilsBr4J schemaValidatorTest.xsd\"> ");
			xml.append("<detail>"+System.getProperty("line.separator"));
			xml.append("<ddd>11</ddd>"+System.getProperty("line.separator"));
			xml.append("<phone>31565700</phone>"+System.getProperty("line.separator"));
			xml.append("<person>Felipe</person>"+System.getProperty("line.separator"));
			xml.append("<number>0</number>"+System.getProperty("line.separator"));				
			xml.append("<code>SP</code>"+System.getProperty("line.separator"));			
			xml.append("</detail>"+System.getProperty("line.separator"));
			xml.append("</resultSet>"+System.getProperty("line.separator"));

			validadorTest.validateXML(xml.toString(),"file:///c:/OpenSutilsBr4J/schemaValidatorTest.xsd");
		} catch (XMLException e) {
			for(XMLElementError error : e.getListErro())
				System.out.println("Line: "+error.getRow() + " Columns: "+error.getColumn() +" Error:" +error.getError());
			fail("Ocorreu um erro ao validar Erro = "+ e.getMessage());

		}
	}
	
	
	@Test
	public void deveOcorrerErroAoValidar(){

		try {
			StringBuffer xml = new StringBuffer();
			xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			xml.append("<resultSet xmlns=\"file:///c:/OpenSutilsBr4J\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"file:///c:/OpenSutilsBr4J schemaValidatorTest.xsd\"> ");
			xml.append("<detail>"+System.getProperty("line.separator"));
			xml.append("<ddd>11</ddd>"+System.getProperty("line.separator"));
			xml.append("<phone>31565700</phone>"+System.getProperty("line.separator"));
			xml.append("<number>0</number>"+System.getProperty("line.separator"));				
			xml.append("<code>SP</code>"+System.getProperty("line.separator"));			
			xml.append("</detail>"+System.getProperty("line.separator"));
			xml.append("</resultSet>"+System.getProperty("line.separator"));

			validadorTest.validateXML(xml.toString(),"file:///c:/OpenSutilsBr4J/schemaValidatorTest.xsd");
			fail("Deveria ocorrer erro.");
		} catch (XMLException e) {
			/*for(XMLElementError erro : e.getListErro()){
				System.out.println(erro.getRow() + " "+erro.getError());
			}*/
			

		}
	}

}
