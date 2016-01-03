/*
Binary Search Tree
Programed by Joseph Crandall
Last modifed September 28, 2015
*/
import edu.gwu.algtest.*;
import edu.gwu.debug.*;
import edu.gwu.util.*;
import java.util.*;
import java.lang.*;
//NOTE keys are unique in this BST, no duplicates
//Tree node class has the following fields has the following parameters
//public TreeNode(java.lang.Comparable key,java.lang.Object value,TreeNode left,TreeNode right,TreeNode parent)
public class SelfAdjTree extends BinarySearchTree {

	//edu.gwu.algtest.Algorithm
	//get name
	public java.lang.String getName(){
 		return "Joseph Crandall's implementation of Self Adjusting Binary Search Tree";
 	}



	//the reursive insert used in the insert method
	public Object recursiveInsert(TreeNode inrec,Comparable keyin,Object valuein ){
		//System.out.println("inside recursive insert");
		int i = keyin.compareTo(inrec.key);
		//insert left
		if(i < 0){
			if(inrec.left == null){
				//System.out.println("left");
								//System.out.println("line above splaystep call");

				TreeNode inLeft =  new TreeNode();
				inrec.left = inLeft; // linkeding down
				inLeft.key = keyin;
				inLeft.value = valuein;
				inLeft.left = null;
				inLeft.right = null;
				inLeft.parent = inrec; // linking up //this is like a doubly linked list with a tree

				//System.out.println(inLeft.key);

				//!!!RUN SPLAYSTEP!!!//
				this.splayStep(inLeft);

				//return(inLeft.value);
				valuein = (inLeft.value);
			}
			else{
				//insert left recursively
				recursiveInsert(inrec.left, keyin, valuein);
			}
		}
		//insert right
		if(i > 0){
			if(inrec.right == null){
				//System.out.println("right");

					System.out.println("line above splaystep call");

				TreeNode inRight = new TreeNode();
				inrec.right = inRight; // linking down
				inRight.key = keyin;
				inRight.value = valuein;
				inRight.left = null;
				inRight.right = null;
				inRight.parent = inrec; // linking up


				//System.out.println(inRight.key);


				//!!!RUN SPLAYSTEP!!!//
				this.splayStep(inRight);

				//return(inRight.value);
				valuein = (inRight.value);
			}
			else{
				//insert right recursively
				recursiveInsert(inrec.right, keyin, valuein);
			}
		}
		//else
		if(i == 0){
		//replace duplicate
			System.out.println("duplicate value NOT KEY");

			System.out.println("line above splaystep call");

			TreeNode temp = new TreeNode();
			temp.value = inrec.value;
			inrec.value = valuein;

			//System.out.println(inrec.value);



			//!!!RUN SPLAYSTEP!!!//
			this.splayStep(inrec);

			//return(temp.value);
			valuein = temp.value;
		}
		return(valuein); // this is not correct, just needed to put something here so that the code compiles
	}

	//searchRecursive
	public ComparableKeyValuePair searchRecursive(TreeNode node,Comparable key, ComparableKeyValuePair searchResult){
		//System.out.println("inside print");
		System.out.println("inside search recursive");
		if (node.left != null){
			searchRecursive(node.left, key, searchResult);
		}
		//if a key is found that is equal to the search key a new ComparableKeyValuePair is created and returned
		int i = key.compareTo(node.key);
		if (i == 0){
			//ComparableKeyValuePair searchResult = new ComparableKeyValuePair();
			searchResult.key = node.key;
			searchResult.value = node.value;
			//blank = searchResult;
			System.out.println("key found:" + searchResult.key + " value found" +  searchResult.value);
			//calling Splay Step
			System.out.println("calling Splay Step");

			this.splayStep(node);

			//System.out.println(searchResult.value);
			//return(searchResult);
		}
		if (node.right != null){
			searchRecursive(node.right, key, searchResult);
		}
		//if no key value maches the search key value the method returns null
		return (searchResult);
	}









































	// I have to figure out how to place bith the value and the key in a comparablekey value pair when search is over.

	public void splayStep(TreeNode targetNode){  //only think I need to node ComparableKeyValuePair step,
		if(targetNode == getRoot()){
			return;
		}

		do{
			System.out.println("splayStep is running");


			//if(targetNode.parent != null){
				TreeNode parent = targetNode.parent;
			//}
			//if(targetNode.parent.parent != null){
				TreeNode grandparent = targetNode.parent.parent;
			//}

			//VERY IMPORTANT
			//rotating left way = 0
	 		//rotating right way = 1

			//case1a
			//target node is the root's left child
			if (root.left == targetNode){
				System.out.println("case 1a running");
				rotateParent(targetNode, parent, 1);

			}
			//case1b
			//target node is the root's right child:
			else if (root.right == targetNode){
				System.out.println("case 1b running");
				rotateParent(targetNode, parent, 0);
			}
			//case 2
			//the target and the target's parent are both left children
			else if(parent.left == targetNode && grandparent.left.left == targetNode){
				System.out.println("case 2 running");
				rotateParent(targetNode, parent, 1);
				rotateParent(targetNode, grandparent, 1);

			}
			//case 3
			//the target and the target's parent are both right children
			else if(parent.right == targetNode && grandparent.right.right == targetNode){
				System.out.println("case 3 running");
				rotateParent(targetNode, parent, 0);
				rotateParent(targetNode, grandparent, 0);
			}
			//case 4
			//the target is a right child, the parent is a left child.
			else if(parent.right == targetNode && grandparent.left.right == targetNode){
				System.out.println("case 4 running");
				rotateParent(targetNode, parent, 0);
				rotateParent(targetNode, grandparent, 1);
			}
			//case 5
			//the target is a left child, the parent is a right child.
			else if(parent.left == targetNode && grandparent.right.left == targetNode){
				System.out.println("case 5 running");
				rotateParent(targetNode, parent, 1);
				rotateParent(targetNode, grandparent, 0);
			}
	}while (targetNode != getRoot());
}







	public void rotateParent(TreeNode targetNode , TreeNode parent, int way){
		System.out.println("inside rotate parent");
		if (way == 1){
			System.out.println("inside rotate right");
			//rotate subtree pointer & simultaneously break the parent pointer to targetNode
			if(targetNode.right == null){
				parent.left = null;
			}else{
			parent.left = targetNode.right;
			}
			//point the target node to where the parent node was previously pointed
			if(parent.parent != null){
				targetNode.parent = parent.parent;
			}else{
				root = targetNode;
				System.out.println("the new root is " + getRoot().key + "very nice");
			}

			//point the targetNode down to the parent
			//this is excesive
			//parent = targetNode.right;

			//point the parent up to the targetNode
			targetNode = parent.parent;
			return;
		}
		if 	(way == 0){
			System.out.println("inside rotate left");
			//rotate subtree pointer & simultaneously break the parent pointer to targetNode
			if(targetNode.left == null){
				parent.right = null;
			}else{
			parent.right = targetNode.left;
			}
			//point the target node to where the parent node was previously pointed
			if(parent.parent != null){
				targetNode.parent = parent.parent;
			}else{
				root = targetNode;
				System.out.println("the new root is " + getRoot().key + "very nice");
			}
			//point the targetNode down to the parent
			//this is excesive
			//parent = targetNode.left;

			//point the parent up to the targetNode
			targetNode = parent.parent;
			return;
		}
	}


	public static void main(String[] args) {

		//System.out.println("The main is runing");

		//System.out.println("test insert SAT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		SelfAdjTree SAT = new SelfAdjTree();

		Comparable testComp = 11;
		Object testObj = 6;
		SAT.insert(testComp, testObj);
		//System.out.println("the new root is " + SAT.getRoot().key);


		SAT.leftNodeRightPrint(SAT.getRoot());


		//System.out.println("insert in SAT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		//System.out.println("the old root is " + SAT.getRoot().key);
		Comparable testComp2 = 10;
		Object testObj2 = 32;
		SAT.insert(testComp2, testObj2);
		//System.out.println("the new root is " + SAT.getRoot().key);


		SAT.leftNodeRightPrint(SAT.getRoot());



		//System.out.println("insert in SAT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("the old root is " + SAT.getRoot().key);
		// Comparable testComp3 = 2;
		// Object testObj3 = 87;
		// SAT.insert(testComp3, testObj3);
		// System.out.println("the current root is " + SAT.getRoot().key);



		// SAT.leftNodeRightPrint(SAT.getRoot());



		// //System.out.println("insert in SAT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// Comparable testComp4 = 886;
		// Object testObj4 = 1342;
		// SAT.insert(testComp4, testObj4);
		// System.out.println("the current root is " + SAT.getRoot().key);

		// SAT.leftNodeRightPrint(SAT.getRoot());





















		// System.out.println("print BST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// TreeNode print = SAT.getRoot();
		// SAT.leftNodeRightPrint(print);
		// System.out.println("the current root is " + root.key);


		// Comparable testComp5 = 7;
		// Object testObj5 = 9;
		// SAT.insert(testComp5, testObj5);
		// System.out.println("the current root is " + SAT.getRoot().key);


		// //System.out.println("testing PARENT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		// // Comparable testcompinset6 = 857;
		// // Object testObjinsert6 = 994;
		// // BST.insert(testcompinset6,testObjinsert6);

		// //try printing a left node here

		// System.out.println("print BST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// BST.leftNodeRightPrint(root);
		// System.out.println("the current root is " + root.key);

		// //how are we suppose to print the entire tree, should we use

		// //System.out.println(BST.root.value);

		// //testing search when object exsists

		// //testing non null search case
		// System.out.println("testing search BST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// Comparable testCompser6 = 2;
		// ComparableKeyValuePair searchtest = BST.search(testCompser6);
		// //Object st = searchtest.value;
		// System.out.println(searchtest);

		// //System.out.print("searched key : " + searchtest.key + " found value : " + searchtest.value);
		// //System.out.print(toString(searchtest.key));

		// Comparable testComp7 = 23;
		// System.out.println("null search result test!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// System.out.println(BST.search(testComp7));

		// // //testing non null search case
		// // ComparableKeyValuePair searchtest2 = BST.search(testComp2);

		// System.out.println("testing getCurrent size!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// int sizetest = BST.getCurrentSize();
		// System.out.println(sizetest);


		// System.out.println("testing min!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// ComparableKeyValuePair mintest = BST.minimum();
		// //int sizetest = BST.getCurrentSize();
		// System.out.println("min key: " + mintest.key + "	min value:" + mintest.value);

		// System.out.println("testing max!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// ComparableKeyValuePair maxtest = BST.maximum();
		// //int sizetest = BST.getCurrentSize();
		// System.out.println("max key: " + maxtest.key + "	max value: " + maxtest.value);

		// // System.out.println("testing getnode!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// // System.out.println("looking for the predecessor of " + testComp5);

		// // TreeNode root = new TreeNode();
		// // root = BST.getRoot();
		// // TreeNode returnNode = new TreeNode();
		// // //TreeNode node = getNode(getRoot(),key, returnNode);
		// // TreeNode nodetest = BST.getNode(root,testComp5,returnNode);
		// // //int sizetest = BST.getCurrentSize();

		// // System.out.println("the predecessor of key : " + testComp5 + "	is key " + predtest);
		// // System.out.println("testing predicessor!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// // System.out.println("looking for the predecessor of " + testComp5);
		// // Comparable predtest = BST.predecessor(testComp5);
		// // //int sizetest = BST.getCurrentSize();
		// // System.out.println("the predecessor of key : " + testComp5 + "	is key " + predtest);

	}
}

