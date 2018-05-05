/**
 * Édouard Gagné #40061204
 * COMP249
 * Assignment #4
 * Due April 13 2018
 */
// -----------------------------------------------------
// Assignment 4
// Question: Part II 
// Written by: Édouard Gagné #40061204
// This class is used to create Course object that have 5 attributes: courseID, courseName, credit, preReqID, coReqID. It contains a parametrized constructor and a 
// copy constructor. It contains mutator and accessor methods for each of the parameters. There is a clone method that implements the copy constructor. An equals method
// and a toString method are also present. Finally, it implements the DirectlyRelatable method that checks if two courses have the same co-required course or pre-required 
// course.
// -----------------------------------------------------
import java.util.Scanner;
/**
 * Course class that allows us to initialize "Course" objects with 5 parameters.
 * It contains setters, getters, constructors and other methods and also implements the DirectlyRelatable interface.
 * 
 * @author edouard
 * @version 1.0
 */
public class Course implements DirectlyRelatable{
	
	private String courseID;
	private String courseName;
	private double credit;
	private String preReqID;
	private String coReqID;
	/**
	 * A constructor that uses 5 parameters to create a Course object.
	 * 
	 * @param courseID  unique ID of the course
	 * @param courseName name of the course
	 * @param credit number of credits gained for completing the course
	 * @param preReqID unique ID of the re-requisite course
	 * @param coReqID unique ID of the co-requesite course
	 */
	public Course(String courseID, String courseName, double credit, String preReqID, String coReqID) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.credit = credit;
		this.preReqID = preReqID;
		this.coReqID = coReqID;
	}
	/**
	 * A constructor that creates a copy of a Course object with a new unique ID
	 * 
	 * @param otherCourse the course object to copy
	 * @param courseID the new unique ID
	 */
	public Course(Course otherCourse, String courseID) {
		this.courseID=courseID;
		this.courseName= otherCourse.courseName;
		this.credit = otherCourse.credit;
		this.preReqID = otherCourse.preReqID;
		this.coReqID = otherCourse.coReqID;
		
	}
	/**
	 * Accessor method that returns the course name of the course 
	 * 
	 * @return course ID of the course
	 */
	public String getCourseID() {
		return courseID;
	}
	/**
	 * Mutator method that sets the course ID of a course to a new one
	 * 
	 * @param courseID new course ID
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	/**
	 * Accessor method that returns the course name of the course 
	 * 
	 * @return course name of the course
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * Mutator method that sets the course name of a course to a new one
	 * 
	 * @param courseName new name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * Accessor method that returns the number of credits of the course 
	 * 
	 * @return number of credits of the course
	 */
	public double getCredit() {
		return credit;
	}
	/**
	 * Mutator method that sets the number of credits of a course to a new one
	 * 
	 * @param credit new number of credits
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}
	/**
	 * Accessor method that returns the ID of the pre-requisite course
	 * 
	 * @return ID of the pre-requisite course
	 */
	public String getPreReqID() {
		return preReqID;
	}
	/**
	 * Mutator method that sets the id of the pre-requisite course to a new one
	 * 
	 * @param preReqID new pre-requisite ID
	 */
	public void setPreReqID(String preReqID) {
		this.preReqID = preReqID;
	}
	/**
	 * Accessor method that returns the ID of the co-requisite course
	 * 
	 * @return ID of the co-requisite course
	 */
	public String getCoReqID() {
		return coReqID;
	}
	/**
	 * Mutator method that sets the id of the co-requisite course to a new one
	 * 
	 * @param coReqID new co-requisite ID
	 */
	public void setCoReqID(String coReqID) {
		this.coReqID = coReqID;
	}
	/**
	 * This method ask the user for a new id and create a copy of the calling course using this ID.
	 * 
	 * @return cloned course
	 */
	public Course clone() {
		Scanner kb= new Scanner(System.in);
		System.out.println("Please enter a unique course ID for the new course: ");
		String id= kb.nextLine();
		return new Course(this, id);
	}
	/**
	 * This method compare all the parameters except the unique ID of 2 courses and returns true if they are equals.
	 * 
	 * @param obj the object to be compared to
	 * 
	 * @return return true if all the parameters except the id are equal
	 */
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		else {
			Course otherCourse=(Course)obj;
			return (courseName.equals(otherCourse.courseName) && credit==otherCourse.credit && preReqID.equals(otherCourse.preReqID) && coReqID.equals(otherCourse.coReqID));
		}
	}
	/**
	 * to String method that returns a string containing all the information of a course.
	 * 
	 * @return a string containing all the informations of the course.
	 */
	public String toString() {
		return  courseID + " (" + courseName + "), " + credit + " credits, prereq: " + preReqID + ", coreq; "+ coReqID;
	}
	/**
	 * method that checks if a course is related to another course, meaning that the other course is either a co-required or pre-required course
	 * 
	 * @param the other course to check if it is related
	 * @return true if the two courses are related
	 */
	public boolean isDirectlyRelatable(Course C) {
		if (C.courseID.equals(this.preReqID)||C.courseID.equals(this.coReqID) ) {
			return true;
		}
		return false;
	}
	
	
	
	
}
