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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.opensutils.StringUtils;


public class StringUtilsTest {

	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testAbbreviateName() {
		
		String respostaTest = StringUtils.abbreviateName("Bartolomeu Silva dos Santos Dias", 40);
		assertTrue( respostaTest.equals("Bartolomeu Silva dos Santos Dias") );
		
		respostaTest = StringUtils.abbreviateName("BARTOLOMEU SILVADOS SANTOS DIAS", 40);
		assertTrue( respostaTest.equals("BARTOLOMEU SILVADOS SANTOS DIAS") );
		
		respostaTest = StringUtils.abbreviateName("Bartolomeu Silva dos Santos Dias", 30);
		assertTrue( respostaTest.equals("Bartolomeu Silva Santos Dias") );
		
		respostaTest = StringUtils.abbreviateName("BARTOLOMEU SILVADOS SANTOS DIAS", 30);
		assertTrue( respostaTest.equals("BARTOLOMEU S. S. DIAS") );

		respostaTest = StringUtils.abbreviateName("Bartolomeu Silva dos Santos Dias", 15);
		assertTrue( respostaTest.equals("Bartolomeu Dias") );
		
		respostaTest = StringUtils.abbreviateName("Bartolomeu Silva dOs Santos Dias",30);
		assertTrue( respostaTest.equals("Bartolomeu Silva Santos Dias") );

		respostaTest = StringUtils.abbreviateName("Bartolomeu, Silva doS Santos Dias",30);
		assertTrue( respostaTest.equals("Bartolomeu Silva Santos Dias") );
		
		respostaTest = StringUtils.abbreviateName("BArToLomeU Silva Das DOS DES des Santos DIas",30);
		assertTrue( respostaTest.equals("BArToLomeU S. D. d. S. DIas") );
		
		respostaTest = StringUtils.abbreviateName("BArToLomeU Gonçalves",30);
		assertTrue( respostaTest.equals("BArToLomeU Gonçalves") );
		
		respostaTest = StringUtils.abbreviateName("BArToLomeU Silva Das Silva Goncalves",30);
		assertTrue( respostaTest.equals("BArToLomeU S. S. Goncalves") );
		//
		respostaTest = StringUtils.abbreviateName("ROBSON GONÇALVES DOS SANTOS",40);
		assertTrue( respostaTest.equals("ROBSON GONÇALVES DOS SANTOS") );
		
		respostaTest = StringUtils.abbreviateName("ROBSON GONÇALVES DOS SANTOS",12);
		assertTrue( respostaTest.equals("ROBSON SANTO") );
		
		respostaTest = StringUtils.abbreviateName("ROBSON GONÇALVES DOS SANTOS",16);
		assertTrue( respostaTest.equals("ROBSON G. SANTOS") );
		
		respostaTest = StringUtils.abbreviateName("ROBSON, GONÇALVES DOS, SANTOS",16);
		assertTrue( respostaTest.equals("ROBSON G. SANTOS") );
		
		respostaTest = StringUtils.abbreviateName("ROBSON GONÇALVéS DOS SáNTOS",16);
		assertTrue( respostaTest.equals("ROBSON G. SáNTOS") );
		
		respostaTest = StringUtils.abbreviateName("áÉÍóÚ GÕóõéíóúNÇáLVéS DOS de e das sámtüs",20);
		assertTrue( respostaTest.equals("áÉÍóÚ G. sámtüs") );
		
		respostaTest = StringUtils.abbreviateName("RóBSõçN GONÇALVES DOS SáNTÕS",20);
		assertTrue( respostaTest.equals("RóBSõçN G. SáNTÕS") );
		
		respostaTest = StringUtils.abbreviateName("BArToLomeU Silva Das Silva Goncãlves",30);
		assertTrue( respostaTest.equals("BArToLomeU S. S. Goncãlves") );
		
		respostaTest = StringUtils.abbreviateName("BárToLomeU Silva Das Silva Gonçãlves",30);
		assertTrue( respostaTest.equals("BárToLomeU S. S. Gonçãlves") );
		
		respostaTest = StringUtils.abbreviateName("Bartolomeu Silva dos Santos Dias", 10);
		assertTrue( respostaTest.equals("Bartolomeu") );
		
		respostaTest = StringUtils.abbreviateName("BartolomeuBartolomeuBartolomeuBartolomeu", 40);
		assertTrue( respostaTest.equals("BartolomeuBartolomeuBartolomeuBartolomeu"));
		
		respostaTest = StringUtils.abbreviateName("BartolomeuBartolomeuBartolomeuBartolomeu".toUpperCase(), 40);
		assertTrue( respostaTest.equals("BartolomeuBartolomeuBartolomeuBartolomeu".toUpperCase()));
		
		respostaTest = StringUtils.abbreviateName("BartolomeuBartolomeuBartolomeuBartolomeu", 20);
		assertTrue( respostaTest.equals("BartolomeuBartolomeu"));
		
		respostaTest = StringUtils.abbreviateName("Bartolomeu", 20);
		assertTrue( respostaTest.equals("Bartolomeu") );
		
		respostaTest = StringUtils.abbreviateName("Bartolomeu Noite", 20);
		assertTrue( respostaTest.equals("Bartolomeu Noite") );
		
		respostaTest = StringUtils.abbreviateName("BARTOLOMEU NOITE", 20);
		assertTrue( respostaTest.equals("BARTOLOMEU NOITE") );
		
		respostaTest = StringUtils.abbreviateName("Bartolomeu Noite", 13);
		assertTrue( respostaTest.equals("Bartolomeu No") );
		
		respostaTest = StringUtils.abbreviateName("", 10);
		assertTrue( respostaTest.isEmpty() );
		
		respostaTest = StringUtils.abbreviateName("123456 123456", 5);
		assertTrue( respostaTest.equals("12345") );
		
		respostaTest = StringUtils.abbreviateName("Marcos dos Santos Pereira Ferreira", 33);
		assertTrue( respostaTest.equals("Marcos Santos Pereira Ferreira") );
		
		respostaTest = StringUtils.abbreviateName("Marcos Dos Santos Pereira Ferreira", 33);
		assertTrue( respostaTest.equals("Marcos Santos Pereira Ferreira") );
		
		respostaTest = StringUtils.abbreviateName("Marcos DOS Santos PEreiRa Ferreira", 33);
		assertTrue( respostaTest.equals("Marcos Santos PEreiRa Ferreira") );
		
		respostaTest = StringUtils.abbreviateName("MARCOS DOS SANTOS PEREIRA FERREIRA", 33);
		assertTrue( respostaTest.equals("MARCOS SANTOS PEREIRA FERREIRA") );
		
		respostaTest = StringUtils.abbreviateName("Marcos dos Santos Pereira Ferreira", 34);
		assertTrue( respostaTest.equals("Marcos dos Santos Pereira Ferreira") );
		
		respostaTest = StringUtils.abbreviateName("Marcos dos de das Ferreira", 22);
		assertTrue( respostaTest.equals("Marcos Ferreira") );
		
		respostaTest = StringUtils.abbreviateName("Marcos dA SILVA de Ferreira", 22);
		assertTrue( respostaTest.equals("Marcos SILVA Ferreira") );
		
		respostaTest = StringUtils.abbreviateName("Marcos dA SILVA de Paulo Ferreira", 28);
		assertTrue( respostaTest.equals("Marcos SILVA Paulo Ferreira") );
		
		respostaTest = StringUtils.abbreviateName("Marcos dos de das Ferreira", 26);
		assertTrue( respostaTest.equals("Marcos dos de das Ferreira") );
		
		respostaTest = StringUtils.abbreviateName("Marcos do", 8);
		assertTrue( respostaTest.equals("Marcos d") );

		respostaTest = StringUtils.abbreviateName("MARIA BEATRIZ BELLO DA SILVA SIMIAO", 26);
		assertTrue( respostaTest.equals("MARIA B. B. S. SIMIAO") );
				
		respostaTest = StringUtils.abbreviateName("ENUTRIA DIANA BLADO STANESCON", 26);
		assertTrue( respostaTest.equals("ENUTRIA D. B. STANESCON") );
		
		respostaTest = StringUtils.abbreviateName("Maria das Neves da Silva e Costa", 20);
		assertTrue( respostaTest.equals("Maria N. S. Costa") );
		
		respostaTest = StringUtils.abbreviateName("MARIA DAS NEVES das SilvaS E COSTA", 20);
		assertTrue( respostaTest.equals("MARIA N. S. COSTA") );
		
		respostaTest = StringUtils.abbreviateName("Josefina das neves carmo", 30);
		assertTrue( respostaTest.equals("Josefina das neves carmo") );
		
		respostaTest = StringUtils.abbreviateName("Josefina & Neves carmo", 30);
		assertTrue( respostaTest.equals("Josefina & Neves carmo") );
		
		respostaTest = StringUtils.abbreviateName("CARMEM JOCELITA L. DOS SANTOS DE MELLO", 30);
		assertTrue( respostaTest.equals("CARMEM J. S. MELLO") );
		
		respostaTest = StringUtils.abbreviateName("BArToLomeU Silva Das Silva Goncãlves",24);
		assertTrue( respostaTest.equals("BArToLomeU Goncãlves") );
		
		respostaTest = StringUtils.abbreviateName("A L VILELA MONTAGEM ESTRUTURAS M L ME",30);
		assertTrue( respostaTest.equals("A L VILELA MONTAGEM ESTRUTURAS") );
		
		respostaTest = StringUtils.abbreviateName("A 2 VILELA MONTAGEM1 ESTRUTURAS M L ME",30);
		assertTrue( respostaTest.equals("A 2 VILELA MONTAGEM1 ESTRUTURA") );
		
		respostaTest = StringUtils.abbreviateName("A L VILELA MONTAGEM ESTRUTURAS M L ME",25);
		assertTrue( respostaTest.equals("A L VILELA MONTAGEM ESTRU") );
		
		respostaTest = StringUtils.abbreviateName("ã L VILELA MONTAGEM ESTRUTURAS M L ME",25);
		assertTrue( respostaTest.equals("ã L VILELA MONTAGEM ESTRU") );
		
		respostaTest = StringUtils.abbreviateName("M. P. DA SILVA DO NORTE",20);
		assertTrue( respostaTest.equals("M. P. DA SILVA DO NO") );
		
		respostaTest = StringUtils.abbreviateName("MÃ. P. DA SILVA DO NORTE",20);
		assertTrue( respostaTest.equals("MÃ. P. DA SILVA DO N") );
		
	}
	
	@Test
	public void testLeftPadding(){
		
		String cnpj = "2525225252";
		String resp = StringUtils.leftPad(cnpj,14,"0");
		assertTrue( resp.equals("00002525225252") );
		
		cnpj = "5251525233";
		resp = StringUtils.leftPad(cnpj,15,"0");
		assertTrue( resp.equals("000005251525233") );

		
		cnpj = "22";
		resp = StringUtils.leftPad(cnpj,2,"0");
		assertTrue( resp.equals("22") );
		

		cnpj = "2";
		resp = StringUtils.leftPad(cnpj,2,"0");
		assertTrue( resp.equals("02") );
		
		cnpj = "2333";
		resp = StringUtils.leftPad(cnpj,5,"R");
		assertTrue( resp.equals("R2333") );
		
		cnpj = "2333";
		resp = StringUtils.leftPad(cnpj,8," ");
		assertTrue( resp.equals("    2333") );
	
	
		cnpj = "2333333333";
		resp = StringUtils.leftPad(cnpj,2,"R");
		assertTrue( resp.equals("2333333333") );
	
		cnpj = "2333";
		resp = StringUtils.leftPad(cnpj,8,"");
		assertTrue( resp.equals("2333") );
	
	}
	
	@Test
	public void testRightPadding(){
		
		String cnpj = "2525225252";
		String resp = StringUtils.rightPad(cnpj,14,"0");
		assertTrue( resp.equals("25252252520000") );
		
		cnpj = "5251525233";
		resp = StringUtils.rightPad(cnpj,15,"0");
		assertTrue( resp.equals("525152523300000") );

		
		cnpj = "22";
		resp = StringUtils.rightPad(cnpj,2,"0");
		assertTrue( resp.equals("22") );
		

		cnpj = "2";
		resp = StringUtils.rightPad(cnpj,2,"0");
		assertTrue( resp.equals("20") );
		
		cnpj = "2333";
		resp = StringUtils.rightPad(cnpj,5,"R");
		assertTrue( resp.equals("2333R") );
		
		cnpj = "2333";
		resp = StringUtils.rightPad(cnpj,8," ");
		assertTrue( resp.equals("2333    ") );
	
		cnpj = "2333333333";
		resp = StringUtils.rightPad(cnpj,2,"R");
		assertTrue( resp.equals("2333333333") );
	
		cnpj = "2333";
		resp = StringUtils.rightPad(cnpj,8,"");
		assertTrue( resp.equals("2333") );
	
	
	}

	
	@Test
	public void testRemoveAccentuation(){
		
		String str = "São Paulo";
		assertTrue("RESULTADO INVALIDO", StringUtils.removeAccentuation(str).equals("Sao Paulo"));
		
		str = "SÃO PAULO";
		assertTrue("RESULTADO INVALIDO", StringUtils.removeAccentuation(str).equals("SAO PAULO"));
		
		str = "SÃO PRAÇA JOSÉ É DAS";
		assertTrue("RESULTADO INVALIDO", StringUtils.removeAccentuation(str).equals("SAO PRACA JOSE E DAS"));
		
		str = "GÜIANAS ÉS BÃS Ý OÚ À";
		assertTrue("RESULTADO INVALIDO", StringUtils.removeAccentuation(str).equals("GUIANAS ES BAS Y OU A"));
		
		
		str = "güianas és bãs ý ´ ã~õe";
		assertTrue("RESULTADO INVALIDO", StringUtils.removeAccentuation(str).equals("guianas es bas y ´ a~oe"));
		
		str = "güianas és bãs ý ´ ã~õe";
		assertTrue("RESULTADO INVALIDO", StringUtils.removeAccentuation(str).equals("guianas es bas y ´ a~oe"));
		
		str = "PÇ çinas borà è datiLHÕoSõs";
		assertTrue("RESULTADO INVALIDO", StringUtils.removeAccentuation(str).equals("PC cinas bora e datiLHOoSos"));
		
		
	}

	@Test
	public void testParseNameFile(){

		String str = "C:\\Documents and Settings\\priuli\\myTestFileName.txt";
		assertTrue("RESULTADO INVALIDO", StringUtils.parseNameFile(str).equals("myTestFileName.txt"));
		

		str = "C:/Documents and Settings/priuli/myTestFileName.txt";
		assertTrue("RESULTADO INVALIDO", StringUtils.parseNameFile(str).equals("myTestFileName.txt"));
	}

	@Test
	public void testRemoveSymbols(){

		String resp = StringUtils.removeSymbols("MiNhã frA$e, C0^ S|MBºL//oS. T*-Es.+-Tes. ");
		assertTrue("RESULTADO INVALIDO", resp.equals("MiNhã frAe, C0 SMBLoS. TEs.Tes. "));
	
		resp = StringUtils.removeSymbols("M$e%¨&u&*ª;{ ??~~]][´t~-=¹£¢³¬³²²³e£¢<>x;:|?t°°oº ;sem simbols.");
		assertTrue("RESULTADO INVALIDO", resp.equals("Meu texto sem simbols."));
		
		resp = StringUtils.removeSymbols("M$e%¨&u&*ª;{ ??~~]][´t~-=¹£¢³¬³²²³e£¢<>x;:|?t°°oº ;sem simbols."
				+System.getProperty("line.separator")+"Nova123456789 987654354321 +-+===Liñhã"		);
		assertTrue("RESULTADO INVALIDO", resp.equals("Meu texto sem simbols."+System.getProperty("line.separator")+"Nova123456789 987654354321 Liñhã"));
	}
	
	@Test
	public void testCapitalize(){
		String resp = StringUtils.capitalize("Meu teXtO para Ser orGaniZãDõ â");
		assertTrue("RESULTADO INVALIDO", resp.equals("Meu teXtO para Ser orGaniZãDõ â"));
		
		resp = StringUtils.capitalize("meu teXtO "+System.getProperty("line.separator")+"para Ser orGaniZãDõ â");
		assertTrue("RESULTADO INVALIDO", resp.equals("Meu teXtO "+System.getProperty("line.separator")+"para Ser orGaniZãDõ â"));
		
		resp = StringUtils.capitalize("meu teXtO");
		assertTrue("RESULTADO INVALIDO", resp.equals("Meu teXtO"));
		
		resp = StringUtils.capitalize("Meu teXtO "+System.getProperty("line.separator")+"para Ser orGaniZãDõ â"+System.getProperty("line.separator"));
		assertTrue("RESULTADO INVALIDO", resp.equals("Meu teXtO "+System.getProperty("line.separator")+"para Ser orGaniZãDõ â"+System.getProperty("line.separator")));
		
		resp = StringUtils.capitalize("meu teXtO "+System.getProperty("line.separator")+"para Ser orGaniZãDõ.  doIs eSpaçOs çtest ");
		assertTrue("RESULTADO INVALIDO", resp.equals("Meu teXtO "+System.getProperty("line.separator")+"para Ser orGaniZãDõ.  doIs eSpaçOs çtest "));
		
		resp = StringUtils.capitalize("   meu teste ");
		System.out.println(resp);
		assertTrue("RESULTADO INVALIDO", resp.equals("   Meu teste "));
		
		resp = StringUtils.capitalize(" meu teste ");
		assertTrue("RESULTADO INVALIDO", resp.equals(" Meu teste "));
		
		resp = StringUtils.capitalize(" meu          teste ");
		assertTrue("RESULTADO INVALIDO", resp.equals(" Meu          teste "));
		
		resp = StringUtils.capitalize(System.getProperty("line.separator")+" meu          teste ");
		assertTrue("RESULTADO INVALIDO", resp.equals(System.getProperty("line.separator")+" Meu          teste "));

		resp = StringUtils.capitalize(" felipe          priuli ");
		assertTrue("RESULTADO INVALIDO", resp.equals(" Felipe          priuli "));
	}
	
	@Test
	public void testCapitalizeAll(){

		String resp = StringUtils.capitalizeAll("Meu teXtO para Ser orGaniZãDõ â");
		assertTrue("RESULTADO INVALIDO", resp.equals("Meu TeXtO Para Ser OrGaniZãDõ Â"));
		
		resp = StringUtils.capitalizeAll("Meu teXtO "+System.getProperty("line.separator")+"para Ser orGaniZãDõ â");
		assertTrue("RESULTADO INVALIDO", resp.equals("Meu TeXtO "+System.getProperty("line.separator")+"Para Ser OrGaniZãDõ Â"));
		
		resp = StringUtils.capitalizeAll("meu teXtO");
		assertTrue("RESULTADO INVALIDO", resp.equals("Meu TeXtO"));
		
		resp = StringUtils.capitalizeAll("Meu teXtO "+System.getProperty("line.separator")+"para Ser orGaniZãDõ â"+System.getProperty("line.separator"));
		assertTrue("RESULTADO INVALIDO", resp.equals("Meu TeXtO "+System.getProperty("line.separator")+"Para Ser OrGaniZãDõ Â"+System.getProperty("line.separator")));
		
		resp = StringUtils.capitalizeAll("Meu teXtO "+System.getProperty("line.separator")+"para Ser orGaniZãDõ.  doIs eSpaçOs çtest ");
		assertTrue("RESULTADO INVALIDO", resp.equals("Meu TeXtO "+System.getProperty("line.separator")+"Para Ser OrGaniZãDõ.  DoIs ESpaçOs Çtest "));
		
		resp = StringUtils.capitalizeAll("   meu teste ");
		assertTrue("RESULTADO INVALIDO", resp.equals("   Meu Teste "));
		
		resp = StringUtils.capitalizeAll(" meu teste ");
		assertTrue("RESULTADO INVALIDO", resp.equals(" Meu Teste "));
		
		resp = StringUtils.capitalizeAll(" meu          teste ");
		assertTrue("RESULTADO INVALIDO", resp.equals(" Meu          Teste "));
		
		resp = StringUtils.capitalizeAll(System.getProperty("line.separator")+" meu          teste ");
		assertTrue("RESULTADO INVALIDO", resp.equals(System.getProperty("line.separator")+" Meu          Teste "));
	}
	@Test
	public void testIgnoreEmpty(){
		
		assertTrue("RESULTADO INVALIDO", StringUtils.ignoreEmpty(null) == null);
		
		assertTrue("RESULTADO INVALIDO", StringUtils.ignoreEmpty("") == null);
		
		assertTrue("RESULTADO INVALIDO", StringUtils.ignoreEmpty("                               ") == null);
		
		assertTrue("RESULTADO INVALIDO", StringUtils.ignoreEmpty("  ") == null);
		
		assertTrue("RESULTADO INVALIDO", StringUtils.ignoreEmpty("Valirvkj ajsu Ukd4 5 6a ").equals("Valirvkj ajsu Ukd4 5 6a "));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.ignoreEmpty("             JUnit       Test  ").equals("             JUnit       Test  "));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.ignoreEmpty("555").equals("555"));
	}

	
	@Test
	public void testTruncateAndRightPad(){

		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndRightPad("VALOR", 12, "X"  ).equals("VALORXXXXXXX"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndRightPad("VaLoR", 12, "X"  ).equals("VaLoRXXXXXXX"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndRightPad("VaLoR", 10, " "  ).equals("VaLoR     "));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndRightPad("         VaLoR         ", 10, " "  ).equals("VaLoR     "));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndRightPad("1125-X", 10, " "  ).equals("1125-X    "));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndRightPad("123456789", 5, " "  ).equals("12345"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndRightPad("            123456789", 5, " "  ).equals("12345"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndRightPad("            123456789          ", 5, " "  ).equals("12345"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndRightPad("                      ", 5, " "  ).equals("     "));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndRightPad("                      ", 2, " "  ).equals("  "));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndRightPad("                      ", 2, "0"  ).equals("00"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndRightPad(null, 2, "0"  ).equals("00"));
		
		try{
			StringUtils.truncateAndRightPad(null, 2, null  );
			fail("Resultado incorreto, era esperado NullPointerException");
		}catch(NullPointerException nfe){
			
		}
		
	}
	
	@Test
	public void testTruncateAndLeftPad(){

		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndLeftPad("VALOR", 12, "X"  ).equals("XXXXXXXVALOR"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndLeftPad("VaLoR", 12, "X"  ).equals("XXXXXXXVaLoR"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndLeftPad("VaLoR", 10, " "  ).equals("     VaLoR"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndLeftPad("         VaLoR         ", 10, " "  ).equals("     VaLoR"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndLeftPad("1125-X", 10, " "  ).equals("    1125-X"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndLeftPad("123456789", 5, " "  ).equals("12345"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndLeftPad("123456789", 20, "0"  ).equals("00000000000123456789"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndLeftPad("            123456789", 5, " "  ).equals("12345"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndLeftPad("            123456789          ", 5, " "  ).equals("12345"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndLeftPad("                      ", 5, " "  ).equals("     "));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndLeftPad("                      ", 2, " "  ).equals("  "));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndLeftPad("                      ", 2, "0"  ).equals("00"));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.truncateAndLeftPad(null, 2, "0"  ).equals("00"));
		
		try{
			StringUtils.truncateAndLeftPad(null, 2, null  );
			fail("Resultado incorreto, era esperado NullPointerException");
		}catch(NullPointerException nfe){
			
		}
	}
	
	@Test
	public void testisNullOrEmpty(){
		
		assertTrue("RESULTADO INVALIDO", StringUtils.isNullOrEmpty(null));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.isNullOrEmpty(""));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.isNullOrEmpty("           "));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.isNullOrEmpty(" "));
		
		assertTrue("RESULTADO INVALIDO", StringUtils.isNullOrEmpty( "    test ") == false);
		
		assertTrue("RESULTADO INVALIDO", StringUtils.isNullOrEmpty(".") == false);
		
		assertTrue("RESULTADO INVALIDO", StringUtils.isNullOrEmpty("132456789") == false);
		
		
		
	}
}

