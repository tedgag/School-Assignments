//-------------------------------------------------------
// Assignment 4
// Written by: Édouard Gagné 40061204
// For COMP 248 Section P A Fall 2017
// This class is used to store informations about a car and define and test methods that will be used in the CarDriver class. It contains different methods notably
// to declare "car" objects, methods to access informations of these "car" objects, methods to modify informations stored under the objects, a method to estimate
// the current price of the cars, a method to print the informations of a car and finally three methods to check if a car is equal to another car by comparing their
// age and type, greater then or less than another car by comparing their cost.
//--------------------------------------------------------

public class Car {
	// Declaring private instance variables to store the age, cost and type of the car.
	private int age;
	private double cost;
	private String type;

	// This method will declare an object of type "Car" with default values, 0 for the age, 32000 for the cost and "Sedan" for the type
	public Car()	{
		age=0;
		cost=32000;
		type="Sedan";
	}
	// This method will declare an object of type "Car" using only the age and uses default values for the other variables: 32000 for the cost and "Sedan" for the type
	public Car(int age)	{
		this.age=age;
		this.cost=32000;
		this.type="Sedan";
	}
	// This method will declare an object of type "Car" using the age and the cost and using default the value the type, "Sedan" 
	public Car(int age, double cost)	{
		this.age=age;
		this.cost=cost;
		this.type="Sedan";
	}
	// This method will declare an object of type "Car" using the age, the cost and the type 
	public Car(int age, double cost, String type)	{
		this.age=age;
		this.cost=cost;
		this.type=type;
	}
	// This method will return the age of the car it is called on
	public int getAge() {
		return age;
	}
	// This method will return the cost of the car it is called on
	public double getCost() {
		return cost;
	}
	// This method will return the type of the car it is called on
	public String getType() {
		return type;
	}
	// This method will set the age of the car it is called on
	public void setAge(int age)	{
		this.age=age;
	}
	// This method will set the cost of the car it is called on
	public void setCost(double cost)	{
		this.cost=cost;
	}
	// This method will set the type of the car it is called on
	public void setType(String type)	{
		this.type=type;
	}
	// This method will set the all values of the car it is called on
	public void setAllValues(int age, double cost, String type)	{
		this.age=age;
		this.cost=cost;
		this.type=type;
	}
	// This method will set the age and cost of the car it is called on
	public void setAgeAndCost(int age, double cost)	{
		this.age=age;
		this.cost=cost;
	}
	/* This method will estimate the price of the car which depends on its age and its cost. A sedan costs $32000, depreciates 10% every year in first five years
	 and 5% every year afterwards. An SUV costs $45000, depreciates 8% every year in the first five years and 4% every year afterwards. */
	public double estimatePrice(int age, String type) {
		if	(type.equals("Sedan"))	{
			cost=32000;	
			if (age<=5)	{
				for (int i=1; i<=age; i++)	{
					cost-=cost/10;
				}
			}
			if (age > 5)	{
				for (int i=1; i<=5; i++)	{
					cost-=cost/10;
				}
				for (int i=5; i<=age; i++)	{
					cost-=cost/20;
				}
			}
		}
		if	(type.equals("SUV"))	{
			cost=45000;	
			if (age<=5)	{
				for (int i=1; i<=age; i++)	{
					cost-=cost*8/100;
				}		
			}
			if (age > 5)	{
				for (int i=1; i<=5; i++)	{
					cost-=cost*8/100;
				}
				for (int i=5; i<=age; i++)	{
					cost-=cost*4/100;
				}
			}
		}
		return cost;
	}
	// This method will print in the console the type, age and cost of the car it is being called on
	public String toString() {
	return	("This car is type " + type + ". Its age is " + age + " and costs $" + cost);
	}
	// This method will return "true" if the age and type of the car the method is called on is the same as the ones of another car, else it will return false
	public boolean equals(Car anotherCar) {
	return (this.age == anotherCar.age && (this.type).equals(anotherCar.type));
	}
	// This method will return "true" if the cost of the car the method is called on is greater than the cost of another car, else it will return false
	public boolean isGreaterThan(Car anotherCar) {
		if (this.cost> anotherCar.cost)
			return true;
		else
			return false;
	}
	// This method will return "true" if the cost of the car the method is called on is less than the cost of another car, else it will return false
	public boolean isLessThan(Car anotherCar) {
		if (this.cost< anotherCar.cost)
			return true;
		else
			return false;
	}
	// This method is the driver for the Car.java class and it will be used to test all the methods declared in the class.
	public static void main(String[] args) {
		// Declaring 4 car objects, "C1", "C2","C3" and "C4" with different values to test the constructor methods
		Car C1= new Car();
		Car C2= new Car(0);
		Car C3= new Car(4,18000);
		Car C4 = new Car(2,18000, "SUV");
		// Printing the type, cost and age of the 4 cars by using the toString method 
		System.out.println("Car C1: " + C1.toString());
		System.out.println("Car C2: " + C2.toString());
		System.out.println("Car C3: " + C2.toString());
		System.out.println("Car C4: " + C2.toString());
		System.out.println();
		// Testing the accessor methods by printing the age, cost and type using those methods
		System.out.println("Accessor Method: the cartype for C4 is " + C4.getType() + ", its age is " + C4.getAge() + ", and it costs $"+ C4.getCost());
		// Testing the method that estimate the cost of the car using C3 and C4 and printing the results
		System.out.println("The estimate price of car C3 is $" + C3.estimatePrice(C3.getAge(), C3.getType()));
		System.out.println("The estimate price of car C4 is $" + C4.estimatePrice(C4.getAge(), C4.getType()));
		// Changing different values of the car C3 by using the different mutator methods and printing the new values
		C3.setAge(5);
		System.out.println("Mutator Method: The new age for car C3 is " + C3.getAge());
		C3.setType("SUV");
		System.out.println("Mutator Method: The new cartype for car C3 is " + C3.getType());
		C3.setCost(14000);
		System.out.println("Mutator Method: The new cost for car C3 is " + C3.getCost());
		C3.setAgeAndCost(9, 9000);;
		System.out.println("Mutator Method: The new age for car C3 is " + C3.getAge() +" and its new cost is "+ C3.getCost());
		C3.setAllValues(14, 5000, "Sedan");
		System.out.println("Mutator Method: The new cartype for car C3 is " + C3.getType() +", its new age is "+ C3.getAge() + " and its cost is " + C3.getCost());
		// Testing the toString method again by printing the informations of car C3
		System.out.println("ToString: " + C3.toString());
		System.out.println();
		// Testing the equals method by checking if the car C1 and C2 are equals, the the car C1 ad C4
		System.out.println("Car C1 and C2 are equals is " + C1.equals(C2));
		System.out.println("Car C1 and C4 are equals is " + C1.equals(C4));
		// Testing the isGreaterThan method and the isLessThan method by compraing C to C1 and C1 to C3
		System.out.println("Car C4 is less than C1 is " + C4.isLessThan(C1));
		System.out.println("Car C1 is less than C3 is " + C1.isGreaterThan(C3));
	}
}