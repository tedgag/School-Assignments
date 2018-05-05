//-------------------------------------------------------
//Assignment 5
//Written by: Édouard Gagné 40061204
//For COMP 248 Section P A Fall 2017
//This method will ask the user for informations about two cars and put them on a race track at different locations . It will then flip the direction of one
//of the cars so both cars are facing each others. Then the race will start and the two cars will get closer and closer to each others and the program will print
//their state at each moment. The program will stop when the two cars meet each others and crash
//--------------------------------------------------------

// Importing the scanner class
import java.util.Scanner;
public class RaceLane1 {
	
	public static void main(String[] args) {
		// Printing a welcome message
		System.out.println("\n***** Welcome to Édouard's demolition derby race track! *****\n");
		
		// Defining the variable "keyboard" to be used as a new scanner to read the user's input 
		Scanner keyboard = new Scanner(System.in);
		
		/* The next few lines will ask the user for the model, location and speed of the first car and store these informations under the variables "model1",
		"location1" and "speed1"*/
		System.out.println("Please enter the model of the first car:");
		String model1 = keyboard.nextLine();
		System.out.println("Please enter the location of the first car:");
		int location1 = keyboard.nextInt();
		System.out.println("Please enter the speed of the first car:");
		int speed1 = keyboard.nextInt();
		keyboard.nextLine();
		
		// The following line will create a "car" object using the variables declared in the section above
		Car car1=new Car(model1, speed1, location1);
		
		/* The next few lines will ask the user for the model, location and speed of the second car and store these informations under the variables "model2",
		"location2" and "speed2"*/
		System.out.println("Please enter the model of the second car:");
		String model2 = keyboard.nextLine();
		System.out.println("Please enter the location of the second car:");
		int location2 = keyboard.nextInt();
		System.out.println("Please enter the speed of the second car:");
		int speed2 = keyboard.nextInt();
		
		// The following line will create a "car" object using the variables declared in the section above
		Car car2=new Car(model2, speed2, location2);
		
		// The following lines will print the informations of the "car" objects declared above
		System.out.println(car1.toString());
		System.out.println(car2.toString());
		
		// The following if/else statement will determine the right-most car on the race track and flip its direction, making it face the other car
		if (car1.getLocation() >= car2.getLocation()) {
			car1.turnAround();
		}
		else		{
			car2.turnAround();
		}
		
		// The following lines will print the current states of the cars
		System.out.println(car1.toString());
		System.out.println(car2.toString());
		
		// The two following lines will start the cars, setting their current speed to their max speed
		car1.go();
		car2.go();
		
		// The following lines will print the current states of the cars
		System.out.println(car1.toString());
		System.out.println(car2.toString());
		
		/* The following if/else statement will move both of the cars in their respective directions until they cross eachothers using a while loop, 
		meaning until the two cars crashed ,and print their states at each iteration of the loop*/
		// This if statement will execute if the first car is moving from left to right
		if (car1.getDirection()==true)
			while (car2.getLocation()>car1.getLocation()) {
				car1.move();
				car2.move();
				System.out.println(car1.toString());
				System.out.println(car2.toString());
			} 
		// This else statement will execute if the second car is moving from left to right
		else
			while (car1.getLocation()>car2.getLocation()) {
				car1.move();
				car2.move();
				System.out.println(car1.toString());
				System.out.println(car2.toString());
			} 
		
		// Printing a message indicating the cars crashed
		System.out.println("Boom!!");
		
		// Printing a closing message
		System.out.println("\n***** I hope you enjoyed the show! *****");
		// Closing the scanner
		keyboard.close();
	}

}
