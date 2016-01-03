import java.util.*;

public interface CameraPlacementProblem {

    public int getMaxX ();

    public int getMaxY ();

    public ArrayList<Wall> getExteriorWalls ();

    public ArrayList<Wall> getInteriorWalls ();

    public int getCameraRange ();
}