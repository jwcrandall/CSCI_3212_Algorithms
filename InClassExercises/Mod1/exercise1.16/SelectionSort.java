
public class SelectionSort {

  static int rangeLow = 100;
  static int rangeHigh = 1000;

  static int[] makeRandomData (int numValues)
  {
    int[] data = new int [numValues];
    for (int i=0; i<data.length; i++)
      data[i] = (int) UniformRandom.uniform ( (int) rangeLow, (int) rangeHigh );
    return data;
  }

  static void printData (int[] data)
  {
    System.out.print ("Data: ");
    for (int i=0; i<data.length; i++)
      System.out.print (" " + data[i]);
    System.out.println ("");
  }
  

  static void sort (int[] data)
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

	private static void swap(int[] array, int i, int j)
	{
		if( i == j )
			return;

		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}


  public static void main (String[] argv) 
  {
    if ( (argv == null) || (argv.length == 0) ) {
      System.out.println ("Usage: java SelectionSort <number-of-values>");
      System.exit(0);
    }

    try {
      int numValues = Integer.parseInt (argv[0]);
      int[] data = makeRandomData (numValues);
      System.out.println ("BEFORE SORTING: ");  
      printData (data);
      sort (data);
      System.out.println ("AFTER SORTING: ");
      printData (data);
    }
    catch (Exception e) {
      System.out.println (e);
    }
  }

}
