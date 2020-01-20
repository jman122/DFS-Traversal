/**
 * Graph.java
 * This class represents an undirected graph
 * Adjacency matrix is used to represent the graph
 * @author Jia Yu Man
 */
import java.util.*;

public class Graph implements GraphADT{

	private int n;
	private Edge adjMatrix [][];
	private Node nodes [];
	
	/**
	 * Constructor that creates an adjacency matrix 
	 * @param n
	 */
	public Graph(int n) {
		
		this.n = n;
		
		nodes = new Node[n];
		adjMatrix = new Edge[n][n];
		
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i);
		}
	}
	
	/**
	 * Returns the node with the specified name
	 * Throws GraphException if no name is found 
	 */
	public Node getNode(int name) throws GraphException {
		
		if (name < 0 || name > nodes.length - 1) {
			throw new GraphException();
		}
		else {
			return nodes[name];
		}
	}

	/**
	 * Adds an edge of the given nodes u and v and edgeType
	 * Throws GraphException if either nodes don't exist or if there already is an edge connecting the two nodes
	 */
	public void insertEdge(Node u, Node v, int edgeType) throws GraphException {
		
		if (findNode(u) && findNode(v)) {
			if (adjMatrix[u.getName()][v.getName()] == null) {
				adjMatrix[u.getName()][v.getName()] = new Edge(u, v, edgeType);
				adjMatrix[v.getName()][u.getName()] = new Edge(v, u, edgeType);
			}
			else {
				throw new GraphException();
			}
		}
		else {
			throw new GraphException();
		}
	}

	/**
	 * Returns a java Iterator storing all the edges incident on node u
	 * Returns null if u doesn't have any incidents 
	 * Throws GraphException if node u is non existent 
	 */
	public Iterator<Edge> incidentEdges(Node u) throws GraphException {
		
		if (findNode(u)) {
			ArrayList<Edge> edges = new ArrayList<>();
			
			for (int i = 0; i < n; i++) {
				if (adjMatrix[u.getName()][i] != null) {
					edges.add(adjMatrix[u.getName()][i]);
				}
			}
			return edges.iterator();
		}
		else if (!findNode(u)){
			throw new GraphException();
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the edge connecting nodes u and v
	 * Throws GraphException if nodes u and v don't exist
	 */
	public Edge getEdge(Node u, Node v) throws GraphException {
		
		if (!findNode(u) || !findNode(v)) {
			throw new GraphException();
		}
		else {
			return adjMatrix[u.getName()][v.getName()];
		}
	}

	/**
	 * Returns true if nodes u and v are adjacent, else false
	 * Throws GraphException when trying to access an invalid node
	 */
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		
		if (findNode(u) && findNode(v)) {
			if (adjMatrix[u.getName()][v.getName()] == null) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			throw new GraphException();
		}
	}
	
	/**
	 * Similar to getNode method
	 * @param node 
	 * @return true if the node is found, else false
	 */
	private boolean findNode(Node node) {
		
		int nameOfNode = node.getName();
		
		if (nameOfNode >= 0 && nameOfNode < nodes.length) {
			return true;
		}
		else {
			return false;
		}
	}
}
