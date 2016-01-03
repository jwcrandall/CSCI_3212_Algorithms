public class CameraPlacementTest {

    public static void main (String[] argv)
    {
	demo1();
	//demo2 ();
	//smallSizeTestCases ();
	//largeSizeTestCases ();
    }

    static void demo1 ()
    {
		// Three ways to use CameraPlacement:

		// 1. Use it to merely display a problem:
		CameraPlacementProblem problem = CameraProblemFactory.getProblem ("small", 0);
		CameraPlacement.displayProblem (problem);

		// 2. Run an algorithm on a problem WITHOUT display.
		// Running without display is useful for iterative algorithms.

		CameraPlacementAlgorithm alg = new JwcrandallAlgorithm ();
		CameraPlacementResult result = CameraPlacement.runAlgorithm (alg, problem, false, false);
		System.out.println (result);

		// 3. Run an algorithm on a problem WITH display NOT showing cameras,
		//    but showing coverage.
		System.out.println("run with display");
		result = CameraPlacement.runAlgorithm (alg, problem, true, false);
		System.out.println (result);

		// 4. Run an algorithm on a problem WITH display AND showing cameras.
		System.out.println("run with display");
		result = CameraPlacement.runAlgorithm (alg, problem, true, true);
		System.out.println (result);
    }

    static void demo2 ()
    {
		// Here we'll merely test IterativeDummyAlgorithm, without display.
		int problemNum = 1;
		CameraPlacementProblem problem = CameraProblemFactory.getProblem ("big", problemNum);
		CameraPlacementAlgorithm alg = new IterativeDummyAlgorithm ();
		CameraPlacementResult result = CameraPlacement.runAlgorithm (alg, problem, true, true);
    }

    static void smallSizeTestCases ()
    {
		int numProblems = CameraProblemFactory.getNumSmall ();
		for (int problemNum=0; problemNum<numProblems; problemNum++) {
		    // Replace DummyAlgorithm with your algorithm.
		    CameraPlacementAlgorithm alg = new DummyAlgorithm ();

		    CameraPlacementProblem problem = CameraProblemFactory.getProblem ("small", problemNum);
		    CameraPlacementResult result = CameraPlacement.runAlgorithm (alg, problem, false, false);
		    System.out.println ("RESULTS on small size problem# " + problemNum);
		    System.out.println (result);
		}
    }

    static void largeSizeTestCases ()
    {
		int numProblems = CameraProblemFactory.getNumLarge ();
		for (int problemNum=0; problemNum<numProblems; problemNum++) {
		    // Replace DummyAlgorithm with your algorithm.
		    CameraPlacementAlgorithm alg = new DummyAlgorithm ();

		    CameraPlacementProblem problem = CameraProblemFactory.getProblem ("small", problemNum);
		    CameraPlacementResult result = CameraPlacement.runAlgorithm (alg, problem, false, false);
		    System.out.println ("RESULTS on large size problem# " + problemNum);
		    System.out.println (result);
		}
    }

}
