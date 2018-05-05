// ---------------------------------------------------------- 
// Assignment#2 
// Question: Part2
// Written by: Édouard Gagné id:40061204
// This is the Festival class, child of the Event class and parent of CulturalFestival and MusicFiesta. It has a year, month, a number of cities, a name, a ticket price, 
// a duration and a number of languages. It contains a default constructor, parameterized constructor and copy constructor. It also contains setters and getters, a toString 
// method and an equals method.
// ----------------------------------------------------------
package packageFestival;

public class CulturalFestival extends Festival {
	//Changed to private
	private int nbOfLanguages;
	//Default constructor
	public CulturalFestival() {
		super();
		nbOfLanguages=0;
	}
	//Parameterized constructor
	public CulturalFestival(int year, int month, int nbOfCities, String name, double ticketPrice, int duration, int nbOfLanguages) {
		super(year, month, nbOfCities, name, ticketPrice, duration);
		this.nbOfLanguages=nbOfLanguages;
	}
	//Copy constructor
	public CulturalFestival(CulturalFestival otherCulturalFestival) {
		super(otherCulturalFestival);
		this.nbOfLanguages = otherCulturalFestival.nbOfLanguages;
	}
	//Setter
	public void setNbOfLanguages(int nbOfLanguages) {
		this.nbOfLanguages=nbOfLanguages;
	}
	//Getter
	public int getNbOfLanguages() {
		return nbOfLanguages;
	}
	//toString method that returns a string containing the informations of an Event
	public String toString() {
		String string=super.toString()+ "It has " + nbOfLanguages + " spoken languages.";
		return string;
	}
	//equals method that return a true if two objects have the same attributes and false otherwise
	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		else {
			CulturalFestival otherCulturalFestival=(CulturalFestival)otherObject;
			return (super.equals(otherCulturalFestival) && otherCulturalFestival.nbOfLanguages==nbOfLanguages);
		}
	}
}
