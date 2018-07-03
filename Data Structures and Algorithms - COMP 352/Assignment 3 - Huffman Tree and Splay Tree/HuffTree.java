// -----------------------------------------------------
// Assignment 3
// Written by: Édouard Gagné 40061204
// Inherits the Tree class, only to replace the root attribute with a HuffNode.
// -----------------------------------------------------
public class HuffTree extends Tree {
	private HuffNode root;
	public HuffTree(HuffNode root) {
		this.root=root;
	}
	public HuffNode getRoot() {
		return root;
	}
	public void setRoot(HuffNode root) {
		this.root = root;
	}
}
