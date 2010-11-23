/*
 * 	 @(#)HexaDecimalUtils.java	0.2 10/11/22
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
/**
 * The class <code>HexaDecimalUtils</code> is used to convert values to base hexadecimal.
 * 
 * @version 0.2
 * @author Felipe Priuli
 */
public final class HexaDecimalUtils {   

	private static final char[] HEXA_DECIMAL_ARRAY = {	  48,		49,		50,		51,		52,	
														  53,		54,		55,		56,		57,	
														  65,		66,		67,		68,		69, 
														  70	
												     };
    
    private HexaDecimalUtils() {  }   

  
	/**
	 * Converts a hexadecimal String to a byte
	 * @param strHexa - String in a HexaDecimal
	 * @return String in HexaDecimal
	 */
    public static final byte toByte(final String strHexa) {   
        return ((byte) (Short.parseShort(	strHexa.toUpperCase(),	16)));   
    }   
  
   /**
    * Converts a hexadecimal String to a byte of array.
    * @param strHexa - String in a HexaDecimal
    * @return String in HexaDecimal
    */
    public static final byte[] toByteArray(final String strHexa) {   
        final char[] chars = strHexa.toCharArray();   
       
        final byte[] byteArray = new byte[chars.length / 2];   
        String aux = null;   
        for (int i = 0, j = 0; i < chars.length; i += 2, j++) {   
        	aux = new String(new char[] {chars[i], chars[i + 1]});   
            byteArray[j] = HexaDecimalUtils.toByte(aux);   
        }   
        return byteArray;   
    }   
    
    
    /** 
     * Converts a byte to a Hex String
     * @param bytes - a String in bytes
     * @return String in HexaDecimal
     */
    public static final String toString(final byte bits) {
    	final int hi = bits >>> 4 & 0xF;			final int lo = bits & 0xF;   
        
        return (String.valueOf(HEXA_DECIMAL_ARRAY[hi]).concat(String.valueOf(HEXA_DECIMAL_ARRAY[lo])));
        
    }   
  
    /** 
     * Converts a byte array to a Hex String
     * @param bytes - a String in bytes
     * @return String in HexaDecimal
     */
    public static final String toString(final byte[] bytes) {       
    	StringBuilder resp = new StringBuilder();   

        for (int i = 0; i < bytes.length; i++)
        	resp.append( HexaDecimalUtils.toString(bytes[i]));   
           
        return resp.toString();   
    }   
}  
