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

import static org.junit.Assert.fail;

import org.junit.Test;

public class TestHexaDecimalUtils {


	@Test
	public void testToStringByte() {

		String resp = HexaDecimalUtils.toString("felipe".getBytes());
	
		if(!resp.equals("66656C697065"))
			fail("Resultado invalido");
		
		resp = HexaDecimalUtils.toString("Generates a hexadecimal String".getBytes());

		if(!resp.equals("47656E65726174657320612068657861646563696D616C20537472696E67"))
			fail("Resultado invalido");
		
		
		resp = HexaDecimalUtils.toString("26".getBytes());
		if(!resp.equals("3236"))
			fail("Resultado invalido");

	}

	@Test
	public void testToStringByteArray() {
		
		String resp = "";
		byte[] bytes = "felipe".getBytes();
		for (byte b : bytes) {
			resp+=HexaDecimalUtils.toString(b);
		}
		
		
		if(!resp.equals("66656C697065"))
			fail("Resultado invalido");
		
		resp = "";
		bytes = "Generates a hexadecimal String".getBytes();
		for (byte b : bytes) {
			resp+=HexaDecimalUtils.toString(b);
		}
		
		if(!resp.equals("47656E65726174657320612068657861646563696D616C20537472696E67"))
			fail("Resultado invalido");
		
		resp = "";
		bytes = "26".getBytes();
		for (byte b : bytes) {
			resp+=HexaDecimalUtils.toString(b);
		}

		if(!resp.equals("3236"))
			fail("Resultado invalido");
	}

	@Test
	public void testToByteArray() {
		
		byte[] bytes = HexaDecimalUtils.toByteArray("66656C697065");
		//System.out.println(""+bytes);
		String resp = new String(bytes);
		if(!resp.equals("felipe"))
			fail("Resultado invalido");
		
	
		bytes = HexaDecimalUtils.toByteArray("47656E65726174657320612068657861646563696D616C20537472696E67");
		resp = new String(bytes);
		if(!resp.equals("Generates a hexadecimal String"))
			fail("Resultado invalido");
		
		bytes = HexaDecimalUtils.toByteArray("3236");
		resp = new String(bytes);
		if(!resp.equals("26"))
			fail("Resultado invalido");
	}

}
