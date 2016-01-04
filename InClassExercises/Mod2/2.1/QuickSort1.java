
public class QuickSort1 {

  public void sortInPlace (int[] data)
  {
    quickSortRecursive (data, 0, data.length-1);
  }

  void swap (int[] data, int i, int j)
  {
    int temp = data[i];
    data[i] = data[j];
    data[j] = temp;
  }


  public int leftPartition(int[] data, int left, int right){
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



  void quickSortRecursive (int[] data, int left, int right)
  {
    // For sub-ranges larger than 1, split and recurse:
    if (left < right) {
      // 1. Partition:
      int partitionPosition = leftPartition (data, left, right);

      // 2. Sort left side:
      quickSortRecursive (data, left, partitionPosition-1);

      // 3. Sort right side:
      quickSortRecursive (data, partitionPosition+1, right);

      // Partition element is already in the correct place.
    }
  }

  public static void test1 ()
  {
    QuickSort1 qSort = new QuickSort1 ();
    int totalTests = 1;
    int dataSize = 10;
    int[] data = new int [dataSize];
    for (int ntests=0; ntests < totalTests; ntests++) {
      // Create the data:
      for (int i=0; i < dataSize; i++)
        data[i] = (int) UniformRandom.uniform ( (int) 100, (int) 1000);
      // Sort:
      qSort.sortInPlace (data);
      // Check:
      boolean ok = true;
      for (int i=0; i < dataSize-1; i++)
        if (data[i] > data[i+1])
          ok = false;
      if (! ok) {
        System.out.println ("Failure detected for test#" + ntests);
        System.exit (0);
      }
    }
    System.out.println ("Passed");
  }


  public static void test2 ()
  {
    QuickSort1 qSort = new QuickSort1 ();
    int dataSize = 100;
    int[] data = new int [dataSize];
    for (int i=0; i < dataSize; i++)
      data[i] = i;
    qSort.sortInPlace (data);

    // Check:
    boolean ok = true;
    for (int i=0; i < dataSize-1; i++)
      if (data[i] > data[i+1])
        ok = false;
    if (! ok) {
      System.out.println ("Failure detected in test2");
      System.exit (0);
    }
    System.out.println ("Passed");
  }

  public static void main (String[] argv)
  {
    test1();
  }

}