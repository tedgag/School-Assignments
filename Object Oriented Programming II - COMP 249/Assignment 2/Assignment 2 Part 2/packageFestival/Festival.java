// ---------------------------------------------------------- 
// Assignment#2 
// Question: Part2
// Written by: Édouard Gagné id:40061204
// This is the Festival class, child of the Event class and parent of CulturalFestival and MusicFiesta. It has a year, month, a number of cities, a name, a ticket price 
// and a duration. It contains a default constructor, parameterized constructor and copy constructor. It also contains setters and getters, a toString method and an 
// equals method.
// ----------------------------------------------------------
package packageFestival;
import packageEvent.Event;

public class Festival extends Event {
	//Changed to private
	private String name;
	private double ticketPrice;
	private int duration;
	//Default constructor
	public Festival() {
		super();
		name="";
		ticketPrice=0;
		duration=0;
	}
	//Parameterized constructor
	public Festival(int year, int month, int nbOfCities, String name, double ticketPrice, int duration) {
		super(year,month,nbOfCities);
		this.name=name;
		this.ticketPrice=ticketPrice;
		this.duration=duration;
	}
	//Copy constructor
	public Festival(Festival otherFestival) {
		super(otherFestival);
		this.name=otherFestival.name;
		this.ticketPrice=otherFestival.ticketPrice;
		this.duration=otherFestival.duration;
	}
	//Setter
	public void setName(String name) {
		this.name=name;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice=ticketPrice;
	}
	public void setDuration(int duration) {
		this.duration=duration;
	}
	//Getters
	public String getName() {
		return name;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public int getDuration() {
		return duration;
	}
	//toString method that returns a string containing the informations of an Event
	public String toString() {
		String string=super.toString()+"It's name is " + name + ", the tickets will cost " + ticketPrice + "$ and it will last for " + duration + " days. ";
		return string;
	}
	//equals method that return a true if two objects have the same attributes and false otherwise
	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		else {
			Festival otherFestival=(Festival)otherObject;
			return (super.equals(otherFestival) && otherFestival.name==name && otherFestival.ticketPrice==ticketPrice && otherFestival.duration==duration);
		}
	}
}
