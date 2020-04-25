import java.util.ArrayList;

class InsertionSort {
	/* Function to sort array using insertion sort */
	private void sort(ArrayList<Integer> arrayList) {
		int n = arrayList.size();
		for (int i = 1; i < n; ++i) {
			int key = arrayList.get(i);
			int j = i - 1;

			/*
			 * Move elements of arr[0..i-1], that are greater than key, to one position
			 * ahead of their current position
			 */
			while (j >= 0 && arrayList.get(j) > key) {

				arrayList.set(j + 1, arrayList.get(j));

				j = j - 1;
			}
			arrayList.set(j + 1, key);
		}
	}

	/* A utility function to print array of size n */
	private static void printArray(ArrayList<Integer> arrayList) {
		int n = arrayList.size();
		for (int i = 0; i < n; ++i)
			System.out.print(arrayList.get(i) + " ");

		System.out.println();
	}

	// Driver method
	public static void main(String args[]) {

		ArrayList<Integer> arrayList = new ArrayList<Integer>();

		arrayList.add(12);
		arrayList.add(11);
		arrayList.add(13);
		arrayList.add(5);
		arrayList.add(6);
		arrayList.add(9);
		arrayList.add(15);
		arrayList.add(23);
		System.out.println("Given Array");
		printArray(arrayList);
		InsertionSort ob = new InsertionSort();
		ob.sort(arrayList);
		int number = arrayList.get((int) Math.ceil(arrayList.size() / 2));
		System.out.println("\nSorted array");
		printArray(arrayList);
		System.out.println("\nMiddle element: " + number);
	}
} /* This code is contributed by Rajat Mishra. */
