import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * Édouard Gagné #40061204
 * COMP249
 * Assignment #4
 * Due April 13 2018
 */
// -----------------------------------------------------
// Assignment 4
// Question: Part IV
// Written by: Édouard Gagné #40061204
// This class has two static methods to check if a student can enroll in certain courses. The first method, creatSyllabusList, takes the name of a Syllabus file then opens it
// using a scanner. It reads all the data from the file, which are all the different courses that a student can enroll in, then store them in a CoureList and return this list.
// The other method takes this syllabus CourseList and a Request file, then for each requested courses, it will check if the student can enroll using the syllabus and the 
// list of completed courses that are also in the request file. It then display a message based on the result of this process. After that, there is a main method that test these
// two methods.
// -----------------------------------------------------
/**
 * EnrolmentResults class that process the Syllabus and Request file to check if a student can enroll for courses based on pre-required and co-required courses.
 * 
 * @author edouard
 * @version 1.0
 */
public class EnrolmentResults {
	/**
	 * Method that creates a CourseList object from a Syllabus.txt file. It extracts the data in the file to form a CourseList.
	 * 
	 * @param fileName name of the Syllabus file
	 * @return CourseList that contains the courses in the syllabus file
	 * @exception FileNotFoundException thrown if the file name entered is invalid
	 */
	public static CourseList createSyllabusList(String fileName) {
		CourseList syllabus = new CourseList();
		Scanner inputScanner=null;
		try {
			inputScanner = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Syllabus file not found!");
			System.exit(0);
		}
		String courseID,courseName,preReqID,coReqID;
		double credit;
		while (inputScanner.hasNextLine()) {
			String line=inputScanner.nextLine();
			if(line.equals("")) {
				line=inputScanner.nextLine();
			}
			String parts[]=line.split("\\s+");
			courseID=parts[0];
			courseName=parts[1];
			credit=Double.parseDouble(parts[2]);
			line=inputScanner.nextLine();
			line=line.replaceFirst("P", "");
			line=line.replaceAll("\\s+", "");//Removes all whitespaces
			if (line.equals("")) {
				preReqID="";
			} else {
				preReqID=line;
			}
			line=inputScanner.nextLine();
			line=line.replaceFirst("C", "");
			line=line.replaceAll("\\s+", ""); //Removes all whitespaces
			if (line.equals("")) {
				coReqID="";
			} else {
				coReqID=line;
			}
			if (syllabus.contains(courseID)==false) {
				syllabus.addToStart(new Course(courseID,courseName,credit,preReqID,coReqID));
			} else {
				System.out.println("The course already exist and has not been added.");
			}
		}
		inputScanner.close();
		return syllabus;
	}
	/**
	 * Method check if a student is able to enroll to different courses using the syllabus and a file containing the requests of the student and outputs the result
	 * 
	 * @param fileName name of the request file
	 * @param sylabus the syllabus CourseList obtained from createSyllabusList 
	 * @exception FileNotFoundException thrown if the file name entered is invalid
	 */
	public static void processRequest(String fileName, CourseList syllabus) {
		Scanner inputScanner=null;
		try {
			inputScanner = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Request file not found!");
			System.exit(0);
		}
		
		ArrayList<String> completedCourses= new ArrayList<String>();
		String line=inputScanner.nextLine();
		line=inputScanner.nextLine(); // Skipping the first line
		while (line.equals("Requested")==false) {
			completedCourses.add(line);
			line= inputScanner.nextLine();
		}
		ArrayList<String> requestedCourses= new ArrayList<String>();
		while (inputScanner.hasNextLine()) {
			line= inputScanner.nextLine();
			requestedCourses.add(line);
		}
			if (requestedCourses.size()==0) {
				System.out.println("No enrollment courses found");
			}
		for (int i=0;i<requestedCourses.size();i++) {
			boolean coReqOK=false, preReqOK=false, stop=false;
			String coReqID="",preReqID="";
			String requestedID=requestedCourses.get(i);
			CourseList.CourseNode requestedClass=syllabus.find(requestedID);
			do {
				for (int j=0;j<completedCourses.size();j++) {
					if (completedCourses.get(j).equals(requestedClass.getCourse().getPreReqID())) {
						preReqOK=true;
						preReqID=requestedClass.getCourse().getPreReqID();
						
					}
					if (completedCourses.get(j).equals(requestedClass.getCourse().getCoReqID())){ //This will check if a co-req course is already finished
						coReqOK=true;
						coReqID=requestedClass.getCourse().getCoReqID();
					}
				}
				requestedClass=syllabus.find(requestedClass.getCourse().getPreReqID());
				if (requestedClass==null||preReqOK==false) {
					stop=true;
				}
			} while (stop!=true);
			requestedClass=syllabus.find(requestedID);
			for (int j=0;j<requestedCourses.size();j++) {
				if (requestedCourses.get(j).equals(requestedClass.getCourse().getCoReqID())) {
					coReqOK=true;
					coReqID=requestedClass.getCourse().getCoReqID();
				}
			}
			for (int j=0;j<requestedCourses.size();j++) {
				if (requestedCourses.get(j).equals(requestedClass.getCourse().getCoReqID())&&coReqOK) {
					preReqOK=true;
					preReqID=coReqID;
				}
			}
			String output;
			if (preReqOK&&coReqOK) {
				output="Student can enroll in " + requestedID + " since he/she is enrolled in or has completed co-requisite " +coReqID+ " and pre-requisite(s) " + preReqID;
				System.out.println(output);
			} else if (preReqOK&&requestedClass.getCourse().getCoReqID().equals("")) {
				output="Student can enroll in " + requestedID + " since he/she is enrolled in  pre-requisite(s) " + preReqID;
				System.out.println(output);
			} else if (coReqOK&&requestedClass.getCourse().getPreReqID().equals("")) {
				System.out.println("Student can enroll in " + requestedID + " since he/she is enrolled in or has completed co-requisite " +coReqID);
			} else if(requestedClass.getCourse().getCoReqID().equals("")&&requestedClass.getCourse().getPreReqID().equals("")) {
				System.out.println("Student can enroll in " + requestedID + " since this course has no pre-requisites or co-requisite");
			} else {
				System.out.println("Student can't enroll in " + requestedID+" since he/she is missing either a co-requisite or a pre-requisite");
			}
			System.out.println("");
		}
	}
	/**
	 * Main method to test the two methods above
	 */
	public static void main(String args[]) {
		System.out.println("Welcome to Édouard's course enrollement program!");
		CourseList syllabus = createSyllabusList("Syllabus.txt");
		Scanner kb= new Scanner(System.in);
		System.out.println("Please enter the name of the file you want to process: ");
		String fileName = kb.next();
		System.out.println();
		processRequest(fileName, syllabus);
		System.out.println("Thank you for using my enrollement check program!");
	}
}
