/*
Implementation of Kruskal's algorithm mim tree.
Programed by Joseph Crandall
last modifed 11/02/15
*/
import edu.gwu.algtest.*;
import edu.gwu.util.*;
import java.util.*;

public class Kruskal implements SpanningTreeAlgorithm{
	double [][] adjMatrix;
	double [] x; double [] y;
	UnionFindInt unionFinder = new UnionFindInt();
	double weight;

	public java.lang.String getName(){
		return "Joseph Crandall's implementation of Kruskal's algorithm mim tree";
	}

	public void setPropertyExtractor(int algID,
                                 edu.gwu.util.PropertyExtractor prop){}

	//gets the distance between two points
	public double distance(double x1, double y1,double x2, double y2){
		double result = 0;
		result = Math.pow( (x2 - x1), 2) + Math.pow( (y2 - y1), 2);
		result = Math.sqrt(result);
		return result;
	}

	//calculates all the weights and puts them into the adjMatrix
	public double[][] calculateWeights(){
		for(int i=0;i<x.length;i++){
			for(int j = 0;j<x.length;j++){
				if(i == j){
					adjMatrix[i][j] = 0;
				}
				else{
					adjMatrix[i][j] = distance(x[i],y[i],x[j],y[j]);
				}
			}
		}
		return adjMatrix;
	}

	//initializes the arrays, generates the points, and calculates the weights
	public void initialize(int numVertices){
		//System.out.println("initialize "+numVertices+" verticies");
		adjMatrix = new double[numVertices][numVertices];
		x = new double[numVertices];y = new double[numVertices];
		unionFinder.initialize(numVertices);
		weight = 0;

		//might not be working right
		for (int i=0; i < numVertices; i++){
      		x[i] = UniformRandom.uniform();
		}

    	for (int i=0; i < numVertices; i++)
      		y[i] = UniformRandom.uniform();

      	adjMatrix = calculateWeights();
      	//printMatrix(adjMatrix);
	}

	//prints a matrix
	public void printMatrix(double[][] matrix){
		for(int i=0;i<x.length;i++){
			System.out.println("");
			for(int j=0;j<x.length;j++){
				System.out.print(matrix[i][j]+ " ");
			}
		}
		System.out.println("");System.out.println("");
	}

	public void bigArray(double[][] treeMatrix){
		for(int j =0;j<x.length;j++){
			for(int k=0;k<x.length;k++){
				treeMatrix[j][k] = 0;//Double.POSITIVE_INFINITY;
			}
		}
	}

	public double[][] minimumSpanningTree(double[][] adjMatrix){
		List<GraphEdge> sortedEdgeList = new ArrayList<GraphEdge>();
		double[][] treeMatrix = new double[x.length][x.length];
		double min = 1001;
		double[][] dummyMatrix;
		GraphEdge e = new GraphEdge(0,0,0);int i = 0;int j = 0;
		dummyMatrix = new double[x.length][x.length];
		//printMatrix(adjMatrix);
		for(int z = 0;z<x.length;z++){
			for(int q =0;q<x.length;q++){
				dummyMatrix[z][q] = adjMatrix[z][q];
			}
		}
		//System.out.println(adjMatrix[5][6]);
		while(min != 1000){
			min = 1000;
			for(j =0;j<x.length;j++){
				for(int k=0;k<x.length;k++){
					if(dummyMatrix[j][k] < min && dummyMatrix[j][k] != 0){
						min = dummyMatrix[j][k];
						e = new GraphEdge (j, k, dummyMatrix[j][k]);
					}
				}
			}
			i = e.startVertex; j = e.endVertex;
			sortedEdgeList.add(e);
			dummyMatrix[i][j] = 0;dummyMatrix[j][i] =0;
		}

		while(sortedEdgeList.isEmpty() != true){
			e = sortedEdgeList.remove(0);
			//System.out.println(e);

			i = e.startVertex; j = e.endVertex;
			if(unionFinder.find(i) != unionFinder.find(j)){
				unionFinder.union(i,j);
				treeMatrix[i][j] = adjMatrix[i][j];
				treeMatrix[j][i] = adjMatrix[i][j];
				weight = weight + adjMatrix[i][j];
				//System.out.println(i+" "+j+" ("+adjMatrix[i][j]+") ");
			}
		}
		//printMatrix(treeMatrix);
		//getTreeWeight();
		return treeMatrix;

	}

	public GraphVertex[] minimumSpanningTree(GraphVertex[] adjList){
		return null;
	}

	public double getTreeWeight(){
		//System.out.println("getTreeWeight "+weight);

		return weight;
	}


	public static void main(String [ ] args)
	{
		Kruskal treeFinder = new Kruskal();
		int start = 10;
		double average = 0;
		int count = 0;

		while(start < 101){
			count = 0;
			average = 0;
			while(count < 100){
				treeFinder.initialize(start);
				treeFinder.minimumSpanningTree(treeFinder.adjMatrix);
				average += treeFinder.getTreeWeight();
				count++;
			}
			average = average/100;
			System.out.println(average);
			start+=10;
		}

		/*for(int i=0;i<10;i++){
			System.out.println(treeFinder.x[i]+", "+treeFinder.y[i]);
		}*/

	}
}