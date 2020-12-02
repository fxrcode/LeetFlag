/*
 * @lc app=leetcode id=125 lang=java
 *
 * [125] Valid Palindrome
 */

// @lc code=start
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int i = 0, j = s.length()-1;
        char[] cs = s.toCharArray();
        while (i < j) {
            if (!Character.isLetterOrDigit(cs[i])) {
                i++;
            }
            else if (!Character.isLetterOrDigit(cs[j])) {
                j--;
            }
            else {
                if (Character.toLowerCase(cs[i]) != Character.toLowerCase(cs[j])) {
                    return false;
                }
                i++;
                j--;
            }
        }
        return true;
    }
}
// @lc code=end

