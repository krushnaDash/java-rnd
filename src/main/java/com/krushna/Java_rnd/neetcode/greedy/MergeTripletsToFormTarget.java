package com.krushna.Java_rnd.neetcode.greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A triplet is an array of three integers. You are given a 2D integer array triplets, where triplets[i] = [ai, bi, ci] describes the ith triplet. You are also given an integer array target = [x, y, z] that describes the triplet you want to obtain.
 * To obtain target, you may apply the following operation on triplets any number of times (possibly zero):
 * * Choose two indices (0-indexed) i and j (i != j) and update triplets[j] to become [max(ai, aj), max(bi, bj), max(ci, cj)].
 *     * For example, if triplets[i] = [2, 5, 3] and triplets[j] = [1, 7, 5], triplets[j] will be updated to [max(2, 1), max(5, 7), max(3, 5)] = [2, 7, 5].
 * Return true if it is possible to obtain the target triplet [x, y, z] as an element of triplets, or false otherwise.
 *
 */
public class MergeTripletsToFormTarget {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        //return countGoodRows(triplets,target);
        return checkEachRow(triplets,target);
    }

    /**
     *Lets take 3 flags t1, t2, t3
     * For each good rows, (Good rows ->A row which does not contain any greater element from target)
     * check for match, if at any point all 3 true return true, else at the end return false.
     */
    public boolean checkEachRow(int[][] triplets, int[] target){
        boolean t1=false, t2=false, t3=false;
        for(int[]t: triplets){
            if(t[0] > target[0] || t[1] > target[1] || t[2] > target[2]){
                continue; // skip the row
            }else{
                // check for match and mark them true
                t1= (t[0]==target[0] || t1);
                t2= (t[1]==target[1] || t2);
                t3= (t[2]==target[2] || t3);
            }
            if(t1 && t2 && t3){
                return true;
            }
        }
        return false;
    }
    public boolean countGoodRows(int[][] triplets, int[] target){
        // Lets find the good rows, and check if they match, if match just count
        // coloumn wise
        Set<Integer> goodRows= new HashSet<>();
        for(int [] t : triplets){
            if(t[0] > target[0] || t[1] > target[1] || t[2] > target[2]){
                continue; // skip row
            }else{
                // check if the row match any target coloumn, if yes, store that coloumn index
                // we should have exactly 3 value 0,1, and 2
                for(int i=0; i < target.length; ++i){
                    if(t[i]== target[i]){
                        goodRows.add(i);
                    }
                }
            }
        }
        return goodRows.size()==3;
    }
    public boolean checkByremovingBadrows(int[][] triplets, int[] target){
        // Lets remove the triplet which are not eligble, the logic is any triplet which contain a > greater number
        // in the same coloumn
        Set<Integer> notElgibleRows= new HashSet<>();
        for(int i=0; i< triplets.length; ++i){
            for(int j=0;  j < triplets[i].length; ++j){
                if(triplets[i][j] > target[j]){
                    notElgibleRows.add(i); // add the row, break the inner loop
                    break;
                }
            }
        }
        // now check the reaming row should have the elements
        // lets do coloumn wise
        for(int j=0; j< target.length; ++j ){
            boolean found=false;
            for (int i=0; i < triplets.length; ++i){
                if(notElgibleRows.contains(i)){
                    continue; //skip this row
                }
                if(target[j]==triplets[i][j]){
                    found=true;
                }
            }
            if (!found){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] triplets={{2,5,3},{1,8,4},{1,7,5}};
        MergeTripletsToFormTarget mt= new MergeTripletsToFormTarget();
        System.out.println(mt.mergeTriplets(triplets, new int [] {2,7,5}));
    }
}
