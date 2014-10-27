/***************************************************************\
|* Christopher Hartman
|* 10/22/2014
|* 
|* Trinary Tree Insert and Delete
|* 
|* This class was written to implement Insert and Delete for
|* a trinary tree.  This class assumes that integers are the
|* data type, since we need a data type that can compare digits
|* for maintaining order.
|* 
|* Starting at the root and for each subsequent node: the left 
|* child is less than its parent, the middle child is equal to
|* its parent, and the right child is greater than the parent.
\***************************************************************/

class TrinaryTree {
	
	// The TrinaryTree is a tree of nodes.  Each node has three children,
	// as well as a value.
	private class Node{
		
		private int value = 0;
		private Node leftChild = null;
		private Node middleChild = null;
		private Node rightChild = null;
		private Node parent = null;
		
		Node(int initialValue){
			value = initialValue;
		}
		
		// Getters and Setters
		int GetValue() { return value; }
		void SetValue(int newValue){ value = newValue;}
		
		Node GetLeftChild() { return leftChild; }
		Node GetMiddleChild() { return middleChild; }
		Node GetRightChild() { return rightChild; }
		Node GetParent() { return parent; }
		
		void SetLeftChild(Node child) { leftChild = child; }
		void SetMiddleChild(Node child) { middleChild = child; }
		void SetRightChild(Node child) { rightChild = child; }
		void SetParent(Node newParent) { parent = newParent; }
	}

	
	private int totalNumberOfNodes;
	private Node root;
	
	
	public TrinaryTree(){
		totalNumberOfNodes = 0;
	}
	
	
	// PrintTree() prints each row in the tree in order from left to right.
	public void PrintTree(){
		
		int depth = 0;
		int nodesLeft = totalNumberOfNodes;
		Node currentNode = GetRootNode();
		String currentLine = "";
		
		// Print each row for each level of depth in the tree starting at the root.
		while(nodesLeft > 0){
			currentLine = StringNextNode(currentNode, depth, currentLine);
			
			// If there are nodes at the current depth, print them out.
			if(currentLine != ""){
				System.out.println(currentLine);
			}
			
			// Need to clear the string to be ready for the next line.
			// Due to the recursion in StringNextNode, the string needs
			// to be cleared here.
			currentLine = "";
			
			depth++;
			nodesLeft--;
		}
		
	}
	
	// Insert a new node into the tree.
	public void Insert(int newNodeValue){
		
		if(totalNumberOfNodes == 0){
			SetRootNode(new Node(newNodeValue));
		}
		
		else{
			
			// Start at the root and traverse down until a suitable
			// position is found.
			Node x = GetRootNode();
			Node parentOfNewNode = null;
			Node newNode = new Node(newNodeValue);
			
			
			while(x != null){
				parentOfNewNode = x;
				
				if(newNode.GetValue() < x.GetValue()){
					x = x.GetLeftChild();
				}
				
				else if(newNode.GetValue() == x.GetValue()){
					x = x.GetMiddleChild();
				}
				
				else{
					x = x.GetRightChild();
				}
			}
			
			newNode.SetParent(parentOfNewNode);
			
			// If the tree was empty.
			if(parentOfNewNode == null){
				SetRootNode(new Node(newNodeValue));
			}
			
			else if(newNode.GetValue() < parentOfNewNode.GetValue()){
				parentOfNewNode.SetLeftChild(newNode);
			}
			
			else if(newNode.GetValue() == parentOfNewNode.GetValue()){
				parentOfNewNode.SetMiddleChild(newNode);
			}
			
			else if(newNode.GetValue() > parentOfNewNode.GetValue()){
				parentOfNewNode.SetRightChild(newNode);
			}
		}
		
		totalNumberOfNodes++;
	}

	// Delete a node in the tree with the same value.  Returns false if the value couldn't have been found.
	public Boolean Delete(int valueToDelete){
		
		Node currentNode = GetRootNode();
		Node nodeToDelete = null;
		boolean couldntFindValue = false;
		
		totalNumberOfNodes--;
		
		// First find the node with the value to delete.
		while(nodeToDelete == null && couldntFindValue == false){
			
			if(currentNode.GetValue() == valueToDelete){
				nodeToDelete = currentNode;
			}
			
			else if(currentNode.GetValue() > valueToDelete){
				
				if(currentNode.GetLeftChild() != null){
					currentNode = currentNode.GetLeftChild();
				}
				
				else{
					System.out.println("Error. Couldn't find value to delete.");
					return false;
				}
			}
			
			else if(currentNode.GetValue() < valueToDelete){
				
				if(currentNode.GetRightChild() != null){
					currentNode = currentNode.GetRightChild();
				}
				
				else{
					System.out.println("Error. Couldn't find value to delete.");
					return false;
				}
			}
		}
		

		// If the node to delete has middle children, delete the deepest middle
		// child to make it simpler for deletion.
		if(nodeToDelete.GetMiddleChild() != null){
			
			while(nodeToDelete.GetMiddleChild() != null){
				nodeToDelete = nodeToDelete.GetMiddleChild();
			}
			
			nodeToDelete.GetParent().SetMiddleChild(null);
			return true;
		}
		
		// If the node to delete has no children
		if(nodeToDelete.GetLeftChild() == null && nodeToDelete.GetRightChild() == null){

			if(nodeToDelete == GetRootNode()){
				SetRootNode(null);
			}
			
			// If the node to delete is a left child
			else if(nodeToDelete.GetParent().GetLeftChild() == nodeToDelete){
				nodeToDelete.GetParent().SetLeftChild(null);
			}
			
			// If the node to delete is a right child
			else if(nodeToDelete.GetParent().GetRightChild() == nodeToDelete){
				nodeToDelete.GetParent().SetRightChild(null);
			}
			
			return true;
		}
		
		// If the node to delete doesn't have a left child.
		else if(nodeToDelete.GetLeftChild() == null){
			if(nodeToDelete == GetRootNode()){
				SetRootNode(nodeToDelete.GetRightChild());
				GetRootNode().SetMiddleChild(nodeToDelete.GetMiddleChild());
			}
			
			else if(nodeToDelete.GetParent().GetLeftChild() == nodeToDelete){
				nodeToDelete.GetParent().SetLeftChild(nodeToDelete.GetRightChild());
			}
			
			else{
				nodeToDelete.GetParent().SetRightChild(nodeToDelete.GetRightChild());
			}
			
			nodeToDelete.GetParent().SetMiddleChild(nodeToDelete.GetMiddleChild());
		}
		
		// If the node to delete doesn't have a right child.
		else if(nodeToDelete.GetRightChild() == null){
			
			if(nodeToDelete == GetRootNode()){
				SetRootNode(nodeToDelete.GetLeftChild());
			}
			
			else if(nodeToDelete.GetParent().GetLeftChild() == nodeToDelete){
				nodeToDelete.GetParent().SetLeftChild(nodeToDelete.GetLeftChild());
			}
			
			else{
				nodeToDelete.GetParent().SetRightChild(nodeToDelete.GetLeftChild());
			}
		}
		
		// If the node to delete has a left and right child, replace it with its successor.
		else{
		
			Node successor = FindSuccessor(nodeToDelete.rightChild);
			
			nodeToDelete.SetValue(successor.GetValue());
			
			if(nodeToDelete.GetRightChild() == successor){
				nodeToDelete.SetRightChild(successor.GetRightChild());
			}
			
			else{
				successor.GetParent().SetLeftChild(null);
			}
		}
		
		return true;
	}
	
	
	public int GetRootValue(){
		return root.GetValue();
	}
	
	private Node GetRootNode(){
		return root;
	}
	
	private void SetRootNode(Node newRoot){
		root = newRoot;
	}
	

	// The successor is the minimum of the larger values
	// of the tree compared to the inputed node.
	private Node FindSuccessor(Node currentNode){
		Node successor = null;
		
		if(currentNode == null){
			return null;
		}
		
		// Go down the left children of the inputed node to find the successor.
		while(currentNode.GetLeftChild() != null){
			successor = currentNode;
			currentNode = currentNode.GetLeftChild();
		}
		
		// The currentNode no longer has any left children, so the
		// successor has been found.
		successor = currentNode;
		
		// If the successor has middle children, replace with the lowest middle child.
		while(successor.GetMiddleChild() != null){
			successor = successor.GetMiddleChild();
		}
		
		return successor;
	}
	
	
	// Used for TreePrint() to print each row of the tree.  The inputed depth
	// is the desired row to be printed as a single string result.
	private String StringNextNode(Node currentNode, int depth, String result){
		
		if(depth == 0){
			result += currentNode.GetValue() + " ";
		}
		else if(depth > 0){
			if(currentNode.leftChild != null){
				result = StringNextNode(currentNode.leftChild, depth - 1, result);
			}
			
			if(currentNode.middleChild != null){
				result = StringNextNode(currentNode.middleChild, depth - 1, result);
			}
			
			if(currentNode.rightChild != null){
				result = StringNextNode(currentNode.rightChild, depth - 1, result);
			}
		}

		
		return result;
	}
}
