package com.krushna.Java_rnd.neetcode.trees;

/**
 * https://leetcode.com/problems/balanced-binary-tree/description/
 * Given a binary tree, determine if it is height balanced
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node 
 * never differs by more than one.
 */
public class BalancedBinaryTree {
	
	
    public boolean isBalanced(TreeNode root) {
        return dfsDepth(root)[0]==1;
        
    }
    
    // Here we need two things to be calculated, balanced and height or depth
    // balanced= true if left Height= right Height diff is not more then 1
    // so we can use int array where we will keep 1 as balanced at 0 index and 1 index is height
	public int[] dfsDepth(TreeNode root) {
		if (root == null) {
			return new int[] { 1, 0 }; // balance, height (1,0)
		}
		int[] leftH = dfsDepth(root.left);
		int[] rightH = dfsDepth(root.right);

		int balanced = (leftH[0] == 1 && rightH[0] == 1) && Math.abs(leftH[1] - rightH[1]) <= 1 ? 1 : 0;
		int height = 1 + Math.max(leftH[1], rightH[1]);

		return new int[] { balanced, height };
	}
	
	/**
	 * The problem with recursive return is it will return value at all steps and the last return value will
	 * be consider, so if the last return is balance then tree will be balanced
	 * hence we need to some how, consider the previous return value and calculate; 
	 * Hence the below will not work
	 * @param root
	 * @return
	 */
	public int dfsDepth2(TreeNode root) {
    	if(root ==null) {
    		return 0;
    	}
    	int leftH=dfsDepth2(root.left);
    	int rightH=dfsDepth2(root.right);
    	
    	if (Math.abs(leftH-rightH) > 1) {
    		return -1;
    	}
    	return 1+ Math.max(leftH, rightH);
    }

}
