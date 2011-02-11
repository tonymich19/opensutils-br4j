/*
 * 	 @(#)C3POLineConverter.java	0.1 2010/12/14
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

package org.opensutils.converter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.opensutils.StringUtils;
import org.opensutils.converter.layout.FieldLayout;
import org.opensutils.converter.layout.Layout;
import org.opensutils.reflect.ReflectField;
import org.opensutils.reflect.ReflectUtils;

/**
 * <code>C3POLineReaderConverter</code>
 * Class responsible for converting a line into an object according to the criteria to separate the fields on the line and turn into an object.
 * You must pass the criteria for parsing the fields and turn it into an object
 * @author Felipe Priuli
 */
public class C3POLineConverter implements LineConverter {

	protected List<Layout> layoutList;

	public C3POLineConverter(List<Layout> layoutList){
		this.layoutList = layoutList;
	}
	
	/**
	 * Criteria to convert String line to Object
	 * @return the fieldLineList
	 */
	public List<Layout> getLayout() {
		return layoutList;
	}

	/**
	 * Criteria to convert String line to Object
	 * @param fieldLineList the fieldLineList to set
	 */
	public void setLayout(List<Layout> layoutList) {
		this.layoutList = layoutList;
	}


	public  List<Layout> getLayoutList(){
		if(this.layoutList == null){
			this.layoutList = new ArrayList<Layout>(2);
		}
		return this.layoutList;

	}

	@Override
	/**
	 * Convert the line to String array.
	 * The conversion is made through criteria of the fieldLineList.
	 */
	public String[] convertToArray(String row) throws ConverterException {
		
		List<Layout> criteriaList = this.getLayoutList();
		for (Layout layout : criteriaList) {
			if(row.substring(layout.getStart(),layout.getEnd()).equals(layout.getEquals())){
				return getAsArray(row, layout);
			}
		}
		
		throw new UnknownConverterException("Unable to identify Converter.");
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	/**
	 * Convert the line to as Object.
	 * The conversion is been a criteria of the fieldLineList.
	 */
	public Object convertToObject(String row)	throws ConverterException, UnknownConverterException {
		//
		List<Layout> layoutList = this.getLayoutList();
		for (Layout layout : layoutList) {
			if(row.substring(layout.getStart(),layout.getEnd()).equals(layout.getEquals())){
				return getAsObject(row, layout.getRepresentantionClass(), layout.getFields());
			}
		}
		
		throw new UnknownConverterException("Unable to identify Converter.");	
	}
	
	@Override
	/**
	 * Convert a Object to a line
	 * The conversion is been a criteria of the fieldLineList.
	 */
	public String convertToLine(Object obj) throws ConverterException, UnknownConverterException {

		List<Layout> layoutList = this.getLayoutList();
		for (Layout layout : layoutList) {
			if(obj.getClass().getName().equals(layout.getRepresentantionClass().getName())){
				return getAsString(	layout, 
									obj, 
									layout.getRepresentantionClass(), 
									layout.getFields()
								   );
			}
	
		}
		
		throw new UnknownConverterException("Unable to identify Converter.");	
	}
	
	@Override
	public String convertToLine(String[] arr) throws ConverterException,UnknownConverterException {
		
		StringBuilder sb = new StringBuilder();
		List<Layout> layoutList = this.getLayoutList();
		for (Layout layout : layoutList) {
			if( (arr[0] == null && layout.getEquals() == null) || arr[0].equals(layout.getEquals())){
				int lengthRow = this.getMaxLineSize(layout.getFields());	
				for(int i = 0 ; i < lengthRow ; i++){sb.append(" ");}//Build line.
				
				if(layout.getEquals() != null){
					sb.replace(layout.getStart(),layout.getEnd(), layout.getEquals());
				}
				
				if(arr.length-1 != layout.getFields().size())
					throw new ConverterException("The array no has the same numbers of fields than the layout");
				
				for(int i = 1 ; i <= layout.getFields().size(); i++){
					FieldLayout fieldLayout = layout.getFields().get(i-1);
					
					String value = this.goPad(fieldLayout, arr[i]);
					sb.replace(fieldLayout.getStart(),fieldLayout.getEnd(), value);	
				}
				
				return sb.toString();
			}

		}
		throw new UnknownConverterException("Unable to identify Converter.");	

	}
	
	protected String[] getAsArray(String row, Layout layout){
		StringBuilder sb = new StringBuilder(row);
		
		List<FieldLayout> fieldLineList = layout.getFields();
		String[] _return = new String[fieldLineList.size()+1];
		_return[0] = row.substring(layout.getStart(),layout.getEnd());
		
		for (int i = 0 ; i < fieldLineList.size() ; i++) {

			FieldLayout cc = fieldLineList.get(i);
			_return[i+1] = sb.substring(cc.getStart(), cc.getEnd());
		}
		
		return _return;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getAsObject(String row, 
								Class<T> _class,
								List<FieldLayout> fieldLineList
						)throws ConverterException{
		try {
			StringBuilder sb = new StringBuilder(row);

			Constructor<?> constructor = ReflectUtils.getEmptyContructor(_class);
			T _return = (T) constructor.newInstance(new Object[0]);

			for (FieldLayout criteria : fieldLineList) {
				try {
					String stringValueToSet = sb.substring(criteria.getStart(), criteria.getEnd());

					Field field = _return.getClass().getDeclaredField(criteria.getFieldName());

					Object valueToSet = criteria.getConverter() != null ? criteria.getConverter().getAsObject(stringValueToSet) : stringValueToSet;

					ReflectField.setValue( _return, field, valueToSet);

				} catch (SecurityException e) {
					throw new ConverterException(row,"Error in field name:"+criteria.getFieldName() + " "+e.getMessage(),e);
				} catch (IllegalArgumentException e) {
					throw new ConverterException(row,"Error in field name:"+criteria.getFieldName() + " "+e.getMessage(),e);
				}catch (NoSuchFieldException e) {
					throw new ConverterException(row,"Error in field name:"+criteria.getFieldName() + " "+e.getMessage(),e);
				}
			}

			return _return;
		} catch (InstantiationException e) {
			throw new ConverterException(row,e);
		} catch (IllegalAccessException e) {
			throw new ConverterException(row,e);
		} catch (InvocationTargetException e) {
			throw new ConverterException(row,e);
		}
	}

	/**
	 * Get the max size of line from layout
	 * @param fieldLineList - fields of line
	 * @return int - max line size
	 */
	protected int getMaxLineSize(List<FieldLayout> fieldLineList){
		int lengthRow = 0;//Calculando tamanho da linha.
		for (FieldLayout fieldLayout : fieldLineList){ 
			if(lengthRow < fieldLayout.getEnd()){
				lengthRow = fieldLayout.getEnd();
			}
		}
		return lengthRow;
	}
	
	protected String getAsString(Layout layout,
								 Object obj, 
								 Class<?> _class, 
								 List<FieldLayout> fieldLineList
							)throws ConverterException{

		if(obj == null) return null;


		int lengthRow = this.getMaxLineSize(fieldLineList);
		if(lengthRow <= 0) throw new UnknownConverterException("No layout to use. Unable to identify layout.");

		StringBuilder sb = new StringBuilder(lengthRow+2);	
		for(int i = 0 ; i < lengthRow ; i++)
			sb.append(" ");


		for (FieldLayout fieldLayout : fieldLineList) {
			try {

				Field f = obj.getClass().getDeclaredField(fieldLayout.getFieldName());

				if(f != null ){

					String value = this.goPad(fieldLayout, ReflectField.getValue(obj, f, null));

					sb.replace(fieldLayout.getStart(),fieldLayout.getEnd(), value);
					continue;
				}

				throw new ConverterException(null,"Field '"+fieldLayout.getFieldName()+"' not exist. ");


			} catch (SecurityException e) {
				throw new ConverterException(null,"Error in field name:"+fieldLayout.getFieldName()+ " " + e.getMessage(),e);
			} catch (NoSuchFieldException e) {
				throw new ConverterException(null,"Error in field name:"+fieldLayout.getFieldName() + " " +e.getMessage(),e);
			} 

		}
		if(layout.getEquals() != null){
			sb.replace(layout.getStart(),layout.getEnd(), layout.getEquals());
		}

		return sb.toString();
	}
	/**
	 * Truncate and/or Padding value to format in layout
	 * @param fieldLayout - layout to used
	 * @param result - the value for a format
	 * @return formated value
	 */
	private String goPad(FieldLayout fieldLayout, Object result){
		return goPad(fieldLayout,  fieldLayout.getConverter() != null ? fieldLayout.getConverter().getAsString(result) : result != null ? result.toString() : "");
	}
	/**
	 * Trunk and/or Padding value to format in layout
	 * @param fieldLayout - layout to used
	 * @param value - the value for a format
	 * @return formated value
	 */
	private String goPad(FieldLayout fieldLayout, String value){
		if(fieldLayout.getTypePad() == null){
			return StringUtils.truncateAndLeftPad( 	value,
													fieldLayout.getEnd() - fieldLayout.getStart(),
													fieldLayout.getPad() != null ? fieldLayout.getPad() : " " 
												);
		}
		
		String _return = null;
		switch (fieldLayout.getTypePad()) {
			case RIGHT_PAD:
				_return = StringUtils.truncateAndRightPad( value,
														fieldLayout.getEnd() - fieldLayout.getStart(),
														fieldLayout.getPad() != null ? fieldLayout.getPad() : " " 
														);
				break;
	
			default:
				_return = StringUtils.truncateAndLeftPad( 	value,
														fieldLayout.getEnd() - fieldLayout.getStart(),
														fieldLayout.getPad() != null ? fieldLayout.getPad() : " " 
														);
				break;
			}
		return _return;
	}


}
