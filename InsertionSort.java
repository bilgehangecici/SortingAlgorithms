import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

class InsertionSort {

  static ArrayList<Integer> arrayList = new ArrayList<Integer>();
  static int counter = 0;

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
        counter++;
			}
			arrayList.set(j + 1, key);
      counter++;
		}
	}

	/* A utility function to print array of size n */
	private static void printArray(ArrayList<Integer> arrayList) {
		int n = arrayList.size();
		for (int i = 0; i < n; ++i)
			System.out.print(arrayList.get(i) + " ");

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

	// Driver method
	public static void main(String args[]) {

    readFromFile("input.txt");

		System.out.println("Given Array");
		printArray(arrayList);
		InsertionSort ob = new InsertionSort();
		long startTime = System.nanoTime();
		ob.sort(arrayList);
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		int number = arrayList.get((int) Math.ceil(arrayList.size() / 2));
		System.out.println("\nSorted array");
		printArray(arrayList);
		System.out.println("\nMiddle element: " + number);
		System.out.println("Total time in nanoseconds: " + totalTime);
	}
} /* This code is contributed by Rajat Mishra. */
