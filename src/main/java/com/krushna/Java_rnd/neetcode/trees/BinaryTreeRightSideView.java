package com.krushna.Java_rnd.neetcode.trees;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/description/
 * 
 * Given the root of a binary tree, imagine yourself standing on the right side of it, 
 * return the values of the nodes you can see ordered from top to bottom.
 */
public class BinaryTreeRightSideView {
	
	
	// We will do the BFS with little twist
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> rightView = new ArrayList<>();
		if (root == null) {
			return rightView;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {

			// just add the first element from the queue form any level
			rightView.add(queue.peek().val);
			// this is just remove the element and add their child to queue.
			for (int i = queue.size(); i > 0; --i) {
				TreeNode node = queue.poll();
				if (node != null) {
					if (node.right != null)
						queue.add(node.right);

					if (node.left != null)
						queue.add(node.left);

				}
			}

		}
		return rightView;
    }

}
