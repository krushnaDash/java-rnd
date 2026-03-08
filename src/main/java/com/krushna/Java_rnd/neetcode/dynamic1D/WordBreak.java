package com.krushna.Java_rnd.neetcode.dynamic1D;

import java.util.List;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[] dp = new Boolean[s.length() + 1];  // Use Boolean to distinguish null (unvisited) from false (visited but failed)
        //return DFSBackTrackWithDP(s, wordDict, 0, dp);
        return  bottomUpWithDP(s,wordDict);
    }

    public boolean bottomUpWithDP(String s, List<String> wordDict){
        boolean[] dp= new boolean[s.length()+1];
        dp[s.length()]=true;
        for(int i=s.length()-1 ; i>=0; --i){
            for(String word:wordDict){
                int l=  i+  word.length();
                if(l <= s.length() && word.equals(s.substring(i,l))){
                    // match update the DP array
                    // or is needed here Since multiple words can start at position i
                    //and any one of them being valid makes dp[i] = true, so
                    dp[i]= dp[i] || dp[l];
                }
            }
        }
        return dp[0];
    }

    // DFS with memoization
    public boolean DFSBackTrackWithDP(String s, List<String> wordDict, int i, Boolean[] dp) {
        // base case when i reach to end return true
        if (i == s.length()) {
            return true;
        }
        // if already computed, return cached result
        if (dp[i] != null) {
            return dp[i];
        }
        // check for each word
        for (String word : wordDict) {
            int length = i + word.length();
            if (length <= s.length() && word.equals(s.substring(i, length))) {
                // match found, recursively check rest of string
                if (DFSBackTrackWithDP(s, wordDict, i + word.length(), dp)) {
                    dp[i] = true;  // Cache the result
                    return true;   // Found a valid path, return immediately
                }
            }
        }
        dp[i] = false;  // Cache that no valid path exists from index i
        return false;   // No valid path found from this position
    }


    // Original DFS without memoization (for comparison)
    public boolean DFSBackTracking(String s, List<String> wordDict, int i){
        // base case when i reach to end return true
        if(i == s.length()){
            return true;
        }
        // check for each word
        for(String word: wordDict){
            int length = i + word.length();
            if(length <= s.length() && word.equals(s.substring(i, length))){
                // match found, recursively check rest of string
                if(DFSBackTracking(s, wordDict, i + word.length())){
                    return true;  // Found a valid path, return immediately
                }
            }
        }
        return false;  // No valid path found from this position
    }


    public static void main(String[] args) {
        WordBreak wb = new WordBreak();

        List<String> wordDict = List.of( "code", "leet");
        String s = "leetcode";

        System.out.println("With Memoization (DFSBackTrackWithDP):");
        System.out.println("'leetcode' -> " + wb.wordBreak(s, wordDict));
        System.out.println("'leetMcode' -> " + wb.wordBreak("leetMcode", wordDict));

        List<String> wordDict1 = List.of("a", "abc", "b", "cd");
        System.out.println("'abcd' -> " + wb.wordBreak("abcd", wordDict1));

        System.out.println("\nWithout Memoization (DFSBackTracking):");
        System.out.println("'neetcode' -> " + wb.DFSBackTracking(s, wordDict, 0));
        System.out.println("'abcd' -> " + wb.DFSBackTracking("abcd", wordDict1, 0));
    }

}
