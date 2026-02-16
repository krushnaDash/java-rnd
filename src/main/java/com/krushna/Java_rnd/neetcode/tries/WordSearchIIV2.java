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
public class WordSearchIIV2 {
	
	private boolean[][] visit;
	
	public List<String> findWords(char[][] board, String[] words) {
		Set<String> findWords= new HashSet<String>();
		Trnode root=createTrie(words);
		// for each position in boards do the DFS, till all words found
		visit= new boolean[board.length][board[0].length];
		
		for(int r=0; r< board.length ; ++r) {
			for(int c=0 ; c< board[r].length; ++c) {
				
				searchDFS(board, r, c, root, findWords,new StringBuilder());
			}
		}
		
        return new ArrayList<String>(findWords);
    }
	
	
	
	
	// Lets create a trie from the board using back tracking and then we can search the word.
	// This not so optimised
	
	public Trnode createTrie(String[] words) {
		Trnode root= new Trnode();
		for( String word: words) {
			Trnode tnode= root;
			for(char c: word.toCharArray()) {
				if( tnode.tnodes[c-'a']==null) {
					tnode.tnodes[c-'a']= new Trnode();
				}
				tnode=tnode.tnodes[c-'a'];
			}
			tnode.isEnd=true;
		}
		
		return root;
	}
	public void searchDFS(char[][] board, int row, int col, Trnode node,Set<String> findWords, StringBuilder currentWord) {
		// base if out of bound then return
		if(row >= board.length || col>= board[0].length || row<0 || col<0
				|| visit[row][col] 
				|| node.tnodes[ (board[row][col])-'a'] ==null
				) {
			return;
		}
		// add the current char and DFS for each direction
		
		visit[row][col]=true;
		char c=board[row][col];
		currentWord.append(c);
		
		Trnode backup=node;
		node=node.tnodes[c-'a'];
		
		// check for end word, if yes add to find words
		if(node.isEnd) {
			findWords.add(new String(currentWord.toString()));
		}
		
		searchDFS(board, row-1, col, node,findWords, currentWord);
		searchDFS(board, row+1, col, node,findWords, currentWord);
		searchDFS(board, row, col-1, node,findWords, currentWord);
		searchDFS(board, row, col+1, node,findWords, currentWord);
		
		visit[row][col]=false;
		// restore the node from backup
		node=backup;
		currentWord.deleteCharAt(currentWord.length()-1);
		
	}
	
	public static void main(String[] args) {
		char[][] borad= {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
		String[] words= {"oath","pea","eat","rain"};
		WordSearchIIV2 iiv2= new WordSearchIIV2();
		List<String> res= iiv2.findWords(borad, words);
		System.out.println(res);
	}
	

}
class Trnode{
	// max 26 small letter
	Trnode[] tnodes= new Trnode[26];
	boolean isEnd=false; 
}
