// -------------------------------------------------------
// Assignment 1
// Written by: Édouard Gagné 40061204
// For COMP 248 Section P – Fall 2017
// This programs will ask the user for a few simple informations and display a message using these informations.
// -------------------------------------------------------

// Importing the scanner class
import java.util.Scanner;

public class Question2 
{
	public static void main(String[] args) 
	{
		// Variables declaration
		Double fever;
		Scanner scannerObject = new Scanner(System.in);
		
		// Welcome message
		System.out.println("Édouard's excuse generator."+"\n");
		
		/* The following section will ask the user for a teacher name, his name, a food, a temperature, an animal, an adjective for a pet and a colour 
		and store them under the variables teacherName, name, food, fever, animal, adjective and colour.*/
		System.out.print("What is your teacher's first or last name? "); String teacherName = scannerObject.next();
		System.out.print("What is your name? "); String name = scannerObject.next();
		System.out.print("Enter a food: "); String food = scannerObject.next();
		System.out.print("Enter the amount of fever (between 37.0 and 40.0) : "); fever = scannerObject.nextDouble();
		System.out.print("Enter an animal: "); String animal = scannerObject.next();
		System.out.print("Enter an adjective that will describe your pet: "); String adjective = scannerObject.next();
		System.out.print("Enter a colour: "); String colour = scannerObject.next();
		System.out.print("\n"+"\n");
		
		// The following line will display the message using the informations in the variables collected during the previous section.
		System.out.print("Dear professor "+ teacherName +","+"\n"+"\n"
		+"I am sorry that I am unable to turn in my computer assignment at this time. First,"+"\n"
		+"I ate a rotten "+ food +", which made me turn " + colour + " and extremely ill. I came down"+"\n"
		+"with a fever of " + fever + ". Next my " + adjective + " pet " + animal + " must have smelled the"+"\n"
		+"remains of the " + food + " on my usb key (which is where the program was stored)"+"\n"+ 
		"because he ate it."+"\n"
		+"I am curently rewriting my program and hope you will accept my assigment late."+"\n"+"\n"
		+"Sincerly,"+"\n"
		+ name +"\n"+"\n" );
		
		// Closing message
		System.out.println("Enjoy your new crafted excuse!");
	}
}
