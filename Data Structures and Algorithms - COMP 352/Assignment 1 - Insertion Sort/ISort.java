// -----------------------------------------------------
// Assignment 1
// Written by: Édouard Gagné 40061204
// This class contains a method to sort an integer array by insertion sort named ISort as well as a main method to test the ISort method.
// -----------------------------------------------------

public class ISort {
	//Method that performs an insertion sort on the given integer array. Will also print debug messages if the passed debug parameter is true.
	public static int[] insertionSort(int[] numbers, boolean debug) {
		//Will keep track of the computation time.
			long startTime = System.nanoTime();
		int newIndex; //Index where the value will be inserted.
		boolean greaterThan=false; //For debugging purpose.
		for (int i=0;i<numbers.length;i++) {
			newIndex=i;
			int curr=numbers[i];
			//Linear search algorithm.
			for(int j=i-1;j>=0;j--) {
				if (debug) {	
					for (int k=0;k<numbers.length;k++) {
						if (k==i) {
							System.out.print("[" + numbers[k]+"] ");
						} else if (k==j) {
							System.out.print("i"+numbers[k]+"i ");
						} else {
							System.out.print(numbers[k]+" ");
						}
					}
				}
				if(curr<numbers[j]) {
					newIndex=j; //Sets the index where the value will be inserted
					//Indicates that a greater value has not been found.
					if (debug) 
						System.out.println("<");
				}
				if(curr>=numbers[j]) {
					j=-1; //Will terminate the loop.
					greaterThan=true;
					//Indicates that ha greater value has been found.
					if (debug) 
						System.out.println(">");
				}
			}
			//Will be printed if the search has not found a value greater than the current value and debug is enabled.
			if (debug && !greaterThan) {
				for (int j=0;j<numbers.length;j++) {
					if (j==i) {
						System.out.print("[" + numbers[j]+"] ");
					} else {
						System.out.print(numbers[j]+" ");
					}
				}
				 System.out.println("|");
			}
			// Loop that shift all the values of the array one position to the right.
			for (int j=i; j>newIndex;j--) {
				numbers[j]=numbers[j-1];
			}
			numbers[newIndex]=curr;
		}
		//Will print the sorted array as well as the completion time of the algorithm
		if (debug) {
			for (int j=0;j<numbers.length;j++) {
					System.out.print(numbers[j]+" ");
			}
			long endTime = System.nanoTime();
			System.out.println("\ncompleted in "+(endTime-startTime)+"ns");
		}
		return numbers;
	}
	//Main method to test the ISort method as well as to convert the args array to an integer array.
	public static void main(String[] args) {
		int[] numbers;
		boolean debug=false;
		//The following if-else statement will check if the debug args is there and change the String array to an integer array.
		if (args[0].toLowerCase().equals("debug")) {
			debug=true;
			numbers= new int[args.length-1];
			for (int i=1;i<args.length;i++) {
				numbers[i-1]=Integer.parseInt(args[i]);
			}
		} else {
			numbers= new int[args.length];
			for (int i=0;i<args.length;i++) {
				numbers[i]=Integer.parseInt(args[i]);
			}
		}
		insertionSort(numbers,debug);
		
        }
	}
	

