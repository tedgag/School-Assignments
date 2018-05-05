// -------------------------------------------------------
// Assignment 2
// Written by: Édouard Gagné 40061204
// For COMP 248 Section P A Fall 2017
// This programs ask the user for a day of the week as a number from 1 to 7 then outputs a message based on the chosen day using an if-else statement.
//--------------------------------------------------------

// Importing the Scanner class
import java.util.Scanner;

public class Q1V1 {
		
	public static void main(String[] args) {
		
		// Welcome message
		System.out.println("***Welcome to number-to-day converter!***/n");
		// The following line defining the variable "input" as a new scanner to read the user input
		Scanner input= new Scanner (System.in);
		// The following lines asks the user for a day of the week as a number and stores it under the variable "day".
		System.out.println("Please enter the day of the week as a number (1-7): ");
		int day = input.nextInt();
		
		/* The following if-else statement works as follow: if the day is a number from 1 to 5, it will display what day it is and that it is a weekday,
		if the day is a number from 6 to 7, it will display what day it is and that it is the weekend, or else if the day is not valid it will display an 
		error message. */
		if (day == 1 )
				System.out.println("It's Monday! It's a weekday!");
			else if (day == 2)
				System.out.println("It's Tuesday! It's a weekday!");
			else if (day == 3)
				System.out.println("It's Wednesday! It's a weekday!");
			else if (day == 4)
				System.out.println("It's Thursday! It's a weekday!");
			else if (day == 5)
				System.out.println("It's Friday! It's a weekday!");
			else if (day == 6)
				System.out.println("It's Saturday! It's the weekend!");
			else if (day == 7)
				System.out.println("It's Sunday! It's the weekend!");
			else
				System.out.println("That's not a valid day!");
		
		// Closing message
		System.out.println("/n***Thank you for using the number-to-day converter!***");
		// Closing the Scanner
		input.close();
			
		}

}
