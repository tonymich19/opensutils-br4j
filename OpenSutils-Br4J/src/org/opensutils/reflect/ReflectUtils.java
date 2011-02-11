/*
 * 	 @(#)ReflectUtils.java	0.6 11/02/08
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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
/**
 * <code>ReflectUtils</code> is class is to abstract the creation of complex reflection.
 * It is a utility class for get information of objects using reflection.
 * @author Felipe Priuli
 * @version 0.6
 *
 */
public final class ReflectUtils {

	/**
	 * Copies information from one object to another new object by cloning the
	 * public fields and private fields that have protected or gets and sets
	 * @param entityClass - the type of class
	 * @param obj - the Object to clone
	 * @return new cloned object
	 * @throws InstantiationException, SecurityException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T clone(	Class<T> entityClass,
								final T obj
							  ) throws InstantiationException, SecurityException{
		
		try{

			Constructor constructor = getEmptyContructor(entityClass);
			if(constructor == null)
				throw new InstantiationException("No default contructor for class '"+entityClass.getName()+"'");

			T objClone = (T) constructor.newInstance(new Object[0]);
			Field[] fields = objClone.getClass().getDeclaredFields();
			
			for (Field field : fields) {					
				ReflectField.setValue(objClone, field, ReflectField.getValue(obj, field.getName(), null));
			}

			return objClone;

		}catch( IllegalAccessException illegalAcess){
			throw new InstantiationException(illegalAcess.getMessage());
		}catch(InvocationTargetException ivTE){
			throw new InstantiationException(ivTE.getMessage());
		}
	}

	/**
	 * Generates the name of a class
	 * @return string of get class
	 */
	public static String generateNameGetClass(Class<?> c){
		String className = c.getSimpleName();
		return className.substring(0,1).toLowerCase() + className.substring(1); 
	}

	/**
	 * Generates the name of a get method
	 * @return string of get method
	 */
	public static String generateNameGetMethod(Field field){
		return generateNameGetMethod(field.getName());
	}
	/**
	 * Generates the name of a get method
	 * @return string of get method
	 */
	public static String generateNameGetMethod(String fieldName){
		return "get"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	/**
	 * Generates the name of a set method
	 * @return string of set method
	 */
	public static String generateNameSetMethod(Field field){
		return generateNameSetMethod(field.getName());
	}
	/**
	 * Generates the name of a set method
	 * @return string of set method
	 */
	public static String generateNameSetMethod(String fieldName){
		return "set"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	/**
	 * Scans the visible fields from object and returns the values and field names in a string<br>
	 * This method not use set method os a obj.
	 * @param obj - object to a scan field
	 * @param recursive - true to scan the objects within the object - false - to scan first leval field.
	 * @return the values and field names in unique String
	 */
	public static String toString(final Object obj, boolean recursive){
		return toString(obj, recursive, false);
	}

	/**
	 * Scans the visible fields from object and returns the values and field names in a string<br>
	 * This method not use set method os a obj.
	 * @param obj - object to a scan field
	 * @param recursive - true to scan the objects within the object - false - to scan first leval field.
	 * @param ignoreCollection - ignores the scan list
	 * @return the values and field names in unique String
	 */
	public static String toString(final Object obj,
								  boolean recursive, boolean ignoreCollection){
		
		String igual = "=", separador = ", ";
		StringBuilder resultToString = new StringBuilder();
		
		try {
			Class<?> cls = obj.getClass();  					         
	        Field fieldlist[] = cls.getDeclaredFields();
	        resultToString.append(obj.getClass().getSimpleName()+ " [");    
	        for (int i = 0; i < fieldlist.length; i++) {  
	            Field fld = fieldlist[i];

	            try{
	            	Object resultMethod = ReflectField.getValue(obj, fld.getName(), null);
	            	
	            	if (resultMethod instanceof java.util.Collection<?> && !ignoreCollection){
	            		resultToString.append( fld.getName() +igual+"[");
	            		if(!((Collection<?>)resultMethod).isEmpty()){
		            		for(Object objColl :(Collection<?>)resultMethod){
		            			resultToString.append(toString(objColl,false,ignoreCollection)+",");
		            		}
		            		resultToString.replace(resultToString.length()-1, resultToString.length(), "");
		            	}
	            		resultToString.append("]");
	            	
	            	}else if (isWrapperPrimitive(resultMethod) || (resultMethod == null) ){
	            		resultToString.append( fld.getName() +igual+ String.valueOf(resultMethod) + (i == fieldlist.length ? "" : separador));

	            	}else if(recursive){//chama recursiva para objetor os objetos de segundo nivel
	            		resultToString.append( fld.getName() +igual );
	            		resultToString.append( toString(resultMethod, true, ignoreCollection) + (i == fieldlist.length ? "" : separador));
	            	}

	            }catch(Exception e){}
	        }  	    
		}catch (Throwable e) {  
            System.err.println(e);  
        }
		 resultToString.append("]");
        return resultToString.toString().replace(", ]", "]");		
	}

	/**
	 * Traverses the list and get all the fields named id, ID
	 * @return List of ids value field.
	 */
	public static List<Object> getIdsFieldInCollection(Collection<?> collection){
		return getValuesInCollection(collection, "id");
	}
	
	/**
	 * Traverses the list and get all the value fields named for 'fieldName'
	 * @param collection - list to get val
	 * @param fieldName - the names of field to get a value
	 * @return List of ids value field.
	 */
	public static List<Object> getValuesInCollection(Collection<?> collection, String fieldName){	
		List<Object> ids = new ArrayList<Object>();
		try {
		
			  Iterator<?> iterator = collection.iterator();
		      while (iterator.hasNext()) {
		    	  Object obj = iterator.next();
		    	  Class<? extends Object> cls = obj.getClass();  					         
			      Field fieldlist[] = cls.getDeclaredFields();
			      
			      for (int i = 0; i < fieldlist.length; i++) {  
		            Field fld = fieldlist[i];  
		            if (fld.getName().equalsIgnoreCase(fieldName)){		
		            	ids.add( 	ReflectField.getValue( obj, fld.getName(), null) ) ;
		            }
			      }
		      }
	      
		}catch (Throwable t){
		}
	     return ids;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Get the constructor without parameters.
	 * @return the constructor if Constructor.getParameterTypes().length == 0
	 */
	public static Constructor getEmptyContructor(Class _class){
		Constructor[] construtores = _class.getConstructors();
	
		Constructor constructor = null;
		for (Constructor c : construtores) {
			if(c.getParameterTypes().length == 0){
				constructor = c;
				break;
			}
		}
		return constructor;

	}
	
	/**
	 * Checks if the object parameter is received at a variable of type primitive or primitive wrapper
	 * @param obj - objet fo cheks
	 * @return 	true to a primitive value or wrapper primitive value. <br>
	 * 			false - to not a primitive value or wrapper primitive
	 */
	public static boolean isWrapperPrimitive(Object obj){
		if (obj instanceof Number) 
			return true;
		else if (obj instanceof String)
			return true;		
		else if (obj instanceof Boolean)
			return true;	
		else if (obj instanceof Date)
			return true;	
		else if (obj instanceof Calendar)
			return true;	
		else 
			return false;
	}

}
