package com.krushna.Java_rnd.leetcode;

/**
 * 
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);

 

Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

Example 3:

Input: s = "A", numRows = 1
Output: "A"

 

Constraints:

    1 <= s.length <= 1000
    s consists of English letters (lower-case and upper-case), ',' and '.'.
    1 <= numRows <= 1000


 */
public class ZigzagConversionL6 {

	/**
	 * Here the formula to find the character for each rows will be like below
	 * for non middle row the incriment will be (row-1)*2
	 * 
	 * for the middle row we need capture extra char and that formula should be
	 * currentIndex+incriment-(2*row)
	 * 
0->P     I    N
1->A   L S  I G
2->Y A   H R
3->P     I



Each char position
P -> 0,A-> 1, Y -> 2, P -> 3
A-> 4, L-> 5, I-> 6, S->7, H-8, I -> 9
R-> 10, I -> 11, N -> 12, G -> 13


0    6      12
1  5 7   11 13
2 4  8  10
3    9

Row 0 -> 0, 6, 12
Row 1-> 1, 5,7, 11,13
Row 2-> 2,4,8,10
Row 3-> 3,9

Incriment formula (r-1)*2
Middle row extra char, currnetIndex+Incriment - 2*r
 
	 *  
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert(String s, int numRows) {
		StringBuilder zigZagVersion = new StringBuilder();
		if (numRows == 1)
			return s;
		else if (numRows <= 0)
			return null;
		// For other cases
		int incriment = (numRows - 1) * 2;
		for (int row = 0; row < numRows; ++row) {

			for (int i = row; i < s.length(); i += incriment) {
				zigZagVersion.append(s.charAt(i));

				if (row > 0 && row < numRows - 1 && (i + incriment - (2 * row)) < s.length()) {
					zigZagVersion.append(s.charAt(i + incriment - (2 * row)));
				}
			}
		}
		
		return zigZagVersion.toString();
		
	}

}
