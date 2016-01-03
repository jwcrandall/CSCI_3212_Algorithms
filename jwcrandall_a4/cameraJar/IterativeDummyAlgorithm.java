import java.util.*;

public class IterativeDummyAlgorithm implements CameraPlacementAlgorithm {

    // Algorithm should solve and return list of Camera instances
    // as camera locations and orientations.

    public ArrayList<Camera> solve (CameraPlacementProblem problem) 
    {
	// INSERT YOUR CODE HERE
    	ArrayList<Camera> cameraList = new ArrayList<>();

	int needToCover = (problem.getMaxX()-2) * (problem.getMaxY()-2);
	System.out.println ("Start: needToCover=" + needToCover);

	// First camera at bottom left.
	CameraPlacementResult result = null;
	cameraList.add ( new Camera ( new Pointd (1,1),  45) );

	// Now iteratively along bottom wall.
	for (int i=2; i<=problem.getMaxX()-1; i++) {
	    // Place and see.
	    // The angle is interpolated between 45 and 135.
	    int angle = intermediateAngle (1,problem.getMaxX()-1, 45,135, i);
	    cameraList.add (new Camera(new Pointd (i,1),  angle));
	    result = CameraPlacement.evaluatePlacement (problem, cameraList);
	    if (result.numIllegalPlacements > 0) {
		// Something went wrong.
		System.out.println ("Bottom row: i=" + i + " illegal");
		System.out.println (result);
	    }
	    else {
		System.out.println ("After i=" + i + " on bottom row: needToCover=" + result.numNotCovered);
		if (result.numNotCovered == 0) {
		    // Done.
		    return cameraList;
		}
	    }
	}

	// Next, the right side.
	cameraList.add (new Camera(new Pointd (problem.getMaxX()-1, 1), 135));
	for (int i=2; i<=problem.getMaxY()-1; i++) {
	    int angle = intermediateAngle (1,problem.getMaxY()-1, 135,225, i);
	    cameraList.add (new Camera(new Pointd (problem.getMaxX()-1,i), angle));
	    result = CameraPlacement.evaluatePlacement (problem, cameraList);
	    if (result.numIllegalPlacements > 0) {
		// Something went wrong.
		System.out.println ("Right side: i=" + i + " illegal");
		System.out.println (result);
	    }
	    else {
		System.out.println ("After i=" + i + " on right side: needToCover=" + result.numNotCovered);
		if (result.numNotCovered == 0) {
		    // Done.
		    return cameraList;
		}
	    }
	}

	// Next, top row (anti-clockwise)
	cameraList.add (new Camera (new Pointd (problem.getMaxX()-2,problem.getMaxY()-1), 225));
	for (int i=problem.getMaxX()-3; i>=1; i--) {
	    // Place and see.
	    int angle = intermediateAngle (1,problem.getMaxX()-1, 315,225, i);
	    cameraList.add (new Camera(new Pointd (i,problem.getMaxY()-1),  angle));
	    result = CameraPlacement.evaluatePlacement (problem, cameraList);
	    if (result.numIllegalPlacements > 0) {
		// Something went wrong.
		System.out.println ("Top row: i=" + i + " illegal");
		System.out.println (result);
	    }
	    else {
		System.out.println ("After i=" + i + " on top row: needToCover=" + result.numNotCovered);
		if (result.numNotCovered == 0) {
		    // Done.
		    return cameraList;
		}
	    }
	}

	// Left side, anti-clockwise.
	cameraList.add (new Camera(new Pointd (1, problem.getMaxY()-2), 315));
	for (int i=problem.getMaxY()-3; i>=2; i--) {
	    int angle = intermediateAngle (1,problem.getMaxY()-1, 45,-45, i);
	    if (angle < 0) angle+=360;
	    cameraList.add (new Camera(new Pointd (1,i), angle));
	    result = CameraPlacement.evaluatePlacement (problem, cameraList);
	    if (result.numIllegalPlacements > 0) {
		// Something went wrong.
		System.out.println ("Left side: i=" + i + " illegal");
		System.out.println (result);
	    }
	    else {
		System.out.println ("After i=" + i + " on left side: needToCover=" + result.numNotCovered);
		if (result.numNotCovered == 0) {
		    // Done.
		    return cameraList;
		}
	    }
	}

    	return cameraList;
    }
    
    int intermediateAngle (int p1, int p2, int q1, int q2, int p)
    {
	// At p1, the angle is q1. At p2, the angle is q2.
	// What should the angle be at p? Linear interpolation.
	double q = q1 + ((q2-q1)*(p-p1)) / (double)(p2-p1);
	return (int)q;
    }
}

