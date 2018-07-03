
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
public class HeapSortData {
	public static void main(String args[]) {
		PrintWriter data=null;
		try {
			data= new PrintWriter(new FileOutputStream("data.txt"));
		} catch (FileNotFoundException e) {}
			int[] array = RandomGen.randomArrayGen(100000);
			long startTime = System.nanoTime();
            HeapSort.sort(array);
            long endTime = System.nanoTime();
            System.out.println(endTime-startTime);
		
		data.close();
	}
}
