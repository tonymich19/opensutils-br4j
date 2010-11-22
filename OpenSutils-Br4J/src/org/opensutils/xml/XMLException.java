package org.opensutils.xml;

import java.util.List;

public class XMLException extends Exception {

	private List<XMLElementError> listErro;
	private static final long serialVersionUID = 1153745384550128972L;

	public XMLException(String message, List<XMLElementError> listErro) {
		this(message);
		this.listErro = listErro;
	}

	public XMLException(String message) {
		super(message);
	}
	
	public XMLException(Throwable source) {
		super(source);
	}

	public XMLException(String message, Throwable source) {
		super(message, source);
	}

	public List<XMLElementError> getListErro() {
		return listErro;
	}

	public void setListErro(List<XMLElementError> listErro) {
		this.listErro = listErro;
	}
		
}
