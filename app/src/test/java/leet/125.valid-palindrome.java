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
        // partition style: outer loop + one while per reverse pointer. 
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(cs[i])) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(cs[j])) {
                j--;
            }
            if (Character.toLowerCase(cs[i]) != Character.toLowerCase(cs[j])) {
                return false;
            }
            i++;
            j--;

        }
        return true;
    }
}
// @lc code=end

