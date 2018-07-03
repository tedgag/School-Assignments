// -----------------------------------------------------
// Assignment 3
// Written by: Édouard Gagné 40061204
// Node class that has a value and key attributes as well as a left an right pointers to node. It has setters and getters for these four attributes.
// -----------------------------------------------------
public class Node {
	private int value;
	private int key;
	private Node left;
	private Node right;
	
	public Node(int value) {
		this.value=value;
		key=0;
		left=null;
		right=null;
	}
	public Node(int value,int key) {
		this.value=value;
		this.key=key;
		left=null;
		right=null;
	}
	public Node(int value, Node left, Node right) {
		this.value=value;
		this.left=left;
		this.right=right;
	}
	public Node(int value, int key, Node left, Node right) {
		this.value=value;
		this.key=key;
		this.left=left;
		this.right=right;
	}
	public void setRight(Node right) {
		this.right=right;
	}
	public void setLeft(Node left) {
		this.left=left;
	}
	public Node getLeft() {
		return left;
	}
	public Node getRight() {
		return right;
	}
	public void setValue(int value) {
		this.value=value;
	}
	public int getValue() {
		return value;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
}
