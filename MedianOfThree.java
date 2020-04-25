
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class MedianOfThree {

  static ArrayList<Integer> arrayList = new ArrayList<Integer>();

	public static int n = 16;// Controls size of array
	static int numSwaps = 0;
	static int numComps = 0;

	public static void main(String[] args) {

    readFromFile("input.txt");
    		// int arr[] = {-3, 9, 6, 11, 4};
		int arr[] = new int[n];
		int n = arr.length - 1;
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(100);
		}

		System.out.println("\n\n unsorted array:");
		printList(arrayList);
		System.out.println();
		medianOfThree(arrayList, 0, arrayList.size()-1);
		System.out.println("\n\n sorted array:");
		printList(arrayList);

		System.out.println("\n\tSwaps: " + numSwaps);
		System.out.println("\tComparisons: " + numComps);
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

	public static int medianPivot(ArrayList<Integer> arrayList, int low, int high) {
		/*
		 * create subarray with low, high, and middle elements in the array sort the
		 * subarray and use index 1 as the median of 3
		 */

		int first = arrayList.get(low);
		int last = arrayList.get(arrayList.size() - 1);
		int mid = (high) / 2;

		System.out.println("\tMiddle of Arr at Index= " + mid + " : " + arrayList.get(mid));
		int[] sortingArr = { arrayList.get(low), arrayList.get(mid), arrayList.get(high) };

		Arrays.sort(sortingArr);

		int middleValue = sortingArr[1];
		System.out.println("\t"+Arrays.toString(sortingArr));
		//printList(sortingArr);

		// swap with the last to serve as pivot
		int temp = arrayList.get(high);
    arrayList.set(high, middleValue);
		if (middleValue == arrayList.get(low)) {
			arrayList.set(low, temp);
		} else if (middleValue == arrayList.get(mid)) {
			arrayList.set(mid, temp);
		}

		// System.out.println("median: ");
		printList(arrayList);
		return partition(arrayList, low, high);

	}

	// ----------------------------------------------------------------------
	/*  method for medianOfThree */
	public static void medianOfThree(ArrayList<Integer> arrayList, int low, int high) {
		if (low >= high)
			return;

		if (low < high) {

			int pi = medianPivot(arrayList, low, high);

			QuickSort(arrayList, low, high);

		}
	}

	// -----------------------------------------------------------------------
	public static void QuickSort(ArrayList<Integer> arrayList, int low, int high) {

		if (low < high) {
			int pi = partition(arrayList, low, high);

			// Recursively sort elements before
			// partition and after partition
			QuickSort(arrayList, low, pi - 1);
			QuickSort(arrayList, pi + 1, high);
		}
	}

	// ----------------------------------------------------------------------
	/* A utility function to print array of size n */
  private static void printList(ArrayList<Integer> arrayList) {
		int n = arrayList.size();
		for (int i = 0; i < n; ++i)
			System.out.print(arrayList.get(i) + " ");

		System.out.println();
	}

	// -----------------------------------------------------------------------
	public static int partition(ArrayList<Integer> arrayList, int low, int high) {
		int pivot = arrayList.get(high);
		int i = (low - 1); // index of smaller element

		for (int j = low; j < high; j++) {
			// If current element is smaller than or
			// equal to pivot
			if (arrayList.get(j) <= pivot) {
				i++;

				// swap arr[i] and arr[j]
				int temp = arrayList.get(i);
				arrayList.set(i, arrayList.get(j));
				arrayList.set(j, temp);
				numSwaps++;
			}
			numComps++;
		}

		// swap arr[i+1] and arr[high] (or pivot)
		int temp = arrayList.get(i+1);
    arrayList.set(i+1, arrayList.get(high));
    arrayList.set(high, temp);
		numSwaps++;
		return i + 1;

	}
}
