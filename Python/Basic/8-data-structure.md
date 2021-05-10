# Algorithms \(8\) - Data Structure

## 1. 数据结构设计类问题

假设我们需要设计一个 Set 的数据结构，提供 lowerBound 和 add 两个方法：

* lowerBound 找到比某个数小的最大值 
* add 将数据加入set

在实际的操作中：

* 算法1:  使用**数组存储**，每次打擂台进行比较，插入就直接插入到数组最后面
  * O\(n\) lowerBound 
  * O\(1\) add
* 算法2: 使用**红黑树\(Red-black Tree\)**存储，Java 里的 TreeSet，C++ 里的 map 
  * O\(logn\) lowerBound 
  * O\(logn\) add

#### 上面两个算法谁好谁坏呢?

**不一定谁好谁坏 !   要看这两个方法被调用的频率如何。**

* 如果 lowerBound 很少调用, add 非常频繁, 则算法1好。
* 如果 lowerBound 和 add 调用的频率都差不多，或者 lowerBound 被调用得更多，则算法2好

不过通常来说，在面试中的问题，我们会很容易找到一个像算法1这样的实现方法，其中一个操作时间 复杂度很大，另外一个操作时间复杂度很低。通常的解决办法都是想办法增大较快操作的时间复杂度来加速较慢操作的时间复杂度。

#### 数据结构的主要考法

数据结构可以认为是一个数据存储集合以及定义在这个集合上的若干操作\(功能），它有如下的三种考法: 

* 考法1：问某种数据结构的基本原理，并要求实现
  * 例题: 说一下 Hash 的原理并实现一个 Hash 表 
* 考法2：使用某种数据结构完成事情
  * 例题: 归并 K 个有序数组 
* 考法3：实现一种数据结构，提供一些特定的功能，通常需要一个或者多个数据结构配合在一起使用
  * 例题: 最高频 K 项问题

#### 数据结构时间复杂度的衡量方法

数据结构通常会提供“多个”对外接口，只用一个时间复杂度是很难对其进行正确评价的，所以通常要对每个接口的时间复杂度进行描述。

## 2. 队列 Queue

队列（queue）是一种采用**先进先出**（FIFO，first in first out）策略的抽象数据结构。比如生活中排队，总是按照先来的先服务，后来的后服务。队列在数据结构中举足轻重，其在算法中应用广泛，**最常用的就是在宽度优先搜索\(BFS）中，记录待扩展的节点**。

队列内部存储元素的方式，一般有两种，**数组**（array）和**链表**（linked list）。两者的最主要区别是：

* 数组对**随机访问**有较好性能。
* 链表对**插入**和**删除**元素有较好性能。

Python中，使用`collections.deque`，即双端队列，队列的主要操作有：

* `add()`队尾追加元素 - O\(1\)
* `poll()`弹出队首元素 - O\(1\)
* `size()`返回队列长度 - O\(1\)
* `empty()`判断队列为空 - O\(1\)

#### 队列的实现

{% embed url="https://docs.python.org/3/library/queue.html" %}

#### 642. Moving Average from Data Stream

一开始是每次都将queue里面原始加起来然后再去计算，主要的启示是:

* 如果使用了特定的数据结构，尽量使用它的特性，而不引入其他的操作

```python
class MovingAverage:
    """
    @param: size: An integer
    """
    def __init__(self, size):
        # do intialization if necessary
        self.queue = collections.deque()
        self.size = size
        self.sum = 0
    """
    @param: val: An integer
    @return:  
    """
    def next(self, val):
    
        if len(self.queue) == self.size :
            head = self.queue.popleft()
            self.sum -= head
            
        self.sum += val
        self.queue.append(val)    
        
        return self.sum / len(self.queue)
```

## 3. 栈 Stack 

栈（stack）是一种采用**后进先出**（LIFO，last in first out）策略的抽象数据结构。比如物流装车，后装的货物先卸，先转的货物后卸。栈在数据结构中的地位很重要，在算法中的应用也很多，比如用于非递归的遍历二叉树，计算逆波兰表达式，最常用的是**非递归实现DFS的主要数据结构**。

栈一般用一个存储结构（常用数组，偶见链表），存储元素。并用一个指针记录**栈顶位置**。**栈底位置**则是指栈中元素数量为0时的栈顶位置，也即栈开始的位置。栈的主要操作：

* `push()`，将新的元素压入栈顶，同时栈顶上升。
* `pop()`，将新的元素弹出栈顶，同时栈顶下降。
* `empty()`，栈是否为空。
* `peek()`，返回栈顶元素。

Python，直接使用`list`，查看栈顶用`[-1]`这样的切片操作，弹出栈顶时用`list.pop()`，压栈时用`list.append()`

#### 栈的实现

#### [495. Implement Stack](https://www.lintcode.com/problem/implement-stack/description)

* python的\[ \] 就是stack，一般的stack实现是通过array数据，或者Linklist

#### 栈在计算机内存当中的应用

我们在程序运行时，常说的内存中的堆栈，其实就是栈空间。这一段空间存放着程序运行时，产生的各种临时变量、函数调用，一旦这些内容失去其作用域，就会被自动销毁。

函数调用其实是栈的很好的例子，后调用的函数先结束，所以为了调用函数，所需要的内存结构，栈是再合适不过了。在内存当中，**栈从高地址不断向低地址扩展**，随着程序运行的层层深入，栈顶指针不断指向内存中更低的地址。

{% embed url="https://www.cnblogs.com/kevinGaoblog/archive/2012/03/23/2413102.html" %}

#### 小结

这里的stack和queue并没有实际是因为在python里面混淆了array和stack的概念，从而使得很多东西显得奇奇怪怪的

* stack 数组实现
* queue 链表实现 

## 4. 哈希表 Hash

哈希表（Hash Table，也叫散列表），是根据关键码值 \(Key-Value\) 而直接进行访问的数据结构。也就是说，它通过把关键码值映射到表中一个位置来访问记录，以加快查找的速度。哈希表的实现主要需要解决两个问题，哈希函数和冲突解决。

#### 哈希函数

哈希函数也叫散列函数，它对不同的输出值得到一个固定长度的消息摘要。理想的哈希函数对于不同的输入应该产生不同的结构，同时散列结果应当具有同一性（输出值尽量均匀）和雪崩效应（微小的输入值变化使得输出值发生巨大的变化）。

* O\(1\) Insert 
* O\(1\) Find 
* O\(1\) Delete

#### [128. Hash Function](https://www.lintcode.com/problem/hash-function/description)

```python
class Solution:
    """
    @param key: A string you should hash
    @param HASH_SIZE: An integer
    @return: An integer
    """
    def hashCode(self, key, HASH_SIZE):
        # write your code here
        ans = 0
        for char in key :
            ans = (ans*33 + ord(char)) % HASH_SIZE
        return ans
```

#### 冲突的解决方式

冲突（Collision），是说两个不同的 key 经过哈希函数的计算后，得到了两个相同的值。解决冲突的方法，主要有两种：

* 开散列法（Open Hashing）。是指哈希表所基于的数组中，每个位置是一个 Linked List 的头结点。这样冲突的 &lt;key, value&gt; 二元组，就都放在同一个链表中。
* 闭散列法（Closed Hashing）。是指在发生冲突的时候，后来的元素，往下一个位置去找空位。
* 重哈希（Refresh Hashing）。哈希表容量的大小在一开始是不确定的。如果哈希表存储的元素太多（如超过容量的十分之一），我们应该将哈希表容量扩大一倍，并将所有的哈希值重新安排。

{% embed url="https://www.jianshu.com/p/bdf6109ecb18" %}

#### [129. Rehashing](https://www.lintcode.com/problem/rehashing/description)

```python
"""
Definition of ListNode
class ListNode(object):

    def __init__(self, val, next=None):
        self.val = val
        self.next = next
"""
class Solution:
    """
    @param hashTable: A list of The first node of linked list
    @return: A list of The first node of linked list which have twice size
    """
    def addlistnode(self, node, number):
        # 把节点和值加入链表
        if node.next != None:
            self.addlistnode(node.next, number)
        else:
            node.next = ListNode(number)

    def addnode(self, anshashTable, number):
        # 确定位置，如果是空就插入进去，不然就连起来
        p = number % len(anshashTable)
        if anshashTable[p] == None:
            anshashTable[p] = ListNode(number)
        else:
            self.addlistnode(anshashTable[p], number)

    def rehashing(self,hashTable):
        # 扩容两倍
        HASH_SIZE = 2 * len(hashTable)
        anshashTable = [None for i in range(HASH_SIZE)]
        # 遍历一遍
        for item in hashTable:
            p = item
            while p != None:
                self.addnode(anshashTable, p.val)
                p = p.next
        return anshashTable
```

## 5. Heap

堆（heap）也被称为优先队列（priority queue）。队列中允许的操作是先进先出（FIFO），在队尾插入元素，在队头取出元素。而堆也是一样，在堆底插入元素，在堆顶取出元素，但是堆中元素的排列不是按照到来的先后顺序，而是按照一定的优先顺序排列的。这个优先顺序可以是元素的大小或者其他规则。如图一所示就是一个堆，堆优先顺序就是大的元素排在前面，小的元素排在后面，这样得到的堆称为**最大堆**。

{% embed url="https://blog.csdn.net/juanqinyang/article/details/51418629" %}

#### 操作的时间复杂度

* O\(log N\) Add 
* O\(log N\) Remove 
* O\(1\) Min or Max 
* O\(n\) heapify

既然堆有这种最大先出的性质，那么利用堆，就可以构建优先队列。大家知道普通的队列是“先进先出”的原则，而优先队列则是“最大先出”的原则，也就是说，队列中的每一个元素都有一个权重，权重大的最先出队。这种思路最常见的应用是医院排队，一种原则是让病情更严重的患者最先就诊，那如果按照这个原则设计一个就医系统的话，优先队列就是最恰当的选择。

{% embed url="https://blog.csdn.net/guoziqing506/article/details/52372469" %}

基本来说，在hashheap中，remove可以是O\(logn\)，堆的实现：

* 堆用数组即可实现，即先上后下，先左后右边
  * root 当前位置 // 2
  * left 当前位置 \* 2
  * right 当前位置 \* 2 + 1
* siftdown 节点向下依次调整，O\(n\)堆化
* siftup 节点向上依次调整，O\(nlog\)

#### [130. Heapify](https://www.lintcode.com/problem/heapify/description)

```python
class Solution:
    """
    @param: A: Given an integer array
    @return: nothing
    """
    def heapify(self, nums):
        for i in range(1, len(nums)):
            self.siftup(i ,nums)


    def siftup(self, i, nums):
        while (i - 1) // 2 >= 0 and nums[i] < nums[(i - 1)// 2]:
            nums[i], nums[(i - 1) // 2] = nums[(i - 1) // 2], nums[i]
            i = (i - 1) // 2
            
class Solution:
    """
    @param: A: Given an integer array
    @return: nothing
    """
    def heapify(self, A):
        # write your code here
        for i in range(len(A) / 2, -1, -1):
            self.siftdown(A, i)
    
    def siftdown(self, A, pos):
        while pos < len(A):
            smallest = pos
            if pos * 2 + 1 < len(A) and A[pos * 2 + 1] < A[smallest]:
                smallest = pos * 2 + 1
            if pos * 2 + 2 < len(A) and A[pos * 2 + 2] < A[smallest]:
                smallest = pos * 2 + 2
            if pos == smallest:
                break
            
            A[smallest], A[pos] = A[pos], A[smallest]
            
            pos = smallest
```

#### Heap的常见操作

值得注意的是，python的heapq是最小堆

* `heapq.heappush`\(_heap_, _item_\) 
  * Push the value _item_ onto the _heap_, maintaining the heap invariant.
* `heapq.heappop`\(_heap_\)
  * Pop and return the smallest item from the _heap_, maintaining the heap invariant. If the heap is empty, [`IndexError`](https://docs.python.org/3.6/library/exceptions.html#IndexError) is raised. To access the smallest item without popping it, use `heap[0]`.
* `heapq.heappushpop`\(_heap_, _item_\) 
  * Push _item_ on the heap, then pop and return the smallest item from the _heap_. The combined action runs more efficiently than [`heappush()`](https://docs.python.org/3.6/library/heapq.html#heapq.heappush) followed by a separate call to [`heappop()`](https://docs.python.org/3.6/library/heapq.html#heapq.heappop).
* `heapq.heapify`\(_x_\)
  * Transform list _x_ into a heap, in-place, in linear time.

#### [4. Ugly Number II](https://www.lintcode.com/problem/ugly-number-ii/)

这个题很简单，不细说了

```python
import heapq

class Solution:
    """
    @param n: An integer
    @return: the nth prime number as description.
    """
    def nthUglyNumber(self, n):
        # 初始化
        heap = [1]
        visited = set([1])
        
        val = None
        # 找到第n个
        for i in range(n) :
            # pop 最小的出来
            val = heapq.heappop(heap)
            # 序列化一个堆
            for mul in [2, 3, 5] :
                if val * mul not in visited :
                    visited.add(val * mul)
                    heapq.heappush(heap, val * mul)
                    
        return val
```

## 6. 数组

#### Intersection

#### [547. Intersection of Two Arrays](https://www.lintcode.com/problem/intersection-of-two-arrays/description)  

很多种方法

```python
# 解法1
class Solution:
    # @param {int[]} nums1 an integer array
    # @param {int[]} nums2 an integer array
    # @return {int[]} an integer array
    def intersection(self, nums1, nums2):
        # Write your code here
        return list(set(nums1) & set(nums2))
# 解法2
class Solution:
    """
    @param nums1: an integer array
    @param nums2: an integer array
    @return: an integer array
    """
    def intersection(self, nums1, nums2):
        nums1.sort()
        nums2.sort()
        p1, p2 = 0, 0
        result = []
        while p1 != len(nums1) and p2 != len(nums2):
            if nums1[p1] < nums2[p2]:
                p1 += 1
            elif nums2[p2] < nums1[p1]:
                p2 += 1
            else:
                result.append(nums1[p1])
                p1 += 1
                p2 += 1
        return list(set(result))
```

#### [548. Intersection of Two Arrays II ](https://www.lintcode.com/problem/intersection-of-two-arrays-ii/)   

双指针对排序的进行处理

```python
# 解法1
class Solution:
    # @param {int[]} nums1 an integer array
    # @param {int[]} nums2 an integer array
    # @return {int[]} an integer array
    def intersection(self, nums1, nums2):
        # Write your code here
        counts = collections.Counter(nums1)
        result = []

        for num in nums2:
            if counts[num] > 0:
                result.append(num)
                counts[num] -= 1

        return result
# 解法2        
class Solution:
    """
    @param nums1: an integer array
    @param nums2: an integer array
    @return: an integer array
    """
    def intersection(self, nums1, nums2):
        nums1.sort()
        nums2.sort()
        p1, p2 = 0, 0
        result = []
        while p1 != len(nums1) and p2 != len(nums2):
            if nums1[p1] < nums2[p2]:
                p1 += 1
            elif nums2[p2] < nums1[p1]:
                p2 += 1
            else:
                result.append(nums1[p1])
                p1 += 1
                p2 += 1
        return result
```

#### 其他方法

#### 1. 子数组 Subarray

主要解决子数组问题用到的主要是前缀和

* PrefixSum\[i\] = A\[0\] + A\[1\] + ... A\[i - 1\]
* PrefixSum\[0\] = 0 

易知构造 PrefixSum 耗费 O\(n\) 时间和 O\(n\) 空间，如需计算子数组从下标i到下标j之间的所有数之和，则有  

* Sum\(i~j\) = PrefixSum\[j + 1\] - PrefixSum\[i\]

#### 2. 两个排序数组的中位数

在两个排序数组中，求他们合并到一起之后的中位数，

时间复杂度要求：O\(log\(n+m\)\)O\(log\(n+m\)\)，其中 n, m 分别为两个数组的长度

#### LintCode 练习地址：

[http://www.lintcode.com/problem/median-of-two-sorted-arrays/](http://www.lintcode.com/problem/median-of-two-sorted-arrays/)

#### 解法

这个题有三种做法：

1. 基于 FindKth 的算法。整体思想类似于 median of unsorted array 可以用 find kth from unsorted array 的解题思路。
2. 基于中点比较的算法。一头一尾各自丢掉一些，去掉一半的时候，整个问题的形式不变。可以推广到 median of k sorted arrays.
3. 基于二分的方法。二分 median 的值，然后再用二分法看一下两个数组里有多少个数小于这个二分出来的值。

#### 3. 合并区间

#### [156. Merge Intervals](https://www.lintcode.com/problem/merge-intervals/description)   

```python
"""
Definition of Interval.
class Interval(object):
    def __init__(self, start, end):
        self.start = start
        self.end = end
"""

class Solution:
    # @param intervals, a list of Interval
    # @return a list of Interval
    def merge(self, intervals):
        # 先进行排序
        intervals = sorted(intervals, key=lambda x: x.start)
        result = []
        for interval in intervals:
            # 如果没有interval, 或者result里面最后一个不能合并
            if len(result) == 0 or result[-1].end < interval.start:
                result.append(interval)
            # 如果可以合并    
            else:
                result[-1].end = max(result[-1].end, interval.end)
        return result
```

#### 问题描述

给一个排好序的区间序列，插入一段新区间。求插入之后的区间序列。要求输出的区间序列是没有重叠的。

LintCode 练习地址：[http://www.lintcode.com/problem/insert-interval/](http://www.lintcode.com/problem/insert-interval/)

#### 算法描述

1. 将该新区间按照**左端值**插入原区间中，使得原区间**左端值**是有序的。
2. 遍历原区间列表，并把它复制到一个新的`answer`区间列表当中，`answer`是最后要返回的结果。
3. 遍历时，要记录上一次访问的区间`last`。若当前区间**左端值**小于等于`last`区间的**右端值**，说明这两区间有重叠，此时仅更新`last`的**右端值**为这两区间**右端值**较大者；若当前区间**左端值**大于`last`的**右端值**，则可以直接加入`answer`。
4. 返回`answer`。

**F.A.Q**  
**Q：第三步有什么意义？**  
A：插入新区间后的原区间列表，仅能保证左端是有序的。而区间中是否存在重叠，右端是否有序，这些都是未知的。

**Q：时空复杂度多少？**  
A：都是O\(N\)O\(N\)。

**Q：有没有更高效的做法？**  
A：有！在查找左端新区见待插位置时，可以采用二分查找。原算法的的第三步，实际上是在查找右端的位置，也可以用二分查找，这样两次查找的复杂度都降为了O\(logN\)O\(logN\)。但是，**完全没必要**，因为这个算法涉及到数组中间位置的移动，所以O\(N\)O\(N\)的时间复杂度是逃不开的，二分查找的改进对效率提升不明显，而且会增大编码难度。有兴趣的同学可以自己尝试~

## 7. 外排序和K路归并算法

#### 介绍

外排序算法（External Sorting），是指在**内存不够**的情况下，如何对存储在一个或者多个**大文件**中的数据进行排序的算法。外排序算法通常是解决一些大数据处理问题的第一个步骤，或者是面试官所会考察的算法基本功。外排序算法是**海量数据处理算法**中十分重要的一块。  
在学习这类大数据算法时，经常要考虑到内存、缓存、准确度等因素，这和我们之前见到的算法都略有差别。

#### 基本步骤

外排序算法分为两个基本步骤：

1. 将大文件切分为若干个个小文件，并分别使用内存排好序
2. 使用**K路归并算法**（k-way merge）将若干个排好序的小文件合并到一个大文件中

**第一步：文件拆分**

根据内存的大小，尽可能多的分批次的将数据 Load 到内存中，并使用系统自带的内存排序函数（或者自己写个快速排序算法），将其排好序，并输出到一个个小文件中。比如一个文件有1T，内存有1G，那么我们就这个大文件中的内容按照 1G 的大小，分批次的导入内存，排序之后输出得到 `1024` 个 1G 的小文件。

**第二步：K路归并算法**

K路归并算法使用的是数据结构堆（Heap）来完成的，使用 Java 或者 C++ 的同学可以直接用语言自带的 PriorityQueue（C++中叫priority\_queue）来代替。

我们将 K 个文件中的第一个元素加入到堆里，假设数据是从小到大排序的话，那么这个堆是一个最小堆（Min Heap）。每次从堆中选出最小的元素，输出到目标结果文件中，然后**如果这个元素来自第 x 个文件，则从第 x 个文件中继续读入一个新的数进来放到堆里，并重复上述操作**，直到所有元素都被输出到目标结果文件中。

**Follow up: 一个个从文件中读入数据，一个个输出到目标文件中操作很慢，如何优化？**

如果我们每个文件只读入1个元素并放入堆里的话，总共只用到了 `1024` 个元素，这很小，没有充分的利用好内存。另外，单个读入和单个输出的方式也不是磁盘的高效使用方式。因此我们可以为输入和输出都分别加入一个缓冲（Buffer）。假如一个元素有10个字节大小的话，`1024` 个元素一共 10K，1G的内存可以支持约 100K 组这样的数据，那么我们就为每个文件设置一个 100K 大小的 Buffer，**每次需要从某个文件中读数据，都将这个 Buffer 装满。当然 Buffer 中的数据都用完的时候，再批量的从文件中读入**。输出同理，设置一个 Buffer 来避免单个输出带来的效率缓慢。

#### 相关练习

**Lintcode相关练习**  
[合并K个有序数组](http://www.lintcode.com/zh-cn/problem/merge-k-sorted-arrays/)  
[合并K个有序链表](http://www.lintcode.com/zh-cn/problem/merge-k-sorted-lists/)

**面试相关问题**  
[面试题：合并 K 个排好序的大文件](http://www.jiuzhang.com/tutorial/big-data-interview-questions/221)  
[面试题：求两个超大文件中 URLs 的交集](http://www.jiuzhang.com/tutorial/big-data-interview-questions/269)

**更多海量数据算法相关知识可参见**  
[九章算法——海量数据处理算法与面试题全集](http://www.jiuzhang.com/tutorial/big-data-interview-questions/148)

### K路归并类问题

#### 第一种：通过队列的队首进行比较

#### [165. Merge Two Sorted Lists](https://www.lintcode.com/problem/merge-two-sorted-lists/description)

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        dummy = ListNode(0)
        head = dummy
        # 取链表左边值进行比较，小的被串起来
        while l1 is not None and l2 is not None :
            
            if l1.val < l2.val :
                dummy.next = ListNode(l1.val)
                l1 = l1.next
            else : 
                dummy.next = ListNode(l2.val)
                l2 = l2.next
            dummy = dummy.next
        # 有可能没有走完，需要重复    
        if l1 is not None :
            dummy.next = l1
        if l2 is not None :
            dummy.next = l2
        
        return head.next
```

#### [104. Merge K Sorted Lists](https://www.lintcode.com/problem/merge-k-sorted-lists/description)

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

from heapq import heappop, heappush

class Solution:
    """
    @param lists: a list of ListNode
    @return: The head of one sorted list.
    """
    def mergeKLists(self, lists):
        self.sequence = 0
        
        if not lists:
            return None
        
        trav = dummy = ListNode(-1)
        heap = []
        for ll in lists:
            if ll:
                self.heappushNode(heap, ll)
                
        while heap:
            node = heappop(heap)[2]
            trav.next = node
            trav = trav.next
            #print(trav.val)
            if trav.next:
                self.heappushNode(heap, trav.next)
                
        return dummy.next
            
    def heappushNode(self, heap, node):
        self.sequence += 1
        heappush(heap, (node.val, self.sequence, node))
```

#### 第二种：in-place 的归并

* 把小数组 Merge 到有足够空余空间的大数组里

#### [64. Merge Sorted Array](https://www.lintcode.com/problem/merge-sorted-array/description)

逆向思维，从后端比较

```python
class Solution:
    def merge(self, nums1, m, nums2, n):
        """
        :type nums1: List[int]
        :type m: int
        :type nums2: List[int]
        :type n: int
        :rtype: void Do not return anything, modify nums1 in-place instead.
        """
        # 已有num的起始
        i, j = m - 1, n - 1
        index = m + n - 1
        # start from end
        while i >= 0 and j >= 0 :
            if nums1[i] < nums2[j] :
                nums1[index] = nums2[j] 
                j -= 1
            else :
                nums1[index] = nums1[i]
                i -= 1
            index -= 1
        # if j != 0 compare num2
        # [4,5,6,0,0,0] -> [4,5,6,4,5,6]
        # [1,2,3]       -> [1,2,3]
        while j >= 0 :
            nums1[index] = nums2[j]
            index -= 1
            j -= 1
```

#### 第三种：区间类

#### [156. Merge Intervals](https://www.lintcode.com/problem/merge-intervals/description)

```python
class Solution:
    def merge(self, intervals):
        """
        :type intervals: List[Interval]
        :rtype: List[Interval]
        """
        result = []
        # 因为是相邻处理，需要排序
        intervals = sorted(intervals, key = lambda x : x.start)
        
        for interval in intervals :
            # result = [] or can  merge
            if len(result) == 0 or result[-1].end < interval.start :
                result.append(interval)
            else :
                result[-1].end = max(result[-1].end, interval.end)
        return result
```

## 8.Ladder

![](../../assets/screen-shot-2018-11-29-at-12.07.01-pm.png)

![](../../assets/screen-shot-2018-11-29-at-12.07.08-pm.png)

![](../../assets/screen-shot-2018-11-29-at-12.07.12-pm.png)

![](../../assets/screen-shot-2018-11-29-at-12.08.05-pm.png)

![](../../assets/screen-shot-2018-11-29-at-12.08.09-pm.png)

![](../../assets/screen-shot-2018-11-29-at-12.08.14-pm.png)

DP

![](../../assets/screen-shot-2018-11-29-at-12.08.50-pm.png)

![](../../assets/screen-shot-2018-11-29-at-12.08.56-pm.png)

![](../../assets/screen-shot-2018-11-29-at-12.09.00-pm.png)

