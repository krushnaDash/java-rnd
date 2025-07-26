package com.krushna.Java_rnd.neetcode.array_hashing;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate/
 * 
 * Given an integer array nums, return true if any value appears at least twice in the array, 
 * and return false if every element is distinct.
 * 
 */
public class ContainsDuplicateL217 {
	public boolean containsDuplicate(int[] nums) {
		Set<Integer> numSet= new HashSet<Integer>();
		for(int i: nums) {
			if(!numSet.add(i))
				return true;
		}
		return false;
	}

}
