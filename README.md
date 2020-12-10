# LeetFlag

## Total Count
* #1715 Jan 9, 2021
## Using vscode-leetcode
* https://github.com/LeetCode-OpenSource/vscode-leetcode
* logined via 3rd party but copy whole cookie from chrome inspect on /all header.
* 有个问题就是没法同一题生成2个file，从而记录多种方法。
    * workaround就是在同一个file里写多个方法。

---

## 1 Hack the Algorithm Interview
* [] 627 Longest Palindrome
* [] 415 Valid Palindrome
* [] 13 Implement strStr()
* [] 200 Longest Palindromic Substring
* [x] leet 5 longest palindrome substring (DP)
    * 要灵活运用 DP，是作为工具而不是目的。这里return string。而区间型DP应该存的是 可以使用 的子结构。所以存 boolean iaPalindrome.
    * 然后用打擂台算法，结合DP matrix 更新想要的结果。

* [x] leet 516 longest palindrome subsequence (DP)
    * 这题要的 length。所以DP的state即 longest length即可。
    * 中间点向两边扩的方法也很妙。也是常用的2 pointer。
    * 这里老师的comment 计算 loop index的边界，很好。


## 2 Binary Search & LogN Algorithm
* [] 457 Classical Binary Search
* [] 14 First Position of Target
* [] 28 Search a 2D Matrix
* [] 38 Search a 2D Matrix II
* [] 61 Search for a Range
* [] 462 Total Occurrence of Target
* [] 600 Smallest Rectangle Enclosing Black Pixels
* [] 437 Copy Books
* [] 458 Last Position of Target
* [] 585 Maximum Number in Mountain Sequence
* [x] 460 Find K Closest Elements
    * 利用459，找到last element <= target， 这样last + 1 > target (如果在边界内的话)
    * 这题体现出子函数的优越性: 容易 debug
    * 把 OOXX binary search 和 findLowerClosest 分出来写。
    * 而且clean code自动就 cover 了边界情况了。果然if/else多就说明思路不清楚。
* [x] Leet 35. Search Insert Position
    * find first position >= target.
    * 注意：对于target > array的max，则是 `end+1`
    * 其实Java自带：`Collections.binarySearch(arr, target)`.
* [x] Leet 658 Find K Closest Elements
    * 和lint的区别在于返回的值。
        * Lint的返回是按照abs dist的顺序。
        * Leet则是返回 sorted closest Elements。
    * 还是双指针，也是要确定边界。不过这里怎么处理比较clean?
        * 其实可以跟Lintcode一样写法。
        * 不过coding基础要掌握好：fori k次，每次 `l--, r++`, 结果应该取 `[l+1,r-1]` ! 因为退出的时候i = k!

* [x] 459 Closest Number in Sorted Array
    * 第二境界 OOXX，找first或last

* [] 447 Search in a Big Sorted Array
* [] 428 Pow(x, n)
* [x] 159 Find Minimum in Rotated Sorted Array
    * 只要画图分析2种情况的RSA，就能发现：只需要比较 `A[mid]`和 `A[end]`.
* [] 140 Fast Power
* [] 75 Find Peak Element
* [] 74 First Bad Version
* [] 62 Search in Rotated Sorted Array


## 3 Two Pointers Algorithm
* [] 604 Window Sum
* [] 380 Intersection of Two Linked Lists
* [] 102 Linked List Cycle
* [] 103 Linked List Cycle II
* [] 380 Intersection of Two Linked Lists
* [] 415 Valid Palindrome
* [] 891 Valid Palindrome II
* [] 587 Two Sum - Unique pairs
* [] 382 Triangle Count
* [] 609 Two Sum - Less than or equal to target
* [] 443 Two Sum - Greater than target
* [] 533 Two Sum - Closest to target
* [] 59 3Sum Closest
* [] 58 4Sum
* [] 610 Two Sum - Difference equals to target
* [] 461 Kth Smallest Numbers in Unsorted Array
* [] 373 Partition Array by Odd and Even
* [] 144 Interleaving Positive and Negative Numbers
* [] 49 Sort Letters by Case
* [] 148 Sort Colors
* [] 894 Pancake Sorting
* [] 228 Middle of Linked List
* [] 607 Two Sum III - Data structure design
* [] 539 Move Zeroes
* [] 521 Remove Duplicate Numbers in Array
* [] 464 Sort Integers II
* [] 608 Two Sum II - Input array is sorted
* [] 143 Sort Colors II
* [] 57 3Sum
* [] 31 Partition Array
* [] 5 Kth Largest Element

## 4 BFS & Topological Sort
* [] 69 Binary Tree Level Order Traversal
* [] 70 Binary Tree Level Order Traversal II
* [] 71 Binary Tree Zigzag Level Order Traversal
* [] 242 Convert Binary Tree to Linked Lists by Depth
* [] 433 Number of Islands
* [] 892 Alien Dictionary
* [] 178 Graph Valid Tree
* [] 618 Search Graph Nodes
* [] 431 Connected Component in Undirected Graph
* [] 598 Zombie in Matrix
* [] 573 Build Post Office II
* [] 433 Number of Islands
* [] 69 Binary Tree Level Order Traversal
* [] 615 Course Schedule
* [] 616 Course Schedule II
* [] 611 Knight Shortest Path
* [] 605 Sequence Reconstruction
* [] 137 Clone Graph
* [] 127 Topological Sorting
* [] 120 Word Ladder
* [] 7 Serialize and Deserialize Binary Tree

## 5 Binary Tree & Tree-based DFS
* [] 597 Subtree with Maximum Average
* [] 175 Invert Binary Tree
* [] 95 Validate Binary Search Tree
* [] 88 Lowest Common Ancestor of a Binary Tree
    * 名字有简写的题目都是经典算法，是必许瞬间写出来的。
    * 这题要认真考虑dfs的返回是什么，从而 dfs(父节点) 里面可以利用 dfs(son) 的state。
        * dfs返回的并不只是 lca，还会返回 either p or q in tree rooted by 父节点; 也会返回 neither p nor q in tree rooted by 父节点。
        * 这样对于父节点。才可以把3种情况都cover，并且有可以用的值。
* [] 86 Binary Search Tree Iterator
* [] 448 Inorder Successor in BST
* [] 67 Binary Tree Inorder Traversal
* [] 11 Search Range in Binary Search Tree
* [] 85 Insert Node in a Binary Search Tree
* [] 87 Remove Node in Binary Search Tree
* [] 900 Closest Binary Search Tree Value
* [x] 596 Minimum Subtree
    * 经典的Tree-based DFS之分治法。看到Binary Tree基本上都是DFS。而分治法基本只会考Tree DFS或Merge Sort。
    * 相对于Python可以返回multi value。Java要写ResultType。
    * 相对于DFS 遍历法的global var，会导致class成为stateful，因而不好改multi-thread。所以最好写分治法。
    * 分治法可以把中间结果作为arg传入func，或作为return。注意Java is always call-by-value. 所以 func 里 List = [xxx] 只对func 内可见; 而 func里 List.add(xxx) 则内外皆可见。
* [] 480 Binary Tree Paths
* [x] 453 Flatten Binary Tree to Linked List
    * DFS 同样有 遍历法 vs 分治法。遍历法需要更多练习，因为以前没有写过。
    * 注意dynamic data struct的update。在restructure的时候，要在赋值新link前，用这个link。
* [] 93 Balanced Binary Tree
* [] 902 Kth Smallest Element in a BST
* [] 578 Lowest Common Ancestor III
* [] 95 Validate Binary Search Tree
* [] 901  Closest Binary Search Tree Value II
* [] 86 Binary Search Tree Iterator

## 6 Combination-based DFS
* [] 90 k Sum II
* [] 192 Wildcard Matching
* [] 154 Regular Expression Matching
* [] 582 Word Break II
* [] 680 Split String
* [] 570 Find the Missing Number II
* [] 136 Palindrome Partitioning
* [] 153 Combination Sum II
* [] 152 Combinations
* [] 135 Combination Sum
* [] 18 Subsets II
* [] 17 Subsets
* [] 780 Remove Invalid Parentheses
* [] 582 Word Break II

## 7 Permutation-based & Graph-based DFS
* [] 33 N-Queens
* [] 52 Next Permutation
* [] 190 Next Permutation II
* [] 197 Permutation Index
* [] 198 Permutation Index II
* [] 132 Word Search II
* [] 862 Next Closest Time
* [] 425 Letter Combinations of a Phone Number
* [] 10 String Permutation II
* [] 34 N-Queens II
* [] 33 N-Queens
* [] 16 Permutations II
* [] 15 Permutations
* [] 829 Word Pattern II
* [] 132 Word Search II
* [] 121 Word Ladder II

## 8 Data Structure - Stack, Queue, Hash, Heap
* [] 657 Insert Delete GetRandom O(1)
* [] 526 Load Balancer
* [] 954 Insert Delete GetRandom O(1) - Duplicates allowed
* [] 960 First Unique Number in a Stream II
* [] 138 Subarray Sum
* [] 105 Copy List with Random Pointer
* [] 171 Anagrams
* [] 124 Longest Consecutive Sequence
* [] 685 First Unique Number In Stream
* [] 545 Top k Largest Numbers II
* [] 228 Middle of Linked List
* [] 81 Find Median from Data Stream
* [] 613 High Five
* [] 486 Merge K Sorted Arrays
* [] 401 Kth Smallest Number in Sorted Matrix
* [] 642 Moving Average from Data Stream
* [] 494 Implement Stack by Two Queues
* [] 209 First Unique Character in a String
* [] 657 Insert Delete GetRandom O(1)
* [] 612 K Closest Points
* [] 544 Top k Largest Numbers
* [] 104 Merge K Sorted Lists
* [] 40 Implement Queue by Two Stacks
* [] 4 Ugly Number II
* [] 134 LRU Cache

## 9 Data Structure - Interval, Array, Matrix & Binary Indexed Tree
* [] 6 Merge Two Sorted Arrays
* [] 486 Merge K Sorted Arrays
* [] 548 Intersection of Two Arrays II
* [] 793 Intersection of Arrays
* [] 149 Best Time to Buy and Sell Stock
* [] 405 Submatrix Sum
* [] 943 Range Sum Query - Immutable
* [] 665 Range Sum Query 2D - Immutable
* [] 817 Range Sum Query 2D - Mutable
* [] 249 Count of Smaller Number before itself
* [] 839 Merge Two Sorted Interval Lists
* [] 547 Intersection of Two Arrays
* [] 138 Subarray Sum
* [] 64 Merge Sorted Array
* [] 41 Maximum Subarray
* [] 944 Maximum Submatrix
* [] 931 Median of K Sorted Arrays
* [] 840 Range Sum Query - Mutable
* [] 654 Sparse Matrix Multiplication
* [] 577 Merge K Sorted Interval Lists
* [] 486 Merge K Sorted Arrays
* [] 65 Median of two Sorted Arrays

## 10 Additional - Dynamic Programming
* [] 115 Unique Paths II
* [] 114 Unique Paths
* [] 111 Climbing Stairs
* [] 110 Minimum Path Sum
* [] 109 Triangle
* [] 603 Largest Divisible Subset
* [] 611 Knight Shortest Path
* [] 513 Perfect Squares
* [] 116 Jump Game
* [] 76 Longest Increasing Subsequence