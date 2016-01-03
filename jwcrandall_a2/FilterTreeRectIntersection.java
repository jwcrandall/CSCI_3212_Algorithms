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





public class FilterTreeRectIntersection implements RectangleSetIntersectionAlgorithm{
		//implemetns the rectagle set intersection algorithm interface

	public static int maxFilterTreeDepth;

public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_PURPLE = "\u001B[35m";
public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_WHITE = "\u001B[37m";


public static boolean printBlue = true; //this works
public static boolean printCyan = true; //this doesnt work as expected..

//to easily print strings of various colors
public static void printGreen(String args){
System.out.println(ANSI_GREEN+"\n"+args+ANSI_RESET); // note: "\n" in front of args
}
public static void printBlack(String args){
System.out.println(ANSI_BLACK+"\n"+args+ANSI_RESET); // note: "\n" in front of args
}
public static void printBlue(String args){
System.out.println(ANSI_BLUE+args+ANSI_RESET); // note: "\n" in front of args
}
public static void printRed(String args){
System.out.println(ANSI_RED+"\n"+args+ANSI_RESET); // note: "\n" in front of args
}
public static void printYellow(String args){
System.out.println(ANSI_YELLOW+args+ANSI_RESET); // note: "\n" in front of args
}

public static void printCyan(String args){
System.out.println(ANSI_CYAN+args+ANSI_RESET); // note: "\n" in front of args
}
public static void printPurple(String args){
System.out.println(ANSI_PURPLE+"\n"+args+ANSI_RESET); // note: "\n" in front of args
}



// public static void printBlue(String args){
// if(printBlue == false){
// return;
// }
// System.out.println(ANSI_BLUE+args+ANSI_RESET);
// }

// public static void printCyan(String args){
// if(printCyan == false){
// return;
// }
// System.out.println(ANSI_CYAN+args+ANSI_RESET);
// }

// public static void printRed(String args){
// System.out.println(ANSI_RED+args+ANSI_RESET);
// }


	public IntPair[] findIntersections(IntRectangle[] rectSet1, IntRectangle[] rectSet2){
		// System.out.println("find intersection running");
		// System.out.println("rectSet1");
		// for (IntRectangle ir1 : rectSet1){
		// 	System.out.println(ir1.toString());
		// }
		// System.out.println("rectSet2");
		// for (IntRectangle ir2 : rectSet2){
		// 	System.out.println(ir2.toString());
		// }


		//System.out.println("find intersection running");



		//get the root node
		FilterTreeNode rootNode = makeRootFilterTreeNode(rectSet1);
		//printRed("root "+ rootNode.toString());

		//make filter out of rectangles from rectangle set one
		for (IntRectangle r1 : rectSet1){makeFilterTreeReursive(r1,rootNode);}

		//initilize the arraylist for storing IntPairs
		List <IntPair> ipl = new ArrayList();

		//enhanced for loop
		for(IntRectangle r2 : rectSet2){
			//System.out.println("loop");
			//run Filtertree Search to find intersections and add them to the Arraylist of IntPair
			recursiveFilterTreeSearch(r2, rootNode, ipl);
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

	//make root Filter Tree Node
	public FilterTreeNode makeRootFilterTreeNode(IntRectangle[] rectSet1){
		//System.out.println("make root filter tree node running");
		int xmax = 0;
		int ymax = 0;
		for (IntRectangle r1 : rectSet1){
			//used to find max values for the size of the rectabgle of the first node
			for (IntRectangle r1Set : rectSet1){
				if (r1Set.topLeft.y > ymax){
					ymax = r1Set.topLeft.y;
				}
				if (r1Set.bottomRight.x > xmax){
					xmax = r1Set.bottomRight.x;
				}
			}
			//if yes
			//FilterTreeNode rootNode = new FilterTreeNode (0,xmax,0,ymax, 0);
		}return(new FilterTreeNode (0,xmax,0,ymax, 0));
	}


	public void recursiveFilterTreeSearch(IntRectangle r2, FilterTreeNode n, List <IntPair> ipl){
		//printGreen("recursive filter tree search running");
		//System.out.println("r2 = "+ r2.toString());
		//printGreen(n.toString());

		//check intersection at the level the rectangle is at, this is where int value pairs are added
		intersection(r2,n,ipl);

		//check both vertial and horitontal bar FilterTreeNode intersection
		//orientation of quadrents
		//01
		//23

		//both vertical and horizontal intersection
		if(r2.topLeft.x <= n.midX && r2.bottomRight.x >= n.midX
		&& r2.topLeft.y >= n.midY && r2.bottomRight.y <= n.midY){
			//printCyan(r2.toString() + " both vertical and horizontal intersection " + n.toString());
			if (n.quadrants[0] != null){
				deepIntersection(r2,n.quadrants[0],ipl);
			}
			if (n.quadrants[1] != null){
				deepIntersection(r2,n.quadrants[1],ipl);
			}
			if (n.quadrants[2] != null){
				deepIntersection(r2,n.quadrants[2],ipl);
			}
			if (n.quadrants[2] != null){
				deepIntersection(r2,n.quadrants[2],ipl);
			}
			return;
		}
		//vertial bar intersection top of horizontal
		if(r2.topLeft.x <= n.midX && r2.bottomRight.x >= n.midX && r2.bottomRight.y > n.midY){
			//printCyan(r2.toString() + " vertial bar intersection top of horizontal " + n.toString());
			if (n.quadrants[0] != null){
				deepIntersection(r2,n.quadrants[0],ipl);
			}
			if (n.quadrants[1] != null){
				deepIntersection(r2,n.quadrants[1],ipl);
			}
			return;
		}

		//vertial bar intersection bottom of horizontal
		if(r2.topLeft.x <= n.midX && r2.bottomRight.x >= n.midX && r2.topLeft.y < n.midY){
			//printCyan(r2.toString() + " vertial bar intersection bottom of horizontal " + n.toString());
			if (n.quadrants[2] != null){
				deepIntersection(r2,n.quadrants[2],ipl);
			}
			if (n.quadrants[3] != null){
				deepIntersection(r2,n.quadrants[3],ipl);
			}
			return;
		}


		//horizontal bar intersection left of vertical
		if(r2.topLeft.y >= n.midY && r2.bottomRight.y <= n.midY && r2.bottomRight.x < n.midX){
			//printCyan(r2.toString() + " horizontal bar intersection left of vertical " + n.toString());
			if (n.quadrants[0] != null){
				deepIntersection(r2,n.quadrants[0],ipl);
			}
			if (n.quadrants[2] != null){
				deepIntersection(r2,n.quadrants[2],ipl);
			}
			return;
		}


		//horizontal bar intersection right of vertical
		if(r2.topLeft.y >= n.midY && r2.bottomRight.y <= n.midY && r2.topLeft.x > n.midX){
			//printCyan(r2.toString() + " horizontal bar intersection right of vertical " + n.toString());
			if (n.quadrants[1] != null){
				deepIntersection(r2,n.quadrants[1],ipl);
			}
			if (n.quadrants[3] != null){
				deepIntersection(r2,n.quadrants[3],ipl);
			}
			return;
		}

		//check to see if you are at level 2, if you are, just exit
		if(n.level == 2){
			//printCyan(r2.toString() + " did not hit a crossbar ");
			return;
		}

		//if NO INTERSECTIONS WITH MID LINES with a mid line, check which quadrent r2 falls in and call same method in sub quadrent
		//if quadrent is null, return, this is beucase no r1 rectangles exist in this quadrent.
		//quad0
		if(r2.bottomRight.x < n.midX
			&& r2.bottomRight.y > n.midY){
			//add a new node if one does not exist at this position
			//System.out.println("in quad 0 search r2 = " + r2.toString());
			if(n.quadrants[0] == null){
				//printBlue("quad 0 is null");
				return;
			}
			else recursiveFilterTreeSearch(r2,n.quadrants[0],ipl);
		}
		//quad1
		if(r2.topLeft.x > n.midX && r2. bottomRight.y > n.midY){
			//System.out.println("in quad 1 search r2 = " + r2.toString());
			if(n.quadrants[1] == null){
				//printBlue("quad 1 is null");
				return;
			}
			else recursiveFilterTreeSearch(r2,n.quadrants[1],ipl);
		}
		//quad2
		if(r2.bottomRight.x < n.midX && r2.topLeft.y < n.midY){
			//System.out.println("in quad 2 search r2 = " + r2.toString());
			if(n.quadrants[2] == null){
				//printBlue("quad 2 is null");
				return;
			}
			else recursiveFilterTreeSearch(r2,n.quadrants[2],ipl);
		}
		//quad3
		if(r2.topLeft.x > n.midX && r2.topLeft.y < n.midY){
			//System.out.println("in quad 3 search r2 = " + r2.toString());
			if(n.quadrants[3] == null){
				//printBlue("quad 3 is null");
				return;
			}
			else recursiveFilterTreeSearch(r2,n.quadrants[3],ipl);
		}
	}


	//checking for further intersections once r2 gets traped at a filter
	public void deepIntersection(IntRectangle r2, FilterTreeNode n, List <IntPair> ipl){
		intersection(r2,n,ipl);
		for(FilterTreeNode nq : n.quadrants){
			if (nq != null){
				deepIntersection(r2, nq,ipl);
			}
		}
		//printPurple("deepIntersection exit");
	}

	public void intersection(IntRectangle r2, FilterTreeNode n, List <IntPair> ipl){
		//check all rectanlges in the nodes rectangle List
		//	printFTNR(n);
		for (Object r1o : n.rectList){
			//cast the object into a IntRectangle
			IntRectangle r1 = (IntRectangle)r1o;
			if (tlbr(r1,r2) == true || trbl(r1,r2) == true || enclosed(r1,r2)== true){
					//initilze pair and add it to the arraylist
					//printYellow("rectangle 1 " + r1.toString() + " intersects with rectangle 2 " + r2.toString());
					IntPair ip = new IntPair (r1.getID(), r2.getID());
					ipl.add(ip);
			}
		}
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

	public void makeFilterTreeReursive(IntRectangle r1, FilterTreeNode n){
		//System.out.println(r1.toString());
		//System.out.println(n.toString());
		//check to see if their is an intersection at that level
		//System.out.println("n.level = " + n.level);

		if(r1.topLeft.x <= n.midX
			&& r1.bottomRight.x >= n.midX
			|| r1.topLeft.y >= n.midY
			&& r1.bottomRight.y <= n.midY){
			//if intersect, add to node rectList
			n.rectList.add(r1);
		return;
		}
		//check to see if you are level 3, if you are, just add the rectagle to the node you are in
		if(n.level == 2){
			//System.out.println("at level three in creation");
			n.rectList.add(r1);
		return;
		}
		//01
		//23
		//quad0
		if(r1.bottomRight.x < n.midX
			&& r1.bottomRight.y > n.midY){
			//System.out.println("in quad 0 add");
			//add a new node if one does not exist at this position
			if(n.quadrants[0] == null){
				FilterTreeNode node = new FilterTreeNode (n.leftX,n.midX,n.midY,n.topY,n.level+1);
				n.quadrants[0] = node;
			}
			makeFilterTreeReursive(r1,n.quadrants[0]);
		}
		//quad1
		if(r1.topLeft.x > n.midX
			&& r1. bottomRight.y > n.midY){
			//System.out.println("in quad 1 add");
			if(n.quadrants[1] == null){
				FilterTreeNode node = new FilterTreeNode (n.midX,n.rightX,n.midY,n.topY,n.level+1);
				n.quadrants[1] = node;
			}
			makeFilterTreeReursive(r1,n.quadrants[1]);
		}
		//quad2
		if(r1.bottomRight.x < n.midX
			&& r1.topLeft.y < n.midY){
			//System.out.println("in quad 2 add");
			if(n.quadrants[2] == null){
				FilterTreeNode node = new FilterTreeNode (n.leftX,n.midX,n.botY,n.midY,n.level+1);
				n.quadrants[2] = node;
			}
			makeFilterTreeReursive(r1,n.quadrants[2]);
		}
		//quad3
		if(r1.topLeft.x > n.midX
			&& r1.topLeft.y < n.midY){
			//System.out.println("in quad 3 add");
			if(n.quadrants[3] == null){
				FilterTreeNode node = new FilterTreeNode (n.midX,n.rightX,n.botY,n.midY,n.level+1);
				n.quadrants[3] = node;
			}
			makeFilterTreeReursive(r1,n.quadrants[3]);

		}
	}


	public void printFilterTreeRecursive(FilterTreeNode n){
		printGreen(n.toString());
		printYellow("Fnode Rectanlge List");
		printCyan(n.rectList.toString());
		for(FilterTreeNode nq : n.quadrants){
			if (nq != null){
				printFilterTreeRecursive(nq);
			}
		}
	}

	//algorithm interface
	//edu.gwu.algtest.Algorithm
	//get name
	public java.lang.String getName(){
 		return "Joseph Crandall's implementation of FilterTreeRectIntersection ";
 	}
	//get property extractor
	public void setPropertyExtractor(int algID,edu.gwu.util.PropertyExtractor prop){
	//empty implementations, method definition empty body
	}



	//main for testing
	public static void main(String[] args) {

		printRed("The five rectangle make tree test");

		FilterTreeRectIntersection FTRC1 = new FilterTreeRectIntersection();

		IntPoint r1po1 = new IntPoint(89,57); //test top left
		IntPoint r1po2 = new IntPoint(91,37); //test bottom right
		IntRectangle r1 = new IntRectangle(r1po1, r1po2);


		IntPoint r2po1 = new IntPoint(12,80); //test top left
		IntPoint r2po2 = new IntPoint(45,66); //test bottom right
		IntRectangle r2 = new IntRectangle(r2po1, r2po2);


		IntPoint r3po1 = new IntPoint(28,40); //test top left
		IntPoint r3po2 = new IntPoint(39,29); //test bottom right
		IntRectangle r3 = new IntRectangle(r3po1, r3po2);


		IntPoint r4po1 = new IntPoint(18,70); //test top left
		IntPoint r4po2 = new IntPoint(32,60); //test bottom right
		IntRectangle r4 = new IntRectangle(r4po1, r4po2);


		IntPoint r5po1 = new IntPoint(79,94); //test top left
		IntPoint r5po2 = new IntPoint(82,90); //test bottom right
		IntRectangle r5 = new IntRectangle(r5po1, r5po2);


		IntRectangle[] rectSet1 = new IntRectangle[5];
		rectSet1[0] = r1;
		rectSet1[1] = r2;
		rectSet1[2] = r3;
		rectSet1[3] = r4;
		rectSet1[4] = r5;

		FilterTreeNode root = FTRC1.makeRootFilterTreeNode(rectSet1);
		for(IntRectangle i : rectSet1){
			FTRC1.makeFilterTreeReursive(i,root);
		}
		FTRC1.printFilterTreeRecursive(root);





		printRed("partyTime@joes rectangle make tree test");
		Random rn = new Random();
		FilterTreeRectIntersection FTRC2 = new FilterTreeRectIntersection();
		IntRectangle[] rectSet2 = new IntRectangle[100];
		for(int i = 0 ; i < 100; i++){
			int min = 0;
			int max = 1000;
			int xlow = rn.nextInt(max - min + 1) + min;
			int xhigh = rn.nextInt(max - xlow + 1) + xlow;
			int ylow = rn.nextInt(max - min + 1) + min;
			int yhigh = rn.nextInt(max - ylow + 1) + xlow;

			rectSet2[i] = new IntRectangle(new IntPoint(xlow,yhigh),new IntPoint(xhigh,ylow));
		}
		FilterTreeNode rootjoe = FTRC2.makeRootFilterTreeNode(rectSet2);
		for(IntRectangle j : rectSet2){
			FTRC2.makeFilterTreeReursive(j,rootjoe);
		}
		FTRC2.printFilterTreeRecursive(rootjoe);



		// IntPoint ipo3 = new IntPoint(9,2);
		// IntPoint ipo4 = new IntPoint(10,1);


		// //int test = ipo3.x;
		// //int test = ir1.bottomRight.x;
		// //how do I get from rectangle to a point to compare
		// IntRectangle ir2 = new IntRectangle(ipo3, ipo4);
		// IntRectangle ir3 = new IntRectangle(ipo1, ipo4);
		// IntRectangle ir4 = new IntRectangle(ipo2, ipo3);

		// //create sets
		// //IntRectangle[] rectSet2 = new IntRectangle[5];

		// rectSet1[0] = ir1;
		// rectSet2[0] = ir2;
		// rectSet2[1] = ir3;
		// rectSet2[2] = ir4;

		// //rectangles created


		// IntPair[] ipa1 = FTRC.findIntersections(rectSet1,rectSet2);
		// System.out.print(ipa1);

		// //enhanced for loop
		// for (IntPair test : ipa1){
		// 	System.out.println(test.toString());
		// }

		//System.out.println(ip1.toString());
		//System.out.println("The five rectangle scrambled");

	}
}