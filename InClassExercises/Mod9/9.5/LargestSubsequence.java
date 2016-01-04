
public class LargestSubsequence {
  
  private static class Sequence {
    double value;
    public Sequence(int b, int e, double[] array)
    {
      for( int x = b; x < e; x++ )
        value += array[x];
    }
  }
  
  private static Sequence getBestSequence(Sequence s1, Sequence s2)
  {
    if( s1.value > s2.value )
      return s1;
    else
      return s2;
  }
  
  static double naiveAlg (double[] X)
  {
    Sequence seq = new Sequence(0, 0, X);
    for( int b = 0; b < X.length-1; b++ )
      for( int e = 0; e < X.length; e++ )
        seq = getBestSequence(seq, new Sequence(b, e, X));
    
    return seq.value;
  }


  static double fastAlg (double[] X)
  {
    // INSERT YOUR CODE HERE.
    return 0;
  }


  public static void main (String[] argv)
  {
    // Correctness tests.
    double[] A = {-1, 8, -2, 5, -3, -1, 2};
    testCorrectness ("naiveAlg", A, 11);
    testCorrectness ("fastAlg", A, 11);

    double[] A2 = {-3, 1.5, -1, 3, -2, -3, 3};
    testCorrectness ("naiveAlg", A2, 3.5);
    testCorrectness ("fastAlg", A2, 3.5);

    // Speed tests.
    testSpeed ("naiveAlg", 1000);
    testSpeed ("fastAlg", 1000);
    
  }



  // Run a correctness test.

  static void testCorrectness (String whichAlg, double[] A, double correctSum) 
  {
    double sum;
    if (whichAlg.equalsIgnoreCase ("naiveAlg")) {
      sum = naiveAlg (A);
    }
    else {
      sum = fastAlg (A);
    }
    if (sum != correctSum) {
      System.out.println ("ERROR: " + whichAlg + " doesn't work");
    }
    else {
      System.out.println ("Test 1 passed by " + whichAlg + ". Sum = " + sum);
    }
  }
  


  // Generate a random array of length problemSize and time the execution.

  static void testSpeed (String whichAlg, int problemSize)
  {
    double[] A = new double [problemSize];
    for (int i=0; i<A.length; i++) {
      A[i] = UniformRandom.uniform (-1.0, 1.0);
    }
    // Make at least one number positive.
    double a = UniformRandom.uniform ();
    int k = (int) UniformRandom.uniform ((int) 0, (int)(problemSize-1));
    A[k] = a;

    // Time the execution.
    long startTime = System.currentTimeMillis ();
    double sum;
    if (whichAlg.equalsIgnoreCase ("naiveAlg")) {
      sum = naiveAlg (A);
    }
    else {
      sum = fastAlg (A);
    }
    long timeTaken = System.currentTimeMillis() - startTime;

    System.out.println ("Time taken by " + whichAlg + ": " + timeTaken + " ms");
  }


}
