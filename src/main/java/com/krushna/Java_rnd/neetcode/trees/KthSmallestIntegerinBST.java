package com.krushna.Java_rnd.neetcode.trees;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
 * https://design-with-krushna.atlassian.net/wiki/spaces/InterviewP/pages/7372811/Kth+Smallest+Integer+in+BST
 * 
 * Given the root of a binary search tree, and an integer k, return the kth
 * smallest value (1-indexed) of all the values of the nodes in the tree.
 */
public class KthSmallestIntegerinBST {

	
	/**
	 * We can approach an iterative approach also, and used a stack store the visited nodes and will go till left
	 * and use the stack to pop out the element and move to right 
	 */
	
	public int kthSmallest(TreeNode root, int k) {
		Deque<TreeNode> stack= new LinkedList<>();
		TreeNode node=root;
		int ctr=0;
		
		while(node !=null || !stack.isEmpty()) {
			while(node !=null) {
				stack.push(node);
				node=node.left;
			}
			// process from stack now
			++ctr;
			node=stack.pop();
			if(ctr==k) {
				return node.val;
			}
			node=node.right;
		}
		return 0;
	}
	
	public int kthSmallestV2(TreeNode root, int k) {
		// lets solve this using recursive DFS
		int[] result= new int[2]; // 0th index will be counter and 1st index will be result
		dfs(root, k, result);
		return result[1];
	}
	
	// 0th index will be counter and 1st index will be result
	public void dfs(TreeNode node, int k, int[] result) {
		if(node==null) return;
		
		dfs(node.left, k, result);
		result[0]++;
		if(result[0]==k) {
			result[1]=node.val;
			return;
		}
		dfs(node.right, k, result);
	}
	
	
	
	

}
