package com.krushna.Java_rnd.neetcode.tries;

/**
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/description/
211. Design Add and Search Words Data Structure

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

    WordDictionary() Initializes the object.
    void addWord(word) Adds word to the data structure, it can be matched later.
    bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.


 */

class WordDictionary {
	
	TrieNode root;

    public WordDictionary() {
    	root= new TrieNode();
    }

    public void addWord(String word) {
    	TrieNode ptr=root;
    	for(char c: word.toCharArray()) {
    		if(ptr.trieNodes[c-'a'] ==null) {
    			ptr.trieNodes[c-'a'] = new TrieNode();
    		}
    		ptr=ptr.trieNodes[c-'a'];
    	}
    	ptr.isEnd=true;
    }

    public boolean search(String word) {
    	
    	return searchWithDFS(word, root, 0);
    	
    }
    
    public boolean searchWithDFS(String word, TrieNode node, int i) {
    	if( i >= word.length()) {
    		return node.isEnd;
    	}
    	char c= word.charAt(i);
    	if(c != '.') {
    		 if(node.trieNodes[c-'a']!=null) {
    			 // char match, move the pointer call the search
    			return searchWithDFS(word, node.trieNodes[c-'a'],  i+1);
    		 }else {
    			 return false;
    		 }
    	}else {
    		// do search for all the chars
    		boolean retrunValue=false;
    		for( TrieNode trieNode : node.trieNodes) {
    			if(trieNode !=null)
    				retrunValue= retrunValue ||searchWithDFS(word, trieNode,  i+1);
    		}
    		return retrunValue; 
    	}
    	
    }
    
    public static void main(String[] args) {
    	WordDictionary dictionary= new WordDictionary();
    	dictionary.addWord("day");
    	dictionary.addWord("bay");
    	dictionary.addWord("may");
    	System.out.println(dictionary.search("say"));
    	System.out.println(dictionary.search("day"));
    	System.out.println(dictionary.search(".ay"));
    	System.out.println(dictionary.search("b.."));
	}
}



class TrieNode {
	boolean isEnd=false;
	// we have max 26 letter so we can use any array also instead of map
	TrieNode[] trieNodes= new TrieNode[26];
	
}