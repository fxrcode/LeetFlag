# Algorithms \(2\) - Binary Search

## 1. Binary Search 第一重境界

#### Binary Search 基本模板：

```python
class Solution:

    def findPosition(self, nums, target):
        # test case
        if len(nums) == 0:
            return -1
        # traverse
        start, end = 0, len(nums) - 1
        while start + 1 < end :
            mid = start + (end - start) // 2            
            if nums[mid] < target :
                start = mid
            else :
                end = mid
        # two pos
        if nums[start] == target :
            return start
        if nums[end] == target:
            return end
            
        return -1
```

几种可能的变种，最后返回的是一个二元的list\[x, y\]，当两个是一样的时候 ：

* 取最左边，也就是只取x :
  * ```python
    if nums[mid] < target :
    # 先start 后end
    ```
* 取最右边，也就是只取y :
  * ```python
    if nums[mid] <= target :
    # 先end 后start
    ```

{% hint style="info" %}
原理非常的简单，能明白等于的时候放在哪边就可以了，不用强行记忆。但是需要注意的是，取左边第一个和右边第一个是确定的，但是不能确定的是，start还是end取到了，所以需要检查 **start和end**，见上面注释
{% endhint %}

#### [458. Last Position of Target](https://www.lintcode.com/problem/last-position-of-target/description)

O\(n\)遍历的写法比较简单就不列在这里了，主要写一下二分法 O\(logn\) ：

就是处理左右指针相等的情况，这里比如说给\[1,2,2,4,5,5\]要找2的话，最后左右指针应该指的是\[2,2\]，这里就需要先输出右指针就可以。 

* mid = start + \(end - start\) // 2
* mid = \(start + end\) //2 
* 这两个是一样的，差别在于第一个防止stack overflow

```python
class Solution:

    def lastPosition(self, nums, target):
        # corner
        if len(nums) == 0 :
            return -1
        # bs 
        start, end = 0, len(nums) - 1
        while start + 1 < end :
            mid = (start + end) // 2
            if nums[mid] <= target :
                start = mid
            else :
                end = mid
        # end first because right side
        if nums[end] == target :
            return end
        if nums[start] == target :
            return start
        return -1
```

#### [61. Search for a Range](https://www.lintcode.com/problem/search-for-a-range/description) / [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/)

leetcode只有这一道题，时间复杂度是O\(logn + k \)， k是一样的个数，建议下面的两种情况:

* 取左边第一个，向右边拓展
* 取右边第一个，向左边拓展

```python
class Solution:
    
    def searchRange(self, nums, target):
        # corner
        n = len(nums)
        if n == 0 or target is None :
            return [-1, -1]
        if n == 1 and nums[0] == target :
            return [0, 0]
        # bs
        start, end = 0, n - 1
        while start + 1 < end :
            mid = (start + end) // 2
            if nums[mid] < target :
                start = mid
            else :
                end = mid
        # check    
        if nums[start] == target:
            end = start 
        elif nums[end] == target :
            start = end
        else :
            return [-1, -1]
        # move right pointer
        while end < n - 1 and nums[end + 1] == nums[end] :
            end += 1
        
        return [start, end]
```

#### 小结 :

二分查找的第一种境界，主要要求是如下:

* 能够熟练掌握二分法，在存在重复数字的情况下，找到左起或者右起的第一个，从而可以解决剩余的问题

## 2. Binary Search 第二重境界

#### 判断XXOO类型的数组，特点是有明确的区分

#### [74. First Bad Version](https://www.lintcode.com/problem/first-bad-version/description) / [278. First Bad Version](https://leetcode.com/problems/first-bad-version/description/)

计算复杂度 ： O\(logn\)

主要是二分查找锁定最后的xo，然后分别查找左右是否是bad version

```python
class Solution:

    def findFirstBadVersion(self, n):
        # bs
        start, end = 0, n
        while start + 1 < end :
            mid = (start + end) // 2
            if SVNRepo.isBadVersion(mid) :
                end = mid
            else :
                start = mid
        # check
        if SVNRepo.isBadVersion(start):
            return start
        else :
            return end
```

#### [460. Find K Closest Elements](https://www.lintcode.com/problem/find-k-closest-elements/description) / [658. Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements/description/)

计算复杂度O\(logn + k\)

首先二分查找找到左右指针位置，然后判断左右哪一个离目标数更近，近的话就放进去，远的话就先不管。这里写了一个helper函数，就是判断左边是不是比右边小，如果小的话就是True，这样可以保证两个一样时，先选左边。

```python
class Solution:

    def kClosestNumbers(self, A, target, k):
        # corner
        if len(A) == 0 or target is None :
            return []
        # bs
        start, end = 0, len(A) - 1
        while start + 1 < end :
            mid = (start + end) // 2
            if target >= A[mid] :
                start = mid
            else :
                end = mid
        # expand
        result = []
        while k > 0  :
            if self.left_close(start, end, target, A) :
                result.append(A[start])
                start -= 1
            else :
                result.append(A[end])
                end += 1
            k -= 1
                        
        return result
            
    def left_close(self, start, end, target, A) :
        if start < 0 :
            return False
        if end >= len(A) :
            return True
        return abs(A[start] - target) <= abs(A[end] - target)
```

#### [447. Search in a Big Sorted Array](https://www.lintcode.com/problem/search-in-a-big-sorted-array/description) / [702. Search in a Sorted Array of Unknown Size](https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/description/)

计算复杂度O\(logn\)

先使用倍增法 - Exponential Backoff，来判断值所处的区间，然后再使用二分法找到值的左右指针，然后因为要的是第一个值，所以start优先。

```python
class Solution:

    def searchBigSortedArray(self, reader, target):
        # upper 
        start, end = 0, 1
        while reader.get(end) < target :
            start = end
            end = start * 2   
        # lower
        while start + 1 < end :
            mid = (start + end) // 2
            if reader.get(mid) < target :
                start = mid
            else :
                end = mid
        # check
        if reader.get(start) == target :
            return start
        elif reader.get(end) == target :
            return end
        else :
            return -1
```

#### [159. Find Minimum in Rotated Sorted Array](https://www.lintcode.com/problem/find-minimum-in-rotated-sorted-array/description) / [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/)

计算复杂度O\(logn\)

这个题主要是中间切一刀之后，应该如何判断的问题，这里主要是和end比较。

```python
class Solution:
    
    def findMin(self, nums):
        # write your code here
        if len(nums) == 0 :
            return 0
            
        start, end = 0, len(nums) - 1
        
        while start + 1 < end :
            mid = (start + end) // 2
            
            if nums[mid] > nums[end]:
                start = mid
            else :
                end = mid 
        
        return min(nums[start],nums[end])
```

#### [585. Maximum Number in Mountain Sequence](https://www.lintcode.com/problem/maximum-number-in-mountain-sequence/)

计算复杂度O\(logn\)

这一类xxoo问题的关键是判断这一刀切下去之后，左右。

```python
class Solution:

    def findMin(self, nums):
        # corner
        if len(nums) == 0 :
            return 0
        # bst
        start, end = 0, len(nums) - 1        
        while start + 1 < end :
            mid = (start + end) // 2
            if nums[mid] > nums[end]:
                start = mid
            else :
                end = mid 
        
        return min(nums[start],nums[end])
```

#### [28. Search a 2D Matrix](https://www.lintcode.com/problem/search-a-2d-matrix/) / [74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/description/)

计算复杂度O\(logn + logm\)

二分查找两次，第一次先找到行，第二次再找到列。

```python

class Solution:
    def searchMatrix(self, matrix, target):
        # test corner
        if len(matrix) == 0 or len(matrix[0]) == 0:
            return False
        # bs
        start, end = 0, len(matrix) - 1
        while start + 1 < end :
            mid = (start + end) // 2
            if matrix[mid][0] < target :
                start = mid
            else :
                end = mid
            
        if self.singleLine(matrix[start],target) or self.singleLine(matrix[end],target) :
            return True      
            
        return False

    def singleLine(self, List, target):
        # bs
        start, end = 0, len(List) - 1
        while start + 1 < end :
            mid = (start + end) // 2
            if List[mid] < target :
                start = mid
            else :
                end = mid
        # check  
        if List[start] == target or List[end] == target :
            return True
        else :
            return False
```

#### 小结 ：

二分法的第二重境界的要求 ：

* 可以在数列中找到明显的条件，使得查找可以使用二分
* 也就是存在xxoo的形式

## 3. Binary Search 第三重境界

#### Half-half 判断一半不符合就扔掉

#### [75. Find Peak Element](https://www.lintcode.com/problem/find-peak-element/) / [162. Find Peak Element](https://leetcode.com/problems/find-peak-element/description/)

计算复杂度O\(logn\)

主要是可以判断出上升序列，就可以比较简单

```python
class Solution:

    def findPeak(self, A):
        # bs
        start, end = 0, len(A) - 1
        while start + 1 < end:
            mid = (start + end) // 2
            if A[mid] < A[mid+1] :
                start = mid
            else :
                end = mid
        # check                
        if A[start] < A[end]:
            return end
        else :
            return start
```

#### [62. Search in Rotated Sorted Array](https://www.lintcode.com/problem/search-in-rotated-sorted-array/my-submissions) / [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/description/)

计算复杂度O\(logn\)

直接通过端点的比较来判断可能值在哪一边，然后不断更新：

* start + 1 &lt; end的模板的致命缺陷在于，总是要求len\(A\)大于2，不然进入不了循环，所以一开始无法处理\[1\]，这样的情况

```python
class Solution:

    def search(self, nums, target):
        # corner
        if not nums:
            return -1

        start, end = 0, len(nums) - 1
        
        if target == nums[start]:
            return start
        if target == nums[end]:
            return end

        while start + 1 < end:
            mid = (start + end) // 2
            if nums[start] <= nums[mid]:
                if nums[start] <= target <= nums[mid]:
                    end = mid 
                else:
                    start = mid
            else:
                if nums[mid] <= target <= nums[end]:
                    start = mid 
                else:
                    end = mid 
                
            if target == nums[start]:
                return start
            if target == nums[end]:
                return end

        return -1
```

#### 小结 :

* 第三重境界主要是通过half-half的思想来实现，从而对原有数组进行了切割，这种思想本身是非常伟大的，但是要能够熟练的应用。
* 现有的模板问题需要注意，也就是不会去探索两端的值，这个非常非常的重要，因为不是start &lt;= end，所以是无法穷尽的
* Rotated Array这道题，非常的有价值，多练习

这里基本的二分法就结束了，二分法的第四重境界是二分答案，见下面，主要是二分之后对二分内的值进行尝试，log\(range\)

{% embed url="https://zijiang.gitbook.io/notes/computer-science/advanced-algorithms/advanced-algorithms-4-sweep-line-deque-binary-search" %}

## 4. 其他Logn的算法

#### [140. Fast Power](https://www.lintcode.com/problem/fast-power/)

计算复杂度O\(logn\)

使用的divide & conquer，先数学降低维度，基本上每一次只去一半取模，然后逐次相乘取模，这里一开始如果是30 -&gt; 15 -&gt; 7 -&gt; 3 -&gt; 1

```python
class Solution:

    def fastPower(self, a, b, n):
        # init
        ans = 1
        while n > 0 :
            if n % 2 == 1:
                ans = ans * a % b
                
            a = a * a % b
            n = n // 2
            # check
            if ans == 0 or a == 0 :
                return 0
                
        return ans % b
```

#### [428. Pow\(x, n\)](https://www.lintcode.com/problem/powx-n/) / [50. Pow\(x, n\)](https://leetcode.com/problems/powx-n/description/)

计算复杂度O\(logn\)

需要注意的是这个题的corner case很多，需要把问题想清楚。

```python
class Solution:

    def myPow(self, x, n):
        # init
        ans, multiple, m = 1, x, abs(n)
        # corner case
        if x == 0 :
            return 0
        # fast mode    
        if n == 0 :
            return 1
        if n == 1 :
            return x
        
        while m > 0 :
            if m % 2 == 1:
                ans *= multiple 
            multiple = multiple * multiple
            m = m // 2
            
        # check negative value
        if n > 0 :
            return ans 
        else :
            return 1 / ans 
```

#### 分解质因数

Log\(sqrt\(n\)\)

```python
class Solution:

    def primeFactorization(self, num):
        # init
        limit = int(math.sqrt(num)) + 1
        prime_list = []
        
        for prime in range(2, limit):
            while num % prime == 0 :
                prime_list.append(prime)
                num = num // 2
        if num > 1 :
            prime_list.append(num)
            
        return prime_list
```

#### 小结 :

基本遇到的两种情况

* 质因数类\(prime number\)，主要用sqrtn来进行遍历
* power和mod类的，数学降维尽量向二分法方向去靠拢

## 5. Ladder

![](../../assets/screen-shot-2018-09-23-at-10.50.37-am.png)

![](../../assets/screen-shot-2018-09-23-at-10.50.43-am.png)

![](../../assets/screen-shot-2018-09-23-at-10.50.49-am.png)

