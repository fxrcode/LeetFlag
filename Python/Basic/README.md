# Algorithms
* forked from: https://github.com/hanrick2000/DatabaseNote/tree/master/computer-science/data-structure-and-algorithms

这里我的主要目的是强化算法应对面试，因而所有的题解和思路都是基于面试和套路的，主要是快速熟悉算法和解决问题，非常面向工程，而非系统地学习算法，具有非常强的功利性，主要是因为时间紧迫。

**方法论**：一套模板为基础来解决所有相似的问题，并通过不断地重复以实现不断对自己的模板进行深入的理解，从而形成自己的风格并不断提高。

#### 独孤**九剑**

* **1.总决式**：最容易出卖你的，就是你的Coding Style工程师的代码长什么比脸长什么样重要
* **2.破剑式**：比O\(n\)更优的时间复杂度几乎只能是O\(logn\)的二分法
* **3.破掌式**：对于求两个变量如何组合的问题，可以循环其中一个变量，然后研究另一个变量如何变化
* **4.破枪式**：能够用BFS解决的问题，一定不要用DFS去做，因为recursion可能stack-overflow
* **5.破枪式**：碰到二叉树的问题，就想想整棵树在该问题上的结果和左右儿子在该问题上的结果直接的联系
* **6.破鞭式**：碰到让你找所有方案的题，基本可以确定是DFS，除了二叉树以外90%DFS的题，那么是排列要么是组合
* **7. 破箭式**： 哈希表的任何时间复杂度从严格意义上都是O\(Keysize\)，而不是O\(1\)
* **8. 破索式：** 链表结构发生变化时，就需要 Dummy Node
* **9. 破气式：** 初始化一个二维的动态规划时，就去初始化第0行和第0列

#### Update Nov 12, 2018/ Dec 11, 2018

* 由于对原有题解和过程有了新的理解，对原有的题进行了整理和重新划分
* 不仅仅局限于Lintcode，而加入了Leetcode，但是还是尊重九章算法的劳动，将Lintcode列在前面
* 目前更新到BFS
  * String 类型的回文串因为比较复杂，目前还没有和其他内容合并
    * 几个重要的算法没有实现，比如Rabin Karp，Manager's Algorithm，还有就是有些题没有实际做一遍，动手很重要
  * 二分查找和二分答案进行了合并
    * 二分查找是比较扎实的，这里可以适当略过
  * 双指针排序
    * Merge Sort, Quick Sort等需要可以熟练写出来，现在有些遗忘
    * 彩虹排序、烙饼排序的内容还没有写过
    * 这里有些不够扎实了
  * BFS的word ladder 在leetcode存在问题，还未debug，bfs这里的course schedule之类的题还没有加入
  * 二叉树整体的遍历有些忘记
  * dfs不是很扎实，需要重新看一遍

## Bookmark

Dec 11/ 2018 - 看到Algorithm Pre - Binary Tree的增查删改

