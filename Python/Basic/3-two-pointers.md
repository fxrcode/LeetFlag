# Algorithms \(3\) - Two Pointers

## 1. 相向双指针

相向双指针，指的是在算法的一开始，两根指针分别位于数组/字符串的两端，并相向行走，当它们相遇的时候就自动结束。

#### [39. Recover Rotated Sorted Array](https://www.lintcode.com/problem/recover-rotated-sorted-array/description)

三步翻转法，主要是两个指针相向而行，交换数组。

```python
class Solution:
    def recoverRotatedSortedArray(self, nums):
        n = len(nums)
        # corner case
        if n == 0 :
            return []
        # find 
        for i in range(n) :
            if nums[i] < nums[i - 1] :
                break
        # reverse
        if i == n - 1:
            pass
        else :
            nums[:i] = self.reverse_array(nums[:i])
            nums[i:] = self.reverse_array(nums[i:])
            nums = self.reverse_array(nums)
            
        return nums
        
    def reverse_array(self, List) :
        start, end = 0, len(List) - 1
        n = len(List) // 2
        
        while n > 0 :
            List[end], List[start] = List[start], List(end)
            start += 1
            end -= 1
            n -= 1
            
        return List
```

#### [607. Two Sum III - Data structure design](https://www.lintcode.com/problem/two-sum-iii-data-structure-design/description) / [170. Two Sum III - Data structure design](https://leetcode.com/problems/two-sum-iii-data-structure-design/description/)

Two Sum类，只能使用hash表来做的，整个题的思路非常的简单，就是找一下a和sum - a在不在哈希表里，需要调整的是，如果a是有两个，这样自己可以自己组成自己

```python
class TwoSum:
    def __init__(self) :
        self.A = {}
        
    def add(self, number):
        if number in self.A:
            self.A[number] += 1
        else:
            self.A[number] = 1
        return 
    
    def find(self, value):
        for i in self.A:
            if value == 2*i:
                if self.A[i] > 1:
                    return True
            elif value - i in self.A:
                return True
        return False
```

#### [587. Two Sum - Unique Pairs](https://www.lintcode.com/problem/two-sum-unique-pairs/description) 

Two Sum中比较典型的问题，主要是记录一下是否有重复的。

```python
class Solution:
    def twoSum6(self, nums, target):
        # paras
        count = 0
        result = []
        
        # check
        if len(nums) < 2 or target is None :
            return count
            
        nums.sort()
        left, right = 0, len(nums) - 1
        while left < right :
            if nums[left] + nums[right] < target :
                left += 1
            elif nums[left] + nums[right] > target :
                right -= 1
            else :
                conbination = [nums[left], nums[right]]
                if  conbination not in result :
                    count += 1
                    result.append(conbination)
                left += 1
                right -= 1   
                
        return count
```

#### [608. Two Sum II - Input array is sorted](https://www.lintcode.com/problem/two-sum-ii-input-array-is-sorted/description) / [167. Two Sum II - Input array is sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/)

双指针可以进行加速的，因为已经排序完成，如果值过大，移动右指针，过小的话，移动左指针。

```python
class Solution:
    def twoSum(self, nums, target):
        n = len(nums) 
        # init
        if n == 0 :
            return []
            
        start,  end = 0, n - 1
        while start < end :
            if nums[start] + nums[end] == target :
                return [start + 1, end + 1]
            if nums[start] + nums[end] < target :
                start += 1
            if nums[start] + nums[end] > target :
                end -= 1

        return []
```

#### [57. 3Sum](https://www.lintcode.com/problem/3sum/description) / [15. 3Sum](https://leetcode.com/problems/3sum/) 

看成是2sum，只需要固定一个值，取负的target，然后双指针如two sum遍历一遍数组就可以了，不是很难。

```python
class Solution:
    def threeSum(self, nums):
        # corner
        if len(nums) < 3 :
            return []
            
        visited = []
        nums.sort()
        # traverse
        for index in range(0, len(nums) - 2):
            # equals 
            if index and nums[index] == nums[index - 1]:
                continue
            target = - nums[index]
            self.twoSum(index + 1, len(nums) - 1, target, nums, visited)

        return visited
            
    def twoSum(self, start, end, target, nums, visited) :
        # iniit
        left, right = start, end
        while left < right:
            if nums[left] + nums[right] == target:
                visited.append((-target, nums[left], nums[right]))
                right -= 1
                left += 1
                
                while left < right and nums[left] == nums[left - 1]:
                    left += 1
                while left < right and nums[right] == nums[right + 1]:
                    right -= 1   
            elif nums[left] + nums[right] > target:
                right -= 1
            else:
                left += 1
```

#### [382. Triangle Count](https://www.lintcode.com/problem/triangle-count/description) 

排序后，先找到最大边，然后从0, i - 1找起， 如果左边满足，自然中间的都满足，依次节约时间进行快速运算即可。

```python
class Solution:
    def triangleCount(self, S):
        S.sort() 
        ans = 0
        for i in range(len(S)):
            left, right = 0, i - 1
            while left < right:
                if S[left] + S[right] > S[i]:
                    ans += right - left
                    right -= 1
                else:
                    left += 1
        return ans
```

#### Two Sum计数问题

#### [609. Two Sum - Less than or equal to target](https://www.lintcode.com/problem/two-sum-less-than-or-equal-to-target/description)

简化的地方在于Two Sum右边最大，如果相加都小，那么中间所有的都小，移动左指针即可。

```python
class Solution:
    def twoSum5(self, nums, target):
        l, r = 0, len(nums)-1
        cnt = 0
        nums.sort()
        while l < r:
            value = nums[l] + nums[r]
            if value > target:
                r -= 1
            else:
                cnt += r - l
                l += 1
        return cnt
```

#### [1. Two Sum](https://leetcode.com/problems/two-sum)

hash O（n）

```python
class Solution(object):
    def twoSum(self, nums, target):
        if len(nums) <= 1:
            return 
        buff_dict = {}
        for i in range(len(nums)):
            if nums[i] in buff_dict:
                return [buff_dict[nums[i]], i]
            else:
                buff_dict[target - nums[i]] = i
```

#### [443. Two Sum - Greater than target](https://www.lintcode.com/problem/two-sum-greater-than-target/description)

和上面一样，移动右指针就行。

```python
class Solution:
    def twoSum2(self, nums, target):
        # init
        left, right = 0, len(nums) - 1
        cnt = 0
        nums.sort()
        
        while left < right :
            twoSum = nums[left] + nums[right]
            if twoSum <= target :
                left += 1
            else :
                cnt += right - left
                right -= 1
        
        return cnt
```

#### [533. Two Sum - Closest to target](https://www.lintcode.com/problem/two-sum-closest-to-target/)

主要是不断更新，如果找到最小就返回。

```python
class Solution:
    def twoSumClosest(self, nums, target):
        nums.sort()
        i, j = 0, len(nums)  - 1

        diff = sys.maxsize
        while i < j:
            if nums[i] + nums[j] < target:
                diff = min(diff, target - nums[i] - nums[j])
                i += 1
            else:
                diff = min(diff, nums[i] + nums[j] - target)
                j -= 1

        return diff
```

#### [59. 3Sum Closest](https://www.lintcode.com/problem/3sum-closest/)

在前一个题的基础上不断找最大和最小，不断更新。

```python
class Solution:
    def threeSumClosest(self, numbers, target):
        numbers.sort()
        ans = None
        for i in range(len(numbers)):
            left, right = i + 1, len(numbers) - 1
            while left < right:
                sum = numbers[left] + numbers[right] + numbers[i]
                if ans is None or abs(sum - target) < abs(ans - target):
                    ans = sum
                    
                if sum <= target:
                    left += 1
                else:
                    right -= 1
        return ans
```

#### 小结：

这里的题比较刻意练习同向双指针，但是实际上，two sum类问题使用hash和dp也是可以解决的，拿到具体的题需要具体分析，模板固然重要，但也需要做到灵活应用，运用自如。

## 2. 同向双指针

计算复杂度 O\(n\)

#### [521. Remove Duplicate Numbers in Array](https://www.lintcode.com/problem/remove-duplicate-numbers-in-array/description) / [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)

第一种做法使用hash表进行记录，就是遍历一次如果不在hash table就扔掉

* 时间复杂度 O\(n\)   
* 空间复杂度 O\(n\)

```python
class Solution:
    def deduplication(self, nums):
        hashList, result = {}, 0
        for val in nums :
            if val not in hashList :
                nums[result] = val
                hashList[val] = 1
                result += 1
                
        return result
```

第二种做法，双指针同向，这里有两个指针一个slow\_pointer，另一个quick\_pointer，slow\_pointer一直从0开始，而quick\_pointer遍历整个数组，如果遇到不一样的，就写入slow\_pointer，然后slow\_pointer右移一格。

```python
class Solution:
    def deduplication(self, nums):
        n = len(nums) 
        if n == 0 :
            return 0
            
        nums.sort()
        slow_pointer = 1
        for quick_pointer in range(1, n) :
            
            if nums[quick_pointer - 1] != nums[quick_pointer] :
                nums[slow_pointer] = nums[quick_pointer]
                slow_pointer += 1
                
        return slow_pointer
```

#### [604. Window Sum](https://www.lintcode.com/problem/window-sum/description)

左右指针分别保持一定的距离，每次一进一出进行加减就可以了，不要使用\[:\]进行切片，如果滑动窗口非常大，计算复杂度会非常非常高

```python
class Solution:
    def winSum(self, nums, k):
        n = len(nums)
        if n == 0 or n < k :
            return []
            
        k_sum = sum(nums[0:k])
        result = [k_sum]
        
        for right in range(k, n) :
            left = right - k
            k_sum += nums[right] - nums[left]
            result.append(k_sum)
            
        return result
```

#### [610. Two Sum - Difference equals to target](https://www.lintcode.com/problem/two-sum-difference-equals-to-target/description)

双指针遍历即可

```python
class Solution:

    def twoSum7(self, nums, target):
        if len(nums) == 0 :
            return []
            
        for left in range(0, len(nums)) :
            for right in range(left + 1, len(nums)) :
                if nums[left] - nums[right] == target :
                    return [left + 1, right + 1]
                
                if nums[right] - nums[left] == target :
                    return [left + 1, right + 1]
                    
        return []
```

#### [228. Middle of Linked List](https://www.lintcode.com/problem/middle-of-linked-list/description)

快慢指针，主要是通过控制快指针走两步，而慢指针只走一步的方法，以此实现了取中点的方法。

```python
"""
Definition of ListNode
class ListNode(object):
    def __init__(self, val, next=None):
        self.val = val
        self.next = next
"""

class Solution:
    # @param head: the head of linked list.
    # @return: a middle node of the linked list
    def middleNode(self, head):
        # Write your code here
        if head == None :
            return None
        
        fast, slow = head, head
        while fast and fast.next and fast.next.next :
            slow = slow.next
            fast = fast.next.next
        
        return slow
```

#### [102. Linked List Cycle](https://www.lintcode.com/problem/linked-list-cycle/description) /  [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)

主要是用快慢指针判断是否存在环，如果要判断入口，只需要将快指针重置到起点，并确定步长为1即可。

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
    @param head: The first node of linked list.
    @return: True if it has a cycle, or false
    """
    def hasCycle(self, head):
        
        if not head or not head.next:
            return False

        slow = head
        fast = head

        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            if slow is fast:
                return True
    
        return False
```

#### [539. Move Zeros](https://www.lintcode.com/problem/move-zeroes/) 

思路：两个指针指向同一个点，如果后一个是0，就使得左指针多走一格，这样交换它们的位置，这样用n次扫描之后，就可以两两互换。

* 如果不要求改变顺序，可以使用quick sort进行快速排序

```python
class Solution:
    def moveZeroes(self, nums):
        left, right = 0, 0
        while right < len(nums):
            if nums[right] != 0:
                nums[left], nums[right] = nums[right], nums[left]
                left += 1
            right += 1
```

#### Follow up : 如何使用最小的写入次数

就是不用继续交换了，每次都将左边的值赋给右边就可以，然后判断左指针位置，剩下的都给0就可以了。

```python
class Solution:
    def moveZeroes(self, nums):
        left, right = 0, 0
        while right < len(nums):
            if nums[right] != 0:
                nums[left] = nums[right]
                left += 1
            right += 1
            
        while left < len(nums):
            nums[left] = 0
            left += 1
```

#### 关于双指针的难题和用法参加这里下面

{% embed url="https://zijiang.gitbook.io/notes/computer-science/advanced-algorithms/advanced-algorithms-1-two-pointers-follow-up" %}

## 3. 经典排序算法

经典的排序算法应该是需要每天都写一遍的，这两个算法的作用对于整个编程的效率和熟练度的提升非常的显著。

{% embed url="https://visualgo.net/bn/sorting" %}

**Quick Sort**

主要思想：现在数组中随便找到一个数字，然后用头尾两个指针相向依次遍历，如果相遇就退出，使得左边的数永远小于右边，如果在左右都找到了符合要求的数字，就进行互换。

* 注意需要left &lt;= right 防止死循环
* 因为在循环中left和right指针变化了，所以需要再次判断
* 因为left和right最后交错了，所以递归的时候注意起点和中点

```python
class Solution:
    def sortIntegers2(self, A):
        self.quickSort(A, 0, len(A) - 1)
    
    def quickSort(self, A, start, end):
        if start >= end:
            return
        left, right = start, end
        # key point 1: pivot is the value, not the index
        pivot = A[(start + end) // 2]
        # key point 2: every time you compare left & right, it should be 
        # left <= right not left < right
        while left <= right:
            while left <= right and A[left] < pivot:
                left += 1
            while left <= right and A[right] > pivot:
                right -= 1
            if left <= right:
                A[left], A[right] = A[right], A[left]
                left += 1
                right -= 1
        self.quickSort(A, start, right)
        self.quickSort(A, left, end)
```

**Merge Sort**

主要思路：先将数组分成两个部分，依次比较数组左右的大小，并形成新的序列。

* 去右边边界的时候需要非常小心，要想一下是开区间还是闭区间
* 比较之后可能会有单个指针走的快的情况，因而要将慢一点的指针走完

```python
class Solution:
    def sortIntegers2(self, A):
        temp = [0 for _ in range(len(A))]
        self.merge_sort(0, len(A) - 1, A, temp)
        
    def merge_sort(self, start, end, A, temp):
        if start >= end:
            return
        mid = (start + end) // 2
        self.merge_sort(start, mid , A, temp)
        self.merge_sort(mid + 1, end, A, temp)
        self.merge(start, mid, end, A, temp)
        
    def merge(self, start, mid, end, A, temp):
        left, right = start, mid + 1
        index = start
        while left <= mid and right <= end:
            if A[left] < A[right]:
                temp[index] = A[left]
                left += 1
            else:
                temp[index] = A[right];
                right += 1
                
            index += 1
            
        while left <= mid:
            temp[index] = A[left]
            left += 1
            index += 1
            
        while right <= end:
            temp[index] = A[right]
            right += 1
            index += 1
            
        for index in range(start, end + 1):
            A[index] = temp[index]
```

#### Quick Sort & Merge Sort 比较

一般来讲，如果不对空间进行要求，应该直接使用merge sort，因为merge sort至是损失了空间。

* 这两个算法非常重要也非常经典，需要**理解并背诵**

| 算法 | 时间复杂度 | 空间复杂度 | 稳定性 |
| :--- | :--- | :--- | :--- |
| Quick | nlogn | 1 | 不好，平均时间 |
| Merge | nlogn | n | 好，稳定 |

#### Quick Select

quick sort二分

```python
class Solution:
    def kthSmallest(self, k, nums):
        return self.quick_select(0, len(nums) - 1, nums, k - 1)
        
    def quick_select(self, start, end, nums, k) :
        if start == end :
            return nums[start]
            
        left, right = start, end
        pivot = nums[(start + end) // 2]
        
        while left <= right :
            while left <= right and nums[left] < pivot :
                left += 1
            while left <= right and nums[right] > pivot :
                right -= 1
                
            if left <= right :
                nums[left], nums[right] = nums[right], nums[left]
                # start, right, left, end
                left += 1
                right -= 1
        
        if start <= right and right >= k :
            self.quick_select(start, right, nums, k)
        if left <= end and left <= k :
            self.quick_select(left, end, nums, k)
            
        return nums[k]
```

#### Partition Array

在原有的quick select 和 quick sort的基础之上，有比较特殊的partition方法，模板如下:

```python
while left <= right :
    while left <= right and nums[left] 在左则：
        left += 1
    while left <= right and nums[right] 在右侧:
        right -= 1
        
    if left <= right: 
        # 找到不应该在左边和右边的
        nums[left], nums[right] = nums[right], nums[left]
        left += 1
        right -= 1
```

#### [148. Sort Colors](https://www.lintcode.com/problem/sort-colors/description) / [75. Sort Colors](https://leetcode.com/problems/sort-colors/)

#### 解法分析

在颜色排序（Sort Color）这个问题中，传统的双指针算法可以这么做：

1. 先用 partition 的方式区分开 0 和 1, 2
2. 再在右半部分区分开 1 和 2

这个算法不可避免的要使用两次 Parition，写两个循环。许多面试官会要求你，能否只 partition 一次，也就是只用一个循环。

用一个循环的方法如下：

[http://www.jiuzhang.com/solution/sort-colors](http://www.jiuzhang.com/solution/sort-colors)

分析一下核心代码部分：

```java
public void sortColors(int[] a) {
    if (a == null || a.length <= 1) {
        return;
    }
    
    int pl = 0;
    int pr = a.length - 1;
    int i = 0;
    while (i <= pr) {
        if (a[i] == 0) {
            swap(a, pl, i);
            pl++;
            i++;
        } else if(a[i] == 1) {
            i++;
        } else {
            swap(a, pr, i);
            pr--;
        }
    }
}
```

pl 和 pr 是传统的双指针，分别代表 0~pl-1 都已经是 0 了，pr+1~a.length - 1 都已经是 2 了。  
另一个角度说就是，如果你发现了一个 0 ，就可以和 pl 上的数交换，pl 就可以 ++；如果你发现了一个 2 就可以和 pr 上的数交换 pr 就可以 --。

这样，我们用第三根指针 i 来循环整个数组。如果发现 0，就丢到左边（和 pl 交换，pl++），如果发现 2，就丢到右边（和 pr 交换，pr--），如果发现 1，就不管（i++）

这就是三根指针的算法，两根指针在两边，一根指针扫描所有的数。

这里有一个实现上的小细节，当发现一个 0 丢到左边的时候，i需要++，但是发现一个2 丢到右边的时候，i不用++。原因是，从pr 换过来的数有可能是0或者2，需要继续判断丢到左边还是右边。而从 pl 换过来的数，要么是0要么是1，不需要再往右边丢了。因此这里 i 指针还有一个角度可以理解为，i指针的左侧，都是0和1。

**类似的题**

G家问过一个类似的题：给出 low, high 和一个数组，将数组分为三个部分，&lt; low, &gt;= low & &lt;= high, &gt; high。解法和本题一模一样

#### [143. Sort Colors II](https://www.lintcode.com/problem/sort-colors-ii/description)

烙饼排序是说，如果有一个操作 flip\(arr, i\)，能够在 O\(1\) 的时间内将数组 arr 的前 i 个数进行翻转。你能否用这个操作来实现一个排序算法？因为 flip 操作就像是一沓烙饼伸一个铲子进去然后把最上面的 i 个烙饼翻转了一下，因此叫做烙饼排序（Pancake Sorting）。

GeeksforGeeks 上已经有比较好的讲解和代码：

[https://www.geeksforgeeks.org/pancake-sorting/](https://www.geeksforgeeks.org/pancake-sorting/)

LintCode 练习地址：  
[http://www.lintcode.com/problem/pancake-sorting/](http://www.lintcode.com/problem/pancake-sorting/)

## 4. Ladder

![](../../assets/screen-shot-2018-09-23-at-10.52.12-am.png)

![](../../assets/screen-shot-2018-09-23-at-10.52.17-am.png)

![](../../assets/screen-shot-2018-09-23-at-10.52.21-am.png)



