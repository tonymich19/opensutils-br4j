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

package org.opensutils.io;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.opensutils.converter.C3POLineConverter;
import org.opensutils.converter.ConverterUtils;
import org.opensutils.converter.layout.FieldLayout;
import org.opensutils.converter.layout.Layout;
import org.opensutils.converter.layout.FieldLayout.TypePad;

public class BufferedFileWriterConvertIntegrationTest {

	public static void main(String[] args) {
		try{
			C3POLineConverter c = new C3POLineConverter(buildLayout());	
			FileWriterConverter writer = new org.opensutils.io.BufferedFileWriterConverter(new File("c:\\testObjectWriter.txt"), c);
			writer.open();
			
			writeHeader(writer);//00IHEADERheaderHEADEROTHERXXXXX
			
			writeDetail(writer);//1NOME_CLIENTE00001255VALUE000400050001.414/12/2010
	
			writer.flush();
			writer.close();
			
		}catch(Exception ioE){
			System.out.println(ioE.getMessage());
			ioE.printStackTrace();
		}
	}
	
	private static void writeDetail(FileWriterConverter writer) throws IOException {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, (12-1), 14, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		DetailMock m = new DetailMock(1255L,"NOME_CLIENTE",(short)1.4,(int)40005,"VALUE",calendar.getTime());
		
		writer.writer(m);
	}
	
	private static void writeHeader(FileWriterConverter writer) throws IOException {
		
		HeaderMock header = new HeaderMock("       HEADERheaderHEADER   "," OTHER ");
		writer.writer(header);
	}

	private static List<Layout> buildLayout() {
		List<Layout> layout = new ArrayList<Layout>(8);
		layout.add( new Layout(0,1,"1", DetailMock.class));//Initial 1 use this layout
		layout.get(0).getFields().add(new FieldLayout(1,13,"name"," ",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(13,21,"id","0",ConverterUtils.buildConvertLong()));
		layout.get(0).getFields().add(new FieldLayout(21,26,"nameNull"," ",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(34,40,"numberShort","0",ConverterUtils.buildConvertShort()));
		layout.get(0).getFields().add(new FieldLayout(21,26,"fieldNotGetMethod"," ",ConverterUtils.buildConvertString()));
		layout.get(0).getFields().add(new FieldLayout(26,34,"numberInteger","0", ConverterUtils.buildConvertInteger()));
		layout.get(0).getFields().add(new FieldLayout(40,50,"data"," ",ConverterUtils.buildConvertDate(new SimpleDateFormat("dd/MM/yyyy"))));
		
		layout.add( new Layout(0,2,"00",HeaderMock.class));//Initial 0 use this layout
		layout.get(1).getFields().add(new FieldLayout(2,21,"name","I",TypePad.LEFT_PAD,ConverterUtils.buildConvertString()));
		layout.get(1).getFields().add(new FieldLayout(21,31,"other","X",TypePad.RIGHT_PAD,ConverterUtils.buildConvertString()));
		
		return layout;
	}

}
@SuppressWarnings("unused")
class HeaderMock{
	private String name;
	private String other;
	
	public HeaderMock(String name, String other) {
		super();
		this.name = name;
		this.other = other;
	}
}
@SuppressWarnings("unused")
class DetailMock{
	private long id;
	private String name;
	private String nameNull = null;
	private short numberShort;
	private Integer numberInteger;
	public String fieldNotGetMethod;
	public Date data;
	
	public DetailMock(long id, String name,short numberShort,
			Integer numberInteger, String fieldNotGetMethod, Date data) {
		super();
		this.id = id;
		this.name = name;
		this.numberShort = numberShort;
		this.numberInteger = numberInteger;
		this.fieldNotGetMethod = fieldNotGetMethod;
		this.data = data;
	}
}