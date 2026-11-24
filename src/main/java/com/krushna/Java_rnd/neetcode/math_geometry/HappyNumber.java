package com.krushna.Java_rnd.neetcode.math_geometry;


import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number n is happy.
 * A happy number is a number defined by the following process:
 * * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 */
public class HappyNumber {

    // using a slow and fast pointer to detect cycle, if they meet and then there is cycle
    // if any number is 1, return true else false
    public boolean isHappyv1(int n) {
        int slow=n;
        int fast=squrSum(n);
        while (slow!=fast){
            if(slow==1 || fast==1){
                return true; // this check can be removed
            }
            slow=squrSum(slow);
            fast=squrSum(fast);
            fast=squrSum(fast);
        }// when loop end there is a cycle, we can check if slow==1 then return true, else false
        return slow==1;
    }

    public boolean isHappy(int n) {
        Set<Integer> seenNumber= new HashSet<>();
        while (true){
            if(n==1){
                return true;
            }
            if(seenNumber.contains(n)){
                return false;
            }
            seenNumber.add(n);
            n=squrSum(n);
        }
    }

    public int squrSum(int n){
        int sum=0;
        while (n >0){
            int d=n%10;
            sum= sum+ (d*d);
            n=n/10;
        }
        return sum;
    }

    public static void main(String[] args) {
        HappyNumber hn= new HappyNumber();
        System.out.println(hn.squrSum(145));
    }
}
