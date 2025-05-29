package com.krushna.Java_rnd.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindDuplicateL287Test {
	
	@BeforeAll
	public static void setUp() {
		System.out.println("some setup");
	}
	
	@BeforeEach
	public void eachTest() {
		System.out.println("some setup");
	}
	
	@Test
	void givenAllNumberDuplicate_whenFindDuplicate_ThenReturnTheSameNumber() {
		int nums[]= {3,3,3,3,3};
		int actualDuplicate=FindDuplicateL287.findDuplicate(nums);
		int expectedDuplicate=3;
		assertEquals(expectedDuplicate, actualDuplicate);
	}
	
	@Test
	void givenOnlyOneNumberDuplicate_whenFindDuplicate_ThenReturnTheSameNumber() {
		int nums[]= {1,2,3,4,5,5};
		int actualDuplicate=FindDuplicateL287.findDuplicate(nums);
		int expectedDuplicate=5;
		assertEquals(expectedDuplicate, actualDuplicate);
	}

}
