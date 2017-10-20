package algorithm;

public class BinarySearch {
	public static void main(String[] args) {
		int array[] = new int[] { 1, 3, 45, 78, 99, 193 };
		int searchData = 99;
		int index = binarySearch(array, searchData);
		System.out.println(" array  binarySearch " + searchData + "  index is :" + index);
		int binarySearch = binarySearch(array, searchData, 0, array.length - 1);
		System.out.println(" array  binarySearch " + searchData + "  index is :" + binarySearch);
	}

	/**
	 * * 二分查找算法 * *
	 * 
	 * @param srcArray
	 *            有序数组
	 * @param des
	 *            查找元素
	 * @return des的数组下标，没找到返回-1
	 */
	public static int binarySearch(int[] srcArray, int des) {
		int low = 0;
		int high = srcArray.length - 1;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (des == srcArray[middle]) {
				return middle;
			} else if (des < srcArray[middle]) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return -1;
	}

	public static int binarySearch(int[] dataset, int data, int beginIndex, int endIndex) {
		if (data < dataset[beginIndex] || data > dataset[endIndex] || beginIndex > endIndex) {
			return -1;
		}
		int midIndex = (beginIndex + endIndex) / 2;
		if (data < dataset[midIndex]) {
			return binarySearch(dataset, data, beginIndex, midIndex - 1);
		} else if (data > dataset[midIndex]) {
			return binarySearch(dataset, data, midIndex + 1, endIndex);
		} else {
			return midIndex;
		}
	}
}
