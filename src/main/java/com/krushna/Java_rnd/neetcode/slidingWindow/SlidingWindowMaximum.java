package com.krushna.Java_rnd.neetcode.slidingWindow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/sliding-window-maximum/description/
 * 
 * 

You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array 
to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

 

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Example 2:

Input: nums = [1], k = 1
Output: [1]

 

Constraints:

    1 <= nums.length <= 105
    -104 <= nums[i] <= 104
    1 <= k <= nums.length



 */
public class SlidingWindowMaximum {
	
	
	   // More Optimise
	    // Here is logic, in maxHeap we store both number and index, if the max number index in the current window
	    // no need to remove any number
	    // if the max number index is not part of current window then we have to remove element till the max number
	    // is part of current window.
	
		public static int[] maxSlidingWindowv3(int[] nums, int k) {
			// we can have maxHeap to find max element
			// the array of size nums.lnegth-k +1
			int[] output = new int[nums.length - k + 1];
			PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
					(o1, o2) -> o2[0]-o1[0] );

			// add element to maxHeap
			for (int i = 0; i < k; ++i) {
				maxHeap.add(new int[] {nums[i],i});
			}
			output[0]=maxHeap.peek()[0];

			// now run the window
			int l = 0;
			int r = k;
			int idx=1;

			while (r < nums.length) {
				
				maxHeap.add(new int[] {nums[r],r}); // add the right element
				int[] crrentMax=maxHeap.peek();
				++l;
				if( l <= crrentMax[1] && r>=crrentMax[1]) {
					output[idx]=crrentMax[0];
				}else {
					// remove the element till max element index in window
					maxHeap.poll();
					int[] el=maxHeap.poll();
					
					while( el[1] >r || el[1] <l) {
						el=maxHeap.poll();
					}
					// the current is the max and it is in window to use it
					output[idx]=el[0];
					// add back element to maxHeap 
					maxHeap.add(el);
				}
				++r;
				++idx;
			}
			
			return output;

		}

	
	// Optimise
	public static int[] maxSlidingWindowv2(int[] nums, int k) {
		// we can have maxHeap to find max element
		List<Integer> maxSlide = new ArrayList<Integer>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());

		// add element to maxHeap
		for (int i = 0; i < k; ++i) {
			maxHeap.add(nums[i]);
		}
		maxSlide.add(maxHeap.peek());

		// now run the window
		int l = 0;
		int r = k;

		while (r < nums.length) {
			maxHeap.remove(nums[l]); // remove the left element
			maxHeap.add(nums[r]); // add the right element
			maxSlide.add(maxHeap.peek()); // add the max element
			++l;
			++r;
		}
		System.out.println(maxSlide);
		return maxSlide.stream().mapToInt(i -> i).toArray();

	}
	
	
	public static int[] maxSlidingWindow(int[] nums, int k) {
		// we will create the List of integer, since we do not know the size.
		int max1=Integer.MIN_VALUE;
		
		List<Integer> maxSlide= new ArrayList<Integer>();
		
		// find the max in in first window
		
		for(int i=0; i < k; ++i) {
			max1=Math.max(max1, nums[i]);
		}
		maxSlide.add(max1);
		
		// now run the window
		int l=0;
		int r=k;
		
		while(r< nums.length) {
			// find the max for new window
			// check adding number
			if(nums[r] > max1) {
				max1=nums[r]; // no need to check whole window
			// check the leaving number
			}else if(nums[l] == max1) {
				// we need to use the max2 now
				max1=findMax(nums, l+1, r);
			}
			maxSlide.add(max1);
			++l;++r;
		}
		System.out.println(maxSlide);
		return maxSlide.stream().mapToInt(i ->i).toArray();
		
		
	}
	
	public static int findMax(int [] nums, int l, int r) {
		int max=Integer.MIN_VALUE;
		for( int i=l; i<=r; ++i) {
			max=Math.max(max, nums[i]);
		}
		return max;
	}
	public static void main(String[] args) {
		//System.out.println(maxSlidingWindowv3(new int [] {1,3,-1,-3,5,3,6,7}, 3));
		
		System.out.println(maxSlidingWindowv3(new int [] {1,-1}, 1));
		
		
		
	}

}
