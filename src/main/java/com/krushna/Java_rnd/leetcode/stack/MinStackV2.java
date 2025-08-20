package com.krushna.Java_rnd.leetcode.stack;

import java.util.Stack;

// We will try to solve the min stack problem using single stack
// by storing the  value
// solve this , it has some error
public class MinStackV2 {

	Stack<Integer> elementStack;
	int min = Integer.MAX_VALUE;

	public MinStackV2() {
		elementStack = new Stack<>();
	}

	public void push(int val) {
		if (elementStack.isEmpty()) {
			elementStack.push(0);
			min = val;
		} else {
			elementStack.push(val - min);
			min = Math.min(val, min);
		}
	}

	public void pop() {
		int element = elementStack.pop();
		int val = element + min;
		min = Math.min(val, min);
	}

	public int top() {
		int element = elementStack.peek();
		int val = element + min;
		min = Math.min(val, min);
		return val;
	}

	public int getMin() {
		return min;
	}
}
