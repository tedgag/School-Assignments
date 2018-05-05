// ---------------------------------------------------------- 
// Assignment#2 
// Question: Part2
// Written by: Édouard Gagné id:40061204
// This is the Event class which is parent to all other classes. It has a year, month, number of cities, nbOfExibitors and type, which is the enum type Type. 
// It contains a default constructor, parameterized constructor and copy constructor. It also contains setters and getters, a toString method and an equals method. 
// ----------------------------------------------------------
package packageFair;
import packageEvent.Event;

public class Fair extends Event {
	public enum Type{CAREER, SCIENCE, TRADE, TRAVEL};
	//Changed to private
	private int nbOfExibitors;
	private Type type;
	//Default Constructor
	public Fair() {
		super();
		nbOfExibitors=0;
		type=null;
	}
	//Parameterized constructor
	public Fair(int year, int month, int nbOfCities, int nbOfExibitors, Type type) {
		super(year,month,nbOfCities);
		this.nbOfExibitors=nbOfExibitors;
		this.type=type;
	}
	//Copy constructor
	public Fair(Fair otherFair) {
		super(otherFair);
		this.nbOfExibitors=otherFair.nbOfExibitors;
		this.type=otherFair.type;
	}
	//Setters
	public void setNbOfExibitors(int nbOfExibitors) {
		this.nbOfExibitors=nbOfExibitors;
	}
	public void setType(Type type) {
		this.type=type;
	}
	//Getters
	public int getNbOfExibitors() {
		return nbOfExibitors;
	}
	public Type getType() {
		return type;
	}
	//toString method that returns a string containing the informations of an Event
	public String toString() {
		String string=super.toString()+"It has " + nbOfExibitors + " activities and takes place in the " + type + ".";
		return string;
	}
	//equals method that return a true if two objects have the same attributes and false otherwise
	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		else {
			Fair otherFair=(Fair)otherObject;
			return (super.equals(otherFair) && otherFair.nbOfExibitors==nbOfExibitors && otherFair.type==type);
		}
	}
}
