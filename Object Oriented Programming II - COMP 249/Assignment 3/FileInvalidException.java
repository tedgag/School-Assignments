/**
 * Édouard Gagné #40061204
 * COMP249
 * Assignment #3
 * Due March 18 2018
 */
// -----------------------------------------------------
// Assignment 3
// Written by: Édouard Gagné 40061204
// This class is used to create exceptions for when a file is missing an information.
// -----------------------------------------------------
/**
 * FileInvalidException class that will be thrown in the BibCreator class.
 * 
 * @author edouard
 * @version 1.0
 */
public class FileInvalidException extends Exception {
	/**
	 * Constructor for FileInvalidException.
	 * 
	 * @param missingInfo string containing the missing information in the file.
	 */
	public FileInvalidException(String missingInfo) {
		super("File is invalid: field \""+ missingInfo +"\" is empty. Processing stopped at this point. Other empty fields may be present as well!\n");
	}
}
