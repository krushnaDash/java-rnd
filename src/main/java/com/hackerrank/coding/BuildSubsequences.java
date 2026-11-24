package com.hackerrank.coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BuildSubsequences {
	
/**
 * A subsequence is obtained by deleting zero or more characters from the string while maintaining the original order. Your task is to generate an array of all subsequences sorted in alphabetically ascending order, omitting the empty string.

 

Example 1
Input: s = 'xyz'

Output: ['x', 'xy', 'xyz', 'xz', 'y', 'yz', 'z']

 

Example 2
Input: s = 'ba'

Output: ['a', 'b', 'ba']

 

Constraints
1 < length of s < 16
s is a string of distinct lowercase English letters ascii[a-z].

 * @param s
 * @return
 */
	
	public static List<String> buildSubsequences(String s) {
		// Approach: build the answer one character at a time with plain for loops.
		//
		// Invariant: after handling s[0..i], result holds every non-empty subsequence of s[0..i].
		// When the next character c arrives, each of those either stays as it is or gets c on its end.
		// So: append c to every existing subsequence (these are the new ones), then add c on its own.
		// The list grows 1, 3, 7, ... -> 2^n - 1 in total (32767 for n = 15).
		List<String> result = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// Freeze the size first. We add to the list while looping over it, and we must only extend
			// the subsequences that existed BEFORE c, otherwise j never reaches the (growing) end of the list.
			int size = result.size();
			for (int j = 0; j < size; j++) {
				result.add(result.get(j) + c);
			}

			result.add(String.valueOf(c));
		}

		// Insertion order is not alphabetical ("xyz" gives x, xy, y, xz, xyz, yz, z), so sort.
		// String.compareTo is lexicographic and puts a prefix before its extensions ("x" < "xy").
		Collections.sort(result);
		return result;
	}

	/**
	 * Same build-up idea as buildSubsequences, but the results live in a TreeSet instead of a List.
	 * A TreeSet keeps its strings sorted and unique, so there is no Collections.sort call,
	 * and it also stays correct if letters repeat (e.g. "aa" gives a, aa instead of a, a, aa).
	 */
	public static List<String> buildSubsequencesTreeSet(String s) {
		Set<String> result = new TreeSet<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// Loop over a snapshot: adding to a set while iterating over it throws ConcurrentModificationException,
			// and (like the frozen size in the List version) we only want to extend what existed BEFORE c.
			for (String prev : new ArrayList<>(result)) {
				result.add(prev + c);
			}

			result.add(String.valueOf(c));   // c on its own, added after the loop so c is never extended with itself
		}
		return new ArrayList<>(result);
	}

	/**
	 * Same result and complexity as buildSubsequences, but with recursion instead of loops over the results.
	 */
	public static List<String> buildSubsequencesBacktracking(String s) {
		// Approach: backtracking to generate every subsequence, then sort.
		//
		// A subsequence is a set of indices picked left to right. So we build it one character at a time:
		// choose a first index i, then optionally a later index j > i, and so on.
		// Every non-empty string we build along the way is itself a valid subsequence, so we record each one.
		// n distinct letters -> 2^n - 1 non-empty subsequences (at most 2^15 - 1 = 32767 here).
		List<String> result = new ArrayList<>();
		backtrack(s, 0, new StringBuilder(), result);

		// DFS emits in input order, which is not alphabetical when s is unsorted ("ba" -> b, ba, a),
		// so sort at the end. String.compareTo is lexicographic and puts a prefix before its extensions ("x" < "xy"),
		// which is exactly the expected order: x, xy, xyz, xz, y, yz, z.
		Collections.sort(result);
		return result;
	}

	/**
	 * Tries every index i >= start as the next character of the current subsequence.
	 * Recursing with i + 1 means we only ever move right, so the original order is kept and nothing repeats.
	 */
	private static void backtrack(String s, int start, StringBuilder current, List<String> result) {
		for (int i = start; i < s.length(); i++) {
			current.append(s.charAt(i));                  // choose s[i]
			result.add(current.toString());               // current is a non-empty subsequence, record it
			backtrack(s, i + 1, current, result);         // extend it with characters to the right of i
			current.deleteCharAt(current.length() - 1);   // un-choose s[i] so the next i starts from the same prefix
		}
	}

}
