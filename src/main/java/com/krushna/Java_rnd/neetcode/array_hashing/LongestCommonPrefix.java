package com.krushna.Java_rnd.neetcode.array_hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
       // lets scan vertically and choose any string to run the loop
        for( int i=0; i < strs[0].length(); ++i){
            for(String str: strs){
                if( i== str.length() || strs[0].charAt(i) != str.charAt(i) ){
                    // out of bound
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }

    public String longestCommonPrefixWithTrie(String[] strs) {
        // First find the min string and create Trie out of it
        String minstr=strs[0];
        for(String str: strs){
           if( str.length() < minstr.length()) {
               minstr=str;
           }
        }
        TrieNode root= new TrieNode();
        // fill the trie with the minStr
        TrieNode ptr=root;
        for(char c: minstr.toCharArray()){
            TrieNode node=ptr.children.computeIfAbsent(c, key -> new TrieNode());
            ptr=node;
        }
        // now search the tri for all string and find the min match length
        int prefixLength= minstr.length();

        for(int i=0; i < strs.length; ++i){
            String currentString=strs[i];
            int currentMatch=0;
            ptr= root;
            for( char c: currentString.toCharArray()){
                TrieNode next=ptr.children.get(c);
                if(next !=null){
                    ptr =next;
                    currentMatch++;
                }else{
                    break;
                }
            }
            prefixLength=Math.min(prefixLength, currentMatch);
        }
         return  minstr.substring(0, prefixLength);
    }

    class TrieNode{
        Map<Character, TrieNode> children= new HashMap<>();
    }

    public static void main(String[] args) {
        LongestCommonPrefix lc= new LongestCommonPrefix();
        String strs[]= {"reflower","flow","flight"};
        lc.longestCommonPrefixWithTrie(strs);
    }


}
