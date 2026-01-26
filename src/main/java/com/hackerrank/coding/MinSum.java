package com.hackerrank.coding;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an array of integers nums, perform k operations to minimize the sum of
 * the elements. Each operation consists of:
 * 
 * Removing an element from the array Dividing it by 2 Inserting the ceiling of
 * the result back into the array Return the minimum possible sum after k
 * operations.
 * 
 * 
 * 
 * Example 1
 * 
 * Input: n = 3 elements, nums = [10, 20, 7], k = 4
 * 
 * Output: 14
 * 
 * Explanation:
 * 
 * The ceiling of 7/2 = 4 → [10, 20, 4] The ceiling of 10/2 = 5 → [5, 20, 4] The
 * ceiling of 20/2 = 10 and 10/2 = 5→ [5, 5, 4] The sum of the final array is 5
 * + 5 + 4 = 14. Example 2
 * 
 * Input: n = 2 elements, nums = [2, 3], k = 1
 * 
 * Output: 4
 * 
 * Explanation: Whether we choose 2 or 3, the answer is the same.
 * 
 * The ceiling of 2/2 = 1, 1 + 3 = 4 The ceiling of 3/2 = 2, 2 + 2 = 4
 */

public class MinSum {
	
	public static int minSum(List<Integer> num, int k) {
	    // Write your code here
	        PriorityQueue<Integer> maxHeap= new PriorityQueue<>( (n1, n2) -> n2 - n1 );
	        for(int n: num){
	            maxHeap.offer(n);
	        }
	        int ctr=0;
	        while(ctr < k){
	            int n= maxHeap.poll();
	            int result= (int) Math.ceil((double)n/2);
	            maxHeap.offer(result);
	            ++ctr;
	        }
	        int sum=0;
	        while(!maxHeap.isEmpty()){
	            sum+=maxHeap.poll();
	        }
	    return sum;

	    }
		public static void main(String[] args) {
			System.out.println( String.valueOf(0d));
		}

}
