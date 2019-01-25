import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * This class is responsible for finding all the possible locations that Peggy
 * and Sam can meet. It prints out all the possible locations, and it is sorted
 * using QuickSort. The locations where they could meet are found using a
 * modified implementation of Depth First Search.
 * 
 * @author Jeremy Galang
 *
 */
public class FindPath {

	private Stack<StreamNode> dfs = new Stack<StreamNode>(); // temporary stack we use to keep track of the path
	private ArrayList<String> avoid, peggy, sam; // the locations we want to avoid and peggy's/sam's locations
	private HashMap<String, StreamNode> path; // lookup table to see which nodes are linked
	private boolean goalFound; // if we've found the goal, gets reset for every iteration
	private Set<String> toSort; // set that helps avoid duplicates
	private ArrayList<Stack<StreamNode>> solutions; // an ArrayList of stacks which contain the possible meeting nodes

	/**
	 * Constructor for the FindPath class
	 * 
	 * @param path  - The HashMap containing all the node information
	 * @param peggy - all of peggy's locations
	 * @param sam   - all of sam's locations
	 * @param avoid - all of the nodes we're trying to avoid
	 */
	public FindPath(HashMap<String, StreamNode> path, ArrayList<String> peggy, ArrayList<String> sam,
			ArrayList<String> avoid) {
		this.path = path;
		this.sam = sam;
		this.peggy = peggy;
		this.avoid = avoid;
		this.goalFound = false;
		this.toSort = new HashSet<String>();
		this.solutions = new ArrayList<Stack<StreamNode>>();

	}

	/**
	 * This method traverses all the nodes in the given map. It takes each location
	 * for Peggy and Sam and tries to find a solution path
	 */
	public void PathFinder() {

		if (peggy.size() == 1 && sam.size() == 1) { // if there's only one of each
			performDFS(peggy.get(0), sam.get(0)); // call depth first search recursively once

			if (dfs.size() == 1) { // if only the root is left
				dfs.pop();
			}
			if (goalFound == true) { // if we find the goal
				Stack<StreamNode> temp = dfs;
				solutions.add(temp); // add this path to our solution
			}
		} else {
			for (int i = 0; i < peggy.size(); i++) { // if there's more than one location for Peggy and Sam
				for (int k = 0; k < sam.size(); k++) {
					dfs = new Stack<StreamNode>(); // re-initialize the stack, (calling .clear() gets rid of the stored
													// stacks in solutions)
					performDFS(peggy.get(i), sam.get(k)); // call dfs recursively for each Peggy and Sam pairing

					if (dfs.size() == 1) { // if only the root is left and the goal wasn't found (which is already
											// assumed)
						dfs.pop();
					}

					if (goalFound == true) {
						Stack<StreamNode> temp = dfs;
						solutions.add(temp); // add this path to our solutions
					}
					unvisitAll(); // unvisit all the nodes for the next iteration
					goalFound = false; // set the goal back to false

				}
			}

		}

	}

	/**
	 * This method is an implementation of depth first search. It utilizes a stack
	 * to keep track of the path we're using to traverse the map.
	 * 
	 * 
	 * 
	 * @param start - the name of the node we're starting on
	 * @param end   - the name of the node we want to end on
	 */
	private void performDFS(String start, String end) {

		StreamNode root = path.get(start); // the starting node we're looking at
		dfs.push(root); // push it onto the stack
		root.visit(); // visit it

		// check if we're avoiding the node we're currently at
		if (avoid.contains(root.returnName())) {
			dfs.pop(); // if so, we pop it and disregard the node completely
			return;
		}

		// if the current node has children and doesn't happen to be the end goal
		if (root.getLinks().size() > 0 && !root.returnName().equals(end)) {
			for (int r = 0; r < root.getLinks().size(); r++) { // iterate through each child
				StreamNode child = root.getLinks().get(r); // get the child

				if (!child.isVisited()) { // if we haven't visited this child yet
					performDFS(child.returnName(), end); // check if that child has children of its own
				}

				// if we've visited all the children and did not find a goal through them
				if (!dfs.isEmpty()) {
					if (dfs.peek().allLinksVisited() && goalFound == false && dfs.size() > 1) {
						dfs.pop();
					}
				}

			}
		}

		// if the goal has been found, mark it
		if (root.returnName().equals(end)) {
			goalFound = true;

		}

		// if we've reached a dead end and its not the goal
		if (root.getLinks().size() == 0 && !root.returnName().equals(end)) {
			dfs.pop(); // pop it off the stack
			while (dfs.peek().getLinks().size() == 1 && !dfs.isEmpty()) {
				dfs.pop();
				// this backtracks and pops off all the nodes with 1 link until we reach a node
				// w/more than 1 child

				if (dfs.isEmpty()) { // this prevents us getting an empty stack
					return; // this basically checks if there is no path to the goal at all
				}

			}
		}

	}

	/**
	 * Marks all the nodes as unvisited, this is only used after the first start and
	 * end pair has been checked
	 */
	public void unvisitAll() {
		for (StreamNode node : path.values()) { // for each node in the path
			node.unVisit();// unvisit it
		}
	}

	/**
	 * This method alphabetizes the path.
	 * 
	 * First it empties the stack into an array, and then that array of strings (the
	 * strings are the nodes) is sorted with the QuickSort algorithm.
	 */
	public void alphabetize() {

		if (!solutions.isEmpty()) { // if we've found at least one solution path

			for (int i = 0; i < solutions.size(); i++) {
				Stack<StreamNode> current = solutions.get(i); // grab the stack at the specified index

				while (!current.isEmpty()) { // if the stack isnt empty
					if (!toSort.contains(current.peek().returnName())) { // if the name hasn't already been added
						StreamNode print = current.pop(); // pop everything off of the stack
						toSort.add(print.returnName()); // store the name of the node into another array
					} else {
						current.pop(); // otherwise we get rid of it and ignore it
					}
				}

			}
			String[] qs = toSort.toArray(new String[toSort.size()]);

			if (qs.length > 0) { // assuming there is a solution
				QuickSort x = new QuickSort(qs); // initialize quicksort
				x.sort(); // sort it
				x.printString(); // then print it
			}

		}

		// otherwise nothing should be printed out

	}

}
