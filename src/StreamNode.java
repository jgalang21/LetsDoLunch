import java.util.ArrayList;

/**
 * This class represents a Node object. 
 * @author Jeremy Galang
 *
 */
public class StreamNode {

	private String name; // name of the node
	private ArrayList<StreamNode> links = new ArrayList<StreamNode>(); // the node's links
	private boolean visited; // whether the node has been visited or not

	/**
	 * Constructor that creates a new Node
	 * 
	 * @param name Name of the node
	 */

	public StreamNode(String name) {
		this.name = name;
		this.links = new ArrayList<StreamNode>();
		this.visited = false;

	}

	/**
	 * Adds a link to another node.
	 * 
	 * @param ds - the link that we're adding
	 */
	public void addLink(StreamNode ds) {
		links.add(ds);
	}

	/**
	 * @return An ArrayList of StreamNodes, all the links that a specified node has
	 */
	public ArrayList<StreamNode> getLinks() {
		return links;
	}

	/**
	 * @return The name of the node 
	 */
	public String returnName() {
		return name;
	}
	
	/**
	 * @return 
	 * true if all of the children of a root node have been visited
	 * false otherwise
	 */
	public boolean allLinksVisited() {
		int x = 0;
		for(int i = 0; i < links.size(); i++) {
			if(links.get(i).isVisited() == true) {
				x++; 
			}
		}
		
		if(x == links.size()) { //if all the links are visited
			return true;
		}
		else { //all the links haven't been visited
			return false;
		}
	}

	/**
	 * @return Marks the node as visited
	 */
	public boolean visit() {
		visited = true;
		return visited;
	}
	
	/**
	 * @return Marks the node as unvisited
	 */
	public boolean unVisit() {
		visited = false;
		return visited;
	}

	/**
	 * @return Whether the node has been visited or not
	 */
	public boolean isVisited() {
		return visited;
	}
	

}
