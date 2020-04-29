
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QucikSelectMedianOfThree {

	// partition function similar to quick sort
	// Considers first element as pivot and adds
	// elements with less value to the left and
	// high value to the right and also changes
	// the pivot position to its respective position
	// in the final array.
	static ArrayList<Integer> arrayList = new ArrayList<Integer>();
	static int counter = 0;

	// finds the kth position (of the sorted array)
	// in a given unsorted array i.e this function
	// can be used to find both kth largest and
	// kth smallest element in the array.
	// ASSUMPTION: all elements in arr[] are distinct
	public static int kthSmallest(ArrayList<Integer> list, int pivot, int high, int k) {
		// find the partition
		counter++;
		long median = medianOf3(0, arrayList.size() - 1);
		int partition = partition(0, arrayList.size() - 1, median);

		// int partition = partition(list, pivot, high);

		// if partition value is equal to the kth position,
		// return value at k.
		if (partition == k) {
			return list.get(partition);
		}

		// if partition value is less than kth position,
		// search right side of the array.
		else if (partition < k)
			return kthSmallest(list, partition + 1, high, k);

		// if partition value is more than kth position,
		// search left side of the array.
		else
			return kthSmallest(list, pivot, partition - 1, k);

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

	public static int partition(int left, int right, long pivot) {
		int leftPtr = left; // right of first elem
		int rightPtr = right - 1; // left of pivot

		while (true) {
			// find bigger
			while (arrayList.get(++leftPtr) < pivot)
				;
			// find smaller
			while (arrayList.get(--rightPtr) > pivot)

				;
			if (leftPtr >= rightPtr) // if pointers cross, partition done
				break;
			else
				// not crossed, so
				swap(leftPtr, rightPtr); // swap elements
		}
		swap(leftPtr, right - 1); // restore pivot
		return leftPtr; // return pivot location
	}

	public static long medianOf3(int left, int right) {
		int center = (left + right) / 2;
		// order left & center
		if (arrayList.get(left) > arrayList.get(center))
			swap(left, center);
		// order left & right
		if (arrayList.get(left) > arrayList.get(right))
			swap(left, right);
		// order center & right
		if (arrayList.get(center) > arrayList.get(right))
			swap(center, right);

		swap(center, right - 1); // put pivot on right
		return arrayList.get(right - 1); // return median value
	}

	public static void swap(int dex1, int dex2) {
		long temp = arrayList.get(dex1);
		arrayList.set(dex1, arrayList.get(dex2));
		arrayList.set(dex2, (int) temp);
	}

	// Driver Code
	public static void main(String[] args) {

		readFromFile("Cases/bestCase1.txt");

		int length = arrayList.size();

		int kPosition = (int) Math.ceil(arrayList.size() / 2.0);

		System.out.println("kPosition: " + kPosition);
		long startTime = System.nanoTime();
		if (kPosition > length) {
			System.out.println("Index out of bound");
		} else {
			// find kth smallest value
			System.out.println(
					"K-th smallest element in array : " + kthSmallest(arrayList, 0, length - 1, kPosition - 1));

			System.out.println("After the partition: " + arrayList);
		}
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Total time in nanoseconds: " + totalTime);
		System.out.println("Counter: " + counter);

	}
}
