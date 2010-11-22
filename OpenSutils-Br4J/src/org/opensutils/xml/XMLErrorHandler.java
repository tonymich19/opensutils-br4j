package org.opensutils.xml;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class XMLErrorHandler implements ErrorHandler {

	private LinkedList <XMLElementError> listErro;

	public XMLErrorHandler(){
		listErro = new LinkedList <XMLElementError>();
	}
	
    public void error(SAXParseException sxpE) {
    	this.listErro.add( new XMLElementError(sxpE.getLineNumber() , sxpE.getColumnNumber(),sxpE.getMessage() ));
    }
         
    public void fatalError(SAXParseException sxpE) {
    	this.listErro.add( new XMLElementError(sxpE.getLineNumber() , sxpE.getColumnNumber(),sxpE.getMessage() ));
    }
         
    public void warning(SAXParseException sxpE) {
    	this.listErro.add( new XMLElementError(sxpE.getLineNumber() , sxpE.getColumnNumber(),sxpE.getMessage() ));
    }

	public List<XMLElementError> getErros() {
		return this.listErro;
	}
    
}
