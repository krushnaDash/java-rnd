package com.krushna.Java_rnd.neetcode.trees;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/description/ A
 * path in a binary tree is a sequence of nodes where each pair of adjacent
 * nodes in the sequence has an edge connecting them. A node can only appear in
 * the sequence at most once. Note that the path does not need to pass through
 * the root.
 * 
 * The path sum of a path is the sum of the node's values in the path.
 * 
 * Given the root of a binary tree, return the maximum path sum of any non-empty
 * path.
 * 
 */
public class BinaryTreeMaximumPathSum {
	
	public int maxPathSum(TreeNode root) {
		int[] res= new int [] {root.val};
		dfs(root,res);
		return res[0];
	}
	
	public int dfs(TreeNode root, int[] res) {
		if (root == null) {
			return 0;
		}
		// lets do the DFS here
		int leftMax = Math.max(dfs(root.left,res), 0);
		int rightMax = Math.max(dfs(root.right,res), 0);
		
		// compute the max path sum with the current root and Split the path
		res[0]=Math.max(res[0], leftMax+rightMax+root.val);
		
		return root.val + Math.max(leftMax, rightMax);
	}

}
