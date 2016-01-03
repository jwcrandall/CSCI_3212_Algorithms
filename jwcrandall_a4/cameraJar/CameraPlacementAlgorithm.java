import java.util.*;

public interface CameraPlacementAlgorithm {

    // Algorithm should solve and return list of Camera instances
    // as camera locations and orientations.
    public ArrayList<Camera> solve (CameraPlacementProblem problem);

}
