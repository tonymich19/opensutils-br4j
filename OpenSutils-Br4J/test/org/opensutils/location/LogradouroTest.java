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

package org.opensutils.location;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.opensutils.location.Logradouro;


public class LogradouroTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testParseTipoLogradouro() {
		
		Logradouro logradouro = new Logradouro();
		String[] resp = logradouro.parseTipoLogradouro("AV   SERGIO LANDULFO FURTADO                                                    ");
		if(resp.length == 2){
			if(!resp[0].equals("AVENIDA"))
				fail("Resultado do parse logradouro invalido");
			if(!resp[1].equals("SERGIO LANDULFO FURTADO"))
				fail("Resultado do parse logradouro invalido");
		}else
			fail("Resultado do parse logradouro invalido");
		
		
		resp = logradouro.parseTipoLogradouro("R. Espártaco");
		if(resp.length == 2){
			if(!resp[0].equals("RUA"))
				fail("Resultado do parse logradouro invalido");
			if(!resp[1].equals("Espártaco"))
				fail("Resultado do parse logradouro invalido");
		}else
			fail("Resultado do parse logradouro invalido");

		
		resp = logradouro.parseTipoLogradouro("AV SERGIO LANDULFO FURTADO                                                    ");
		if(resp.length == 2){
			if(!resp[0].equals("AVENIDA"))
				fail("Resultado do parse logradouro invalido");
			if(!resp[1].equals("SERGIO LANDULFO FURTADO"))
				fail("Resultado do parse logradouro invalido");
		}else
			fail("Resultado do parse logradouro invalido");
		
		resp = logradouro.parseTipoLogradouro("R    GASPAR LEME                                                                ");
		if(resp.length == 2){
			if(!resp[0].equals("RUA"))
				fail("Resultado do parse logradouro invalido");
			if(!resp[1].equals("GASPAR LEME"))
				fail("Resultado do parse logradouro invalido");
		}else
			fail("Resultado do parse logradouro invalido");
		
		resp = logradouro.parseTipoLogradouro("TV   VIEIRA");
		if(resp.length == 2){
			if(!resp[0].equals("TRAVESSA"))
				fail("Resultado do parse logradouro invalido");
			if(!resp[1].equals("VIEIRA"))
				fail("Resultado do parse logradouro invalido");
		}else
			fail("Resultado do parse logradouro invalido");
		                                                            
		resp = logradouro.parseTipoLogradouro("QD   24 CJ F                                                                    ");
		if(resp.length == 2){
			if(!resp[0].equals("QUADRA"))
				fail("Resultado do parse logradouro invalido");
			if(!resp[1].equals("24 CJ F"))
				fail("Resultado do parse logradouro invalido");
		}else
			fail("Resultado do parse logradouro invalido");

		resp = logradouro.parseTipoLogradouro("PC   CAMPOS DA CUNHA");
		if(resp.length == 2){
			if(!resp[0].equals("PRAÇA"))
				fail("Resultado do parse logradouro invalido");
			if(!resp[1].equals("CAMPOS DA CUNHA"))
				fail("Resultado do parse logradouro invalido");
		}else
			fail("Resultado do parse logradouro invalido");
	
	}

	
	@Test
	public void testRemoveTipoLogradouro(){
		Logradouro logradouro = new Logradouro();
		String resp = logradouro.removeTipoLogradouro("   CASA DA CUNHA");
		if(!resp.equals("CASA DA CUNHA"))
			fail("Resultado do parse logradouro invalido");
		
		resp = logradouro.removeTipoLogradouro(" RUA  CASA DA CUNHA");
		if(!resp.equals("CASA DA CUNHA"))
			fail("Resultado do parse logradouro invalido");
		
		resp = logradouro.removeTipoLogradouro(" R. Espártaco");
		if(!resp.equals("Espártaco"))
			fail("Resultado do parse logradouro invalido");
		
		resp = logradouro.removeTipoLogradouro(" PC   CAMPOS DA CUNHA     ");
		if(!resp.equals("CAMPOS DA CUNHA"))
			fail("Resultado do parse logradouro invalido");
		
		resp = logradouro.removeTipoLogradouro("AV SERGIO LANDULFO FURTADO   ");
		if(!resp.equals("SERGIO LANDULFO FURTADO"))
			fail("Resultado do parse logradouro invalido");
		
		resp = logradouro.removeTipoLogradouro("AV guaicurius   ");
		if(!resp.equals("guaicurius"))
			fail("Resultado do parse logradouro invalido");

		resp = logradouro.removeTipoLogradouro("          AV SERGIO LANDULFO FURTADO   ");
		if(!resp.equals("SERGIO LANDULFO FURTADO"))
			fail("Resultado do parse logradouro invalido");
		
		resp = logradouro.removeTipoLogradouro("   TV   VIEIRA ");
		if(!resp.equals("VIEIRA"))
			fail("Resultado do parse logradouro invalido");

		resp = logradouro.removeTipoLogradouro("QD   24 CJ F                                                                 ");
		if(!resp.equals("24 CJ F"))
			fail("Resultado do parse logradouro invalido");
		
		resp = logradouro.removeTipoLogradouro("QD   rua                                                                ");
		if(!resp.equals("rua"))
			fail("Resultado do parse logradouro invalido");
		
		resp = logradouro.removeTipoLogradouro("           SERGIO LANDULFO furtado   ");
		if(!resp.equals("SERGIO LANDULFO furtado"))
			fail("Resultado do parse logradouro invalido");
	}
}
