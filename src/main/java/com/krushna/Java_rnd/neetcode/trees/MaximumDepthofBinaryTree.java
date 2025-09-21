package com.krushna.Java_rnd.neetcode.trees;


/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 * Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.


Input: root = [3,9,20,null,null,15,7]
Output: 3

Example 2:

Input: root = [1,null,2]
Output: 2


 */

public class MaximumDepthofBinaryTree {

	public int maxDepth(TreeNode root) {
		return dfs(root,0);
	}
	
	public int dfs(TreeNode root, int ctr) {
		if(root == null) {
			return ctr;
		}
		ctr+=1;
		return Math.max(dfs(root.left, ctr),dfs(root.right, ctr));
	}

}
