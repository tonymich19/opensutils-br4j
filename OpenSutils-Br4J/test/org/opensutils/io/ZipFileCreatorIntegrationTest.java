package org.opensutils.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ZipFileCreatorIntegrationTest {


	public static void main(String args[]){
		// TESTE
		try {
			ZipFileCreator zip = new ZipFileCreator("C:\\zipDir.zip");
			zip.addDirectory(new File("C:\\teste"));
			zip.addFile(new FileInputStream("c://VARA_NOVAS_COMARCAS.SQL"), "VARA.SQL");
			zip.compress();
			zip.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
