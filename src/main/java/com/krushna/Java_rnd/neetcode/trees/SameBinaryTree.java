package com.krushna.Java_rnd.neetcode.trees;

/**
 https://leetcode.com/problems/same-tree/
 
 Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 */
public class SameBinaryTree {

	public boolean isSameTree(TreeNode p, TreeNode q) {
		return checkWithDfs(p, q);
	}
	
	public boolean checkWithDfs(TreeNode p, TreeNode q) {
		// if both are null means we have compare till we reached null and hence return true
		if(p == null && q == null) {
			return true;
		// one of them is null so return false
		}else if (p== null || q ==null) {
			return false;
		// there value did not match return false
		}else if (p.val != q.val) {
			return false;
		}
		// compare left with left and right with right
		return checkWithDfs(p.left,  q.left) && checkWithDfs(p.right,  q.right);
	}
}
