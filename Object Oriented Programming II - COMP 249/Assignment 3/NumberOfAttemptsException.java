/**
 * Édouard Gagné #40061204
 * COMP249
 * Assignment #3
 * Due March 18 2018
 */
// -----------------------------------------------------
// Assignment 3
// Written by: Édouard Gagné 40061204
// This class is used to create exceptions for when the user fails too many time to enter a correct file name.
// -----------------------------------------------------
import java.io.IOException;
import java.io.FileNotFoundException;
/**
 * NumberOfAttemptsException class that is thrown by FileNotFOundExceptions in the BibCreator class.
 * 
 * @author edouard
 * @version 1.0
 * @see BibCreator.java
 */
public class NumberOfAttemptsException extends Exception{
	private static final int MAX_ATTEMPTS=2;
	private int attempts;
	/**
	 * Default constructor for NumberOfAttemptsException.
	 * 
	 */
	public NumberOfAttemptsException() {
		attempts=0;
	}
	/**
	 * Constructor for NumberOfAttemptsException.
	 * 
	 * @param message string containing the message that will be used to display the error message.
	 */
	public NumberOfAttemptsException(String message) {
		super(message);
		attempts=0;
	}
	/**
	 * Method that increments the number of attempts before the program exit. It will try to execute the displayFileContent method
	 * if the maximum number of attempts isn't reached.
	 * 
	 */
	public void incNbAttempts(){
		attempts++;	
		// This if statement will execute if their are no attempts left.
		if (attempts>=MAX_ATTEMPTS) {
			System.out.print("File not found! You have no more attempts left. The program will now exit.");
			System.exit(0);
		}
		// Else, this statement will try again to display the content of the file or throw the exception if the file name is still incorrect.
		else {
			System.out.print("File not found! You have " + attempts + "attempt left. ");
			try {
				BibCreator.displayFileContent();
			} catch(FileNotFoundException e) {
				try {
					throw (this);
				} catch (NumberOfAttemptsException e2) {
					e2.incNbAttempts();
				}
			} catch (IOException e) {
				System.out.println("An error as occured while opening the file, system will now exit.");
				System.exit(0);
			}
		}
	}

}
