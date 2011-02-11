package org.opensutils.currency;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DecimalUtils {

	
	public static String format(BigDecimal value) {
		return format(null, value);
	}
	
	public static String format(Locale locale, BigDecimal value) {
		if(value == null){ return ""; }
		if(locale == null){
			locale=new Locale("pt","BR");
		}
		
		DecimalFormatSymbols symbols=new DecimalFormatSymbols(locale);

		String pattern = "###,###,##0.00";
		
		DecimalFormat decimalFormat = new DecimalFormat(pattern,symbols);
		
		value.setScale(8,BigDecimal.ROUND_HALF_UP);		                 
   
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');           


		return decimalFormat.format(value);
	}
}
