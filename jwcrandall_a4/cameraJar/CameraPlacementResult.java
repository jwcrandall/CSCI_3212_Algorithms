
public class CameraPlacementResult {

    public int numIllegalPlacements;
    public int numCovered;
    public int numNotCovered;
    public double percentCovered;
    public String resultString;
    public int[][] cover;

    public String toString ()
    {
	return resultString;
    }

}
