package com.krushna.Java_rnd.neetcode.bitManipulation;

public class SumofTwoIntegers {
    // do not use the + or - operator
    // we will fix this tomorrow
    public int getSum(int a, int b) {
       // use XOR for the sum and & operation for carry
        while(b!=0){
            int carry= (a & b) << 1; // shift by 1 bit to get the carry
            a= a^b; // store the sum without carry in a
            b=carry;
        }
        return  a;
    }

    public static void main(String[] args) {
        SumofTwoIntegers st= new SumofTwoIntegers();
        System.out.println(st.getSum(2,3));
    }

}
