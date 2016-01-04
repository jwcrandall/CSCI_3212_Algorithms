// File: TSPGreedyLocalSearchAlt.java
//
// Alternate neighborhood for TSP.



// A class used to represent a point: simple x and y value.
class Pointd {
  public double x, y;
}

Random r = new Random();


public class TSPGreedyLocalSearchAlt {

  // The method that is responsible for the overall tour computation.

  public int[] computeTour (Pointd[] points)
  {
    int numPoints = points.length;

    // Create space for current and next tour.
    int[] tour = new int [numPoints];

    // Initial tour in given sequence.
    for (int i=0; i<numPoints; i++)
      tour[i] = i;

    // Don't need to compute if tour length < 4.
    if (numPoints < 4)
      return tour;

    // Start with current tour and next one.
    double currentCost = computeCost (tour, points);
    int[] nextTour = computeNextState (tour, points);
    double nextCost = computeCost (nextTour, points);

    // As long as next one is less...
    while (nextCost < currentCost) {
      // Update cost.
      currentCost = nextCost;
      // Make next-tour the current tour.
      copy (nextTour, tour);
      // Create new next-tour.
      nextTour = computeNextState (tour, points);
      nextCost = computeCost (nextTour, points);
    }

    // Return best one found.
    return tour;
  }


  int[] computeNextState (int[] tour, Pointd[] points)
  {
    // INSERT YOUR CODE HERE:
    // Create a new tour and return it. To create the new
    // tour, pick a random point i in the current tour. Then,
    // try all possible j values (where j != i). For each
    // such (i,j) pair, do a swap and see which swap resulted
    // in the best (least) cost. Then build the new tour
    // with these values.

    int i = r.nextInt(tour.length - 0) + 0;
    //min cost
    double min = Double.POSITIVE_INFINITY;
    //elment to be swaped after checking all
    int swap;
    for (int j : tour[]){
     if(j != i){
      swap(tour, i , j);
      if(computeCost(tour, points) < min){
        swap = j;
        min = comcomputeCost(tour, points);
      }
      //swap back
      swap(tour, i ,j);
      }
     }
     //final swap
     swap(tour, i, swap);
    }
    //updated tour
    return tour;
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
    TSPGreedyLocalSearchAlt alg = new TSPGreedyLocalSearchAlt ();
    Pointd[] points = makeRandomPoints (25);
    int[] bestTour = alg.computeTour (points);
    double cost = alg.computeCost (bestTour, points);
    System.out.println ("TSPGreedyLocalSearchAlt: best cost = " + cost);
    alg.printTour ("TSPGreedyLocalSearchAlt", bestTour);

    // Output:
    //  cost: 6.429877982298063
    //  tour: 0 1 2 9 15 5 8 7 13 3 10 14 12 6 11 21 16 17 18 23 20 19 22 4 24
  }

}