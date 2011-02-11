/*
 * 	@(#)CharSymbols.java	0.2 10/11/22
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

import java.util.ArrayList;

/**
 * The class <code>CharSymbols</code> is utility for char symbols.

 * @version 0.2
 * @author Felipe Priuli
 */
public class CharSymbols {

	/**
	 * The array with light char symbols.
	 */
	public static final char[] LIGHT_SYMBOLS = {
		(char)0,	(char)1,	(char)2,	(char)3,	(char)4,	(char)5,	(char)6,	(char)7,	
		(char)8,	(char)11,	(char)12,	(char)14,	(char)15,	(char)16,	(char)17,	(char)18,	
		(char)19,	(char)20,	(char)21,	(char)22,	(char)23,	(char)24,	(char)25,	(char)26,	
		(char)27,	(char)33,	(char)34,	(char)35,	(char)36,	(char)37,	(char)38,	(char)39,	
		(char)40,	(char)41,	(char)42,	(char)43,	(char)45,	(char)47,	(char)58,	(char)59,	
		(char)60,	(char)61,	(char)62,	(char)63,	(char)64,	(char)91,	(char)92,	(char)93,	
		(char)94,	(char)95,	(char)96,	(char)123,	(char)124,	(char)125,	(char)126,	(char)127,	
		(char)128,	(char)129,	(char)130,	(char)131,	(char)132,	(char)133,	(char)134,	(char)135,	
		(char)136,	(char)137,	(char)138,	(char)139,	(char)140,	(char)141,	(char)142,	(char)143,	
		(char)144,	(char)145,	(char)146,	(char)147,	(char)148,	(char)149,	(char)150,	(char)151,	
		(char)152,	(char)153,	(char)154,	(char)155,	(char)156,	(char)157,	(char)158,	(char)159,	
		(char)161,	(char)162,	(char)163,	(char)164,	(char)165,	(char)166,	(char)167,	(char)168,	
		(char)169,	(char)170,	(char)171,	(char)172,	(char)173,	(char)174,	(char)175,	(char)176,	
		(char)177,	(char)178,	(char)179,	(char)180,	(char)181,	(char)182,	(char)183,	(char)184,	
		(char)185,	(char)186,	(char)187,	(char)188,	(char)189,	(char)190,	(char)191,	(char)198,	
		(char)208,	(char)215,	(char)216,	(char)222,	(char)223,	(char)230,	(char)240,	(char)247,	
		(char)248,	(char)254,	(char)256,	(char)257,	(char)258,	(char)259,	(char)260,	(char)261,	
		(char)262,	(char)263,	(char)264,	(char)265,	(char)266,	(char)267,	(char)268,	(char)269,	
		(char)270,	(char)271,	(char)272,	(char)273,	(char)274,	(char)275,	(char)276,	(char)277,	
		(char)278,	(char)279,	(char)280,	(char)281,	(char)282,	(char)283,	(char)284,	(char)285,	
		(char)286,	(char)287,	(char)288,	(char)289,	(char)290,	(char)291,	(char)292,	(char)293,	
		(char)294,	(char)295,	(char)296,	(char)297,	(char)298,	(char)299,	(char)300,	(char)301,	
		(char)302,	(char)303,	(char)304,	(char)305,	(char)306,	(char)307,	(char)308,	(char)309,	
		(char)310,	(char)311,	(char)312,	(char)313,	(char)314,	(char)315,	(char)316,	(char)317,	
		(char)318,	(char)319,	(char)320,	(char)321,	(char)322,	(char)323,	(char)324,	(char)325,	
		(char)326,	(char)327,	(char)328,	(char)329,	(char)330,	(char)331,	(char)332,	(char)333,	
		(char)334,	(char)335,	(char)336,	(char)337,	(char)338,	(char)339,	(char)340,	(char)341,	
		(char)342,	(char)343,	(char)344,	(char)345,	(char)346,	(char)347,	(char)348,	(char)349,	
		(char)350,	(char)351,	(char)352,	(char)353,	(char)354,	(char)355,	(char)356,	(char)357,	
		(char)358,	(char)359,	(char)360,	(char)361,	(char)362,	(char)363,	(char)364,	(char)365,	
		(char)366,	(char)367,	(char)368,	(char)369,	(char)370,	(char)371,	(char)372,	(char)373,	
		(char)374,	(char)375,	(char)376,	(char)377,	(char)378,	(char)379,	(char)380,	(char)381,	
		(char)382,	(char)383,	(char)384,	(char)385,	(char)386,	(char)387,	(char)388,	(char)389,	
		(char)390,	(char)391,	(char)392,	(char)393,	(char)394,	(char)395,	(char)396,	(char)397,	
		(char)398,	(char)399,	(char)400,	(char)401,	(char)402		
	};
	
	/**
	 * Get the light char symbols.
	 * @return character.
	 */
	public static ArrayList<Character> getLightSymbols(){
		ArrayList<Character> arrSymbols1 = new ArrayList<Character>();
		for(int i = 0; i<= 8; i++)
			arrSymbols1.add((char)i);
		arrSymbols1.add(((char)11));
		arrSymbols1.add(((char)12));
		for(int i = 14; i<= 27; i++)
			arrSymbols1.add((char)i);
		for(int i = 33; i<= 43; i++)
			arrSymbols1.add((char)i);
		arrSymbols1.add((char)45);
		arrSymbols1.add((char)47);
		for(int i = 58; i<= 64; i++)
			arrSymbols1.add((char)i);
		for(int i = 91; i<= 96; i++)
			arrSymbols1.add((char)i);
		for(int i = 123; i<= 159; i++)
			arrSymbols1.add((char)i);
		for(int i = 161; i<= 191; i++)
			arrSymbols1.add((char)i);
		arrSymbols1.add(((char)198));
		arrSymbols1.add(((char)208));
		arrSymbols1.add(((char)215));
		arrSymbols1.add(((char)216));
		arrSymbols1.add(((char)222));
		arrSymbols1.add(((char)223));
		arrSymbols1.add(((char)230));
		arrSymbols1.add(((char)240));
		arrSymbols1.add(((char)247));
		arrSymbols1.add(((char)248));
		arrSymbols1.add(((char)254));
		for(int i = 256; i<= 402; i++)
			arrSymbols1.add((char)i);
		
		return arrSymbols1;
	}
	
	/**
	 * Remove all these character 'CharSymbols.LIGHT_SYMBOLS' symbols of param1
	 * @param value - the value for a remove caracteres.
	 * @return the new text without the characters
	 */
	public static String removeSymbols(	final String value){
		return StringUtils.remove(value, CharSymbols.LIGHT_SYMBOLS);
	}
	
}
