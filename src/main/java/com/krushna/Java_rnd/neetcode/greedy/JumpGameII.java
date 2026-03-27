package com.krushna.Java_rnd.neetcode.greedy;

public class JumpGameII {

    public int jump(int[] nums) {
        Integer[] memo= new Integer[nums.length];
        //return  DFSWithDP(nums,0,memo);
        return  dpBottomUp(nums);
    }

    public int dpBottomUp(int[] nums){
        int min= nums.length+1; // max min possible
        int[] cache= new int[nums.length];
        for(int i =nums.length-2; i>=0; --i){
            // calculate the min for each step, the formula is min[i] =  1+ min(all reachable position)
            if(nums[i] <=0){
                cache[i]= nums.length+1;
            }
            // check all reachable position and choose the min
            int end= Math.max((nums.length-1 -i), nums[i]);

            for(int j=1; j <= end; ++j){
                min=Math.min(min, 1+ cache[i+j]);
            }
        }
        return cache[0];
    }
    //2,3,1,1,4
    public int DFSWithDP(int[] nums, int i, Integer[] memo){
        if(i>= nums.length-1){
            return  0; // reached no more jump
        }
        if(memo[i] !=null){
            return memo[i];
        }
        int min=100000; // not use the MaxValue, as MaxValue +1 will become -ve
        if(nums[i]<=0){
            return 100000;
        }
        // find the end index, no need to do till total size
        int end= Math.min(nums.length-1, i+nums[i]);
        for(int j=1; j <= end; ++j){
            //Find the minimum jumps needed from any of the positions I can jump to,
            // then add 1 for the current jump
            // not min= 1+Math.min(min, DFSWithDP(nums, i +j,memo)), this will add extra  1 to min
            int value=DFSWithDP(nums, i +j,memo);
            min = Math.min(min,  1+DFSWithDP(nums, i +j,memo)); // 1+
        }
        memo[i]=min;
        return  min;
    }


    public static void main(String[] args) {
        int[] nums={2,3,1,1,4};
        JumpGameII jg= new JumpGameII();
        System.out.println(jg.jump(nums));
    }
}
