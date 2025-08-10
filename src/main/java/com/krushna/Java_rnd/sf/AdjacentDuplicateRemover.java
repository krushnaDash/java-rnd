package com.krushna.Java_rnd.sf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Question - for each word in a list of words , if any two adjacent characters
 * are equal , change one of them. 
 * 
 * determine the minimum number of substitutions so the final string contains no adjacent equal characters. 
 * EXAMPLE: words = ["add", "book", "break"] 1. "add": change one d(one change) 2. "boook" :
 * 
 * change the middle o(1 change) 3. "break": no changes are necessary(0change)
 * 
 * the return array is [1,1,0] complete the function
 */
public class AdjacentDuplicateRemover {
	
	public static List<Integer> minimalOperations(List<String> words) {
		// Base case
		if(words.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<Integer> result= new ArrayList<Integer>();
		for(String word : words) {
			int count=0;
			char [] chars=word.toCharArray();
			// get all the chars to check
			for(int i=0; i< chars.length-1; ++i) {
				if(chars[i]== chars[i+1]) {
				 // adjacent characters same
					count++;	
					
				// now find a replacement we can start with a, we will check that it is not same with i+1 and i+2
					
				char newChar='a';
				while(newChar == chars[i+1] ||  ((i+2 < chars.length) && newChar==chars[i+2])) {
					newChar++;
				}
				chars[i+1]=newChar;
				}
			}
			result.add(count);
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(minimalOperations(Arrays.asList(new String[] {"add", "book", "break"})));
	}

}
