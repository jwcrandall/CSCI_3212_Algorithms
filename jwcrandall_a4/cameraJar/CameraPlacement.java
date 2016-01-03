// CameraPlacement
//
// Author: Rahul Simha, Bill Edison
// November 2015


import java.util.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;


public class CameraPlacement extends JPanel {

    // GUI stuff.
    CameraPlacementAlgorithm alg;
    CameraPlacementProblem problem;
    boolean showDisplay = false;
    boolean showCameras = false;

    // Problem data (for drawing).
    int maxX=0, maxY=0;
    ArrayList<Wall> walls;
    ArrayList<Camera> cameraList;
    Color[][] grid;
    int inset = 20;

    ////////////////////////////////////////////////////////////////////////
    // GUI and Drawing

    public CameraPlacement (CameraPlacementAlgorithm alg,
			    CameraPlacementProblem problem,
			    boolean showDisplay,
			    boolean showCameras)
    {
	this.alg = alg;
	this.problem = problem;
	this.showDisplay = showDisplay;
	this.showCameras = showCameras;
	init ();
    }

    void init ()
    {
	maxX = problem.getMaxX();
	maxY = problem.getMaxY();
	walls = new ArrayList<Wall> ();

	// Get exterior walls.
	ArrayList<Wall> exteriorWalls = problem.getExteriorWalls();
	for (Wall w: exteriorWalls) {
	    walls.add (w);
	}

	// Get additional interior walls.
	ArrayList<Wall> interiorWalls = problem.getInteriorWalls();
	for (Wall w: interiorWalls) {
	    // Check if legal: endpoints have to be within the outerwalls.
	    if ( (w.start.x < 1) || (w.start.x >= maxX)
		 || (w.end.x < 1) || (w.end.x >= maxX)
		 || (w.start.y < 1) || (w.start.y >= maxY)
		 || (w.end.y < 1) || (w.end.y >= maxY) ) {
		// Illegal.
		System.out.println ("ERROR: Illegal interior wall coords: " + w);
		System.exit (0);
	    }

	    //** Also need to check that no two walls intersect.
	    // Otherwise add.
	    walls.add (w);
	}

	grid = new Color [maxX][maxY];
	for (int i=0; i<maxX; i++) {
	    for (int j=0; j<maxY; j++) {
	    	grid[i][j] = Color.white;
	    }
	}

	// Now set grid colors for the walls.
	for (Wall w: walls) {
	    if (w.start.x == w.end.x) {
		// The y values need to be filled.
		for (int y=(int)w.start.y; y<(int)w.end.y; y++) {
		    grid[(int)w.start.x][y] = Color.gray;
		}
	    }
	    else if (w.start.y == w.end.y) {
		// The x values need to be filled.
		for (int x=(int)w.start.x; x<(int)w.end.x; x++) {
		    grid[x][(int)w.start.y] = Color.gray;
		}
	    }
	}
    }

    void makeFrame ()
    {
	JFrame frame = new JFrame ();
	frame.setSize (1000, 700);
	frame.setTitle ("Camera Placement");
	Container cPane = frame.getContentPane();
	cPane.add (this, BorderLayout.CENTER);
	frame.setVisible (true);
    }

    public void paintComponent (Graphics g)
    {
	if (! showDisplay) {
	    return;
	}
	super.paintComponent (g);

	Graphics2D g2 = (Graphics2D) g;

	// Clear.
	Dimension D = this.getSize();
	g.setColor (Color.white);
	g.fillRect (0,0, D.width,D.height);

	if (problem == null) {
	    return;
	}

	// Grid squares first.
	int gridSizeX = (D.width - 2*inset) / maxX;
	int gridSizeY = (D.height - 2*inset) / maxY;
	int gridSize = Math.min (gridSizeX, gridSizeY);
	for (int x=0; x<maxX; x++) {
	    for (int y=0; y<maxY; y++) {
		g.setColor (grid[x][y]);
		int topLeftJavaX = x * gridSize + inset;
		int topLeftJavaY = D.height-inset - y*gridSize - gridSize;
		g.fillRect (topLeftJavaX,topLeftJavaY, gridSize, gridSize);
	    }
	}

	// Now we'll draw grid lines so that the squares are seen.
	g.setColor (Color.black);
	for (int x=0; x<=maxX; x++) {
	    int x1 = x * gridSize;
	    int y1 = 0;
	    int y2 = maxY * gridSize;
	    g.drawLine (inset+x1, D.height-inset-y1, inset+x1, D.height-inset-y2);
	}
	for (int y=0; y<=maxY; y++) {
	    int x1 = 0;
	    int x2 = maxX * gridSize;
	    int y1 = y*gridSize;
	    g.drawLine (inset+x1, D.height-inset-y1, inset+x2, D.height-inset-y1);
	}

	// Draw camera points
	if ((cameraList == null) || (!showCameras)) {
	    return;
	}

	for (Camera camera : cameraList) {
	    if (! camera.show) {
		continue;
	    }
	    int cameraSize = 10;
	    int bottomLeftJavaX = (int)camera.location.x * gridSize + inset - cameraSize/2;
	    int bottomLeftJavaY = D.height-inset - (int)camera.location.y*gridSize - cameraSize/2;
	    g.setColor(Color.BLUE);
	    g.fillOval(bottomLeftJavaX, bottomLeftJavaY, cameraSize, cameraSize);

	    // Draw camera orientation line
	    int x1 = (int)camera.location.x * gridSize + inset;
	    int y1 = D.height-inset - (int)camera.location.y*gridSize;
	    Pointd ce =  CameraGeometry.findBoundaryEnd(camera, camera.orientation, maxX, maxY);
	    int x2 = (int)ce.x * gridSize + inset;
	    int y2 = D.height - inset - (int)ce.y*gridSize;
	    g2.setStroke(new BasicStroke(2));
	    g2.drawLine(x1, y1, x2, y2);

	    // Draw camera boundary lines
	    int wallType = CameraGeometry.findWallType(camera.location,
						       problem.getExteriorWalls(),
						       problem.getInteriorWalls(),
						       maxX, maxY);
	    int range = problem.getCameraRange();

	    // Draw camera left boundary line
	    int baLeft = CameraGeometry.findBoundaryAngle(range, camera, wallType, true);
	    Pointd beLeft = CameraGeometry.findBoundaryEnd(camera, baLeft, maxX, maxY);
	    int beLeftX = (int)(beLeft.x*gridSize + inset);
	    int beLeftY = (int)(D.height - beLeft.y*gridSize - inset);
	    //int beLeftX = (int)beLeft.x*gridSize + inset;
	    //int beLeftY = D.height - inset - (int)beLeft.y*gridSize;
	    g2.setColor(Color.green);
	    g2.drawLine(bottomLeftJavaX+cameraSize/2, bottomLeftJavaY+cameraSize/2,
			beLeftX, beLeftY);

	    //Draw camera right boundary line
	    int baRight = CameraGeometry.findBoundaryAngle(range, camera, wallType, false);
	    Pointd beRight = CameraGeometry.findBoundaryEnd(camera, baRight, maxX, maxY);
	    int beRightX = (int)(beRight.x*gridSize + inset);
	    int beRightY = (int)(D.height - beRight.y*gridSize - inset);
	    //int beRightX = (int)beRight.x*gridSize + inset;
	    //int beRightY = D.height - inset - (int)beRight.y*gridSize;
	    g2.setColor(Color.green);
	    g2.drawLine(bottomLeftJavaX+cameraSize/2, bottomLeftJavaY+cameraSize/2,
			beRightX, beRightY);
	}
    }


    ////////////////////////////////////////////////////////////////////////
    // static methods.

    public static void displayProblem (CameraPlacementProblem problem)
    {
	CameraPlacement cam = new CameraPlacement (null, problem, true, false);
	cam.makeFrame ();
    }

    public static CameraPlacementResult evaluatePlacement (CameraPlacementProblem problem, ArrayList<Camera> cameraList)
    {
	CameraPlacementResult camResult = new CameraPlacementResult ();
	String results = null;

	for (Camera camera : cameraList) {
	    int wallType = CameraGeometry.findWallType (camera.location,
				   problem.getExteriorWalls(),
				   problem.getInteriorWalls(),
				   problem.getMaxX(),
				   problem.getMaxY());
	    if (wallType < 0) {
		results = results + "\nInvalid camera location: " + camera.toString();
		camResult.numIllegalPlacements ++;
	    }
	}

	if (results != null) {
	    // Invalid camera placement. No results displayed.
	    camResult.resultString = results;
	    return camResult;
	}

	// Otherwise, compute the cover.
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
	camResult.percentCovered = 100.0*(double) camResult.numCovered/(double)total;
	String coverSummary = "\nTotal tiles= " + total +
	    ", # covered= " + camResult.numCovered +
	    ", # not Covered= " + camResult.numNotCovered +
	    ", % covered= " + camResult.percentCovered;
	camResult.resultString = coverSummary + "\n" + results;

	return camResult;
    }


    public static CameraPlacementResult runAlgorithm (
				           CameraPlacementAlgorithm alg,
					   CameraPlacementProblem problem,
					   boolean showDisplay,
					   boolean showCameras)
    {
	CameraPlacement cam = new CameraPlacement (alg, problem, showDisplay, showCameras);

	cam.cameraList = alg.solve (problem);

	CameraPlacementResult camResult;

	if (cam.cameraList == null) {
	    camResult = new CameraPlacementResult ();
	    camResult.resultString = "No cameras defined";
	    // No results displayed.
	    return camResult;
	}

	camResult = evaluatePlacement (problem, cam.cameraList);
	System.out.println (camResult);

	if (showDisplay) {
	    for (int i = 0; i < problem.getMaxX(); i++) {
		for (int j = 0; j < problem.getMaxY(); j++) {
		    if (camResult.cover[i][j] == 1) {
			cam.grid[i][j] = Color.red;
		    }
		}
	    }
	    cam.makeFrame ();
	}

	return camResult;
    }


}
