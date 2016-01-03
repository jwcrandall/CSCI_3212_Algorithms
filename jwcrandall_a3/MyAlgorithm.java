import edu.gwu.algtest.*;
import edu.gwu.util.*;
import edu.gwu.geometry.*;
import java.util.*;
//import java.lang.*;

public class MyAlgorithm implements MTSPAlgorithm{

	//MTSPAlgorithm interface method
	public int[][] computeTours(int m, Pointd[] points){
		int[][] x = new int[2][4];
		return x;
	}

	//algorithm interface methods
	//get name
	public java.lang.String getName(){
		return "Joseph Crandall's implementation of MyAlgorithm Traveling Salesman";
	 }
	//get property extractor
	public void setPropertyExtractor(int algID,edu.gwu.util.PropertyExtractor prop){
	//empty implementations, method definition empty body
	}

	//main for testing
	public static void main(String[] args) {
		System.out.println("The main is runing");

	// 	NaiveRectIntersection NRC = new NaiveRectIntersection();

	// 	IntPoint ipo1 = new IntPoint(3,8); //test top left
	// 	IntPoint ipo2 = new IntPoint(7,4); //test bottom right

	// 	IntRectangle ir1 = new IntRectangle(ipo1, ipo2);

	// 	IntPoint ipo3 = new IntPoint(9,2);
	// 	IntPoint ipo4 = new IntPoint(10,1);


	// 	//int test = ipo3.x;
	// 	//int test = ir1.bottomRight.x;
	// 	//how do I get from rectangle to a point to compare
	// 	IntRectangle ir2 = new IntRectangle(ipo3, ipo4);
	// 	IntRectangle ir3 = new IntRectangle(ipo1, ipo4);
	// 	IntRectangle ir4 = new IntRectangle(ipo2, ipo3);



	// 	//create sets
	// 	IntRectangle[] rectSet1 = new IntRectangle[5];
	// 	IntRectangle[] rectSet2 = new IntRectangle[5];

	// 	rectSet1[0] = ir1;
	// 	rectSet2[0] = ir2;
	// 	rectSet2[1] = ir3;
	// 	rectSet2[2] = ir4;





	// 	IntPair[] ipa1 = NRC.findIntersections(rectSet1,rectSet2);
	// 	//System.out.print(ipa1);

	// 	//enhanced for loop
	// 	for (IntPair test : ipa1){
	// 		System.out.println(test.toString());
	// 	}

	// 	//System.out.println(ip1.toString());
	// 	System.out.println("got through");
	}
}