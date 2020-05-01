import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class MaxHeap {
	static MaxHeap maxHeap = new MaxHeap(1000000);
	private int[] Heap;
	private int size;
	private int maxsize;
	static int counter = 0;

	// Constructor to initialize an
	// empty max heap with given maximum
	// capacity.
	public MaxHeap(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		Heap = new int[this.maxsize + 1];
		Heap[0] = Integer.MAX_VALUE;
	}

	// Returns position of parent
	private int parent(int pos) {
		return pos / 2;
	}

	// Below two functions return left and
	// right children.
	private int leftChild(int pos) {
		
		return (2 * pos);
	}

	private int rightChild(int pos) {
		return (2 * pos) + 1;
	}

	// Returns true of given node is leaf
	private boolean isLeaf(int pos) {
		if (pos >= (size / 2) && pos <= size) {
			counter++;
			return true;
		}
		return false;
	}

	private void swap(int fpos, int spos) {
		int tmp;
		tmp = Heap[fpos];
		Heap[fpos] = Heap[spos];
		Heap[spos] = tmp;
		counter++;
	}

	// A recursive function to max heapify the given
	// subtree. This function assumes that the left and
	// right subtrees are already heapified, we only need
	// to fix the root.
	private void maxHeapify(int pos) {
		if (isLeaf(pos))
			return;

		if (Heap[pos] < Heap[leftChild(pos)] || Heap[pos] < Heap[rightChild(pos)]) {

			if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) {
				swap(pos, leftChild(pos));
				maxHeapify(leftChild(pos));
				counter++;
			} else {
				swap(pos, rightChild(pos));
				maxHeapify(rightChild(pos));
				counter++;
			}
			counter++;
		}
	}

	// Inserts a new element to max heap
	public void insert(int element) {
		Heap[++size] = element;

		// Traverse up and fix violated property
		int current = size;
		while (Heap[current] > Heap[parent(current)]) {
			swap(current, parent(current));
			current = parent(current);
			counter++;
		}
	}


	// Remove an element from max heap
	public int extractMax() {
		int popped = Heap[1];
		Heap[1] = Heap[size--];
		maxHeapify(1);
		return popped;
	}

	static void readFromFile(String fileName) {
		try {
			File myObj = new File(fileName);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNext()) {
				int data = myReader.nextInt();
				maxHeap.insert(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void main(String[] arg) {

		readFromFile("Cases/worstCase6.txt");

		int number = (int) Math.floor(maxHeap.size / 2.0);
		long startTime = System.nanoTime();
		for (int i = 0; i < number; i++) {

			// System.out.println("The max val is " + maxHeap.extractMax());
			maxHeap.extractMax();
			counter++;
		}

		// maxHeap.print();
		System.out.println("The max val is " + maxHeap.extractMax());
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Total time in nanoseconds: " + totalTime);
		System.out.println("Counter: " + counter);
	}
}
