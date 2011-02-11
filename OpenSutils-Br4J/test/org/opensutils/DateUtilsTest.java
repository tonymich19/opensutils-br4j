/*
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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.opensutils.DateFormat;


public class DateUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetStringFromDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2004, 4-1, 16, 11, 25, 15);
		
		String result = DateFormat.format(calendar, "/", DateFormat.DDMMYYYY);
		
		if(!result.equals("16/04/2004"))
			fail("Erro obtenção de data no formato 'DD/MM/YYYY'");
		
		result = DateFormat.format(calendar, "/", DateFormat.DDMMYYYY_HHMISS);
		
		if(!result.equals("16/04/2004 11:25:15"))
			fail("Erro obtenção de data no formato 'DD/MM/YYYY HH24:MI:SS'");
		
		result = DateFormat.format(calendar, "-", DateFormat.DDMMYYYY_HHMISS);
		
		if(!result.equals("16-04-2004 11:25:15"))
			fail("Erro obtenção de data no formato 'DD-MM-YYYY HH24:MI:SS'");
		
		result = DateFormat.format(calendar, "-", DateFormat.YYYYMMDD);
		
		if(!result.equals("2004-04-16"))
			fail("Erro obtenção de data no formato 'YYYY-MM-DD'");
		
		result = DateFormat.format(calendar, "", DateFormat.YYYYMMDD);
		
		if(!result.equals("20040416"))
			fail("Erro obtenção de data no formato 'YYYYMMDD'");
		
		result = DateFormat.format(calendar, "-", DateFormat.YYYYMMDD_HHMISS);
		if(!result.equals("2004-04-16 11:25:15"))
			fail("Erro obtenção de data no formato 'YYYY-MM-DD HH24:MI:SS'");
		
		result = DateFormat.format(calendar, "", DateFormat.YYYYMMDD_HHMISS);
		if(!result.equals("20040416 11:25:15"))
			fail("Erro obtenção de data no formato 'YYYYMMDD HH24:MI:SS'");
		
		result = DateFormat.format(calendar, "/", DateFormat.YYYYMMDD_HHMISS);
		if(!result.equals("2004/04/16 11:25:15"))
			fail("Erro obtenção de data no formato 'YYYY/MM/DD HH24:MI:SS'");
		
		result = DateFormat.format(calendar, "/", DateFormat.YYYYMM);
		if(!result.equals("2004/04"))
			fail("Erro obtenção de data no formato 'YYYYMM'");
		
		result = DateFormat.format(calendar, "/", DateFormat.MMYYYY);
		if(!result.equals("04/2004"))
			fail("Erro obtenção de data no formato 'MMYYYY'");
		
		result = DateFormat.format(calendar, "/", DateFormat.YYYYMMDDTHHMISS);
		if(!result.equals("2004/04/16T11:25:15"))
			fail("Erro obtenção de data no formato 'YYYY/MM/DDTHH24:MI:SS'");
		
		result = DateFormat.format(calendar, "-", DateFormat.YYYYMMDDTHHMISS);
		if(!result.equals("2004-04-16T11:25:15"))
			fail("Erro obtenção de data no formato 'YYYY-MM-DD HH24:MI:SS'");
		
		result = DateFormat.format(calendar, "", DateFormat.YYYYMMDDTHHMISS);
		if(!result.equals("20040416T11:25:15"))
			fail("Erro obtenção de data no formato 'YYYYMMDD HH24:MI:SS'");
	}
	
	@Test
	public void testPassandoNullParaGetStringFromDate() {
		try{
			String result = DateFormat.format((Date)null, "", DateFormat.DDMMYYYY);
			
			if(result != null)
				fail("Voltou algo diferente de null");
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(2004, 4-1, 16, 11, 25, 15);
			
			
			//Se vier null no delimiter, temos que interpretar como "" (string vazia)
			result = DateFormat.format(calendar, null, DateFormat.DDMMYYYY);
			
			if(!result.equals("16042004"))
				fail("Erro obtenção de data no formato 'DDMMYYYY'");
			
		}catch(NullPointerException npe){
			fail("Ocorreu NullPointerException em decorrência da passagem de null como parâmetro");
		}
	}
	
	@Test
	public void testPassandoNumeroEstranhoParaGetStringFromDate(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(2004, 4-1, 16, 11, 25, 15);
		
		String result = DateFormat.format(calendar, "/", (short)20);
		
		if(!result.equals("2004-04-16"))
			fail("Aqui a data deveria aparecer no formato default 'YYYY-MM-DD'");
	}

	@Test
	public void testGetDateFromString() {
		Calendar calendar = DateFormat.parse("2008-04-16");
		
		String result = calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);
		
		if(!result.equals("16/4/2008 0:0:0"))
			fail("Erro ao aplicar o formato simples");
		
		calendar = DateFormat.parse("2008-04-16 10:15:25");

		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);
		
		if(!result.equals("16/4/2008 10:15:25"))
			fail("Erro ao aplicar o formato extendido");
		
		calendar = DateFormat.parse("2008-04-16 23:16:12");
		
		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);
		
		if(!result.equals("16/4/2008 23:16:12"))
			fail("Erro ao aplicar o formato extendido com horário HH24");
		
		calendar = DateFormat.parse("2008-04-16 23:16:12:0");
		
		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);
		
		if(!result.equals("16/4/2008 23:16:12"))
			fail("Erro ao aplicar o formato extendido com horário HH24");
		
		calendar = DateFormat.parse("2008-04-16 23:16:12.557");
		
		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);
		
		if(!result.equals("16/4/2008 23:16:12"))
			fail("Erro ao aplicar o formato extendido com horário HH24");
		
		calendar = DateFormat.parse("08-04-16 23:16:12");
		
		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);
		
		if(!result.equals("16/4/2008 23:16:12"))
			fail("Erro ao aplicar o formato extendido com horário HH24");
		
		calendar = DateFormat.parse("90-05-07 11:06:02");
		
		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);
		
		if(!result.equals("7/5/1990 11:6:2"))
			fail("Erro ao aplicar o formato extendido com horário HH24");

		calendar = DateFormat.parse("30-05-07 11:06:02");
		
		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);
		
		if(!result.equals("7/5/30 11:6:2"))
			fail("Erro ao aplicar o formato extendido com horário HH24");
		
	}

	@Test
	public void testParseBr() {
		Calendar calendar = DateFormat.parseBr("16-04-2008");
		
		String result = calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);
		
		if(!result.equals("16/4/2008 0:0:0"))
			fail("Erro ao aplicar o formato simples");
		
		calendar = DateFormat.parseBr("16-04-2008 10:15:25");
		
		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);
		
		if(!result.equals("16/4/2008 10:15:25"))
			fail("Erro ao aplicar o formato extendido");
		
		calendar = DateFormat.parseBr("16-04-2008 23:16:12");
		
		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);
		
		if(!result.equals("16/4/2008 23:16:12"))
			fail("Erro ao aplicar o formato extendido com horário HH24");
		
		calendar = DateFormat.parseBr("29/09/2008 15:15:15");
		
		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);
		
		if(!result.equals("29/9/2008 15:15:15"))
			fail("Erro ao aplicar o formato extendido com horário HH24");
	
		calendar = DateFormat.parseBr("29/09/08 15:15:15");
		
		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);

		if(!result.equals("29/9/2008 15:15:15"))
			fail("Erro ao aplicar o formato extendido com horário HH24");
		
		calendar = DateFormat.parseBr("29-12-99 12:11:14");
		
		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);

		if(!result.equals("29/12/1999 12:11:14"))
			fail("Erro ao aplicar o formato extendido com horário HH24");
		
		calendar = DateFormat.parseBr("02/10/2009 01:05:59");
		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
		(calendar.get(Calendar.MONTH) + 1) + "/" +
		calendar.get(Calendar.YEAR) + " " +
		calendar.get(Calendar.HOUR_OF_DAY) + ":" +
		calendar.get(Calendar.MINUTE) + ":" +
		calendar.get(Calendar.SECOND);

		if(!result.equals("2/10/2009 1:5:59"))
			fail("Erro ao aplicar o formato extendido com horário HH24");

		calendar = DateFormat.parseBr("02/01/2009 11:07:07");
		
		result= calendar.get(Calendar.DAY_OF_MONTH)+ "/" +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							calendar.get(Calendar.YEAR) + " " +
							calendar.get(Calendar.HOUR_OF_DAY) + ":" +
							calendar.get(Calendar.MINUTE) + ":" +
							calendar.get(Calendar.SECOND);
		
		if(!result.equals("2/1/2009 11:7:7"))
			fail("Erro ao aplicar o formato extendido com horário HH24");
		
	}
	
	@Test
	public void testSubmetendoNullParaGetDateFromString() {
		try{
			DateFormat.parse(null);
		}catch(NullPointerException npe){
			fail("Tratamento contra entrada null falhou");
		}
		
	}
	
	@Test
	public void testGetFormatedTimeFromMilisseconds() {
		String result = DateFormat.formatTime(11720000);
		
		if(!result.equals("03:15:20"))
			fail("Erro na obtenção de tempo formatada a partir de um dado de entrada em milissegundos");
	}
	
	
	@Test
	public void getHoraFromDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2004, 4-1, 16, 11, 25, 15);
		
		String result = DateFormat.formatTime(calendar);

		if(!result.equals("11:25:15"))
			fail("Erro na obtenção da hora, minutos e segundos do Calendar criado");
	}
	
	@Test
	public void differenceInDays(){
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2009, 4-1, 16, 11, 25, 15);
		//2009-4-16 11:25:15
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(2009, 5-1, 1, 11, 25, 15);
		//2009-5-1 11:25:15
		
		int dias = DateFormat.differenceInDays(calendar1, calendar2);
		
		if(! (dias == 15))
			fail("Resultado invalido, contagem de dias invalido");
		
		
		
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 5-1,1, 12, 25, 15);
		//2009-5-1 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 5-1, 31, 6, 2, 44);
		//2009-5-31 6:02:44
		
		dias = DateFormat.differenceInDays(calendar1, calendar2);
		
		// ************** TESTANDO DATAS IGUAIS ***********************
		if(! (dias == 29))
			fail("Resultado invalido, contagem de dias invalido");
		
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 5-1,1, 12, 25, 15);
		//2009-5-1 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 5-1,1, 12, 25, 15);
		//2009-5-1 12:25:15
		
		dias = DateFormat.differenceInDays(calendar1, calendar2);
		
		if(! (dias == 0))
			fail("Resultado invalido, contagem de dias invalido");
		
		// ************** TESTANDO RESULTADO NEGATIVO ***********************
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 5-1,3, 12, 25, 15);
		//2009-5-3 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 5-1,1, 12, 25, 15);
		//2009-5-1 12:25:15
		
		dias = DateFormat.differenceInDays(calendar1, calendar2);
		
		if(! (dias == -2))
			fail("Resultado invalido, contagem de dias invalido");
		
		// ************** TESTANDO RESULTADO NEGATIVO ***********************
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, (10-1),10, 12, 25, 15);
		//2009-10-10 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, (10-1),20, 12, 25, 15);
		//2009-10-20 12:25:15
		
		dias = DateFormat.differenceInDays(calendar1, calendar2);
		
		if(! (dias == 10 || dias == 9))
			fail("Resultado invalido, contagem de dias invalido");
		
		// ************** TESTANDO RESULTADO NEGATIVO ***********************
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 10-1,20, 12, 25, 15);
		//2009-10-20 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 10-1,10, 12, 25, 15);
		//2009-10-10 12:25:15
		
		dias = DateFormat.differenceInDays(calendar1, calendar2);
		
		if(! (dias == -10 || dias == -9))
			fail("Resultado invalido, contagem de dias invalido");
	}

	@Test
	public void differenceInDaysDate(){
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2009, 4-1, 16, 11, 25, 15);
		//2009-4-16 11:25:15
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(2009, 5-1, 1, 11, 25, 15);
		//2009-5-1 11:25:15
		
		int dias = DateFormat.differenceInDays(calendar1.getTime(), calendar2.getTime());
		
		if(! (dias == 15))
			fail("Resultado invalido, contagem de dias invalido");
		
		
		
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 5-1,1, 12, 25, 15);
		//2009-5-1 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 5-1, 31, 6, 2, 44);
		//2009-5-31 6:02:44
		
		dias = DateFormat.differenceInDays(calendar1.getTime(), calendar2.getTime());
		
		// ************** TESTANDO DATAS IGUAIS ***********************
		if(! (dias == 29))
			fail("Resultado invalido, contagem de dias invalido");
		
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 5-1,1, 12, 25, 15);
		//2009-5-1 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 5-1,1, 12, 25, 15);
		//2009-5-1 12:25:15
		
		dias = DateFormat.differenceInDays(calendar1.getTime(), calendar2.getTime());
		
		if(! (dias == 0))
			fail("Resultado invalido, contagem de dias invalido");
		
		// ************** TESTANDO RESULTADO NEGATIVO ***********************
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 5-1,3, 12, 25, 15);
		//2009-5-3 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 5-1,1, 12, 25, 15);
		//2009-5-1 12:25:15
		
		dias = DateFormat.differenceInDays(calendar1.getTime(), calendar2.getTime());
		
		if(! (dias == -2))
			fail("Resultado invalido, contagem de dias invalido");
		
		// ************** TESTANDO RESULTADO NEGATIVO ***********************
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 10-1,10, 12, 25, 15);
		//2009-10-10 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 10-1,20, 12, 25, 15);
		//2009-10-20 12:25:15
		
		dias = DateFormat.differenceInDays(calendar1.getTime(), calendar2.getTime());
		
		if(! (dias == 10 || dias == 9))
			fail("Resultado invalido, contagem de dias invalido");
		
		// ************** TESTANDO RESULTADO NEGATIVO ***********************
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 10-1,20, 12, 25, 15);
		//2009-10-20 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 10-1,10, 12, 25, 15);
		//2009-10-10 12:25:15
		
		dias = DateFormat.differenceInDays(calendar1.getTime(), calendar2.getTime());
		
		if(! (dias == -10 || dias == -9))
			fail("Resultado invalido, contagem de dias invalido");
	}

	@Test
	public void differenceInDaysDateEmMiliSeconds(){
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2009, 4-1, 16, 11, 25, 15);
		//2009-4-16 11:25:15
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(2009, 5-1, 1, 11, 25, 15);
		//2009-5-1 11:25:15
		
		int dias = DateFormat.differenceInDays(calendar1.getTime().getTime(), calendar2.getTime().getTime());
		
		if(! (dias == 15))
			fail("Resultado invalido, contagem de dias invalido");
		
		
		
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 5-1,1, 12, 25, 15);
		//2009-5-1 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 5-1, 31,  12, 25, 15);
		//2009-5-31 12:25:15
		
		dias = DateFormat.differenceInDays(calendar1.getTime().getTime(), calendar2.getTime().getTime());
		
		// ************** TESTANDO DATAS IGUAIS ***********************
		if(! (dias == 30))
			fail("Resultado invalido, contagem de dias invalido");
		
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 5-1,1, 12, 25, 15);
		//2009-5-1 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 5-1,1, 12, 25, 15);
		//2009-5-1 12:25:15
		
		dias = DateFormat.differenceInDays(calendar1.getTime().getTime(), calendar2.getTime().getTime());
		
		if(! (dias == 0))
			fail("Resultado invalido, contagem de dias invalido");
		
		// ************** TESTANDO RESULTADO NEGATIVO ***********************
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 5-1,3, 12, 25, 15);
		//2009-5-3 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 5-1,1, 12, 25, 15);
		//2009-5-1 12:25:15
		
		dias = DateFormat.differenceInDays(calendar1.getTime().getTime(), calendar2.getTime().getTime());
		
		if(! (dias == -2))
			fail("Resultado invalido, contagem de dias invalido");
		
		// ************** TESTANDO RESULTADO NEGATIVO ***********************
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 10-1,10, 12, 25, 15);
		//2009-10-10 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 10-1,20, 12, 25, 15);
		//2009-10-20 12:25:15
		
		dias = DateFormat.differenceInDays(calendar1.getTime().getTime(), calendar2.getTime().getTime());
		
		if(! (dias == 10 || dias == 9))
			fail("Resultado invalido, contagem de dias invalido");
		
		// ************** TESTANDO RESULTADO NEGATIVO ***********************
		calendar1 = Calendar.getInstance();
		calendar1.set(2009, 10-1,20, 12, 25, 15);
		//2009-10-20 12:25:15
		
		calendar2 = Calendar.getInstance();
		calendar2.set(2009, 10-1,10, 12, 25, 15);
		//2009-10-10 12:25:15
		
		dias = DateFormat.differenceInDays(calendar1.getTime().getTime(), calendar2.getTime().getTime());
		
		if(! (dias == -10 || dias == -9))
			fail("Resultado invalido, contagem de dias invalido");
		
		//Calendar c = DateFormat.parseBr("20-09-2007");
		//System.out.println("#####="+DateFormat.format(c, "-", DateFormat.DDMMYYYY));
	}
	
	@Test
	public void deveTruncarAsDatasCalendar(){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2009, 4-1, 16, 11, 25, 15);
		//2009-4-16 11:25:15
		
		Calendar resp = DateFormat.trunc(calendar1);

		assertTrue(resp.get(Calendar.HOUR_OF_DAY) == 0);
		assertTrue(resp.get(Calendar.HOUR) == 0);
		assertTrue(resp.get(Calendar.MINUTE) == 0);
		assertTrue(resp.get(Calendar.SECOND) == 0);
		assertTrue(resp.get(Calendar.MILLISECOND) == 0);		
		
	}
	
	@Test
	public void deveTruncarAsDatasDate(){
		Date date = new Date();
		//2009-4-16 11:25:15
		
		Date resp = DateFormat.trunc(date);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(resp);

		assertTrue(calendar.get(Calendar.HOUR_OF_DAY) == 0);
		assertTrue(calendar.get(Calendar.HOUR) == 0);
		assertTrue(calendar.get(Calendar.MINUTE) == 0);
		assertTrue(calendar.get(Calendar.SECOND) == 0);
		assertTrue(calendar.get(Calendar.MILLISECOND) == 0);		
		
	}
	
}
