/**
* @author Jospeh Crandall
* @version 1.00 December 6th 2015
*/
import java.util.*;


// public static final String ANSI_RESET = "\u001B[0m";
// public static final String ANSI_BLACK = "\u001B[30m";
// public static final String ANSI_RED = "\u001B[31m";
// public static final String ANSI_GREEN = "\u001B[32m";
// public static final String ANSI_YELLOW = "\u001B[33m";
// public static final String ANSI_BLUE = "\u001B[34m";
// public static final String ANSI_PURPLE = "\u001B[35m";
// public static final String ANSI_CYAN = "\u001B[36m";
// public static final String ANSI_WHITE = "\u001B[37m";



public class JwcrandallAlgorithm implements CameraPlacementAlgorithm {

    public ArrayList<Camera> solve (CameraPlacementProblem problem){
    	ArrayList<Camera> cameraList = new ArrayList<>();

    	//printWalls(problem);
   		ArrayList<Pointd> possibleCameraLocations = new ArrayList<>();
   		possibleCameraLocations = getPossibleCameraLocations(problem);
   		//printPossibleCameraLocations(possibleCameraLocations);


   		ArrayList<Camera> allPossibleCameraWithCoverage = new ArrayList();
   		allPossibleCameraWithCoverage = getPossibleCameraWithCoverage(problem, possibleCameraLocations);
   		//printAllPossibleCameraWithCoverage(allPossibleCameraWithCoverage);



   		//get start state, with Greedy Dynamic Programing
   		cameraList = startState(problem, allPossibleCameraWithCoverage);


		// Place cameras at the four corners, but only show the first two
		// (just to demonstrate that you can selectively view cameras).
		// Camera camera = new Camera( new Pointd(7,5), 61, true);
		// cameraList.add(camera);

		//camera = new Camera( new Pointd(1,problem.getMaxY()-1), 315, false);
		//cameraList.add(camera);
		//camera = new Camera( new Pointd(problem.getMaxX()-1, 1), 150, true );
		//cameraList.add(camera);
		// camera = new Camera( new Pointd(problem.getMaxX()-1, problem.getMaxY()-1), 225, false );
		// cameraList.add(camera);

   		printAllPossibleCameraWithCoverage(cameraList);

    	return cameraList;
    }

    public ArrayList<Camera> startState(CameraPlacementProblem problem, ArrayList<Camera> allPossibleCameraWithCoverage){

    	ArrayList<Camera> cameraList = new ArrayList<>();

    	//cammera ussages array
    	//0 available
    	//1 space taken by differnt orinetation
    	//2 camera being used
    	int usage[] = new int[allPossibleCameraWithCoverage.size()];
    	//initalize array
    	for(int i = 0; i < usage.length; i ++){
    		usage[i] = 0;
    	}

    	//find inital single camera with best coverage
    	int coverage = 0;
    	int position = -1;
    	for (int j = 0; j < allPossibleCameraWithCoverage.size(); j++) {
    		CameraPlacementResult camResult = new CameraPlacementResult ();
    		ArrayList<Camera> singleCameraList = new ArrayList<>();
    		//might be incorrect
			singleCameraList.add(allPossibleCameraWithCoverage.get(j));
			int currentCoverage = calculateCoverage(singleCameraList, problem);
			if(currentCoverage > coverage){
				coverage = currentCoverage;
				position = j;
			}
    	}

    	//update usage
    	for (int k = 0; k < allPossibleCameraWithCoverage.size(); k++) {
	    	if(allPossibleCameraWithCoverage.get(k).location == allPossibleCameraWithCoverage.get(position).location){
	    		usage[k] = 1;
	    		if(allPossibleCameraWithCoverage.get(k).orientation == allPossibleCameraWithCoverage.get(position).orientation){
	    			usage[k] = 2;
	    		}
	    	}
	    }

	    cameraList.add(allPossibleCameraWithCoverage.get(position));
	    //printAllPossibleCameraWithCoverage(cameraList);

	    //System.out.println(coveragePercentage(cameraList, problem));















	   	//System.out.println("\u001B[31m" + "This text is red!" + "\u001B[0m");
	   	///System.out.println("hello");

    	//build dynamic greedy
	    while(coveragePercentage(cameraList, problem) != 100.0){

		    //System.out.println("\u001B[31m" + "This text is red!" + "\u001B[0m");
		    printAllPossibleCameraWithCoverage(cameraList);
		    System.out.println(coveragePercentage(cameraList, problem));

	    	//find next camera that adds the most new coverage
	    	int bestAddedCoverage = 0;
	    	int positionSpot = -1;

	    	for (int l = 0; l < allPossibleCameraWithCoverage.size(); l++) {
	    		CameraPlacementResult camResult = new CameraPlacementResult ();
	    		//This line was making me cry
	    		//ArrayList<Camera> newCameraList = cameraList;
	    				    //printAllPossibleCameraWithCoverage(newCameraList);

	    		//check if camera is available
	    		if(usage[l] == 0){
	    			cameraList.add(allPossibleCameraWithCoverage.get(l));
					int addedCoverage = calculateCoverage(cameraList, problem);
					if(bestAddedCoverage < addedCoverage - coverage){
						bestAddedCoverage = addedCoverage - coverage;
						positionSpot = l;
					}
					cameraList.remove(allPossibleCameraWithCoverage.get(l));

	    		}
	    	}

	    	//update usage
	    	for (int m = 0; m < allPossibleCameraWithCoverage.size(); m++) {
		    	if(allPossibleCameraWithCoverage.get(m).location == allPossibleCameraWithCoverage.get(positionSpot).location){
		    		usage[m] = 1;
		    		if(allPossibleCameraWithCoverage.get(m).orientation == allPossibleCameraWithCoverage.get(positionSpot).orientation){
		    			usage[m] = 2;
		    		}
		    	}
		    }

		   	if(positionSpot != -1){
		   		System.out.println("\u001B[31m" + "should print alot" + "\u001B[0m");
		   		cameraList.add(allPossibleCameraWithCoverage.get(positionSpot));
		   	}
		   	//printAllPossibleCameraWithCoverage(cameraList);
	    }

    	return(cameraList);
    }

	public double coveragePercentage(ArrayList<Camera> cameraList, CameraPlacementProblem problem){

		CameraPlacementResult camResult = new CameraPlacementResult ();
		String results = null;
		camResult.cover = CameraGeometry.findCover(problem.getMaxX(),
			problem.getMaxY(),
			problem.getCameraRange(),
			cameraList,
			problem.getExteriorWalls(),
			problem.getInteriorWalls(),
			false,
			true);

			double total = problem.getMaxX()*problem.getMaxY();
			camResult.numCovered = 0;
			camResult.numNotCovered = 0;
			camResult.percentCovered = 0;

			results = " Covered cells: ";
			for (int i = 0; i < problem.getMaxX(); i++) {
				for (int j = 0; j < problem.getMaxY(); j++) {
					if (camResult.cover[i][j] == 1) {
					    camResult.numCovered++;
					    results = results + "(" + i + ", " + j + ") ";
					}
					else if (camResult.cover[i][j] == 0) {
					    camResult.numNotCovered++;
					}
				}
			}
			camResult.percentCovered = 100.0 * (double) camResult.numCovered/((double)camResult.numCovered + (double)camResult.numNotCovered);
		return (camResult.percentCovered);
	}

	public int calculateCoverage(ArrayList<Camera> cameraList, CameraPlacementProblem problem){
		CameraPlacementResult camResult = new CameraPlacementResult ();
		String results = null;
		camResult.cover = CameraGeometry.findCover(problem.getMaxX(),
			problem.getMaxY(),
			problem.getCameraRange(),
			cameraList,
			problem.getExteriorWalls(),
			problem.getInteriorWalls(),
			false,
			true);

			double total = problem.getMaxX()*problem.getMaxY();
			camResult.numCovered = 0;
			camResult.numNotCovered = 0;
			camResult.percentCovered = 0;

			results = " Covered cells: ";
			for (int i = 0; i < problem.getMaxX(); i++) {
				for (int j = 0; j < problem.getMaxY(); j++) {
					if (camResult.cover[i][j] == 1) {
					    camResult.numCovered++;
					    results = results + "(" + i + ", " + j + ") ";
					}
					else if (camResult.cover[i][j] == 0) {
					    camResult.numNotCovered++;
					}
				}
			}
		return (camResult.numCovered);
	}

    public void printAllPossibleCameraWithCoverage(ArrayList<Camera> allPossibleCameraWithCoverage){
    	for (int i = 0; i < allPossibleCameraWithCoverage.size(); i++) {
    		System.out.println((allPossibleCameraWithCoverage.get(i)));
    	}
    }

    public ArrayList<Camera> getPossibleCameraWithCoverage(CameraPlacementProblem problem, ArrayList<Pointd> possibleCameraLocations){
    	//quantized into 12 possible angles that achieve best result
    	//camera field of vision off angle = 30, total field of vision = 60)
    	//30 45 60
    	//120 135 150
		//210 225 240
		//300 315 330
    	ArrayList<Camera> cameraList = new ArrayList<>();
    	for (int i = 0; i < possibleCameraLocations.size(); i++) {
    		//loop through angles
    		for(int j = 0; j < 360; j+=90){
    			for(int k = 30 ; k<=60 ; k+=15){
    				//only inlcude orintations that cover at least one square
    				//how to calculate coverge
    				//this is overkill

    				CameraPlacementResult camResult = new CameraPlacementResult ();
					ArrayList<Camera> singleCameraList = new ArrayList<>();
					singleCameraList.add(new Camera(possibleCameraLocations.get(i),k+j,true));
    				camResult.cover = CameraGeometry.findCover(problem.getMaxX(), problem.getMaxY(),problem.getCameraRange(),
    				singleCameraList,
			    	problem.getExteriorWalls(),problem.getInteriorWalls(),false,true);

    				int yes = 0;
			    	for (int l = 0; l < problem.getMaxX(); l++) {
	    				for (int m = 0; m < problem.getMaxY(); m++) {
							if (camResult.cover[l][m] == 1) {
								yes = 1;
							}
						}
					}
					if(yes == 1){
						cameraList.add(new Camera(possibleCameraLocations.get(i),k+j,true));
					}
    			}
  			}
    	}
    	return (cameraList);
    }

    public void printWalls(CameraPlacementProblem problem){
    	//I want to get all possible wall placements
    	for (int i = 0; i < problem.getExteriorWalls().size(); i++) {
    		System.out.println((problem.getExteriorWalls().get(i)));
    	}
    	System.out.println();
    	for (int i = 0; i < problem.getInteriorWalls().size(); i++) {
    		System.out.println((problem.getInteriorWalls().get(i)));
   		}
    }

	//I dont think this accounts for double thickness walls
	public ArrayList<Pointd> getPossibleCameraLocations (CameraPlacementProblem problem){
		// Array List of possible Camera Locations
		ArrayList<Pointd> possibleCameraLocations = new ArrayList<>();

		//adding exterior wall possitions to possibleCameraLocations

		//System.out.println(problem.getExteriorWalls().size());

		for (int i = 0; i < problem.getExteriorWalls().size(); i++) {
		 	Pointd start = problem.getExteriorWalls().get(i).start;
		    Pointd end = problem.getExteriorWalls().get(i).end;


		    //System.out.println("\u001B[31m" + "This text is red!" + "\u001B[0m");
		    //System.out.println(i);



		    //vertical wall
		    if(start.x == end.x){
		     	//left
		       	if(start.x == 0){
		       		//the +1 in the for loop is to get through the wall so you can place first camera
		       		//the +1 in the point addition is because points is defined bottom left, it needs to be
		       			//on the right to be on the inside of the room
		       		for(double j = start.y + 1; j < end.y ; j++){
					    int check = 0;
					    //check if point is already in ArrayList
					    for (int k = 0; k < possibleCameraLocations.size(); k++) {
			    			if(possibleCameraLocations.get(k).x == start.x+1){
								if(possibleCameraLocations.get(k).y == j){
									check = 1;
								}
							}
			    		}
			    		if(check == 0){
			    			possibleCameraLocations.add(new Pointd (start.x + 1 , j));
			    		}
		       		}
		       	}
		       	//right
		       	if(start.x != 0){
		       	//the +1 in the for loop is to get through the wall so you can place first camera
		       		for(double j = start.y + 1; j < end.y ; j++){
						int check = 0;
					    //check if point is already in ArrayList
					    for (int k = 0; k < possibleCameraLocations.size(); k++) {
			    			if(possibleCameraLocations.get(k).x == start.x){
								if(possibleCameraLocations.get(k).y == j){
									check = 1;
								}
							}
			    		}
			    		if(check == 0){
			    			possibleCameraLocations.add(new Pointd (start.x , j));
			    		}
		       		}
		       	}
		    }
		    //horitzontal wall
		    if(start.y == end.y){
		   		//top
		   		if(start.y != 0){
	   				//the +1 is to get on inside of wall
		        	for(double j = start.x + 1 ; j < end.x ; j++){
						int check = 0;
		        		//check if point is already in ArrayList
					    for (int k = 0; k < possibleCameraLocations.size(); k++) {
			    			if(possibleCameraLocations.get(k).x == j){
								if(possibleCameraLocations.get(k).y == start.y){
									check = 1;
								}
							}
			    		}
			    		if(check == 0){
			    			possibleCameraLocations.add(new Pointd (j,start.y));
			    		}
		        	}
		        }
		        //bottom
		        if(start.y == 0){
		        	//the +1 is to get on inside of wall
		        	for(double j = start.x + 1 ; j < end.x ; j++){
		        		int check = 0;
		        		//check if point is already in ArrayList
					    for (int k = 0; k < possibleCameraLocations.size(); k++) {
			    			if(possibleCameraLocations.get(k).x == j){
								if(possibleCameraLocations.get(k).y == start.y+1){
									check = 1;
								}
							}
			    		}
			    		if(check == 0){
			    			possibleCameraLocations.add(new Pointd (j, start.y + 1));
			    		}
		        	}
		        }
		    }
		}


		//this breaks if walls can overlap, can a wall block be placed on top of another wall block
		//adding interior wall possitions to possibleCameraLocations
		for (int i = 0; i < problem.getInteriorWalls().size(); i++) {
		 	Pointd start = problem.getInteriorWalls().get(i).start;
		    Pointd end = problem.getInteriorWalls().get(i).end;
			//vertical wall
		    if(start.x == end.x){
		       		//left side of the wall
		       		for(double j = start.y; j <= end.y ; j++){

					    int check = 0;
					    //check if point is already in possibleWallPossition ArrayList
					    for (int k = 0; k < possibleCameraLocations.size(); k++) {
			    			if(possibleCameraLocations.get(k).x == start.x){
								if(possibleCameraLocations.get(k).y == j){
									check = 1;
								}
							}
			    		}
			    		if(check == 0){
			    			possibleCameraLocations.add(new Pointd (start.x , j));
			    		}
		       		}
		       		//right side of the wall
		       		for(double j = start.y; j <= end.y ; j++){
						int check = 0;
					    //check if point is already in possibleWallPossition ArrayList
					    for (int k = 0; k < possibleCameraLocations.size(); k++) {
			    			if(possibleCameraLocations.get(k).x == start.x+1){
								if(possibleCameraLocations.get(k).y == j){
									check = 1;
								}
							}
			    		}
			    		if(check == 0){
			    			possibleCameraLocations.add(new Pointd (start.x + 1 , j));
			    		}
		       		}
		    }
		    //horitzontal wall
		    if(start.y == end.y){
		    	//top
	   			//the +1 is to get on inside of wall
		        for(double j = start.x + 1 ; j <= end.x ; j++){
					int check = 0;
		        	//check if point is already in ArrayList
				    for (int k = 0; k < possibleCameraLocations.size(); k++) {
			    		if(possibleCameraLocations.get(k).x == j){
							if(possibleCameraLocations.get(k).y == start.y){
								check = 1;
							}
						}
			    	}
			   		if(check == 0){
			   			possibleCameraLocations.add(new Pointd (j,start.y));
			   		}
		       	}
		        //bottom
		        //the +1 is to get on inside of wall
		        for(double j = start.x + 1 ; j <= end.x ; j++){
		        	int check = 0;
		        	//check if point is already in ArrayList
				    for (int k = 0; k < possibleCameraLocations.size(); k++) {
			    		if(possibleCameraLocations.get(k).x == j){
							if(possibleCameraLocations.get(k).y == start.y+1){
								check = 1;
							}
						}
			    	}
			    	if(check == 0){
			    		possibleCameraLocations.add(new Pointd (j, start.y + 1));
			    	}
		        }
		    }
		}
		return(possibleCameraLocations);
	}

	public void printPossibleCameraLocations(ArrayList<Pointd> possibleCameraLocations){
		for (int i = 0; i < possibleCameraLocations.size(); i++) {
    		System.out.println((possibleCameraLocations.get(i)));
    	}
	}
}
