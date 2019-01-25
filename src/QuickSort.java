/**
 * This class is just a standard implementation of QuickSort, it simply sorts
 * an array of strings in alphabetical order.
 * 
 * @author Jeremy Galang
 *
 */
public class QuickSort {

	private String[] nodes; //array of the node's names represented as strings
	private int length; //number of nodes
	
	public QuickSort(String[] list) {
		
		if(list.length == 0 || list == null) { //error checking
			
			return; 
		}
		this.nodes = list; 
		this.length = list.length;
		
		
		
	}
	
	public void sort() {
		quickSort(0, length-1); //call QuickSort
	}
	
	public void quickSort(int begin, int end) {
		int i = begin; 
		int j = end; 
		
		String pivot = this.nodes[begin + (end - begin) / 2]; //selecting our pivot
		
		while(i <= j) {  
			//comparesToIgnoreCase simply just compares the string and returns a negative/positive int or 0
			//depending on the comparisons
			while(nodes[i].compareToIgnoreCase(pivot) < 0){ 
				i++;
			}
			while(nodes[j].compareToIgnoreCase(pivot) > 0) {
				j--; 
			}
	
			if(i <= j) {
				swap(i, j); //swap the two elements
				i++;
				j--;
				
			}
	
		}
		if(begin < j) {
			quickSort(begin, j); 
		}
		if(i < end) {
			quickSort(i, end);
		}
		
	}
		
	/**
	 * Swaps the two values
	 * @param i
	 * @param j
	 */
	public void swap(int i, int j) {
		String temp = nodes[i];
		nodes[i] = nodes[j];
		nodes[j] = temp; 
	}
	
	
	/**
	 * This prints the entire sorted array
	 */
	public void printString() {
		for(int i = 0; i < nodes.length; i++) {
			System.out.println(nodes[i]);
		}
	}
			
}
