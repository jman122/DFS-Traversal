/**
 * Node.java
 * This class represent a node of the graph
 * @author Jia Yu Man
 */
public class Node {
	
	private int name;
	private boolean mark;
	
	/**
	 * This is the constructor for the class and it creates a node with the given name
	 * @param name
	 */
	public Node(int name) {
		
		this.name = name;
	}
	
	/**
	 * Marks the node with either true or false
	 * @param mark
	 */
	public void setMark(boolean mark) {
		
		this.mark = mark;
	}
	
	/**
	 * @return mark
	 */
	public boolean getMark() {
		
		return mark;
	}
	
	/**
	 * @return name
	 */
	public int getName() {
		
		return name;
	}
}

