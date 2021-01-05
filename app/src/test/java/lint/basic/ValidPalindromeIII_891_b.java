package lint.basic;

import org.junit.Test;

/**
 * This is mentioned in https://www.lintcode.com/problem/valid-palindrome-ii/note/233999
 *
 */
public class ValidPalindromeIII_891_b {
    @Test
    public void test() {
        String s = "abccbaa";
        int n = 0;
        s = "abcd";
        n = 2;
        System.out.println( validPalindrome(s, n));
    }

    /**
     * Check if s can be valid palindrome, you can delete at most n elements.
     * @param s
     * @param n
     * @return
     */
    public boolean validPalindrome(String s, int n) {
        if (s == null || s.length() == 1) {
            return true;
        }
        int times = Math.min(s.length(), n);
        return dfs(s, times, 0, s.length()-1);
    }

    private boolean dfs(String s, int times, int l, int r) {
        while (l < r ) {
            if (s.charAt(l) != s.charAt(r)) {
                // delete one
                if (times == 0)
                    return false;
                return dfs(s, times-1, l+1, r) || dfs(s, times-1, l, r-1);
            }
            l++;
            r--;
        }
        return true;
    }
}
