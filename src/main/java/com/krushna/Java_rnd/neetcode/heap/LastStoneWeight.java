package com.krushna.Java_rnd.neetcode.heap;

import java.util.PriorityQueue;

/**
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:

    If x == y, both stones are destroyed, and
    If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.

At the end of the game, there is at most one stone left.

Return the weight of the last remaining stone. If there are no stones left, return 0.

https://leetcode.com/problems/last-stone-weight/description/

 */
public class LastStoneWeight {

	public int lastStoneWeight(int[] stones) {
		if(stones.length ==1) {
			return stones[0];
		}
		
		PriorityQueue<Integer> maxHeap= new PriorityQueue<Integer>( (o1,o2) -> o2-o1);
		for(int stone: stones) {
			maxHeap.offer(stone);
		}
		while(!maxHeap.isEmpty()) {
			int stone1=maxHeap.poll();
			int stone2=maxHeap.poll();
			if(stone1!=stone2) {
				maxHeap.offer(stone1-stone2);
			}
			if(maxHeap.size() ==1) {
				return maxHeap.poll();
			}
		}
		return 0;
		
	}

}
