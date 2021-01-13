class Solution:
    """
    time complex: O(N^3)
    space complex: O(1)
    @param s: input string
    @return: a string as the longest palindromic substring
    """

    def longestPalindrome(self, s):
        # write your code here
        if not s:
            return ''

        start, longest = 0, 1
        for i in range(len(s)):
            for j in range(i, len(s)):
                if j - i + 1 > longest and self.is_palindrome(s, i, j):
                    # pass
                    longest = j - i + 1
                    start = i
        return s[start: start+longest]

    """
    Central line sweep
    time complex: O(N^2)
    space complex: O(1)
    """

    def longestPalindrome2(self, s):
        if not s:
            return ''

        res = ''
        for middle in range(len(s)):
            start, length = self.center_spread(s, middle, middle)
            if length > len(res):
                res = s[start:start+length]
            start, length = self.center_spread(s, middle, middle+1)
            if length > len(res):
                res = s[start:start+length]
        return res

    """use interval DP
    time: O(N^2)
    space: O(N^2)
    """
    def longestPalindrome3(self, s):
        if not s:
            return ''

        # DP table
        n = len(s)
        is_palin = [[False] * n for _ in range(n)]
        for i in range(n):
            is_palin[i][i] = True

        start, longest = 0, 1
        for length in range(2, n+1):
            # since (n-1)-end+1 = length => end = n-length => range = n-length+1
            for i in range(n - length + 1):
                j = i + length - 1
                is_palin[i][j] = is_palin[i+1][j-1] and s[i] == s[j]
                if is_palin[i][j] and length > longest:
                    start = i
                    longest = length
        # print(start, longest)
        return s[start:start+longest]

    def is_palindrome(self, s, i, j):
        while i < j and s[i] == s[j]:
            i += 1
            j -= 1
        return i >= j

    """
    learned from https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
    return a tuple: start and length
    """
    def center_spread(self, s, i, j):
        while i >= 0 and j < len(s):
            if s[i] == s[j]:
                i -= 1
                j += 1
            else:
                break
        return i+1, j-i+1-2


test = Solution()
print(test.longestPalindrome('abcdzdcab'))
print(test.longestPalindrome('abc'))
print(test.longestPalindrome('aba'))

print(test.longestPalindrome2('abcdzdcab'))
print(test.longestPalindrome2('abc'))
print(test.longestPalindrome2('ab'))
print(test.longestPalindrome2('aba'))

print(test.longestPalindrome3('abcdzdcab'))
print(test.longestPalindrome3('abc'))
print(test.longestPalindrome3('aba'))
