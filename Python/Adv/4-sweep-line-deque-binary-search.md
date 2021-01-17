# Advanced Algorithms \(4\) - Sweep Line, Deque, Binary Search

## 1. Sweep Line 

扫描线本质应该是源于对区间合并而产生的问题的，从区间合并的思想里面汲取了处理区间的方法，从而广泛地应用于区间的问题。

这里简单回顾一下区间合并，主要是学习一下区间归并：

#### [156. Merge Intervals](https://www.lintcode.com/problem/merge-intervals/description) / [56. Merge Intervals](https://leetcode.com/problems/merge-intervals)

* 首先需要排序，然后再判断是否可以合并
* 合并的依据就是 ： 前一个的end和现在的start大小
  * 不能合并，直接append 新区间
  * 可以合并，修改前一个的end为现在的end即可

```python
class Solution(object):
    def merge(self, intervals):
        intervals = sorted(intervals, key = lambda x: x.start)
        result = []
        for interval in intervals:
            if len(result) == 0 or result[-1].end < interval.start:
                result.append(interval)
            else:
                result[-1].end = max(result[-1].end, interval.end)
        return result
```

* 类似的题还有下面的两道，本质都是查看是否可以merge，下面的相对更加简单一些，但是对这个问题的本质探索并不改变

练习 ：[920. Meeting Rooms](https://www.lintcode.com/problem/meeting-rooms/description) / [252. Meeting Rooms](https://leetcode.com/problems/meeting-rooms/)

#### 扫描线

扫描线的主要思想：将起点和终点打散排序

* \[\[1,3\], \[2,4\]\] =&gt; \[\[1,start\], \[2,start\], \[3,end\], \[4,end\]\]
* 画在图上就是一段一段的线段

扫描主要用来解决的问题特征：

* 事件往往是以区间的形式存在
* 区间两端代表事件的开始和结束
* 按照区间起点排序，起点相同的按照终点拍排序

#### [919. Meeting Rooms II](https://www.lintcode.com/problem/meeting-rooms-ii/description) / [53. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)

先将所有start, end记录下来，之后对list进行排序，起点就加1，终点就减1，然后维护一个最大的值，这个题想的比较久的是，如果区间交错会怎么样，其实并不影响的，因为并不用合并区间，如果重合，还是一样的。

* 这个题非常的经典
* 时间复杂度 O\(nlogn\)

```python
"""
class Interval(object):
    def __init__(self, start, end):
        self.start = start
        self.end = end
"""
class Solution:
    def countOfAirplanes(self, airplanes):
        points = []
        for airplane in airplanes :
            points.append([airplane.start, 1])
            points.append([airplane.end, -1])
        
        max_num, cum_num = 0, 0
        for _, delta in sorted(points):
            cum_num += delta
            max_num = max(max_num, cum_num)
        return max_num
```

**练习** [391. Number of Airplanes in the Sky](https://www.lintcode.com/problem/number-of-airplanes-in-the-sky/description)

#### [821. Time Intersection](https://www.lintcode.com/problem/time-intersection/description)

和前面的主要做法基本一样，就是找到一个共有的区间，特点就是起点或者终点堆在了一起，这个值只会为最大为2，而我们要找的就是具有这样特征的区间，并记录下来：

```python
"""
class Interval(object):
    def __init__(self, start, end):
        self.start = start
        self.end = end
"""

class Solution:

    def timeIntersection(self, seqA, seqB):
        points = []
        for interval in seqA + seqB :
            points.append([interval.start, 1])
            points.append([interval.end, -1])
        
        results = []
        online, last_time = 0, None 
        for cur_time, delta in sorted(points) :
        # 如果找到了头和尾就merge
            if online == 2:
                self.merge(results, last_time, cur_time)
            online += delta
            last_time = cur_time
        
        return results
        
    def merge(self, results, last_time, cur_time) :
        # 起始时间不存在以及两个时间一致的情况
        if last_time is None or last_time == cur_time :
            return
        # 最后一个的时间可以合并
        if results and results[-1].end == last_time :
            results[-1].end = cur_time
            return
        
        results.append(Interval(last_time, cur_time))
```

#### [30. Insert Interval](https://www.lintcode.com/problem/insert-interval/description) / [57. Insert Interval](https://leetcode.com/problems/insert-interval/) 

这个解法比较巧妙，所以放了上来，基本来讲因为以及排序好了，所以可以直接进行遍历，主要是标记一下位置，然后不断查看是否可以合并，然后不断合并。

```python
class Solution:
    def insert(self, intervals, newInterval):
        results = []
        insertPos = 0
        for interval in intervals:
            # [1, 3] -> [4, 5]
            if interval.end < newInterval.start:
                results.append(interval)
                insertPos += 1
            # [3, 5] -> [1, 3] 改变顺序
            elif interval.start > newInterval.end:
                results.append(interval)
            # [1, 3] -> [3, 5] or [2, 5] 可以合并 为 [1, 5]
            else:
                newInterval.start = min(interval.start, newInterval.start)
                newInterval.end = max(interval.end, newInterval.end)
        results.insert(insertPos, newInterval)
        return results
```

累了... 先到这里？

#### [131. The Skyline Problem](https://www.lintcode.com/problem/the-skyline-problem/description)

主要思路分为几个部分:

* 先利用扫描线将头尾进行分拆
* 排序后从左到右扫描
* 如果发现可以合并，则outline就是最大的高度

这个题的难点还是在于要先实现hashheap，之后才可以实现具体算法，具体算法并不是很复杂，但是实现hashheap是比较复杂的过程。

{% embed url="https://briangordon.github.io/2014/08/the-skyline-problem.html" %}

硬骨头慢慢啃..

## 2 .Binary Search

#### [390. Find Peak Element II](https://www.lintcode.com/problem/find-peak-element-ii/description)

* 暴力解就是全部遍历，O\(n^2\)

优化用到了二分答案：

* 二分行之后，找到行的最大值，这里可以想象一个最大值是一堵墙分隔了整个空间
* 如果上面比这一堵墙大，上面比如有最大，反之则在下面
* 进一步优化，上面或下面的空间再二分

由此，这个题经过不断的二分：

* O\(nlogn\) 这是一个方向二分
* O\(n\) 两个方向进行二分

#### 二分答案

主要的特征是，不像其他题目，给出数组进行二分，但是要求还是找到满足某个条件的最大或者最小值。

解题方法： 通过猜值判断是否满足题意不对去搜索可能解

* a. 找到可行解范围
* b. 猜答案
* c. 检验条件
* d. 调整搜索范围

到这里对基本的二分法来一个小结：

* 二分适合 - **任何存在单调不减的函数，只要存在这样的特征，必然可二分**

```python
# 1. 确定答案范围
start, end = 答案可能的值域范围

while start + 1 < end :
    # 2. 猜答案
    mid = (start + end) // 2
    # 3. 检测答案 
    if should_be_smaller(mid) :
        # 4. 范围调整
        end = mid
    else :
        # 4. 范围调整
        start = mid
        
最好再检测start 和 end，哪个是要的答案
```

#### [141. Sqrt\(x\)](https://www.lintcode.com/problem/sqrtx/description)

二分之后，看一下三种情况，然后确定范围和边界。

* m^2 &lt; x
* m^2 = x
* \(m + 1\) ^2 &gt; x

```python
class Solution:

    def sqrt(self, x):
        start, end = 0, x 
        
        while start + 1 < end :
            
            mid = (start + end) // 2
            # 三种情况
            if mid * mid == x :
                return mid
            elif mid * mid > x :
                end = mid
            else :
                start = mid
        # 二分答案后，看一下end即可，因为总是会偏左        
        if end * end == x :
            return end
            
        return start
```

#### [586. Sqrt\(x\) II](https://www.lintcode.com/problem/sqrtx-ii/description)

这个题非常好，1e-10就是精度，这里可以进行修改来确认精度。

* 时间复杂度：O\(log\(r - l\) \* 10^精度位数\)
* r - l 就是range

```python
class Solution:

    def sqrt(self, x):
        # test > 1 or < 1
        if x >= 1 :
            start, end = 1, x
        else :
            start, end = x, 1
    
        while end - start > 1e-10 :
            # 使用//会变成整数，小心
            mid = (start + end) / 2
            
            if mid * mid < x :
                start = mid
            else :
                end = mid
        
        return start
```

#### [183. Wood Cut](https://www.lintcode.com/problem/wood-cut/description)

这个题比较巧妙，难度一般，不细说了

* 切木头的函数存在单调不减
* 计算复杂度nlog\(range\) - range\(1, max\(L\)\)

```python
class Solution:

    def woodCut(self, L, k):
        if not L :
            return 0
            
        start, end = 1, max(L)
        
        while start + 1 < end :
            mid = (start + end) // 2
            # 切的多，说明长度小，改start
            if self.get_pieces(L, mid) >= k :
                start = mid
            else :
                end = mid
                
        if self.get_pieces(L, end) >= k :
            return end
        if self.get_pieces(L, start) >= k :
            return start
        
        return 0
    
    def get_pieces(self, L, length) :
        pieces = 0
        for w in L :
            pieces += w // length
        return pieces
```

#### [437. Copy Books](https://www.lintcode.com/problem/copy-books/description)

这个题的用二分法比较意外，很难想到：

* 其实是向n - 1个空位中，插入k - 1个人来划分书
* check 时间的可行性，这里可以想的是，一个函数一开始水平，然后突然上升，找的就是这个上升的瞬间，也就是可行性

因此这个题的时间复杂度是 O\(nlog\(range\)\) - range\(max\(a\), sum\(a\)\)

占坑

#### [633. Find the Duplicate Number](https://www.lintcode.com/problem/find-the-duplicate-number/description)

这个题能够使用二分法是因为对数据有了非常特殊的限制，使得函数存在了单调非增的特性。

* hash - O\(n\) O\(n\)
* 二分法 O\(nlogn\) O\(1\)

![](../../.gitbook/assets/screen-shot-2018-10-29-at-1.40.11-pm.png)

占坑

#### 快慢指针

通过指向关系将数据连成环，如果有一个数被两个数指向，则它就是重复的数字，因此从这个角度来看，本质就是判断链表之间是否存在环，因此可以使用快慢指针求解。

占坑

####  **Refer to LintCode** [103. Linked List Cycle II](https://www.lintcode.com/problem/linked-list-cycle-ii/description)

#### [617. Maximum Average Subarray II](https://www.lintcode.com/problem/maximum-average-subarray-ii/description)

* 首先，看到了maximum subarray，这里估计就要使用prefix sum的解法了，但是控制了k，所以等同于移动一个k的窗口，比较一下即可
* 其次，如何消除average的影响，这里的解法就是猜，对它们分别减一个数，看是否可行
* 时间复杂度 O\(nlog\(range\)\) - range: max - min

占坑

## 3. Deque

#### [362. Sliding Window Maximum](https://www.jiuzhang.com/solution/sliding-window-maximum/#tag-highlight-lang-python)

维护一个双端递减栈

## 4. Laddar

![](../../.gitbook/assets/screen-shot-2018-12-11-at-9.00.36-pm.png)

![](../../.gitbook/assets/screen-shot-2018-12-11-at-9.00.40-pm.png)

![](../../.gitbook/assets/screen-shot-2018-12-11-at-9.00.45-pm.png)

