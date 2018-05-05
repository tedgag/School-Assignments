import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class EnrolmentResult {
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
	public static void main(String args[]) {
		CourseList syllabus = createSyllabusList("Syllabus.txt");
		Scanner kb= new Scanner(System.in);
		System.out.println("Please enter the name of the file you want to process: ");
		String fileName = kb.next();
		System.out.println();
		processRequest(fileName, syllabus);
	}
}
