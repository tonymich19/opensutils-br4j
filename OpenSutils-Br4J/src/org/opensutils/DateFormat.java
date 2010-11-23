/*
 * 	 @(#)DateFormat.java	1.6 10/11/22
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
package org.opensutils;

import java.util.Calendar;
import java.util.Date;
/**
 * The class <code>DateFormat</code> is used for validade, convert and parser 
 * dates.
 * 
 * @version 1.6
 * @author Felipe Priuli
 */
public final class DateFormat {
	
	private DateFormat(){}
	
	public static final short YYYYMMDD = 1;
	public static final short DDMMYYYY = 2;
	public static final short DDMMYYYY_HHMISS = 3;
	public static final short YYYYMMDD_HHMISS = 4;
	public static final short MMYYYY = 5;
	public static final short YYYYMM = 6;
	public static final short YYYYMMDDTHHMISS = 7;

	/**
	 * Formats a date in text.
	 * @param calendar - the date to format in text.
	 * @param delimiter - separator for date fields
	 * @param FORMAT - <br>
	 * 				DateFormat.YYYYMMDD<br>
	 * 				DateFormat.DDMMYYYY<br>
	 * 				DateFormat.DDMMYYYY_HHMISS<br>
	 * 				DateFormat.YYYYMMDD_HHMISS<br>
	 * 				DateFormat.MMYYYY<br>
	 * 				DateFormat.YYYYMMDDTHHMISS<br>
	 * 
	 * @return String formated
	 */
	public static String format(final Calendar calendar,
								String delimiter,
								short FORMAT){
	
		String result = null;
		if(calendar != null ){
			
			if(delimiter == null)
				delimiter = "";

			String ano = String.valueOf(calendar.get(Calendar.YEAR));
			String mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
			if(mes.length() < 2) mes = "0" + mes;
			String dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
			if(dia.length() < 2) dia = "0" + dia;
			
			String minutos = null;
			String segundos = null;
			String hora = null;
			switch(FORMAT){
				case YYYYMMDD:
					result = ano + delimiter + mes + delimiter + dia;
					break;
				case DDMMYYYY:
					result = dia + delimiter + mes + delimiter + ano;
					break;
				case DDMMYYYY_HHMISS:
					hora = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
					if(hora.length() < 2) hora = "0" + hora;
					minutos = String.valueOf(calendar.get(Calendar.MINUTE));
					if(minutos.length() < 2) minutos = "0" + minutos;
					segundos = String.valueOf(calendar.get(Calendar.SECOND));
					if(segundos.length() < 2) segundos = "0" + segundos;
					
					result = dia + delimiter + mes + delimiter + ano + " " + hora + ":" + minutos + ":" + segundos;
					break;
				case YYYYMMDD_HHMISS:
					hora = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
					if(hora.length() < 2) hora = "0" + hora;
					minutos = String.valueOf(calendar.get(Calendar.MINUTE));
					if(minutos.length() < 2) minutos = "0" + minutos;
					segundos = String.valueOf(calendar.get(Calendar.SECOND));
					if(segundos.length() < 2) segundos = "0" + segundos;
					
					result = ano + delimiter + mes + delimiter + dia + " " + hora + ":" + minutos + ":" + segundos;
					break;
				case YYYYMMDDTHHMISS:
					hora = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
					if(hora.length() < 2) hora = "0" + hora;
					minutos = String.valueOf(calendar.get(Calendar.MINUTE));
					if(minutos.length() < 2) minutos = "0" + minutos;
					segundos = String.valueOf(calendar.get(Calendar.SECOND));
					if(segundos.length() < 2) segundos = "0" + segundos;
					
					result = ano + delimiter + mes + delimiter + dia + "T" + hora + ":" + minutos + ":" + segundos;
					break;
				case MMYYYY:
					result = mes + delimiter + ano;
					break;
				case YYYYMM:
					result = ano + delimiter + mes;
					break;
				default:
					result = ano + "-" + mes + "-" + dia;
			}
		}
		
		return result;
		
	}
	
	/**
	 * Parse the String to Calendar.<br> 
	 * <b>Supported formats</b>
	 * 
	 * 	YYYY-MM-DD<br>
	 * 	YYYY-MM-DD HH24:MI:SS<br>
	 *	YYYY-MM-DD HH24:MI:SS.ML<br>
	 * 	YY-MM-DD<br>
	 * 	YY-MM-DD HH24:MI:SS<br>
	 * 
	 * @param 	data - String Date in supported formats
	 * @see 	DateFormat.parseBr(java.lang.String)
	 * @return 	Calendar - the calendar
	 */
	public static Calendar parse(String data){

		Calendar c = null;
		
		if(data != null){
			c = Calendar.getInstance();

			if(data.length() == 10) // formato esperado "YYYY-MM-DD"
				c.set(Integer.parseInt(data.substring(0,4)), Integer.parseInt(data.substring(5,7))-1, Integer.parseInt(data.substring(8,10)), 0, 0, 0);
			else if(data.length() == 19 || data.length() >= 21 ){ // ou no formato "YYYY-MM-DD HH24:MI:SS" ou no formato "YYYY-MM-DD HH24:MI:SS.ML"
				c.set(Integer.parseInt(data.substring(0,4)), Integer.parseInt(data.substring(5,7))-1, Integer.parseInt(data.substring(8,10)), Integer.parseInt(data.substring(11,13)), Integer.parseInt(data.substring(14,16)), Integer.parseInt(data.substring(17,19)));
			}else if(data.length() == 8){// String de data no formato "YY-MM-DD"
				c.set(Integer.parseInt(getPrefixoAno(data.substring(0,2))), Integer.parseInt(data.substring(3,5))-1, Integer.parseInt(data.substring(6,8)),0,0,0);
			}else if(data.length() == 17 ){// ou no formato "YY-MM-DD HH24:MI:SS"
				c.set(Integer.parseInt(getPrefixoAno(data.substring(0,2))), Integer.parseInt(data.substring(3,5))-1, Integer.parseInt(data.substring(6,8)), Integer.parseInt(data.substring(9,11)), Integer.parseInt(data.substring(12,14)), Integer.parseInt(data.substring(15,17)));
			}else
				c = null;
			
		}
		
		return c;
	}
	

	/**
	 * Parse the String to Calendar.<br> 
	 * <b>Supported formats</b>
	 * 
	 * 	DD-MM-YYYY<br> 
	 * 	DD-MM-YYYY HH24:MI:SS<br> 
	 * 	DD-MM-YY<br> 
	 * 	DD-MM-YY HH24:MI:SS<br> 
	 * 
	 * @param 	data - String Date in supported formats
	 * @see 	DateFormat.parseBr(java.lang.String)
	 * @return 	Calendar - the calendar
	 */
	public static Calendar parseBr(String data){
		Calendar dt = null;
		if ( data != null ){
			dt = Calendar.getInstance();
			
			if(data.length() == 10) // String de data no formato "DD-MM-YYYY"
				dt.set(Integer.parseInt(data.substring(6,10)), Integer.parseInt(data.substring(3,5))-1, Integer.parseInt(data.substring(0,2)),0,0,0);
			else if(data.length() == 19){// ou no formato "DD-MM-YYYY HH24:MI:SS"
				dt.set(Integer.parseInt(data.substring(6,10)), Integer.parseInt(data.substring(3,5))-1, Integer.parseInt(data.substring(0,2)), Integer.parseInt(data.substring(11,13)), Integer.parseInt(data.substring(14,16)), Integer.parseInt(data.substring(17,19)));
			}else if(data.length() == 8){// String de data no formato "DD-MM-YY"
				dt.set(Integer.parseInt(getPrefixoAno(data.substring(6,8))), Integer.parseInt(data.substring(3,5))-1, Integer.parseInt(data.substring(0,2)),0,0,0);
			}else if(data.length() == 17){// ou no formato "DD-MM-YY HH24:MI:SS"
				dt.set(Integer.parseInt(getPrefixoAno(data.substring(6,8))), Integer.parseInt(data.substring(3,5))-1, Integer.parseInt(data.substring(0,2)), Integer.parseInt(data.substring(9,11)), Integer.parseInt(data.substring(12,14)), Integer.parseInt(data.substring(15,17)));
			}else{
				dt = null;
			}
		}
		return dt;
	}
	
	
	/**
	 * Return the hour, minutes and seconds of the calendar
	 * @param calendar - The date to format
	 * @return time - HH:MM:SS
	 */
	public static String formatTime(final Calendar calendar){
		String dateString = format(calendar, "-", DateFormat.YYYYMMDD_HHMISS);
		if(dateString != null){
			try{
				dateString = dateString.substring(11, 13)+":"+dateString.substring(14, 16)+":"+dateString.substring(17, 19);
			}catch(IndexOutOfBoundsException indexE){
				dateString = "00:00:00";
			}
		}
		return dateString;
	}
	
	/**
	 * Return the hour, minutes and seconds.
	 * @param milisseconds
	 * @return time - HH:MM:SS
	 */
	public static String formatTime(final long milisseconds){
		double nInHours = milisseconds / 3600000D;
		int horas = (int)Math.floor(nInHours);
		double nInMinutes = (nInHours - horas) * 60;
		int minutos = (int)Math.floor(nInMinutes);
		double nInSeconds = (nInMinutes - minutos) * 60;
		int segundos = (int)Math.round(nInSeconds);
		
		String temp = String.valueOf(horas);
		if(temp.length() == 1 ){
			temp = "0".concat(temp);
		}
		
		return leftPadZero(String.valueOf(horas)) + ":" + leftPadZero(String.valueOf(minutos)) + ":" + leftPadZero(String.valueOf(segundos));
	}
	/**
	 * Calculate the difference in days from a date with another date. <br>
	 * @param c1 - First date 
	 * @param c2 - Second date
	 * @return Difference in days 
	 */
	public static int differenceInDays(final Calendar c1, final Calendar c2){
      return DateFormat.differenceInDays( c1.getTime().getTime(), c2.getTime().getTime());
    }
	/**
	 * Calculate the difference in days from a date with another date. <br>
	 * @param c1 - First date 
	 * @param c2 - Last date
	 * @return Difference in days 
	 */
	public static int differenceInDays(final java.sql.Date d1, final java.sql.Date d2){
		return DateFormat.differenceInDays(d1.getTime(), d2.getTime());
	}
	/**
	 * Calculate the difference in days from a date with another date. <br>
	 * @param d1 - First date 
	 * @param d2 - Last date
	 * @return Difference in days 
	 */
	public static int differenceInDays(final Date d1, final Date d2){
		return DateFormat.differenceInDays(d1.getTime(), d2.getTime());
    }
	/**
	 * Calculate the difference in days from a date with another date. <br>
	 * @param miliSeconds1 - First date 
	 * @param miliSeconds2 - Last date
	 * @return Difference in days 
	 */
	public static int differenceInDays(final long miliSeconds1, final long miliSeconds2){;
	       return (int) ((miliSeconds2 - miliSeconds1) / (24*60*60*1000));
	}
	

	public static String format(Date date, String delimiter, short FORMAT){
		if(date == null)
			return null;
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return format(c, delimiter, FORMAT);
	}

	private static String leftPadZero(String str){
		if(str != null){
			if(str.length() == 1 ){
				return "0".concat(str);
			}else if(str.length() == 0 ){
				return "00";
			}else
				return str;		
		}
		return null;
	}
	
	private static String getPrefixoAno(String ano){
		if(ano.length() == 2 && ano.startsWith("0")){
			return "20"+ano;
		}else if(ano.length() == 2 && ano.startsWith("9")){
			return "19"+ano;
		}else
			return ano;
	}
	

}
