// -----------------------------------------------------
// Assignment 2
// Written by: Édouard Gagné 40061204
// This class contains a method to heapify a level of a heap in an array as well as a method that can sort an array by heap sort.
// -----------------------------------------------------
public class HeapSort {
	//Simple swap method to swap two integer values.
	public static void swap(int val1, int val2) {
		int temp = val1;
		val1=val2;
		val2=temp;
	}
	// Method that heapify a level of a heap. For each nodes in a level, it checks if the left child or right child is greater than the nodes and
	// swap them if they are, then recursively calls the method on the level above.
	public static void heapify(int level, int[] heap) {
		int leftChild=0, rightChild=0;
		//Starting index of the level.
		int startIndex = (int)Math.pow(2,level)-1;
		//Number of children below the level.
		int nbOfChilds= heap.length-(startIndex+(int)Math.pow(2,level));
		for (int j=0; j<(int)Math.pow(2,level);j++) {
			// Index of the left children.
			leftChild=startIndex+(int)Math.pow(2,level)+2*j;
			// Index of the right children.
			rightChild=startIndex+(int)Math.pow(2,level)+2*j+1;
			// Check if there are still children left to evaluate to avoid an IndexOutOfBound exception and check if the left child is greater than the node.
			if ((2*j+1)<=nbOfChilds && heap[leftChild]>heap[startIndex+j]) {
				swap(heap[leftChild],heap[startIndex+j]);
				//If we are not at the root, heapify the level above.
				if (level!=0)
					heapify(level-1,heap);
			}
			// Check if there are still children left to evaluate to avoid an IndexOutOfBound exception and check if the right child is greater than the node.
			if ((2*j+2)<=nbOfChilds && heap[rightChild]>heap[startIndex+j]) {
				swap(heap[rightChild],heap[startIndex+j]);
				//If we are not at the root, heapify the level above.
				if (level!=0)
					heapify(level-1,heap);
			}
		}
	}
	// Method that sort an array by first building the heap using the heapify method above and then for each of the values of the array perform a bottom-up heapsort
	// swapping nodes from the root down to the prime leaf and storing the previous root value at the end of the array.
	public static void sort(int[] input) {
		int depth=(int)(Math.log(input.length)/Math.log(2));
		// Building the heap
		for (int i=0; i<depth;i++) {
			heapify(i,input);
		}
		int value,nbOfChilds,leftChild,rightChild;
		int startIndex;
		int curr;
		// For each node in the array, check for each level, the swap that node with the biggest child and store its position  
		for (int i=input.length-1;i>=0;i--) {
			value=input[0];
			curr=0;
			for (int j=0;j<depth;j++) {
				//Starting index of the level
				startIndex = (int)Math.pow(2,j)-1;
				//Number of children
				nbOfChilds= i+1-(startIndex+(int)Math.pow(2,j));
				//Index of the children
				leftChild=2*curr+1;
				rightChild=2*curr+2;
				// Check if there are enough children and if the left child is greater than the right child, if so swap its position with the current node and save it
				if (leftChild-startIndex-(int)Math.pow(2,j)+1<=nbOfChilds && rightChild-startIndex-(int)Math.pow(2,j)+1<=nbOfChilds
						&& input[leftChild]>=input[rightChild]) {
						swap(input[curr],input[leftChild]);
						curr=leftChild;
				// Check if there are enough children and if the right child is greater than the left child, if so swap its position with the current node and save it
				} else if (leftChild-startIndex-(int)Math.pow(2,j)+1<=nbOfChilds && rightChild-startIndex-(int)Math.pow(2,j)+1<=nbOfChilds
						&& input[leftChild]<input[rightChild]) {
						swap(input[curr],input[rightChild]);
						curr=rightChild;
				//Swap the position of the node and left child if there are no right child
				} else if (leftChild-startIndex-(int)Math.pow(2,j)+1<=nbOfChilds) {
					swap(input[curr],input[leftChild]);
					curr=leftChild;
				}
				
			}
			// If the prime leaf is not at the end of the array, store the value of the end of the array in the prime leaf position
			if (curr!=i) {
				swap(input[curr],input[i]);
			}	
			//Sets the old root value at the end of the array
			input[i]=value;
		}
	}
	//Sort method that has an additional traceStep parameter to print a step in the algorithm process.
	public static void sort(int[] input, int traceStep) {
		int depth=(int)(Math.log(input.length)/Math.log(2));
		for (int i=0; i<depth;i++) {
			heapify(i,input);
		}
		if (traceStep==0) {
			System.out.print("trace at step "+traceStep+": ");
			for (int k=0;k<input.length;k++) {
				System.out.print(input[k]+" ");
			}
			System.out.println();
		}
		int value,nbOfChilds,leftChild,rightChild;
		int startIndex;
		int curr;
		for (int i=input.length-1;i>=0;i--) {
			value=input[0];
			curr=0;
			for (int j=0;j<depth;j++) {
				startIndex = (int)Math.pow(2,j)-1;
				nbOfChilds= i+1-(startIndex+(int)Math.pow(2,j));
				leftChild=2*curr+1;
				rightChild=2*curr+2;
				if (leftChild-startIndex-(int)Math.pow(2,j)+1<=nbOfChilds && rightChild-startIndex-(int)Math.pow(2,j)+1<=nbOfChilds
						&& input[leftChild]>=input[rightChild]) {
						swap(input[curr],input[leftChild]);
						curr=leftChild;
				} else if (leftChild-startIndex-(int)Math.pow(2,j)+1<=nbOfChilds && rightChild-startIndex-(int)Math.pow(2,j)+1<=nbOfChilds
						&& input[leftChild]<input[rightChild]) {
						swap(input[curr],input[rightChild]);
						curr=rightChild;
				} else if (leftChild-startIndex-(int)Math.pow(2,j)+1<=nbOfChilds) {
					swap(input[curr],input[leftChild]);
					curr=leftChild;
				}
			}
			if (curr!=i) {
				swap(input[curr],input[i]);
			}
			input[i]=value;
			
			if (traceStep==(input.length-i)) {
				System.out.print("trace at step "+traceStep+": ");
				for (int k=0;k<input.length;k++) {
					System.out.print(input[k]+" ");
				}
				System.out.println();
			}
		}
	}

}
