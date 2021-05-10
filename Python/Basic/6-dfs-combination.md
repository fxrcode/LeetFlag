# Algorithms \(6\) - DFS Combination

在非二叉树上的深度优先搜索（Depth-first Search）中，90%的问题，不是求组合（Combination）就是求排列（Permutation）。特别是组合类的深度优先搜索的问题特别的多。

#### 组合搜索问题 Combination

* 问题模型: 求出所有满足条件的“组合”。 
* 判断条件: 组合中的元素是顺序无关的。
* 时间复杂度：与 2^n 相关 （多层for循环）。

## 1. 全子集问题

#### 如何用最简单的递归方式来实现？

使用DFS组合的思路，对于每一位，考虑是否需要加入subset，是的话append再DFS，否的话pop再DFS

* 需要注意的是python的reference，这里不断的new 新的list，所以不存在之前的问题

```python
class Solution:
    """
    @param nums: A set of numbers
    @return: A list of lists
    """
    def subsets(self, nums):
        # write your code here
        self.result = []
        self.dfs(sorted(nums), [], 0)
        return self.result
    
    # 1. 递归的定义    
    def dfs(self, nums, subset, level) :
        # 3.出口
        if len(nums) == level :
            self.result.append(subset)
            return
        
        # 2.分拆
        # 加进去
        self.dfs(nums, subset + [nums[level]], level + 1)
        # 不加
        self.dfs(nums, subset, level + 1)
```

#### 如何用可以推广到排列类搜索问题的递归方式来实现？

使用DFS排列的思路，对于每一位，从这一位往后进行DFS，DFS结束后要pop（回溯）

```python
class Solution:
    """
    @param nums: A set of numbers
    @return: A list of lists
    """
    def subsets(self, nums):
        # write your code here
        self.result = []
        self.dfs(sorted(nums), [], 0)
        return self.result
    
    # 1. 递归的定义    
    def dfs(self, nums, subset, level) :
        # 3.出口
        self.result.append(subset[:])
        # 2.分拆
        for i in range(level, len(nums)) :
            # 向下走
            subset.append(nums[i])
            self.dfs(nums, subset, i + 1)
            # 回溯
            subset.pop(-1)
```

#### 如果集合中有重复元素如何处理？

带有重复函数对数据进行是否重复的判断

```python
class Solution:
    """
    @param nums: A set of numbers.
    @return: A list of lists. All valid subsets.
    """
    def subsetsWithDup(self, nums):
        # write your code here
        self.results = []
        self.dfs(sorted(nums), [], 0)
        return self.results
        
    def dfs(self, nums, subset, index) :
        self.results.append(subset[:])
        # 判断是否重复
        for i in range(index, len(nums)) :
            if i != 0 and nums[i] == nums[i - 1] and i > index :
                continue
            
            subset.append(nums[i])
            self.dfs(nums, subset, i + 1)
            subset.pop()
```

#### 通用的DFS时间复杂度计算公式 

O\(答案个数 \* 构造每个答案的时间\)

http://www.jiuzhang.com/qa/2994/

## 2. 重复元素和限制条件

#### 135. Combination Sum

* Combination Sum 限制了组合中的数之和 
  *  加入一个新的参数来限制
* Subsets 无重复元素
  * Combination Sum 有重复元素 
  * 需要先去重
* Subsets 一个数只能选一次，Combination Sum 一个数可以选很多次
  * 搜索时从 index 开始而不是从 index + 1

```python
class Solution:
    """
    @param candidates: A list of integers
    @param target: An integer
    @return: A list of lists of integers
    """
    def combinationSum(self, candidates, target):
        # write your code here
        self.results = []
        self.dfs(sorted(candidates), 0, [], target)
        return self.results
        
    def dfs(self, nums, index, subset, target) :
        
        if target == 0 :
            self.results.append(subset[:])
     
        for i in range(index, len(nums)) :
            
            if nums[i] > target :
                break
            # 去重
            if i != index and nums[i - 1] == nums[i] :
                continue
            
            subset.append(nums[i])
            self.dfs(nums, i, subset, target - nums[i])
            subset.pop()
```

#### 153. Combination Sum II

主要考察对去重的理解

```python
class Solution:
    """
    @param num: Given the candidate numbers
    @param target: Given the target number
    @return: All the combinations that sum to target
    """
    def combinationSum2(self, num, target):
        # write your code here  
        self.results = []
        self.dfs(sorted(num), 0, [], target)
        return self.results
        
    def dfs(self, nums, index, subset, target) :
        
        if target == 0 :
            self.results.append(subset[:])
            return
        
        for i in range(index, len(nums)) :
            
            if nums[i] > target :
                break
            if nums[i] == nums[i - 1] and i != index :
                continue
            subset.append(nums[i])
            self.dfs(nums, i + 1, subset, target - nums[i])
            subset.pop()
```

## 3. 记忆化搜索

#### 192. Wildcard Matching

这个题不是特别难，但是很典型。

```python
class Solution:
    """
    @param s: A string 
    @param p: A string includes "?" and "*"
    @return: is Match?
    """
    def isMatch(self, source, pattern):
        return self.is_match(source, 0, pattern, 0, {})
    
    def is_match(self, source, i, pattern, j, memo) :
        # 记忆化搜索
        if (i, j) in memo :
            return memo[(i, j)]
        # DFS出口    
        # 如果source为空
        if len(source) == i :
            # check pattern
            for index in range(j, len(pattern)) :
                if pattern[index] != '*' :
                    return False
            return True
        # pattern empty
        if len(pattern) == j :
            return False
            
        # 递归条件
        # 如果不是*，看一下是否match，然后找下一个
        if pattern[j] != '*' :
            matched = self.is_match_char(source[i], pattern[j]) and self.is_match(source, i + 1, pattern, j + 1, memo)
        # 如果是*,两个指针随便移动一个即可
        else :
            matched = self.is_match(source, i + 1, pattern, j, memo) or self.is_match(source, i, pattern, j + 1, memo)
        # 记忆
        memo[(i, j)] = matched
        return matched
    
    def is_match_char(self, s, p) :
        return s == p or p == '?'
```

## 4. Ladder

![](../../assets/screen-shot-2018-11-29-at-12.05.24-pm.png)

![](../../assets/screen-shot-2018-11-29-at-12.05.30-pm.png)

![](../../assets/screen-shot-2018-11-29-at-12.05.34-pm.png)