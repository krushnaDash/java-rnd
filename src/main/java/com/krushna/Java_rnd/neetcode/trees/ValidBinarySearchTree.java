package com.krushna.Java_rnd.neetcode.trees;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/description/
 * 
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left of a node contains only nodes with keys strictly less than the node's key.
The right subtree of a node contains only nodes with keys strictly greater than the node's key.
Both the left and right subtrees must also be binary search trees.

 */
public class ValidBinarySearchTree {
	
    public boolean isValidBST(TreeNode root) {
        return validWithDFS(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    /**
     * The logic here is we will pass the left and right value it should satisfy the below condition
     * left < node < right
     * and then for left child no change to left and but will be the node value
     * for right child opposite no change to right but left will be node value
     * 
     */
    public boolean validWithDFS(TreeNode node, long left, long right) {
    	if(node == null)
    		return true;
    	
    	if( !(left < node.val &&  node.val < right) ) {
    		return false;
    	}
    	return validWithDFS(node.left, left, node.val) &&
    			
    			validWithDFS(node.right, node.val, right);
    }

}


