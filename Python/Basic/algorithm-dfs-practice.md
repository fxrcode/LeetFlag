# Algorithm - DFS Practice

在刷dfs的过程中，感觉自己对dfs的掌握不是特别的好，这严重影响了我对于dp的理解，因为dp本质还是一种dfs，所以这里先刷一刷简单题，强制自己写出来一些思路，慢慢锻炼思维

#### [155. Minimum Depth of Binary Tree](https://www.lintcode.com/problem/minimum-depth-of-binary-tree/description) / [111. Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/)

递归三要素：

* 定义：使用的树的形式，具体来讲需要取左右节点
* 出口：出口在于触及到叶节点，叶节点的特征是null，所以如果是null，那么返回0即可
* 递归的拆解：左右两个树，返回两个数里面最小的即可，这个问题的核心是到底怎么样确定节点的深度，如果左边节点和右边节点都不在就是0，如果其中有一个在就是1，这就是这一层的深度，所以递归的主要是这一部分

```python
class Solution:
    def minDepth(self, root):
        if root is None :
            return 0
            
        if not root.left or not root.right :
            return max(self.minDepth(root.left), self.minDepth(root.right)) + 1
        else :
            return min(self.minDepth(root.left), self.minDepth(root.right)) + 1
```

BFS的思路

* 很简单就是层级遍历，如果遇到了下一个节点是null，那么很简单，就是已经触底了，这时的深度就是最小的深度

```python
class Solution:
    def minDepth(self, root):
        # if root is leaf
        if root is None :
            return 0
        
        queue = collections.deque([root])
        depth = 0
        while queue :
            depth += 1
            for _ in range(len(queue)) :
                node = queue.popleft()
                # if node is leaf node
                if not node.left and not node.right:
                    return depth
                
                if node.right :
                    queue.append(node.right)
                if node.left :
                    queue.append(node.left)
        return depth
```

#### [97. Maximum Depth of Binary Tree](https://www.lintcode.com/problem/maximum-depth-of-binary-tree/description) / [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

* 出口：如果遇到了null，return 0
* 子问题：一个节点分为两边，左边最大的和右边最大的中再去最大的

```python
class Solution:
    def maxDepth(self, root):
        if root is None :
            return 0
        
        return max(self.maxDepth(root.left), self.maxDepth(root.right)) + 1
```







[551. Nested List Weight Sum](https://www.lintcode.com/problem/nested-list-weight-sum/description) / [339. Nested List Weight Sum ](https://leetcode.com/problems/nested-list-weight-sum/)



