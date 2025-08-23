package com.krushna.Java_rnd.neetcode.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.

Evaluate the expression. Return an integer that represents the value of the expression.

Note that:

    The valid operators are '+', '-', '*', and '/'.
    Each operand may be an integer or another expression.
    The division between two integers always truncates toward zero.
    There will not be any division by zero.
    The input represents a valid arithmetic expression in a reverse polish notation.
    The answer and all the intermediate calculations can be represented in a 32-bit integer.

 

Example 1:

Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9

Example 2:

Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6

Example 3:

Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

 */
public class EvaluateReversePolishNotation {

	/**
	 * The logic here, we will have a stack where we will push if it is number, as soon as we get a operator
	 * we will pop out two number and apply the operation and push the result. This will continue 
	 * 
	 */
	public static  int evalRPN(String[] tokens) {
		Stack<Integer> numberStack= new Stack<Integer>();
		String oprators="*-+/";
		for(String token:tokens) {
			// if it is operators
			
			if(oprators.contains(token)) {
				int num1=numberStack.pop();
				int num2=numberStack.pop();
				switch (token) {
				case "+" : {
					numberStack.push(num1+num2);
					break;
				}
				case "-" : {
					numberStack.push(num2-num1);
					break;
				}
				case "*" : {
					numberStack.push(num1*num2);
					break;
				}
				case "/" : {
					numberStack.push(num2/num1);
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + tokens);
				}
			}
			else
				numberStack.push(Integer.valueOf(token));
			
			System.out.println(numberStack);
		}
		return numberStack.pop();
	}
	
	public static void main(String[] args) {
		System.out.println(evalRPN ( new String[] {"4","13","5","/","+"}));
	}

}
