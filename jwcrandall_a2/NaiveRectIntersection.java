/*
implementation of the naive algorithm for the rectangle- set intersection problem
Programed by Joseph Crandall
last modifed 10/9/15
*/
import edu.gwu.algtest.*;
import edu.gwu.util.*;
import edu.gwu.geometry.*;
import java.util.*;
//import java.lang.*;

public class NaiveRectIntersection implements RectangleSetIntersectionAlgorithm{
		//implemetns the rectagle set intersection algorithm interface
	public IntPair[] findIntersections(IntRectangle[] rectSet1, IntRectangle[] rectSet2){
		//initilize and array list for the Intpairs

		List <IntPair> ipl = new ArrayList();

		//if (ipl.isEmpty()){
			//System.out.println("the array list of IntPair is should be empty just initilized");
		//}

		//check one rectangle in the first array against all the other rectangles in the second array
		//using enhanced forloop to check each rectangle in set one with each rectangle in set 2
		for (IntRectangle r1 : rectSet1){
			for(IntRectangle r2 : rectSet2){

				//System.out.println(r1.toString());
				//System.out.println(r2.toString());

				if (tlbr(r1,r2) == true || trbl(r1,r2) == true || enclosed(r1,r2)== true){
					//initilze pair and add it to the arraylist
					IntPair ip = new IntPair (r1.getID(), r2.getID());
					ipl.add(ip);
				}
			}
		}

		//null case, check if array list is empty
		if (ipl.isEmpty()){
			//System.out.print("the array list of IntPair is empty");
			return(null);
		}

		//convert ArrayList to Array
		//ipl = int pair array list
		//ipa = int pair array
		IntPair[] ipa = new IntPair[ipl.size()];
		ipa = ipl.toArray(ipa);

		return(ipa);
	}

	public Boolean tlbr(IntRectangle r1, IntRectangle r2){
		if(	//r1 top left
			r1.topLeft.x <= r2.bottomRight.x
			&& r1.topLeft.y >= r2.bottomRight.y
			//r1 botom right
			&& r1.bottomRight.x >= r2.topLeft.x
			&& r1.bottomRight.y <= r2.topLeft.y){
			//System.out.println("tlbr passed");
			return(true);
		}
		return (false);
	}
	public Boolean trbl(IntRectangle r1, IntRectangle r2){
		if (//r1 top right
			r1.bottomRight.x >= r2.topLeft.x
			&& r1.topLeft.y >= r2.bottomRight.y
			//r1 bottom left
			&& r1.topLeft.x <= r2.bottomRight.x
			&& r1.bottomRight.y <= r2.topLeft.y
			){
			//System.out.println("trbl passed");
			return (true);
		}
		return (false);
	}

	public Boolean enclosed (IntRectangle r1, IntRectangle r2){
		if(//r1 enclosed r2
			r1.topLeft.x <= r2.topLeft.x
			&&r1.topLeft.y >= r2.topLeft.y
			&&r1.bottomRight.x>= r2.bottomRight.x
			&&r1.bottomRight.y<= r2.bottomRight.y
			){
			//System.out.println("r1 enclosed r2");
			return (true);
		}
		if(//r2 encloses r1
			r2.topLeft.x <= r1.topLeft.x
			&&r2.topLeft.y >= r1.topLeft.y
			&&r2.bottomRight.x>= r1.bottomRight.x
			&&r2.bottomRight.y<= r1.bottomRight.y
			){
		//System.out.println("r2 enclosed r1");
		return(true);
		}
		return (false);
	}

	//algorithm interface
	//edu.gwu.algtest.Algorithm
	//get name
	public java.lang.String getName(){
 		return "Joseph Crandall's implementation of NaiveRectIntersection";
 	}
	//get property extractor
	public void setPropertyExtractor(int algID,edu.gwu.util.PropertyExtractor prop){
	//empty implementations, method definition empty body
	}

	//main for testing
	public static void main(String[] args) {
		System.out.println("The main is runing");

		NaiveRectIntersection NRC = new NaiveRectIntersection();

		IntPoint ipo1 = new IntPoint(3,8); //test top left
		IntPoint ipo2 = new IntPoint(7,4); //test bottom right

		IntRectangle ir1 = new IntRectangle(ipo1, ipo2);

		IntPoint ipo3 = new IntPoint(9,2);
		IntPoint ipo4 = new IntPoint(10,1);


		//int test = ipo3.x;
		//int test = ir1.bottomRight.x;
		//how do I get from rectangle to a point to compare
		IntRectangle ir2 = new IntRectangle(ipo3, ipo4);
		IntRectangle ir3 = new IntRectangle(ipo1, ipo4);
		IntRectangle ir4 = new IntRectangle(ipo2, ipo3);



		//create sets
		IntRectangle[] rectSet1 = new IntRectangle[5];
		IntRectangle[] rectSet2 = new IntRectangle[5];

		rectSet1[0] = ir1;
		rectSet2[0] = ir2;
		rectSet2[1] = ir3;
		rectSet2[2] = ir4;





		IntPair[] ipa1 = NRC.findIntersections(rectSet1,rectSet2);
		//System.out.print(ipa1);

		//enhanced for loop
		for (IntPair test : ipa1){
			System.out.println(test.toString());
		}

		//System.out.println(ip1.toString());
		System.out.println("got through");
	}
}