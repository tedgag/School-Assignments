// -----------------------------------------------------
// Assignment 3
// Written by: Édouard Gagné 40061204
// General tree class that contains a root which is a Node object and setters and getters for this root.
// -----------------------------------------------------
public class Tree {
	private Node root;
	public Tree(Node root) {
		this.root=root;
	}
	public Tree() {
		root=null;
	}
	public void setRoot(Node root) {
		this.root=root;
	}
	public Node getRoot() {
		return root;
	}
}
