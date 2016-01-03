import edu.gwu.algtest.*;
import edu.gwu.debug.*;
import edu.gwu.util.*;

public class InsertionSort implements SortingAlgorithm{

	// these four methods are part of the SortingAlgorithm interface

	// sortInPlace should sort the given integer data in the same array
	public void sortInPlace(int[] data){

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

	// sortInPlace sorts object arrays that meet the Comparable interface so that comparisons may be done by called the compareTo method of the objects.
	public void sortInPlace(java.lang.Comparable[] data){
	//empty implementations, method definition empty body
	}

	//Instead of modifying the order of data in the original array, createSortIndex should create and return an array of indices into the original array in sort order. Thus, if data[0]=10, data[1]=15, data[2]=5 the returned array should contain 2, 0, 1.
	public int[] createSortIndex(int[] data){
		return data;
	}

	//createSortIndex should return a sort index for Comparable
	public int[] createSortIndex(java.lang.Comparable[] data){
		int[] test = new int[] {1,2,3};
		return test;
	}


	//These two methods are part of the Algorithm interface which is a parent of the SortingAlgorithm inerface
 	public java.lang.String getName(){
 		return "Joseph Crandall's implementation of InsertionSort	Enjoy";
 	}

	public void setPropertyExtractor(int algID,edu.gwu.util.PropertyExtractor prop){
	//empty implementations, method definition empty body
	}

}
