//-------------------------------------------------------
// Assignment 5
// Written by: Édouard Gagné 40061204
// For COMP 248 Section P A Fall 2017
// This class is used to store informations about a car and define and methods that will be used in the two driver classes, RaceLane1 and RaceLane2.
// It contains methods to declare a "car" object, methods to access information about the cars, a method to move the car and one to stop the car, 
// two methods to print information about the cars, a method to accelerate the car and a method to slow it down. All these method will be used in 
// the two driver classes.
//--------------------------------------------------------
public class Car {
	// Part 1
	// Declaring private instance variables to store the model, location, speed, maximum speed and direction of the cars
	private String model;
	private int location;
	private int currentSpeed;
	private int maxSpeed;
	private boolean movingForward;
	// Declaring private instance variables to store the state of the car, notably if it is stopped or crashed
	private boolean crashed;
	private boolean stopped;
	// Declaring a default constructor
	public Car()	{
	}
	/* This method will declare an object of type "car" that takes the model, max speed and location from the user, and set the 
	values of the direction of the car to "true" (moving forward) and the current speed to "0". */
	public Car(String model, int maxSpeed, int location)	{
		this.model=model;
		this.maxSpeed=maxSpeed;
		this.location=location;
		movingForward=true;
		currentSpeed=0;
	}
	// This method will return the model of the "car" object it is called on
	public String getModel() {
		return model;
	}
	// This method will return the direction of the "car" object it is called on
	public boolean getDirection() {
		return movingForward;
	}
	// This method will return the location of the "car" object it is called on
	public int getLocation()	{
		return location;
	}
	// This method will set the speed of the "car" object it is called on to the max speed of the car
	public void go()	{
		this.currentSpeed=this.maxSpeed;
	}
	// This method will set the speed of the "car" object it is called on to 0
	public void stop() {
		this.currentSpeed=0;
	}
	// This method will change the direction of the "car" object, so if it is moving forward, it will now move backward
	public void turnAround() {
		this.movingForward= !this.movingForward;
	}
	/* This method will move the car in the direction it is facing, by adding or subtract its current speed to its location based on
	which direction it is going*/
	public void move()	{
	if (this.movingForward==true)
		this.location += this.currentSpeed;
	else
		this.location -= this.currentSpeed;
	}
	// This method will print the model, location, direction and current speed of the car it is being called on
	public String toString() {
		String string;
		if (this.stopped==true)	{
			string = this.model + ": Located at " + this.location + ", facing " + ((this.movingForward==true) ?  "forward" : "backward") + ", not moving";
		}
		else		{
			string = this.model + ": Located at " + this.location + ", facing " + ((this.movingForward==true) ?  "forward" : "backward") + " and moving at " + this.currentSpeed + " speed.";
		}
		return string;
	}
	
	// Part 2
	/* This method will declare a car object that takes the model and max speed from the user and sets the location to "0", the direction to moving forward,
	the current speed to "0" and the state of the car to not crashed and not stopped. */
	public Car(String model, int maxSpeed) {
		this.model=model;
		this.maxSpeed=maxSpeed;
		location=0;
		movingForward=true;
		currentSpeed=0;
		crashed=false;
		stopped=false;
	}
	// This method will accelerate the car it is being called on by adding "1" to its current speed
	public void accelerate()	{
		if (currentSpeed != maxSpeed)
			currentSpeed ++;
	}
	// This method will slow down the car it is being called on by subtracting "1" to its current speed
	public void brake()	{
		if (currentSpeed != 0)
			currentSpeed --;
	}
	// This method will crash the car by setting its "crashed" variable to "true"
	public void setCrashed(boolean crashed) {
		this.crashed=crashed;
	}
	// This method will stop the car by setting its "stopped" variable to "true"
	public void setStopped(boolean stopped) {
		this.stopped=stopped;
	}
	// This method will return the "crashed" variable of the car
	public boolean getCrashed() {
		return crashed;
	}
	// This method will return the "crashed" variable of the car
	public boolean getStopped() {
		return stopped;
	}
}

