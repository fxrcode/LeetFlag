# Algorithms \(1\) - String

这里主要是讲一些比较杂乱的和字符串有关的内容放在了这里，主要是因为对于串的处理考到的几率比较高，但是较难以归类到一种特定的模板或者算法里面去。

## 1. 回文判断

一般的做法比较简单，重要是从字符串的中间或者两边出发，然后同时移动指针来判断是否一致，或者基于中心节点进行枚举。

#### [627. Longest Palindrome](https://www.lintcode.com/problem/longest-palindrome/description)  /  [**409. Longest Palindrome**](https://leetcode.com/problems/longest-palindrome/description/) 

使用了hash table来记录每一个元素出现的次数，然后区分一些特殊情况即可，这里特殊情况很多，调试了很多次是因为对test case的判断不够熟练 ：

* 'aaa',  '',  'a',  'aaaa', 'aaaabbbb'

```python
class Solution:

    def longestPalindrome(self, s):
        # corner
        if len(s) == 0 or len(s) == 1:
            return len(s)
        # build hashmap
        hash_map = {}
        for c in s :
            if hash_map.get(c) is None :
                hash_map[c] = 1
                continue
            hash_map[c] += 1
        # count
        cnt, single = 0, False
        for val in hash_map :
            # aabb
            if hash_map[val] % 2 != 0 :
                single = True
            cnt += hash_map[val] // 2
        # 'aaaa'    
        if len(hash_map) == 1 and hash_map[val] % 2 == 0 :
            return 2 * cnt
            
        if single :
            return 2 * cnt + 1
        
        return 2 * cnt
```

#### [415. Valid Palindrome](https://www.lintcode.com/problem/valid-palindrome/description) /  [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/description/)

使用相向双指针，遇到非数字和字母的跳过，等同于对于一个字符串，从两边同时移动两个指针，一旦不同就跳过

* start &lt;= end ：这里这样写可以解决字符串奇数和偶数的问题，如果使用了start + 1 &lt; end，必定是两个，需要判断奇数和偶数

```python
class Solution:
    """
    @param s: A string
    @return: Whether the string is a valid palindrome
    """
    def isPalindrome(self, s):
        if len(s) < 2 :
            return True
        # init
        s = s.lower()
        start, end = 0, len(s) - 1
        # traverse
        while start <= end :
            # move left
            while not s[start].isalnum() and start < end:
                start +=1
            # move right    
            while not s[end].isalnum() and start < end:
                end -=1
            # judge
            if s[start] != s[end] :
                return False
            
            start += 1
            end -= 1
            
        return True
```

#### [891. Valid Palindrome II](https://www.lintcode.com/problem/valid-palindrome-ii/description) / [680. Valid Palindrome II](https://leetcode.com/problems/valid-palindrome-ii/description/)

这里不能直接使用前面一个题的模板，错误的想法是设置一个deleted变量，如果deleted了，就记录一下，但是这里最重要的是要区分往左还是往右删除，如果不能区分，即使过了lintcode，也过不了leetcode。

* 左删和右删都是可以的，这里比较容易出错

```python
class Solution:
    """
    @param s: a string
    @return: nothing
    """
    def validPalindrome(self, s):
        # find diff position
        left, right = 0, len(s) - 1
        while left < right:
            if s[left] != s[right]:
                break
            
            left += 1
            right -= 1
            
        if left >= right:
            return True
            
        return self.is_palindrome(s, left + 1, right) or self.is_palindrome(s, left, right - 1)

    def is_palindrome(self, s, left, right):
        while left < right:
            if s[left] != s[right]:
                return False
            left += 1
            right -= 1
            
        return True
```

#### [13. Implement strStr\(\)](https://www.lintcode.com/problem/implement-strstr/description) /  [28. Implement strStr\(\)](https://leetcode.com/problems/implement-strstr/description/) \*\*重要\*\*

#### Rabin Karp : 将文本通过hash function变成数字，从而对数字进行比较

* 平均计算复杂度 O\(n + m\)，最坏情况O\(nm\)

这里主要实现了Rabin Karp Algorithm，从而使得计算复杂度为O\(n\)，具体原理是将文本变为数字，从而加速了实际比较的运算消耗。

* 使用hash表计算target的hash值，然后每次移动指针来比较
* 每次移动需要去除第一个值，加入新的值
* 这里取31只是出于习惯，使用37，997也是可以的

{% embed url="https://www.jianshu.com/p/24895aca0459" %}

```python
class Solution:
    
    def strStr(self, source, target):
            size = len(target)
            # corner
            if source == None or target == None: 
                return -1
            if size == 0: 
                return 0
            # build hash
            power, hash_target = 10**8 , 0
            for t in target:
                hash_target = (hash_target*31 + ord(t)) % power
            
            hash_source = 0 
            for i in range(len(source)):
                hash_source = (hash_source*31 + ord(source[i])) % power
                if i < size - 1:
                    continue
                    
                if hash_source == hash_target:
                    return i - size + 1 
                
                hash_source = (hash_source - ord(source[i - size + 1]) * (31 ** (size - 1)) ) % power
                
                if hash_source < 0:
                    hash_source += power
                    
            return -1
```

#### [594. strStr II](https://www.lintcode.com/problem/strstr-ii/description?_from=ladder&&fromId=1)

strStr II就是强制使用了Rabin Karp算法，没有什么可以说的，需要注意的是可能会tle，power可以稍微小一点。

#### [841. String Replace](https://www.lintcode.com/problem/string-replace/description?_from=ladder&&fromId=1)  

类似与RP算法的进阶版，需要先匹配再替换，先码

#### [200. Longest Palindromic Substring](https://www.lintcode.com/problem/longest-palindromic-substring/description) / [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/description/)

Manacher's Algorithm - 通过轴对称的思路来确定半径，进而查看是否是回文

* 时间复杂度O\(n\)
* 主体思想是通过 ’\#‘ 来解决回文串奇偶性的问题
* 通过建立回文对称轴来进行计算

这个算法非常的巧妙，但是还是推荐暴力算法吧，对于普通人来说，暴力是比较实际和容易想到的。

{% embed url="https://segmentfault.com/a/1190000003914228" %}

```python
class Solution:

    def longestPalindrome(self, s):
        # Transform S into T.
        # For example, S = "abba", T = "^#a#b#b#a#$".
        # ^ and $ signs are sentinels appended to each end to avoid bounds checking
        T = '#'.join('^{}$'.format(s))
        n = len(T)
        P = [0] * n
        C = R = 0
        for i in range (1, n-1):
            P[i] = (R > i) and min(R - i, P[2*C - i]) # equals to i' = C - (i-C)
            # Attempt to expand palindrome centered at i
            while T[i + 1 + P[i]] == T[i - 1 - P[i]]:
                P[i] += 1
    
            # If palindrome centered at i expand past R,
            # adjust center based on expanded palindrome.
            if i + P[i] > R:
                C, R = i, i + P[i]
    
        # Find the maximum element in P.
        maxLen, centerIndex = max((n, i) for i, n in enumerate(P))
        return s[(centerIndex  - maxLen)//2: (centerIndex  + maxLen)//2]
```

#### [667. Longest Palindromic Subsequence ](https://www.lintcode.com/problem/longest-palindromic-subsequence/description?_from=ladder&&fromId=1)/ [516. Longest Palindromic Subsequence](https://leetcode.com/problems/longest-palindromic-subsequence/description/)

这个题用dp非常容易解决，这里先码住

#### 小结：

这一部分其实本质是字符串匹配，这里感觉不是很适合放在最开始，因为其实题的难度是不简单的，而且非常依赖于一些成熟的算法。

* Rabin Karp 是需要掌握的，因为本质是hash的实现
* 回头看很多题都可以用DP和DFS来解，还是有不一样的感觉

## 2. Ladder

![](../../assets/screen-shot-2018-09-23-at-10.49.15-am.png)

![](../../assets/screen-shot-2018-09-23-at-10.49.19-am.png)

