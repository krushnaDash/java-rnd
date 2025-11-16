package com.krushna.Java_rnd.neetcode.heap;

import java.util.PriorityQueue;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).


K closet points to origin

If we create a heap for each point re-presenting distance from the centre and store them in min heap 
We will have the heap size as K, if size becomes greater then K > poll from heap.

We will create a max heap., so that max distance elements will be removed

 */
public class KClosestPointsToOrigin {

	public int[][] kClosest(int[][] points, int k) {
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
				(a, b) -> Double.compare(getDistance(b[0], b[1]), getDistance(a[0], a[1])));

		for (int[] point : points) {
			maxHeap.offer(point);
			if (maxHeap.size() > k)
				maxHeap.poll();
		}
		int[][] closePoints = new int[k][2];
		int i = 0;
		while (!maxHeap.isEmpty()) {
			closePoints[i] = maxHeap.poll();
			++i;
		}
		return closePoints;
	}

	public double getDistance(int x, int y) {
		return Math.sqrt(x * x + y * y);
	}

}
