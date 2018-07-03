// -----------------------------------------------------
// Assignment 2
// Written by: Édouard Gagné 40061204
// Driver class that uses the classes HeapSort, RandomGen and FixedGen to generate a user specified array, sort it using heap sort and display the
// results.
// -----------------------------------------------------
public class HSDriver {

	public static void main(String[] args) {
		int[] array=null;
		long startTime=0, endTime=0;
		// Checks if the first argument is RandomGen, then if there is a seed argument, generate an array using the seed method, else generate one 
		// without the seed parameter.
		if (args[0].equals("RandomGen"))	{
			if (args.length==4) {
				array = RandomGen.randomArrayGen(Integer.parseInt(args[1]), Long.parseLong(args[3])); //RandomGen with seed.
			} else {
				array = RandomGen.randomArrayGen(Integer.parseInt(args[1])); // RandomGen without seed.
			}
		} else if (args[0].equals("FixedGen")) {
			array = FixedGen.fixedArrayGen(Integer.parseInt(args[1]));
		}
		//Print the initial array.
		System.out.print("sorting: ");
		for (int k=0;k<array.length;k++) {
			System.out.print(array[k]+" ");
		}
		System.out.println();
		//If the trace step argument is -1, it will simply sort the array, else it will print the array at the specified trace step.
		if (Integer.parseInt(args[2])==-1) {
			startTime = System.nanoTime();
			HeapSort.sort(array);
			endTime = System.nanoTime();
			
		} else {
			startTime = System.nanoTime();
			HeapSort.sort(array,Integer.parseInt(args[2]));
			endTime = System.nanoTime();
		}
		//Printing out the results.
		System.out.print("result: ");
		for (int k=0;k<array.length;k++) {
			System.out.print(array[k]+" ");
		}
		System.out.println();
		System.out.println("completed in " + (endTime-startTime)+"ns");

	}

}
