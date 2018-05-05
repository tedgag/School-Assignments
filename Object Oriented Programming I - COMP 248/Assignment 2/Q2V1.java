// -------------------------------------------------------
// Assignment 2
// Written by: Édouard Gagné 40061204
// For COMP 248 Section P A Fall 2017
// This programs ask how fast was going a driver that got arrested on the highway then displays the fines the driver has to pay 
// and the demerit points the driver lost. After that, it asks if the driver had any demerit points prior to the arrest, it adds
// them to the new demerit points he lost and then evaluate if the driver lost his license.
//--------------------------------------------------------

// Importing the Scanner class.
import java.util.Scanner;

public class Q2V1 {

	public static void main(String[] args) {
		
		// Welcome message
		System.out.println("***Édouard's SpeedyFines calculator*** \n");
		// The following line defines the variable "input" as a new scanner to read the user input.
		Scanner input = new Scanner(System.in);
		// The following line will ask for the speed of the driver and store it under the variable "speed".
		System.out.print("How fast was the driver going? ");
		int speed= input.nextInt();
		/* The following if/else statement will display the penalty the driver will receive depending on how fast he was going.
		If he was going 100, no penalty. If he was going over 160, he loses his license and has to pay 520$. If he was going 
		100-109, he has to pay 65$. If he was going 110 to 119, he has to pay 120$ and gets 2 demerit points, 240$ and 5 demerit
		points for 120-139 and finally  360 and 7 demerit points for 140-159. */
		if(speed < 100)
				System.out.println("The driver was not speeding.");
			else if (speed >= 160)
				System.out.println("The fine is $520 and the driver loses his license.");
			else if (speed < 110 && speed > 100)
				System.out.println("The fine is 65$.");
			else if (speed<= 119 && speed >= 110)
				System.out.println("The fine is $120 and the driver gets 2 demerit points!");
			else if (speed <= 139 && speed >= 120)
				System.out.println("The fine is $240 and the driver gets 5 demerit points!");
			else if (speed <= 159 && speed >= 140)
				System.out.println("The fine is $360 and the driver gets 7 demerit points!");
		/* The following if/else statement will ask the user for the demerit points the driver  had before being stopped and 
		add them to the demerit points he gained based on the speed he was going, then it will evaluate if the driver lost his 
		license, meaning if he now has 12 or more demerit points, and displays an appropriate message.*/
		if (speed < 160 && speed > 110) {
			// Asking the user for the points the driver had before and storing them under the variable "points".
			System.out.print("How many demerit points did the driver have prior to being stopped? ");
			int points= input.nextInt();
				// Adding points based on the speed the user was going.
				if (speed<= 119 && speed >= 110)
					points = points + 2;
					else if (speed <= 139 && speed >= 120)
						points = points + 5;
							else if (speed <= 159 && speed >= 140)
								points = points + 7;
				// Evaluating if the driver lost his license and displaying an appropriate message if he did or did not loose his license.
				if (points < 12)
					System.out.println("The driver now has " + points + " demerit points.");
				else
					System.out.println("The driver has " + points +" demerit points and loses his license.");
			}
		// Closing message.
		System.out.println("\n***Thank you for using my SpeedyFines calculator!***");
		// Closing input.
		input.close();
			
	}

}
