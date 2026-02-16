package com.krushna.Java_rnd.neetcode.tries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an m x n board of characters and a list of strings words, return all
 * words on the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring. The same
 * letter cell may not be used more than once in a word.
 */
public class WordSearchII {
	
	Set<String> visitedPosition= new HashSet<String>();
	
	public List<String> findWords(char[][] board, String[] words) {
		List<String> findWords= new ArrayList<String>();
		Tnode root=createTrie(board);
		for(String word:words) {
			if(search(word, root))
			findWords.add( word);
		}
        return findWords;
    }
	
	public boolean search(String word, Tnode root) {
		Tnode ptr= root;
		for(char c: word.toCharArray()) {
			if(ptr.tnodes[c-'a'] ==null) {
				return false;
			}
			ptr=ptr.tnodes[c-'a'];
		}
		return true;
	}
	
	
	// Lets create a trie from the board using back tracking and then we can search the word.
	// This not so optimised
	
	public Tnode createTrie(char[][] board) {
		Tnode tnode= new Tnode();
		for(int r=0; r< board.length; ++r) {
			for(int c=0; c< board[r].length; ++c) {
				// run the DFS for each char
				updateTnodeDFS(board, r, c, tnode);
			}
		}
		
		return tnode;
	}
	public void updateTnodeDFS(char[][] board, int row, int col, Tnode node) {
		// base if out of bound then return
		if(row >= board.length || col>= board[0].length || row<0 || col<0
				|| visitedPosition.contains(row+","+col)
				) {
			return;
		}
		// add the current char and DFS for each direction
		
		visitedPosition.add(row+","+col);
		char c=board[row][col];
		
		if(node.tnodes[c-'a'] ==null) {
			// add the nodes
			node.tnodes[c-'a']= new Tnode();
		}
		Tnode backup=node;
		node=node.tnodes[c-'a'];
		
		updateTnodeDFS(board, row-1, col, node);
		updateTnodeDFS(board, row+1, col, node);
		updateTnodeDFS(board, row, col-1, node);
		updateTnodeDFS(board, row, col+1, node);
		
		visitedPosition.remove(row+","+col);
		// restore the node from backup
		node=backup;
		
	}
	

}
class Tnode{
	// max 26 small letter
	Tnode[] tnodes= new Tnode[26];
}
