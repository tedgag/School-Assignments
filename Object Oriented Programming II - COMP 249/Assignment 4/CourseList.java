import java.util.NoSuchElementException;
/**
 * Édouard Gagné #40061204
 * COMP249
 * Assignment #4
 * Due April 13 2018
 */
// -----------------------------------------------------
// Assignment 4
// Question: Part III
// Written by: Édouard Gagné #40061204
// This class has two attributes: a size and a head. The head is a CourseNode, which is an inner class, and this CourseNode class has two attributes: a Course object and a 
// next, which is another node. The CourseNode class has all the setters, getters and constructors for its attributes. It also has a clone method to create copies. The CourseList
// class has a default, parameterized and copy constructor. It has a addToStart method that adds a node to the start of the list and an insertAtIndex method that adds a node at an
// specific position in the list. A deleteFromStart method allows us to delete a node at the start of the list and deleteFromIndex at a specific position. The replaceAtIndex method
// allows us to replace a node with another one. The find method returns a pointer to a node with a given course ID and the contains methods simply tells us if the course ID exist.
// Finally, the equals method checks if two list are the same. There is a main method to test all of these methods.
// -----------------------------------------------------

/**
 * CourseList class that allows us implement linked lists for Course object. Contains various method to modify, create and manipulate linked lists.
 * 
 * @author edouard
 * @version 1.0
 */
public class CourseList {
	private CourseNode head;
	private int size;
	/**
	 * Default constructor that sets the head of the list to null and its size to 0
	 * 
	 */
	public CourseList() {
		this.head=null;
		this.size=0;
	}
	/**
	 * Copy constructor that copies every node in a list and creates a new list with the nodes.
	 * 
	 * @param otherList The list to be copied
	 */
	public CourseList(CourseList otherList) {
		this.head=null;
		this.size=0;
		addToStart(otherList.head.course.clone());
		CourseNode p1=head;
		CourseNode p2=otherList.head;
		while (p2.next!=null) {
			p1.next=new CourseNode(p2.next.course.clone(),null);
			p1=p1.next;
			p2=p2.next;
		}
		
	}
	/**
	 * CourseNode inner class that allows use to initialized nodes that contains Course objects and link them in the linked list
	 * 
	 * @author edouard
	 * @version 1.0
	 */
	// PRIVACY LEAK: The fact that this class is public creates a privacy leak. Any user could call the find() method of CourseList to acquire a pointer to a node object
	// then use accessors and mutators of the node to change the content of the course objects at will.
	public class CourseNode{
		private Course course;
		private CourseNode next;
		/**
		 * Default constructor that sets the course and next to null
		 * 
		 */
		public CourseNode() {
			course=null;
			next=null;
		}
		/**
		 * Parameterized constructor that creates a node with a course and a next pointer
		 * 
		 * @param course the course to be added
		 * @param next the next node
		 */
		public CourseNode(Course course, CourseNode next) {
			this.course=course;
			this.next=next;
			size++;
		}
		/**
		 * Copy constructor that copy a node by copying the course and the next pointer
		 * 
		 * @param course the course to be added
		 * @param next the next node
		 */
		public CourseNode(CourseNode node) {
			this.course=node.course.clone();
			this.next=node.next;
			size++;
		}
		/**
		 * Clone method that calls the copy constructor to copy a node
		 * 
		 * @return an object that is a copy of the node
		 */
		public Object clone() {
			return new CourseNode(this);
		}
		/**
		 * Accessor method that returns the course of the node 
		 * 
		 * @return course
		 */
		public Course getCourse() {
			return course;
		}
		/**
		 * Mutator method that sets the course of the node to a new course
		 * 
		 * @param course the new course
		 */
		public void setCourse(Course course) {
			this.course = course;
		}
		/**
		 * Accessor method that returns the next node 
		 * 
		 * @return next node
		 */
		public CourseNode getNext() {
			return next;
		}
		/**
		 * Mutator method that sets the  next node to another node
		 * 
		 * @param next the next node
		 */
		public void setNext(CourseNode next) {
			this.next = next;
		}
	}
	/**
	 * Method that add a node to the start of the list
	 * 
	 * @param course the course to be added
	 */
	public void addToStart(Course course) {	
			head = new CourseNode(course,head);
	}
	/**
	 * Method that insert a node at the index. If there is already node at the index, it linked at then end of the new node.
	 * 
	 * @param course the course to be added
	 * @param index index of the course to be added
	 * @exception NoSuchElementException thrown and caught if the index is invalid
	 */
	public void insertAtIndex(Course course, int index){
		try{
			if (index == 0) {
				addToStart(course);
			} else {
				CourseNode p=head;
				for (int i=0; i<(index-1);i++) {
					p=p.next;
				}
				p.next= new CourseNode(course,p.next);
				p=null;
			}
		} catch (NoSuchElementException e) {
			System.out.println("Index is invalid! The program will now exit.");
			System.exit(0);
		}
	}
	/**
	 * Method that delete a node at the index. The nodes linked to this nodes are then linked together
	 * 
	 * @param index index of the node to be deleted
	 * @exception NoSuchElementException thrown and caught if the index is invalid
	 */
	public void deleteAtIndex(int index) {
		try {
		if (index>size||index<0) {
			throw new NoSuchElementException();
		}
		if (index == 0) {
			head=head.next;
			size--;
		} else {
			CourseNode p=head;
			for (int i=0; i<(index-1);i++) {
				p=p.next;
			}
			p.next= p.next.next;
			p=null;
			size--;
		}
		} catch (NoSuchElementException e) {
			System.out.println("Index is invalid! The program will now exit.");
			System.exit(0);
		}
	}
	/**
	 * Method that deletes the first node of a list
	 */
	public void deleteFromStart() {
		if (head==null) {
			return;
		} else {
			head=head.next;
			size--;
		}
	}
	/**
	 * Method that replace a node at the index. The previous node will be deleted.
	 * 
	 * @param course the course to be added
	 * @param index index of the node to be replaced
	 * @exception NoSuchElementException thrown and caught if the index is invalid
	 */
	public void replaceAtIndex(Course course, int index) {
		try {
			if (index == 0) {
				head= new CourseNode(course, head.next);
				size--; //Since the constructor increments the size
			} else {
				CourseNode p=head;
				for (int i=0; i<(index-1);i++) {
					p=p.next;
				}
				p.next= new CourseNode(course,p.next.next);
				size--;
				p=null;
			}
		} catch (NoSuchElementException e) {
			System.out.println("Index is invalid! The program will now exit.");
			System.exit(0);
		} 
	}
	/**
	 * Method that finds a course in the list with a courseID. The method returns a pointer to the node.
	 * 
	 * @param courseID the ID of the course we are looking for
	 * @return pointer to the node
	 */
	public CourseNode find(String courseID) {
		int iterations=0;
		CourseNode p=head;
		while (p!= null) {
			iterations++;
			if(p.course.getCourseID().equals(courseID)) {
				System.out.println("The course was found after " + iterations +" iterations.");
				return p;
			}
			p=p.next;
		}
		System.out.println("The course was not found after " + iterations + " iterations.");
		return p; //Returns null
	}
	/**
	 * Method that uses the find() method to search for an ID and return true if it is found
	 * 
	 * @param courseID the ID of the course we are looking for
	 * @return true if the node is found
	 */
	public boolean contains(String courseID) {
		if (find(courseID)==null) {
			return false;
		}
		return true;
	}
	/**
	 * Method that checks if two list are equals by check node by node if they are equals.
	 * 
	 * @param obj the object to be compared with
	 * @return true if the lists are equal
	 */
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		else {
			CourseList otherList=(CourseList)obj;
			if (otherList.size!=size) {
				return false;
			} else {
				CourseNode p1=head;
				CourseNode p2=otherList.head;
				while(p1!=null&&p2!=null){
					if (p1.course.equals(p2.course)==false) {
						return false;
					}
					p1=p1.next;
					p2=p2.next;
				}
				return true;
			}
		}
	}
	/**
	 * Main method to test the different methods in CourseList
	 */
	public static void main(String args[]) {
		CourseList a = new CourseList();
		a.addToStart(new Course("COMP249","OOP 2", 5, "COMP248", "COMP351"));
		a.addToStart(new Course("COMP248","OOP 1", 5, "", "COMP249"));
		System.out.println(a.find("COMP249"));
		System.out.println(a.contains("COMP248"));
		a.replaceAtIndex(new Course("SOEN228","Sys.Hard.",5,"COMP248","SOEN229"), 0);
		System.out.println(a.contains("COMP248"));
		a.insertAtIndex(new Course("COMP248","OOP 1", 5, "", "COMP249"), 2);
		System.out.println(a.contains("COMP248"));
		a.deleteAtIndex(1);
		System.out.println(a.contains("COMP249"));
		a.addToStart(new Course("COMP249","OOP 2", 5, "COMP248", "COMP351"));
		System.out.println(a.size);
		CourseList b= new CourseList(a);
		System.out.println(b.contains("COMP249"));
		System.out.println(a.equals(b));
		
		Course c= new Course("COMP248","OOP 1", 5, "", "COMP249");
		Course d= new Course("COMP249","Something", 4, "", "");
		System.out.println(c);
		System.out.println(d);
		System.out.println(c.isDirectlyRelatable(d));
		b.deleteAtIndex(4);
		
	}
	
}
