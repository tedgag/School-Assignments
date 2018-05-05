// ---------------------------------------------------------- 
// Assignment#2 
// Question: Part2
// Written by: Édouard Gagné id:40061204
// This is the Event class which is parent to all other classes. It has a year, month and number of cities. It contains a default constructor, parameterized constructor
// and copy constructor. It also contains setters and getters, a toString method and an equals method.
// ----------------------------------------------------------
package packageEvent;

public class Event {
	/* The private modifier will prevents all other classes to access this attributes unless they call a method, which is already the case for all the classes 
	in this program. Therefore, using private on all the attributes is only more secure and has no tradeoffs.*/
	private int year;
	private int month;
	private int nbOfCities;
	// Default constructor
	public Event()	{
		year=2018;
		month=01;
		nbOfCities=0;
	}
	//Parameterized constructor
	public Event(int year, int month, int nbOfCities) {
		this.year=year;
		this.month=month;
		this.nbOfCities=nbOfCities;
	}
	//Copy constructor
	public Event(Event otherEvent) {
		this.year=otherEvent.year;
		this.month=otherEvent.month;
		this.nbOfCities=otherEvent.nbOfCities;
	}
	//Setters
	public void setYear(int year) {
		this.year=year;
	}
	public void setMonth(int month) {
		this.month=month;
	}
	public void setNbOfCities(int nbOfCities) {
		this.nbOfCities=nbOfCities;
	}
	//Getters
	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getNbOfCities() {
		return nbOfCities;
	}
	//toString method that returns a string containing the informations of an Event
	public String toString() {
		String string= "This "+ getClass().getSimpleName() + " will be held in " + year + ", " + month + " in " + nbOfCities + " cities. ";
		return string;
	}
	//equals method that return a true if two objects have the same attributes and false otherwise
	public boolean equals(Object otherObject) {
		/* Here we check if the passed object is null and return false if it is. This prevents the program from crashing in the else statement bellow otherwise because
		we would call a method on a null object. */
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		else {
			Event otherEvent=(Event)otherObject;
			return (otherEvent.year== year && otherEvent.month==month && otherEvent.nbOfCities==nbOfCities);
		}
	}
}
