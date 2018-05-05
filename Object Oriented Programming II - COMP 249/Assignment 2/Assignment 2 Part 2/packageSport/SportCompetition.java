// ---------------------------------------------------------- 
// Assignment#2 
// Question: Part2
// Written by: Édouard Gagné id:40061204
// This is the Event class which is parent to all other classes. It has a year, month, number of cities, nbOfExibitors and seasonName, which is the enum type Season. 
// It contains a default constructor, parameterized constructor and copy constructor. It also contains setters and getters, a toString method and an equals method. 
// ----------------------------------------------------------
package packageSport;
import packageEvent.Event;

public class SportCompetition extends Event {
	public enum Season {SUMMER,FALL,WINTER,SPRING};
	//Changed to private
	private int nbOfActivities;
	private Season seasonName;
	//Default Constructor
	public SportCompetition() {
		super();
		nbOfActivities=0;
		this.seasonName= null;
	}
	//Parameterized Constructor
	public SportCompetition(int year, int month, int nbOfCities, int nbOfActivities, Season seasonName) {
		super(year,month,nbOfCities);
		this.nbOfActivities=nbOfActivities;
		this.seasonName=seasonName;
	}
	//Copy constructor
	public SportCompetition(SportCompetition otherSportCompetition) {
		super(otherSportCompetition);
		this.nbOfActivities=otherSportCompetition.nbOfActivities;
		this.seasonName=otherSportCompetition.seasonName;
	}
	// Setters
	public void setNbOfActivities(int nbOfActivities) {
		this.nbOfActivities=nbOfActivities;
	}
	public void setSeasonName(Season seasonName) {
		this.seasonName=seasonName;
	}
	//Getters
	public int getNbOfActivities() {
		return nbOfActivities;
	}
	public Season getSeasonName() {
		return seasonName;
	}
	//toString method that returns a string containing the informations of an Event
	public String toString() {
		String string=super.toString()+"It has " + nbOfActivities + " activities and takes place in the " + seasonName + ".";
		return string;
	}
	//equals method that return a true if two objects have the same attributes and false otherwise
	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		else {
			SportCompetition otherSportCompetition=(SportCompetition)otherObject;
			return (super.equals(otherSportCompetition) && otherSportCompetition.nbOfActivities==nbOfActivities && otherSportCompetition.seasonName==seasonName);
		}
	}
}
