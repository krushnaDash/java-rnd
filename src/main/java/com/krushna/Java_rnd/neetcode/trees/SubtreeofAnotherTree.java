package com.krushna.Java_rnd.neetcode.trees;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/description/
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure 
 * and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. 
The tree tree could also be considered as a subtree of itself.
 */
public class SubtreeofAnotherTree {
	
	public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    
	return checkWithDFS(root, subRoot);   	
		
    }
	// The time complexity will be o(n X m)
	// Where m is the number of nodes in subRootsubRoot and n is the number of nodes in root.
	public boolean checkWithDFS(TreeNode root, TreeNode subRoot) {
		// tarverse the main tree with DFS and if we find the node with same value as subroot then check for same
		
		if(root == null)
			return false;
		
		// at each node check if its match
		else if (isSame(root, subRoot))
			return true;
		
		return checkWithDFS(root.left, subRoot) || checkWithDFS(root.right, subRoot);
		
	}
	
	public boolean isSame(TreeNode r1, TreeNode r2) {
	   if(r1 == null && r2 == null) return true;
	   else if(r1 == null || r2 == null) return false;
	   else if (r1.val != r2.val) return false;
	   
	   return isSame(r1.left, r2.left)&& isSame(r1.right, r2.right);
	   
	}

}
