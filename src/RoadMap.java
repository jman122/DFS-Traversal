import java.io.*;
import java.util.Iterator;
import java.util.Stack;
/**
 * RoadMap.java
 * This class represents the road map
 * A graph will be used to store the map and to try to find a path from the starting point to the destination
 * @author Jia Yu Man
 */
public class RoadMap {

	private int START;
	private int END;
	private int WIDTH;
	private int LENGTH;
	private int INITIAL_BUDGET;
	private int TOLL;
	private int GAIN;
	private Graph graph;
	private Stack<Node> stack;
	
	/**
	 * Constructor for building a graph from the input file
	 * @param inputFile
	 * @throws MapException
	 */
	public RoadMap(String inputFile) throws MapException {
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(inputFile));
			Integer.parseInt(input.readLine());
			START = Integer.parseInt(input.readLine());
			END = Integer.parseInt(input.readLine());
			WIDTH = Integer.parseInt(input.readLine());
			LENGTH = Integer.parseInt(input.readLine());
			INITIAL_BUDGET = Integer.parseInt(input.readLine());
			TOLL = Integer.parseInt(input.readLine());
			GAIN = Integer.parseInt(input.readLine());
			
			graph = new Graph(WIDTH*LENGTH);                       // Creates a graph from the specified width and length
			
			String[] lineArray = new String[2*LENGTH - 1];         // Creates a String array storing the characters
            String chars;
            char symbol;
            int edge = 0;
            int size = 0;
            while ((chars = input.readLine()) != null){
                lineArray[size] = chars;
                size = size + 1;
            }
            for (int x = 0; x < size; x++) {                        // Loop through the characters 
            	for (int y = 0; y < WIDTH - 1; y++) {
            		if (x%2 == 0) {                                 // Horizontal connecting edges
            			symbol = lineArray[x].charAt(2*y+1);
            			if (symbol != 'X') {                        // Avoid Houses
            				if (symbol == 'F') {                    // If symbol is an open road
            					edge = 0;
            				}
            				else if (symbol == 'T') {               // If symbol is a toll road
            					edge = 1;
            				}
            				else if (symbol == 'C') {               // If symbol is a reward road
            					edge = -1;
            				}
            				if (lineArray[x].charAt(2*y) == '+' && lineArray[x].charAt(2*y+2) == '+'){                      // If symbol is an intersection it will insert an edge for the nodes
            					graph.insertEdge(graph.getNode((x/2)*(WIDTH)+y), graph.getNode((x/2)*(WIDTH)+y+1), edge);
            				}
            			}
            		}
            		else if (x%2 == 1) {                            // Vertical connecting edges 
            			symbol = lineArray[x].charAt(2*y);
            			if (symbol != 'X') {                        // Avoid houses
            				if (symbol == 'F') {                    // If symbol is an open road
            					edge = 0;
            				}
            				else if (symbol == 'T') {               // If symbol is a toll road
            					edge = 1;
            				}
            				else if (symbol == 'C') {               // If symbol is a reward road
            					edge = -1;
            				}
            				if (lineArray[x-1].charAt(2*y) == '+' && lineArray[x+1].charAt(2*y+2) == '+'){                  // If symbol is an intersection it will insert an edge for the nodes
            					graph.insertEdge(graph.getNode((x/2)*(WIDTH)+y), graph.getNode((x/2+1)*(WIDTH)+y), edge);
            				}
            			}
            		}
            	}
            }
		}
		catch (Exception e) {             // If any exceptions are caught it will throw MapException
			throw new MapException();
		}
	}
	
	/**
	 * @return graph
	 */
	public Graph getGraph() {
		
		return graph;
	}
	
	/**
	 * @return starting node
	 */
	public int getStartingNode() {
		
		return START;
	}
	
	/**
	 * @return destination node
	 */
	public int getDestinationNode() {
		
		return END;
	}
	
	/**
	 * @return initial money
	 */
	public int getInitialMoney() {
		
		return INITIAL_BUDGET;
	}
	
	/**
	 * Utilizes a stack 
	 * @param start
	 * @param destination
	 * @param initialMoney
	 * @return iterator containing nodes of path from start to destination  
	 */
	public Iterator findPath(int start, int destination, int initialMoney) {
		
		stack = new Stack<>();
		
		if (path(start, destination, initialMoney)) {
			return stack.iterator();
		}
		else {
			return null;
		}
	}
	
	/**
	 * @param s
	 * @param d
	 * @param startMoney
	 * @return true if there is a path, else false 
	 */
	private boolean path (int s, int d, int startMoney) {
		
		Node startNode = graph.getNode(s);
		startNode.setMark(true);  // Marks first node 
		stack.push(startNode);
		if (s == d) {             // Base case
			return true;
		}
		else {                                                              // Recursive case
			Iterator<Edge> incident = graph.incidentEdges(startNode);
			while (incident.hasNext()) {                                    // Loops through edges
				Edge next = incident.next();
				Node endPoint = next.secondEndpoint();
				if (!endPoint.getMark()) {                                  // If not marked 
					int money = 0;                                          
					if (next.getType() == 0) {                              // If an open road do nothing
						money = startMoney;
					}
					else if (next.getType() == 1) {                         // If a toll road subtract toll from money 
						money = startMoney - TOLL;
					}
					else {
						money = startMoney + GAIN;                          // If a reward road add gain to money
					}
					if (money >= 0) {
						if (path(endPoint.getName(), d, money) == true) {   
							return true;
						}
					}
				}
			}
			startNode.setMark(false);  // unmarks node 
			stack.pop();               // removes node from stack
			return false;             
		}
	}
}
