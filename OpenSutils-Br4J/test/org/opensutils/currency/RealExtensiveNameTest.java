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
package org.opensutils.currency;


import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.opensutils.currency.RealExtensiveName;


public class RealExtensiveNameTest {

	@Before
	public void setUp() throws Exception {

	}
	@Test
	public void test(){
		RealExtensiveName teste = new RealExtensiveName(new BigDecimal("2555.22"));
		//System.out.println("Numero  : " + (new DecimalFormat().format(Double.valueOf(("2555.22")))));
		//System.out.println("ValorExtenso : " + teste.toString());

		if(!teste.toString().equals("dois mil e quinhentos e cinquenta e cinco reais e vinte e dois centavos")){
			fail("Resultado incorreto");
		}	
		
		teste = new RealExtensiveName(new BigDecimal("854.10"));
		//System.out.println("Numero  : " + (new DecimalFormat().format(Double.valueOf(("854.10")))));
		//System.out.println("ValorExtenso : " + teste.toString());

		if(!teste.toString().equals("oitocentos e cinquenta e quatro reais e dez centavos")){
			fail("Resultado incorreto");
		}

	}

}
