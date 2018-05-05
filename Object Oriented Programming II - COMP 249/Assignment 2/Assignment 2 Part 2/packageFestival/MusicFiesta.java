// ---------------------------------------------------------- 
// Assignment#2 
// Question: Part2
// Written by: Édouard Gagné id:40061204
// This is the Festival class, child of the Event class and parent of CulturalFestival and MusicFiesta. It has a year, month, a number of cities, a name, a ticket price, 
// a duration and a number of bands. It contains a default constructor, parameterized constructor and copy constructor. It also contains setters and getters, a toString 
// method and an equals method.
// ----------------------------------------------------------
package packageFestival;

public class MusicFiesta extends Festival {
	//Changed to private
	private int nbOfBands;
	//Default Constructors
	public MusicFiesta() {
		super();
		nbOfBands=0;
	}
	//Parameterized Constructor
	public MusicFiesta(int year, int month, int nbOfCities, String name, double ticketPrice, int duration, int nbOfBands) {
		super(year, month, nbOfCities, name, ticketPrice, duration);
		this.nbOfBands=nbOfBands;
	}
	//Copy constructor
	public MusicFiesta(MusicFiesta otherMusicFiesta) {
		super(otherMusicFiesta);
		this.nbOfBands=otherMusicFiesta.nbOfBands;
	}
	//Setter
	public void setNbOfBands(int nbOfBands) {
		this.nbOfBands=nbOfBands;
	}
	//Getter
	public int getNbOfBands() {
		return nbOfBands;
	}
	//toString method that returns a string containing the informations of an Event
	public String toString() {
		String string=super.toString()+ "It has " + nbOfBands + " bands.";
		return string;
	}
	//equals method that return a true if two objects have the same attributes and false otherwise
	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		else {
			MusicFiesta otherMusicFiesta=(MusicFiesta)otherObject;
			return (super.equals(otherMusicFiesta) && otherMusicFiesta.nbOfBands==nbOfBands);
		}
	}
}
