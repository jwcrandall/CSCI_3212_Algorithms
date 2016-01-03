/*
Non Recursive Merge Sort
Programed by Joseph Crandall
Last modifed September 14, 2015
*/
import edu.gwu.algtest.*;
import edu.gwu.debug.*;
import edu.gwu.util.*;
import java.lang.Math.*;


public class MergeSortOpt implements SortingAlgorithm{



// Print Array
    public static void printArray(int[] array){
        for(int i : array) {
            System.out.printf("%d ", i);
        }
        System.out.printf("n");
    }

/*
The java.lang.Math.min(int a, int b) returns the smaller of two int values.
That is, the result is the value closer to negative infinity.
If the arguments have the same value, the result is that same value.
If either value is NaN, then the result is NaN. Unlike the numerical comparison operators,
	this method considers negative zero to be strictly smaller than positive zero.
If one argument is positive zero and the other is negative zero, the result is negative zero.
*/

	// Interface sorting algorithm methods
	public void sortInPlace(int[] data){
		if(data.length < 2)
		{
			// We consider the array already sorted, no change is done
			return;
		}

		if(data.length < 1000){
			//this.sortInPlaceInsertion(data);
			this.sortInPlaceSelection(data);
		}

		//n is the array length
		int n = data.length;
		for(int size = 1; size < n; size = size + size){

			for(int low = 0; low < n-1; low += size+size){

				simpleMerge(data, low, low+size-1, Math.min(low+size+size-1,n-1));
			}
		}
	}

	public void sortInPlaceInsertion(int[] data){

		int temp;
        for (int i = 1; i < data.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(data[j] < data[j-1]){
                    temp = data[j];
                    data[j] = data[j-1];
                    data[j-1] = temp;
                }
            }
        }

	}

	public void sortInPlaceSelection(int[] data){

		for (int i = 0; i < data.length - 1; i++){
                  int index = i;
                  for (int j = i + 1; j < data.length; j++)
                      if (data[j] < data[index])
                          index = j;

                  int smallerNumber = data[index];
                  data[index] = data[i];
                  data[i] = smallerNumber;
              }
	}



	// Merge to already sorted blocks
	void simpleMerge (int[] data, int left, int middle, int right)
  	{
	    // 1. Create the space and initialize the cursors:
	    int[] mergeSpace = new int [right-left+1];
	    int leftCursor = left;
	    int rightCursor = middle+1;

	    // 2. Fill the merge space by one by one, selecting from the correct partition
	    for (int i=0; i < mergeSpace.length; i++) {

	      if (leftCursor > middle) {
	        // 2.1  If left side is done, merge only from right:
	        mergeSpace[i] = data[rightCursor];
	        rightCursor++;
	      }
	      else if (rightCursor > right) {
	        // 2.2  If right side is done, merge only from left:
	        mergeSpace[i] = data[leftCursor];
	        leftCursor++;
	      }
	      //else if (data[leftCursor].compareTo (data[rightCursor]) <= 0) {
	      else if (data[leftCursor] <= data[rightCursor]) {
	        // 2.3 Otherwise, if the leftCursor element is less, move it:
	        mergeSpace[i] = data[leftCursor];
	        leftCursor++;
	      }
	      else {
	        // 2.4 Move from right:
	        mergeSpace[i] = data[rightCursor];
	        rightCursor++;
	      }
	    }

	    // 3. Copy back into original array:
	    for (int i=0; i < mergeSpace.length; i++)
	      data[left+i] = mergeSpace[i];
	}



	public void sortInPlace(java.lang.Comparable[] data){
		int n = data.length;
		for(int size = 1; size < n; size = size + size){

			for(int low = 0; low < n-1; low += size+size){

				simpleMerge(data, low, low+size-1, Math.min(low+size+size-1,n-1));
			}
		}
	}



	void simpleMerge (Comparable[] data, int left, int middle, int right)
  	{
	    // 1. Create the space and initialize the cursors:
	    Comparable[] mergeSpace = new Comparable [right-left+1];
	    int leftCursor = left;
	    int rightCursor = middle+1;

	    // 2. Fill the merge space by one by one, selecting from the correct partition
	    for (int i=0; i < mergeSpace.length; i++) {

	      if (leftCursor > middle) {
	        // 2.1  If left side is done, merge only from right:
	        mergeSpace[i] = data[rightCursor];
	        rightCursor++;
	      }
	      else if (rightCursor > right) {
	        // 2.2  If right side is done, merge only from left:
	        mergeSpace[i] = data[leftCursor];
	        leftCursor++;
	      }
	      else if (data[leftCursor].compareTo (data[rightCursor]) <= 0) {
	      //else if (data[leftCursor] <= data[rightCursor]) {
	        // 2.3 Otherwise, if the leftCursor element is less, move it:
	        mergeSpace[i] = data[leftCursor];
	        leftCursor++;
	      }
	      else {
	        // 2.4 Move from right:
	        mergeSpace[i] = data[rightCursor];
	        rightCursor++;
	      }
	    }

	    // 3. Copy back into original array:
	    for (int i=0; i < mergeSpace.length; i++)
	      data[left+i] = mergeSpace[i];
	}



	public int[] createSortIndex(int[] data){
		return data;
	}

	public int[] createSortIndex(java.lang.Comparable[] data){
		int[] test = new int[] {1,2,3};
            return test;
	}

	// Super interface algorithm methods
	public java.lang.String getName(){
		return "Joseph Crandall's Implementation of Iterative Optimized Merge Sort";
	}

	public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop){

	}

	public static int [] uniqueRandomElements (int size){
		int [] a = new int[size];
		for (int i = 0; i < size; i++) {
    		a[i] = (int)(Math.random()*10);

    		for (int j = 0; j < i; j++) {
        		if (a[i] == a[j]) {
            		a[j] = (int)(Math.random()*10); //What's this! Another random number!
        		}
    		}
		}
		return a;
	}

	public static void main(String[] args) {
		// Beacuse of the chosen Sentinel the array
		// should contain values smaller than Integer.MAX_VALUE .
		System.out.println("You must wait 20 seconds for the largest test case");


		int[] array = new int[] { 5, 2, 1, 12, 2, 10, 4, 13, 5,999};

		//100 random int array
		int[] array2 = uniqueRandomElements(100);
		//100000 random int array
		int[] array3 = uniqueRandomElements(100000);
		//sortInPlace = new sortInPlace();

		//10 int test
		MergeSort test = new MergeSort();
		test.sortInPlace(array);
		printArray(array);

		System.out.println("");

		//100 int test
		MergeSort test2 = new MergeSort();
		test2.sortInPlace(array2);
		printArray(array2);

		//100000 int test
		MergeSort test3 = new MergeSort();
		test3.sortInPlace(array3);
		printArray(array3);

	}
}