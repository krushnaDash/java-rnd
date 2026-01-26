package com.krushna.Java_rnd.neetcode.heap;

import java.util.PriorityQueue;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

    For example, for arr = [2,3,4], the median is 3.
    For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.

Implement the MedianFinder class:

    MedianFinder() initializes the MedianFinder object.
    void addNum(int num) adds the integer num from the data stream to the data structure.
    double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

https://leetcode.com/problems/find-median-from-data-stream/description/
 */
class MedianFinder {
	// Small Heap will be implemented using maxHeap
	PriorityQueue<Integer> smallHeap;
	
	// large Heap will be implemented using a  minHeap
	PriorityQueue<Integer> largeHeap;
	
	// lets a have size variable also
	int size;
	public MedianFinder() {
		// maxHeap
		smallHeap= new PriorityQueue<>( (a,b) -> b-a);
		// minheap, default natural order 
		largeHeap= new PriorityQueue<>();
		size=0;
	}

	public void addNum(int num) {
		++size;
		// add to small heap first
		smallHeap.add(num);
		balanceHeaps(smallHeap,largeHeap);
		
	}
	public void balanceHeaps(PriorityQueue<Integer> smallHeap,PriorityQueue<Integer> largeHeap) {
		// compare the top element and balance them
		if(smallHeap.size()>=1 && largeHeap.size() >=1) {
			if(smallHeap.peek() > largeHeap.peek()) {
				// move the element from the small heap to large heap
				largeHeap.offer(smallHeap.poll());
			}
		}
		// now compare the size and balance them
		if(smallHeap.size() > largeHeap.size()+1) {
			// move element from small heap now
			largeHeap.offer(smallHeap.poll());
		}else if (largeHeap.size() > smallHeap.size()+1) {
			// move element from large heap
			smallHeap.offer(largeHeap.poll());
		}
				
	}

	public double findMedian() {
		// even size, take one from both and fine the median
		if (size % 2 == 0) {
			return (double) (smallHeap.peek() + largeHeap.peek()) / 2;
		} else {
			if (smallHeap.size() < largeHeap.size())
				return largeHeap.peek();
			else
				return smallHeap.peek();
		}
	}
}