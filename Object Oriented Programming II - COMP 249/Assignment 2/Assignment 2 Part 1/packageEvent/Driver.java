// ---------------------------------------------------------- 
// Assignment#2 
// Question: Part2
// Written by: Édouard Gagné id:40061204
// This is the driver for the program. It contains a traceArray method that takes and array of events and display the events with the maximum and minimum number of cities and their
// indexes in the array and also display all the events that have the same year and their indexes. Finally, the main methods declare 12 objects from the 6 classes and test the equals
// ,toString  and traceArray methods.
// ----------------------------------------------------------
package packageEvent;
import packageFestival.Festival;
import packageFestival.CulturalFestival;
import packageFestival.MusicFiesta;
import packageSport.SportCompetition;
import packageSport.SportCompetition.Season;
import packageFair.Fair;

public class Driver {
	// TraceArray static method
	public static void traceArray(Event[] array) {
		int maxIndex=0, minIndex=0,maxNbOfCities=0,minNbOfCities=1000;
		Boolean[] sameYear = new Boolean[array.length];
		// This for loop iterates over the array and if an Event has a bigger or smaller number of cities than another, it stores its index under a variable
		for (int i=0;i<array.length;i++) {
			if (array[i].getNbOfCities()>maxNbOfCities) {
				maxIndex=i;
				maxNbOfCities=array[i].getNbOfCities();
			}
			if (array[i].getNbOfCities()<minNbOfCities) {
				minIndex=i;
				minNbOfCities=array[i].getNbOfCities();
			}
		}
		//Printing the Events with the smallest and biggest number of cities
		System.out.println("The event with the most number of cities is at index " + maxIndex + ": " + array[maxIndex].toString()+"\n");
		System.out.println("The event with the least number of cities is at index " + minIndex + ":"  + array[minIndex].toString()+"\n");
		for (int i=0;i<sameYear.length;i++) {
			sameYear[i]=false;
		}
		//This for loop and nested-for loop will iterate over the array and if two events have the same year, it will print them. It will then sets their value in the sameYear
		// array to true so they don't get reprinted unnecessarily in the next iterations.
		for (int i=0; i<array.length;i++) {
			for(int j=0; j<array.length;j++) {
				if(array[i].getYear()==array[j].getYear() && i!=j && sameYear[j]==false && sameYear[i]==false) {
					sameYear[j]=true;
					System.out.println("The event at index " + i + " and at index " + j + " have the same year: " + array[i].toString()+ "\n" + array[j].toString() +"\n");
				}
			}
			sameYear[i]=true;
		}
	}
	//main method
	public static void main(String[] args) {
		//Declaring 12 objects from each classes
		Event event1 = new Event();
		Event event2 = new Event(2018,01,0);
		Festival festival1=new Festival(2017,02,10,"Cool guys", 5.25,30 );
		Festival festival2=new Festival(2017,02,10,"Cool guys", 5.25,30 );
		CulturalFestival culturalFestival1= new CulturalFestival();
		CulturalFestival culturalFestival2= new CulturalFestival(2017,02,10,"Cool guys", 5.25,30, 3);
		MusicFiesta fiesta1= new MusicFiesta();
		MusicFiesta fiesta2= new MusicFiesta(2015,04,20,"Nice music", 6.00, 30, 40) ;
		SportCompetition competition1= new SportCompetition(2016,06,8,20, Season.SUMMER);
		SportCompetition competition2= new SportCompetition(2016,06,8,20, Season.SUMMER);
		Fair fair1= new Fair();
		Fair fair2= new Fair();
		//Testing the toString methods and equals methods
		System.out.println("Testing the toString methods...");
		System.out.println(event1+"\n" + event2+"\n" + festival1 +"\n"+festival2 +"\n" + culturalFestival1 +"\n"+culturalFestival2 +"\n" +fiesta1 +"\n"+ competition1+"\n"+ competition2+ "\n" + fair1 +"\n");
		System.out.println("Testing the equals methods...");
		System.out.println(event1.equals(event2)+ " " + event1.equals(festival1) +" " +festival1.equals(festival2));
		//Creating a new array with 10 of the objects above and testing TraceArray
		Event[] array1= new Event[] {event1,event2,festival1,festival2,culturalFestival1,culturalFestival2,fiesta1,competition1,competition2,fair1};
		traceArray(array1);
 	}

}
