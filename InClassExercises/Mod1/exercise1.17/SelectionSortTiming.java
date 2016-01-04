import java.util.*;

public class SelectionSortTiming {

    static int rangeLow = 100;
    static int rangeHigh = 1000;
    
    static Integer[] makeRandomData (int numValues)
    {
        Integer[] data = new Integer [numValues];
        for (int i=0; i<data.length; i++) {
            data[i] = (int) UniformRandom.uniform ( (int) rangeLow, (int) rangeHigh );
        }
        return data;
    }

    static Integer[] copy (Integer[] data)
    {
        Integer[] dataCopy = new Integer [data.length];
        for (int i=0; i<data.length; i++) {
            dataCopy[i] = data[i];
        }
        return dataCopy;
    }
	
	private static void swap(Object[] array, int i, int j)
	{
		if( i == j )
			return;

		Object tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
  

    static void selectionSort (Integer[] data)
    {
	for( int x = 0; x < data.length; x++ )
	{
		int smallestIndex = x;
		for( int y = x + 1; y < data.length; y++ )
			if( data[y] < data[smallestIndex] )
				smallestIndex = y;

		swap(data, x, smallestIndex);
	}
    }
    

    public static void main (String[] argv) 
    {
        Integer[] data = makeRandomData (50000);
        Integer[] dataCopy = copy (data);
        
        // Java's sort algorithm.
        long startTime = System.currentTimeMillis ();
        Arrays.sort (data);
        double timeTaken = System.currentTimeMillis() - startTime;
        System.out.println ("Time taken by Java's sort: " + timeTaken);

        startTime = System.currentTimeMillis ();
        selectionSort (dataCopy);
        timeTaken = System.currentTimeMillis() - startTime;
        System.out.println ("Time taken by your selection sort: " + timeTaken);

        
    }
    
}
