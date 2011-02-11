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
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.opensutils.converter.layout.FieldLayout;
import org.opensutils.converter.layout.Layout;
import org.opensutils.converter.layout.FieldLayout.TypePad;

public class C3POLineConverterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConvertToArray() {
		String row = "1NOME_CLIENTE00001255VALUE000400050001.414/12/2010";
		//Começando a contagem com 0
		//NOME_CLIENTE = 0 - 12
		//00001255 = 12 - 20
		//VALUE = 20 - 25
		//0004 = 25 - 29
		//0005 = 29 - 34
		//0001.4 = 33 - 39
		//14/12/2010 = 39 - 50
		
		List<Layout> layout = new ArrayList<Layout>(8);
		layout.add( new Layout(0,1,"1", MockClass.class));//Começa com 1 utilizará este layout
		layout.get(0).getFields().add(new FieldLayout(1,13,"name",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(13,21,"id",ConverterUtils.buildConvertLong()));
		layout.get(0).getFields().add(new FieldLayout(21,26,"nameNull",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(34,40,"numberFloat",ConverterUtils.buildConvertFloat()));
		layout.get(0).getFields().add(new FieldLayout(21,26,"fieldNotGetMethod",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(26,37,"numberInteger",ConverterUtils.buildConvertInteger()));
		layout.get(0).getFields().add(new FieldLayout(40,50,"data",ConverterUtils.buildConvertDate(new SimpleDateFormat("dd/MM/yyyy"))));
		
		
		C3POLineConverter c = new C3POLineConverter(layout);
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.set(2010, (12-1), 14, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			
			String[] resp =  c.convertToArray(row);
			assertTrue(resp.length == 8);
			assertTrue(resp[0].equals("1"));
			assertTrue(resp[1].equals("NOME_CLIENTE"));
			assertTrue(resp[2].equals("00001255"));
			assertTrue(resp[3].equals("VALUE"));
			assertTrue(resp[4].equals("0001.4"));
			assertTrue(resp[5].equals("VALUE"));
			assertTrue(resp[6].equals("00040005000"));
			assertTrue(resp[7].equals("14/12/2010"));
			
			
			
		} catch (ConverterException e) {
			e.printStackTrace();
			fail("Erro inesperado.");
			
		}
		
	}
	
	@Test
	public void testConvertToLineArray() {
		List<Layout> layout = new ArrayList<Layout>(8);
		layout.add( new Layout(0,1,"1", MockClass.class));//Começa com 1 utilizará este layout
		layout.get(0).getFields().add(new FieldLayout(1,13,"name"," ",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(13,21,"id","0",ConverterUtils.buildConvertLong()));
		layout.get(0).getFields().add(new FieldLayout(21,26,"nameNull"," ",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(34,40,"numberFloat","0",ConverterUtils.buildConvertFloat()));
		layout.get(0).getFields().add(new FieldLayout(21,26,"fieldNotGetMethod"," ",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(26,34,"numberInteger","0", ConverterUtils.buildConvertInteger()));
		layout.get(0).getFields().add(new FieldLayout(40,50,"data"," ",ConverterUtils.buildConvertDate(new SimpleDateFormat("dd/MM/yyyy"))));
		
		
		C3POLineConverter c = new C3POLineConverter(layout);
		
		String[] array = new String[8];
		array[0] = "1";
		array[1] = "NOME_CLIENTE";
		array[2] = "1255";
		array[3] = "VALUE";
		array[4] = "1.4";
		array[5] = "VALUE";
		array[6] = "40005";
		array[7] = "14/12/2010";
		
		String line = c.convertToLine(array);
    
		assertTrue(line.equals("1NOME_CLIENTE00001255VALUE000400050001.414/12/2010"));
		
		layout.add( new Layout(0,2,"00",MockHeaderClass.class));//Começa com 00 utilizará este layout
		layout.get(1).getFields().add(new FieldLayout(2,21,"name","I",TypePad.LEFT_PAD,ConverterUtils.buildConvertString()));
		layout.get(1).getFields().add(new FieldLayout(21,31,"other","X",TypePad.RIGHT_PAD,ConverterUtils.buildConvertString()));

		array = new String[3];
		array[0] = "00";
		array[1] = "       HEADERheaderHEADER   ";
		array[2] = " OTHER  ";
			
		
		line = c.convertToLine(array);
								
		assertTrue(line.equals("00IHEADERheaderHEADEROTHERXXXXX"));
	
	}

	@Test 
	public void testConvertToLine(){
		List<Layout> layout = new ArrayList<Layout>(8);
		layout.add( new Layout(0,1,"1", MockClass.class));//Começa com 1 utilizará este layout
		layout.get(0).getFields().add(new FieldLayout(1,13,"name"," ",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(13,21,"id","0",ConverterUtils.buildConvertLong()));
		layout.get(0).getFields().add(new FieldLayout(21,26,"nameNull"," ",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(34,40,"numberFloat","0",ConverterUtils.buildConvertFloat()));
		layout.get(0).getFields().add(new FieldLayout(21,26,"fieldNotGetMethod"," ",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(26,34,"numberInteger","0", ConverterUtils.buildConvertInteger()));
		layout.get(0).getFields().add(new FieldLayout(40,50,"data"," ",ConverterUtils.buildConvertDate(new SimpleDateFormat("dd/MM/yyyy"))));
		
		
		C3POLineConverter c = new C3POLineConverter(layout);
		
		MockClass m = new MockClass();
		m.setName("NOME_CLIENTE");
		m.setId(1255L);
		m.setNameNull("VALUE");
		m.setNumberFloat((float)1.4);
		m.fieldNotGetMethod = "VALUE";
		m.setNumberInteger((int)40005);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, (12-1), 14, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		m.setData(calendar.getTime());
		
		
		String line = c.convertToLine(m);

		assertTrue(line.equals("1NOME_CLIENTE00001255VALUE000400050001.414/12/2010"));

		//########### TESTE2
		
		layout.clear();
		layout.add( new Layout(0,1,"9",MockClass.class));//Começa com 9 utilizará este layout
		layout.get(0).getFields().add(new FieldLayout(false,10,22,"name"," ",TypePad.LEFT_PAD,ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(false,2,10,"id","0",TypePad.LEFT_PAD,ConverterUtils.buildConvertLong()));
		layout.get(0).getFields().add(new FieldLayout(false,22,27,"nameNull"," ",TypePad.LEFT_PAD,ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(false,35,41,"numberFloat","0",TypePad.LEFT_PAD,ConverterUtils.buildConvertFloat()));
		layout.get(0).getFields().add(new FieldLayout(false,22,27,"fieldNotGetMethod"," ",TypePad.LEFT_PAD,ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(false,27,35,"numberInteger","0",TypePad.LEFT_PAD,ConverterUtils.buildConvertInteger()));
		layout.get(0).getFields().add(new FieldLayout(false,41,51,"data"," ",TypePad.LEFT_PAD,ConverterUtils.buildConvertDate(new SimpleDateFormat("dd/MM/yyyy"))));
				 
		line = c.convertToLine(m);
		
		assertTrue(line.equals("900001255NOME_CLIENTEVALUE000400050001.414/12/2010"));
		
	//########### TESTE3
		
		layout.clear();	 
		layout.add( new Layout(0,1,"$",MockHeaderClass.class));//Começa com $ utilizará este layout
		layout.get(0).getFields().add(new FieldLayout(1,20,"name","I",TypePad.LEFT_PAD,ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(20,30,"other","X",TypePad.RIGHT_PAD,ConverterUtils.buildConvertString()));

		layout.add( new Layout(0,1,"1",MockClass.class));//Começa com 1 utilizará este layout
		layout.get(1).getFields().add(new FieldLayout(false,10,22,"name"," ",TypePad.LEFT_PAD,ConverterUtils.buildConvertString()));
		layout.get(1).getFields().add(new FieldLayout(false,2,10,"id","0",TypePad.LEFT_PAD,ConverterUtils.buildConvertLong()));
		layout.get(1).getFields().add(new FieldLayout(false,22,27,"nameNull"," ",TypePad.LEFT_PAD,ConverterUtils.buildConvertString()));
		layout.get(1).getFields().add(new FieldLayout(false,35,41,"numberFloat","0",TypePad.LEFT_PAD,ConverterUtils.buildConvertFloat()));
		layout.get(1).getFields().add(new FieldLayout(false,22,27,"fieldNotGetMethod"," ",TypePad.LEFT_PAD,ConverterUtils.buildConvertString()));
		layout.get(1).getFields().add(new FieldLayout(false,27,35,"numberInteger","0",TypePad.LEFT_PAD,ConverterUtils.buildConvertInteger()));
		layout.get(1).getFields().add(new FieldLayout(false,41,51,"data"," ",TypePad.LEFT_PAD,ConverterUtils.buildConvertDate(new SimpleDateFormat("dd/MM/yyyy"))));
			
		MockHeaderClass header = new MockHeaderClass();
		header.setName("       HEADERheaderHEADER   ");
		header.setOther(" OTHER ");
		
		line = c.convertToLine(header);
		
		assertTrue(line.equals("$IHEADERheaderHEADEROTHERXXXXX"));
		
		line = c.convertToLine(m);
		
		assertTrue(line.equals("100001255NOME_CLIENTEVALUE000400050001.414/12/2010"));
		
//########### TESTE4
		
		layout.clear();	 
		layout.add( new Layout(0,1,"$",MockHeaderClass.class));//Começa com $ utilizará este layout
		layout.get(0).getFields().add(new FieldLayout(true,1,20,"name","I",TypePad.LEFT_PAD,ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(true,20,30,"other","X",TypePad.RIGHT_PAD,ConverterUtils.buildConvertString()));

		line = c.convertToLine(header);

		assertTrue(line.equals("$IHEADERheaderHEADEROTHERXXXXX"));

	}
	
	@Test
	public void testConvertToObject() {
		
		String row = "1NOME_CLIENTE00001255VALUE000400050001.414/12/2010";
		//Começando a contagem com 0
		//NOME_CLIENTE = 0 - 12
		//00001255 = 12 - 20
		//VALUE = 20 - 25
		//0004 = 25 - 29
		//0005 = 29 - 34
		//0001.4 = 33 - 39
		//14/12/2010 = 39 - 50
		
		List<Layout> layout = new ArrayList<Layout>(8);
		layout.add( new Layout(0,1,"1", MockClass.class));//Começa com 1 utilizará este layout
		layout.get(0).getFields().add(new FieldLayout(1,13,"name",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(13,21,"id",ConverterUtils.buildConvertLong()));
		layout.get(0).getFields().add(new FieldLayout(21,26,"nameNull",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(34,40,"numberFloat",ConverterUtils.buildConvertFloat()));
		layout.get(0).getFields().add(new FieldLayout(21,26,"fieldNotGetMethod",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(26,37,"numberInteger",ConverterUtils.buildConvertInteger()));
		layout.get(0).getFields().add(new FieldLayout(40,50,"data",ConverterUtils.buildConvertDate(new SimpleDateFormat("dd/MM/yyyy"))));
		
		
		C3POLineConverter c = new C3POLineConverter(layout);
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.set(2010, (12-1), 14, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			
			MockClass resp = (MockClass) c.convertToObject(row);
			
			assertTrue(resp.getName().equals("NOME_CLIENTE"));
			assertTrue(resp.getId() == 1255L);
			assertTrue(resp.getNameNull().equals("VALUE"));
			assertTrue(resp.getNumberFloat()==(float)1.4);
			assertTrue(resp.fieldNotGetMethod.equals("VALUE"));
			assertTrue(resp.getNumberInteger() == (int)40005000);
			assertTrue(resp.getData().compareTo(calendar.getTime()) == 0);
			
			
			
		} catch (ConverterException e) {
			e.printStackTrace();
			fail("Erro inesperado.");
			
		}
		
		
		//Começando a contagem com 1
		//NOME_CLIENTE = 0 - 13
		//00001255 = 13 - 21
		//VALUE = 21 - 26
		//0004 = 26 - 30
		//0005 = 30 - 34
		//0001.4 = 34 - 40
		//14-12-2010 = 40 - 51

		layout = new ArrayList<Layout>(8);
		layout.add( new Layout(0,1,"1",MockClass.class));//Começa com 1 utilizará este layout
		layout.get(0).getFields().add(new FieldLayout(false,2,14,"name",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(false,14,22,"id",ConverterUtils.buildConvertLong()));
		layout.get(0).getFields().add(new FieldLayout(false,22,27,"nameNull",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(false,35,41,"numberFloat",ConverterUtils.buildConvertFloat()));
		layout.get(0).getFields().add(new FieldLayout(false,22,27,"fieldNotGetMethod",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(false,27,38,"numberInteger",ConverterUtils.buildConvertInteger()));
		layout.get(0).getFields().add(new FieldLayout(false,41,51,"data",ConverterUtils.buildConvertDate(new SimpleDateFormat("dd/MM/yyyy"))));
		
		
		c = new C3POLineConverter(layout);
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.set(2010, (12-1), 14, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			
			MockClass resp = (MockClass)c.convertToObject(row);
			
			assertTrue(resp.getName().equals("NOME_CLIENTE"));
			assertTrue(resp.getId() == 1255L);
			assertTrue(resp.getNameNull().equals("VALUE"));
			assertTrue(resp.getNumberFloat()==(float)1.4);
			assertTrue(resp.fieldNotGetMethod.equals("VALUE"));
			assertTrue(resp.getNumberInteger() == (int)40005000);
			assertTrue(resp.getNumberInteger() == (int)40005000);
			assertTrue(resp.getData().compareTo(calendar.getTime()) == 0);
			
			
			
			
		} catch (ConverterException e) {
			e.printStackTrace();
			fail("Erro inesperado.");
			
		}
		
		row = "900001255NOME_CLIENTEVALUE000400050001.414/12/2010";
		layout.add( new Layout(0,1,"9",MockClass.class));//Começa com 1 utilizará este layout
		layout.get(1).getFields().add(new FieldLayout(false,10,22,"name",ConverterUtils.buildConvertString()));
		layout.get(1).getFields().add(new FieldLayout(false,2,10,"id",ConverterUtils.buildConvertLong()));
		layout.get(1).getFields().add(new FieldLayout(false,22,27,"nameNull",ConverterUtils.buildConvertString()));
		layout.get(1).getFields().add(new FieldLayout(false,35,41,"numberFloat",ConverterUtils.buildConvertFloat()));
		layout.get(1).getFields().add(new FieldLayout(false,22,27,"fieldNotGetMethod",ConverterUtils.buildConvertString()));
		layout.get(1).getFields().add(new FieldLayout(false,27,35,"numberInteger",ConverterUtils.buildConvertInteger()));
		layout.get(1).getFields().add(new FieldLayout(false,41,51,"data",ConverterUtils.buildConvertDate(new SimpleDateFormat("dd/MM/yyyy"))));
				 
		 try {
			 
				Calendar calendar = Calendar.getInstance();
				calendar.set(2010, (12-1), 14, 0, 0, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				
				MockClass resp = (MockClass)c.convertToObject(row);
				c = new C3POLineConverter(layout);
				
				assertTrue(resp.getName().equals("NOME_CLIENTE"));
				assertTrue(resp.getId() == 1255L);
				assertTrue(resp.getNameNull().equals("VALUE"));
				assertTrue(resp.getNumberFloat()==(float)1.4);
				assertTrue(resp.fieldNotGetMethod.equals("VALUE"));
				assertTrue(resp.getNumberInteger() == (int)40005);
				assertTrue(resp.getData().compareTo(calendar.getTime()) == 0);
				
	
			} catch (ConverterException e) {
				e.printStackTrace();
				fail("Erro inesperado.");
				
			}
		
		
	}
	
	

}

class MockHeaderClass{
	private String name;
	private String other;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}	
}

class MockClass{
	private long id;
	private String name;
	private String nameNull = "it a value, not null.";
	private BigDecimal value;
	private short numberShort;
	private Integer numberInteger;
	public Float numberFloat;
	protected boolean boo;
	private MockClass mockClass;
	public String fieldNotGetMethod;
	public Date data;

	public MockClass(){
	}
	public MockClass(long id){
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public short getNumberShort() {
		return numberShort;
	}
	public void setNumberShort(short numberShort) {
		this.numberShort = numberShort;
	}
	public Integer getNumberInteger() {
		return numberInteger;
	}
	public void setNumberInteger(Integer numberInteger) {
		this.numberInteger = numberInteger;
	}
	public Float getNumberFloat() {
		return numberFloat;
	}
	public void setNumberFloat(Float numberFloat) {
		this.numberFloat = numberFloat;
	}
	public boolean isBoo() {
		return boo;
	}
	public void setBoo(boolean boo) {
		this.boo = boo;
	}
	public boolean getBoo() {
		return boo;
	}

	public MockClass getMockClass() {
		return mockClass;
	}

	public void setMockClass(MockClass mockClass) {
		this.mockClass = mockClass;
	}

	public String getNameNull() {
		return nameNull;
	}

	public void setNameNull(String nameNull) {
		this.nameNull = nameNull;
	}

	public void setFieldNotGetMethod(String fieldNotGetMethod) {
		this.fieldNotGetMethod = fieldNotGetMethod;
	}
	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}
	

}

