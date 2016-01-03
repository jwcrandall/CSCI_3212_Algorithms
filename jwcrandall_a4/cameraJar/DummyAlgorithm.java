import java.util.*;

public class DummyAlgorithm implements CameraPlacementAlgorithm {

    // Algorithm should solve and return list of Camera instances
    // as camera locations and orientations.

    public ArrayList<Camera> solve (CameraPlacementProblem problem)
    {
	// INSERT YOUR CODE HERE
    	ArrayList<Camera> cameraList = new ArrayList<>();

	// Place cameras at the four corners, but only show the first two
	// (just to demonstrate that you can selectively view cameras).
	Camera camera = new Camera( new Pointd(1,1),  45, true );
	cameraList.add(camera);
	camera = new Camera( new Pointd(1,problem.getMaxY()-1), 315, true);
	cameraList.add(camera);
	camera = new Camera( new Pointd(problem.getMaxX()-1, 1), 135, true );
	cameraList.add(camera);
	camera = new Camera( new Pointd(problem.getMaxX()-1, problem.getMaxY()-1), 225, true );
	cameraList.add(camera);

    	return cameraList;
    }
}
