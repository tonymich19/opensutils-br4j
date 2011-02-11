/*
 * 	 @(#)ReflectField.java	0.2 11/01/28
 * 
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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.opensutils.converter.Converter;
/**
 * The class <code>ReflectField</code> is a utility class for get/set a value information in a Field, of the any class.
 * @author Felipe Priuli
 * @version 0.1
 */
public class ReflectField {

	/**
	 * Get value of an Field through Java Reflection
	 * @param parentField - The parent class of Field
	 * @param fieldName - the name of a field to get a value
	 * @param converter - Converter to convert the result obtained from the field 
	 * @return the value object of Field
	 */
	public static Object getValue(Object parentField, String fieldName, Converter converter) {

		String[] fieldsNames = fieldName.split("[.]");
		Object obj = null;
		for (String fn : fieldsNames) {
			try{
				if(obj == null){
					obj = getValue(parentField, parentField.getClass().getDeclaredField(fn), null);
				}else{
					obj = getValue(obj, obj.getClass().getDeclaredField(fn), null);
				}
			}catch(NoSuchFieldException nsfE){
				throw new IllegalArgumentException("Not exist the field of name: '"+fn+"' in classe '"+(obj != null ? obj.getClass().getName() : parentField.getClass().getName()) ,nsfE);
			}
		}

		return converter != null ? converter.getAsString(obj) : obj;

	}
	/**
	 * Get value of an Field through Java Reflection
	 * @param parentField - The parent class of Field
	 * @param field - the java Field to get a value
	 * @param converter - Converter to convert the result obtained from the field 
	 * @return the value object of Field
	 */
	public static Object getValue(Object parentField, Field field, Converter converter){

		Object obj = null;
		try {
			if(Modifier.isPublic(parentField.getClass().getModifiers()) && Modifier.isPublic(field.getModifiers())){

				obj = field.get(parentField) ;

			}else{
				if(field.isAccessible()){
					obj = field.get(parentField) ;
				}else{
					field.setAccessible(true);
					obj = field.get(parentField) ;
					field.setAccessible(false);
				}	
			}

			return converter != null ? converter.getAsString(obj) : obj;
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Param 1 not is a parent of Field",e);
		}	
	}
	/**
	 * Set value of an Field through Java Reflection
	 * @param parentField - The parent class of Field
	 * @param fieldName - the name of Field to set a new value
	 * @param value - new value to set in Field 
	 * @return true - if sucess
	 */
	public static boolean setValue(Object parentField, String fieldName, Object value){

		String[] fieldsNames = fieldName.split("[.]");
		if(fieldsNames.length > 1){
			String penultimoField = "";
			for (int i = 0 ; i < (fieldsNames.length -1) ; i++) {
				penultimoField += fieldsNames[i];
			}
			Object obj = getValue(parentField,penultimoField, null);

			try{
				return setValue(obj, obj.getClass().getDeclaredField(fieldsNames[fieldsNames.length-1]), value);
			}catch(NoSuchFieldException nsfE){
				throw new IllegalArgumentException("Not exist the field of name: '"+fieldsNames[fieldsNames.length-1]+"' in classe '"+obj.getClass().getName(),nsfE);
			}
		}else{
			try{
				return setValue(parentField, parentField.getClass().getDeclaredField(fieldName), value);
			}catch(NoSuchFieldException nsfE){
				throw new IllegalArgumentException("Not exist the field of name: '"+fieldName+"' in classe '"+parentField.getClass().getName(),nsfE);
			}
		}
	}
	/**
	 * Set value of an Field through Java Reflection
	 * @param parentField - The parent class of Field
	 * @param field - the Java Field to set a new value
	 * @param value - new value to set in Field 
	 * @return true - if sucess
	 */
	public static boolean setValue(Object parentField, Field field, Object value){

		try {
			if(Modifier.isPublic(parentField.getClass().getModifiers()) && Modifier.isPublic(field.getModifiers())){

				field.set(parentField, value) ;
				return true;
			}else{
				if(field.isAccessible()){
					field.set(parentField, value) ;
					return true;
				}else{
					field.setAccessible(true);
					field.set(parentField, value) ;
					field.setAccessible(false);
					return true;
				}	
			}
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("There was an error to set the value in Field. " +
					"Make sure the first parameter is a parent of Field " +
					"and if the value is set to the same type of field.",e);
		}	
	}
}
