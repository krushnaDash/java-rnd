package com.krushna.Java_rnd.neetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 
 * 
 * 
 * 

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Every close bracket has a corresponding open bracket of the same type.

 

Example 1:

Input: s = "()"

Output: true

Example 2:

Input: s = "()[]{}"

Output: true

Example 3:

Input: s = "(]"

Output: false

Example 4:

Input: s = "([])"

Output: true

Example 5:

Input: s = "([)]"

Output: false


 */

public class ValidParentheses {

	public static boolean isValid(String s) {
		Stack<Character> parenthesesStack = new Stack<>();
		Map<Character, Character> parenthessesMap = Map.of(')', '(', ']', '[', '}', '{');
		for (char c : s.toCharArray()) {
			// if parenthessesMap does not contain
			if (parenthessesMap.containsKey(c)) {
				// check the stack it has to match, if not match return false;
				if (parenthesesStack.isEmpty() || !parenthessesMap.get(c).equals(parenthesesStack.pop()))
					return false;
			} else {
				parenthesesStack.push(c);
			}
		}
		return parenthesesStack.isEmpty();
	}
	
	public static void main(String[] args) {
		System.out.println(isValid("()"));
		System.out.println(isValid("()[]{}"));
		System.out.println(isValid("(]"));
		System.out.println(isValid("([])"));
		System.out.println(isValid("([)]"));
	}

}
