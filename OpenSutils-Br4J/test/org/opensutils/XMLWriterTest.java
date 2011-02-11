/*
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
package org.opensutils;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;



import org.junit.Before;
import org.junit.Test;
import org.opensutils.xml.XMLWriter;


public class XMLWriterTest {

	private XMLWriter xml;
	
	@Before
	public void setUp() throws Exception {
		xml = new XMLWriter("<result>");
	}
	
	@Test
	public void addRegistro(){
		try{
			xml.add("<testeBolean>", true);
			String resp = xml.getXML();
			assertTrue("Dados invalidos de XMLWriter", resp.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><result><testeBolean><![CDATA[true]]></testeBolean></result>"));
			xml.add("<testeString>", "TRUE"); 
			resp = xml.getXML();
			assertTrue("Dados invalidos de XMLWriter", resp.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><result><testeBolean><![CDATA[true]]></testeBolean><testeString><![CDATA[TRUE]]></testeString></result>"));
			xml.add("<testeLong>", 20L);
			resp = xml.getXML();
			assertTrue("Dados invalidos de XMLWriter", resp.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><result><testeBolean><![CDATA[true]]></testeBolean><testeString><![CDATA[TRUE]]></testeString><testeLong>20</testeLong></result>"));
			xml.add("<testeNull>", null);  
			resp = xml.getXML();
			assertTrue("Dados invalidos de XMLWriter", resp.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><result><testeBolean><![CDATA[true]]></testeBolean><testeString><![CDATA[TRUE]]></testeString><testeLong>20</testeLong><testeNull/></result>"));
		
		}catch(Exception e){
			fail("Ocorreu um erro ao adicionar registro no XMLWriter");
		}
	}
	
	@Test
	public void clearXml(){
		xml.add("<teste>", "nada"); 
		xml.add("<teste>", "nada2");
		xml.add("<teste>", "nada3");
		xml.add("<teste>", "nada4");
		xml.clearXML();
		
		String resp = xml.getXML();
		assertTrue( resp.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><result></result>"));
	}
	
	@Test
	public void openTag(){
		xml.clearXML();
		
		xml.openTag("<teste>");
		String resp = xml.getXML();
		assertTrue( resp.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><result><teste></result>"));
	}
	
	@Test
	public void closeTag(){
		xml.clearXML();
		
		xml.closeTag();

		String resp = xml.getXML();
		assertTrue( resp.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><result></result>"));
		
		xml.openTag("<teste>");	
		xml.closeTag();
		resp = xml.getXML();
		assertTrue( resp.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><result><teste></teste></result>"));
		
		xml.clearXML();
		xml.openTag("<teste>");
			xml.openTag("<filhoTeste>");	
				xml.openTag("<filhoTeste2>");	
				xml.closeTag();
			xml.closeTag();
		xml.closeTag();
		resp = xml.getXML();
		assertTrue( resp.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><result><teste><filhoTeste><filhoTeste2></filhoTeste2></filhoTeste></teste></result>"));
		
		
		
	}
	
	@Test
	public void getXmlError(){
		xml.clearXML();

		String resp = xml.getXMLError("Erro no teste");
		assertTrue( resp.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><result><error><![CDATA[Erro no teste]]></error></result>"));
		
		resp = xml.getXML();
		assertTrue( resp.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><result><error><![CDATA[Erro no teste]]></error></result>"));
		
		 resp = xml.getXMLError("Erro no teste2");
		assertTrue( resp.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><result><error><![CDATA[Erro no teste2]]></error></result>"));		
	}
	
}
