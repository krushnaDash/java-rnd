package com.krushna.Java_rnd.neetcode.array_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 * 
 * 
 * Given an integer array nums and an integer k, return the k most frequent
 * elements. You may return the answer in any order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1,2,2,3], k = 2 Output: [1,2]
 * 
 * Example 2:
 * 
 * Input: nums = [1], k = 1 Output: [1]
 * 
 * 
 * 
 */
public class TopKFrequentElementsL347 {
	
	
	/**
	 * We will use minHeap here, and what we will do hear, from the queue we will remove element 
	 * if it's size > K in this way, our queue will have only needed element
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int[] topKFrequentWithMinHeap(int[] nums, int k) {
		// Create the count map
		Map<Integer, Integer> countMap= new HashMap<Integer, Integer>();
		
		for(int i: nums) {
			countMap.put(i, countMap.getOrDefault(i, 0)+1);
		}
		
		// create the min heap and store only K element
		PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<Map.Entry<Integer, Integer>>(
				Comparator.comparing(Map.Entry::getValue));
		
		// add element to min heap and remove extra element
		
		for(Map.Entry<Integer, Integer> entries: countMap.entrySet()) {
			minHeap.offer(entries);
			if(minHeap.size() > k) {
				minHeap.poll();// remove the small element
			}
		}
		
		
		// create an array and return the result
		
		int[] result= new int[k];
		
		for(int i=result.length-1; i>=0; --i) {
			result[i]=minHeap.poll().getKey();
		}
		return result;
		
	}

	
	/**
	 * This approach using PriorityQueue to peek top value with MaxHeap
	 * @param nums
	 * @param k
	 * @return
	 */
	public int[] topKFrequent(int[] nums, int k) {
		// create frequency map
		
		Map<Integer, Integer> frequencyMap= new HashMap<>();
		PriorityQueue<Integer[]> frequencyQueue= new PriorityQueue<>(
				Comparator.comparing(  a -> a[1], Comparator.reverseOrder() )
				);
	    
		//PriorityQueue<Integer[]> frequencyQueue1 = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		
				
		// O(n)
		for(int num:nums) {
			frequencyMap.put(num, frequencyMap.computeIfAbsent(num, ignore -> 1)+1);
		}
		// Priority Queue Insertion time complexities is (O(log n))
		// So for N insertion it should be O(nlogn)
		
		for( Entry<Integer, Integer> entries: frequencyMap.entrySet() )
			frequencyQueue.add(new Integer[] {entries.getKey(), entries.getValue()});
		
		int[] result= new int[k];
		for(int i=0; i < k ; ++i) {
			// O(1)
			result[i]=frequencyQueue.poll()[0];
		}
		return result;
	}
	
	/**
	 * Lets build the same count map and instead storing in priority queue we will use bucket sort
	 * Which means  we create array where the index will be the count and value list of element 
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int[] topKFrequentOptimized(int[] nums, int k) {
		Map<Integer, Integer> countMap= new HashMap<>();
		for(int i : nums) {
			countMap.put(i,countMap.getOrDefault(i, 0)+1);
		}
		
		// We need extra one size as here the count is index, the min count is 1 and we need ignore the 0 index
		List<List<Integer>> countBucket = new ArrayList<>(nums.length+1);
		// fill with Empty list
		for(int i=0; i < nums.length+1; ++i) {
			countBucket.add(new ArrayList<>());
		}
		
		for(Map.Entry<Integer, Integer> entry: countMap.entrySet()) {
			int key=entry.getKey();
			int value=entry.getValue();
			countBucket.get(value).add(key);
			
		}
		
		int[] result= new int[k];
		int index=0;
		
		for( int j =countBucket.size()-1 ; j>0 && index <k; --j ) {
			if(countBucket.get(j).isEmpty())
				continue;
			for(int i: countBucket.get(j)) {
				result[index]=i;
				++index;
			}
		}
		
		
		return result;
		
	}
	
	public static void main(String[] args) {
		int[] result=topKFrequentWithMinHeap(new int[] {1,2,3,1,3,3,3,4,4,4,5}, 3) ;
		System.out.println(Arrays.toString(result));
	}

}
