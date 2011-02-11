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

package org.opensutils.reflect;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReflectUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGenerateNameGetClass(){
		assertTrue( "Result incorrect. Name of class incorrect", ReflectUtils.generateNameGetClass(MockClasse.class).equals("mockClasse"));
		assertTrue( "Result incorrect. Name of class incorrect", ReflectUtils.generateNameGetClass(ReflectUtilsTest.class).equals("reflectUtilsTest"));
	}
	
	@Test
	public void generateNameGetMethod(){
		assertTrue( "Result incorrect. Incorrect Name of get method", ReflectUtils.generateNameGetMethod("name").equals("getName"));
		assertTrue( "Result incorrect. Incorrect Name of get method", ReflectUtils.generateNameGetMethod("value").equals("getValue"));
		assertTrue( "Result incorrect. Incorrect Name of get method", ReflectUtils.generateNameGetMethod("myMethodTestJUni").equals("getMyMethodTestJUni"));

		try {
			MockClasse mockClass = new MockClasse();
			assertTrue( "Result incorrect. Incorrect Name of get method", ReflectUtils.generateNameGetMethod(mockClass.getClass().getField("fieldNotGetMethod")).equals("getFieldNotGetMethod"));
			assertTrue( "Result incorrect. Incorrect Name of get method", ReflectUtils.generateNameGetMethod(mockClass.getClass().getField("numberFloat")).equals("getNumberFloat"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("A error ocurred.");
		}
	}

	@Test
	public void generateNameSetMethod(){
		assertTrue( "Result incorrect. Incorrect Name of set method", ReflectUtils.generateNameSetMethod("name").equals("setName"));
		assertTrue( "Result incorrect. Incorrect Name of set method", ReflectUtils.generateNameSetMethod("value").equals("setValue"));
		assertTrue( "Result incorrect. Incorrect Name of set method", ReflectUtils.generateNameSetMethod("myMethodTestJUni").equals("setMyMethodTestJUni"));
		
		try {
			MockClasse mockClass = new MockClasse();
			assertTrue( "Result incorrect. Incorrect Name of set method", ReflectUtils.generateNameSetMethod(mockClass.getClass().getField("fieldNotGetMethod")).equals("setFieldNotGetMethod"));
			assertTrue( "Result incorrect. Incorrect Name of set method", ReflectUtils.generateNameSetMethod(mockClass.getClass().getField("numberFloat")).equals("setNumberFloat"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("A error ocurred.");
		}
	}
	
	@Test
	public void testClone(){
		MockClasse mockClass = new MockClasse();
		mockClass.setBoo(true);
		mockClass.setId(789456123L);
		mockClass.setName("MockClass JUnit Test");
		mockClass.setNumberFloat(new Float(2));
		mockClass.setNumberInteger(74411);
		mockClass.setNumberShort( new Short((short)1));
		mockClass.setValue(new BigDecimal(22225));
		mockClass.setMockClass(new MockClasse() );
		mockClass.setNameNull(null);
		mockClass.setFieldNotGetMethod("teste");
		try {
			MockClasse result = ReflectUtils.clone(MockClasse.class, mockClass);
			if(result == mockClass){
				fail("result can not be the same instance that MockClass");
			}
			
			assertTrue( "wrapper primitive, result incorrect. ",result.isBoo() == true );
			assertTrue( "wrapper primitive, result incorrect. ",result.getId()== 789456123L );
			assertTrue( "wrapper primitive, result incorrect. ",result.getName().equals("MockClass JUnit Test") );
			assertTrue( "wrapper primitive, result incorrect. ",result.getNumberFloat() == mockClass.getNumberFloat() );
			assertTrue( "wrapper primitive, result incorrect. ",result.getNumberFloat().equals(new Float(2)) );
			assertTrue( "wrapper primitive, result incorrect. ",result.getNumberInteger().equals(74411) );
			assertTrue( "wrapper primitive, result incorrect. ",result.getNumberShort() ==  new Short((short)1));
			assertTrue( "wrapper primitive, result incorrect. ",result.getValue().equals(mockClass.getValue()));
			assertTrue( "wrapper primitive, result incorrect. ",result.getMockClass() == mockClass.getMockClass());
			assertTrue( "wrapper primitive, result incorrect. ",result.getNameNull() == null);
			assertTrue( "wrapper primitive, result incorrect. ",result.fieldNotGetMethod.equals("teste"));
			
			
		} catch (Exception e) {
			//e.printStackTrace();
			fail("A error ocurred.");
		}
		
	}

	@Test
	public void testToString(){
		MockClasse mockClass = new MockClasse();
		mockClass.setBoo(true);
		mockClass.setId(789456123L);
		mockClass.setName("MockClasse JUnit Test");
		mockClass.setNumberFloat(new Float(2));
		mockClass.setNumberInteger(74411);
		mockClass.setNumberShort( new Short((short)1));
		mockClass.setValue(new BigDecimal(22225));
		mockClass.setMockClass(new MockClasse() );
		mockClass.setNameNull(null);
		mockClass.setFieldNotGetMethod("teste");
		
		String resp = ReflectUtils.toString(mockClass, true);
		//System.out.println(resp);
		//System.out.println(mockClass.toString());
		assertTrue( "result incorrect. " , 
					resp.equals("MockClasse [id=789456123, name=MockClasse JUnit Test, nameNull=null, value=22225, numberShort=1, numberInteger=74411, numberFloat=2.0, boo=true, mockClass=MockClasse [id=0, name=null, nameNull=it a value, not null., value=null, numberShort=0, numberInteger=null, numberFloat=null, boo=false, mockClass=null, fieldNotGetMethod=null], fieldNotGetMethod=teste]")
				  );
		
		resp = ReflectUtils.toString(mockClass, false);
		assertTrue( "result incorrect. " , 
					resp.equals("MockClasse [id=789456123, name=MockClasse JUnit Test, nameNull=null, value=22225, numberShort=1, numberInteger=74411, numberFloat=2.0, boo=true, fieldNotGetMethod=teste]")
			  	   );
		//System.out.println(resp);
		//System.out.println(mockClass.toString());

	}
	
	@Test
	public void testGetValuesInCollection(){
		List<MockClasse> list = new ArrayList<MockClasse>();
		list.add( new MockClasse("open"));
		list.add( new MockClasse("sutils"));
		list.add( new MockClasse("br"));
		list.add( new MockClasse("4J"));
		
		List<Object> resultList = ReflectUtils.getValuesInCollection(list,"name");
		
		assertTrue( "result incorrect. " , ((String) resultList.get(0)).equals("open") );
		assertTrue( "result incorrect. " , ((String) resultList.get(1)).equals("sutils") );
		assertTrue( "result incorrect. " , ((String) resultList.get(2)).equals("br") );
		assertTrue( "result incorrect. " , ((String) resultList.get(3)).equals("4J") );
		
	}
	
	@Test
	public void testToGetIdsCollection(){
		List<MockClasse> list = new ArrayList<MockClasse>();
		list.add( new MockClasse(1L));
		list.add( new MockClasse(2L));
		list.add( new MockClasse(3L));
		list.add( new MockClasse(4L));
		
		List<Object> resultList = ReflectUtils.getIdsFieldInCollection(list);
		
		assertTrue( "result incorrect. " , ((Long) resultList.get(0)) == 1L );
		assertTrue( "result incorrect. " , ((Long) resultList.get(1)) == 2L );
		assertTrue( "result incorrect. " , ((Long) resultList.get(2)) == 3L );
		assertTrue( "result incorrect. " , ((Long) resultList.get(3)) == 4L );
		
	}
	
	@Test
	public void testToIsWrapperPrimitive(){
		
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive(new Long(1)) );
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive(new Integer(1)) );
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive(new Boolean(false)) );
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive(new Short((short)1)) );
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive(new Double(100.5)) );
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive(new BigDecimal(1)) );
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive(new String("teste") ));
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive(new Float(1.2)) );
		
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive((long)1) );
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive((int)1) );
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive(true) );
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive((short)1) );
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive((double)100.5) );
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive("teste" ));
		assertTrue( "wrapper primitive, result incorrect. ",ReflectUtils.isWrapperPrimitive((float)1.2) );
	}
	

	public void testEmptyContructor(){
		fail("Não implementado");
	}
}

class MockClasse{
	private long id;
	private String name;
	private String nameNull = "it a value, not null.";
	private BigDecimal value;
	private short numberShort;
	private Integer numberInteger;
	public Float numberFloat;
	protected boolean boo;
	private MockClasse mockClass;
	public String fieldNotGetMethod;

	public MockClasse(){
	}
	public MockClasse(long id){
		this.id = id;
	}
	public MockClasse(String name){
		this.name = name;
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

	public MockClasse getMockClass() {
		return mockClass;
	}

	public void setMockClass(MockClasse mockClass) {
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

}
