/*
 * 	 @(#)ReflectUtils.java	0.2 10/11/22
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
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
 * @version 0.4
 *
 */
public class ReflectUtils {

	
	@SuppressWarnings(value={"unchecked","rawtypes"})
	/**
	 * Copies information from one object to another new object by cloning the
	 * public fields and private fields that have protected or gets and sets
	 * @param entityClass - the type of class
	 * @param obj - the Object to clone
	 * @return new cloned object
	 * @throws InstantiationException, SecurityException
	 */
	public static <T> T clone(	Class<T> entityClass,
								final T obj
							  ) throws InstantiationException, SecurityException{
		
		try{
			
			Constructor[] construtores = Class.forName(entityClass.getName()).getConstructors();
			Constructor constructor = null;
			for (Constructor c : construtores) {
				if(c.getParameterTypes().length == 0){
					constructor = c;
					break;
				}
			}
			if(constructor == null)
				throw new InstantiationException("No default contructor for class '"+entityClass.getName()+"'");

			T objClone = (T) constructor.newInstance(new Object[0]);
			Field[] fields = objClone.getClass().getDeclaredFields();
			for (Field field : fields) {
				try{
					if(Modifier.isPublic(field.getModifiers())){
						field.set(objClone, obj.getClass().getField(field.getName()).get(obj) );
						
					}else{
						Method getMethod = obj.getClass().getMethod(	generateNameGetMethod(field.getName()),
																		new Class[0] );
						if(Modifier.isPublic(getMethod.getModifiers())){
							Object result = getMethod.invoke(obj, new Object[0]);
	
							Method setMethod = objClone.getClass().getMethod(	generateNameSetMethod(field.getName()),
																				field.getType() );
	
							setMethod.invoke(objClone, new Object[]{result});
						}
					}
					
				}catch(NoSuchMethodException nsmE){}//Method not found(no java beans field)
				catch(IllegalArgumentException ilaE){}
				catch(InvocationTargetException ivTE){}
				catch( IllegalAccessException illegalAcess){} 
				catch (NoSuchFieldException e) {}
				catch(SecurityException se){}
			}

			return objClone;

		}catch(ClassNotFoundException cnfE){//ignoring. no give this exception
			throw new InstantiationException("Class not found. "+cnfE.getMessage());
		}catch( IllegalAccessException illegalAcess){
			throw new InstantiationException(illegalAcess.getMessage());
		}catch(InvocationTargetException ivTE){
			throw new InstantiationException(ivTE.getMessage());
		}
	}
	
	@SuppressWarnings("rawtypes")
	/**
	 * Generates the name of a class
	 * @return string of get class
	 */
	public static String generateNameGetClass(Class c){
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
	
	@SuppressWarnings("rawtypes")
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
			Class cls = obj.getClass();  					         
	        Field fieldlist[] = cls.getDeclaredFields();
	        resultToString.append(obj.getClass().getSimpleName()+ " [");    
	        for (int i = 0; i < fieldlist.length; i++) {  
	            Field fld = fieldlist[i];

	            try{
	            	Object resultMethod = null;
	            	if(Modifier.isPublic(fld.getModifiers())){
	            		resultMethod = obj.getClass().getField(fld.getName()).get(obj) ;

	            	}else{
	            		Method getMethod = obj.getClass().getMethod(generateNameGetMethod(fld.getName()),new Class[0] );	            
	            		resultMethod = getMethod.invoke(obj, new Object[0]);
	            	}

	            	if (resultMethod instanceof java.util.Collection && !ignoreCollection){
	            		resultToString.append( fld.getName() +igual+"[");
	            		if(!((Collection)resultMethod).isEmpty()){
		            		for(Object objColl :(Collection)resultMethod){
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

	            }catch(NoSuchMethodException nsmE){}//Method not found(no java beans field)
	            catch(Exception e){}
	        }  	    
		}catch (Throwable e) {  
            System.err.println(e);  
        }
		 resultToString.append("]");
        return resultToString.toString().replace(", ]", "]");		
	}

	@SuppressWarnings("rawtypes")
	/**
	 * Traverses the list and get all the fields named id, ID
	 * @return List of ids value field.
	 */
	public static List<Object> getIdsFieldInCollection(Collection collection){	
		List<Object> ids = new ArrayList<Object>();
		try {
		
			  Iterator iterator = collection.iterator();
		      while (iterator.hasNext()) {
		    	  Object obj = iterator.next();
		    	  Class cls = obj.getClass();  					         
			      Field fieldlist[] = cls.getDeclaredFields();
			      
			      for (int i = 0; i < fieldlist.length; i++) {  
		            Field fld = fieldlist[i];  
		            if (fld.getName().equalsIgnoreCase("id")){		
		            	
		            	if(Modifier.isPublic(fld.getModifiers())){
		            		ids.add( obj.getClass().getField(fld.getName()).get(obj)) ;
		            	}else{
			            	Method getMethod = obj.getClass().getMethod(generateNameGetMethod(fld.getName()),new Class[0] );
			            	if(Modifier.isPublic(getMethod.getModifiers()))
			            		ids.add(getMethod.invoke(obj, new Object[0]));
		            	}
		            }
			      }
		      }
	      
		}catch (Throwable t){
			System.err.println(t);  
		}
	     return ids;
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
