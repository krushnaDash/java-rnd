package com.krushna.Java_rnd.neetcode.bitManipulation;

public class NumberOf1Bits {

    public int hammingWeight(int n) {
        int count=0;
        while(n!=0){
                int rem=n%2;
                if(rem==1){
                    count++;
                }
                // or can say simply count=count+ n%2 , since n%2== 1 or 0
                //n=n/2; instead of this we can use shift operator also
            n= n>>1;
        }
        return count;
    }

    // another solutions is using bit masking and count the 1
    // this is most optimised one
    public int hammingWeightv2(int n) {
        int count=0;
        while(n!=0){
            n= n & (n-1);
            ++count;
        }
        return count;
    }

}
