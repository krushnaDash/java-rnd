package com.krushna.Java_rnd.neetcode.stack;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 
 * https://leetcode.com/problems/min-stack/description/

Implement the MinStack class:

    MinStack() initializes the stack object.
    void push(int val) pushes the element val onto the stack.
    void pop() removes the element on the top of the stack.
    int top() gets the top element of the stack.
    int getMin() retrieves the minimum element in the stack.

You must implement a solution with O(1) time complexity for each function.

The logic is to maintain another stack to have minimum value each element  
 */
public class MinStack {
	
	Stack<Integer> elementStack;
	Stack<Integer> corposndingMinStack;
	
	public MinStack() {
		elementStack= new Stack<>();
		corposndingMinStack= new Stack<>();
	}

	public void push(int val) {
		elementStack.push(val);
		if(!corposndingMinStack.isEmpty())
			corposndingMinStack.push(Math.min(corposndingMinStack.peek(), val));
		else
			corposndingMinStack.push(val);
	}

	public void pop() {
		elementStack.pop();
		corposndingMinStack.pop();
	}

	public int top() {
       return elementStack.peek();
	}

	public int getMin() {
		return corposndingMinStack.peek();
	}

}
