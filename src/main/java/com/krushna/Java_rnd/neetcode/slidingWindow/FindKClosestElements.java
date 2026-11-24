package com.krushna.Java_rnd.neetcode.slidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 * An integer a is closer to x than an integer b if:
 * * |a - x| < |b - x|, or
 * * |a - x| == |b - x| and a < b
 */
public class FindKClosestElements {

    // the array is sorted, we need k sub-array.
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> nums = new ArrayList<>();
        // single-pass slide: move window right only while the outgoing left
        // element is strictly farther from x than the incoming right element.
        // No undo step needed: (x - arr[left]) - (arr[left+k] - x) is
        // monotonically non-decreasing as left grows (array is sorted), so
        // once the slide stops it never needs to reverse.
        int left = 0;
        // here right is (left + k) -1
        // x - arr[left], left Gap > right Gap, then add more element from right
        while (left < arr.length - k && x - arr[left] > arr[left + k] - x) {
            ++left;
        }
        for (int i = left; i < left + k; ++i) {
            nums.add(arr[i]);
        }
        return nums;
    }

    public static void main(String[] args) {
        FindKClosestElements fk= new FindKClosestElements();
        int[] arr= {1,1,1,10,10,10};
        System.out.println(fk.findClosestElements(arr,1,9));
    }

}
