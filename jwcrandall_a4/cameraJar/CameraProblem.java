import java.util.*;

public class CameraProblem implements CameraPlacementProblem {
    int maxX = 200;
    int maxY = 100;
    int range = 30;
    ArrayList<Wall> exteriorWalls = new ArrayList<> ();
    ArrayList<Wall> interiorWalls = new ArrayList<> ();

    public CameraProblem (int maxX, int maxY, int range)
    {
	this.maxX = maxX;
	this.maxY = maxY;
	this.range = range;
    	exteriorWalls = new ArrayList<> ();
    	// The four major walls:
    	exteriorWalls.add ( new Wall (new Pointd(0,0),  new Pointd(maxX,0)) );
    	exteriorWalls.add ( new Wall (new Pointd(0,maxY-1),  new Pointd(maxX,maxY-1)  ) );
    	exteriorWalls.add ( new Wall (new Pointd(0,0),  new Pointd(0,maxY)) );
    	exteriorWalls.add ( new Wall (new Pointd(maxX-1,0),  new Pointd(maxX-1,maxY) ) );
	
    }

    public int getMaxX ()
    {
    	return maxX;
    }

    public int getMaxY ()
    {
    	return maxY;
    }

    public ArrayList<Wall> getExteriorWalls ()
    {
    	return exteriorWalls;
    }

    public ArrayList<Wall> getInteriorWalls ()
    {
    	return interiorWalls;
    }

    public int getCameraRange ()
    {
    	return range;
    }

}
