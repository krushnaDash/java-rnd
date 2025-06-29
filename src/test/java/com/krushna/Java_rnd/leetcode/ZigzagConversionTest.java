package com.krushna.Java_rnd.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ZigzagConversionTest {
	
	static ZigzagConversionL6 conversionL6;
	
	@BeforeAll
	static void setup() {
		conversionL6= new ZigzagConversionL6();
	}
	
	@Test
	void givenRowSizeOne_whenConvert_ThenRetrunSameString() {
		String expected="HelloWorld";
		String actual=conversionL6.convert(expected, 1);
		assertEquals(expected, actual);
	}
	
	@Test
	void givenRowSizeZero_whenConvert_ThenRetrunNull() {
		String expected=null;
		String actual=conversionL6.convert(expected, 0);
		assertEquals(expected, actual);
	}
	
	@Test
	void givenPAYPALISHIRINGWithNumRow3_whenConvert_ThenReturnPINALSIGYAHRPI() {
		String expected="PAHNAPLIGYIR";
		String actual=conversionL6.convert("PAYPALISHIRING", 3);
		assertEquals(expected, actual);
	}
	
	@Test
	void givenPAYPALISHIRINGWithNumRow4_whenConvert_ThenReturnPINALSIGYAHRPI() {
		String expected="PINALSIGYAHRPI";
		String actual=conversionL6.convert("PAYPALISHIRING", 4);
		assertEquals(expected, actual);
	}
	
	

}
