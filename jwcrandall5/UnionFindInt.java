import edu.gwu.algtest.*;
import edu.gwu.util.*;
import java.util.*;

public class UnionFindInt {
	int[] sets;

	// Call this repeatedly to instantiate initial sets. 
	public void makeSingleElementSet (int value){
		sets[value] = value;
	}

	// Initialize with number of items (e.g., number of vertices) 
	public void initialize (int numVertices){
		sets = new int[numVertices];
		for(int i=0;i<numVertices;i++){
			makeSingleElementSet(i);
		}
	}

	// Union operation. 
	public void union (int value1, int value2){
		int check = 0;
		if(sets[value1] < sets[value2]){
			check = sets[value2];
			for(int i=0;i<sets.length;i++){
				if(sets[i] == check){
					sets[i] = sets[value1];
				}
			}
		}
		else{
			check = sets[value1];
			for(int i=0;i<sets.length;i++){
				if(sets[i] == check){
					sets[i] = sets[value2];
				}
			}
		}
	}

	// Find operation: returns the ID of the set that currently contains "value". 
	public int find (int value){
		return sets[value];
	}
}