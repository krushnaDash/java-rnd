package com.krushna.Java_rnd.neetcode.twoPointer;

//Given a string s, return true if the s can be palindrome after deleting at most one character from it.
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {

            if (s.charAt(l) != s.charAt(r)) {
                // we have two choice, either skip left or right , check both and return
                // try both possibility
                return checkPalindrome(s, l + 1, r) || checkPalindrome(s, l, r - 1);
            }
            ++l;
            --r;
        }
        return true;
    }

    public boolean checkPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            ++l;
            --r;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindromeII v = new ValidPalindromeII();
        v.validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga");
    }
}
