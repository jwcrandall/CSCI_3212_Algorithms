import java.util.*;

public class ex3 {

  public static int NUMITEMS = 5;
  public static int NUMPROCESSORS = 3;

  private static final boolean debug = true;
  
  static int[] myAlgorithm (double[] taskTimes, int numProcessors)
  {
    // INSERT YOUR CODE HERE.
    // What to return:
    //   Create an int array called partition such that
    //   partition[i] = the index of the last task assigned to processor i.
    // Thus, if 5 tasks break up into 3 processors as:
    //   Processor 0:   0, 1
    //   Processor 1:   2, 3
    //   Processor 2:   4
    // Then, 
    //   partition[0] = 1   (implying the range 0 to 1)
    //   partition[1] = 3   (implying the range 2 to 3)
    //   partition[2] = 4   (implying the range 4 to 4)
    
    int[] partition = new int[ numProcessors ];
    
    partition[0] = Math.max( 1, taskTimes.length / numProcessors );
    
    for( int x = 1; x < numProcessors; x++ )
    {
      partition[x] = Math.max( 1, taskTimes.length / numProcessors );
      partition[x] += partition[x-1];
    }
    
    partition[ partition.length - 1 ] = taskTimes.length-1;
    
    return partition;
  }
  

  public static void main (String[] argv)
  {
    test();
  }
  
  public static void test ()
  {
    // Test1:
    int numProcessors = 3;
    double[] taskTimes = {50, 23, 62, 72, 41};
    System.out.println ("Test1: (optimal time: 113)");
    runTest (taskTimes, numProcessors);

    // Test2: 
    numProcessors = 2;
    double[] taskTimes2 = {50, 23, 62, 72, 41};
    System.out.println ("Test2: (optimal time: 135)");
    runTest (taskTimes2, numProcessors);

    // Test3: 
    numProcessors = 6;
    double[] taskTimes3 = {50, 23, 62, 72, 41, 17, 68, 12, 19, 45};
    System.out.println ("Test3: (optimal time: 76)");
    runTest (taskTimes3, numProcessors);
  }


  static void runTest (double[] taskTimes, int numProcessors)
  {
    int[] partition = myAlgorithm (taskTimes, numProcessors);
    double cost = cost (taskTimes, partition, numProcessors, true);
    System.out.println ("MYALGORITHM: numTasks=" + taskTimes.length + " numProcessors=" + numProcessors + "  Final completion time: " + cost);
  }
  

  static double cost (double[] taskTimes, int[] partition, int numProcessors, boolean printIt)
  {
    // Check whether too many processors were assigned.
    if (partition.length > numProcessors) {
      System.out.println ("ERROR: partition length=" + partition.length + " larger than numProcessors=" + numProcessors);
      System.exit(1);
    }

    // Print partition.
    if (printIt) {
      System.out.print ("Partition: ");
      for (int i=0; i<partition.length; i++) 
        System.out.print (" " + partition[i]);
      System.out.println ("");
    }


    // Process partitions.
    double max = 0;
    int currentLeft = 0;
    int start=0, end=0;

    for (int i=0; i<partition.length; i++) {

      // Identify i-th partition.
      start = currentLeft;
      end = partition[i];
      // Convention: partition[i] includes the rightmost element in partition.

      if (end < start) {
        System.out.println ("ERROR: wrong partition i=" + i + " start=" + start + " end=" + end + " partition[i]=" + partition[i]);
        System.exit(1);
      }

      // Find the completion time of the i-th partition.
      double sum = 0;
      for (int j=start; j<=end; j++)
        sum += taskTimes[j];
      if (sum > max) {
        max = sum;
      }

      if (printIt) {
        System.out.print ("  Partition i=" + i + "(sum=" + sum + ",max=" + max + ",end=" + partition[i] + "): ");
        for (int j=start; j<=end; j++)
          System.out.print (" " + taskTimes[j]);
        System.out.println ("");
      }

      // Advance to next partition.
      currentLeft = end + 1;
    }


    // The last partition should end with the last task.
    if (end != taskTimes.length-1) {
      System.out.println ("ERROR: end=" + end + " numTasks=" + taskTimes.length);
      System.exit(1);
    }
    
    // Return total completion time.
    return max;
  }

}
