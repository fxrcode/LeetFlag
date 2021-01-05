# LeetFlag

## Total Count
* #1715 Jan 9, 2021
## Using vscode-leetcode
* https://github.com/LeetCode-OpenSource/vscode-leetcode
* logined via 3rd party but copy whole cookie from chrome inspect on /all header.
* 有个问题就是没法同一题生成2个file，从而记录多种方法。
    * workaround就是在同一个file里写多个方法。

---

## xxx Segment Tree and Binary Indexed Tree
* [x] 206 Interval Sum
    * when it comes 2 range: query range & root range, 搞清楚什么变，什么不变，compare的哪个range的mid.

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
* [x] 5 Kth Largest Element
    * 之前在Basic里写过partition和quickselect，以为自己懂了，其实并不是...
    * 这次因为BST Kth largest element所以来做这题才发现一直是stack overflow。print来发现因为partition return的一直都是原本nums的index。所以并不需要像JeffE书里那样在 k>r 的时候 k-r。
        * JeffE之所以 k-r，是因为它passin的是subarray。所以index变化了。
    * 还有就是partition的pivot要换成random，否则LeetCode只有20%的成绩。改成random就变成97%了。
    * 可见还是要多写多总结，包括各种小的coding知识点: `index, off-by-1, etc.`
    * created my 1st leetcode post: https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/1023974/Linear-time-selection-with-Nico-Lomuto-Partition-as-in-JeffE's-Algorithms.wtf-book


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
* [x] 900 Closest Binary Search Tree Value
    * lower/upper bound, traverse the BST according to root.val vs target.
* [x] 596 Minimum Subtree
    * 经典的Tree-based DFS之分治法。看到Binary Tree基本上都是DFS。而分治法基本只会考Tree DFS或Merge Sort。
    * 相对于Python可以返回multi value。Java要写ResultType。
    * 相对于DFS 遍历法的global var，会导致class成为stateful，因而不好改multi-thread。所以最好写分治法。
    * 分治法可以把中间结果作为arg传入func，或作为return。注意Java is always call-by-value. 所以 func 里 List = [xxx] 只对func 内可见; 而 func里 List.add(xxx) 则内外皆可见。
* [] 480 Binary Tree Paths
* [x] 453 Flatten Binary Tree to Linked List
    * DFS 同样有 遍历法 vs 分治法。遍历法需要更多练习，因为以前没有写过。
    * 注意dynamic data struct的update。在restructure的时候，要在赋值新link前，用这个link。
    * 对于 遍历法，是按照pre-order的顺序 处理root -> restruct lastnode，并dfs(root.left) & dfs(root.right)
    * 对于 分治法，定义好dfs的返回值后，无脑call dfs(root.left) & dfs(root.right). 然后做restruct。注意这里做connect和return lastnode的处理。做完connect后，先判断有没有right_last, 再判断有没有left_last. 这样写可以少一次比较。
* [] 93 Balanced Binary Tree
* [] 902 Kth Smallest Element in a BST
* [] 578 Lowest Common Ancestor III
* [] 95 Validate Binary Search Tree
* [] 901  Closest Binary Search Tree Value II
* [x] 86 Binary Search Tree Iterator
    * BST 的题目必然考 Iterator，即non-recursion in-order traversal。要理解了才能记下来，跟其他算法、数据结构一样。
    * 记住stack的意义：保存一路走到当前节点的所有节点，top一定指向next。
    * 不仅适用于BST，任意Binary Tree都可以。
    * 像B站的北大大雪菜所说，他们训练的方式就是每天重复默写知识架构的DSA；做了的题目都会擦掉重写，重要的事情说3遍。

## 6 Combination-based DFS
* [] 90 k Sum II
* [x] 192 Wildcard Matching
    * 下半节课开始讲记忆化搜索，可以帮助从DFS -> DP.
    * 注意DFS中，如果pattern的char是*，match 1 or more的话，并不需要for t <- i+1...s.length-1 : res |= dfs(s, t, p, j).
        * 而只需要一个dfs。因为递归是在循环层数不确定时的一个更优雅的实现多重循环的方式。
        * 这个好好体会。
    * 相比较Python，只要一个 memo = { (i,j) }. Java 则需要2个 2D boolean array。一个是memo， 一个是visited。
        * 注意由于 i,j 最大会是length。所以在 dfs 一进入的时候，先判断edge case。然后才从memo返回值，如果visited的话。
    * 如果要从DFS->memoization search. 需要先是 divide & conquer的DFS，即有返回值的DFS。
        * 然后memoization search即是把dfs的return记忆起来即可。例如用map，或matrix存储。
        * 然后可以在dfs之前先看看map、matrix存不存在，若存在，直接返回即可，这就将时间复杂度瞬间变成DP的 O( matrix size )了。
    * 如果是纯DP的话，这是“匹配型DP”
* [] 154 Regular Expression Matching
* [] 582 Word Break II
    * 如果是纯DP做，can_cut(), 是“单序列型DP”.
* [] 680 Split String
* [] 570 Find the Missing Number II
* [] 136 Palindrome Partitioning
* [] 153 Combination Sum II
* [] 152 Combinations
* [] 135 Combination Sum
* [] 18 Subsets II
* [x] 17 Subsets
    * 预习入门题，有4种做法，我做了前2种：通用的DFS；组合类DFS专用DFS(一层一层的选择当前element是否加入结果中)
* [] 780 Remove Invalid Parentheses
* [] 582 Word Break II

## 7 Permutation-based & Graph-based DFS
* [x] 33 N-Queens
    * 果然如果没按照 声明 & 返回， add & remove. 就很容易漏掉。面试的时候不是bug-free就很容易挂了。
    * 先用了O(N)的isValid。
    * 后来改用了permutation DFS的模板，加了visited hashmap。注意map加速干什么？
        * 这里valid是根据col, sum, diff. 所以map就存这3个对应的已经visited的states。所以value是一个hashset。
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
* [x] 16 Permutations II
    * Given "aabb", return ["aabb", "abab", "baba", "bbaa", "abba", "baab"].
    * 经典的permutation DFS。需要用visited，以及去重（aka 选代表）。
        * 不会选出 `a1a3`. 因为sorted之后的nums，然后for loop。那么选代表的三个条件会保证。选择2个a的时候，必然是a1a2。只有选3个a的时候，才会a1a2a3.
        * 这里要学习这个小的选代表snippet。关键在于思想。这样coding skill上去了，即逻辑清晰了，其他算法题也就通了。面试的DSA加起来就几十个。但逻辑能力是融会贯通的。
* [] 15 Permutations
* [x] 829 Word Pattern II
    * 因为要求说bijection, 即一个pattern的char，一一对应一个substring。
    * 建立1个map，和一个used set。
        * map 负责建立 pattern -> substring 的 dict。
        * used set 记录这个 路径下来的 word 会否 break bijection。
    * loop找candidate，如果used，则应该continue，而不是直接return false！
    * 学习coding skill
        * loop 得到 str的有效prefix。
        * 用substring(startIdx).startWith(p2s), 而不是 substring(start,end).startWith(p2s). 因为前者有可能stringBoundaryException。
    * 比较了传substring和传s_idx的total runtime. 前者明显慢了太多，因为每次都是new string。
* [] 132 Word Search II
* [x] 121 Word Ladder II
    * 很喜爱这道题目，感觉学到了做事情的哲学：
        * 立下目标之后，要做的是一步步向达成目标的自己靠近，而不是离上一刻的自己更远！
        * 如果 dfs 的时候，只是离上一刻的自己更远，有可能会走入死胡同，同时也离目标更远。要从新回到走入死胡同的入口，去向下一个地方走（而如果还是离自己更远的地方去，可能又走入第 n+1 个死胡同，同样要退回到死胡同的入口）。
        * 人生跟面试，跟算法一样，如果LTE的话：生命无take two。浪费时间 => 就等于失败了。因为人/面试挂了。
    * 学习了recursion时，java call-by-value的意义和作用
        * 只是机械的知道dfs recursion 之前、之后要 collection.add vs collection.pop.
        * 实际上我在 return 之前，做 `Collections.reverse(path)`, 也同样会影响到path。如果不再reverse回来的话，在dfs return之后，回到stack的上一级时，path整个就是反的！
            * 之所以用例子 hit,cog跑的时候，只有第二个结果的最后一个值不对，是因为正巧一直回退到hot。
        * 所以这里reverse应该写 stateless 的一个static function。
    * Java int collection (list, set) ，只能用最老土的一个个加。因为 `Arrays.asList()` 返回的是AbstractList！是backed by the origin array. 是fixed size! 但是跟 `Set.of()` 之类的还有些不同。前者可以 not be structurally modified (不能加减，但能改变element)。而后者则是immutable。
    * 所以刘老师所说的 proficient in 1 language的原因就在这里，要深入到语言的实现，和熟练运用**所有**API/Lib.
    * 再说回这道题目。既然说shortest path，又是simple graph (edge =1), 所以可以用退化版的 **Dijkstra algs** 做即可：即BFS，找到dist(u).
        * 参见《Algorithms DPV》的BFS。
        * 由于它是无向图，所以跟binary tree（无环有向图）的BFS多了 visited set/map, 避免倒车！
    * 并不需要分层遍历：因为每个node的neighbor加入queue的时候，都会visited，且 `neighbor.dist = node.dist+1`
        * ~~由于要 分层遍历，从而记录start BFS 到该点的min dist。所以比最简单的 BFS 多了一个loop，循环 q.poll, 即拿出这一层的所有点。~~
    * 还在implement的时候改了好几次 `buildG()`. 实际上应该写之前就定好这个method干什么：input/output。
    * BFS 找 single point shortest distance to every node. DFS 则是找 ALL solutions (根据条件找到valid solution)
    * 还有个重点：有时候要正反思维。其实也很好想，因为dist应该记录离 目标（end) 的距离更近，所以每个node记录到end的距离。所以BFS要从end到start。这样每个点的dist则是到end的距离。
        * 1st do: BFS(`end => start`). To find shortest dist from start to every nodes.
            * so start has the min dist(start,end). and end.dist = 0
        * 2nd do: DFS(`start => end`). To get ALL paths.
            * using neig.dist = node.dist - 1 (get closer to end)
    * 在 lint、leet上面写的时候，完全没有autocomplete。所以必须对API熟悉；也不会红线表示syntax error，除非hit run。所以naming + subproblem很重要，多一个debug，就少了XXX分钟！
    * 老师的解法：这道题是implicit graph，所以完全没有必要建立Graph！
        * 怎么学算法：每个算法的目的，input、output，然后是复杂度。
        * 看《Algorithms DPV》里BFS就是 `input: G(E,V), output: distMap, distMap[u] = min_dist_from[s]`.
            * 而如果有distance map的时候，就不需要多个loop去做level traversal了。
    * BFS, DFS写method的时候就要写出来method comment：搞清楚目的，input, output.
        * 例如DFS，里面 path 是在recursion call之前 add(node), 之后remove。所以当 dfs(end) 的时候，path已经有end了。可以add 到results了。所以退出recursion的条件就是 `cur.equals(end)`
            * 搞清楚这个，也才能bug-free的写出来 dfs之前，要把 path初始化为 `[start]`.
            * 注意这里 string比较值的话，必须用 `a.equals(end)`. 如果用 == 就错了， 因为 cur 从 neighs里拿。而这个是我用 helper function 的动态生成的 `set<String>`. 是新的String object。
    * 今天看了Dijkstra algorithm的Algs4和JeffE。都有 `pred[]`, `pred[v]` 表示到 v 的shortest path的predecessor。而且JeffE也谢了BFS跟DFS都可以加上 `pred[]`. 所以 都可以找到SSSP，并且列出这个path。 但是这word ladder II要求的是 ALL shortest paths。所以只能用DFS 去得到所有paths了。

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
* [x] 209 First Unique Character in a String
    * 因为不能用额外空间，map，或者26位数组/bitset. 那只能用最笨的方法：每一个字母都在整个string里搜一遍。
* [x] 657 Insert Delete GetRandom O(1)
    * 为了 O(1) for remove. 我先swap last和val，然后再update map和remove val。
    * 又学习了 `Collections.swap(collection, idx1, idx2)`.
* [] 612 K Closest Points
* [] 544 Top k Largest Numbers
* [] 104 Merge K Sorted Lists
* [] 40 Implement Queue by Two Stacks
* [] 4 Ugly Number II
* [] 134 LRU Cache

## 9 Data Structure - Interval, Array, Matrix & Binary Indexed Tree
* [x] 6 **Merge Two Sorted Arrays**
    * 模板题
    * 虽然是简单题，但是这种while，List<Integer>转int[], 以及print int[]. 这些coding细节还是要刻入到muscle memory。
    * 像《Certified Kubernetes Application Developer (CKAD) Study Guide》所说："The exam is very time constrained on purpose. It’s designed to put you under pressure to ensure that your knowledge of Kubernetes has been deeply ingrained into muscle memory."
* [x] 165 **Merge Two Sorted Lists**
    * 模板题： 2 pointers
    * 虽然是简单题，但是我惯性地跟Arrays一样做。所以while 2个都不null之后，我还继续while。所以只beat 1.2%!. 看了leaderboard才发现是只要把dummy.next = 不空的那个node即可。因为list是连着的啊。
    * 很久不做list。都不记得什么时候要用dummy node了。只有不确定边界的output才要用。input既然给了。就不需要dummy node了。
* [x] 547 Intersection of Two Arrays
    * 多种解法
    * 解法1：把2个arrays放入2个set，返回intersect。时间 O(n+m)，空间 O(n+m)
    * 解法2：用 Merge two sorted arrays 的 模板。现在是 merge的条件是2个元素相同。
        * 对于duplicate，需要避免，所以用permutation模板的方法。
        * 时间 O(nlogn + mlogm + n + m). 前2个是sort的时间，m+n是模板的扫描时间。空间则是O(1)。因为input、output的空间在external。
    * 解法3：对input排序，然后排序数组里搜索，当然是用二分法。
        * 时间 O(nlogn + mlogm + mlogn), 前2个是sort的时间，mlogn则是做m次 二分搜索。
* [x] 548 Intersection of Two Arrays II
    * 解法1：还是用 merge 2 sorted arrays的模板。而且比I的更简单些，不用比较duplicate。
    * 解法2：用HashMap。
* [x] 793 Intersection of Arrays
    * 解法1：直接把所有arrays合并到1个大list，先sort，然后再类似run-length-encoding去找到每个数的occurrence，同时跟size比较。因为题目说了no duplicate in each array。
        * 注意处理 [1,1,1] 的边界情况。
        * 还有用count == 0 来判断是 all[0] element。因为lastNum不太好初始化，例如我设为0，结果array里也有0。于是错在78%的testcase。
        * 时间 O(NlogN)
    * 解法2：用map统计。
        * 时间 O(N)。
    * 解法3：还是用heap。不过这里又掌握了Pair class作为每个array的pointer来顺序遍历。
        * 注意这里有多个变量，所以要搞清楚pq poll之后，什么时候，更新哪些var。这些coding 细节只有多写才能熟练。
        * 否则面试没法 bug-free
* [x] 295 Intersections
    * 发现简单题才是最重要的，因为体现了medium、hard题的基本思想。如果没有掌握所有easy的体型，则可能当场挂掉了。因为还是有需要画图分析讨论的一些结论。
    * 判断2个区间是否相交，可以看做 `反(不想交)`。这个容易理解。
    * 例如本题的特点：排好序的区间序列，且序列内每个区间**两两互不相交**。

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
* [x] 41 Maximum Subarray
    * 一个simple的题目，原来可以有 brute-force，greedy，分治法，DP，以及linear scanning。
    * 而且linear scanning的思想其实非常常见：maxSoFar & maxEndingHere. 这在DP也用到了。
    * 是subarray的基础题型。要烂熟。
* [] 944 Maximum Submatrix
* [] 931 Median of K Sorted Arrays
* [x] 840 Range Sum Query - Mutable
    * 第一次写BIT。挺多细节，不要搞错 A vs BIT 的index。
* [] 654 Sparse Matrix Multiplication
* [] 577 Merge K Sorted Interval Lists
* [] 486 Merge K Sorted Arrays
    * 用 heap (PriorityQueue) 也挺有讲究，program to interface。所以是 `Queue<Node> pq = PriorityQueue<>();`. 因为这样符合OOP的8大原则。如果pq后面换成其他implementation，下面用到pq的地方就不用改，并regression test了。特别是OOP的目标是为了方便多人协作完成项目，open to extension, closed for modification. 即好的OOD是 robust 的 interface。
        * 当然也要适可而止，总不能走极端的 `Object pq` 吧。
        * 但是**TLE**了，fail在92%。
    * 按照 Intersection of Arrays 的九章解法：用Pair就不用存value了。

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