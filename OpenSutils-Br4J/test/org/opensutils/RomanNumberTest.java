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

public class RomanNumberTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFormat() {

		assertTrue( "Resultado invalido", RomanNumber.format(1).equals("I") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(2).equals("II") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(3).equals("III") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(4).equals("IV") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(5).equals("V") );

		assertTrue( "Resultado invalido", RomanNumber.format(6).equals("VI") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(7).equals("VII") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(8).equals("VIII") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(9).equals("IX") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(10).equals("X") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(11).equals("XI") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(12).equals("XII") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(13).equals("XIII") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(14).equals("XIV") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(15).equals("XV") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(16).equals("XVI") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(17).equals("XVII") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(18).equals("XVIII") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(19).equals("XIX") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(20).equals("XX") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(40).equals("XL") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(45).equals("XLV") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(50).equals("L") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(90).equals("XC") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(100).equals("C") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(400).equals("CD") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(500).equals("D") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(900).equals("CM") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(1000).equals("M") );

		assertTrue( "Resultado invalido", RomanNumber.format(4552).equals("MMMMDLII") );
		
		assertTrue( "Resultado invalido", RomanNumber.format(54).equals("LIV") );
		
		try{
			RomanNumber.format(0);
			fail("Resultado invalido");
			
		}catch(ArithmeticException e){
			//OK
		}
		
		try{
			RomanNumber.format(-885);
			fail("Resultado invalido");
			
		}catch(ArithmeticException e){
			//OK
		}


	}

	@Test
	public void testParse(){

		assertTrue( "Resultado invalido", RomanNumber.parse("I") == 1);
		
		assertTrue( "Resultado invalido", RomanNumber.parse("II") == 2 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("III") == 3 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("IV") == 4 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("V") == 5);
		
		assertTrue( "Resultado invalido", RomanNumber.parse("VI") == 6 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("         VII") == 7 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("VIII") == 8);
		
		assertTrue( "Resultado invalido", RomanNumber.parse("IX") == 9 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("X") == 10 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("       XI     ") == 11 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("XII") == 12 );;
		
		assertTrue( "Resultado invalido", RomanNumber.parse("XIII") == 13 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("XIV") == 14 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("XV") == 15);
		
		assertTrue( "Resultado invalido", RomanNumber.parse("XVI") == 16 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("XVII") == 17 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("XVIII           ") == 18 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("XIX") == 19 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("XX") == 20);
		
		assertTrue( "Resultado invalido", RomanNumber.parse("XL") == 40 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("XLV") == 45);
		
		assertTrue( "Resultado invalido", RomanNumber.parse("L") == 50);
		
		assertTrue( "Resultado invalido", RomanNumber.parse("XC") == 90 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("C") == 100 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("CD") == 400 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("D")  == 500 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("CM") == 900 );
		
		assertTrue( "Resultado invalido", RomanNumber.parse("M") == 1000 );

		assertTrue( "Resultado invalido", RomanNumber.parse("MMMMDLII")== 4552);
		
		assertTrue( "Resultado invalido", RomanNumber.parse("LIV")== 54);
	}
	
	@Test
	public void isValid(){
		
	assertTrue( "Resultado invalido", RomanNumber.isValid("I"));
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("II")  );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("III")  );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("IV")  );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("V") );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("VI")  );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("VII") );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("VIII") );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("IX") );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("X"));
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("XI") );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("XII") );;
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("XIII")  );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("XIV")  );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("XV"));
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("XVI")  );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("XVII") );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("XVIII") );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("XIX") );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("XX"));
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("XL"));
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("XLV") );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("L"));
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("XC") );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("C")  );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("CD")  );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("D")   );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("CM")  );
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("M"));

		assertTrue( "Resultado invalido", RomanNumber.isValid("MMMMDLII"));
		
		assertTrue( "Resultado invalido", RomanNumber.isValid("LIV"));
		
		
		assertFalse( "Resultado invalido", RomanNumber.isValid("x"));
		
		assertFalse( "Resultado invalido", RomanNumber.isValid("d"));
		
		assertFalse( "Resultado invalido", RomanNumber.isValid("y"));
		
		assertFalse( "Resultado invalido", RomanNumber.isValid("78915"));
		
		assertFalse( "Resultado invalido", RomanNumber.isValid("1"));
		
		assertFalse( "Resultado invalido", RomanNumber.isValid("I1."));
		
		assertFalse( "Resultado invalido", RomanNumber.isValid("Roman Number"));
		
		assertFalse( "Resultado invalido", RomanNumber.isValid("ROMAN NUMBER"));
		
		assertFalse( "Resultado invalido", RomanNumber.isValid("DK"));
		
		assertFalse( "Resultado invalido", RomanNumber.isValid("*/-++"));
		
		assertFalse( "Resultado invalido", RomanNumber.isValid("         "));
		
		assertFalse( "Resultado invalido", RomanNumber.isValid(null));
		
	}
	
}
