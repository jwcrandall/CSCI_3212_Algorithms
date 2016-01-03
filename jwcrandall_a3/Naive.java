import edu.gwu.algtest.*;
import edu.gwu.util.*;
import edu.gwu.geometry.*;
import java.util.*;
import java.lang.*;

//public static final String ANSI_RESET = "\u001B[0m";
//public static final String ANSI_RED = "\u001B[31m";


public class Naive implements MTSPAlgorithm{

	//MTSPAlgorithm interface method
	public int[][] computeTours(int m, Pointd[]points){

		//TURN ON AND OFF
		//System.out.println("\u001B[31m" + "!!!TEST STARTED!!!" + "\u001B[0m");

		//TURN ON AND OFF
		//startPrint(m, points);


		//create return int array so that it is possible to give all points to a single salesman
		int[][] x = new int[m][points.length];
		initialize(x,m,points.length);

		//assign points
		int point = 0;
		int i;
		int j;
		//utilize the quotient and the remainder
		//System.out.println(points.length/m);
		//System.out.println(points.length%m);
		for(i = 0; i < (points.length/m) ; i++){
			for(j = 0; j < m; j++){
				x[j][i] = point;
				point++;
			}
		}
		if(points.length%m != 0){
			for(j = 0; j < (points.length%m); j++){
				x[j][i] = point;
				point++;
			}
		}

		//TURN ON AND OFF
		//printRoutes(x,m,points.length);





		//remove exessive indexs in each row in array
		for(int k = 0; k < m; k++){

			//find first excessive element
			int stop = 0;
			while(x[k][stop] != -1){
				stop++;
			}

			//create new array
			int[] copy = new int[stop];

			//fill it
			for(int fill = 0; fill < copy.length; fill++){
				//System.out.println(fill);
				copy[fill] = x[k][fill];
			}

			//update 2d array with no excesse sub collum array
			x[k] = copy;
		}

		//TURN ON AND OFF
		//printJaggedRoutes(x);

		return x;
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


	//initialize array
	public void initialize(int [][] x, int row, int collum){
		for(int i = 0; i < row ; i++){
			for(int j = 0 ; j < collum; j++){
				x[i][j] = -1;
			}
		}
	}

	//print the salesman routes
	public void printRoutes(int [][] x, int row, int collum){
		System.out.print("Salesman Route Points");
		for(int i = 0; i < row ; i++){
			System.out.println("");
			System.out.print("Salesman: "+ i +":     ");
			for(int j = 0 ; j < collum; j++){
				System.out.print(x [i][j] + "    ");
			}
		}
		System.out.println("");
		System.out.println("");

	}

	//print to check what the test enviorment is sending
	public void startPrint(int m, Pointd[]points){
		System.out.println("computeTours runing");
		System.out.println("number of salesman: " + m);
		System.out.println("array of points");
		int j = 0;
		for(Pointd i : points){
			System.out.println("Point: "+j+"    "+i);
			//System.out.println("["+i.x+","+i.y+"]");
			j++;
		}
		System.out.println("");

	}


	//algorithm interface methods
	//get name
	public java.lang.String getName(){
		return "Joseph Crandall's implementation of Naive Traveling Salesman";
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