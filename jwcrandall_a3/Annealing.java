import edu.gwu.algtest.*;
import edu.gwu.util.*;
import edu.gwu.geometry.*;
import java.util.*;
//import java.lang.*;

public class Annealing implements MTSPAlgorithm{

	//MTSPAlgorithm interface method
	public int[][] computeTours(int m, Pointd[]points){
		int isJagged = 0;
		//TURN ON AND OFF
		//System.out.println("\u001B[31m" + "!!!TEST STARTED!!!" + "\u001B[0m");

		//TURN ON AND OFF
		//startPrint(m, points);




		//randomly allocate all the points to the available salesmen
		int[][] routes = firstState(m,points);
		//TURN ON AND OFF
		//printRoutes(routes);

		//calculate the inital total cost of the state
		double stateCost = calculateStateCost(routes, points);
		//System.out.println(stateCost);
		double min = stateCost;
		int [][] minRoutes = routes;

		//System.out.println("\u001B[31m" + "!!!TEST STARTED!!!" + "\u001B[0m");




		int annealingInital = 10000;
		int annealing = annealingInital;

		while(annealing > 0){
			//pick a random salesman and a random point
			//pick another random salesman and a random point,
			//swap
			//printRoutes(routes);
			int [][]swap;
			if(isJagged == 0){
				swap = randomSwap(routes);
			}
			else{
				swap = randomSwapJagged(routes);
			}
			//printRoutes(swap);
			//calcualte cost of state
			double swapCost = calculateStateCostJagged(swap, points);
			//System.out.println(swapCost);
			//if less than previous allow
			//is less than min set as new min
			if(swapCost < min){
				isJagged = 1;
				min = swapCost;
				minRoutes = swap;
				routes = swap;
				//System.out.println(min);
			}
			//if greater than previous, (allow if probabiilty allows)
			if (swapCost >= min){
				isJagged = 1;
				//run annealing probability
				int prob = annealingProbability(annealing, annealingInital);
				//if allowed, apply swap and continue
				//decrease annealing value
				if(prob == 1){
					routes = swap;
					annealing --;
				}
				//if not allowed, dont apply swap and continue
				//decrease annealing value
				if(prob == 0){
					annealing--;
				}
			}
		}

		//return the overall min when the array can swap no more


		// //get routes
		// int[][] routes = annealingAlgorithm(m, points);

		// //TURN ON AND OFF
		// //printRoutes(routes,m,points.length);

		//remove excessive space in array

		if(isJagged == 0){
			minRoutes = jaggedArray(routes);
		}

		//System.out.println();
		//System.out.println();
		//double test = calculateStateCostJagged(minRoutes, points);
		//System.out.println(test);




		//TURN ON AND OFF
		//printJaggedRoutes(routes);

		return minRoutes;
	}

int annealingProbability (int annealing, int annealingInital){
	//random int between 0 and annealingInital
		//if less then annealing yes
		//if greater than annealing no
	Random random = new Random();
	int probPoint = random.nextInt(annealingInital);
	if(probPoint < annealing){
		return 1;
	}
	else return 0; //(probPoint >= annealing){
}

int[][]randomSwap(int [][]routes){
	//copy routes
	int [][] copy = routes.clone();
	//make copy jagged
	jaggedArray(copy);
	//get random index
	Random random = new Random();
	int row1 = random.nextInt(copy.length);
	int column1 = random.nextInt(copy[row1].length);
	int randomIndex1 = copy[row1][column1];
	int row2 = random.nextInt(copy.length);
	int column2 = random.nextInt(copy[row2].length);
	int randomIndex2 = copy[row2][column2];
	//swap
	//int tempIndex = randomIndex1;
	copy[row1][column1] = randomIndex2;
	copy[row2][column2] = randomIndex1;

	return copy;
}

int[][]randomSwapJagged(int [][]copy){
	//copy routes
	//int [][] copy = routes.clone();
	//make copy jagged
	//jaggedArray(copy);
	//get random index
	Random random = new Random();
	int row1 = random.nextInt(copy.length);
	int column1 = random.nextInt(copy[row1].length);
	int randomIndex1 = copy[row1][column1];
	int row2 = random.nextInt(copy.length);
	int column2 = random.nextInt(copy[row2].length);
	int randomIndex2 = copy[row2][column2];
	//swap
	//int tempIndex = randomIndex1;
	copy[row1][column1] = randomIndex2;
	copy[row2][column2] = randomIndex1;

	return copy;
}

double calculateStateCost(int [][] routes, Pointd[]points){
	//copy routes in order remove excessive -1's without modifying routes
	//array.clone witll only work on primitive types(don't use for other stuff)
	int [][] copy = routes.clone();
	copy = jaggedArray(copy);
	//printRoutes(copy);
	//printRoutes(routes);
	double totalCost = 0;
	for(int row = 0 ; row < copy.length; row++){
		for (int col = 0; col < copy[row].length -1 ; col++){
			//calculace distnace betwen points determined by indexs in routes
			totalCost += distance(points[copy[row][col]] , points[copy[row][col+1]]);
		}
	}
	return totalCost;
}

double calculateStateCostJagged(int [][] copy, Pointd[]points){
	//copy routes in order remove excessive -1's without modifying routes
	//array.clone witll only work on primitive types(don't use for other stuff)
	// int [][] copy = routes.clone();
	// copy = jaggedArray(copy);
	//printRoutes(copy);
	//printRoutes(routes);
	double totalCost = 0;
	for(int row = 0 ; row < copy.length; row++){
		for (int col = 0; col < copy[row].length -1 ; col++){
			//calculace distnace betwen points determined by indexs in routes
			totalCost += distance(points[copy[row][col]] , points[copy[row][col+1]]);
		}
	}
	return totalCost;
}



int [][] firstState(int m, Pointd[]points){

	//create routes 2d array
	int[][] routes = new int[m][points.length];
	//intialize routes
	for(int row = 0 ; row < routes.length; row++){
		for (int col = 0; col < routes[row].length ; col++){
			routes[row][col] = -1;
		}
	}
	//create point order
	int[] pointOrder = new int[points.length];
	//intilzie point order
	for(int i = 0 ; i < pointOrder.length; i++){
		pointOrder[i] = i;
	}
	//randomize point order
	//printAvailablePoints(pointOrder);
	shuffleArray(pointOrder);
	//printAvailablePoints(pointOrder);

	//give random indexes to random salesman
	Random random = new Random();

	//printRoutes(routes);

	for(int j : pointOrder){
		int salesman = random.nextInt(m);
		//find next empty spot in random salesman's array
		int k = 0;
		while(routes[salesman][k] != -1){
			k++;
		}
		//assign the point index to a salesman
		//System.out.println(salesman);
		routes[salesman][k] = pointOrder[j];
	}
	return(routes);
}

static void shuffleArray(int[] ar){
    // If running on Java 6 or older, use `new Random()` on RHS here
	Random random = new Random();
    for (int i = ar.length - 1; i > 0; i--){
    	int index = random.nextInt(i + 1);
    	// Simple swap
    	int a = ar[index];
    	ar[index] = ar[i];
     	ar[i] = a;
	}
}






































// //greedyAlgorithm
// 	int [][] greedyAlgorithm(int m, Pointd[]points){
// 		///////Initalization//////////
// 		//create return int array so that it is possible to give all points to a single salesman
// 		int[][] routes = new int[m][points.length];
// 		initialize(routes,m,points.length);

// 		//array to be moddifed as points are used
// 		int[] pointsAvailable = new int[points.length];

// 		//initialize
// 		for(int q = 0; q < pointsAvailable.length; q++){
// 			pointsAvailable[q] = 1;
// 		}


// 		////////GreedyAlgorithm//////////////
// 		//does not account for mismatch odd run out of shit senarior
// 		//first index filled with an integer coresponding to a point
// 		//on each row associated with a salesman
// 		for(int i = 0; i < m ; i++){
// 			routes[i][0] = i;
// 			pointsAvailable[i] = 0;
// 		}

// 		//printJaggedRoutes(routes);
// 		//printAvailablePoints(pointsAvailable);


// 		int keepGoing = 1;
// 		while(keepGoing == 1){

// 			//this does not acount for an uneven number of points

// 			//goes through each row of jagged index matrix
// 			for(int row = 0; row < routes.length ; row++){

// 				//find the last non -1 index in a row
// 				int iterator = 0;
// 				while(routes[row][iterator+1] != -1){
// 					iterator+=1;
// 				}
// 				int pointIndex = iterator;
// 				int next = -1;
// 				int min = Integer.MAX_VALUE;
// 				int distance = Integer.MAX_VALUE;

// 				//traverse the available points array
// 				int j;
// 				for(j = 0; j < pointsAvailable.length ; j++){

// 					//see if point is avaible
// 					if(pointsAvailable[j] != 0){
// 						//calculate the distance betwwen the two points at indexes column and j
// 						int howFar = distance(points[pointIndex], points[j]); // not correct
// 						if (howFar < min){
// 							min = howFar;
// 							next = j;
// 						}
// 					}
// 				}

// 				//System.out.println("\u001B[31m" + "!!!here!!!" + "\u001B[0m");
// 				//System.out.print(next);


// 				//check to see if an index from pointsAvailable has been chosen
// 				//this same index is used in points to find the x and y values
// 				//used during computing distance
// 				if(next != -1){
// 					//update routes and pointsAvailable
// 					routes[row][pointIndex+1] = next;
// 					pointsAvailable[next] = 0;
// 				}


// 			//printJaggedRoutes(routes);
// 			//printAvailablePoints(pointsAvailable);


// 			}

// 			//check to see if any points left to be traversted
// 			keepGoing = 0;
// 			for(int t = 0; t < pointsAvailable.length; t++){
// 				if(pointsAvailable[t] == 1){
// 					keepGoing = 1;
// 				}
// 			}

// 		}
// 		return routes;
// 	}




























































	void printAvailablePoints(int [] pointsAvailable){
		System.out.print("Points Avaible Array    ");
		for(int i : pointsAvailable){
			System.out.print(i+ "    ");
		}
		System.out.println("");
		System.out.println("");
	}


	int [][] jaggedArray(int[][] routes){
		//remove exessive indexs in each row in array
		for(int k = 0; k < routes.length; k++){
			//find first excessive element
			int stop = 0;
			while(routes[k][stop] != -1){
				stop++;
			}
			//create new array
			int[] copy = new int[stop];
			//fill it
			for(int fill = 0; fill < copy.length; fill++){
				//System.out.println(fill);
				copy[fill] = routes[k][fill];
			}
			//update 2d array with no excesse sub collum array
			routes[k] = copy;
		}
		return(routes);
	}





	//calculate distance
	double distance(Pointd one, Pointd two){
		double s1 = Math.pow((one.x-two.x),2);
		double s2 = Math.pow((one.y-two.y),2);
		double x = Math.sqrt((s1+s2));
		//this will always round down 1.999 = 1
		//long y = Math.round(x);
		//cast it
		//int z = (int)y;
		return(x);
	}


	//print the jagged route array
	//print the salesman routes
	public void printJaggedRoutes(int [][] x){
		System.out.print("Salesman Jagged Route Points");
		for(int i = 0; i < x.length ; i++){
			System.out.println("");
			System.out.print("Salesman: "+ i +":     ");
			for(int j = 0 ; j < x[i].length; j++){
				System.out.print(x [i][j] + "    ");
			}
		}
		System.out.println("");
		System.out.println("");

	}


// 	//initialize array
// 	public void initialize(int [][] x, int row, int collum){
// 		for(int i = 0; i < row ; i++){
// 			for(int j = 0 ; j < collum; j++){
// 				x[i][j] = -1;
// 			}
// 		}
// 	}

	//print the salesman routes
	public void printRoutes(int [][] x){
		System.out.print("Salesman Route Points");
		for(int i = 0; i < x.length ; i++){
			System.out.println("");
			System.out.print("Salesman: "+ i +":     ");
			for(int j = 0 ; j < x[i].length; j++){
				System.out.print(x [i][j] + "    ");
			}
		}
		System.out.println("");
		System.out.println("");
	}

// 	//print to check what the test enviorment is sending
// 	public void startPrint(int m, Pointd[]points){
// 		System.out.println("computeTours runing");
// 		System.out.println("number of salesman: " + m);
// 		System.out.println("array of points");
// 		int j = 0;
// 		for(Pointd i : points){
// 			System.out.println("Point: "+j+"    "+i);
// 			//System.out.println("["+i.x+","+i.y+"]");
// 			j++;
// 		}
// 		System.out.println("");

// 	}




























































	//algorithm interface methods
	//get name
	public java.lang.String getName(){
		return "Joseph Crandall's implementation of Annealing Traveling Salesman";
	 }
	//get property extractor
	public void setPropertyExtractor(int algID,edu.gwu.util.PropertyExtractor prop){
	//empty implementations, method definition empty body
	}

	//main for testing
	public static void main(String[] args) {
		System.out.println("The main is runing");


	}
}