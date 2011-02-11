package org.opensutils.reflect;

import java.util.ArrayList;
import java.util.List;

public class ReflectUtilsTeste{
	public static void main(String... args){
		List<Project> list = new ArrayList<Project>();
		list.add( new Project("Open"));
		list.add( new Project("Sutils"));
		list.add( new Project("-Br"));
		list.add( new Project("4J"));

		List<Object> resultList = ReflectUtils.getValuesInCollection(list,"name");

		System.out.print((String) resultList.get(0));
		System.out.print((String) resultList.get(1));
		System.out.print((String) resultList.get(2));
		System.out.print((String) resultList.get(3));
		//Irá imprimir: OpenSutils-Br4J
	}

}

class Project{
	private String name;
	public Project(String name){
		this.name = name;
	}
}