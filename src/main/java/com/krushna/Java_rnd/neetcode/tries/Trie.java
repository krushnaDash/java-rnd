package com.krushna.Java_rnd.neetcode.tries;

import java.util.HashMap;
import java.util.Map;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to
 * efficiently store and retrieve keys in a dataset of strings. There are
 * various applications of this data structure, such as autocomplete and
 * spellchecker.
 * 
 * Implement the Trie class:
 */

public class Trie {
	
	TrieNodeV1 root;

	public Trie() {
		root= new TrieNodeV1();
	}

	public void insert(String word) {
		TrieNodeV1 ptr=root;
		for( char c: word.toCharArray()) {
			if( !ptr.childrens.containsKey(c)) {
				ptr.childrens.put(c, new TrieNodeV1());
			}
			ptr=ptr.childrens.get(c);
		}
		// mark the end
		ptr.isEnd=true;
	}

	public boolean search(String word) {
		TrieNodeV1 ptr= root;
		for(char c: word.toCharArray()) {
			if(!ptr.childrens.containsKey(c)) {
				return false;
			}
			ptr=ptr.childrens.get(c);
		}
		return ptr.isEnd;
	}

	public boolean startsWith(String prefix) {
		TrieNodeV1 ptr= root;
		for(char c: prefix.toCharArray()) {
			if(!ptr.childrens.containsKey(c)) {
				return false;
			}
			ptr=ptr.childrens.get(c);
		}
		return true;
	}

}
class TrieNodeV1 {
	boolean isEnd=false;
	Map<Character, TrieNodeV1> childrens= new HashMap<Character, TrieNodeV1>();
}
