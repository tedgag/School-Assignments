// -------------------------------------------------------
// Assignment 3
// Written by: Édouard Gagné 40061204
// For COMP 248 Section P A Fall 2017
// This program asks the user for the number of sequences he/she wants in a Fibonacci sequence and prints the resulting series. It then
// asks the user if he/she wants to repeat the process and do it if the user enters "Yes", or stop the program if the user enters "No".
//--------------------------------------------------------

// Importing the Scanner class.
import java.util.Scanner;
public class A3Q1 {

	public static void main(String[] args) {
		// Declaring the "repeat" variable to determine if the program will restart and initializing it to "true"
		boolean repeat=true;
		
		// The following line defines the variable "keyboard" as a new scanner to read the user input.
		Scanner keyboard = new Scanner(System.in);
		
		// Welcome message
		System.out.println("*** Fibonacci Sequence Generator ***\n");
		
		/* The following while loop will repeat while the "repeat" variable is "true". It first ask the user for a number of sequences and stores it under
		the variable "n". It then sets the number to "1" if the user entered a negative "n". After that, it will print out the fibonnaci sequences, printing
		the variables "j" and "k" which are initialized to "1" first, then it will output the following numbers by adding "j" to "k" and storing it under the
		variable "output", setting the value of "k" to the value of "j" and then set the value of "j" to the value of "output", repeating this process depending 
		on the variable "n". Finally, it asks the user if he wants to repeat, if the user enters "Yes", repeat is set to "true" and if he enter "No", repeat
		is set to "false" and the while loop ends. */
		while (repeat==true)		{
			int j=1,k=1,output=0;
			System.out.println("Please enter the number of sequences");
			int n= keyboard.nextInt();
			if (n<0)
				n=1;
			System.out.println();
			for (int i=1;i<=n;i++) {
				if (i==1)
					System.out.print("The results is: " + j );
					else if (i==2)
						System.out.print("," + k );
						else		{
							output=j+k;
							System.out.print("," + output);
							k=j;
							j=output;
						}			
			}
			System.out.println("\nDo you want to repeat? (Yes/No)");
			String answer = keyboard.next();
			if (answer.equals("Yes"))
				repeat = true;
			else
				repeat = false;
			System.out.println();
			}
		
		// Closing message.
		System.out.println("** Enjoy your Fibonacci Sequence(s) **");
		
		// Closing scanner
		keyboard.close();
		}
	}


