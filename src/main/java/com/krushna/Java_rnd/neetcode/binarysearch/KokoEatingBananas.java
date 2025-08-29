package com.krushna.Java_rnd.neetcode.binarysearch;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/koko-eating-bananas/description/
 * 
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

 

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4

Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30

Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23

 
 */
public class KokoEatingBananas {

	public static int minEatingSpeed(int[] piles, int h) {
	
		 int lb=1;
		 // the upper bound should be the max value in pile
		 int ub= IntStream.of(piles).max().getAsInt();
		 int k=ub;
		 
		 while(lb <= ub) {
			 int mid=(lb+ub)/2;
			 int time=findTime(piles, mid);
			 if(time >h) {
				 lb=mid+1;
			 }else {
				 k=mid;
				 ub=mid-1;
			 }
		 }
		 return k;
	 
	
	}
	public static int findTime(int[] piles, int k) {
		int time=0;
		for(int p : piles) {
			time+=Math.ceil( (double) p/k);
		}
		return time;
	}
	
	public static int minEatingSpeedv2(int[] piles, int h) {
        int l = 1;
        int r = Arrays.stream(piles).max().getAsInt();
        int res = r;

        while (l <= r) {
            int k = (l + r) / 2;

            long totalTime = 0;
            for (int p : piles) {
                totalTime += Math.ceil((double) p / k);
            }
            if (totalTime <= h) {
                res = k;
                r = k - 1;
            } else {
                l = k + 1;
            }
        }
        return res;
    }
	
	public static void main(String[] args) {
		System.out.println(minEatingSpeedv2(new int[] {805306368,805306368,805306368}, 1000000000));
		System.out.println(minEatingSpeed(new int[] {805306368,805306368,805306368}, 1000000000));
		
		System.out.println(minEatingSpeed(new int[] {2,2}, 2));
		System.out.println(minEatingSpeed(new int[] {3,6,7,11}, 8));
		System.out.println(minEatingSpeed(new int[] {30,11,23,4,20}, 5));
		System.out.println(minEatingSpeed(new int[] {30,11,23,4,20}, 6));
		
		System.out.println(minEatingSpeed(new int[] {332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184}, 823855818));
		
		
	}

}
