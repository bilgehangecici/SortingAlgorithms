
// Java program of Quick Select 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

class QuickSelect {

	// partition function similar to quick sort
	// Considers first element as pivot and adds
	// elements with less value to the left and
	// high value to the right and also changes
	// the pivot position to its respective position
	// in the final array.
	static ArrayList<Integer> arrayList = new ArrayList<Integer>();

	public static int partition(ArrayList<Integer> list, int low, int high) {
		int i = low + 1;
		int piv = list.get(low); // make the first element as pivot element.
		for (int j = low + 1; j <= high; j++) {
			/*
			 * rearrange the array by putting elements which are less than pivot on one side
			 * and which are greater that on other.
			 */

			if (list.get(j) < piv) {

				Collections.swap(list, i, j);
				i += 1;
			}
		}
		Collections.swap(list, low, i - 1); // put the pivot element in its proper place.

		return i - 1; // return the position of the pivot
	}

	// finds the kth position (of the sorted array)
	// in a given unsorted array i.e this function
	// can be used to find both kth largest and
	// kth smallest element in the array.
	// ASSUMPTION: all elements in arr[] are distinct
	public static int kthSmallest(ArrayList<Integer> list, int low, int high, int k) {
		// find the partition
		int partition = partition(list, low, high);

		// if partition value is equal to the kth position,
		// return value at k.
		if (partition == k)
			return list.get(partition);

		// if partition value is less than kth position,
		// search right side of the array.
		else if (partition < k)
			return kthSmallest(list, partition + 1, high, k);

		// if partition value is more than kth position,
		// search left side of the array.
		else
			return kthSmallest(list, low, partition - 1, k);
	}

	static void printList(ArrayList<Integer> list) {
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
				arrayList.add(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	// Driver Code
	public static void main(String[] args) {

		readFromFile("input.txt");

		int kPosition = 3;
		int length = arrayList.size();

		if (kPosition > length) {
			System.out.println("Index out of bound");
		} else {
			// find kth smallest value
			System.out.println(
					"K-th smallest element in array : " + kthSmallest(arrayList, 0, length - 1, kPosition - 1));

			System.out.println("After the partition: " + arrayList);
		}
	}
}
