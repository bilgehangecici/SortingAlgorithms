import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
/* Name of the class has to be "Main" only if the class is public. */

class MedianOfMedians {

	static ArrayList<Integer> arrayList = new ArrayList<Integer>();
	static int counter = 0;

	private static int getKthSmallestQuickSelectWorstCaseLinearTime(ArrayList<Integer> arrayList, int low, int high,
			int k) {

		if (k > 0 && k <= high - low + 1) {
			// number of elements in array
			int n = high - low + 1;

			int i;
			ArrayList<Integer> median = new ArrayList<Integer>();

			for (i = 0; i < (n + 4) / 5 - 1; i++) {
				median.add(getMedian(arrayList.subList(5 * i + low, 5 * i + low + 4), 5));
				counter++;
			}

			if (n % 5 == 0) {
				median.add(getMedian(arrayList.subList(5 * i + low, 5 * i + low + 4), 5));
				i++;
				counter++;
			} else {
				median.add(getMedian(arrayList.subList(5 * i + low, 5 * i + low + (n % 5)), n % 5));
				i++;
				counter++;
			}

			int medOfMed = i == 1 ? median.get(i - 1)
					: getKthSmallestQuickSelectWorstCaseLinearTime(median, 0, i - 1, i / 2);

			int partition = partitionPractise(arrayList, low, high, medOfMed);

			if (partition - low == k - 1) {
				return arrayList.get(partition);
				counter++;
			}

			if (partition - low > k - 1) {
				return getKthSmallestQuickSelectWorstCaseLinearTime(arrayList, low, partition - 1, k);
				counter++;
			}

			return getKthSmallestQuickSelectWorstCaseLinearTime(arrayList, partition + 1, high,
					k - (partition + 1) + low);
		}
		counter++;
		return -1;
	}

	private static int getMedian(List<Integer> List, int n) {
		counter++;
		ArrayList<Integer> arrayList = new ArrayList<Integer>(List);
		Collections.sort(arrayList);
		return arrayList.get(n / 2);
	}

	private static void swap(ArrayList<Integer> arrayList, int i, int index) {
		if (arrayList.get(i) == arrayList.get(index)) {
			return;
		}
		int temp = arrayList.get(i);
		arrayList.set(i, arrayList.get(index));
		arrayList.set(index, temp);
		counter++;
	}

	private static int partitionPractise(ArrayList<Integer> arrayList, int low, int high, int pivot) {

		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i) == pivot) {
				swap(arrayList, i, high);
				break;
			}
			counter++;
		}
		int index = low - 1;
		int i = low;
		while (i < high) {
			if (arrayList.get(i) < pivot) {
				index++;
				swap(arrayList, i, index);
			}
			i++;
			counter++;
		}
		index++;
		counter++;
		swap(arrayList, index, high);
		return index;
	}

	private static void printList(ArrayList<Integer> arrayList) {
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

	public static void main(String[] args) throws java.lang.Exception {

		readFromFile("input.txt");
		long startTime = System.nanoTime();
		System.out.println("kth smallest in the given array is "
				+ getKthSmallestQuickSelectWorstCaseLinearTime(arrayList, 0, arrayList.size() - 1, 3));
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		printList(arrayList);
		System.out.println("Total time in nanoseconds: " + totalTime);
		System.out.println("Counter: " + counter);

	}

}
