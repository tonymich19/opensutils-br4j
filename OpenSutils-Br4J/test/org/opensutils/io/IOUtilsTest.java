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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IOUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetNameFile() {
		String name = IOUtils.getNameFile("c:\\test\\file\\file1.txt");
		assertTrue(name.equals("file1.txt"));

		name = IOUtils.getNameFile("file1.txt");
		assertTrue(name.equals("file1.txt"));

		name = IOUtils.getNameFile("d:\\test\\file\\file1.txt");
		assertTrue(name.equals("file1.txt"));

		name = IOUtils.getNameFile("dev/test/file/file1.txt");
		assertTrue(name.equals("file1.txt"));

		name = IOUtils.getNameFile("d:\\dev\\test\\dir\\file");
		assertTrue(name.equals("file"));

		name = IOUtils.getNameFile("/opt/jboss/server/default/conf/jboss-log4j");
		assertTrue(name.equals("jboss-log4j"));

		name = IOUtils.getNameFile("/opt/jboss/server/default/conf/jndi.properties");
		assertTrue(name.equals("jndi.properties"));

		name = IOUtils.getNameFile("");
		assertTrue(name.equals(""));

		name = IOUtils.getNameFile(null);
		assertTrue(name == null);
	}

	@Test
	public void testGetExtension() {
		String extension = IOUtils.getExtension("c:\\test\\file\\file1.txt");
		assertTrue(extension.equals("txt"));

		extension = IOUtils.getExtension("file1.txt");
		assertTrue(extension.equals("txt"));

		extension = IOUtils.getExtension("c:\\my\\dir\\extensions\\file1.jpeg");
		assertTrue(extension.equals("jpeg"));


		extension = IOUtils.getExtension("file1.pdf");
		assertTrue(extension.equals("pdf"));

		extension = IOUtils.getExtension("h:\\file.rtf");
		assertTrue(extension.equals("rtf"));

		extension = IOUtils.getExtension("h:\\file");
		assertTrue(extension == null);

		extension = IOUtils.getExtension("archive");
		assertTrue(extension == null);

		extension = IOUtils.getExtension("");
		assertTrue(extension == null);

		extension = IOUtils.getExtension("d:\\dev\\test\\dir\\file.log");
		assertTrue(extension.equals("log"));

		extension = IOUtils.getExtension("/opt/jboss/server/default/conf/jboss-log4j.xml");
		assertTrue(extension.equals("xml"));

		extension = IOUtils.getExtension("/opt/jboss/server/default/conf/jndi.properties");
		assertTrue(extension.equals("properties"));

		extension = IOUtils.getExtension(null);
		assertTrue(extension == null);
	}

	@Test
	public void testRemoveExtension() {
		String extension = IOUtils.removeExtension("c:\\test\\file\\file1.txt");
		assertTrue(extension.equals("c:\\test\\file\\file1"));

		extension = IOUtils.removeExtension("file1.txt");
		assertTrue(extension.equals("file1"));

		extension = IOUtils.removeExtension("c:\\my\\dir\\extensions\\file1.jpeg");
		assertTrue(extension.equals("c:\\my\\dir\\extensions\\file1"));

		extension = IOUtils.removeExtension("file1.pdf");
		assertTrue(extension.equals("file1"));

		extension = IOUtils.removeExtension("h:\\file.rtf");
		assertTrue(extension.equals("h:\\file"));

		extension = IOUtils.removeExtension("h:\\file");
		assertTrue(extension.equals("h:\\file"));

		extension = IOUtils.removeExtension("archive");
		assertTrue(extension.equals("archive"));

		extension = IOUtils.removeExtension("");
		assertTrue(extension.equals(""));

		extension = IOUtils.removeExtension("d:\\dev\\test\\dir\\file.log");
		assertTrue(extension.equals("d:\\dev\\test\\dir\\file"));

		extension = IOUtils.removeExtension("/opt/jboss/server/default/conf/jboss-log4j.xml");
		assertTrue(extension.equals("/opt/jboss/server/default/conf/jboss-log4j"));

		extension = IOUtils.removeExtension("/opt/jboss/server/default/conf/jndi.properties");
		assertTrue(extension.equals("/opt/jboss/server/default/conf/jndi"));

		extension = IOUtils.removeExtension(null);
		assertTrue(extension == null);
	}

}
