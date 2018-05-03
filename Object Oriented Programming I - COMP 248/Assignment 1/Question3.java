// -------------------------------------------------------
// Assignment 1
// Written by: Édouard Gagné 40061204
// For COMP 248 Section P – Fall 2017
// This programs will ask the user for two words and will display the first and last characters of the two words and create some new words using these characters.
// -------------------------------------------------------

import java.util.Scanner;

public class Question3 {

	public static void main(String[] args) 
	{
		// Variable declaration
		Scanner scannerObject = new Scanner (System.in);
	
		// Welcome message
		System.out.println("Welcome to Édouard's words blender!" + "\n");
		
		// The following section will be used to ask for the two words under the variables word1 and word2 and read them to be used later.
		System.out.println("Enter 2 words on on line separated by at least one space (no white space allowed IN each word): ");
		String word1 = scannerObject.next();
		String word2 = scannerObject.next();
		System.out.println("\n");
		
		// The following line will display the first word stored under word1, the length of the first word and the first and last character of the word.
		System.out.println("First word you entered is <" + word1 + "> which is " + word1.length() + " character long." +"\n"
				+ "It starts with the character '" + word1.substring(0,1) + "' and ends with the character '" + word1.substring(word1.length()-1)+"'." + "\n");
		
		// The following line will do the same for the second word stored under word2.
		System.out.println("First word you entered is <" + word2 + "> which is " + word2.length() + " character long." +"\n"
				+ "It starts with the character '" + word2.substring(0,1) + "' and ends with the character '" + word2.substring(word2.length()-1)+"'." + "\n");
		
		/* The following line will create and print two new words such as the new first word starts and end with the first and last character of the second word 
		stored under word 2 and the new second word start and end with the first and last character of the first word stored under word1. */
		System.out.println("New words: " + word2.substring(0,1) + word1.substring(1,word1.length()-1) + word2.substring(word2.length()-1) + " " 
		+ word1.substring(0,1) + word2.substring(1,word2.length()-1) + word1.substring(word1.length()-1)+ "\n");
		
		// Closing message
		System.out.println("Enjoy your two new words!");
	}

}
