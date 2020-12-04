# LeetFlag

## Total Count
* #1715 Jan 9, 2021
## Using vscode-leetcode
* https://github.com/LeetCode-OpenSource/vscode-leetcode
* logined via 3rd party but copy whole cookie from chrome inspect on /all header.

---

## 1 Hack the Algorithm Interview
* 627 Longest Palindrome
* 415 Valid Palindrome
* 13 Implement strStr()
* 200 Longest Palindromic Substring
* leet 5 longest palindrome substring (DP)
    * 要灵活运用 DP，是作为工具而不是目的。这里return string。而区间型DP应该存的是 可以使用 的子结构。所以存 boolean iaPalindrome.
    * 然后用打擂台算法，结合DP matrix 更新想要的结果。

* leet 516 longest palindrome subsequence (DP)
    * 这题要的 length。所以DP的state即 longest length即可。
    * 中间点向两边扩的方法也很妙。也是常用的2 pointer。
    * 这里老师的comment 计算 loop index的边界，很好。


## 2 Binary Search & LogN Algorithm
* 457 Classical Binary Search
* 14 First Position of Target
* 28 Search a 2D Matrix
* 38 Search a 2D Matrix II
* 61 Search for a Range
* 462 Total Occurrence of Target
* 600 Smallest Rectangle Enclosing Black Pixels
* 437 Copy Books
* 458 Last Position of Target
* 585 Maximum Number in Mountain Sequence
* 460 Find K Closest Elements
    * 利用459，找到last element <= target， 这样last + 1 > target (如果在边界内的话)
    * 这题体现出子函数的优越性: 容易 debug
    * 把 OOXX binary search 和 findLowerClosest 分出来写。
    * 而且clean code自动就 cover 了边界情况了。果然if/else多就说明思路不清楚。

* 459 Closest Number in Sorted Array
    * 第二境界 OOXX，找first或last

* 447 Search in a Big Sorted Array
* 428 Pow(x, n)
* 159 Find Minimum in Rotated Sorted Array
* 140 Fast Power
* 75 Find Peak Element
* 74 First Bad Version
* 62 Search in Rotated Sorted Array


## 3 Two Pointers Algorithm
* 604 Window Sum
* 380 Intersection of Two Linked Lists
* 102 Linked List Cycle
* 103 Linked List Cycle II
* 380 Intersection of Two Linked Lists
* 415 Valid Palindrome
* 891 Valid Palindrome II
* 587 Two Sum - Unique pairs
* 382 Triangle Count
* 609 Two Sum - Less than or equal to target
* 443 Two Sum - Greater than target
* 533 Two Sum - Closest to target
* 59 3Sum Closest
* 58 4Sum
* 610 Two Sum - Difference equals to target
* 461 Kth Smallest Numbers in Unsorted Array
* 373 Partition Array by Odd and Even
* 144 Interleaving Positive and Negative Numbers
* 49 Sort Letters by Case
* 148 Sort Colors
* 894 Pancake Sorting
* 228 Middle of Linked List
* 607 Two Sum III - Data structure design
* 539 Move Zeroes
* 521 Remove Duplicate Numbers in Array
* 464 Sort Integers II
* 608 Two Sum II - Input array is sorted
* 143 Sort Colors II
* 57 3Sum
* 31 Partition Array
* 5 Kth Largest Element

## 4 BFS & Topological Sort
* 69 Binary Tree Level Order Traversal
* 70 Binary Tree Level Order Traversal II
* 71 Binary Tree Zigzag Level Order Traversal
* 242 Convert Binary Tree to Linked Lists by Depth
* 433 Number of Islands
* 892 Alien Dictionary
* 178 Graph Valid Tree
* 618 Search Graph Nodes
* 431 Connected Component in Undirected Graph
* 598 Zombie in Matrix
* 573 Build Post Office II
* 433 Number of Islands
* 69 Binary Tree Level Order Traversal
* 615 Course Schedule
* 616 Course Schedule II
* 611 Knight Shortest Path
* 605 Sequence Reconstruction
* 137 Clone Graph
* 127 Topological Sorting
* 120 Word Ladder
* 7 Serialize and Deserialize Binary Tree

## 5 Binary Tree & Tree-based DFS
* 597 Subtree with Maximum Average
* 175 Invert Binary Tree
* 95 Validate Binary Search Tree
* 596 Minimum Subtree
* 88 Lowest Common Ancestor of a Binary Tree
* 86 Binary Search Tree Iterator
* 448 Inorder Successor in BST
* 67 Binary Tree Inorder Traversal
* 11 Search Range in Binary Search Tree
* 85 Insert Node in a Binary Search Tree
* 87 Remove Node in Binary Search Tree
* 900 Closest Binary Search Tree Value
* 596 Minimum Subtree
* 480 Binary Tree Paths
* 453 Flatten Binary Tree to Linked List
* 93 Balanced Binary Tree
* 902 Kth Smallest Element in a BST
* 578 Lowest Common Ancestor III
* 95 Validate Binary Search Tree
* 901  Closest Binary Search Tree Value II
* 86 Binary Search Tree Iterator

## 6 Combination-based DFS
* 90 k Sum II
* 192 Wildcard Matching
* 154 Regular Expression Matching
* 582 Word Break II
* 680 Split String
* 570 Find the Missing Number II
* 136 Palindrome Partitioning
* 153 Combination Sum II
* 152 Combinations
* 135 Combination Sum
* 18 Subsets II
* 17 Subsets
* 780 Remove Invalid Parentheses
* 582 Word Break II

## 7 Permutation-based & Graph-based DFS
* 33 N-Queens
* 52 Next Permutation
* 190 Next Permutation II
* 197 Permutation Index
* 198 Permutation Index II
* 132 Word Search II
* 862 Next Closest Time
* 425 Letter Combinations of a Phone Number
* 10 String Permutation II
* 34 N-Queens II
* 33 N-Queens
* 16 Permutations II
* 15 Permutations
* 829 Word Pattern II
* 132 Word Search II
* 121 Word Ladder II

## 8 Data Structure - Stack, Queue, Hash, Heap
* 657 Insert Delete GetRandom O(1)
* 526 Load Balancer
* 954 Insert Delete GetRandom O(1) - Duplicates allowed
* 960 First Unique Number in a Stream II
* 138 Subarray Sum
* 105 Copy List with Random Pointer
* 171 Anagrams
* 124 Longest Consecutive Sequence
* 685 First Unique Number In Stream
* 545 Top k Largest Numbers II
* 228 Middle of Linked List
* 81 Find Median from Data Stream
* 613 High Five
* 486 Merge K Sorted Arrays
* 401 Kth Smallest Number in Sorted Matrix
* 642 Moving Average from Data Stream
* 494 Implement Stack by Two Queues
* 209 First Unique Character in a String
* 657 Insert Delete GetRandom O(1)
* 612 K Closest Points
* 544 Top k Largest Numbers
* 104 Merge K Sorted Lists
* 40 Implement Queue by Two Stacks
* 4 Ugly Number II
* 134 LRU Cache

## 9 Data Structure - Interval, Array, Matrix & Binary Indexed Tree
* 6 Merge Two Sorted Arrays
* 486 Merge K Sorted Arrays
* 548 Intersection of Two Arrays II
* 793 Intersection of Arrays
* 149 Best Time to Buy and Sell Stock
* 405 Submatrix Sum
* 943 Range Sum Query - Immutable
* 665 Range Sum Query 2D - Immutable
* 817 Range Sum Query 2D - Mutable
* 249 Count of Smaller Number before itself
* 839 Merge Two Sorted Interval Lists
* 547 Intersection of Two Arrays
* 138 Subarray Sum
* 64 Merge Sorted Array
* 41 Maximum Subarray
* 944 Maximum Submatrix
* 931 Median of K Sorted Arrays
* 840 Range Sum Query - Mutable
* 654 Sparse Matrix Multiplication
* 577 Merge K Sorted Interval Lists
* 486 Merge K Sorted Arrays
* 65 Median of two Sorted Arrays

## 10 Additional - Dynamic Programming
* 115 Unique Paths II
* 114 Unique Paths
* 111 Climbing Stairs
* 110 Minimum Path Sum
* 109 Triangle
* 603 Largest Divisible Subset
* 611 Knight Shortest Path
* 513 Perfect Squares
* 116 Jump Game
* 76 Longest Increasing Subsequence