import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

class MergeSort {

	static ArrayList<Integer> list = new ArrayList<Integer>();
	// Merges two subarrays of arr[].
	// First subarray is arr[l..m]
	// Second subarray is arr[m+1..r]
	void merge(ArrayList<Integer> list, int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
			L[i] = list.get(l + i);
		for (int j = 0; j < n2; ++j)
			R[j] = list.get(m + 1 + j);

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				list.set(k, L[i]);
				i++;
			} else {
				list.set(k, R[j]);
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			list.set(k, L[i]);
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			list.set(k, R[j]);
			j++;
			k++;
		}
	}

	// Main function that sorts arr[l..r] using
	// merge()
	void sort(ArrayList<Integer> list, int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = (l + r) / 2;

			// Sort first and second halves
			sort(list, l, m);
			sort(list, m + 1, r);

			// Merge the sorted halves
			merge(list, l, m, r);
		}
	}

	/* A utility function to print array of size n */
	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	static void printList(ArrayList<Integer> list){
		int n = list.size();
		for (int i = 0; i < n; ++i)
			System.out.print(list.get(i) + " ");
		System.out.println();
	}

	static void readFromFile(String fileName) {
		try {
      File myObj = new File(fileName);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNext()) {
        int data = myReader.nextInt();
				list.add(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
	}

	// Driver method
	public static void main(String args[]) {
		readFromFile("input.txt");
		MergeSort ob = new MergeSort();
		ob.sort(list, 0, list.size() - 1);

		System.out.println("\nMiddle element: " + list.get((int) Math.ceil(list.size() / 2)));

		System.out.println("\nSorted array");
		printList(list);


	}
}
/* This code is contributed by Rajat Mishra */
