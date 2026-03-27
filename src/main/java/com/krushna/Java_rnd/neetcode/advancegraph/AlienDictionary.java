package com.krushna.Java_rnd.neetcode.advancegraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * There is a foreign language which uses the latin alphabet, but the order among letters is not "a", "b", "c" ... "z" as in English.
 * You receive a list of non-empty strings words from the dictionary, where the words are sorted lexicographically based on the rules of this new language.
 * Derive the order of letters in this language. If the order is invalid, return an empty string. If there are multiple valid order of letters, return any of them.
 * A string “a” is lexicographically smaller than a string “b” if either of the following is true:
 * * The first letter where they differ is smaller in a than in b.
 * * a is a prefix of b and a.length < b.length.
 *
 * Example 1:
 * Input: ["z","o"]
 *
 * Output: "zo"
 * Explanation: From "z" and "o", we know 'z' < 'o', so return "zo".
 * Example 2:
 * Input: ["hrn","hrf","er","enn","rfnn"]
 *
 * Output: "hernf"
 */
public class AlienDictionary {

    public String foreignDictionary(String[] words) {
        // build the adjacency list using map, set is avoid duplicate
        Map<Character, Set<Character>> nodesMap= new HashMap<>();
        // compare each word to build the Map
        Set<Character> allChars= new HashSet<>();
        // add the chars to all chars
        for(String word : words){
            for(char c: word.toCharArray())
                allChars.add(c);
        }
        for(int i=0; i < words.length-1; ++i){
            String w1= words[i];
            String w2=words[i+1];
            // edge case if w1 is before w2 because same prefix with smaller length
            int minLength= Math.min(w1.length(),w2.length());
            if(w2.length() > w1.length() // w1 length is small
                    &&  w1.substring(0,minLength).equals(w2.substring(0,minLength))){  // Prefix same
                // skip this word
                continue;
            }// add an invalid check
            else if(w1.length() > w2.length() &&  w1.substring(0,minLength).equals(w2.substring(0,minLength)) ){
                // invalid case
                return  "";
            }
            // check the char of each word
            char[] w1c=w1.toCharArray();
            char[] w2c=w2.toCharArray();

            for(int j =0; j< w1.length(); ++j){
                if(w1c[j] != w2c[j]){
                    // found the mismatch break the loop
                    nodesMap.computeIfAbsent(w1c[j], (key) -> new HashSet<>()).add(w2c[j]);
                    break;
                }
            }
        }

        StringBuilder res= new StringBuilder("");
        Map<Character,Boolean> visitedMap= new HashMap<>();
        // call the DFS for each cahr key from the map
        for( char c: allChars){
            boolean cycle=postDFS(nodesMap,res,visitedMap,c);
            if(cycle){
                return  "";
            }
        }
        return res.reverse().toString();
    }

    /**
     * We can say there is cycle if the node come twice in the same path,
     * So lets use visit map
     * TRUE : that visited and it same path cycle detected
     * FALSE : visited but not in same path
     */
    public boolean postDFS( Map<Character, Set<Character>> nodesMap, StringBuilder path, Map<Character,Boolean> visited,
    char start
    ){
        // base case
        if(visited.containsKey(start)){
        // cycle detected return
            return visited.get(start);
        }
        visited.put(start,true);// put all  the node from the path as true
        if(nodesMap.containsKey(start)) {
            for (char node : nodesMap.get(start)) {
                if(postDFS(nodesMap, path, visited, node)){
                    return true;
                }
            }
        }
        path.append(start);
        // update the map with value as false, for the next run for differnt path
        visited.put(start,false);
        return false;
    }

    public static void main(String[] args) {
        String[] word= {"A","BA","BC","C"};
        AlienDictionary ad= new AlienDictionary();
       // System.out.println(ad.foreignDictionary(word));
        String[] word2= {"abc","bcd","cde"};
        System.out.println(ad.foreignDictionary(word2));
    }
}
