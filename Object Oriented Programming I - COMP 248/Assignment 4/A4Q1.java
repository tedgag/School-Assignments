//-------------------------------------------------------
// Assignment 4
// Written by: Édouard Gagné 40061204
// For COMP 248 Section P A Fall 2017
// This program will predict the outcome of a tournament with 8 teams chosen by the user. It will first ask the user for the tournament name
// and the team names, then it will output the winner of the quarter-finals, the winners of the semi-finals and the winner of the final.
// It will then ask the user if he wants another outcome and prints a new set of winners if the user wants to, or end the program.
//--------------------------------------------------------

// Importing scanner class and random
import java.util.Scanner;
import java.util.Random;

public class A4Q1 {

	public static void main(String[] args) {
		// Declaring and initializing the variable "repeat" that will be used when the user will chose whether he/she wants a different outcome or not 
		Boolean repeat=true;
		
		// Defining the variable "input" to be used as a new scanner to read the user's input
		Scanner input= new Scanner(System.in);
		
		// Welcome message
		System.out.println("Welcome to Édouard's Tournament Outcome Predictor Program\n");
		
		// The following lines will ask the user for the name of the tournament and store it under the variable "name";
		System.out.println("Please enter the name for the soccer tournament: ");
		String name= input.nextLine();
		
		// The following lines will ask the user for the name of the 8 teams using a for loop and store all the name under a 1-D array called "teams"
		System.out.println("\nPlease enter the 8 participating teams");
		String[] teams = new String[8];
		for (int i=0; i<8;i++)	{
			teams[i]= input.nextLine();
		}
		
		// Start of the do-while loop that will keep repeating until the user choose not to
		do {	 
			// The following line will print a message announcing the random results of the tournament
			System.out.println("\n" + name + " Outcome Predictions\n");
			
			// Defining the variable "random" to be used as a new random method to pick a random number from 0 to the chosen number
			Random random = new Random();
			
			/* The following for loop will randomly shuffle the content of the "teams" array by switching the spot of first team with the spot of a
			random team, and do the same thing 7 more times. For example, team1 could switch place with team5, team 2 with team4, etc. */
			for (int i=0 ; i < 8; i++)	{
				int j = random.nextInt(7);
				String temp= teams[i];
				teams[i]=teams[j];
				teams[j]=temp;	
			}
			// The following line will declare a 1-D array named "winnersQF" that will be used to store the winners of the quarter finals.
			String[] winnersQF= new String [4];
			
			/*	The following for loop will randomly decide the winners of the fours quarter finals by announcing the teams fighting each-others
			 in the first quarter final, which are the first and second teams in the randomized "teams" array, then it will decide the winner by essentially
			 flipping a coin and announce this winner. The loop will then repeat the process for the other quarter finals.*/
			for (int i=0,j=0; i<8;i+=2)	{
				System.out.println("Quarter final "+ (j+1) +": " + teams[i] + " Vs " + teams[i+1]);
				
				if	(random.nextBoolean())
					winnersQF[j]= teams[i];
				else
					winnersQF[j] = teams[i+1];
				System.out.println(winnersQF[j] + " Wins!!\n");
				j++;
			}
			
			/* The following for loop will randomly shuffle the content of the "winnersQF" array in the same way the "teams" array was shuffled to determine
			 which teams will be against which other teams in the semi finals*/
			for (int i=0 ; i < 4; i++)	{
				int j = random.nextInt(3);
				String temp= winnersQF[i];
				winnersQF[i]=winnersQF[j];
				winnersQF[j]=temp;	
			}
			
			// The following line will declare a 1-D array named "winnersSF" that will be used to store the winners of the semi finals.
			String[] winnersSF= new String [2];
			
			/* The following for loop will randomly decide the winners of the two semi finals and announce the winnersin the same way as the for loop that decided
			 the winners of the quarter finals */
			for (int i=0,j=0; i<4;i+=2)	{
				System.out.println("Semi final "+ (j+1) +": " + winnersQF[i] + " Vs " + winnersQF[i+1]);
				
				if	(random.nextBoolean())
					winnersSF[j]= winnersQF[i];
				else
					winnersSF[j] = winnersQF[i+1];
				System.out.println(winnersSF[j] + " Wins!!\n");
				j++;
			}
			
			/* The following for loop will randomly decide the winner of the tournament by a coin flip between the two semi finals winners and store it under the variable
			"winner"*/
			System.out.println("Finals: " + winnersSF[0] + " Vs " + winnersSF[1]);
			String winner;
			if	(random.nextBoolean())				
				winner= winnersSF[0];
			else
				winner= winnersSF[1];
			
			// Printing the winner of the tournament
			System.out.println(winner + " Wins!!\n");
			
			/* The following section will ask the user if he/she wants a different outcome and if she answer "y", repeat will be assigned to "true" and the do-while
			loop will continue, else it will be assigned to false and it will stop */
			System.out.println("Do you want a different outcome? y or n");
			String answer= input.next();
			if (answer.equals("y")==true)
				repeat=true;
			else
				repeat=false;
		} while (repeat == true);
		
		// Closing message
		System.out.print("\nThank you for using the JAVA Tournament Winner Predictor Program!");
		
		// Closing input
		input.close();
	}

}
