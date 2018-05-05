//-------------------------------------------------------
// Assignment 3
// Written by: Édouard Gagné 40061204
// For COMP 248 Section P A Fall 2017
// This program will ask the user for information about a purchase and compute the taxes, subtotal and total. It will ask for the number of items,
// the rate of the two taxes and the price of the items and print the result. It will also print out the number of times the user entered an invalid
// input depending on limits on the input determined in advance.
//--------------------------------------------------------

// Importing the Scanner class.
import java.util.Scanner;
public class A3Q2 {

	public static void main(String[] args) {
		// Welcome message
		System.out.println("$$$ Welcome to Édouard's Cashier Calculator Program $$$\n");
		
		// The following line defines the variable "keyboard" as a new scanner to read the user input.
		Scanner keyboard = new Scanner(System.in);
		
		/* Declaring  and settingthe constants to their values, notably the minimum number of items (MINN) and maximum number of items (MAXN), 
	 	the minimum tax rate (MINTAX) and the maximum tax rate (MAXTAX) and the minimum price per item (MINPRICE) and the maximum price per 
		item (MAXPRICE).*/
		final int MINN = 1, MAXN=10, MINTAX=1, MAXTAX=100;
		final double MINPRICE = 1, MAXPRICE = 1000;
		
		/* This line will declare the variable "n" which is the number of items, the tax rate 1 "tax1Rate" , the tax rate 2 "tax2Rate", the "tax1"
		computed with the item price and the "tax1Rate" , "tax2" computed with the item price and the "tax2Rate", the "SubTotal" initialized to 0
		and finally the "total". It will also declare and initialize the variable"a", "b" and "c" to "-1", which will be used to compute the number 
		of times the user entered an incorrect input.*/
		int n, a=-1,c=-1, d=-1;
		double tax1Rate, tax2Rate, tax1, tax2, SubTotal=0, total;
		
		/* The following do-while loop will asks the user for the number of items bought from 1 to 10 and stores it in "n" and add 1 to the "a" variable.
		If the input is invalid, the loop will continue until the input is valid.*/
		do {
			System.out.println("Please enter the number of items bought [1...10]: ");
			n= keyboard.nextInt();
			a++;
		} while ( n < MINN || n > MAXN);
		System.out.println();
		
		// Declaring an array named "price" that will store the price of each items.
		double[] prices = new double[n];
		
		// Declaring the variable "b" that will be used to compute the number of invalid input in this section.
		int b=-n;
		
		/* The following for loop will execute a do loop depending on the number of items. The do-while loop will ask the user for the price of the first item
		and stores it in "price" as the first value, then add 1 to the variable "b". If the value is invalid, it will continue the loop. When the loop stops, it 
		will restart for the second price, and so on for "n" times */
		for (int i=0;i<n;i++)	{
			do {
				System.out.println("Please enter the price of items " + (i+1) + " [1...1000]: ");
				prices[i] = keyboard.nextDouble();
				b++;
			} while (prices[i] < MINPRICE || prices[i] > MAXPRICE);
		System.out.println();
		}
		
		/* The following do-while loop will asks the user for the tax rate of the first tax from 0 to 100 and stores it in "taxRate1" and add 1 to the "c" variable.
		If the input is invalid, the loop will continue until the input is valid.*/
		do {
			System.out.println("Please enter the tax rate of Tax1 [0...100]:");
			tax1Rate= keyboard.nextDouble();
			c++;
		} while (tax1Rate < MINTAX || tax1Rate > MAXTAX);
		System.out.println();
		
		/* The following do-while loop will asks the user for the tax rate of the second tax from 0 to 100 and stores it in "taxRate2" and add 1 to the "d" variable.
		If the input is invalid, the loop will continue until the input is valid.*/
		do {
			System.out.println("Please enter the tax rate of Tax2 [0...100]:");
			tax2Rate= keyboard.nextDouble();
			d++;
		} while (tax2Rate < MINTAX || tax2Rate > MAXTAX);
		
		// This line will print out a line presenting the results.
		System.out.println("\nHere are your results\n---------------------");
		
		/* This lie will declare and initialize the variable "sumInvalid" that will add up the number of time the input was invalid, stored under the variables "a"
		"b", "c" and "d". */
		int sumInvalid= a+b+c+d;
		
		//The following if statement will print out the the number of times the input was invalid if it is greater than 0.
		if (sumInvalid > 0)
			System.out.println("You have entered " + sumInvalid + " invalid inputs.");
		
		// The following for loop will add up all the elements of the array "price" and store it under the variable "SubTotal".
		for (int j=0;j<n;j++)	{
			SubTotal+= prices[j];
		}
		
		// The following section will simply print out the subtotal.
		System.out.print("Subtotal: $ ");
		System.out.printf("%4.2f", SubTotal);
		System.out.print("\n");
		
		// The following section will compute the first tax and store it under the variable "tax1" then print the result.
		tax1= SubTotal* tax1Rate/100;
		System.out.print("Tax 1: $ ");
		System.out.printf("%4.2f", tax1);
		System.out.print("\n");
		
		// The following section will compute the second tax and store it under the variable "tax2" then print the result.
		tax2= (SubTotal+ tax1)* tax2Rate/100;
		System.out.print("Tax 2: $ ");
		System.out.printf("%4.2f", tax2);
		System.out.print("\n");
		
		// The following section will compute the total and store it under the variable "total" then print the result.
		total= SubTotal + tax1 + tax2;
		System.out.print("TOTAL: $ ");
		System.out.printf("%4.2f", total);
		
		// Closing message
		System.out.print("\n\nHope you shopping trip was an enjoyable one!");
		
		// Closing scanner
		keyboard.close();
	}

}
