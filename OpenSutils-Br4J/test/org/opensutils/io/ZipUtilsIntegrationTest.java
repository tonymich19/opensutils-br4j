package org.opensutils.io;

import java.io.File;

public class ZipUtilsIntegrationTest {
	/////////////////////////////////////////////////
	//////// PARA TESTE ///////////////////////////////
	///////////////////////////////////////////////
	public static void main(String[] args) {
		try {
			System.out.println("compacta.");
			String[] arr = {"c:\\felipe.priuli\\BCS_20101021.475homologacao","c:\\felipe.priuli\\nagent_log.txt"};
			String[] arrNomes = {"ARQUIVO1","ARQUIVO2"};
			File file = ZipUtils.compress(arr,arrNomes, "c:\\felipe.priuli\\outZipTest.zip");
			System.out.println("filename: "+file.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}
		/*try {
				System.out.println("compacta.");
				String[] arr = {"c:\\felipe.priuli\\BCS_20101021.475homologacao","c:\\felipe.priuli\\nagent_log.txt"};
				File file = ZipUtils.compress(arr, "c:\\felipe.priuli\\outZipTest.zip");
				System.out.println("filename: "+file.getName());

			} catch (Exception e) {
				e.printStackTrace();
			}*/
		/*	try {
			System.out.println("compacta.");
			File file = ZipUtils.compress("c:\\felipe.priuli\\", "BCS_20101021.475homologacao","c:\\felipe.priuli\\outZipTest.zip");
			System.out.println("filename: "+file.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		//descompacta
		/*try {
			System.out.println("Descompactando.");
			File[] files = ZipUtils.decompresses(new File("c:\\felipe.priuli\\outZipTest.zip"), new File("c:\\felipe.priuli"));
			for (File file : files) {
				System.out.println("filename: "+file.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}
