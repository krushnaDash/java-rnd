package com.krushna.Java_rnd.neetcode.trees;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 * Given two integer arrays preorder and inorder where preorder is the preorder
 * traversal of a binary tree and inorder is the inorder traversal of the same
 * tree, construct and return the binary tree.
 */
public class BinaryTreeFromPreorderAndInorderTraversal {
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {
        
		if(preorder == null || inorder == null || preorder.length==0 || inorder.length ==0 )
			return null;
		
		// the first element from the pre-order is the root, so create the root node;
		
		TreeNode root= new TreeNode(preorder[0]);
		
		// from the in order array the root is mid, and left part is left child, and right part is right
		// so find the index of root from in order array
		
		// we need to find the index
		int mid=IntStream.range(0, inorder.length).filter( i -> inorder[i] == preorder[0]).findFirst().getAsInt();
		
		// create pre order left and in order right
		// leaving the 0, till mid is left
		int[] prOrderLeft= Arrays.copyOfRange(preorder, 1, mid+1);
		// from 0 till mid-1 is left
		int[] inOrderLeft= Arrays.copyOfRange(inorder, 0, mid);
		
		// leaving the 0, till mid is left
		int[] prOrderRight= Arrays.copyOfRange(preorder, mid+1, inorder.length);
		// from 0 till mid-1 is left
		int[] inOrderRight= Arrays.copyOfRange(inorder, mid+1, inorder.length);
		
		root.left= buildTree(prOrderLeft,inOrderLeft);
		root.right= buildTree(prOrderRight,inOrderRight);
		
		return root;
			
    }
	
	public static void main(String[] args) {
		int [] a= {1,2,3,4,5,6,7};
		int mid=IntStream.range(0, a.length).filter( i -> a[i] == 3).findFirst().getAsInt();
		System.out.println(mid);
	}
	
	
	

}
