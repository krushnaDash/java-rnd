package com.krushna.Java_rnd.neetcode.greedy;

import java.util.*;

/**
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"], but partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 * Return a list of integers representing the size of these parts.
 *
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        return usingMapCount(s);
    }
    public List<Integer> usingMapCount(String s){
        // have map to store the min and max index
        Map<Character, Integer> charToIndexMap= new HashMap<>();
        for(int i=0; i < s.length(); ++i){
            char c= s.charAt(i);
            charToIndexMap.put(c,i); // this will store the max index
        } // do we need the min index no to store in map no, we just need the max one

        // now start from the 0 char find the max, and include all char which are in beetween
        int max=0;
        int min=0;
        List<Integer> partations= new ArrayList<>();
        while (max < s.length()){
            max=charToIndexMap.get(s.charAt(min));
            if(min==max || max-min ==1){
                // we can create batch with this char only
                partations.add(max-min+1);
            }else{// find all the char whcih are inbetween min and Max add them
                // skip the char which are process
                for(int i=min+1; i <max; ++i){
                    int newMax=charToIndexMap.get(s.charAt(i));
                    max=Math.max(max,newMax);
                }
                partations.add(max-min+1);
            }
            min=max+1;
            max=max+1;
        }
        return partations;
    }

    public static void main(String[] args) {
        PartitionLabels pl= new PartitionLabels();
        System.out.println(pl.partitionLabels("ababcc"));
        System.out.println(pl.partitionLabels("ababcbacadefegdehijhklij"));
    }
}
