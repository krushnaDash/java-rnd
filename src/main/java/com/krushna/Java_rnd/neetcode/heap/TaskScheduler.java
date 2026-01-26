package com.krushna.Java_rnd.neetcode.heap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/task-scheduler/description/ You are given an
 * array of CPU tasks, each labeled with a letter from A to Z, and a number n.
 * Each CPU interval can be idle or allow the completion of one task. Tasks can
 * be completed in any order, but there's a constraint: there has to be a gap of
 * at least n intervals between two tasks with the same label.
 * 
 * Return the minimum number of CPU intervals required to complete all tasks.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * 
 * Output: 8
 * 
 * Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A ->
 * B.
 * 
 * After completing task A, you must wait two intervals before doing A again.
 * The same applies to task B. In the 3rd interval, neither A nor B can be done,
 * so you idle. By the 4th interval, you can do A again as 2 intervals have
 * passed.
 * 
 * 
 * 
 */
public class TaskScheduler {

	/**
	 * The idea here is we will create a count map and process the max one using a max heap
	 * Then we will use queue store the task, when the next it can be process.
	 * @param tasks
	 * @param n
	 * @return
	 */
	// example to run "A,B,A" n=2
	public int leastInterval(char[] tasks, int n) {
		Map<Character, Integer> countMap = new HashMap<>();
		for (char c : tasks) {
			countMap.put(c, countMap.getOrDefault(c, 0) + 1);
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
		maxHeap.addAll(countMap.values());
		Queue<int[]> taskQueue = new LinkedList<>();
		int time = 0;
		while (!maxHeap.isEmpty() || !taskQueue.isEmpty()) {
			// check if any tasks from the queue can be process, if yes add them to the
			// heap.

			if (!taskQueue.isEmpty() && taskQueue.peek()[1] == time) {
				maxHeap.offer(taskQueue.poll()[0]);
			}
			// move the time
			time = time + 1;
			// check the heap and process
			if (!maxHeap.isEmpty()) {
				// take task with high count
				int task = maxHeap.poll();
				// task is process.
				task = task - 1;
				if (task != 0) {
					// add the task to the queue with the time when again it should get process.
					taskQueue.offer(new int[] { task, time + n });
				}
			}
		}
		return time;

    }
}
