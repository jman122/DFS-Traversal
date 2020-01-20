/**
 * Edge.java
 * This class represents an edge of the graph
 * @author Jia Yu Man
 */
public class Edge {

	private Node u, v;
	private int type;
	
	/**
	 * Constructor
	 * @param u first end point
	 * @param v second end point
	 * @param type can be 0 or -1 or 1
	 */
	public Edge(Node u, Node v, int type) {
		
		this.u = u;
		this.v = v;
		this.type = type;
	}
	
	/**
	 * @return first end point u
	 */
	public Node firstEndpoint() {
		
		return u;
	}
	
	/**
	 * @return second end point v
	 */
	public Node secondEndpoint() {
		
		return v;
	}
	
	/**
	 * @return type
	 */
	public int getType() {
		
		return type;
	}
}

