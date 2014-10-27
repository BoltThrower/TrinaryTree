/*******************************************************************\
|* Christopher Hartman
|* 10/22/2014
|* 
|* Test of TrinaryTree Insert and Delete 
|* 
|* This class was written to test the TrinaryTree class.  Currently,
|* it shows what the tree looks after inserting values.  Then, it 
|* deletes them in the same order as well as printing the tree again
|* after each deletion.
|* 
|* The tests below may be rewritten for further testing.
|*
\*******************************************************************/

class main {
	public static void main(String[] args) {
		TrinaryTree tree = new TrinaryTree();

		// Insert
        tree.Insert(5);      
        tree.Insert(4);
        tree.Insert(9);
        tree.Insert(5);
        tree.Insert(7);
        tree.Insert(2);
        tree.Insert(2);
        
        
        tree.PrintTree();
        System.out.println(); 

        
        // Delete and Print
        System.out.println("Delete 5");
        tree.Delete(5);
        tree.PrintTree();
        System.out.println();
        
        System.out.println("Delete 4");
        tree.Delete(4);
        tree.PrintTree();
        System.out.println();
        
        System.out.println("Delete 9");
        tree.Delete(9);
        tree.PrintTree();
        System.out.println();
        
        System.out.println("Delete 5");
        tree.Delete(5);
        tree.PrintTree();
        System.out.println();
        
        System.out.println("Delete 7");
        tree.Delete(7);
        tree.PrintTree();
        System.out.println();
        
        System.out.println("Delete 2");
        tree.Delete(2);
        tree.PrintTree();
        System.out.println();
        
        System.out.println("Delete 2");
        tree.Delete(2);
        tree.PrintTree();
        System.out.println(); 
	}
}
