package org.opensutils.io;

import java.io.File;
import java.io.IOException;

public class BufferedFileWriterIntegrationTest {
	
	public static void main(String[] args) {
		try{
		 org.opensutils.io.FileWriter writer = new org.opensutils.io.BufferedFileWriter(new File("c:\\arquivo.txt"));
		 writer.open();
		 writer.writeLine("teste 1");
		 writer.writeLine("teste 2");;
		 writer.writeLine("teste 3");
		 writer.flush();
		 writer.close();
		}catch(IOException ioE){
			ioE.printStackTrace();
		}
	}
	 
}
