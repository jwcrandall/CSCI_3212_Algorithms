/**
* Camera class defines a camera object with:
* - location - Pointd coordinates of the camera location on a Wall
* - orientation - angle with respect to the x-axis of central camera view
*
* @author William Edison
* @version 1.02 September 2015
*
*/
public class Camera {

    public Pointd location;
    public int orientation;
    public boolean show = true;

    /**
     * Constructs a Camera object given a side start and end point.
     *
     * @param	location	Pointd location of the camera on a wall
     * @param	orientation	Angle with respect to the x axis in degrees, 0 through 360
     *
     */
    public Camera (Pointd location, int orientation) 
    {
	this.location = location;
	this.orientation = orientation;
	this.show = true;
    }

    public Camera (Pointd location, int orientation, boolean show) 
    {
	this.location = location;
	this.orientation = orientation;
	this.show = show;
    }
    
    /**
     * Returns a String for printing Camera values
     *
     * @return	String for printing Camera field values
     */
    public String toString() {
    	return "location= " + location + ", orientation= " + orientation + " show=" + show;
    }

}
