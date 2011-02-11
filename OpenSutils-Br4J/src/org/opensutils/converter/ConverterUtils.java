/*
 * 	 @(#)ConverterUtils.java	0.1 2010/12/14
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ConverterUtils is a class utilitary to build Converter implementations
 * @author Felipe Priuli
 */
public final class ConverterUtils {

	private ConverterUtils(){
	}
	
	public static Converter buildConvertInteger(){
		return new Converter(){

			@Override
			public Object getAsObject(String value) {

				if(value == null || value.isEmpty()){
					return null;
				}else{
					return Integer.parseInt(value.trim());
				}
			}

			@Override
			public String getAsString(Object obj) throws ConverterException {
				if(obj == null ){
					return null;
				}else{
					try{
						return Integer.toString((Integer)obj);
					}catch(Exception ccE){
						throw new ConverterException(obj,ccE);
					}
				}
			}
			
		};
	}
	
	public static Converter buildConvertDouble(){
		return new Converter(){

			@Override
			public Object getAsObject(String value) {

				if(value == null || value.isEmpty()){
					return null;
				}else{
					return Double.parseDouble(value.trim());
				}
			}
			
			@Override
			public String getAsString(Object obj) throws ConverterException {
				if(obj == null ){
					return null;
				}else{
					try{
						return Double.toString((Double)obj);
					}catch(Exception ccE){
						throw new ConverterException(obj,ccE);
					}
				}
			}
			
		};
	}
	
	public static Converter buildConvertShort(){
		return new Converter(){

			@Override
			public Object getAsObject(String value) {

				if(value == null || value.isEmpty()){
					return null;
				}else{
					return Short.parseShort(value.trim());
				}
			}
			
			@Override
			public String getAsString(Object obj) throws ConverterException {
				if(obj == null ){
					return null;
				}else{
					try{
						return Short.toString((Short)obj);
					}catch(Exception ccE){
						throw new ConverterException(obj,ccE);
					}
				}
			}
			
		};
	}
	
	public static Converter buildConvertFloat(){
		return new Converter(){

			@Override
			public Object getAsObject(String value) {

				if(value == null || value.isEmpty()){
					return null;
				}else{
					return Float.parseFloat(value.trim());
				}
			}
			
			@Override
			public String getAsString(Object obj) throws ConverterException {
				if(obj == null ){
					return null;
				}else{
					try{
						return Float.toString((Float)obj);
					}catch(Exception ccE){
						throw new ConverterException(obj,ccE);
					}
				}
			}
			
		};
	}
	
	public static Converter buildConvertLong(){
		return new Converter(){

			@Override
			public Object getAsObject(String value) {

				if(value == null || value.isEmpty()){
					return null;
				}else{
					return Long.parseLong(value.trim());
				}
			}
			
			@Override
			public String getAsString(Object obj) throws ConverterException {
				if(obj == null ){
					return null;
				}else{
					try{
						return Long.toString((Long)obj);
					}catch(Exception ccE){
						throw new ConverterException(obj,ccE);
					}
				}
			}
			
		};
	}
	
	
	public static Converter buildConvertString(){
		return new Converter(){

			@Override
			public Object getAsObject(String value) {

				if(value == null || value.isEmpty()){
					return null;
				}else{
					return value.trim();
				}
			}
			
			@Override
			public String getAsString(Object obj) throws ConverterException {
				if(obj == null ){
					return null;
				}else{
					try{
						return (String)obj;
					}catch(Exception ccE){
						throw new ConverterException(obj,ccE);
					}
				}
			}
			
		};
	}
	
	
	public static Converter buildConvertDate(final SimpleDateFormat format){
		return new Converter(){

			@Override
			public Object getAsObject(String value) throws ConverterException {

				if(value == null || value.isEmpty()){
					return null;
				}else{
					try {
						return format.parse(value.trim());
					} catch (ParseException e) {
						throw new ConverterException(value,e);
					}
				}
			}
			
			@Override
			public String getAsString(Object obj) throws ConverterException {
				if(obj == null ){
					return null;
				}else{
					try{
						return format.format((Date)obj);
					}catch(Exception ccE){
						throw new ConverterException(obj,ccE);
					}
				}
			}
			
		};
	}
	
}
