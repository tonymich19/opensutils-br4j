/*
 * 	 @(#)Cryptography.java	0.2 10/11/22
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

package org.opensutils.security;

import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptography {
	
	/**
	 * Cryptography a String to MD5
	 * @param value - string to encrypt;
	 * @return md5 hash
	 */
	public static String toMD5(String value){
		MessageDigest md = null;
		BigInteger hash = null;
		
		try{
			md = MessageDigest.getInstance("MD5");
			hash = new BigInteger(1, md.digest(value.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			hash = null;
		}
		
		return hash != null? hash.toString(16): null;
	}
	
	/**
	 * Cryptography a String to SHA1
	 * @param value - string to encrypt;
	 * @return sha1 hash
	 */
	public static String toSHA1(String senha) {     
		MessageDigest md = null;
		BigInteger hash = null;
		
		try{
			md = MessageDigest.getInstance("SHA1");
			hash = new BigInteger(1, md.digest(senha.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			hash = null;
		}
		
		return hash != null? hash.toString(16): null;
	}
}
