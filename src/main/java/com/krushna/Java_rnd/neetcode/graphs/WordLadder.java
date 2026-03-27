package com.krushna.Java_rnd.neetcode.graphs;

import java.util.*;

/**
 A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

 Every adjacent pair of words differs by a single letter.
 Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 sk == endWord

 Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.



 Example 1:

 Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 Output: 5
 Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

 Example 2:

 Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 Output: 0
 Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 https://leetcode.com/problems/word-ladder/description/
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // if end word is not there in list then return 0
        if(!wordList.contains(endWord)){
            return 0;
        }
        // add the begin word, is it is not part of the list
        wordList.add(beginWord);
        Map<String, List<String>> wordMap= new HashMap<>();
        // create the adjacency list using a map, where the key is pattern
        for(String word : wordList){
          // create each pattern
            char[] chars=word.toCharArray();
            for(int i=0; i < chars.length; ++i){
                char temp=chars[i];
                chars[i]='*';
                String pattern=  new String(chars);
                wordMap.computeIfAbsent(pattern, (key) -> new ArrayList<>()).add(word);
                // restore
                chars[i]=temp;
            }
        }
        return BFS(wordMap,endWord,beginWord);
    }
    // BFS is good for shortest path
    public int BFS(Map<String, List<String>> wordMap, String endWord, String beginWord){
        int res=1;
        Set<String> visited= new HashSet<>();
        // we will start with begin word.
        Queue<String> queue= new LinkedList<>();
        queue.add(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()){
            int size=queue.size();
            res++;
            for(int i=0; i< size; ++i){
                String word= queue.poll();
                // for each pattern for this word
                char[] chars= word.toCharArray();
                for(int j=0; j< chars.length; ++j){
                    char temp=chars[j];
                    chars[j]='*';
                    String pattern= new String(chars);
                    // restore the char
                    chars[j]=temp;
                    for(String nword: wordMap.get(pattern)){
                        if(visited.contains(nword))
                            continue;
                        visited.add(nword);
                        if(nword.equals(endWord)){
                            return  res;
                        }
                        queue.offer(nword);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String beginWord="hit";
        String endWord="cog";
        List<String> wordList= new ArrayList<>();
        wordList.add("hot");wordList.add("dot");
        wordList.add("dog");wordList.add("lot");
        wordList.add("log");wordList.add("cog");
        WordLadder ladder= new WordLadder();
        System.out.println(ladder.ladderLength(beginWord,endWord,wordList));
    }

}
