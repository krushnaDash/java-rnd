package com.krushna.Java_rnd.neetcode.trees;


/**
 * 
 * 
 * https://leetcode.com/problems/diameter-of-binary-tree/description/ 
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * 
 * The diameter of a binary tree is the length of the longest path between any
 * two nodes in a tree. This path may or may not pass through the root.
 * 
 * The length of a path between two nodes is represented by the number of edges
 * between them.
 */
public class DiameterofBinaryTree {
	
	// Diameter= max of height of left + height right.
	// so we can have dfs height function which return the height of a node, which should max(height of left, height right)
	int maxDiameter=0;
	public int diameterOfBinaryTree(TreeNode root) {
		dfsHeight(root);
		return maxDiameter;
    }
	public int dfsHeight(TreeNode node) {
		if(node == null) {
			return 0;
		}
		
		int leftH=dfsHeight(node.left);
		int rightH=dfsHeight(node.right);
		maxDiameter=Math.max(maxDiameter, leftH+rightH);
		return  1+Math.max(leftH, rightH);
	}
	

}
