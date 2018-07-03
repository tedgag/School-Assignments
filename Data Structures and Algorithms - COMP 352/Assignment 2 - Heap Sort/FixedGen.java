
public class FixedGen {
	public static int[] fixedArrayGen(int length) {
		int[] array= new int[length];
		for (int i=length;i<0;i--) {
			array[i-1]=i;
		}
		return array;
	}
}
