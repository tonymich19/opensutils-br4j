package org.opensutils.converter.layout;

import java.util.ArrayList;
import java.util.List;

public class Layout {

	private int start;
	private int end;
	private String equals;
	private Class<?> representantionClass;
	private List<FieldLayout> fields;
	
	public Layout(int start, int end, String equals,Class<?> representantionClass ) {
		super();
		this.start = start;
		this.end = end;
		this.equals = equals;
		this.representantionClass= representantionClass;
	}

	public Layout(int start, int end, String equals,
			List<FieldLayout> fieldLineList, Class<?> representantionClass) {
		super();
		this.start = start;
		this.end = end;
		this.equals = equals;
		this.fields = fieldLineList;
		this.representantionClass= representantionClass;
	}
	
	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}
	/**
	 * @return the equals
	 */
	public String getEquals() {
		return equals;
	}
	/**
	 * @param equals the equals to set
	 */
	public void setEquals(String equals) {
		this.equals = equals;
	}
	/**
	 * @return the fields
	 */
	public List<FieldLayout> getFields() {
		if(fields == null){
			fields = new ArrayList<FieldLayout>();
		}
		return fields;
	}
	/**
	 * @param fields the fieldLineList to set
	 */
	public void setFields(List<FieldLayout> fieldLayoutList) {
		this.fields = fieldLayoutList;
	}

	/**
	 * @return the representantionClass
	 */
	public Class<?> getRepresentantionClass() {
		return representantionClass;
	}

	/**
	 * @param representantionClass the representantionClass to set
	 */
	public void setRepresentantionClass(Class<?> representantionClass) {
		this.representantionClass = representantionClass;
	}
	
	
	
}
