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

package org.opensutils.security;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.opensutils.security.Cryptography;


public class TestCryptography {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToSHA1() {
	//	System.out.println( "FELIPEPRIULI="+Cryptography.toSHA1("FELIPEPRIULI"));
		String testeCrip = Cryptography.toSHA1("senha");
		if(! testeCrip.equalsIgnoreCase("7751a23fa55170a57e90374df13a3ab78efe0e99"))
			fail("A Criptografia para SHA1 Falhou");
		
		testeCrip = Cryptography.toSHA1("FELIPEPRIULI");
		if(! testeCrip.equalsIgnoreCase("5a69fcc9463b7d85db72dbc161dd9a2b75501b2e"))
			fail("A Criptografia para SHA1 Falhou");
	

	}
	@Test
	public void testToMD5() {
	//	System.out.println( "FELIPEPRIULI="+Cryptography.toSHA1("FELIPEPRIULI"));
		String testeCrip = Cryptography.toMD5("senha");
		if(! testeCrip.equalsIgnoreCase("e8d95a51f3af4a3b134bf6bb680a213a"))
			fail("A Criptografia para SHA1 Falhou");
		
		testeCrip = Cryptography.toMD5("FELIPEPRIULI");
		if(! testeCrip.equalsIgnoreCase("87fecc581b72d4b57651b56fbb8c0682"))
			fail("A Criptografia para SHA1 Falhou");
	

	}

}
