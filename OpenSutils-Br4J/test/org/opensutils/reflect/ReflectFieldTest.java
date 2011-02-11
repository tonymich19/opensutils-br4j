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

import org.junit.Before;
import org.junit.Test;
/**
 * <code>TestReflectField</code> is test class for Test Driven Development (TDD)
 * @author Felipe Priuli
 */
public class ReflectFieldTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetValueFieldPrivate() {

		try{
			Car c = new Car("ford", 2, false);

			Object obj = ReflectField.getValue( c ,c.getClass().getDeclaredField("model"), null);

			assertTrue( obj instanceof String);
			assertTrue( (obj).equals("ford") );

		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}
	}
	@Test
	public void testGetValueFieldProtected() {
		try{
			Car c = new Car("ford", 2, false);

			Object obj = ReflectField.getValue( c ,c.getClass().getDeclaredField("flag"), null);

			assertTrue( obj instanceof Boolean);
			assertTrue( obj.equals(false) );

		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}
	}
	@Test
	public void testGetValueFieldPublic() {
		try{
			Car c = new Car("ford", 2, false);
			c.valShort = 55;
			Object obj = ReflectField.getValue( c ,c.getClass().getDeclaredField("valShort"), null);

			assertTrue( obj instanceof Short);
			assertTrue( obj.equals((short)55) );

		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}

	}
	@Test
	public void testGetValueStringFieldPrivate() {
		try{
			Car c = new Car("ford", 222, true);

			Object obj = ReflectField.getValue( c ,"number", null);

			assertTrue( obj instanceof Integer);
			assertTrue( (obj).equals(222) );
		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}
	}
	@Test
	public void testGetValueStringFieldProtected() {
		try{
			Car c = new Car("ford", 2, true);
			Object obj = ReflectField.getValue( c ,"flag", null);

			assertTrue( obj instanceof Boolean);
			assertTrue( obj.equals(true) );

		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}
	}
	@Test
	public void testGetValueStringFieldPublic() {
		try{
			Car c = new Car("ford", 2, true);
			c.valShort = 864;
			Object obj = ReflectField.getValue( c ,"valShort", null);

			assertTrue( obj instanceof Short);
			assertTrue( obj.equals((short)864) );

		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}
	}
	@Test
	public void testGetValueFieldPublicMultiple() {
		try{
			Car c = new Car("", 0, false);
			c.engine = new Engine(280,"V6",6L);

			Object value = ReflectField.getValue( c , "engine.name",null);

			assertTrue( value.equals("V6"));

			value = ReflectField.getValue( c , "engine.hp",null);
			assertTrue( value.equals(280));

			value = ReflectField.getValue( c , "engine.march",null);
			assertTrue( value.equals(6L));

		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}
	}


	@Test
	public void testSetValueFieldPrivate() {

		try{
			Car c = new Car("", 0, false);

			assertTrue(ReflectField.setValue( c ,c.getClass().getDeclaredField("model"), "myModel"));

			assertTrue( c.toString().equals("model=myModel;number=0;flag=false;valShort=null"));

		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}
	}

	@Test
	public void testSetValueFieldPrivateInt() {

		try{
			Car c = new Car("", 0, false);

			assertTrue(ReflectField.setValue( c ,c.getClass().getDeclaredField("number"), 9852));

			assertTrue( c.toString().equals("model=;number=9852;flag=false;valShort=null"));

		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}
	}

	@Test
	public void testSetValueFieldProtectedBoolean() {
		try{
			Car c = new Car("", 0, false);

			assertTrue(ReflectField.setValue( c ,c.getClass().getDeclaredField("flag"), true));

			assertTrue( c.toString().equals("model=;number=0;flag=true;valShort=null"));

		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}


	}

	@Test
	public void testSetValueStringFieldMultipleFields(){

		try{
			Car c = new Car("", 0, false);
			c.engine = new Engine(280,null,6L);

			assertTrue( ReflectField.setValue( c ,"engine.name", "V8"));

			assertTrue( c.toString().equals("model=;number=0;flag=false;valShort=null"));
			assertTrue( c.engine.toString().equals("hp=280;march=6;name=V8"));

			assertTrue(ReflectField.setValue( c ,"engine.hp", 320));

			assertTrue( c.toString().equals("model=;number=0;flag=false;valShort=null"));
			assertTrue( c.engine.toString().equals("hp=320;march=6;name=V8"));

			assertTrue(ReflectField.setValue( c ,"engine.march", 4L));

			assertTrue( c.toString().equals("model=;number=0;flag=false;valShort=null"));
			assertTrue( c.engine.toString().equals("hp=320;march=4;name=V8"));

		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}

	}

	@Test
	public void testSetValueStringFieldPrivateString(){
		try{
			Car c = new Car("", 0, false);

			assertTrue(ReflectField.setValue( c ,"model", "myModel"));

			assertTrue( c.toString().equals("model=myModel;number=0;flag=false;valShort=null"));

		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}

	}

	@Test
	public void testSetValueStringFieldProtectedLong(){
		try{
			Car c = new Car("", 0, false);

			assertTrue(ReflectField.setValue( c ,"number", 9852));

			assertTrue( c.toString().equals("model=;number=9852;flag=false;valShort=null"));

		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}
	}
	@Test
	public void testSetValueStringFieldPublicBoolean(){
		try{
			Car c = new Car("", 0, false);

			assertTrue(ReflectField.setValue( c ,"flag", true));

			assertTrue( c.toString().equals("model=;number=0;flag=true;valShort=null"));

		}catch(Exception e){
			e.printStackTrace();
			fail("Error!!! "+e.getMessage());
		}

	}
	/**
	 * Mock class
	 * @author Felipe Priuli
	 */
	class Car{
		private String model;

		private int number;
		protected boolean flag;
		public Short valShort; 
		public Engine engine;

		public Car(String model, int number, boolean flag){
			this.flag = flag;
			this.model = model;
			this.number = number;
		}
		public String toString(){
			return "model="+model+";number="+number+";flag="+flag+";valShort="+valShort;
		}
	}
	class Engine {
		private int hp;
		public String name;
		protected Long march;
		Engine(int hp, String name, Long march){
			this.hp = hp;
			this.name = name;
			this.march = march;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "hp=" + hp + ";march=" + march + ";name=" + name;
		}

	}
}
