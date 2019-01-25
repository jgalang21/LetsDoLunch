import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * Let's Do Lunch Programming Exercise
 * 
 * This class contains the main method, which handles the scanning
 * and finding the places where Peggy and Sam can meet.
 * 
 * @author Jeremy Galang
 *
 */
public class Main {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in); // initialize scanner
		
		while (scan.hasNext()) { // scanning in the user's input
			String begin = scan.next();

			if (begin.equals("Map:")) { // if the next line is map, start scanning the linked pairs
				break;
			}
		}

        // scan in all the nodes and pair them
		ArrayList<String> mapped = scanString("Avoid:"); // map of pairings to print out
		
		// scan in all the nodes we're avoiding and print them
		ArrayList<String> avoidList = scanString("Peggy:");

		// scan in all of peggy's locations, there can be more than one
		ArrayList<String> pegLoc = scanString("Sam:");

		// scan in all of sam's locations, there can be more than one
		Scanner sam = new Scanner(System.in);

		String x = sam.nextLine(); //scan in the next line
		String[] t = x.split(" "); //get rid of the spaces and store them into an array

		ArrayList<String> samLoc = new ArrayList<String>(Arrays.asList(t));

		// after all of sam's locations have been scanned in, we then begin finding the goal

		String[] mapArray = mapped.toArray(new String[mapped.size()]);

		TreeGenerator tree = new TreeGenerator(mapArray); // generate a new tree
		HashMap<String, StreamNode> generated = tree.connectNodes(); // connect all the nodes

		FindPath path = new FindPath(generated, pegLoc, samLoc, avoidList);
		// pass in the tree, peggy's location(s), sam's location(s), and the nodes we're
		// avoiding
		path.PathFinder(); // find the path(s)
		path.alphabetize(); // alphabetize it

	}

	/**
	 * This method takes in all of the user's inputs, it scans in each word
	 * separately and pairs them.
	 * 
	 * @param start - the starting keyword
	 * @param end   - ending keyword that moves onto the next set of inputs
	 * @return - an ArrayList that references the nodes
	 */
	public static ArrayList<String> scanString(String end) {
		Scanner scan = new Scanner(System.in);

		ArrayList<String> list = new ArrayList<String>(); //list of strings to return

		String elements = ""; //the string of elements

		while (scan.hasNextLine()) { //while we're still scanning in strings
			elements = scan.next(); //grab the next word

			if (elements.equals(end)) { //until we want to add the next set of inputs
				break;
			}
			list.add(elements); //add them to the ArrayList

		}

		return list; //return the list
	}

}
