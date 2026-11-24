package com.krushna.Java_rnd.neetcode.math_geometry;

public class POWOfX {
    // to calculate 2^6, instead of multiplying 2 six times, we compute 2^3 and square the result 2ˆ3 * 2ˆ3
    public double myPow(double x, int n) {
        // base case
        if(n==0){
            return 1;
        }
        if(x==0){
            return 0;
        }
        double result=pow(x,Math.abs((long)n)); // make to long, to avoid the interger overflow error
        if(n <0){ // if n is negative make the result as 1/result
            return  1/result;
        }else {
            return  result;
        }
    }
    public double pow(double x, long n){
        if(n==1){
            return  x;
        }
        double result=pow(x, n/2); // optimisation just call one time
        if(n%2==0)
            result=result*result;
        else { // 1 reminder we need to multipy that also
            result=x * result*result;
        }
        return result;
    }

    public static void main(String[] args) {
        POWOfX powOfX= new POWOfX();
        System.out.println(powOfX.myPow(2,6) );
    }
}
