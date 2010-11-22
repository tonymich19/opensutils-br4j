package org.opensutils.xml;
/**
 * <code>ElementError</code> is a class to represent an error that occurred during validation of an xml
 * @author Felipe
 *
 */
public class XMLElementError {
	private int row;
	private int column;
	private String error;
	
	public XMLElementError( int row,
							int column, 
							String error) {
		this.row = row;
		this.column = column;
		this.error = error;
	}
	/**
	 * Get the line number that is in error
	 * @return
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Set the line number that is in error
	 * @return
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * The message of error
	 */
	public String getError() {
		return error;
	}
	/**
	 * The message of error
	 */
	public void setError(String error) {
		this.error = error;
	}	
	public String toString(){
		return row + " - " + error;
	}
	/**
	 * Get the column number that is in error
	 * @return number column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * Set the column number that is in error
	 * @return
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
}
