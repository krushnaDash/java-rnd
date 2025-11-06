package com.krushna.Java_rnd.neetcode.trees;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/description/
 * 
 * Given the root of a binary tree, return the level order traversal of its nodes' values. 
 * (i.e., from left to right, level by level).
 * 
 * 
 */
public class BinaryTreeLevelOrderTraversal {
	
	// root
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> levelList = new ArrayList<>();
		// base case
		if (root == null) {
			return levelList;
		}
		// let do the BFS here.
		Queue<TreeNode> elementQueue = new LinkedList<>();
		elementQueue.add(root);
		
		while (!elementQueue.isEmpty()) {
			
			List<Integer> element = new ArrayList<>();
			
			// we can avoid the temp storage, if we know how many element we should remove
			// and that will be the size queue
			List<TreeNode> tempStorage= new ArrayList<>();
			
			while(!elementQueue.isEmpty()) {
				TreeNode e = elementQueue.poll();
				if (e != null) {
					element.add(e.val);
					if(e.left !=null)
						tempStorage.add(e.left);
					if(e.right !=null)
						tempStorage.add(e.right);
				}
			}
			levelList.add(element);
			elementQueue.addAll(tempStorage);
			
		}
		return levelList;
	}
	
	    /**
	     * Cleaner approach
	     * @param root
	     * @return
	     */
		public List<List<Integer>> levelOrderV2(TreeNode root) {
			List<List<Integer>> levelList = new ArrayList<>();
			// base case
			if (root == null) {
				return levelList;
			}
			// let do the BFS here.
			Queue<TreeNode> elementQueue = new LinkedList<>();
			elementQueue.add(root);
			
			while (!elementQueue.isEmpty()) {
				
				List<Integer> level = new ArrayList<>();
				
				// we can avoid the temp storage, if we know how many element we should remove
				// and that will be the size queue
				
				// to do that run a for loop
				
				for (int i = elementQueue.size(); i > 0; --i) {
					TreeNode node = elementQueue.poll();
					if (node != null) {
						level.add(node.val);

						if (node.left != null)
							elementQueue.add(node.left);

						if (node.right != null)
							elementQueue.add(node.right);
					}
				}
				levelList.add(level);
				
				
			}
			return levelList;
		}

}
