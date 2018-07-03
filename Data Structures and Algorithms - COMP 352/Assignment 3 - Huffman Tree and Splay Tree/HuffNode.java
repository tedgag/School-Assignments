// -----------------------------------------------------
// Assignment 3
// Written by: Édouard Gagné 40061204
// Class that inherits the Node class and contains an additional occurrence attribute and a compareTo method.
// -----------------------------------------------------
public class HuffNode extends Node {
	private int occurrence;
	
	public HuffNode(int value, int occurrence, int key, HuffNode left, HuffNode right) {
		super(value,key,left,right);
		this.occurrence=occurrence;
	}
	public HuffNode(int value, int occurrence, int key) {
		super(value,key);
		this.occurrence=occurrence;
	}
	
	public int getOccurence() {
		return occurrence;
	}


	public void setOccurence(int occurrence) {
		this.occurrence = occurrence;
	}

	// Return 1 if the first node is greater or -1 if it is less. If the two nodes are equal, instead compare the occurrence. 
	public int compareTo(HuffNode otherNode) {
		if (this.getValue()>otherNode.getValue()) {
			return 1;
		}
		if (this.getValue()== otherNode.getValue()) {
			if (this.getOccurence()<otherNode.getOccurence())
				return 1;
			else if(this.getOccurence()==otherNode.getOccurence())
				return 0;
			else return -1;
		}
		else return -1;
	}
	
}
