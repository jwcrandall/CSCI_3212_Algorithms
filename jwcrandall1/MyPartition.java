import edu.gwu.algtest.*;
import edu.gwu.debug.*;
import edu.gwu.util.*;

public class MyPartition implements PartitionAlgorithm{


	//The following two methods are part of the PartitionAlgorithm Interface

	/*
	leftIncreasingPartition should partition the given array only in the range data[left] ... data[right] (both values inclusive).
	This method should use data[left] as the partitioning element and should return the index where it gets located after partitioning.
	Suppose, for example, data[left]==10. Then, after partitioning, suppose that this value ends up in position 4, so data[4]==10.
		Then, data[i]<=10 when i is between "left" and 3, and data[i]>10 for all i between 5 and "right".
	*/

	//This method should use data[left] as the partitioning element and should return the index where it gets located after partitioning.

	public int leftIncreasingPartition(int[] data, int left, int right){
	    int pivot = 0;
	    for(int i = 1; i <= right;i++){

		    if(data[pivot] >= data[pivot+1]){
		    	int temp = data[pivot+1];
		        data[pivot+1] = data[pivot];
		        data [pivot] = temp;
		        pivot++;
		    }
		    else{
		    	int temp = data[pivot+1];
		        for(int j = pivot + 1; j < right; j++ ){
		        	data[j] = data[j+1];
		        }
		      	data[right] = temp;
		    }
		}
		return pivot;
	}


	//rightIncreasingPartition is similar to leftIncreasingPartition except that data[right] is used.
	public int rightIncreasingPartition(int[] data, int left, int right){
		return 0;
	}


      //These two methods are part of the Algorithm interface which is a parent of the SortingAlgorithm inerface

 	public java.lang.String getName(){
 		return "Joseph Crandall's implementation of MyPartition		Enjoy!!!";
 	}

	public void setPropertyExtractor(int algID,edu.gwu.util.PropertyExtractor prop){
		//empty implementation, method definition with empty body
	}

}

