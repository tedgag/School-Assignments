import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//-----------------------------------------------------
//Assignment 3
// Written by: Édouard Gagné 40061204
// Classe that has an inner class called SplayNode that allows the creation of a splay tree from a file containing commands.
// It has tree new attributes: the number of zigzag, the number of zigzags and the number of compare used to build a Splay Tree. 
//-----------------------------------------------------
public class SplayTree extends Tree {
	// SplayNode class that extends the Node class as well as having a new attribute named ”parent” that point to its parent in the tree structure. 
	public class SplayNode extends Node {
		private SplayNode parent;
		private SplayNode left;
		private SplayNode right;
		public SplayNode(int value, SplayNode left, SplayNode right, SplayNode parent) {
			super(value,left,right);
			this.parent=parent;
		}
		public SplayNode(int value, SplayNode parent) {
			super(value);
			this.parent=parent;
		}
		public SplayNode(int value) {
			super(value);
			parent=null;
		}
		public SplayNode getParent() {
			return parent;
		}
		public void setParent(SplayNode parent) {
			this.parent = parent;
		}
		public SplayNode getLeft() {
			return left;
		}
		public void setLeft(SplayNode left) {
			this.left = left;
		}
		public SplayNode getRight() {
			return right;
		}
		public void setRight(SplayNode right) {
			this.right = right;
		}
	}
	
	private SplayNode root;
	private int zigzig;
	private int zigzag;
	private int compare;
	public SplayTree(SplayNode root) {
		super(root);
		zigzig=0;
		zigzag=0;
		compare=0;
	}
	public SplayTree() {
		super();
		zigzig=0;
		zigzag=0;
		compare=0;
	}
	//The splay method takes a splay node as an argument and splay it to the root of the tree or one of its children. 
	//It does so by executing multiple series of rotations using the rotateLeft and rotateRight classes depending on how it is positioned
	//in respect to its parent and grandparent until the node is either at the root or is one of its children. 
	public void splay(SplayNode node) {
		while(node.parent!=null && node.parent!=root) {
			SplayNode parent= node.parent;
			SplayNode grandParent= parent.parent;
			if (node==parent.left) {
				if (parent==grandParent.left) {
					rotateLeft(parent,grandParent);
					rotateLeft(node,parent);
					zigzig++;
				}
				else {
					rotateLeft(node,node.parent);
					rotateRight(node,node.parent);
					zigzag++;
				}
				
			}
			else {
				if (parent==grandParent.left) {
					rotateRight(node,node.parent);
					rotateLeft(node,node.parent);
					zigzag++;
				}
				else {
					rotateRight(parent,grandParent);
					rotateRight(node,parent);
					zigzig++;
				}
			}
		}
	}
	// Rotate the node to the left
	public void rotateLeft(SplayNode c, SplayNode p) {
		// setting parent pointers...
		if (p.parent!=null) {
			if (p==p.parent.left) 
				p.parent.left=c;
			else
				p.parent.right=c;
		}
		if (c.right!=null) 
			c.right.parent=p;
		c.parent = p.parent;
		p.parent = c;
		// doing the rotation
		p.left = c.right;
		c.right=p;
		if (p==root)
			root=c;
	}
	// Rotate the noe to the right
	public void rotateRight(SplayNode c, SplayNode p) {
		// setting parent pointers...
		if (p.parent!=null) {
			if (p==p.parent.left) 
				p.parent.left=c;
			else
				p.parent.right=c;
		}
		if (c.left!=null) 
			c.left.parent=p;
		c.parent = p.parent;
		p.parent = c;
		// doing the rotation
		p.right = c.left;
		c.left = p;
		if (p==root)
			root=c;
	}
	// The splay method is used in the add method which adds a node to the tree. If the tree has no node, the tree is 
	// initialized with the node as its root, otherwise the method finds where the node is supposed to go thanks to the 
	// binary tree property, insert it and splay it. 
	public void add(int value) {
		SplayNode node=root;
		SplayNode parent=null;
		if (node==null) {
			root=new SplayNode(value);
			return;
		}
		boolean right=false;
		while (node!=null) {
			parent=node;
			if (value > node.getValue()) {
				node=node.right;
				right=true;
				compare++;
			} else {	
				node=node.left;
				right=false;
				compare++;
			}
			
		}
		node = new SplayNode(value,parent);
		if (right)
			parent.right=node;
		else
			parent.left=node;
		splay(node);
	}
	// The remove method takes a node usually found using the find method and removes it depending on three cases. 
	// If it has two children, the node swaps its value with the smallest value of its right sub tree and delete this node, 
	// then splay its parent. If it has one children, the node is deleted, and its child is connected to its parent. If it 
	// has no parent, it is simply deleted. 
	public void remove(SplayNode node) {
		if (node==null) {
			return;
		}
		if (node.left!=null && node.right!=null) {
				SplayNode curr=node.right;
				while(curr.left!=null) {
					curr=curr.left;
				}
				int temp=curr.getValue();
				curr.setValue(node.getValue());
				node.setValue(temp);
				SplayNode parent= curr.parent;
				if (curr==node.right) {
					curr.right.parent=node;
					node.right=curr.right;
				} else if (curr.right!=null) {
					parent.left=curr.right;
					curr.right.parent=parent;
				} else {
					parent.left=null;
				}
				splay(parent);
		} else if (node.left!=null) {
			if(node==root) {
				node.left.parent=null;
				root=node.left;
			}
			else {
				node.left.parent=root;
				root.left=node.left;
			}
			
		} else if (node.right!=null) {
			if(node==root) {
				node.right.parent=null;
				root=node.right;
			}
			else {
				node.right.parent=root;
				root.right=node.right;
			}
			
		}  else {
			if (node==root)
				root=null;
			else if (node==root.left) 
				root.left=null;
			else
				root.right=null;
			
		}
	}
    // The find method finds a node in the tree using again the binary search tree property and splay it. 
	public SplayNode find(int value) {
		SplayNode curr=root;
		while(curr!=null) {
			if (value>curr.getValue()) {
				curr=curr.right;
				compare++;
			}
			else if (value < curr.getValue()) {
				curr=curr.left;
				compare++;
				compare++;
			}
			else {
				splay(curr);
				compare++;
				compare++;
				return curr;
			}
		}
		return null;
	}
	// The traverse method execute the postorderTraversal method on the root.
	public void traverse() {
		postorderTraversal(root);
		System.out.println();
	}
	//Prints the post order traversal of the tree. 
	public void postorderTraversal(SplayNode node) {
		if (node==null)
			return;
		postorderTraversal(node.left);
		postorderTraversal(node.right);
		System.out.print(node.getValue()+",");
	}
	
	// The processFile method will iterate a file containing a serie of command and execute either add,
	// remove or find methods. If the step argument is the same as the current iterative step in the method, 
	// a post-order traversal is executed using the traverse method. This method will also print the number of
	// zig-zigs, zig-zags and compares executed by the process. 
	public void processFile(String fileName, int step) {
		try {
			Scanner stream = new Scanner(new FileInputStream(fileName));
			String s;
			int currStep=0;
			while(stream.hasNext()) {
				s=stream.next();
				if (s.charAt(0)=='a') {
					currStep++;
					int value =Integer.parseInt(s.substring(1));
					add(value);
					if(currStep==step) {
						System.out.print("Traversal at " +step+": ");
						traverse();
					}
				} else if (s.charAt(0)=='r') {
					currStep++;
					int value =Integer.parseInt(s.substring(1));
					remove(find(value));
					if(currStep==step) {
						System.out.print("Traversal at " +step+": ");
						traverse();
					}
				} else if (s.charAt(0)=='f') {
					currStep++;
					int value =Integer.parseInt(s.substring(1));
					find(value);
					if(currStep==step) {
						System.out.print("Traversal at " +step+": ");
						traverse();
					}
				}
			}
			System.out.println(compare + " compares");
			System.out.println(zigzig + " Zig-Zigs");
			System.out.println(zigzag + " Zig-Zags");
			stream.close();
		} catch (FileNotFoundException e) {}
	}
	// The main method will process the file parsed as an argument. 
	public static void main(String[] args) {
		SplayTree tree= new SplayTree();
			if (args.length==2) 
				tree.processFile(args[0],Integer.parseInt(args[1]));
			else 
				tree.processFile(args[0],-1);
	}
}
