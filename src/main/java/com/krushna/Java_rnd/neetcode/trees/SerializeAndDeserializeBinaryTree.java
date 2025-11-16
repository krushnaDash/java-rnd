package com.krushna.Java_rnd.neetcode.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 * 
 * Clarification: The input/output format is the same as how LeetCode serializes
 * a binary tree. You do not necessarily need to follow this format, so please
 * be creative and come up with different approaches yourself.
 */
public class SerializeAndDeserializeBinaryTree {
	
	
	// Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
    	if(root==null)
    		return "";
        // lets do the BFS and create the String, we will put x if there is no child
    	StringBuilder sb= new StringBuilder();
    	Queue<TreeNode> deQueue= new LinkedList<TreeNode>();
    	while(root != null || ! deQueue.isEmpty() ) {
    		
    		if(root!=null) {
    			sb.append(root.val+",");
    			deQueue.add(root.left);
        		deQueue.add(root.right);
    		}
    		else {
    			sb.append("X"+",");
    		}
    		root=deQueue.poll();
    	}
    	return sb.deleteCharAt(sb.length()-1).toString();
    	
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if(data == null || data.isEmpty()) {
        	return null;
        }
    	String[] values= data.split(",");
        TreeNode root=new TreeNode(Integer.valueOf(values[0]));
        Queue<TreeNode> deQueue= new LinkedList<TreeNode>();
        deQueue.offer(root);
        
        for(int i=1;  i< values.length;  i++) {
        	TreeNode node=deQueue.poll();
        	
        	if(!values[i].equals("X")) {
        		// we will go by two step
        		node.left=new TreeNode(Integer.valueOf(values[i]));
        		deQueue.offer(node.left);
        	}
        	++i;
        	if(i< values.length && !values[i].equals("X") ) {
        		// we will go by two step
        		node.right=new TreeNode(Integer.valueOf(values[i]));
        		deQueue.offer(node.right);
        	}
        	
        }
        return root;
    }
    
    public static void main(String[] args) {
    	
    	TreeNode root= new TreeNode(1);
    	root.left=new TreeNode(2);
    	root.right=new TreeNode(3);
    	root.right.left=new TreeNode(4);
    	root.right.right=new TreeNode(5);
    	
    	System.out.println(serialize(root));
    	
    	TreeNode ans = deserialize(serialize(root));
    	
    	System.out.println(serialize(ans));
    	
	}

}
