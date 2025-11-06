package com.krushna.Java_rnd.neetcode.trees;

/**
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/
 * 
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.

 */

public class CountGoodNodesInBinaryTree {

	/**
	 * Use DFS (Depth First Search) to traverse the tree, and constantly keep track of the current path maximum.
	 * @param root
	 * @return
	 */
	public int goodNodes(TreeNode root) {
		
		//return dfsGoodNodesV2(root,root.val);
		// why +1, this is because root node is always good node
		return dfsGoodNodes(root, Integer.MIN_VALUE)+1;
	}
	
	public int dfsGoodNodes(TreeNode root, int max) {
		if (root == null) {
			return 0;
		}
		max = Math.max(max, root.val);

		if ((root.left != null && max <= root.left.val ) && (root.right != null && max <= root.right.val )) {
			return 2 + dfsGoodNodes(root.left, max) + dfsGoodNodes(root.right, max);
		} else if ((root.right != null && max <= root.right.val ) || (root.left != null && max <= root.left.val )) {
			return 1 + dfsGoodNodes(root.left, max) + dfsGoodNodes(root.right, max);
		}

		else
			return dfsGoodNodes(root.left, max) + dfsGoodNodes(root.right, max);
		
	}
	
	// another simplify method can be
	public int dfsGoodNodesV2(TreeNode node, int max) {
		if(node == null)
			return 0;
		int res=0;
		if(max <= node.val) {
			res++;
		}
		max=Math.max(max, node.val);
		res+=dfsGoodNodesV2(node.left, max);
		res+=dfsGoodNodesV2(node.right, max);
		return res;
	}
	

}
