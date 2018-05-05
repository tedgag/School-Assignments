//-------------------------------------------------------
//Assignment 5
//Written by: Édouard Gagné 40061204
//For COMP 248 Section P A Fall 2017
//This method will ask the user for any number of cars he wants to race and the number of laps in the race. It will then ask for informations
//about each cars in the race and then start the race. The race has two lanes and all the cars go in the same direction. The cars start to accelerate
//until they reach their maximum speed, and after the first lap, crashes can occur. A crash occur when three cars are at the same location at any moment
//(unless they finished the race), then the cars get removed from the race so they don't cause more crashes. The race stop when all cars crashed or 3 cars
//, then the program will print the winners of the race.
//--------------------------------------------------------

// Importing the scanner class
import java.util.Scanner;

public class RaceLane2 {

	public static void main(String[] args) {
		// Welcome message
		System.out.println("***** Welcome to Édouard's race track simulator! *****\n");
		
		// Defining the variable "keyboard" to be used as a new scanner to read the user's input
		Scanner keyboard=new Scanner(System.in);
		
		//  The following lines will ask the user for the number of cars and laps in the race and store them under the variables "nbCars" and "nblaps"
		System.out.println("How many cars are going to race:");
		int nbCars = keyboard.nextInt();
		System.out.println("How many laps:");
		int nbLaps = keyboard.nextInt();
		keyboard.nextLine();
		
		// The following line will create an array called "racingCars" of "car" object the size of the number of cars in the race
		Car racingCars[]= new Car[nbCars];
		
		// Declaring the variables "model" and "maxSpeed" that will store the models and maximum speed of the cars
		String model;
		int maxSpeed;
		
		/* The following for loop will ask the user for the model and max speed of a car, reate a new car object with them and store it in the first spot 
		in the "racingCars" array. It will do the same for the second car and all the other cars, depending on the amount cars the user chose for the race */
		for (int i=0; i<racingCars.length; i++) {
			System.out.println("Please enter the model of car " + i);
			model= keyboard.nextLine();
			System.out.println("Please enter the max speed of the car " + i);
			maxSpeed= keyboard.nextInt();
			keyboard.nextLine();
			racingCars[i]= new Car(model,maxSpeed);
		}
		
		/* Declaring and initializing the variables "carFinished" that will keep tracked of the number of cars that finished the race, "sameLocationCars" that 
		will keep track of the number of cars at a same location at any moment, "crashedCars" that keeps track of the number of cars that crashed at a moment
		, "currentCars" that keeps track of th number of cars currently in the race and "crashDetection" that determine if the crash detection will occur*/
		int carFinished=0, sameLocationCars=0, crashedCars, currentCars= nbCars;
		boolean crashDetection = false;
		
		// Declaring an array of "car" objects called "winners" that will be used to store the winners of the race
		Car winners[]=new Car[3];
		
		/* The following do while loop will keep looping until there are 3 winners, all the cars crashed or the amount of winners is equal to the number of cars 
		still in the race. Each iteration of this loop is a moment in the race */
		do {	
			// Setting the number of crashed car in the moment to 0
			crashedCars=0;
			
			/* The following for loop will accelerate each of the car until they are at their max speed and move them. It will then print the state of the car.
			If the car is stopped, it will only print its state. Then, if the car has crossed the finished line, it will stop the car and add it to the "winners"
			array. It will also start the crash detection if any car finish the first lap.*/
			for (int i=0; i<racingCars.length;i++) {
				// This if statement will execute if the car is still in the race, accelerating and moving it and printing its state
				if (racingCars[i].getStopped() == false) {
					racingCars[i].accelerate();
					racingCars[i].move();
					System.out.println(racingCars[i].toString());
				}
				// The following else statement will execute if the car is not in the race, only printing its state
				else
					System.out.println(racingCars[i].toString());
				/* The following if statement will store the car information in the "winners" array if it crosses the finish line the race and has not already 
				finished the race. It will then reduce the number of cars in the race by 1, increased the number of finished cars by 1 and stop the car. */
				if (racingCars[i].getLocation() > 100*nbLaps && racingCars[i].getStopped()==false) 	{
					winners[carFinished]=racingCars[i];
					carFinished++;
					currentCars--;
					racingCars[i].setStopped(true);
				}
				// The following if statement will execute only if the car finished the first lap and will start the crash detection
				if (racingCars[i].getLocation() >= 100)
					crashDetection=true;
			}
			
			// The following if statement will only execute if the crash detection is started by the for loop above
			if (crashDetection==true) {
				/* The following for, nested for and nested if statements will be executed for all the cars in the race. It will check how many cars are at same locations
				and are neither stopped or already crashed then add 1 to the number of cars at a same location if two cars are at a same location. Then, if three cars 
				are at the same location, the if statement will execute and crash the cars.*/
				for (int i=0; i<racingCars.length;i++) {
					// resetting the number of cars at a same location to 0
					sameLocationCars=0;
					/* For statement that will check how many cars are at the location of the car of the current iteration, then add 1 to the "sameLocationCars" variable if 
					it is the case.*/
					for (int j=0; j<racingCars.length; j++)	{
						if (racingCars[i].getLocation()==racingCars[j].getLocation() && racingCars[j].getCrashed()==false && racingCars[j].getStopped()==false)
							sameLocationCars++;
					}
					// This if statement will only execute if there are 3 cars or more at the same location
					if (sameLocationCars>2) {
						// This for loop will stop all the cars in the race, then crash any cars at the same location of the car of the current iteration
						for (int k=0; k<racingCars.length; k++) {
							racingCars[k].stop();
							/* This if statement will execute only if two cars are at a same location, then crash the cars and reduce the number of cars in the race and the number
							of total cars by 1 and add 1 to the number of crashed cars.*/
							if (racingCars[i].getLocation() == racingCars[k].getLocation())	{
								racingCars[k].setCrashed(true);
								currentCars--;
								crashedCars++;
								nbCars--;
							}
						}
					}
				}
				
				// The following if statement will execute only if there are cars that crashed at this moment in the race and will print the state of the cars that crashed
				if (crashedCars!=0) {
					System.out.println("The following cars got crashed:");
					/* This for loop and if statement will check for each car if the car is crashed and not stopped, then print the state of the cars that respond to these
					conditions. It will then stop the cars so they are out of the race. */
					for (int i=0; i<racingCars.length; i++) {
						if (racingCars[i].getCrashed()==true &&racingCars[i].getStopped()==false)	{
							System.out.println(racingCars[i].toString());
							racingCars[i].setStopped(true);
						}
					}
				}
				
				// The following lines will print the number of cars that got crashed at this moment in the race and the number of cars remaining in the race
				System.out.println("Number of crashed cars: " + crashedCars);
				System.out.println("Number of current cars in race " + currentCars);
			}
		} while (carFinished < 3 && carFinished < nbCars);
		
		// Announcing the winners
		System.out.println("The winners are:");
		
		// This for loop will print all the cars sored in th "winners" array
		for (int i=0; i< carFinished;i++)	{
			System.out.println(winners[i].toString());
		}
		
		// Displaying a closing message
		System.out.println("\n***** Thank you for using Édouard's race track simulator! *****");
		
		// Closing scanner
		keyboard.close();
	}

}
