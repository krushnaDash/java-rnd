package com.krushna.Java_rnd.neetcode.array_hashing;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/encode-and-decode-strings/description/
 * 
 * Design an algorithm to encode a list of strings to a single string. The
 * encoded string is then decoded back to the original list of strings.
 * 
 * Please implement encode and decode
 * 
 * Example 1:
 * 
 * Input: ["neet","code","love","you"]
 * 
 * Output:["neet","code","love","you"]
 * 
 * Example 2:
 * 
 * Input: ["we","say",":","yes"]
 * 
 * Output: ["we","say",":","yes"]
 * 
 * Constraints:
 * 
 * 0 <= strs.length < 100 0 <= strs[i].length < 200 strs[i] contains only UTF-8
 * characters.
 * 
 * 
 * 
 */

public class EncodeandDecodeStrings {
	
	
	public String encode(List<String> strs) {
	      String result="";
	      for(String s: strs){
	        result+= s.length()+"#"+ s;
	      }
	      return result;
	    }

	    public List<String> decode(String str) {
	    	List<String> result= new ArrayList<String>();
	    	for(char c: str.toCharArray() ) {
	    		
	    	}
	    	return null;
	    }

}
