package org.opensutils.io;

import java.io.File;
import java.io.IOException;

public class BufferedFileReaderIntegrationTest {

	
	public static void main(String[] args) {
		try{
		 org.opensutils.io.FileReader reader = new org.opensutils.io.BufferedFileReader(new File("c:\\arquivo.txt"));
		 reader.open();
		 System.out.println(reader.nextLine());
		 System.out.println(reader.nextLine());
		 System.out.println(reader.nextLine());
		 System.out.println("Linhas lidas no arquivo :"+reader.getCountReadLine());
		 reader.close();
		 reader.clean();
		}catch(IOException ioE){
			ioE.printStackTrace();
		}
	}	
	
}
