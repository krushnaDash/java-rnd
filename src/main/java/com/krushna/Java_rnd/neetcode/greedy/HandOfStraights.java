package com.krushna.Java_rnd.neetcode.greedy;

import java.util.*;

public class HandOfStraights {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        // do a basic check, hand size has to be multiple of groupsize
        if(hand.length%groupSize !=0){
            return  false;
        }
        //return  usingSorting(hand,groupSize);
        return  usingMinHeap(hand,groupSize);

    }

    public boolean usingMinHeap(int[] hand, int groupSize){
        Queue<Integer> minHeap= new PriorityQueue<>();
        Map<Integer, Integer> countMap= new HashMap<>();
        // offer the element to the queue and create the count Map also
        // lets not have duplicate in minHeap, we will remove the element only when it count is 0
        for(int i: hand){
            // do a check an offer
            if(!countMap.containsKey(i)){
                minHeap.offer(i);
            }
            countMap.put(i, countMap.getOrDefault(i,0)+1);
        }
        while (!minHeap.isEmpty()){
            int n1=minHeap.peek();
            // check the count Map for the group size
            for(int i=n1; i<n1+groupSize; ++i){
                int count=countMap.getOrDefault(i,0);
                if(count <=0){
                    return false;
                }
                // update the count, if its 0 poll it from minHeap
                count=count-1;
                countMap.put(i,count);
                if(count==0){
                    // check here i should be the min element
                    if(minHeap.peek() !=i){
                        return false;
                    }
                    minHeap.poll();
                }
            }
        }
        return  true;
    }

    public boolean usingSorting(int[] hand, int groupSize){
        int groupCount=hand.length/groupSize;
        // Lets create a mapping with number and their count
        Map<Integer, Integer> countMap= new HashMap<>();
        Arrays.sort(hand); // always start from min num
        // which means if we can not create a group from that num then we can return false
        // no need to check more
        for(int i: hand){
            countMap.put(i, countMap.getOrDefault(i,0)+1);
        }
        // start creating the group, total elements, groupPosition.
        // try to pick the first element and see if group can be created or not, like bruteforce
        int gc=0;
        for(int n1: hand){
            int count=countMap.getOrDefault(n1,0);
            // create the group
            if(count<=0){
                //skip it is used
                continue;
            }
            countMap.put(n1,countMap.getOrDefault(n1,0)-1);
            for(int i=1; i<=groupSize-1; ++i){
                // look for n1+1 and update count, not found break the loop
                int nextNum=n1+i;
                int ni=countMap.getOrDefault(nextNum,0);
                if(ni<=0){
                    return  false;// no need to check further
                }
                countMap.put(nextNum,countMap.getOrDefault(nextNum,0)-1);
            }

        }
        // all match found
        return true;
    }

    public static void main(String[] args) {
        int[] hand= {1,2,3,6,2,3,4,7,8};
        int [] hand2={1,2,3,4,5,6};
        int[] hand3= {8,8,9,7,7,7,6,7,10,6};
        HandOfStraights hs= new HandOfStraights();
        System.out.println(hs.isNStraightHand(hand,3));
        System.out.println(hs.isNStraightHand(hand2,2));
        System.out.println(hs.isNStraightHand(hand3,2));
    }

}
