package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



class BinaryTree
{
	Node root;
	
	public BinaryTree()
	{
	    this.root = null;
	}
	
	public Node insertLeft(int parent,int leftvalue )
	{
		Node n = find(root,parent);
	    Node leftchild = new Node(leftvalue, null, null);
	    n.left = leftchild;
	    leftchild.parent = n;
	    return leftchild;
	}
	
	public Node insertRight(int parent, int rightvalue)
	{
	    Node n = find(root,parent);
	    Node rightchild = new Node(rightvalue, null, null);
	    n.right = rightchild;
	    rightchild.parent = n;
	    return rightchild;
	}
	
	public void insertRoot(int data)
	{
	    root = new Node(data, null, null);
	}
	
	public Node getRoot()
	{
	    return root;
	}
	
	public Node find(Node n,int key)
	{       
	    Node result = null;
	    if (n == null)
	        return null;
	    if (n.data ==key)
	        return n;
	    if (n.left != null)
	        result = find(n.left,key);
	    if (result == null)
	        result = find(n.right,key);
	    return result;
	} 
	
	public int getheight(Node root)
	{
	    if(root == null)
	        return  0;      
	    return Math.max(getheight(root.left),getheight(root.right))+1; 
	}
	
	public static void printTree(Node n)
	{       
	    if( n == null)
	        return;
	    

	    printTree(n.left);
	    n.displayNode(n);
	    printTree(n.right);             
	}
	
	public static void cloneTree(BinaryTree source, BinaryTree target) {
		
		Node clonePoint = source.find(source.root, target.root.data);
		if(target.root.left.data == clonePoint.parent.data) {
			clonePoint.left = target.root.right;
		} else {
			clonePoint.right = target.root.left;
		}
	}
	
	public static void rotateTree(BinaryTree btree, BinaryTree resTree, Node sourceMe, Node targetMe) {
        
	//	int input = 0;
		
		if(sourceMe == null || targetMe == null) return;
		if(targetMe.data == btree.root.data) return;
		
		Node childLeft = null;
		Node childRight = null;
		Node resLeftChild = null;
		Node resRightChild = null;
		
		List<Node> array = new ArrayList<Node>();
		
	    Node parent = sourceMe.parent;
	    childLeft = sourceMe.left;
	    childRight = sourceMe.right;

	    if(parent != null) {
	    	array.add(parent);
	    }
	    if(childLeft != null) {
	    	array.add(childLeft);
	    }
	    if(childRight != null) {
	    	array.add(childRight);
	    }

	    for(int i = 0 ; i < array.size() ; i++) {
	    	if( targetMe.parent != null && array.get(i).data == targetMe.parent.data)
	    		array.remove(i);
	    }
	    
	    if(array.size() == 1)
			resLeftChild = resTree.insertLeft(sourceMe.data, array.get(0).data);
		if(array.size() == 2){
			resLeftChild = resTree.insertLeft(sourceMe.data, array.get(0).data);
			resRightChild = resTree.insertRight(sourceMe.data, array.get(1).data);}
			
		if(array.size() == 1)
			rotateTree(btree,resTree,array.get(0), resLeftChild);
		if(array.size() == 2) {
			rotateTree(btree,resTree,array.get(0), resLeftChild);
			rotateTree(btree,resTree,array.get(1), resRightChild);
		}
	}
	
	public static void main(String args[]) {
		BinaryTree btree = new BinaryTree();
		BinaryTree resBtree= new BinaryTree();
		
		int pickNumber = 4;
		btree.insertRoot(100);
		btree.insertLeft(100, 2);
		btree.insertRight(100, 3);
		btree.insertLeft(2, 4);
		btree.insertRight(2, 5);
		btree.insertLeft(3, 6);
		btree.insertRight(3, 7);
		btree.insertRight(6, 8);
		btree.insertLeft(5, 9);
		btree.insertRight(5, 10);
		btree.insertRight(10, 11);

		Node me = btree.find(btree.root, pickNumber);
		resBtree.insertRoot(me.data);
		
		rotateTree(btree,resBtree,me, resBtree.root);
		cloneTree(resBtree, btree);
	
		BTreePrinter.printNode(btree.root);
		BTreePrinter.printNode(resBtree.root);
	}
}


