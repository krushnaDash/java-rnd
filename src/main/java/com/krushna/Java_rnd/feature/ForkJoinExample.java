package com.krushna.Java_rnd.feature;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * It’s built for CPU-bound, non-blocking, short-lived tasks. Why only
 * non-blocking tasks? this because it uses a work-stealing algorithm. and fixed
 * set of threads. what is work-stealing algorithm? work-stealing algorithm is a
 * scheduling algorithm that allows idle threads to "steal" tasks from busy
 * threads, improving load balancing and resource utilization.
 */
public class ForkJoinExample {
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(10);
		int[] arr = IntStream.range(1, 10001).toArray();

		// Blocking
		int result = forkJoinPool.invoke(new MyTask(arr, 0, arr.length));
		System.out.println(result);
		System.out.println((10000 * (10000 + 1)) / 2);

		// non blocking
		ForkJoinTask<Integer> resultTask = forkJoinPool.submit(new MyTask(arr, 0, arr.length));
		System.out.println(resultTask.join());
		// we can submit other tasks while waiting for the result
		forkJoinPool.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello from another task");

			}
		});
	}
}

class MyTask extends RecursiveTask<Integer> {
	int[] arr;
	int start, end;

	public MyTask(int[] arr, int start, int end) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		if (end - start <= 5) {
			// base case: compute directly
			return Arrays.stream(arr, start, end).sum();
		} else {
			int mid = (start + end) / 2;
			MyTask left = new MyTask(arr, start, mid);
			MyTask right = new MyTask(arr, mid, end);
			left.fork(); // run in parallel
			int rightResult = right.compute(); // run current
			int leftResult = left.join(); // wait for left
			return leftResult + rightResult;
		}
	}
}
