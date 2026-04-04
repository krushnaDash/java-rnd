package com.krushna.Java_rnd.neetcode.greedy;

import java.util.Arrays;

public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        // lets verify with sum
        int gasSum= 0;
        int costSum= 0;
        int[] diff= new int[gas.length];
        for(int i=0; i < gas.length; ++i){
            gasSum+=gas[i];
            costSum+=cost[i];
            diff[i]=gas[i]-cost[i];
        }
        if(costSum > gasSum){
            return  -1;
        }
        // check the diff ary is there any index which can upto end without -ve
        int total=0;
        int foundIndex=-1;
        for(int i=0; i < diff.length ; ++i){
            total+=diff[i];
            if(total<0){
                total=0;
                foundIndex=i+1;
            }
        }
        return  foundIndex;
    }



}
