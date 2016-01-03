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

public class BinarySearchTree implements TreeSearchAlgorithm {

	//int count = 0;

	public TreeNode root;
	//Interface TreeSearchAlgorithm
	public TreeNode getRoot(){
		return(root); //the root
	}

	//All Superinterfaces: edu.gwu.algtest.Algorithm, edu.gwu.algtest.OrderedSearchAlgorithm, edu.gwu.algtest.SearchAlgorithm

	//edu.gwu.algtest.SearchAlgorithm
	// initialize
	public void initialize(int maxSize){
		//TreeNode root = getRoot();
		root = null;
	}

	//getCurrentSize
	public int getCurrentSize(){
		TreeNode root = getRoot();
		return(countSize(root));
	}
	// used in geCurrentSize
	int countSize(TreeNode node){
        if( node == null){
            return(0);
        }
        else{
        	//root only case
            if( node.left == null && node.right == null){
                return(1);
            }
            //count the left and right children recusivly
            else{
            	return(1 + (countSize(node.left) + countSize(node.right)));
       	    }
        }
	}

	//edu.gwu.algtest.Algorithm
	//get name
	public java.lang.String getName(){
 		return "Joseph Crandall's implementation of Binary Search Tree";
 	}
 	//get property extractor
	public void setPropertyExtractor(int algID,edu.gwu.util.PropertyExtractor prop){
	//empty implementations, method definition empty body
	}

	//comprable java.lang.Comparable
	//edu.gwu.algtest.OrderedSearchAlgorithm
	//insert
	public Object insert(Comparable keyin,Object valuein){

		//creating the root if this is the first value to be inserted
		if (root == null){
			//System.out.println("inserting root");
            root = new TreeNode();  // do not initialize a new root every time this is wrong TreeNode root = new TreeNode();
            //TreeNode in =  new TreeNode();
			root.key = keyin;				//might not need these
			root.value = valuein;			//or these
			root.left = null;
			root.right = null;
			root.parent = null;
			//System.out.println(root.key);
		 	return (root.value);
		}
		else{
			//System.out.println("recursive insert");
			return(recursiveInsert(root, keyin, valuein));
		}
	}

	//the reursive insert used in the insert method
	public Object recursiveInsert(TreeNode inrec,Comparable keyin,Object valuein ){
		//System.out.println("inside recursive insert");
		int i = keyin.compareTo(inrec.key);
		//insert left
		if(i < 0){
			if(inrec.left == null){
				//System.out.println("left");
				TreeNode inLeft =  new TreeNode();
				inrec.left = inLeft; // linkeding down
				inLeft.key = keyin;
				inLeft.value = valuein;
				inLeft.left = null;
				inLeft.right = null;
				inLeft.parent = inrec; // linking up //this is like a doubly linked list with a tree
				//System.out.println(inLeft.key);
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
				TreeNode inRight = new TreeNode();
				inrec.right = inRight; // linking down
				inRight.key = keyin;
				inRight.value = valuein;
				inRight.left = null;
				inRight.right = null;
				inRight.parent = inrec; // linking up
				//System.out.println(inRight.key);
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
			//System.out.println("duplicate value NOT KEY");
			TreeNode temp = new TreeNode();
			temp.value = inrec.value;
			inrec.value = valuein;
			//System.out.println(inrec.value);
			//return(temp.value);
			valuein = temp.value;
		}
		return(valuein); // this is not correct, just needed to put something here so that the code compiles
	}

	//print Use Left Node Right Print
	public void leftNodeRightPrint(TreeNode node){
		//System.out.println("inside print");
		if (node.left != null){
			leftNodeRightPrint(node.left);
		}
		System.out.println("key : " + node.key + " value : " + node.value);
		if (node.right != null){
			leftNodeRightPrint(node.right);
		}
	}

	//search
	public ComparableKeyValuePair search(Comparable key){
		//System.out.println("inside search");
		ComparableKeyValuePair searchResult = new ComparableKeyValuePair();
			//searchResult.key = null;
			//searchResult.value = null;
		if(getRoot() == null){
			return(null);
		}
		searchResult = (searchRecursive(getRoot(),key, searchResult));  //there is a better way
		if(searchResult.key == null){
			return null;
		}
		return searchResult;
	}

	//searchRecursive
	public ComparableKeyValuePair searchRecursive(TreeNode node,Comparable key, ComparableKeyValuePair searchResult){
		//System.out.println("inside print");
		//System.out.println("inside search recursive");
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
			//System.out.println("key found:" + searchResult.key + " value found" +  searchResult.value);
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

	//minimum
	public ComparableKeyValuePair minimum(){
		//System.out.println("inside minimum");

		//get root
		root = getRoot();
		//root null case
		if(root == null){
			//System.out.println("root null case");
			return(null);
		}
		//initilize the return value
		ComparableKeyValuePair min = new ComparableKeyValuePair();
		//System.out.println("past root null case");
		//root left null clase
		if (root.left == null){
			//ComparableKeyValuePair min = new ComparableKeyValuePair();
			//System.out.println("root is min");
			min.key = root.key;
			min.value = root.value;
			//System.out.println("root is min : " + min.key);

			//System.out.print(min.key);
			//return(minroot);
		}
		if(root.left != null){
			//ComparableKeyValuePair min = new ComparableKeyValuePair();
			min = minimumRecursive(root.left, min);
		}
		return(min);
		//error case, should never be returned
		//return (null);
	}

	//minimum recursive method
	public ComparableKeyValuePair minimumRecursive(TreeNode node, ComparableKeyValuePair min){
		//System.out.println("inside minimum recursive");
		//recursing further left
		if (node.left != null){
			//System.out.println("inside minimum recursive going left");
			minimumRecursive(node.left, min);
		}

		//setting the min key value pair
		if (node.left == null){
			//System.out.print(root.left);
			//ComparableKeyValuePair min = new ComparableKeyValuePair();
			min.key = node.key;
			min.value = node.value;
			//return(min);
		}
		return(min);
	}

	//maximum
	public ComparableKeyValuePair maximum(){
		//System.out.println("inside maximum");
		//get root
		root = getRoot();
		//root null case
		if(root == null){
			//System.out.println(" root null case");
			return(null);
		}
		//System.out.println("past root null case");
		//initilize the return value
		ComparableKeyValuePair max = new ComparableKeyValuePair();
		//root left null clase
		if (root.right == null){
			//System.out.println("root is max");
			//ComparableKeyValuePair maxroot = new ComparableKeyValuePair();
			max.key = root.key;
			max.value = root.value;
			//System.out.println("root case");
			//return(maxroot);
		}
		//System.out.println("past root case");
		//recursive if root left not null
		if (root.right != null){
		//ComparableKeyValuePair max = new ComparableKeyValuePair();
			max = maximumRecursive(root.right, max);
		}

		//error case, should never be returned
		return (max);
	}
	//maximum recursive method
	public ComparableKeyValuePair maximumRecursive(TreeNode node, ComparableKeyValuePair max){
		//System.out.println("inside maximum recursive");
		if (node.right != null){
			//System.out.println("inside maximum recursive going right");
			//System.out.println(node.right.key);
			maximumRecursive(node.right, max);
		}
		//ComparableKeyValuePair max = new ComparableKeyValuePair();
		if (node.right == null){
			max.key = node.key;
			max.value = node.value;
			//System.out.print(max.key);
			//System.out.println("found max : " + max.key);
		}
		return(max);
	}

	//maximum recursive method wich returns TreeNode
	public TreeNode maximumRecursiveTN(TreeNode node, ComparableKeyValuePair max){
		if (root.right != null){
			maximumRecursive(root.right, max);
		}
		return(node);
	}

	//delete and return the object from the deleted node
	public Object delete(Comparable key){
		//System.out.println("the key to be deleted : " + key);

		if(search(key).key == null){
			System.out.println("key does not exist case, return null object");
			return (null);
		}
		//Object deletedValue = null;
		TreeNode temp = new TreeNode(key, null);
		return (deleteRecursive(temp,getRoot()));

		//deletedValue = deleteRecursive(temp,getRoot());
		//return deletedValue;
	}

	//recursive delete
	public Object deleteRecursive(TreeNode node, TreeNode start){
		Comparable startKey = start.key;
		Object deletedValue = null;

		if(startKey.compareTo(node.key)>0){
			//recurse left
			deletedValue = deleteRecursive(node, start.left);
		}
		else if(startKey.compareTo(node.key)<0){
			//recurse right
			deletedValue = deleteRecursive(node, start.right);
		}
		else if (startKey.compareTo(node.key)==0){
			//tree node start.value is the node to be deleted and value removed
			deletedValue = start.value;


			if(startKey.compareTo(getRoot().key)==0){
				//System.out.println("delete the node " + getRoot().key);

				//node to be deleted is root of tree (null parent)
				//System.out.println("**Deleting: (Key = "+startKey+" = root)");

				if(start.left == null && start.right == null){
					//node to be deleted has 0 children
					start = null;
					root = null;
				}
				else if(start.left == null){
					//node to be deleted only has a right child
					root = start.right;
					start.right.parent = null;
					start = null;
				}
				else if(start.right == null){
					//node to be deleted only has a left child
					root = start.left;
					start.left.parent = null;
					start = null;
				}
				else{
					//node to be deleted has two children

					//find successor of node to be deleted
					//successor will have at most one node -think about it. very clever!
					Comparable successorKey = successor(startKey); //get successor's key
					//System.out.println("successorKey = "+successorKey);
					Object successorValue = delete(successorKey); //get successor's value and remove it from the tree
					TreeNode successor = new TreeNode(successorKey, successorValue); //make a successor node

					//now, replace node to be deleted with successor node
					root = successor;
					successor.parent = null;
					successor.left = start.left;
					successor.right = start.right;
				}

				return deletedValue;
				//return null;
			}

			if(start.left == null && start.right == null){
				//node to be deleted has 0 children
				if(start.equals(start.parent.left)){
					start.parent.left = null;
				}
				else if(start.equals(start.parent.right)){
					start.parent.right = null;
				}
			}
			else if(start.left == null){
				//node to be deleted only has a right child
				if(start.equals(start.parent.left)){
					start.right.parent = start.parent;
					start.parent.left = start.right;
				}
				else if(start.equals(start.parent.right)){
					start.right.parent = start.parent;
					start.parent.right = start.right;
				}
			}
			else if(start.right == null){
				//node to be deleted only has a left child
				if(start.equals(start.parent.left)){
					start.left.parent = start.parent;
					start.parent.left = start.left;
				}
				else if(start.equals(start.parent.right)){
					start.left.parent = start.parent;
					start.parent.right = start.left;
				}
			}
			else{
				//CASE: node to be deleted has two children

				//find successor of node to be deleted
				//successor will have at most one node -think about it. very clever!
				Comparable successorKey = successor(startKey); //get successor's key
				//System.out.println("successorKey = "+successorKey);
				Object successorValue = delete(successorKey); //get successor's value and remove it from the tree
				TreeNode successor = new TreeNode(successorKey, successorValue); //make a successor node

				//now, replace node to be deleted with successor node
				if(start.equals(start.parent.left)){
					successor.parent = start.parent;
					successor.left = start.left;
					successor.right = start.right;
					start.parent.left = successor;
				}else if(start.equals(start.parent.right)){
					successor.parent = start.parent;
					successor.left = start.left;
					successor.right = start.right;
					start.parent.right = successor;
				}
			}
		}
		return deletedValue;
	}

	//sucessor
	public Comparable successor (Comparable key){
		Comparable successor = null;
		//print
		if(search(key).key == null){
			//key not in tree
			return successor;
		}
		Enumeration e = getKeys();
		Comparable temp = null;
		Comparable prev = null;

		while (e.hasMoreElements()){
			temp = (Comparable) e.nextElement();
			//successor = temp
			if(prev != null){
				if(prev.equals(key)){
					successor = temp;
					return successor;
				}
			}
			prev = temp;
		}
		//System.out.print("successor: " + successor);
		if(!e.hasMoreElements()){
			//System.out.println("--key("+key+") is maximum. No Successor");
		}
		return successor;
	}

	//predecessor
	public Comparable predecessor (Comparable key){
		Comparable predecessor = null;
		//print
		if(search(key).key == null){
			//key is not in tree
			return predecessor;
		}
		Enumeration e = getKeys();
		Comparable temp = (Comparable) e.nextElement();
		if(temp.equals(key)){
			//key is minimum - no predecessor
			//System.out.println("--key ("+key+") is minimum. No predecessor");
			return predecessor;
		}

		while(e.hasMoreElements()){
			predecessor = temp;
			temp = (Comparable) e.nextElement();
			if(temp.equals(key)){
				return predecessor;
			}
		}
		return predecessor;
	}

	//getKeys
	public Enumeration getKeys(){
		Keys keys = new Keys(getCurrentSize());
		//printBlue("getKeys() (sorted)");
		getKeysRecursive(keys, getRoot());
		Enumeration e = keys.getEnumeration();
		return e;
	}
	//recursively add keys to Keys enum
	public Keys getKeysRecursive(Keys keys, TreeNode node){
		if(node.left != null)
			getKeysRecursive(keys, node.left);
		keys.addElement(node.key);
		if(node.right != null)
			getKeysRecursive(keys, node.right);
		return keys;
	}

	//getValues
	public Enumeration getValues(){
		Values values = new Values(getCurrentSize());
		getValuesRecursive(values, getRoot());
		Enumeration e = values.getEnumeration();
		return e;
	}
	//recursively add values to values enum
	public Values getValuesRecursive(Values values, TreeNode node){
		if(node.left != null)
			getValuesRecursive(values, node.left);
		values.addElement(node.value);
		if(node.right != null)
			getValuesRecursive(values, node.right);
		return values;
		//return(null);
	}

	public static void main(String[] args) {

		System.out.println("The main is runing");

		System.out.println("test insert!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		BinarySearchTree BST = new BinarySearchTree();

		Comparable testComp = 4;
		Object testObj = 6;
		BST.insert(testComp, testObj);

		Comparable testComp2 = 9;
		Object testObj2 = 32;
		BST.insert(testComp2, testObj2);

		Comparable testComp3 = 2;
		Object testObj3 = 87;
		BST.insert(testComp3, testObj3);

		Comparable testComp4 = 886;
		Object testObj4 = 1342;
		BST.insert(testComp4, testObj4);

		Comparable testComp5 = 74;
		Object testObj5 = 924;
		BST.insert(testComp5, testObj5);

		Comparable testComp6 = 14;
		Object testObj6 = 83;
		BST.insert(testComp6, testObj6);

		Comparable testComp7 = 71;
		Object testObj7 = 23;
		BST.insert(testComp7, testObj7);

		Comparable testComp8 = 39;
		Object testObj8 = 2222;
		BST.insert(testComp8, testObj8);

		Comparable testComp9 = 1000;
		Object testObj9 = 2222;
		BST.insert(testComp9, testObj9);

		System.out.println("testing print BST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		TreeNode print = BST.getRoot();
		BST.leftNodeRightPrint(print);


		//testing non null search case
		System.out.println("testing search BST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Comparable testCompser6 = 2;
		ComparableKeyValuePair searchtest = BST.search(testCompser6);
		System.out.println(searchtest);


		Comparable testCompNull1 = 23;
		System.out.println("null search result test!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(BST.search(testCompNull1));


		System.out.println("testing getCurrent size!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		int sizetest = BST.getCurrentSize();
		System.out.println(sizetest);


		System.out.println("testing min!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		ComparableKeyValuePair mintest = BST.minimum();
		System.out.println("min key: " + mintest.key + "	min value:" + mintest.value);

		System.out.println("testing max!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		ComparableKeyValuePair maxtest = BST.maximum();
		System.out.println("max key: " + maxtest.key + "	max value: " + maxtest.value);


		System.out.println("testing successor!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Comparable successor = BST.successor(testComp5);
		System.out.println("the successor of key " + testComp5 + " is key " + successor );

		System.out.println("testing predecessor!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Comparable predecessor = BST.predecessor(testComp5);
		System.out.println("the predecessor of key " + testComp5 + " is key " + predecessor );



		// System.out.println("testing delete root!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// Object deleted = BST.delete(testComp);
		// System.out.println("deleted object  " + deleted + " at key " + testComp);
		// System.out.println(" the current root now is " + BST.getRoot().key);

		// System.out.println("testing delete no children!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// Object deleted = BST.delete(testComp3);
		// System.out.println("deleted object  " + deleted + " at key " + testComp3);

		// System.out.println("testing delete with left child !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// Object deleted = BST.delete(testComp5);
		// System.out.println("deleted object  " + deleted + " at key " + testComp5);

		// System.out.println("testing delete with right child !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// Object deleted = BST.delete(testComp6);
		// System.out.println("deleted object  " + deleted + " at key " + testComp6);

		System.out.println("testing delete with left and right child !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Object deleted = BST.delete(testComp4);
		System.out.println("deleted object  " + deleted + " at key " + testComp4);




	}
}


//Keys Class
class Keys implements Enumeration{
	Comparable[] keys;
	int index = 0;
	int size = 0;

	Keys(int s){
		keys = new Comparable[s];
	}
	public void addElement(Comparable key){
		keys[size] = key;
		size++;
	}

	public boolean hasMoreElements(){
		if(index<size){
			return true;
		}else return false;
	}
	//next element
	public Comparable nextElement(){
		Comparable key = keys[index];
		index++;
		return key;
	}
	//get enumeration method
	public Enumeration getEnumeration(){
		index = 0;
		return this;
	}
}

//Values class
class Values implements Enumeration{
	Object[] keys;
	int index = 0;
	int size = 0;

	Values(int s){
		keys = new Object[s];
	}
	//add element to key
	public void addElement(Object key){
		keys[size] = key;
		size++;
	}
	//check if their are more elements to add
	public boolean hasMoreElements(){
		if(index<size){
			return true;
		}else return false;
	}
	//iterate to the next element
	public Object nextElement(){
		Object key = keys[index];
		index++;
		return key;
	}
	//get Enumeation method
	public Enumeration getEnumeration(){
		index = 0;
		return this;
	}
}