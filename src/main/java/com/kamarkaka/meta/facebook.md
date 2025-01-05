Coding
LC1249. Minimum Remove to Make Valid Parentheses
    - use stack to keep track of parentheses in pairs
    - push '(' into stack, pop '(' when ')' is read; if stack is empty, delete ')'
    - stack is supposed to be empty when finishing reading the string, if not, delete all '(' stored in stack
LC0408. Valid Word Abbreviation
    - two pointers, one for word and one for abbr
    - both starts at 0
    - move both if two pointers pointing to the same char
    - if abbr pointer reach a number, keep moving the pointer to read whole number (can be more than one digit)
    - then move word point whole number to the right
LC0056. Merge Intervals
    - sort intervals by starting time
    - if next interval's start time overlaps with current interval, merge
    - otherwise move current interval to result, set next interval to current
LC0227. Basic Calculator II
    - keep track of 3 vars: prevNum, op, currNum
    - traverse string
    - if current char is digit, append to currNum
    - if lastNum (+|-) currNum (+|-|*|/) -> add lastNum to result, set lastNum to currNum
    - if lastNum (*|/) currNum (+|-|*|/) -> set lastNum to lastNum (*|/) currNum
    - set op to current char, set currNum to 0
    - add lastNum to result before return
LC0314. Binary Tree Vertical Order Traversal
    - bfs and dfs both works
    - keep track of current column id when traversing the tree
    - keep a hashmap (key: column id, value: a list of nodes with that column id)
    - keep updating the hashmap when traversing the tree
    - keep track of min and max column ids
LC0339. Nested List Weight Sum
    - bfs with depth
LC0528. Random Pick with Weight
    - go through weights to build a prefix sum array and total sum
    - generated a random number less than total sum
    - binary search the prefix sum array to find the index
LC1650. Lowest Common Ancestor of a Binary Tree III
    - two pointers one for each tree
    - keep moving pointers towards root
    - if reaching root, pointing to the other node and continue running
LC0215. Kth Largest Element in an Array
    - have a priority queue of size K+1
    - keep adding numbers into queue
    - keep polling the smallest number from queue if queue size is K
LC0680. Valid Palindrome II
    - two pointers, one at head and one at tail
    - if head == tail, move both pointers
    - otherwise, check if the rest is a palindrome if delete head or tail
LC1570. Dot Product of Two Sparse Vectors
    - keep a list of non-zero elements and their indexes
    - add to result only if both elements are non zero at the same index
LC0560. Subarray Sum Equals K
    - keep a map with k is sum, value is count
    - keep track of current sum starting from index 0
    - increment result if current sum - k has non-zero counts in map
LC0162. Find Peak Element找小于等于
    - binary search
LC0670. Maximum Swap
    - start from the leftmost digit
    - find the last occurrence of the largest digit that is larger than the leftmost digit
    - swap and return
    - if no such swap, move to the second leftmost digit and repeat
LC0071. Simplify Path变形题，实现一个cd函数
    - use a stack to keep track of current directory
    - "." is no-op
    - "/" is push
    - ".." is pop
LC0921. Minimum Add to Make Parentheses Valid
    - two integer counters a and b
    - increment a when reading '(', decrease a when reading ')'
    - so a == 0 means opening and closing parenthesis are matched
    - if a < 0, we need to delete/add parenthesis, a++ and b++
    - if a > 0 after iteration, means there are unmatched opening parenthesis, b + a is total ops
LC0347. Top K Frequent Elements
    - use a hashmap to count frequencies for each number
    - use an array where index is the frequency number, value is a list of nums
    - iterate the array from top index, keep filling until K elements
LC0236. Lowest Common Ancestor of a Binary Tree
    - recursion
LC0050. Pow(x, n) 追问了如果输入有零 都是零 程序能处理吗
    - x^n -> x * x^(n-1)
    - x^n -> (x * x)^(n/2)
LC0125. Valid Palindrome
    - two pointers one at beginning and one at end
    - keep moving the pointers until a letter/digit is read
LC0138. Copy List with Random Pointer
    - use a hashmap to keep track of old node to new node
LC0938. Range Sum of BST
    - use a stack, initially push root
    - pop from stack, of node value is within given range, add to sum
    - push left node if left range < node.val
    - push right node if right range > node.val
LC1091. Shortest Path in Binary Matrix
    - bfs
LC1762. Buildings With an Ocean View
    - start from rightmost building
    - keep track of max height
LC0636. Exclusive Time of Functions
    - stack
LC0346. Moving Average from Data Stream
    - queue of K elements
LC0973. K Closest Points to Origin
    - sort all points
LC0426. Convert Binary Search Tree to Sorted Doubly Linked List
    - make flatten(node) return Node[] where 0 is head of the list and 1 is tail
    - recursion
    - left = flatten(node.left); right = flatten(node.right)
    - stitch left, node, right together as output
    - link head and tail before returning head
LC0791. Custom Sort String
    - keep track of character frequency
    - go through dict and output frequency number of characters
LC0199. Binary Tree Right Side View用了广度搜索，被追问深度优先搜索如何做
    - bfs and dfs
LC0987. Vertical Order Traversal of a Binary Tree
    - dfs, keep track of row and col ids for each node
LC0708. Insert into a Sorted Circular Linked List
    - insert value can be min, max or regular
    - min: prev > next and val < next (next is the min)
    - max: prev > next and val > prev (prev is the max)
    - regular: prev <= val <= next
LC0065. Valid Number
    - just go through each character and return false in the following scenario
    - +|- not in the beginning, and not prefixed by 'e' or 'E'
    - e|E not prefixed by digits
    - . has more than 1 or prefixed by exponent
    - any other characters
LC0249. Group Shifted Strings
    - for each string, calculate a hash value by calculating the relative distance between one letter to the next
    - for example ab -> 1; ac -> 2; etc.
LC0282. Expression Add Operators
LC0498. Diagonal Traverse
LC0415. Add Strings
LC0317. Shortest Distance from All Buildings
LC0163. Missing Ranges
LC1216. Valid Palindrome III
LC0489. Robot Room Cleaner
LC0398. Random Pick Index
LC0523. Continuous Subarray Sum
LC1428. Leftmost Column with at Least a One
LC0766. Toeplitz Matrix
LC0301. Remove Invalid Parentheses
LC0173. Binary Search Tree Iterator
    - use inorder traversal to build a sorted list of nodes
    - then next() and hasNext() will be O(1)
    - or alternatively, only keep track of a stack used by tree traversal
    - the next smallest node in a BST is the leftmost leaf of current node's right subtree.
    - if the right subtree does not exist, pop stack to get its parent
LC0270. Closest Binary Search Tree Value
    - try root
    - update closest
    - if target < root, try root.left next, otherwise try root.right next
LC2060. Check if an Original String Exists Given Two Encoded Strings
    - dfs with memo
    - keep track of i in s1, j in s2, and diff which is number diff between s1 and s2
    - if s1.charAt(i) is digit, increment i and try i,j,diff-num
    - if s2.charAt(i) is digit, increment j and try i,j,diff+num
    - if c is letter, increment diff by +|- 1
    - if c is equal, increment both i and j, keep diff
    - return false otherwise
LC1891. Cutting Ribbons
    - binary search for max length with target cuts 


LC0002 Add Two Numbers
LC0015 3Sum
LC0031 Next Permutation
LC0062 Unique Paths
LC0076 Minimum Window Substring
LC0088 Merge Sorted Array (in place)
LC0146 LRU Cache
LC0190 Reverse Bits追问了溢出如何处理
LC0230 Kth Smallest Element in a BST
LC0253 Meeting Rooms II
LC0303 Range Sum Query - Immutable
LC0329 Longest Increasing Path in a Matrix
LC0371 Sum of Two Integers
LC0380 Insert Delete GetRandom O(1)
LC0392 Is Subsequence
LC0468 Validate IP Address
LC0543 Diameter of Binary Tree
LC0695 Max Area of Island
LC0721 Accounts Merge
LC0730 Count Different Palindromic Subsequences (???)
LC0748 Shortest Completing Word
LC0792 Number of Matching Subsequences
LC0799 Champagne Tower
LC0814 Binary Tree Pruning
LC0986 Interval List Intersections
LC1004 Max Consecutive Ones III
LC1249 Minimum Remove to Make Valid Parentheses
LC1373 Maximum Sum BST in Binary Tree
LC1644 Lowest Common Ancestor of a Binary Tree II

MedianBST 二叉搜索树找中位数
CommonElements 3个array, non-decreasing order, 找common number. duplicate的number也需要算[10, 10], [10, 10], [10, 10] -> [10, 10]
MonotonicSequences

一个char[]，里面有'x', 'y'两种字符，找最长的window size 可以满足 <= k个'y'.
给一个只有小写英文字母的字符串，返回所有出现最多次的字母，按照它们的出现顺序
123456789=100, backtracking，自己加(+-*/)
There are 2 arrays which denote departing and returning flights with the respective indexes being time and the values of the array being the cost it takes for the flight. Return the minimum cost for a round trip provided the return flight can only be taken at a time post departing flight time (i.e if departing at time i, one can catch a returning flight only from time (i+1) onwards). For eg departing = [1,2,3,4] and returning = [4,3,2,1], the minimum cost for round trip will be 2 i.e departing[0] + returning[3]. Solve this is O(n) time
便利答应一个矩阵，找一个加起来是某个数的subarray
convert tree to circular doubly linkedlist(斯尔溜变种)follow up: thread safe


斜着打印2d array
1 2 3 4
5 6 7 8
-> 1 6
   5
   2 7
   3 8
   4










BQ
tell me about yourself
different working style
challenge/conflict
proud of/interesting project
bad feedback/how to handle constructive feedback/negative feedback
have you given feedback to others
mistake
ambiguity
cross functional合作，disagree怎么处理
mentor别人去做一些决定
risk
最建设型的领导给的建议（就是自己没做好，被领导批评了），还有如果领导做了错误决定，如何纠正的

Design
alex xu
写码竞赛平台
订票系统，只focus在很多人抢一个event的票
design image upload service, dive deep: share image through unique url + how to ensure unique url(我答得是hashing), handle unequal read/write ratio: 20:1, db 我用的是Postgres + blob


















------------------------------------------------------------------------------------------------------------------------
VO
7-9 days

45min
2 coding
1 design
    backend system design

1 behavioral
empathy
upward feedback
take initiative

-----------------
LC0023. Merge k Sorted Lists
LC0056. Merge Intervals
LC0065. Valid Number (eg. 1.2E4)
LC0071. Simplify Path
LC0103. Binary Tree Zigzag Level Order Traversal
LC0125. Valid Palindrome
LC0129. Sum Root to Leaf Numbers
LC0133. Clone Graph
LC0146. LRU Cache
LC0199. Binary Tree Right Side View
LC0215. Kth Largest Element in an Array
LC0236. Lowest Common Ancestor of a Binary Tree
LC0263. Ugly Number
LC0268. Missing Number
LC0314. Binary Tree Vertical Order Traversal
LC0339. Nested List Weight Sum
LC0346. Moving Average from Data Stream
LC0387. First Unique Character in a String
LC0408. Valid Word Abbreviation
LC0490. The Maze
LC0493. Reverse Pairs
LC0560. Subarray Sum Equals K
LC0680. Valid Palindrome II (remove at most 1)
LC0721. Accounts Merge
LC0827. Making A Large Island
LC0863. All Nodes Distance K in Binary Tree
LC0973. K Closest Points to Origin
LC1004. Max Consecutive Ones III
LC1249. Minimum Remove to Make Valid Parentheses
LC1570. Dot Product of Two Sparse Vectors
LC1644. Lowest Common Ancestor of a Binary Tree II
LC1762. Buildings With an Ocean View
LC1868. Product of Two Run-Length Encoded Arrays

做选择
conflict
bad feedback
uncertain
volunteer take responsibility
bad teammate

top k songs
ad click counts (realtime)
distributed web crawler


given an integer array, find all subarrays of length l, where l is the power of 2 (1,2,4,8,...)
that the sum of the subarray is between [k, 2k]
LC0636
