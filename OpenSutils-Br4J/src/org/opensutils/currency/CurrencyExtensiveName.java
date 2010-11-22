/*
 * 	 @(#)CurrencyExtensiveName.java	0.4 10/11/22
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

package org.opensutils.currency;

import java.io.PrintStream;
import java.math.BigDecimal;

/**
 * Currency extensive name, convert de currency to text extensive name
 * @author Felipe Priuli - 10/11/2010
 * @version 0.4 
 */
public interface CurrencyExtensiveName {

	/**
	 * Set the value of to convert for extensive name
	 * @param   value
	 */
	void setNumber(BigDecimal value);
	/**
	 * Set the value of to convert for extensive name
	 * @param   value
	 */
	void setNumber(double value);
	
	void print(PrintStream out);
	/**
	 * Get the value formating in extensive name
	 * @return extensive full name
	 */
	String format();
}
