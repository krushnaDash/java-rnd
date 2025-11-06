package com.krushna.Java_rnd.neetcode.trees;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * 
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia: 
“The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants 
(where we allow a node to be a descendant of itself).”

 */
public class LowestCommonAncestorInBinarySearchTree {
	
	
    /**
     * Binary search tree , where left < node < right
     * LCA : for the p and q will be one of the parent node, the ancestor which is smallest
     * 
     * the ancestor node can be consider as LCA, if there p and q are part of different sub tree.
     * 
     * So the logic is we will start from root and compare
     * if the below condition met then the root is LCA
     * 
     * p.val <= root.val <= q.val or q.val <= root.val <= p.val
     * 
     * O(h) 
     */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root ==null || p==null || q==null)
			return null;
		
        if( (p.val <= root.val && root.val <= q.val) || 
        		(q.val <= root.val && root.val <= p.val) ) {
        	return root;
        // if both greater then root look in right sub tree else left sub tree
        }else if(p.val > root.val && q.val > root.val ) {
        	return lowestCommonAncestor(root.right, p, q);
        }else {
        	return lowestCommonAncestor(root.left, p, q);
        }
    }

}
