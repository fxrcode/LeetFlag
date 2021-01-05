package lint.basic;

import java.util.*;

/*-
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.
*/
public class LongestPalindrome_627 {
    public static void main(String[] args) {
        System.out.println( longestPalindrome( "abccccdd") );
        System.out.println( longestPalindrome( "a") );
        System.out.println( longestPalindrome( "abc") );
    }
    /**
     * @param s: a string which consists of lowercase or uppercase letters
     * @return: the length of the longest palindromes that can be built
     */
    public static int longestPalindrome(String s) {
        // write your code here
        if (s ==null || s.length() == 0) {
            return 0;
        }
        char[] cs = s.toCharArray();
        // find all even
        // find all odd, remove 1 from all odd so as to make them even, but keep one odd pair so as to be middle point
        Set<Character> alpha =  new HashSet<>();
        for (char c : cs) {
            if (!alpha.contains(c)) {
                alpha.add(c);
            } else {
                alpha.remove(c);
            }
        }
        return s.length() - (alpha.size() == 0 ? 0 : alpha.size()-1);
    }
}
