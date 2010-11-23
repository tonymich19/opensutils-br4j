/*
 * 	 @(#)ZipUtils.java	0.6 10/11/22
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

package org.opensutils.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
/**
 * The class <code>ZipUtil</code> is a Utility for zip and un-zip files.
 * 
 * @version 0.6
 * @author Felipe Priuli
 */
public final class ZipUtils {
	
	private ZipUtils(){}

	/**
	 * Method to compress one or more files
	 * @param absolutePathFileNames - array with the path of files to be compressed
	 * @param absolutePathFileNameOut path of out file .zip
	 * @return the compress file
	 * @throws IOException
	 */
	public static File compress(String[] absolutePathFileNames, String absolutePathFileNameOut) throws IOException {

	    byte[] buf = new byte[1024];

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(absolutePathFileNameOut));

        for (int i=0; i<absolutePathFileNames.length; i++) {
            FileInputStream in = new FileInputStream(absolutePathFileNames[i]);
            // Adiciona a entrada ZIP no output stream
            out.putNextEntry(new ZipEntry(absolutePathFileNames[i]));
            // Transfere os bytes do arquivo para o ZIP
            int len;
            while((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.closeEntry();
            in.close();
        }
        out.close();
        
        return new File(absolutePathFileNameOut);
	}
	
	/**
	 * Method to compress one or more files
	 * @param absolutePathFileNames - array with the path of files to be compressed
	 * @param String[] nomesArquivos array com os nomes dos arquivos a serem compactados
	 * @param absolutePathFileNameOut path of out file .zip
	 * @return the compress file
	 * @throws IOException
	 */
	public static File compress(	String[] absolutePathFileNames, 
									String[] nomesArquivos,
									String absolutePathFileNameOut) throws IOException {
	    
	    byte[] buf = new byte[1024];
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(absolutePathFileNameOut));
        for (int i=0; i<absolutePathFileNames.length; i++) {
            FileInputStream in = new FileInputStream(absolutePathFileNames[i]);
            out.putNextEntry(new ZipEntry(nomesArquivos[i]));
            // Transfere os bytes do arquivo para o ZIP
            int len;
            while((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.closeEntry();
            in.close();
        }
        out.close();
        
        return new File(absolutePathFileNameOut);
	}
	
	/**
	 * Method to compress one files
	 * @param String pathFile path do arquivo a ser compactado Ex.: "c:/temp/"
	 * @param String fileName - name of file to as compact in zip format
	 * @param String absolutePathFileNameOut  path of out file .zip
	 * @return the compress file
	 * @throws IOException
	 */
	public static File compress(String pathFile,
								String fileName,
								String absolutePathFileNameOut) throws IOException {

		String absolutePath = null;
		if(!pathFile.substring((pathFile.length()-1)).equals(File.separator)){
			absolutePath = pathFile + File.separator + fileName;
		}else{
			absolutePath = pathFile + fileName;
		}

	    byte[] buf = new byte[1024];
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(absolutePathFileNameOut));
        FileInputStream in = new FileInputStream(absolutePath);
        out.putNextEntry(new ZipEntry(fileName));
        int len;
        while((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.closeEntry();
        in.close();
        out.close();
        
        return new File(absolutePathFileNameOut);
	}
	
	/**
	 * Descompress a file zip.
	 * @param 	file - the file compress
	 * @param 	destinationDirectory - Destination descompress directory
	 * @return	the descompress files
	 * @throws 	IOException
	 * @throws 	ZipException 
	 */
	public static File[] decompresses(	File file, 
										File destinationDirectory) throws ZipException, IOException{ 	
		
		if(!destinationDirectory.exists()){
			destinationDirectory.mkdir();
		}
		if( !destinationDirectory.isDirectory()){
			throw new IllegalArgumentException("Second param not is a Directory"); 
		}
		ZipFile arquivoZip	= new ZipFile(file);
		File[] retorno = new File[arquivoZip.size()];
		Enumeration<? extends ZipEntry> enumerador = arquivoZip.entries(); 
		int countFiles = 0;
		while(enumerador.hasMoreElements()){   
			ZipEntry arquivoDentroZip = enumerador.nextElement();

			//Criando estrutura de pastas
			if(arquivoDentroZip.isDirectory()) {   
				(new File(arquivoDentroZip.getName())).mkdir();   
				continue;   
			}     

			//arquivos dentro do zip
			InputStream  arquivoLido = null;
			OutputStream outputStream = null;
			BufferedOutputStream  bufferArquivo = null;
			File outFile = new File(destinationDirectory.getAbsolutePath()+File.separator+arquivoDentroZip.getName());
			try{
				arquivoLido = arquivoZip.getInputStream(arquivoDentroZip);
				outputStream = new FileOutputStream(outFile);     
				bufferArquivo = new BufferedOutputStream(outputStream);

				byte[] buffer;				int numeroBytes;			buffer = new byte[1024];   
				while((numeroBytes = arquivoLido.read(buffer)) >= 0)
					bufferArquivo.write(buffer, 0, numeroBytes);   

				bufferArquivo.flush();		  	 
				outputStream.flush();
				
				retorno[countFiles++] = outFile;
			}finally{
				if(arquivoLido != null ) arquivoLido.close();
				if(bufferArquivo != null ) bufferArquivo.close();
				if(outputStream != null ) outputStream.close();
			}
			
		} 	


		return retorno;
	}

	/////////////////////////////////////////////////
	//////// PARA TESTE ///////////////////////////////
	///////////////////////////////////////////////
	/*public static void main(String[] args) {
		try {
			System.out.println("compacta.");
			String[] arr = {"c:\\felipe.priuli\\BCS_20101021.475homologacao","c:\\felipe.priuli\\nagent_log.txt"};
			String[] arrNomes = {"ARQUIVO1","ARQUIVO2"};
			File file = compress(arr,arrNomes, "c:\\felipe.priuli\\outZipTest.zip");
			System.out.println("filename: "+file.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
			/*try {
				System.out.println("compacta.");
				String[] arr = {"c:\\felipe.priuli\\BCS_20101021.475homologacao","c:\\felipe.priuli\\nagent_log.txt"};
				File file = compress(arr, "c:\\felipe.priuli\\outZipTest.zip");
				System.out.println("filename: "+file.getName());
				
			} catch (Exception e) {
				e.printStackTrace();
			}*/
	/*	try {
			System.out.println("compacta.");
			File file = compress("c:\\felipe.priuli\\", "BCS_20101021.475homologacao","c:\\felipe.priuli\\outZipTest.zip");
			System.out.println("filename: "+file.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		//descompacta
		/*try {
			System.out.println("Descompactando.");
			File[] files = decompresses(new File("c:\\felipe.priuli\\outZipTest.zip"), new File("c:\\felipe.priuli"));
			for (File file : files) {
				System.out.println("filename: "+file.getName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
