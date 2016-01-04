// Simulated annealing, using the O(n) neighborhood.


// A class used to represent a point: simple x and y value.
class Pointd {
  public double x, y;
}

public class TSPAnnealingAlt {

  public static final int INIT_TEMP = 1000;     // Initial temp hardcoded.
  public static final int ITERATIONS = 1000;    // Number of iterations to use overall.

  int numIterations = ITERATIONS;

  double delT = 0.0001;                         // Additively decreasing temp schedule.


  public int[] computeTour (Pointd[] points)
  {
    // Use numPoints for convenience.
    int numPoints = points.length;

    // Space for current and best tour.
    int[] tour = new int [numPoints];
    int[] bestTour = new int [numPoints];

    // Initial tour in given sequence.
    for (int i=0; i<numPoints; i++)
      tour[i] = i;

    // No need to construct 3-point tour.
    if (numPoints < 4)
      return tour;

    // Initial temperature.
    double T = INIT_TEMP;

    // Current tour is current best tour:
    copy (tour, bestTour);
    double bestCost = computeCost (bestTour, points);

    // Start iterations.
    int iteration = 0;

    // INSERT YOUR CODE HERE:
    while (iteration < numIterations) {

      // Get next tour by calling computeNextState. Then,
      // compute its cost.

      // If cost is better, accept new tour and check whether
      // its the best one so far.

      // Otherwise, see if we are going to accept higher-cost tour.

      // Get next temperature.
      T = computeNewTemperature (T);

      // Increment number of iterations.
      iteration ++;
    }

    // Return best tour found.
    return bestTour;
  }


  double computeNewTemperature (double T)
  {
    return (T - delT);
  }


  int[] computeNextState (int[] tour, Pointd[] points)
  {
    // INSERT YOUR CODE HERE.

    // Perform a random-swap, for example.

    // Temporarily:
    return null;
  }

  double computeCost (int[] tour, Pointd[] points)
  {
    // Now, the cost.
    double tourCost = 0;
    for (int i=0; i<points.length-1; i++) {
      // Distance from i to i+1
      tourCost += distance (points[tour[i]], points[tour[i+1]]);
    }
    // Last one.
    tourCost += distance (points[tour[points.length-1]], points[tour[0]]);

    return tourCost;
  }

  //////////////////////////////////////////////////////////////////////////////
  // USEFUL METHODS

  void swap (int[] tour, int i, int j)
  {
    int temp = tour[i];
    tour[i] = tour[j];
    tour[j] = temp;
  }

  double sqr (double a)
  {
    return a*a;
  }


  double distance (Pointd p1, Pointd p2)
  {
    return Math.sqrt ( sqr(p1.x - p2.x) + sqr (p1.y - p2.y) );
  }

  void copy (int[] tour1, int[] tour2)
  {
    for (int i=0; i<tour1.length; i++)
      tour2[i] = tour1[i];
  }

  ///////////////////////////////////////////////////////////////////////////////
  // PRINTING

  void printTour (String msg, int[] tour)
  {
    System.out.print (msg);
    for (int i=0; i<tour.length; i++)
      System.out.print (" " + tour[i]);
    System.out.println ("");
  }

  void printPoints (Pointd[] points)
  {
    System.out.print ("Points: ");
    for (int i=0; i<points.length; i++)
      System.out.print ("  (" + points[i].x + "," + points[i].y + ")");
    System.out.println ("");
  }

  //////////////////////////////////////////////////////////////////////////////////////
  // TESTING

  static Pointd[] makeRandomPoints (int numPoints)
  {
    Pointd[] points = new Pointd [numPoints];
    for (int i=0; i<numPoints; i++) {
      points[i] = new Pointd ();
      points[i].x = UniformRandom.uniform (0.1, 0.9);
      points[i].y = UniformRandom.uniform (0.1, 0.9);
    }
    return points;
  }

  public static void main (String[] argv)
  {
    TSPAnnealingAlt alg = new TSPAnnealingAlt ();
    Pointd[] points = makeRandomPoints (25);
    int[] bestTour = alg.computeTour (points);
    double cost = alg.computeCost (bestTour, points);
    System.out.println ("TSPAnnealingAlt: best cost = " + cost);
    alg.printTour ("TSPAnnealingAlt", bestTour);
    // Output:
    //  cost: 4.470116608418266
    //  tour: 9 24 2 1 5 22 15 7 3 10 0 6 14 12 16 21 11 4 18 23 20 19 8 13 17
  }

}