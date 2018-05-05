//-------------------------------------------------------
// Assignment 4
// Written by: Édouard Gagné 40061204
// For COMP 248 Section P A Fall 2017
// The program will perform a "magic trick", by showing the user a semi-random 4 by 4 set of cards from a classic playing card deck, the ask the user to 
// choose a card from the set. Then the program will switch the columns with the lines of the set (the columns will become lines and vice-versa) and ask
// the user where his/her card is now. This will pinpoint the exact location of the card, since we know which column and line it will be in, and it will
// print the result. The program will then ask the user if she/he wants to restart the process.
//--------------------------------------------------------

// Importing scanner class and random
import java.util.Random;
import java.util.Scanner;

public class A4Q2 {

	public static void main(String[] args) {
		// Welcome message
		System.out.println("Welcome to Card Magic 101\n");
		
		// Declaring a 1-D array named "numbers" that will store the numbers of the cards, from ace to king
		String[] numbers= new String[] {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
		
		// Defining the variable "random" to be used as a new random method to pick a random number from 0 to the chosen number
		Random random = new Random();
		
		// Defining the variable "input" to be used as a new scanner to read the user's input
		Scanner input= new Scanner(System.in);

		/* Declaring and initializing the variable "repeat" that will be used when the user will chose whether he/she wants a different outcome or not, the
		variable "loop" that will be used to check if the user input is valid, and the variables "column1" and "column2" to store the user's inputs */
		Boolean repeat, loop=true;
		int column1=0, column2=0;
		
		// Start of the do-while loop that will keep repeating until the user choose not to
		do	{
			/* The following for loop will randomly shuffle the content of the "numbers" array by switching the spot of first number with the spot of a
			random number, and do the same thing 12 more times. For example, "Ace" could switch place with "King", "Two" with "Nine", etc. */
			for (int i=0 ; i < 13; i++)	{
			int j = random.nextInt(12);
			String temp= numbers[i];
			numbers[i]=numbers[j];
			numbers[j]=temp;	
			}
			
			// Declaring a 2-D array called "cards" that will be used to store randomly chosen numbers from the "numbers" array and pair it with a symbol
			String [][] cards = new String [4][4];
			
			/* The following nested for loop will assign the four first numbers in the randomized "numbers" array and will assign them to each symbols, "Diamonds",
			"Hearts", "Clubs" and "Spades" and store them in the 2-D array "cards" that will be printed.*/
			for (int i=0; i<4;i++)	{
				for	(int j=0; j<4; j++)	{
					switch (i)	{
					case 0: cards[i][j]= numbers[j] + " of Diamonds";
					break;
					case 1:	cards[i][j]= numbers[j] + " of Hearts";
					break;
					case 2:	cards[i][j]= numbers[j] + " of Clubs";
					break;
					case 3:	cards[i][j]= numbers[j] + " of Spades";
					break;
					}
				}
			}
			
			/* Following for loop will print the "cards" array by printing the first number with each of the symbols in the first column, the second number and each of 
			the symbols in the second column, same for the third and fourth number. The result will be a 4x4 table listing the previously determined combinations of cards*/
			for (int i=0; i<4; i++)	{
				for (int j=0; j<4; j++)	{
						System.out.printf("%1$-22s", cards[i][j]);
				}
			System.out.println();
			}
			/* The following lines will asks the user to choose a card and enter the column number where it appears, then store the result under the variable "column1".
			It will execute if the column number entered is not an integer between 1 and 4, it will ask the user for another input and loop until the input is valid */
			loop=true;
			System.out.println("Please pick a card and enter the column number (1-4) where it appears");
			do {
				if (input.hasNextInt()) {
					column1 = input.nextInt();
					if (column1 >= 1 && column1<=4)
						loop=false;
					else 
						System.out.println("Please enter an integer value between 1 and 4");
				}
				else {
					System.out.println("Please enter an integer value between 1 and 4");
					input.next();
				}
			}	while (loop==true);
			
			/* The following for loop will print the cards array, this time by printing the first number with each of the symbols in the first line, same for second,
			third and fourth number. The result will be a new 4x4 table listing the combinations of cards */
			for (int i=0; i<4; i++)	{
				for (int j=0; j<4; j++)	{
					System.out.printf("%1$-22s", cards[j][i]);
				}
				System.out.println();
			}
			
			/* The following lines will asks the user to choose a card and enter the column number where it appears, then store the result under the variable "column2".
			It will execute if the column number entered is not an integer between 1 and 4, it will ask the user for another input and loop until the input is valid */
			loop=true;
			System.out.println("Please indicate which column (1-4) it is in now");
			do {
				if (input.hasNextInt()) {
					column2 = input.nextInt();
					if (column2 >= 1 && column2<=4)
						loop=false;
					else 
						System.out.println("Please enter an integer value between 1 and 4");
				}
				else {
					System.out.println("Please enter an integer value between 1 and 4");
					input.next();
				}
			}	while (loop==true);
			
			// The following line will output the card chosen by the user depending on the numbers he/she entered stored in "column1" and "column2"
			System.out.println("Your card is " + cards[column2-1][column1-1]);
			
			/* The following section will ask the user if he/she wants a different outcome and if she answer "y", repeat will be assigned to "true" and the do-while
			loop will continue, else it will be assigned to false and it will stop */
			System.out.println("Do you want to try one more time? y or n");
			String answer = input.next();
			if (answer.equals("y"))
				repeat=true;
			else
				repeat=false;
		} while (repeat==true);
		
		// Closing message
		System.out.println("Thank you for usng the JAVA Magic 101 program");
		
		// Closing input
		input.close();
	}

}
