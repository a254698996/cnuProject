package jvm;

public class ThreadTestDemo2 {
	static {
		System.out.println("ThreadTestDemo2  static  execute ...");
	}

	public ThreadTestDemo2() {
		System.out.println("ThreadTestDemo2 begin ");
	}

	public static void main(String[] args) {
		// new ThreadTestDemo2();
		// new ThreadTestDemo9999();
		// new ThreadTestDemo2();
		int[] arrr = new int[] { 1, 5, 33, 41, 112 };
		int search = ThreadTestDemo9999.search(arrr, 0, arrr.length - 1, 333);
		System.out.println("search  " + search);
	}

}

class ThreadTestDemo9999 {
	public ThreadTestDemo9999() {
		System.out.println("ThreadTestDemo9999 .... ");
	}

	public static int search(int[] arr, int begin, int end, int data) {
		if (data > arr[end] || data < arr[begin] || begin > end) {
			return -1;
		}
		int middle = (begin + end) / 2;
		if (data < arr[middle]) {
			return search(arr, begin, middle - 1, data);
		} else if (data > arr[middle]) {
			return search(arr, middle + 1, end, data);
		}
		return middle;
	}
}
