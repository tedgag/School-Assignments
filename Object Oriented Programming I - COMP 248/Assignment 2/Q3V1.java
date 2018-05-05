// -------------------------------------------------------
// Assignment 2
// Written by: Édouard Gagné 40061204
// For COMP 248 Section P A Fall 2017
// This programs ask how many times the user would like to order food from a delivery service in a month and compute the price based on three subscriptions
// choices: pay per delivery, where the user pay 3.00 per delivery, OccasionalFoodie, where the user pay 15 $ for 6 deliveries per month with 2$ per additional 
// deliveries and MontrealFoodie, where the user pay 30$ for 12 deliveries per month with 1.50$ of additional deliveries. It will then recommend the subscription
// that will save the most money by comparing it to the others computing the money saved.
//--------------------------------------------------------

// Importing the Scanner class.
import java.util.Scanner;

public class Q3V1 {

	public static void main(String[] args) {
		
		// Welcome message.
		System.out.println("***FoodieDelivery Subscription Calculator*** \n");
		// The following line defines the variable "input" as a new scanner to read the user input.
		Scanner input= new Scanner(System.in);
		// Defining the variables of type double "PayPerDelivery", "OccasionalFoodie" and "MontrealFoodie" that will store the cost of the subscriptions.
		double PayPerDelivery, OccasionalFoodie, MontrealFoodie;
		// The following line will ask the user for the number of times he plans to order food per month and store it under the variable "times".
		System.out.println("Please enter the number of times a month you use food delivery services: ");
		int times = input.nextInt();
		// The following line will compute and display the Pay per delivery subscription price based on the "times" variable, multiplying it by 3.00$.
		PayPerDelivery = 3.00 * times;
		System.out.print("The cost of Pay per delivery would be: $");
		System.out.printf("%4.2f",PayPerDelivery);
		System.out.println("/month");
		/* The following if/else statement will compute and display the OccasionalFoodie subscription price based on the "times" variable, which is 15.00$ if 
		for less than 6 deliveries per month and if it is more than 6, it will add the number of additional times and multiply it by 2.00$.*/
		if (times <= 6)
			OccasionalFoodie = 15.00;
		else
			OccasionalFoodie = 15.00 + (times - 6)*2.00;
		System.out.print("The cost of OccasionalFoodie would be: $");
		System.out.printf("%4.2f",OccasionalFoodie);
		System.out.println("/month");
		/* The following if/else statement will compute and display the MontrealFoodie subscription price based on the "times" variable, which is 15.00$ if 
		for less than 12 deliveries per month and if it is more than 12, it will add the number of additional times and multiply it by 1.50$.*/
		if (times <= 12)
			MontrealFoodie = 30.00;
		else
			MontrealFoodie = 30.00 + (times - 12)*1.50;
		System.out.print("The cost of MontrealFoodie would be: $");
		System.out.printf("%4.2f",MontrealFoodie);
		System.out.println("/month \n");
		/* The following if statements will display the recommended subscription based on which prices computed before are the lowest. It will also display the 
		money save by choosing this subscription versus the other subscriptions prices.*/
		// The following statement will process if PayPerDelivery is the cheapest subscription.
		if (PayPerDelivery < OccasionalFoodie && PayPerDelivery< MontrealFoodie)	{
			System.out.println("**We recommend getting the Pay per delivery subscription.**");
			System.out.print("You would save $");
			System.out.printf("%4.2f", (OccasionalFoodie-PayPerDelivery));
			System.out.print(" from Occasional Foodie and $");
			System.out.printf("%4.2f", (MontrealFoodie-PayPerDelivery));
			System.out.println(" from MontrealFoodie.");
		}
		// The following statement will process if OccasionalFoodie is the cheapest subscription.
		if (OccasionalFoodie <= PayPerDelivery && OccasionalFoodie< MontrealFoodie)	{
			System.out.println("**We recommend getting the OccasionalFoodie subscription..**");
			System.out.print("You would save $");
			System.out.printf("%4.2f", (PayPerDelivery - OccasionalFoodie));
			System.out.print(" from Pay per delivery and $");
			System.out.printf("%4.2f", (MontrealFoodie - OccasionalFoodie));
			System.out.println(" from MontrealFoodie.");
			}
		// The following statement will process if MontrealFoodie is the cheapest subscription.
		if (MontrealFoodie <= PayPerDelivery && MontrealFoodie<OccasionalFoodie)		{
			System.out.println("**We recommend getting the MontrealFoodie subscription..**");
			System.out.print("You would save $");
			System.out.printf("%4.2f", (PayPerDelivery-MontrealFoodie));
			System.out.print(" from Pay per delivery and $");
			System.out.printf("%4.2f", (OccasionalFoodie-MontrealFoodie));
			System.out.println(" from OccasionalFoodie.");
			}
		// Closing message.
		System.out.println("\n***Thank you for using my FoodieDelivery Subscription Calculator! Have a nice lunch!***");
		// Closing input.
		input.close();
		}

}
