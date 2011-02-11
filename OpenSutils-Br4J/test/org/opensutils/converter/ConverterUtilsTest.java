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
package org.opensutils.converter;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class ConverterUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBuildConvertInteger() {
		
		String value = "2568";
		Converter c = ConverterUtils.buildConvertInteger();
		Object obj = c.getAsObject(value);
		assertTrue( "Resultado invalido", obj.equals(2568));
		
		String result = c.getAsString(obj);
		assertTrue( "Resultado invalido", result.equals("2568"));
		
	}
	
	@Test
	public void testBuildConvertShort() {
		
		String value = "2568";
		Converter c = ConverterUtils.buildConvertShort();
		Object obj = c.getAsObject(value);
		assertTrue( "Resultado invalido", obj.equals((short)2568));
		
		String result = c.getAsString(obj);
		assertTrue( "Resultado invalido", result.equals("2568"));
		
	}

	@Test
	public void testBuildConvertDouble() {
		String value = "15875.42";
		Converter c = ConverterUtils.buildConvertDouble();
		Object obj = c.getAsObject(value);
		assertTrue( "Resultado invalido", obj.equals((double)15875.42));
		
		String result = c.getAsString(obj);
		assertTrue( "Resultado invalido", result.equals("15875.42"));
	}

	@Test
	public void testBuildConvertFloat() {
		String value = "155.01";
		Converter c = ConverterUtils.buildConvertFloat();
		Object obj = c.getAsObject(value);
		assertTrue( "Resultado invalido", obj.equals((float)155.01));
		
		String result = c.getAsString(obj);
		assertTrue( "Resultado invalido", result.equals("155.01"));
	}

	@Test
	public void testBuildConvertLong() {
		String value = "58886645423";
		Converter c = ConverterUtils.buildConvertLong();
		Object obj = c.getAsObject(value);
		assertTrue( "Resultado invalido", obj.equals((long)58886645423L));
		
		String result = c.getAsString(obj);
		assertTrue( "Resultado invalido", result.equals("58886645423"));
	}

	@Test
	public void testBuildConvertString() {
		String value = "254ASTEST_JUNIT 4 .,;~0875452/*-";
		Converter c = ConverterUtils.buildConvertString();
		Object obj = c.getAsObject(value);
		assertTrue( "Resultado invalido", obj.equals("254ASTEST_JUNIT 4 .,;~0875452/*-"));
		
		String result = c.getAsString(obj);
		assertTrue( "Resultado invalido", result.equals("254ASTEST_JUNIT 4 .,;~0875452/*-"));
	}

	@Test
	public void testBuildConvertDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2004, 4-1, 16, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		//16/04/2004
		
		Converter c = ConverterUtils.buildConvertDate(new SimpleDateFormat("dd/MM/yyyy"));
		Object obj = c.getAsObject("16/04/2004");
		assertTrue(obj instanceof java.util.Date);
		assertTrue( "Resultado invalido", ((java.util.Date)obj).compareTo(calendar.getTime()) == 0 );
		
		String result = c.getAsString(obj);
		assertTrue( "Resultado invalido", result.equals("16/04/2004"));
	}

}
