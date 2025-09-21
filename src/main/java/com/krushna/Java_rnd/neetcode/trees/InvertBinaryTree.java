package com.krushna.Java_rnd.neetcode.trees;

/**
 * https://leetcode.com/problems/invert-binary-tree/description/
 * 
 */

public class InvertBinaryTree {
	
	
	public TreeNode invertTree(TreeNode root) {
        dfs(root);
        return root;
    }
	
	public void dfs(TreeNode root) {
		if(root==null)
			return ;
		
		// swap the element and do the DFS on each left and right
		
		TreeNode temp= root.left;
		root.left=root.right;
		root.right=temp;
		dfs(root.left);
		dfs(root.right);
	}
	
	

}
