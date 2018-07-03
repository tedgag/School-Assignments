
import java.util.stream.IntStream;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
//-----------------------------------------------------
//Assignment 1
//Written by: Édouard Gagné 40061204
//This class contains a main method that perform the ISort method on 100 arrays of sizes ranging from 10 to 1000 and contains values from 0 to 100 and print the
//resulting completion times and array sizes to a text file that will be graphed using Microsoft Excel.
//-----------------------------------------------------
public class ISortData {

	public static void main(String[] args) {
		PrintWriter data=null;
		try {
			data= new PrintWriter(new FileOutputStream("data.txt"));
		} catch (FileNotFoundException e) {
			
		}
		int[] ns = IntStream.iterate(10, n -> n + 10).limit(100).toArray();
        for (int n : ns) {
            int[] nums = IntStream.generate(() -> new Random().nextInt(10000)).limit(n).toArray();
            long startTime = System.nanoTime();
            ISort.insertionSort(nums,false);
            long endTime = System.nanoTime();
            data.println(nums.length+"\t"+(endTime-startTime));
        }
        data.close();
	}

}
