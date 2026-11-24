package com.krushna.Java_rnd.neetcode.bitManipulation;

public class ReverseBits {

    public int reverseBits(int n) {
        int res = 0;
        // Loop exactly 32 times to handle all bits, including leading zeros
        for (int i = 0; i < 32; i++) {
            int bit = n & 1;
            // Use parentheses to force correct operator precedence
            res = res + (bit << (31 - i));
            // Use unsigned right shift to handle negative numbers safely
            n = n >>> 1;
        }
        return res;
    }



    public static void main(String[] args) {
        ReverseBits rb= new ReverseBits();
        System.out.println(rb.reverseBits(4));
    }

}
