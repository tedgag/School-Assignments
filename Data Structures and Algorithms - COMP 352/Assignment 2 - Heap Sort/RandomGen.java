// -----------------------------------------------------
// Assignment 2
// Written by: Édouard Gagné 40061204
// This class contains a method to generate randomized array with a specific length and a method to generate randomized array with a specific length 
// and a seed.
// -----------------------------------------------------
import java.util.Random;
public class RandomGen {
	//Randomized array method with a length parameter
	public static int[] randomArrayGen(int length) {
		Random generator= new Random(System.currentTimeMillis());
		int[] array = new int[length];
		for (int i=0;i<length;i++) {
			array[i]= generator.nextInt(100);
		}
		return array;
	}
	//Randomized array method with a length parameter and a seed parameter
	public static int[] randomArrayGen(int length, long seed) {
		Random generator= new Random(seed);
		int[] array = new int[length];
		for (int i=0;i<length;i++) {
			array[i]= generator.nextInt(100);
		}
		return array;
	}
}
