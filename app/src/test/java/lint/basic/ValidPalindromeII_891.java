package lint.basic;

import java.util.*;

import org.junit.Test;

public class ValidPalindromeII_891 {
    @Test
    public void test() {
        // String s = "ognfjhgbjhzkqhzadmgqbwqsktzqwjexqvzjsopolnmvnymbbzoofzbbmynvmnloposjzvqxejwqztksqwbqgmdazhqkzhjbghjfno";
        String s = "abccbaa";
        System.out.println( validPalindrome(s));
    }

    public boolean validPalindrome(String s) {
        int l = 0, r = s.length()-1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                break;
            }
            l++;
            r--;
        }
        if (l >= r) {
            return true; // eg. s = aa.
        }
        // then check remove one from left || remove one from right.
        return isValidPalin(s, l+1, r) || isValidPalin(s, l, r-1);
    }

    private boolean isValidPalin(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public boolean validPalindrome_b(String s) {
        return validPalindrome_L(s) || validPalindrome_R(s);
    }

    private boolean validPalindrome_L(String s) {
        int l = 0, r = s.length()-1;
        int removed = 0;
        while (l < r) {
            while (l < r && s.charAt(l) != s.charAt(r) ) {
                // remove left one
                if (removed == 2) {
                    return false;
                }
                l++;
                removed++;
            }
            l++;
            r--;
        }
        return true;
    }
    private boolean validPalindrome_R(String s) {
        int l = 0, r = s.length()-1;
        int removed = 0;
        while (l < r) {
            while (l < r && s.charAt(l) != s.charAt(r) ) {
                // remove left one
                if (removed == 2) {
                    return false;
                }
                r--;
                removed++;
            }
            l++;
            r--;
        }
        return true;
    }
    /**
     * I miss understood the question, you can at most delete one char, and you CANNOT change order!
     * @param s: a string
     * @return boolean: whether you can make s a palindrome by deleting at most one
     *         character
     */
    public boolean validPalindrome_a(String s) {
        // Write your code here
        List<Character> l = new ArrayList<>();
        Set<Character> sets = new HashSet<>();
        Map<Character, Integer> cnt = new HashMap<>();
        for (Character c : s.toCharArray()) {
            l.add(c);
            cnt.putIfAbsent(c, 0);
            cnt.put(c, cnt.get(c)+1);
            if (sets.contains(c)) {
                sets.remove(c);
            } else {
                sets.add(c);
            }
        }
        Collections.sort(l);
        System.out.println();
        System.out.println( cnt );
        return sets.size() <= 2;
    }
}
