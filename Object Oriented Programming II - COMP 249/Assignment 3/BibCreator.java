/**
 * Édouard Gagné #40061204
 * COMP249
 * Assignment #3
 * Due March 18 2018
 */
// -----------------------------------------------------
// Assignment 3
// Written by: Édouard Gagné 40061204
// This program's purpose is to convert files containing various informations about articles written using the JSON format into three different files containing these
// articles written in three other formats: IEEE, ACM and NationalJournal. If any of these files are missing an information, the three output files will be deleted for
// this file only. The program will then ask the user to review one of the created files. If the user enters an invalid file name, he has another attempts. If it is still
// invalid, the program will terminate.
// -----------------------------------------------------
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
/**
 * BibCreator class that allows that convert different articles stored in input files into three other formats stored in output files. It then allows the user to review any
 * of the created files.
 * 
 * @author edouard
 * @version 1.0
 */
public class BibCreator {
	/**
	 * This method compare process the input files into output files in the three different formats.
	 * 
	 * @param inputArray an array of scanners from the input files.
	 * @param outputArray an array of printwriters from the output files.
	 * @param inputFiles an array of File object from the input files.
	 */
	public static void processFilesForValidation(Scanner[] inputArray, PrintWriter[] outputArray, File[] inputFiles) {
		// Creating a 2D array that will be used to store informations about the articles
		String[][] informations= new String[][] {{"author", ""},{"title",""},{"journal", ""},{"volume", ""},{"year", ""},{"number",""},{"pages",""},{"DOI",""},{"month",""}};
		// This boolean will be used to terminate the process of an input file.
		boolean skipToEnd;
		// This for loop will execute for each input file.
		for (int i=0;i<inputArray.length;i++) {
			//Resetting skipToEnd to false.
			skipToEnd=false;
			// This while loop (the process) will execute until the input file is empty or is forced to stop using skipToEnd.
			while (inputArray[i].hasNextLine() && skipToEnd==false) {
				// will determine if we have reached the end of an article.
				boolean endOfArticle=false;
				// This do-while loop will go through an article and break each line in 2. If the first part of the line is "{", which means we are at the end of an 
				// article, it will set endOfArticle to false. If it is an informations, it will store the info in the information array. Else, it will continue to the next line.
				do {
					String line=inputArray[i].nextLine();
					String[] parts=line.split("\\{");
					switch (parts[0]) {
						case "}":
							endOfArticle=true;
							break;
						case "author=":
							informations[0][1]=parts[1];
							break;
						case "title=":
							informations[1][1]=parts[1];
							break;
						case "journal=":
							informations[2][1]=parts[1];
							break;
						case "volume=":
							informations[3][1]=parts[1];
							break;
						case "year=":
							informations[4][1]=parts[1];
							break;
						case "number=":
							informations[5][1]=parts[1];
							break;
						case "pages=":
							informations[6][1]=parts[1];
							break;
						case "doi=":
							informations[7][1]=parts[1];
							break;
						case "month=":
							informations[8][1]=parts[1];
							break;
						default:
							break;
					}
				} while (endOfArticle==false && inputArray[i].hasNextLine()==true);
				//This try loop will remove useless carachters from the informations in the array then it will check if any informationis missing. If it is missing,
				// It will close the input streams and delete the output files and throw and invalid file exception.
				try {
				for (int j=0;j<9;j++) {
					informations[j][1]=informations[j][1].replace("}", "");
					informations[j][1]=informations[j][1].replace(",", "");
					String temp = informations[j][1].replaceAll("\\s+", "");
					// This will execute if an information is missing
					if (temp.equals("")) {
						outputArray[i].close();
						outputArray[i+10].close();
						outputArray[i+20].close();
						// Creating file object to be able to delete the actual files
						File file1 = new File("OutputFiles/IEEE" +(i+1)+".json");
						File file2 = new File("OutputFiles/ACM" +(i+1)+".json");
						File file3 = new File("OutputFiles/NJ" +(i+1)+".json");
						file1.delete();
						file2.delete();
						file3.delete();
						//Forcing the process to stop for that input file.
						skipToEnd=true;
						throw new FileInvalidException(informations[j][0]);
					}
				}
				} catch (FileInvalidException e) {
					System.out.println("Error opening file: "+inputFiles[i].getPath());
					System.out.println(e.getMessage());
				}
				String[] names= informations[0][1].split("\\sand");
				//This for loop will print the author in the IEEE format for the IEEE file.
				for(int j=0;j<names.length;j++) {
					if (j<(names.length-1))
						outputArray[i].print(names[j]+", ");
					else
						outputArray[i].print(names[j]+".");
				}
				// ACM format.
				outputArray[i+10].print("["+i+"]\t"+names[0] +" et al. ");
				
				//NJ format.
				for(int j=0;j<names.length;j++) {
					if (j<(names.length-1))
						outputArray[i+20].print(names[j]+" &");
					else
						outputArray[i+20].print(names[j]+".");
				}
				// This statement will print the remaining information in IEEE format in the IEEE file.
				outputArray[i].println("\"" + informations[1][1] + "\", " + informations[2][1] + ", vol. " + informations[3][1] + ", no. " + informations[5][1] + ", p." +informations[6][1]+ ", " +informations[8][1]+ " " +informations[4][1]+".");
				// ACM format
				outputArray[i+10].println(informations[1][1] + ". "+ informations[2][1] + ". "+ informations[3][1] + ", " + informations[5][1] + " ("+informations[4][1]+ "), " +informations[6][1]+". DOI:https://doi.org/" +informations[7][1]+".");	
				// NJ format
				outputArray[i+20].println(informations[1][1] + ". "+ informations[2][1] + ". "+informations[3][1] + ", "+ informations[6][1] +"(" +informations[4][1]+").");
			}
			// CLosing the streams.
			outputArray[i].close();
			outputArray[i+10].close();
			outputArray[i+20].close();
		}
		
	}
	/**
	 * This method asks the user for an outputfile and display its content.
	 * 
	 * @exception FileNotFoundException will be thrown if the requested file doesn't exist.
	 * @exception IOException will be thrown if any error occurs during the method.
	 */
	public static void displayFileContent() throws FileNotFoundException, IOException{
		Scanner keyboard= new Scanner(System.in);
		// Asking the user for a file name in the folder.
		System.out.print("Please enter the name of the file you need to review: ");
		String fileName=keyboard.next();
		fileName="OutputFiles/"+ fileName;
		// Exception might be thrown here.
		BufferedReader outputStream= new BufferedReader(new FileReader(fileName));
		int x;
		// Reading each character one by on in the folder and printing it on the screen.
		System.out.println();
		x=outputStream.read();
		while(x!=-1) {
			System.out.print((char)x);
			x=outputStream.read();
		}
		// Closing the streams.
		outputStream.close();
		keyboard.close();
	}
	/**
	 * Main method to create the input and output streams and execute the previous methods.
	 */
	public static void main(String args[]) { 
	System.out.println("Welcome to Édouard's file processing program!\n");
	String filePath="";
	// Creating a file object using the InputFiles directory, then creating an array of files using the content of the directory.
	File inputFolder = new File("InputFiles");
	File[] inputFiles = inputFolder.listFiles();
	// Creating an array of scanners for all the input files.
	Scanner[] inputStreams = new Scanner[inputFiles.length];
	//This for loop will try to open all the input files or throw an exception if any of the files are missing.
	for (int i=0;i<inputFiles.length;i++) {
		try	{
			// Exception might be thrown here.
			inputStreams[i] = new Scanner(new FileInputStream(inputFiles[i]));
		}
		catch (FileNotFoundException e) {							   
			// Displaying an error message.
			System.out.println("Could not open input file " + inputFiles[i].getPath() + " for reading.");
			System.out.println("Please check if file exist! Program will terminate after closing any opened files.");
			// Closing all the input streams.
			for (int j=0;j<inputFiles.length;j++) {
				inputStreams[j].close();
			}
			//Exiting the program.
			System.exit(0);			   
			}
		}
	//Creating a directory that will contain all the output files.
	File outputFolder = new File("OutputFiles");
	outputFolder.mkdirs();
	// Creating an array of printwriters for all the outputfiles.
	PrintWriter[] outputStreams=new PrintWriter[3*inputFiles.length];
	// this for loop will create all the input files in the OuputFiles folder and throw an exception if a file doesn't exist
	for (int i=0;i<inputStreams.length;i++) {
		try {
		filePath = "OutputFiles/IEEE" +(i+1)+".json";
		outputStreams[i] = new PrintWriter(new FileOutputStream(filePath));
		filePath = "OutputFiles/ACM" +(i+1)+".json";
		outputStreams[i+10] = new PrintWriter(new FileOutputStream(filePath));
		filePath = "OutputFiles/NJ" +(i+1)+".json";
		outputStreams[i+20] = new PrintWriter(new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			// Displaying error message.
			System.out.println("Could not open output file " + filePath + " for reading. All the files will be closed and deleted and the program will terminate.");
			// Creating an array of files to delete and the actual files.
			File [] createdFiles= outputFolder.listFiles();
			
			for (int j=0; j<(3*inputStreams.length);j++) {
				createdFiles[j].delete();
				//Closing the output streams.
				outputStreams[j].close();
			}
			System.exit(0);
		}
	}
	// Processing the files.
	processFilesForValidation(inputStreams,outputStreams, inputFiles);
	// Trying to ask the user to display a file or throwing an exception if the file doesn't exist or a problem happened.
	try {
		displayFileContent();
	} catch (FileNotFoundException e) {
		try {
			// This will throw a exception to decrease the number of attempts left.
			throw new NumberOfAttemptsException();
		} catch (NumberOfAttemptsException e2) {
			e2.incNbAttempts();
		}
	} catch (IOException e) {
		System.out.println("An error has occured, the program will now exit.");
		System.exit(0);
	} finally {
		System.out.println("\nThank you for using my file processing program!");
	}
	
	}
}

