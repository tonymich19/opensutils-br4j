/*
 * 	 @(#)FieldLine.java	0.1 2010/12/14
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

package org.opensutils.converter.layout;

import org.opensutils.converter.Converter;

/**
 * The <code>FieldLayout</code> class represent de fields layout present in the line.
 */
public class FieldLayout {
	
	private int end;
	private int start;
	private String fieldName;
	private String pad;
	private TypePad typePad;
	private Converter converter;
	
	/**
	 * Constructor of FieldLayout class
	 */
	public FieldLayout(){
	}
	
	public FieldLayout(	int start, int end,
						String fieldName,
						Converter converterField) {
		
		this(true,start,end,fieldName, null, TypePad.LEFT_PAD, converterField);
	}
	public FieldLayout(	int start, int end,
						String fieldName,
						String pad,
						Converter converterField) {
			
		this(true,start,end,fieldName, pad,TypePad.LEFT_PAD,converterField);
	}
	public FieldLayout(	int start, int end,
						String fieldName,
						String pad,
						TypePad typePad,
						Converter converterField) {

	this(true,start,end,fieldName, pad,typePad,converterField);
	}
	
	
	public FieldLayout(	boolean startInZero,
						int start, 
						int end, 
						String fieldName,
						Converter converter){
		
		this(startInZero,start,end,fieldName, null,TypePad.LEFT_PAD,converter);
	}
	
	public FieldLayout(	boolean startInZero,
			int start, 
			int end, 
			String fieldName,
			String pad,
			Converter converter){

		this(startInZero,start,end,fieldName, pad,TypePad.LEFT_PAD,converter);
	}
	
	public FieldLayout(	boolean startInZero,
						int start, 
						int end, 
						String fieldName, 
						String pad,
						TypePad typePad,
						Converter converter) {
		
		super();
		if(!startInZero)
			this.start = start-1;
		else{
			this.start = start;
		}
		if(!startInZero){
			this.end = end-1;
		}else{
			this.end = end;
		}
		if(start > end){
			throw new IllegalArgumentException("The value of parameter 'start' can not be greater than the value of parameter 'end'.");
		}
		if(start < 0){
			throw new IllegalArgumentException("The value of parameter 'start' can not be less than zero.");
		}
		if(end < 0){
			throw new IllegalArgumentException("The value of parameter 'start' can not be less than zero.");
		}
		
		this.fieldName = fieldName;
		this.converter = converter;
		this.pad = pad != null ? pad : " ";
		this.typePad = typePad;
	}


	/**
	 * Get the initial number of layout of this field
	 * @return the start
	 */
	public int getStart() {
		return start;
	}
	/**
	 * Set the initial number of layout of this field
	 * @return the initial number
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * Get the final number of layout of this field
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}
	/**
	 * Set the final number of layout of this field
	 * @param the end
	 */
	public void setEnd(int end) {
		this.end = end;
	}
	/**
	 * Get the field name.
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * Set the field name.
	 * @return the fieldName
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Get the converter to parse the String value.
	 * @return the converter
	 */
	public Converter getConverter() {
		return converter;
	}


	/**
	 * Set the converter to parse the String value.
	 * @param converter the converter to set
	 */
	public void setConverter(Converter converter) {
		this.converter = converter;
	}

	/**
	 * @return the pad
	 */
	public String getPad() {
		return pad;
	}

	/**
	 * @param pad the pad to set
	 */
	public void setPad(String pad) {
		this.pad = pad != null ? pad : " ";
	}

	/**
	 * @return the typePad
	 */
	public TypePad getTypePad() {
		return typePad;
	}

	/**
	 * @param typePad the typePad to set
	 */
	public void setTypePad(TypePad typePad) {
		this.typePad = typePad;
	}

	public static enum TypePad{
		LEFT_PAD, RIGHT_PAD;
	}	
}
