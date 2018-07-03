import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
//-----------------------------------------------------
//Assignment 3
//Written by: Édouard Gagné 40061204
//Huffman class that makes use of the HuffNode and HuffTree classes to build a Huffman coding tree from a file containing a string.
//-----------------------------------------------------
public class Huffman {
	// The buildPriorityQueue method builds an array containing sorted HuffNode objects with frequencies and occurrences extracted from 
	// the file parsed as a parameter. The nodes in the queue are sorted using the iSort method. 
	public static HuffNode[] buildPriorityQueue (String fileName) {
		HuffNode[] queue=null;
		//Mapping letters
		try {
			BufferedReader input= new BufferedReader(new FileReader(fileName));
			int key,order=0;
			queue= new HuffNode[0];
			while ((key=input.read())!=-1) {
				boolean contain=false;
				for(int i=0;i<queue.length;i++) {
					if (queue[i].getKey()==key) {
						contain=true;
					}
				}
				if (contain && key!=10) { // 10: ASCII code for next line
					for(int i=0;i<queue.length;i++) {
						if (queue[i].getKey()==key) {
							queue[i].setValue(queue[i].getValue()+1); // Incrementing frequency
						}
					}	
				} else if (!contain && key!=10){
					HuffNode[] temp= new HuffNode[queue.length+1];
					for (int i=0;i<queue.length;i++) {
						temp[i]=queue[i];
					}
					queue=temp;
					queue[queue.length-1]= new HuffNode(1,order,key); // New node with a frequency of 1, the current order and the found key
					order++; 
				}
			}
			input.close();
		} catch (FileNotFoundException E) {}
		catch (IOException E) {}
		iSort(queue); //Sorting using iSort
		return queue;
	}
	// This method sorts an array using the insertion sort algorithm
	public static void iSort(HuffNode[] queue) {
		for(int i=1;i<queue.length;i++) {
			HuffNode curr = queue[i];
			int j=i-1;
			while(j >= 0 && queue[j].compareTo(curr)==1){ 
				queue[j+1] = queue[j];
				j--; 
			}
			queue[j+1] = curr;  
		}
	}
	// This method builds Huffman tree by iterating through the queue and combining the two frequencies if the lowest nodes to create a new node 
	// which has the lowest nodes as its children that is re-inserted into the queue.
	public static HuffTree buildHuff(HuffNode[] queue){
		int nbNodes= queue.length;
		HuffTree huffTree=null;
		for (int i=1;i<queue.length;i++) {
			HuffNode temp = new HuffNode((queue[i-1].getValue()+queue[i].getValue()),nbNodes,0,queue[i-1],queue[i]);
			huffTree = new HuffTree(temp);
			queue[i]=temp;
			nbNodes++;
			iSort(queue); // resorting the queue
		}
		return huffTree;
	}
	//This method will recursively go through the tree until it reaches the node with the requested key and print the resulting code.
	public static void searchAndPrint(HuffNode node, int key, String code) {
		if (node.getRight()==null && node.getLeft()==null) {
			if(node.getKey()==key)
				System.out.print(code);
			return;
		}
		searchAndPrint((HuffNode)node.getLeft(),key, code+"0");
		searchAndPrint((HuffNode)node.getRight(),key, code+"1");
		
	}
	// This method will call the previous method for each characters of a string.
	public static void codify(String string, HuffTree tree) { 
		for (int i=0; i<string.length();i++) {
			String code="";
			searchAndPrint(tree.getRoot(), (int) string.charAt(i),code);
		}
	}
	// Main method that build the tree from the parsed file name and ask the user of a string to encode.
	public static void main(String args[]) {
		HuffNode[] queue = buildPriorityQueue(args[0]);
		HuffTree tree=buildHuff(queue);
		Scanner kb= new Scanner(System.in);
		System.out.print("Please enter a line to be encoded: ");
		String s= kb.nextLine();
		System.out.print("Result: ");
		codify(s,tree);
		kb.close();
	}
}
