import edu.gwu.algtest.*;
import edu.gwu.debug.*;
import edu.gwu.util.*;

public class SelectionSort implements SortingAlgorithm{

      // these four methods are part of the SortingAlgorithm interface

      // sortInPlace should sort the given integer data in the same array
      public void sortInPlace(int[] data){

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

      // sortInPlace sorts object arrays that meet the Comparable interface so that comparisons may be done by called the compareTo method of the objects.
      public void sortInPlace(java.lang.Comparable[] data){
      //empty implementations, method definition empty body
      }

      //Instead of modifying the order of data in the original array, createSortIndex should create and return an array of indices into the original array in sort order. Thus, if data[0]=10, data[1]=15, data[2]=5 the returned array should contain 2, 0, 1.
      public int[] createSortIndex(int[] data){
      //empty implementations, method definition empty body
            return data;
      }

      //createSortIndex should return a sort index for Comparable
      public int[] createSortIndex(java.lang.Comparable[] data){
      //empty implementations, method definition empty body
            int[] test = new int[] {1,2,3};
            return test;
      }

      //These two methods are part of the Algorithm interface which is a parent of the SortingAlgorithm inerface

      //getName should return your name and the name of your algorithm
      public java.lang.String getName(){
            return "Joseph Crandall's implementation of SelectionSort   Enjoy";
      }

      //setPropertyExtractor will be called by the simulator with an instance of PropertyExtractor which an algorithm can use to extract properties from the original properties file.
      public void setPropertyExtractor(int algID,edu.gwu.util.PropertyExtractor prop){
      //empty implementations, method definition empty body
      }
}



