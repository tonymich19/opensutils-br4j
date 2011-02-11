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


public class CpfCnpjUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCnpjToStringLong() {
		String resp = CpfCnpjUtils.cnpjToString(245526522L);
		assertTrue( resp.equals("00000245526522") );
		
		resp = CpfCnpjUtils.cnpjToString(526522L);
		assertTrue( resp.equals("00000000526522") );
	}

	@Test
	public void testCnpjToStringString() {
		String resp = CpfCnpjUtils.cnpjToString("00245526522");
		assertTrue( resp.equals("00000245526522") );
		
		resp = CpfCnpjUtils.cnpjToString("526522");
		assertTrue( resp.equals("00000000526522") );
	}

	@Test
	public void testCpfToStringLong() {

		String resp = CpfCnpjUtils.cpfToString(245526522L);
		assertTrue( resp.equals("00245526522") );

		resp = CpfCnpjUtils.cpfToString("554444");
		assertTrue( resp.equals("00000554444") );
	}

	@Test
	public void testCpfToStringString() {
		String resp = CpfCnpjUtils.cpfToString("00245526522");
		assertTrue( resp.equals("00245526522") );

		resp = CpfCnpjUtils.cpfToString("554444");
		assertTrue( resp.equals("00000554444") );

	}

	@Test
	public void testCpfCnpjToStringLong() {
		String resp = CpfCnpjUtils.cpfCnpjToString(245526522L);
		assertTrue( resp.equals("00000245526522") );

		resp = CpfCnpjUtils.cpfCnpjToString(689165234L);
		assertTrue( resp.equals("00689165234") );
		
		resp = CpfCnpjUtils.cpfCnpjToString(245526522L);
		assertTrue( resp.equals("00000245526522") );
		
		resp = CpfCnpjUtils.cpfCnpjToString(526522L);
		assertTrue( resp.equals("00000000526522") );
	}

	@Test
	public void testCpfCnpjToStringString() {
		String resp = CpfCnpjUtils.cpfCnpjToString("00245526522");
		assertTrue( resp.equals("00000245526522") );

		resp = CpfCnpjUtils.cpfCnpjToString("689165234");
		assertTrue( resp.equals("00689165234") );
		
		resp = CpfCnpjUtils.cpfCnpjToString("00245526522");
		assertTrue( resp.equals("00000245526522") );
		
		resp = CpfCnpjUtils.cpfCnpjToString("526522");
		assertTrue( resp.equals("00000000526522") );
	}

	@Test
	public void testFormatCnpjString() {
		String resp = CpfCnpjUtils.formatCnpj("00245526522");
		assertTrue( resp.equals("00.000.245/5265-22") );

		resp = CpfCnpjUtils.formatCnpj("23320245526522");
		assertTrue( resp.equals("23.320.245/5265-22") );


		resp = CpfCnpjUtils.formatCnpj("526522");
		assertTrue( resp.equals("00.000.000/5265-22") );


		resp = CpfCnpjUtils.formatCnpj("5222232222222222233333336522");
		assertTrue( resp.equals("5222232222222222233333336522") );
	}

	@Test
	public void testFormatCnpjLong() {
		String resp = CpfCnpjUtils.formatCnpj(245526522L);
		assertTrue( resp.equals("00.000.245/5265-22") );

		resp = CpfCnpjUtils.formatCnpj(23320245526522L);
		assertTrue( resp.equals("23.320.245/5265-22") );


		resp = CpfCnpjUtils.formatCnpj(526522L);
		assertTrue( resp.equals("00.000.000/5265-22") );


		resp = CpfCnpjUtils.formatCnpj(322222222222333332L);
		assertTrue( resp.equals("322222222222333332") );

	}

	@Test
	public void testFormatCpfString() {
		String resp = CpfCnpjUtils.formatCpf("00245526522");
		assertTrue( resp.equals("002.455.265-22") );

		resp = CpfCnpjUtils.formatCpf("554444");
		assertTrue( resp.equals("000.005.544-44") );


		resp = CpfCnpjUtils.formatCpf("124244");
		assertTrue( resp.equals("000.001.242-44") );


		resp = CpfCnpjUtils.formatCpf("5222232222222222233333336522");
		assertTrue( resp.equals("5222232222222222233333336522") );

	}

	@Test
	public void testFormatCpfLong() {

		String resp = CpfCnpjUtils.formatCpf(245526522L);
		assertTrue( resp.equals("002.455.265-22") );

		resp = CpfCnpjUtils.formatCpf(554444L);
		assertTrue( resp.equals("000.005.544-44") );


		resp = CpfCnpjUtils.formatCpf(124244L);
		assertTrue( resp.equals("000.001.242-44") );


		resp = CpfCnpjUtils.formatCpf(52222322233332323L);
		assertTrue( resp.equals("52222322233332323") );
	}

	@Test
	public void testFormatCpfOrCnpj() {
		String resp = CpfCnpjUtils.formatCpf(245526522L);
		assertTrue( resp.equals("002.455.265-22") );

		resp = CpfCnpjUtils.formatCpf(554444L);
		assertTrue( resp.equals("000.005.544-44") );
		

		resp = CpfCnpjUtils.formatCnpj(526522L);
		assertTrue( resp.equals("00.000.000/5265-22") );


		resp = CpfCnpjUtils.formatCnpj(322222222222333332L);
		assertTrue( resp.equals("322222222222333332") );

		resp = CpfCnpjUtils.formatCnpj(23320245526522L);
		assertTrue( resp.equals("23.320.245/5265-22") );
	}

	@Test
	public void testValidaCpfString() {
		boolean resp = CpfCnpjUtils.isValidCpf("03359643895");//Cpf valido
		if(resp == false)
			fail("Resultado invalido para validacao de CPF");

		resp = CpfCnpjUtils.isValidCpf("33596438950");//Cpf valido
		if(resp == false)
			fail("Resultado invalido para validacao de CPF");

		resp = CpfCnpjUtils.isValidCpf("00689165234");//CPF valido
		if(resp == false)
			fail("Resultado invalido");

		resp = CpfCnpjUtils.isValidCpf("00000689165234");//Cpf invalido
		if(resp == true)
			fail("Resultado invalido");

		resp = CpfCnpjUtils.isValidCpf("3359643895"); //Cpf valido
		if(resp == false)
			fail("Resultado invalido para validacao de CPF");

		resp = CpfCnpjUtils.isValidCpf("33596438951");//Cpf invalido
		if(resp == true)
			fail("Resultado invalido para validacao de CPF");

		resp = CpfCnpjUtils.isValidCpf("33blabla51");//Cpf invalido
		if(resp == true)
			fail("Resultado invalido para validacao de CPF");

		resp = CpfCnpjUtils.isValidCpf("335964389500000");//Cpf invalido
		if(resp == true)
			fail("Resultado invalido para validacao de CPF");
	}

	@Test
	public void testIsValidCpfLong() {
		boolean resp = CpfCnpjUtils.isValidCpf(3359643895L);//Cpf valido
		if(resp == false){
			fail("Resultado invalido para validacao de CPF");
		}
		
		resp = CpfCnpjUtils.isValidCpf(33596438950L);//Cpf valido
		if(resp == false){
			fail("Resultado invalido para validacao de CPF");
		}
		
		resp = CpfCnpjUtils.isValidCpf(689165234L);//Cpf valido (00689165234)
		if(resp == false){
			fail("Resultado invalido para validacao de CPF");
		}
				
		resp = CpfCnpjUtils.isValidCpf(33596438950000000L);//Cpf invalido
		if(resp == true){
			fail("Resultado invalido para validacao de CPF");
		}
		
		resp = CpfCnpjUtils.isValidCpf(33596438925L);//Cpf invalido
		if(resp == true){
			fail("Resultado invalido para validacao de CPF");
		}
	}

	@Test
	public void testValidaCnpjString() {
		boolean resp = CpfCnpjUtils.isValidCnpj("04312419000130");//CNPJ valido
		if(resp == false)
			fail("Resultado invalido");		

		resp = CpfCnpjUtils.isValidCnpj("04312419000131");//CNPJ invalido
		if(resp == true)
			fail("Resultado invalido");

		resp = CpfCnpjUtils.isValidCnpj("043blabla131");//CNPJ invalido
		if(resp == true)
			fail("Resultado invalido");

		resp = CpfCnpjUtils.isValidCnpj("4312419000130");//CNPJ valido
		if(resp == false)
			fail("Resultado invalido");

		resp = CpfCnpjUtils.isValidCnpj("00000689165234");//CNPJ valido
		if(resp == false)
			fail("Resultado invalido");

		resp = CpfCnpjUtils.isValidCnpj("335964389500000");//Cpf invalido
		if(resp == true)
			fail("Resultado invalido para validacao de CPF");
	}

	@Test
	public void testIsValidCnpjLong() {
		boolean resp = CpfCnpjUtils.isValidCnpj(689165234L);//CNPJ valido
		if(resp == false)
			fail("Resultado invalido");
		
		resp = CpfCnpjUtils.isValidCnpj(4312419000130L);//CNPJ valido
		if(resp == false)
			fail("Resultado invalido");
		
		resp = CpfCnpjUtils.isValidCnpj(4312419000131L);//CNPJ invalido
		if(resp == true)
			fail("Resultado invalido");

	}

	@Test
	public void testIsValidCpfOrCnpjString() {
		boolean resp = CpfCnpjUtils.isValidCpfOrCnpj("03359643895");//Cpf valido
		if(resp == false)
			fail("Resultado invalido para validacao de CPF");

		resp = CpfCnpjUtils.isValidCpfOrCnpj("33596438950");//Cpf valido
		if(resp == false)
			fail("Resultado invalido para validacao de CPF");

		resp = CpfCnpjUtils.isValidCpfOrCnpj("33596438959");//Cpf invalido
		if(resp == true)
			fail("Resultado invalido para validacao de CPF");

		resp = CpfCnpjUtils.isValidCpfOrCnpj("3359643895");//Cpf valido
		if(resp == false)
			fail("Resultado invalido para validacao de CPF");

		resp = CpfCnpjUtils.isValidCpfOrCnpj("33596438951");//Cpf invalido
		if(resp == true)
			fail("Resultado invalido para validacao de CPF");

		resp = CpfCnpjUtils.isValidCpfOrCnpj("33blabla51");//Cpf invalido
		if(resp == true)
			fail("Resultado invalido para validacao de CPF");

		resp = CpfCnpjUtils.isValidCpfOrCnpj("04312419000130");//CNPJ valido
		if(resp == false)
			fail("Resultado invalido");		

		resp = CpfCnpjUtils.isValidCpfOrCnpj("00000689165234");//CNPJ valido
		if(resp == false)
			fail("Resultado invalido");

		resp = CpfCnpjUtils.isValidCpfOrCnpj("04312419000131");//CNPJ invalido
		if(resp == true)
			fail("Resultado invalido");

		resp = CpfCnpjUtils.isValidCpfOrCnpj("043blabla131");//CNPJ invalido
		if(resp == true)
			fail("Resultado invalido");

		resp = CpfCnpjUtils.isValidCpfOrCnpj("4312419000130");//CNPJ valido
		if(resp == false)
			fail("Resultado invalido");	
		
		resp = CpfCnpjUtils.isValidCpfOrCnpj("4312419000138");//CNPJ invalido
		if(resp == true)
			fail("Resultado invalido");	

		resp = CpfCnpjUtils.isValidCpfOrCnpj("335964389500000");//Cpf invalido
		if(resp == true)
			fail("Resultado invalido para validacao de CPF");

	}

	@Test
	public void testIsValidCpfOrCnpjLong() {

		boolean resp = CpfCnpjUtils.isValidCpfOrCnpj(3359643895L);//Cpf valido
		if(resp == false){
			fail("Resultado invalido para validacao de CPF");
		}

		resp = CpfCnpjUtils.isValidCpfOrCnpj(33596438950L);//Cpf valido
		if(resp == false){
			fail("Resultado invalido para validacao de CPF");
		}

		resp = CpfCnpjUtils.isValidCpfOrCnpj(689165234L);//Cpf valido (00689165234)
		if(resp == false){
			fail("Resultado invalido para validacao de CPF");
		}

		resp = CpfCnpjUtils.isValidCpfOrCnpj(33596438925L);//Cpf invalido
		if(resp == true){
			fail("Resultado invalido para validacao de CPF");
		}

		resp = CpfCnpjUtils.isValidCpfOrCnpj(689165234L);//CNPJ valido
		if(resp == false)
			fail("Resultado invalido");

		resp = CpfCnpjUtils.isValidCpfOrCnpj(4312419000130L);//CNPJ valido
		if(resp == false)
			fail("Resultado invalido");

		resp = CpfCnpjUtils.isValidCpfOrCnpj(4312419000131L);//CNPJ invalido
		if(resp == true)
			fail("Resultado invalido");

		resp = CpfCnpjUtils.isValidCpfOrCnpj(4312419000130000000L);//CNPJ invalido
		if(resp == true)
			fail("Resultado invalido");
	}

	@Test
	public void testCpfLongZerado() {
		boolean resp = CpfCnpjUtils.isValidCpf(0L);
		if(resp != false)
			fail("CpfCnpj = 0 Resultado inválido");
	}

	@Test
	public void testCpfStringZerado() {
		boolean resp = CpfCnpjUtils.isValidCpf("00000000000");
		if(resp != false)
			fail("CpfCnpj = 00000000000Resultado inválido");
	}

	@Test
	public void testCnpjLongZerado() {
		boolean resp = CpfCnpjUtils.isValidCnpj(0L);
		if(resp != false)
			fail("CpfCnpj = 0 Resultado inválido");
	}

	@Test
	public void testValidaCnpjStringQuandoValorZerado() {
		boolean resp = CpfCnpjUtils.isValidCnpj("00000000000000");
		if(resp != false)
			fail("CpfCnpj = 00000000000 Resultado inválido");
	}

	@Test
	public void testCpfCnpjZerado(){
		boolean resp = CpfCnpjUtils.isValidCpfOrCnpj(0L);
		if(resp != false)
			fail("CpfCnpj = 00000000000 Resultado inválido");
	}

}
