/*
 * 	 @(#)TipoLogradouro.java	0.2 10/11/22
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

package org.opensutils.location;

import java.util.ArrayList;
import java.util.List;

/**
 * The class <code>TipoLogradouro</code> represent a street type.
 * 
 * @author Felipe Priuli
 * @version 0.2
 */
public class TipoLogradouro {
	/**
	 * The id to this street type. 
	 */
	private String id;
	/**
	 * Name of street type
	 */
	private String label;
	/**
	 * List of expression Language to match of this street type.
	 */
	private List<String> regexList;
	
	/**
	 * Get the id to street type. 
	 * @return id - identifier this object
	 */
	public String getId() {
		return id;
	}
	/**
	 * Set the id to street type. 
	 * @param id - the id to identify this object
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * The respective name of street type
	 * @return label - name of street type
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * Set respective name of street type
	 * @param label - name of street type
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * List of expression Language to match of this street type.
	 * return regexList
	 */
	public List<String> getRegexList() {
		if(this.regexList == null)
			this.regexList = new ArrayList<String>(4);
		
		return regexList;
	}
	/**
	 * List of expression Language to match of this street type.
	 * return regexList - (Can't be null)
	 */
	public void setRegexList(List<String> regexList) {
		if(regexList==null)
			throw new IllegalArgumentException("Can't be a null value.");
		
		this.regexList = regexList;
	}
	
	
}
