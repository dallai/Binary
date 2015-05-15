package tree;

class Node
{
	public int data;
	public Node left;
	public Node right;
	public Node parent;
	
	public Node(int ddata, Node left, Node right)
	{
	    this.data = ddata;
	    this.left = left;
	    this.right =right;    
	    this.parent = null;
	}
	
	public void displayNode(Node n)
	{
	    System.out.println(n.data +" ");  
	}
	public void displayNode()
	{
	    System.out.println(data +" ");  
	}
}