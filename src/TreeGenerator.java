import java.util.HashMap;

/**
 * This class creates the tree of nodes based off of the given string.
 * It stores the nodes in a HashMap,which is used as a reference for
 * the FindPath class.
 * 
 * @author Jeremy Galang
 *
 */
public class TreeGenerator {

	private String[] treeMap; // string of nodes scanned in
	private StreamNode[] nodes; // array that contains all the said nodes

	/**
	 * Constructor for tree generator
	 * 
	 * @param map - all of the nodes we've scanned in
	 */
	public TreeGenerator(String[] map) {
		if (map == null || map.length == 0) { //in case the map has nothing
			throw new NullPointerException();
		}

		this.treeMap = map;
		this.nodes = new StreamNode[map.length];

		for (int i = 0; i < map.length; i++) { //Initialize new nodes
			this.nodes[i] = new StreamNode(treeMap[i]);
		}

	}

	/**
	 * Connects all the nodes to their respective links and putting them in a HashMap
	 * 
	 * At each element at the HashMap, the key value represents the name of the node
	 * and the value represents the node data itself. The key is strictly used for identifying
	 * the node, while the value can be modified. 
	 * 
	 * @return A HashMap containing the information of all the nodes
	 */
	public HashMap<String, StreamNode> connectNodes() {

		HashMap<String, StreamNode> map = new HashMap<String, StreamNode>();
		

		if (nodes.length == 1) { // if there's only one node
			map.put(nodes[0].returnName(), nodes[0]); 
			return map;
		}

		for (int i = 1; i < nodes.length; i += 2) {

			// if one of the pairings doesn't exist
			if (!map.containsKey(nodes[i - 1].returnName()) || !map.containsKey(nodes[i].returnName())) {

				// if both the nodes don't exist, add them to the HashMap
				if (!map.containsKey(nodes[i - 1].returnName()) && !map.containsKey(nodes[i].returnName())) {
					nodes[i - 1].addLink(nodes[i]);
					map.put(nodes[i - 1].returnName(), nodes[i - 1]);
					map.put(nodes[i].returnName(), nodes[i]);

				} else if (!map.containsKey(nodes[i - 1].returnName())) { // if only the first node doesn't exist

					StreamNode x = map.get(nodes[i].returnName());
					nodes[i - 1].addLink(x);
					map.put(nodes[i - 1].returnName(), nodes[i - 1]);

				} else if (!map.containsKey(nodes[i].returnName())) { // if only the second node doesn't exist

					StreamNode x = map.get(nodes[i - 1].returnName());
					x.addLink(nodes[i]);
					map.put(nodes[i].returnName(), nodes[i]);

				}

			}

			else { // if the pairing already exists in the map, they might not be linked, so here
					// we link them
				StreamNode t1 = map.get(nodes[i - 1].returnName());
				StreamNode t2 = map.get(nodes[i].returnName());
				t1.addLink(t2);
			}

		}

		return map;
	}

}
