/**
 * Édouard Gagné #40061204
 * COMP249
 * Assignment #1
 * Due January 31 2018
 */
// -----------------------------------------------------
// Assignment 1
// Question: Part I and II 
// Written by: Édouard Gagné #40061204
// This program is used to create and manage an inventory of book objects that each have 5 attributes: title, author, price, ISBN and index. It contains a constructor, getters and setters a toSting 
// method, an equal method and a method to find the number of created book. After that, there is a main method that implements the previously established methods. The program starts by displaying a
// main menu with 5 options for the user to choose: create new books, change books, search by author, search by price and quit. The first option will ask the user for a password to enter and return
// to the main menu after 3 tries. If the user already did this process 3 other times, the program will terminate. Then, if the password is correct, the program will allow the user to create any number
// of books. The second option will also ask for a password and quit at 3 tries. Then, if the password is correct, it will allow the user to modify any of the attributes of any book. The third option
// will search for any book object with an author entered by the user. The fourth option will do the same with a price and finally the last option will terminate the program.
// 
// -----------------------------------------------------
import java.util.Scanner;
/**
 * Book class that allow the user to manage an inventory of books by creating new books, changing the attributes of the books and
 * searching all the book that have a specific author and price.
 * 
 * @author edouard
 * @version 1.0
 */
public class Book {
	/* Declaring all the attributes of the class "Book", mainly "title", "author", "price", "ISBN", "index", which is the index
	of a book in the array of books and a static attribute, "numberOfBooks".*/
	private String title;
	private String author;
	private double price;
	private int ISBN;
	private int index;
	private static int numberOfBooks=0;
	/**
	 * A constructor that uses 4 parameters to create a book object.
	 * 
	 * @param title  name of the book
	 * @param author author of the book
	 * @param price price of the book
	 * @param ISBN ISBN of the book
	 */
	public Book(String title, String author, double price, int ISBN)	{
		this.title=title;
		this.author=author;
		this.price=price;
		this.ISBN=ISBN;
		this.index=numberOfBooks;
		numberOfBooks+=1;
	}
	/**
	 * Mutator method that sets the title of a book to a new one.
	 * 
	 * @param title new title of the book
	 */
	public void setTitle(String title)	{
		this.title=title;
	}
	/**
	 * Mutator method that sets the author of a book to a new one.
	 * 
	 * @param author new author of the book
	 */
	public void setAuthor(String author)	{
		this.author=author;
	}
	/**
	 * Mutator method that sets the price of a book to a new one.
	 * 
	 * @param price new price of the book
	 */
	public void setPrice(double price)	{
		this.price=price;
	}
	/**
	 * Mutator method that sets the ISBN of a book to a new one.
	 * 
	 * @param ISBN new ISBN of the book
	 */
	public void setISBN(int ISBN)	{
		this.ISBN=ISBN;
	}
	/**
	 * Accessor method that returns the title of a book 
	 * 
	 * @return the title of the book
	 */
	public String getTitle()	{
		return title;
	}
	/**	
	 * Accessor method that returns the author of a book
	 * 
	 * @return the author of the book
	 */
	public String getAuthor()	{
		return author;
	}
	/**	
	 * Accessor method that returns the price of a book
	 * 
	 * @return the price of the book
	 */
	public double getPrice()	{
		return price;
	}
	/**	
	 * Accessor method that returns the ISBN of a book
	 * 
	 * @return the ISBN of the book
	 */
	public int getISBN() {
		return ISBN;
	}
	/**	
	 * Accessor method that returns the index of a book
	 * 
	 * @return the index of the book
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * to String method that returns a string containing all the information of a book.
	 * 
	 * @return a string containing all the informations of the book.
	 */
	public String toString() {
		String string = "";
		string="\nBook # " + this.getIndex() +"\n"
				+ "Author: " + this.getAuthor() + "\n"
				+ "Title: " + this.getTitle() + "\n"
				+ "ISBN: "	+ this.getISBN() +"\n"
				+ "Price: " + this.getPrice() + "\n";
		return string;
	}
	/**
	 * A method that returns the number of created books
	 * 
	 * @return the number of created books
	 */
	public static int findNumberOfCreatedBooks() {
		return numberOfBooks;
	}
	/**
	 * This method compare the price and ISBN of two books and return true if they are equals.
	 * 
	 * @param otherBook The book to be compared with the other book the method is called on
	 * @return return true if the price and ISBN are equals, otherwise return false
	 */
	public boolean equals(Book otherBook) {
		if (this.getPrice() == otherBook.getPrice() && this.getISBN() == otherBook.getISBN())
			return true;
		return false;
	}
	/**
	 * Main method that uses the previous methods to manage an inventory of book.
	 */
	public static void main(String[] args) {
		// Welcome message
		System.out.println("*** Welcome to Édouard's books inventory management system! ***\n");
		/* This section will ask the user for the maximum number of book he wants to store, then it will create a scanner called "input" and read and store
		the number under the variable "maxBooks", then create a book array called "inventory" with size equals to the value entered by the user.*/
		System.out.print("Please enter the maximum number of books you want to store > ");
		Scanner input= new Scanner(System.in);
		int maxBooks= input.nextInt();
		Book inventory[] = new Book[maxBooks];
		//This next section will declare a large number of variables that will be useful in the for loop after.
		/* "menuChoice" will be used to store the option the user has chosen in the menu, "attempts" to store the number of attempts to enter a password the user has made,
		"newBooks" is the number of books the user wants to enter, "lockoutAttempts" the number of attempts  at the password the user have made before the program will terminate, 
		"newBookIndex" is the index of a newly created book, "tempISBN" is used to store the ISBN of a new book and "bookNumber" is used to store the index of a new book. 	*/
		int menuChoice=0, attempts,newBooks,lockoutAttempts=0,newBookIndex, tempISBN, bookNumber;
		// "quit" is used to determine if the program will exit one of the menus and "retry" will determine if the user wants to enter another answer.
		boolean quit=false, retry=true;
		// "password" will be used to store the password for some menu choices.
		final String password="password";
		/* "passwordAnswer" will store the answer of the user when he/she is asked for a password, "tempName" is used to store the title of a new book, "tempAuthor" is used to store
		the author of a new book and "answer" will be the answer of a user when he is prompted for one. */
		String passwordAnswer,tempName,tempAuthor,answer="";
		// "tempPrice" is used to store the price of a new book.
		double tempPrice;
		// This do-while loop will execute until the program changes the value of "quit" to true.
		do	{	
			// This section will print the main menu and ask the user which option he wants to choose from 1 to 5 then stores it under "menuChoice".
			System.out.print("What do you want to do?\n"
				+ "   1.  Enter new books (password required)\n"
				+ "   2.  Change information of a book (password required)\n"
				+ "   3.  Display all books by a specific author\n"
				+ "   4.  Display all books under a certain a price\n"
				+ "   5.  Quit\n"
				+ "Please enter your choice > ");
			menuChoice=input.nextInt();
			// This switch statement will use "menuChoice" and execute execute a case depending on its value, from 1 to 5.
			switch (menuChoice) {
			case 1:
				// Resetting the number of attempts and the number of books to be created to 0. 
				attempts=0;
				newBooks=0;
				/* This do-while loop will first ask the user for the password, store it under "passwordAnswer" and check if it is equals to the password stored under "password".
				If it is, the loop break, else the program increment the number of attempts by 1 and ask the user for another password. The loop will keep going until the user 
				makes 3  incorrect attempts.*/
				do { 
					System.out.print("Please enter the password > ");
					passwordAnswer= input.next();
					if (passwordAnswer.equals(password))
						break;
					else
						System.out.print("Incorrect password. ");
						attempts++;
				} while (attempts<3);
				/*This if statement will execute if the user has made 3 incorrect attempts, it will increment the number of "lockoutAttempts" by 1 then if the user has 4 lockout attempts,
				/, the program will terminate completely by setting "quit" to true and breaking. Otherwise, the program will return to the main menu by breaking.*/
				if (attempts==3) {
					lockoutAttempts++;
					if(lockoutAttempts ==4)	{
						System.out.println("Program detected suspicous activities and will terminate immediately!");
						quit=true;
						break;
					}
					break;
				}
				/* The following section will ask the user for the number of new books he wants to store and store that number under "newBooks", then if the number of new books is higher
				than the number of spots left in the inventory, it will ask the user for another number using a while loop, until the user enter a correct number.*/
				System.out.print("Please enter the number of books you want to store > ");
				newBooks=input.nextInt();
				while ((maxBooks-newBooks)<findNumberOfCreatedBooks()) {
					System.out.println("Incorrect number of books, not enough room in the inventory. Please enter another number > ");
					newBooks=input.nextInt();
				}
				/* This for loop will be used to create new books and will execute for each new book to be created. It will first set the index of a new book to the current number of books 
				and store it in "newBookIndex", then it will ask the user for the name, author, price and ISBN and store them in their respective variables. It will then create a new books 
				using these variables and the constructor and store it in "inventory" at the index of "newBookIndex".*/
				for (int i=1;i<=newBooks;i++)	{
					newBookIndex=findNumberOfCreatedBooks();
					System.out.print("Please enter a name for new book #" + i + " > ");
					input.nextLine();
					tempName= input.nextLine();
					System.out.print("Please enter the author of \"" + tempName + "\" > ");
					tempAuthor=input.nextLine();
					System.out.print("Please enter the price of " + tempName + " > ");
					tempPrice=input.nextDouble();
					System.out.print("Please enter the ISBN of " + tempName + " > ");
					tempISBN=input.nextInt();
					inventory[newBookIndex]= new Book(tempName,tempAuthor,tempPrice,tempISBN);
				}
				// Returning to the main menu.
				break;
			case 2:
				// Resetting the number of attempts to 0.
				attempts=0;
				// In the same way as the do-while loop in case 1, this loop will ask the user for a password then loop if the password is incorrect, until the user has made 3 incorrect attempts.
				do { 
					System.out.print("Please enter the password > ");
					passwordAnswer= input.next();
					if (passwordAnswer.equals(password))
						break;
					else
						System.out.print("Incorrect password. ");
						attempts++;
				} while (attempts<3);
				// This if statement will execute if the user has made 3 incorrect attempts and simply return the user to the main menu.
				if (attempts==3)
					break;
				/* This do-while loop will ask the user for the book number he wants to change, which is the index of the book in "inventory", and if that number is greater or equal to the 
				number of currently created books, then it will ask the user to enter a new book by pressing "y", or if he/she wishes to the main menu by pressing "n". */
				do {	
					System.out.print("Which book do you want to update? Please enter the book number (starting at 0) > ");
					bookNumber=input.nextInt();
					if (bookNumber >= findNumberOfCreatedBooks())	{
						System.out.print("There are no books at that location. If you want to re-enter another book, enter \"y\" or to return to the main menu, enter \"n\" > ");
						answer=input.next();
						if (answer.equals("y"))
							retry=true;
						if (answer.equals("n"))
							retry=false;
					}
					else
						retry=false;
				} while (retry==true);
				// This statement will return the user to the main menu if he entered "n".
				if (answer.equals("n"))
					break;
				/* This do-while loop will execute until the user no longer wants to make a change to the previously selected book. It will previously display a menu displaying the options
				available to the user and ask him/her to enter his choice. Then, a switch statement will execute and do different things based on the user's input. If the user enter "1" in 
				the menu, it will ask the user to enter a new author name for the book and set it using the accessor method for the author. Similarly, options 2 to 4 can change the title, 
				ISBN and price of the book. If the user chooses option 5, the program will return to the main menu.*/
				do {	
					System.out.println(inventory[bookNumber].toString());
					System.out.print ("What information would you like to change?\n"
						+ "   1.  author\n"
						+ "   2.  title\n"
						+ "   3.  ISBN\n"
						+ "   4.  price\n"
						+ "   5.  Quit\n"
						+ "Enter your choice > ");
					menuChoice=input.nextInt();
					switch (menuChoice)	{
						case 1:
							System.out.print("Please enter a new author name for \"" + inventory[bookNumber].getTitle()+"\" > ");
							input.nextLine();
							tempAuthor=input.nextLine();
							inventory[bookNumber].setAuthor(tempAuthor);
							break;
						case 2:
							System.out.print("Please enter a new title for \"" + inventory[bookNumber].getTitle()+"\" > ");
							input.nextLine();
							tempName=input.nextLine();
							inventory[bookNumber].setTitle(tempName);
							break;
						case 3:
							System.out.print("Please enter a new ISBN for \"" + inventory[bookNumber].getTitle()+"\" > ");
							input.nextLine();
							tempISBN=input.nextInt();
							inventory[bookNumber].setISBN(tempISBN);
							break;
						case 4:
							System.out.print("Please enter a new price for \"" + inventory[bookNumber].getTitle()+"\" > ");
							input.nextLine();
							tempPrice=input.nextDouble();
							inventory[bookNumber].setPrice(tempPrice);
							break;
						case 5:
							System.out.println("Returning to main menu...");
							quit=true;
							break;
					}
				} while (quit==false);
				// Setting "quit" to false so return to the main menu (so the program does not terminate).
				quit=false;
				break;
			case 3:
				/* This do-while loop will ask the user for the author name he/she wants to search for, then it will search for this name by checking if all the author names of the books
				in the inventory are the same as the one entered by the user. If they are, then the program will display all the informations of the books using the toString method. After that,
				it will ask the user if he/she wants to search for another author name. If the user enter "y", the do-while loop will restarts and if the user enters "n", then the program will
				return to the main menu.*/
				do {
					System.out.print("Please enter the author's name > ");
					input.nextLine();
					tempAuthor=input.nextLine();
					System.out.println("Here are the results:\n");
					for (int i=0;i<findNumberOfCreatedBooks(); i++) {
						if (inventory[i].getAuthor().equals(tempAuthor)) {
							System.out.println(inventory[i].toString());
						}
					}
					System.out.print("If you want to search for another author name, press \"y\", otherwise, press \"n\" > ");
					answer=input.next();
					if (answer.equals("y"))
						retry=true;
					if (answer.equals("n"))
						retry=false;
				} while (retry==true);
				break;
			case 4:
				/* In a similar fashion as the do-while loop in case 3, this do-while loop will search the inventory for a price that the user entered, then display all the books that have this price.
				It will also ask the user to search for another price or return to the main menu. */
				do {
					System.out.print("Please enter the price > ");
					input.nextLine();
					tempPrice=input.nextDouble();
					System.out.println("Here are the results: ");
					for (int i=0;i<findNumberOfCreatedBooks(); i++) {
						if (inventory[i].getPrice()<tempPrice) {
							System.out.println(inventory[i].toString());
						}
					}
					System.out.print("If you want to search for books under another price, press \"y\", otherwise, press \"n\" > ");
					answer=input.next();
					if (answer.equals("y"))
						retry=true;
					if (answer.equals("n"))
						retry=false;
				} while (retry==true);
				break;
			case 5:
				//This case will display a closing message then terminate the program by setting "quit" to true.
				System.out.println("\n*** Thank you for using Édouard's library management system! ***");
				quit=true;
				
				break;
			}
		} while (quit==false);
	input.close();
	}
	
	
}
