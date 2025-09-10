package com.hackerrank.coding;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 
Disk Space Analysis

For each segment of contiguous computers of length x, find the minimum available disk space within that segment. Then determine the maximum value among all these minimums.
 
Example 1
Input: x = 2, the segment length, n = 4, the number of computers, space = [8, 2, 4, 6]
Output: 4
Explanation: The segments of length 2 are [8, 2], [2, 4], and [4, 6]. The minimum values in each segment are 2, 2, and 4, respectively. The maximum of these values is 4.
 
Example 2
Input: x = 1, n = 5, space = [1, 2, 3, 1, 2]
Output: 3
Explanation: The segments of size x = 1 are [1], [2], [3], [1], and [2]. Each value is minimal within its segment.
 
Constraints
1 ≤ n ≤ 106
1 ≤ x ≤ n
1 ≤ space[i] ≤ 109
 */
public class DiskSpaceAnalysis {
	
	/**
	 * we can use A monotonic queue is a special type of queue where elements are always kept
	 * in either non-decreasing or non-increasing order from front to back, achieved
	 * by removing elements that violate this property whenever a new element is
	 * added. 
	 * 
	 * This structure is commonly implemented using a double-ended queue
	 * (deque) and is particularly useful for efficiently solving problems like
	 * sliding window maximum/minimum queries, where it allows for linear time
	 * complexity
	 */
	
	
	public static int segment(int x, List<Integer> space) {
		// base case
		if (space == null || space.isEmpty()) {
			return -1;
		}
		int finalMax = Integer.MIN_VALUE;
		int p = 0;
		int q = 0;
		// we will store the index here
		Deque<Integer> stack = new LinkedList<>();
		while (q < space.size()) {
			// remove form the stack if the element are greater then this
			// since this is monotonic stack with increasing order
			while (!stack.isEmpty() && space.get(stack.peek()) > space.get(q)) {
				stack.pop();
			}

			stack.push(q);
			// got the window, pick min value from the stack
			// we have the window update the max value
			if ((q - p + 1) == x) {
				finalMax = Math.max(finalMax, space.get(stack.getLast()));
				++p;
				// if p index not belongs to stack then remove the element
				if (stack.getLast() < p) {
					stack.removeLast();
				}
			}
			++q;
		}
		return finalMax;

	}


}
