# Algorithm Pre - Binary Tree

这一部分较为重要因为这涉及到了BFS和DFS的全部内容，所以优先放在了BFS的前面，以便于后面的理解。

## 1. 二叉树专题

二叉树因为通过树的形式可以将数据存储的比较好，而使得高度为Logn\(平衡二叉树\)，因而它的特性非常重要，这也对后面的heap等数据就够产生了深远的影响。

而对它来说，最重要的就是怎么获得元素，或者在树里面进行查找，这就涉及了遍历、分治、递归的主体思想。

### 遍历\(Traverse\)

遍历（Traversal），顾名思义，就是**通过某种顺序，一个一个访问一个数据结构中的元素**。比如我们如果需要遍历一个数组，无非就是要么从前往后，要么从后往前遍历。但是对于一棵二叉树来说，他就有很多种方式进行遍历：

* 层序遍历\(Level order\) / 先序遍历\(Pre order\)/ 中序遍历\(In order\) / 后序遍历\(Post order\)

通过 BFS 获得的顺序我们也可以称之为 BFS Order，即一层一层遍历。而剩下的三种遍历，都需要通过深度优先搜索的方式来获得。而这一小节中，我们将讲一下通过深度优先搜索（DFS）来获得的节点顺序，

**a. 先序遍历（又叫先根遍历、前序遍历 - 根左右）**

首先访问根结点，然后遍历左子树，最后遍历右子树。**遍历左、右子树时，仍按先序遍历**。若二叉树为空则返回。该过程可简记为**根左右**，注意该过程是**递归的**。如图先序遍历结果是：**ABDECF**。  


![&#x56FE;&#x7247;](http://media.jiuzhang.com/markdown/images/3/15/d77b07ce-27f7-11e8-9f14-0242ac110002.jpg)

#### [66. Binary Tree Preorder Traversal](https://www.lintcode.com/problem/binary-tree-preorder-traversal/description) / [144. Binary Tree Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal/)

```python
"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        this.val = val
        this.left, this.right = None, None
"""
# Version 0: Recursion 
class Solution:
    def preorderTraversal(self, root):
        self.results = []
        self.traverse(root)
        return self.results
        
    def traverse(self, root):
        if root is None:
            return
        self.results.append(root.val)
        self.traverse(root.left)
        self.traverse(root.right)

# Version 1: Non-Recursion 
class Solution:
    def preorderTraversal(self, root):
        if root is None:
            return []
        stack = [root]
        preorder = []
        while stack:
            node = stack.pop()
            preorder.append(node.val)
            if node.right:
                stack.append(node.right)
            if node.left:
                stack.append(node.left)
        return preorder
```

**b. 中序遍历（又叫中根遍历 - 左根右）**

首先遍历左子树，然后访问根结点，最后遍历右子树。**遍历左、右子树时，仍按中序遍历**。若二叉树为空则返回。简记为**左根右**。

![&#x56FE;&#x7247;](http://media.jiuzhang.com/markdown/images/3/15/d77b07ce-27f7-11e8-9f14-0242ac110002.jpg)

上图中序遍历结果是：**DBEAFC**。

#### [67. Binary Tree Inorder Traversal](https://www.lintcode.com/problem/binary-tree-inorder-traversal/description) / [94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)

```python
#1. Recursion
class Solution:
    def inorderTraversal(self, root):
        self.result = []
        self.traverse(root)
        return self.result
        
    def traverse(self, root) :
        if root is None :
            return 
        
        self.traverse(root.left)
        self.result.append(root.val)
        self.traverse(root.right)

#2.traverse
class Solution:
    def inorderTraversal(self, root):
        if root is None :
            return []
            
        stack, result = [], []
        while root :
            stack.append(root)
            root = root.left
            
        while stack :
            current_node = stack.pop()
            result.append(current_node.val)
            
            if current_node.right :
                current_node = current_node.right 
                while current_node :
                    stack.append(current_node)
                    current_node = current_node.left
                    
        return result
```

**c. 后序遍历（又叫后根遍历 - 左右根）**

首先遍历左子树，然后遍历右子树，最后访问根结点。**遍历左、右子树时，仍按后序遍历**。若二叉树为空则返回。简记为**左右根**。



![&#x56FE;&#x7247;](http://media.jiuzhang.com/markdown/images/3/15/d77b07ce-27f7-11e8-9f14-0242ac110002.jpg)

上图后序遍历结果是：**DEBFCA**。

#### [68. Binary Tree Postorder Traversal](https://www.lintcode.com/problem/binary-tree-postorder-traversal/description) / [145. Binary Tree Postorder Traversal](https://leetcode.com/problems/binary-tree-postorder-traversal/)

```python
# 1. Recursion
class Solution:
     def postorderTraversal(self, root):
         # write your code here
         self.result = []
         self.traverse(root)
         return self.result
        
     def traverse(self, root) :
         if root is None :
             return
        
         self.traverse(root.left)
         self.traverse(root.right)
         self.result.append(root.val)
        
# 2. Traverse
class Solution:
    def postorderTraversal(self, root):
        # 非递归
        result = []
        stack = []

        curNode = root
        while stack or curNode:
            # 能左就左，否则向右一步
            while curNode:
                stack.append(curNode)
                curNode = curNode.left if curNode.left else curNode.right

            # pop stack，添加到结果
            curNode = stack.pop()
            result.append(curNode.val)

            # 栈不空且当前节点是栈顶的左子节点，转到其右兄弟，否则退栈
            if stack and stack[-1].left == curNode:
                curNode = stack[-1].right
            else:
                curNode = None

        return result
```

* 注意三种遍历方法：根左右，左根右，左右根，只记忆根就会非常地容易，以遍历法来说也是最好实现的
* 需要多练习，之前写的的时候，会分不清往左还是往右

### 分治法\(Divide & Conquer）

分治法（Divide & Conquer Algorithm）是说将一个大问题，拆分为2个或者多个小问题，当小问题得到结果之后，合并他们的结果来得到大问题的结果。

* 关键在于从小问题出发架构出大问题的基本逻辑

#### 为什么二叉树的问题适合使用分治法？

在一棵二叉树（Binary Tree）中，如果将整棵二叉树看做一个大问题的话，那么根节点（Root）的左子树（Left subtree）就是一个小问题，右子树（Right subtree）是另外一个小问题。这是一个天然就帮你完成了“分”这个步骤的数据结构。

**小结两者之间的关系**

分治法（Divide & Conquer）与遍历法（Traverse）是两种常见的递归（Recursion）方法。从程序实现角度分治法的递归函数，通常有一个`返回值`，遍历法通常没有。

* **分治法解决问题的思路**
  * 先让左右子树去解决同样的问题，然后得到结果之后，再整合为整棵树的结果。
* **遍历法解决问题的思路**
  * 通过前序/中序/后序的某种遍历，游走整棵树，通过一个全局变量或者传递的参数来记录这个过程中所遇到的点和需要计算的结果。

### 递归

**什么是递归 \(Recursion\) ？**

很多书上会把递归（Recursion）当作一种算法。事实上，递归是包含两个层面的意思的：

1. 一种由大化小，由小化无的解决问题的算法。类似的算法还有动态规划（Dynamic Programming）。
2. 一种程序的实现方式。这种方式就是一个函数（Function / Method / Procedure）自己调用自己。

与之对应的，有非递归（Non-Recursion）和迭代法（Iteration），你可以认为这两个概念是一样的概念（番茄和西红柿的区别）。不需要做区分。

#### 递归三要素

* **递归的定义** 
  * 每一个递归函数，都需要有明确的定义，有了正确的定义以后，才能够对递归进行拆解。
* **递归的拆解**
  * 一个`大问题`如何拆解为若干个`小问题`去解决
* **递归的出口** 
  * 什么时候可以直接知道答案，不用再拆解，直接 return

**什么是搜索 \(Search\)？**

搜索分为深度优先搜索（Depth First Search）和宽度优先搜索（Breadth First Search），通常分别简写为 DFS 和 BFS。搜索是一种类似于枚举（Enumerate）的算法。比如我们需要找到一个数组里的最大值，我们可以采用枚举法，因为我们知道数组的范围和大小，比如经典的打擂台算法：

```python
int max = nums[0];
for i in range(len(nums)) :
    max = Math.max(max, nums[i]);
```

枚举法通常是你知道循环的范围，然后可以用几重循环就搞定的算法。比如我需要找到 所有 x^2 + y^2 = K 的整数组合，可以用两重循环的枚举法：

```python
// 不要在意这个算法的时间复杂度
for x in range(k + 1) :
    for y in range(k + 1) :
        if x*x + y*y == k :
            print (x, '-', y)
```

而有的问题，比如求 N 个数的全排列，你可能需要用 N 重循环才能解决。这个时候，我们就倾向于采用递归的方式去实现这个变化的 N 重循环。这个时候，我们就把算法称之为`搜索`。因为你已经不能明确的写出一个不依赖于输入数据的多重循环了。

通常来说 DFS 我们会采用递归的方式实现（当然你强行写一个非递归的版本也是可以的），而 BFS 则无需递归（使用队列 Queue + 哈希表 HashMap就可以）。**所以我们在面试中，如果一个问题既可以使用 DFS，又可以使用 BFS 的情况下，一定要优先使用 BFS。**因为他是非递归的，而且更容易实现。

**什么是回溯\(Backtracking\)？**

有的时候，深度优先搜索算法（DFS），又被称之为回溯法，所以你可以完全认为回溯法，就是深度优先搜索算法。在我的理解中，回溯实际上是深度优先搜索过程中的一个步骤。比如我们在进行全子集问题的搜索时，假如当前的集合是 {1,2} 代表我正在寻找以 {1,2}开头的所有集合。那么他的下一步，会去寻找 {1,2,3}开头的所有集合，然后当我们找完所有以 {1,2,3} 开头的集合时，我们需要把 3 从集合中删掉，回到 {1,2}。然后再把 4 放进去，寻找以 {1,2,4} 开头的所有集合。这个把 3 删掉回到 {1,2} 的过程，就是回溯。

```text
subset.add(nums[i]);
subsetsHelper(result, subset, nums, i + 1);
subset.remove(len(nums) - 1) // 这一步就是回溯
```

### AVL Tree

平衡二叉树（Balanced Binary Tree，又称为AVL树，**有别于AVL算法**）是二叉树中的一种特殊的形态。二叉树当且仅当满足如下两个条件之一，是平衡二叉树：

* 空树。
* **左右子树高度差绝对值不超过1**且**左右子树都是平衡二叉树**。

![&#x56FE;&#x7247;&#x6765;&#x81EA;&#x7F51;&#x7EDC;](http://media.jiuzhang.com/markdown/images/3/13/36c0bebe-2685-11e8-b3fe-0242ac110002.jpg)

节点旁边的数字表示左右两子树高度差。\(a\)是AVL树，\(b\)不是，\(b\)中5节点不满足AVL树，故4节点，3节点都不再是AVL树。

#### AVL树的高度为 O\(logN\)

当AVL树有N个节点时，高度为O\(logN\)。为何？  
试想一棵满二叉树，每个节点左右子树高度相同，随着树高的增加，叶子容量指数暴增，故树高一定是O\(logN\)O\(logN\)。而相比于满二叉树，**AVL树仅放宽一个条件，允许左右两子树高度差1**，当树高足够大时，可以把1忽略。如图是高度为9的最小AVL树，若节点更少，树高绝不会超过8，也即为何AVL树高会被限制到O\(logN\)O\(logN\)，因为**树不可能太稀疏**。严格的数学证明复杂,略去。  


![&#x56FE;&#x7247;](http://media.jiuzhang.com/markdown/images/3/13/d4b9b288-268a-11e8-99b6-0242ac110002.jpg)

为何普通二叉树不是O\(logN\)？这里给出最坏的单枝树，若单枝扩展，则树高为O\(N\)  


![&#x56FE;&#x7247;](http://media.jiuzhang.com/markdown/images/3/13/23657f56-268c-11e8-b3fe-0242ac110002.jpg)

#### AVL树有什么用？

若[二叉搜索树](http://www.jiuzhang.com/tutorial/algorithm/399)是AVL树，则最大作用是保证查找的**最坏**时间复杂度为O\(logN\)。而且较浅的树对插入和删除等操作也更快。

### BST Tree

二叉搜索树（Binary Search Tree，又名排序二叉树，二叉查找树，通常简写为BST）定义如下：

**空树**或是**具有下列性质的二叉树**：

1. 若左子树不空，则左子树上所有节点值均小于或等于它的根节点值
2. 若右子树不空，则右子树上所有节点值均大于或等于它的根节点值
3. 左、右子树也为二叉搜索树

![&#x56FE;&#x7247;](http://media.jiuzhang.com/markdown/images/3/14/cdc97d0c-2723-11e8-9bba-0242ac110002.jpg)

#### BST 的特性

* 按照[中序遍历](http://www.jiuzhang.com/tutorial/algorithm/405#)（inorder traversal）打印各节点，会得到**由小到大**的顺序。
* 在BST中搜索某值的平均时间复杂度为O\(logN\)，其中N为节点个数。类似二分查找（binary search），将待寻值与节点值比较，若不相等，则**通过是小于还是大于，可断定该值只可能在左子树还是右子树，继续向该子树搜索**。故一次比较平均排除半棵树。

#### BST 的作用

* 通过中序遍历，可快速得到升序节点列表。
* 在BST中查找元素，只需要**平均**O\(logN\)的时间，这与有序数组（sorted array）一样。但BST**平均**log\(N\)即可实现元素的增加和删除，有序数组却需要O\(N\)。

BST是一种**重要**且**基本**的结构，其相关题目也十分经典，并延伸出很多算法。  
在BST之上，有许多高级且有趣的变种,以解决各式各样的问题，例如:

* 用于数据库或各语言标准库中索引的[红黑树](https://baike.baidu.com/item/%E7%BA%A2%E9%BB%91%E6%A0%91/2413209?fr=aladdin)
* 提升二叉树性能底线的[伸展树](https://baike.baidu.com/item/%E4%BC%B8%E5%B1%95%E6%A0%91/7003945?fr=aladdin)
* 优化红黑树的[AA树](https://baike.baidu.com/item/AA%E6%A0%91/9246960?fr=aladdin)
* 随机插入的[树堆](https://baike.baidu.com/item/Treap?fromtitle=%E6%A0%91%E5%A0%86&fromid=4478083)
* 机器学习kNN算法的高维快速搜索[k-d树](https://baike.baidu.com/item/kd-tree/2302515)

{% embed url="https://www.jianshu.com/p/a826ab614e4a" %}

#### 小结：

* 二叉搜索树：有大小关系，可以快速查找，但是不一定平衡
* 平衡二叉树：整体树的高度为logn，但是未不存在搜索特性
* 红黑树：平衡二叉搜索树，兼具了前面两者的特性

## 2. 树的补充知识

### BST 的增删查改

#### 1. 什么是二叉搜索树\(Binary Search Tree\)？

**二叉搜索树**可以是一棵空树或者是一棵满足下列条件的[二叉树](https://zh.wikipedia.org/wiki/%E4%BA%8C%E5%8F%89%E6%A0%91):

* 如果它的左子树不空，则左子树上所有节点值`均小于`它的根节点值。
* 如果它的右子树不空，则右子树上所有节点值`均大于`它的根节点值。
* 它的左右子树均为二叉搜索树\(BST\)。
* 严格定义下BST中是没有值相等的节点的\(No duplicate nodes\)。 根据上述特性，我们可以得到一个结论：BST**中序遍历**得到的序列是**升序**的。如下述BST的中序序列为：\[1,3,4,6,7,8,10,13,14\]

![BST](http://media.jiuzhang.com/markdown/images/3/14/64328624-2749-11e8-b863-0242ac110002.jpg)

#### BST基本操作——增删改查\(CRUD\)

对于树节点的定义如下：

```python
class TreeNode:
    def __init__(self, val):
        this.val = val
        this.left, this.right = None, None
```

**基本操作之查找\(Retrieve\)**

* 思路
  * 查找值为**val**的节点，如果val小于根节点则在左子树中查找，反之在右子树中查找
* 代码实现

```python
def searchBST(root, val) :
	# 未找到值为val的节点 
	if root == null :
		return null
	# val小于根节点值，在左子树中查找
	if val < root.val :
		return searchBST(root.left, val) 
	# val大于根节点值，在右子树中查找
	elif val > root.val :
		return searchBST(root.right, val) 
	# 找到了
	else :
		return root
```

* 实战
  * [11. Search Range in Binary Search Tree](https://www.lintcode.com/problem/search-range-in-binary-search-tree/description)
  * [689. Two Sum IV - Input is a BST](https://www.lintcode.com/problem/two-sum-iv-input-is-a-bst/description)
  * [900. Closest Binary Search Tree Value](https://www.lintcode.com/problem/closest-binary-search-tree-value/description)
  * [901. Closest Binary Search Tree Value II](https://www.lintcode.com/problem/closest-binary-search-tree-value-ii/description)
  * [701. Trim a Binary Search Tree](https://www.lintcode.com/problem/trim-a-binary-search-tree/description)
  * [691. Recover Binary Search Tree](https://www.lintcode.com/problem/recover-binary-search-tree/description)

**基本操作之修改\(Update\)**

* 思路
  * 修改仅仅需要在查找到需要修改的节点之后，更新这个节点的值就可以了
* 代码实现

```python
def updateBST(root, target, val) :
	# 未找到target节点
	if root == null :
		return
	# target小于根节点值，在左子树中查找
	if target < root.val :
		updateBST(root.left, target, val) 
	# target大于根节点值，在右子树中查找
	elif target > root.val :
		updateBST(root.right, target, val) 
	# 找到了
	else:
		root.val = val 
```

* 实战
  * [http://www.lintcode.com/en/problem/bst-swapped-nodes/](http://www.lintcode.com/en/problem/bst-swapped-nodes/)

**基本操作之增加\(Create\)**

* 思路
  * 根节点为空，则待添加的节点为根节点
  * 如果待添加的节点值小于根节点，则在左子树中添加
  * 如果待添加的节点值大于根节点，则在右子树中添加
  * 我们统一在树的叶子节点\(Leaf Node\)后添加
* 代码实现

```python
def insertNode(root, node) :
    if root == null :
        return node 
    
    if root.val > node.val :
        root.left = insertNode(root.left, node)
    else 
        root.right = insertNode(root.right, node)    
    return root
```

* 实战
  * [http://www.lintcode.com/en/problem/insert-node-in-a-binary-search-tree/](http://www.lintcode.com/en/problem/insert-node-in-a-binary-search-tree/)

**基本操作之删除\(Delete\)**

* 思路\(最为复杂\)
  * 考虑待删除的节点为叶子节点，可以直接删除并修改父亲节点\(Parent Node\)的指针，需要区分待删节点是否为根节点
  * 考虑待删除的节点为单支节点\(只有一棵子树——左子树 or 右子树\)，与删除链表节点操作类似，同样的需要区分待删节点是否为根节点
  * 考虑待删节点有两棵子树，可以将待删节点与左子树中的最大节点进行交换，由于左子树中的最大节点一定为叶子节点，所以这时再删除待删的节点可以参考第一条
  * 详细的解释可以看 [http://www.algolist.net/Data\_structures/Binary\_search\_tree/Removal](http://www.algolist.net/Data_structures/Binary_search_tree/Removal)
* 代码实现

```python
def removeNode(root, value) :
    dummy = TreeNode(0)
    dummy.left = root
    parent = findNode(dummy, root, value)
    if parent.left != null && parent.left.val == value :
        node = parent.left;
    elif parent.right != null && parent.right.val == value :
        node = parent.right;
    else : 
        return dummy.left
    
    deleteNode(parent, node)
    return dummy.left


def findNode(parent, node, value)  :
    if node == null :
        return parent
    
    if node.val == value :
        return parent
    
    if value < node.val :
        return findNode(node, node.left, value) 
    else :
        return findNode(node, node.right, value) 
    
def deleteNode(parent, node) :
    if node.right == null :
        if parent.left == node :
            parent.left = node.left
        else :
            parent.right = node.left
    else :
        temp = node.right
        father = node
        while temp.left != null :
            father = temp
            temp = temp.left
        
        if father.left == temp :
            father.left = temp.right
        else :
            father.right = temp.right
        
        if parent.left == node :
            parent.left = temp
        else :
            parent.right = temp :
        
        temp.left = node.left
        temp.right = node.right
```

* 实战
  * [http://www.lintcode.com/en/problem/remove-node-in-binary-search-tree/](http://www.lintcode.com/en/problem/remove-node-in-binary-search-tree/)
  * [http://www.lintcode.com/en/problem/trim-binary-search-tree/](http://www.lintcode.com/en/problem/trim-binary-search-tree/)

### 平衡排序二叉树

平衡二叉搜索树又被称为AVL树（有别于AVL算法），且具有以下性质：

* 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1
* 左右两棵子树都是一棵平衡二叉搜索树
* 平衡二叉搜索树必定是二叉搜索树，反之则不一定。

**平衡排序二叉树 与 二叉搜索树 对比**

也许因为输入值不够随机，也许因为输入顺序的原因，还或许一些插入、删除操作，会使得二叉搜索树失去平衡，造成搜索效率低落的情况。  
  
比如上面两个树，在平衡树上寻找15就只要2次查找，在非平衡树上却要5次查找方能找到，效率明显下降。

![AVL](http://media.jiuzhang.com/markdown/images/3/15/7c52db9e-27ff-11e8-af91-0242ac110002.jpg)

**平衡排序二叉树节点定义**

```java
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    pubic TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
```

**常用的实现办法**

* AVL树 --&gt; 

{% embed url="https://en.wikipedia.org/wiki/AVL\_tree" %}

* 红黑树\(Red Black Tree\) --&gt; 

{% embed url="http://blog.csdn.net/v\_july\_v/article/details/6105630" %}

#### [TreeSet](https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html) / [TreeMap](https://docs.oracle.com/javase/7/docs/api/java/util/TreeMap.html)

TreeSet / TreeMap 是底层运用了[红黑树](https://zh.wikipedia.org/wiki/%E7%BA%A2%E9%BB%91%E6%A0%91)的数据结构

**对比 HashSet / HashMap**

* HashSet / HashMap 存取的时间复杂度为**O\(1\)，**而 TreeSet / TreeMap 存取的时间复杂度为 **O\(logn\)** 所以在存取上并不占优。
* HashSet / HashMap 内元素是无序的，而TreeSet / TreeMap 内部是有序的\(可以是按自然顺序排列也可以自定义排序\)。
* TreeSet / TreeMap 还提供了类似 [lowerBound](http://www.cplusplus.com/reference/algorithm/lower_bound/) 和 [upperBound](http://www.cplusplus.com/reference/algorithm/upper_bound/) 这两个其他数据结构没有的方法
  * 对于 TreeSet, 实现上述两个方法的方法为：
    * **lowerBound**
      * **public E lower\(E e\)** --&gt; 返回set中**严格小于**给出元素的**最大元素**，如果没有满足条件的元素则返回 **null**。
      * **public E floor\(E e\)** --&gt; 返回set中**不大于**给出元素的**最大元素**，如果没有满足条件的元素则返回 **null**。
    * **upperBound**
      * **public E higher\(E e\)** --&gt; 返回set中**严格大于**给出元素的**最小元素**，如果没有满足条件的元素则返回 **null**。
      * **public E ceiling\(E e\)** --&gt; 返回set中**不小于**给出元素的**最小元素**，如果没有满足条件的元素则返回 **null**。
  * 对于 TreeMap, 实现上述两个方法的方法为：
    * **lowerBound**
      * **public Map.Entry&lt;K,V&gt; lowerEntry\(K key\)** --&gt; 返回map中**严格小于**给出的key值的**最大key**对应的**key-value对**，如果没有满足条件的key则返回 **null**。
      * **public K lowerKey\(K key\)** --&gt; 返回map中**严格小于**给出的key值的**最大key**，如果没有满足条件的key则返回 **null**。
      * **public Map.Entry&lt;K,V&gt; floorEntry\(K key\)** --&gt; 返回map中**不大于**给出的key值的**最大key**对应的**key-value对**，如果没有满足条件的key则返回 **null**。
      * **public K floorKey\(K key\)** --&gt; 返回map中**不大于**给出的key值的**最大key**，如果没有满足条件的key则返回 **null**。
    * **upperBound**
      * **public Map.Entry&lt;K,V&gt; higherEntry\(K key\)** --&gt; 返回map中**严格大于**给出的key值的**最小key**对应的**key-value对**，如果没有满足条件的key则返回 **null**。
      * **public K higherKey\(K key\)** --&gt; 返回map中**严格大于**给出的key值的**最小key**，如果没有满足条件的key则返回 **null**。
      * **public Map.Entry&lt;K,V&gt; ceilingEntry\(K key\)** --&gt; 返回map中**不小于**给出的key值的**最小key**对应的**key-value对**，如果没有满足条件的key则返回 **null**。
      * **public K ceilingKey\(K key\)** --&gt; 返回map中**不小于**给出的key值的**最小key**，如果没有满足条件的key则返回 **null**。
  * lowerBound 与 upperBound 均为二分查找\(因此要求有序\)，时间复杂度为**O\(logn\)**.

#### **对比 PriorityQueue\(Heap\)**

**PriorityQueue**是基于Heap实现的，它可以保证队头元素是优先级最高的元素，但其余元素是不保证有序的。

* 方法时间复杂度对比：
  * 添加元素 add\(\) / offer\(\)
    * TreeSet: O\(logn\)
    * PriorityQueue: O\(logn\)
  * 删除元素 poll\(\) / remove\(\)
    * TreeSet: O\(logn\)
    * PriorityQueue: O\(logn\)
  * 查找 contains\(\)
    * TreeSet: O\(logn\)
    * PriorityQueue: O\(n\)
  * 取最小值 first\(\) / peek\(\)
    * TreeSet: O\(logn\)
    * PriorityQueue: O\(1\)

**常见用法**

比如滑动窗口需要保证有序，那么这时可以用到TreeSet,因为TreeSet是有序的，并且不需要每次移动窗口都重新排序，只需要插入和删除\(O\(logn\)\)就可以了。

注：在 C++ 中类似的结构为 set / map

### AVL实现

这一部分待补充。。

#### [106. Convert Sorted List to Binary Search Tree](https://www.lintcode.com/problem/convert-sorted-list-to-binary-search-tree/description)

可以十分容易想到一个一个 O\(nlogn\) 的分治算法，以链表作为参数，二叉树作为返回值：

```java
TreeNode convert(ListNode head) {
    if (head == null) {
        return null;
    }

    // find the following three nodes in the linked list
    // .... prev -> mid -> next ...
    // prev.next = null; // break the connect between prev & mid
    
    TreeNode root = new TreeNode(mid.val);
    root.left = convertListBBT(head);
    root.right = convertListBBT(next);
    return root;
}
```

算法的大致思路就是，找到链表中点和他前后的点，然后左边的部分递归生成一棵左子树，右边的部分递归生成一棵右子树，再和中间的点拼接起来就好了。

这个算法我们不难发现他的时间复杂度是 O\(nlogn\)O\(nlogn\) 的，因为找到中点的时间复杂度是 O\(n\)O\(n\)，因此可以用 T 函数推算法来进行推算：

```text
T(n) = 2 * T(n/2) + O(n) = O(nlogn)
```

#### 优化的算法

为了优化这个算法，我们给分治函数带上了一个参数 n 代表目前打算去转换 head 开始，长度为 n 那么多个节点，让其变为 Balanced Binary Tree。递归函数接口如下：

```text
TreeNode convert(ListNode head, int n)
```

这样，我们不用真正把链表从 prev 和 mid 之间断开。可以利用对第二个参数的大小控制来让处理规模缩小。

但是虽然我们可以很快的调用 `convert(head, n / 2)`，让链表的一半变成二叉树。但是如何很快知道链表的中点呢？这里的办法是，如果我们把 head 放在参数里，那么就无法利用 convert 函数对 head 进行挪动了，所以我们把 head 挪出来，放到全局，作为一个全局变量。这样之后函数的接口改为：

```java
public class Solution {
    private ListNode current;

    private TreeNode convert(int n) {
        // ...
    }

    // the entry point to public
    public TreeNode sortedListToBST(ListNode head) {
        current = head;
        convert(getLength(head));
    }
```

这里我们在全局放了一个 current 指针，这个指针会指向当前还没有被变成 Tree 的下一个 List 上的节点。因此如果我们把左子树变成 Tree 以后，current 就要让他指向 List 上的下一个点，也就是中间的这个点了。

**完整算法描述如下：**

1. 首先求得整个list的长度 O\(n\)
2. 利用 helper 函数进行递归，helper\(head, len\) 表示把从 head 开始的，长度为len的链表，转换为一个bst并且return。与此同时，把global variable的指针挪到head开始的第 len + 1个listnode上。

那么 convert\(head, len\) 就可以分为，三个步骤：

1. 把head开头的长度为 len/2的先变成bst，也就是我们的左子树，convert\(head, len / 2\)。这个时候他顺便会把global variable 挪到第len / 2 + 1的那个node，这个就是我们的root。
2. 然后得到了root之后，把global variable 往下挪一个挪到 第 len/2 + 2个点，也就是右子树开头的那个点，然后调用 convert\(global variable, len - len/2 -1\)，构造出右子树。
3. 然后把root，左子树，右子树，拼接在一起，return

这个题算法框架就是这样，如果不是很明白的话，建议模拟一个小数据，比如 5个节点的情况。模拟几个数据结合算法的思路来分析，就应该可以明白。这个题的这种解法背下来就好了。

