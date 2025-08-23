package com.krushna.Java_rnd.neetcode.stack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**


Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:

Input: n = 1
Output: ["()"]

 

Constraints:

    1 <= n <= 8


 */
public class GenerateParentheses {
	
	/**
	 * Here we can use the backtracking algorithm, with below condition to check valid or in valid 
	 * 1. open bracket== close bracket == n
	 * 2. close bracket < open bracket
	 * 3. Start with open brackert
	 * 
	 * for this we need to have recursive function
	 * ((()))
	 * ((())
	 */
	public static List<String> generateParenthesis(int n) {
        List<String> reuslt= new ArrayList<String>();
        StringBuilder sb= new StringBuilder();
        backTracking(0,0,n,reuslt,sb);
        return reuslt;
    }
	
	
	
	public static void backTracking(int open, int close, int n, List<String> result, StringBuilder sb) {
		if(open==close && open ==n) {
			result.add(sb.toString());
			return;
		}if(open < n) {
			sb.append("(");
			backTracking(open+1, close,n,result, sb);
			sb.deleteCharAt(sb.length()-1);
		}if (close < open) {
			sb.append(")");
			backTracking(open, close+1,n,result, sb);
			sb.deleteCharAt(sb.length()-1);
		}
	}
	
	// This one easy to understand, with iterative approach using stack
	public static List<String> generateParenthesisIterative(int n) {
		List<String> result = new ArrayList<>();
		// Lets add the initial value to stack, and then we will continue till stack is
		// not empty.
		// In the stack we need to store current generated string, open bracket count,
		// close bracket count.
		// so Lets create a state record to have all these. 
		// Deque is the counter part Stack, from non-synchronized point of view
		Deque<State> stateStack = new LinkedList<>();
		stateStack.push(new State(0, 0, ""));

		while (!stateStack.isEmpty()) {
			State state = stateStack.pollLast();
			//State state = stateStack.pop();
			// if there is same number of open and close bracket and that match to n, we got
			// a proper combination
			if (state.open == state.close && state.open == n) {
				result.add(state.currentString);
				continue;
			}
			if (state.open < n) {
				stateStack.push(new State(state.open + 1, state.close, state.currentString + "("));
			}

			if (state.close < state.open) {
				stateStack.push(new State(state.open, state.close + 1, state.currentString + ")"));
			}
		}

		return result;
	}
    static record State(int open, int close, String currentString) {}

	public static void main(String[] args) {
		int n = 3;
		
		System.out.println("Recursive solution:");
		List<String> recursiveResult = generateParenthesis(n);
		System.out.println(recursiveResult);
		
		System.out.println("\nIterative solution:");
		List<String> iterativeResult = generateParenthesisIterative(n);
		System.out.println(iterativeResult);
		
		System.out.println("\nBoth solutions produce same result: " + 
			recursiveResult.equals(iterativeResult));
	}
}