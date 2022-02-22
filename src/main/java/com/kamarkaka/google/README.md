[1167. Minimum Cost to Connect Sticks](https://leetcode.com/problems/minimum-cost-to-connect-sticks/)
- priority queue
- pop first two stick, combine them, put back to queue

[1197. Minimum Knight Moves](https://leetcode.com/problems/minimum-knight-moves/)
- (x,y) can be simplified to (|x|,|y|) due to symmetry
- dp[0][0], dp[1][0], dp[0][1], dp[1][1] can be set by manual calculation
- bfs to keep filling dp[x][y]
- return dp[x][y]

[1277. Count Square Submatrices with All Ones](https://leetcode.com/problems/count-square-submatrices-with-all-ones/)
- dp
- dp[i][j] means number of squares ends on this point (bottom right corner)
- dp[i][j] = 1 + min(dp[i-1][j-1], min(dp[i][j-1], dp[i-1][j]))

[1423. Maximum Points You Can Obtain from Cards](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/)
- pick all cards from beginning
- return one card at a time, replace with one from rear, update max
- return max

[1539. Kth Missing Positive Number](https://leetcode.com/problems/kth-missing-positive-number/)
- consider sequence 1,2,3,...last element+k
- pointer 0 to arr
- if arr[pointer] matches number, increment pointer
- otherwise k--
- return number when k is 0
- or return last element+k when pointer reaches end of arr

[158. Read N Characters Given read4 II - Call Multiple Times](https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/)
- have a cache and cachecount
- have a readbuffer
- initially, cache is empty, fill readbuffer with read4
- put char in readbuffer to output buffer
- if readbuffer is exhausted, read4 to fill it again
- if desired length is reached, put whatever is left to cache
- for next call, load cache into readbuffer

[1723. Find Minimum Time to Finish All Jobs](https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/)
- greedy does not work
- back tracking

[2031. Count Subarrays With More Ones Than Zeros](https://leetcode.com/problems/count-subarrays-with-more-ones-than-zeros/)
- hashmap <key sum, value index>
- sum is the sum of 1(1) and 0(-1) from 0 to index
- dp[i] is total number of subarrays ends in index i
- if sum is encountered before, dp[i] is dp[prevIdx]
- but if curr number is 1, need to add more (i - 1 - prevIdex)
- if sum is not encountered before, dp[i] is i+1;
- but if curr number is 0, need to decrease by 1
- add all dp[i] for final answer

[2096. Step-By-Step Directions From a Binary Tree Node to Another](https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/)
- find path to start and dest from root, separately
- discard LCA path
- convert remaining start path to u
- append remaining end path

[2115. Find All Possible Recipes from Given Supplies](https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/)
- recursively check whether all ingredients in a recipe can:
- either in supply
- can be made by a recipe

[295. Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/)
- 2 priority queues
- one min heap storing upper half
- one max heap storing lower half
- keep adding to lower half (max heap), then add top of min heap to upper half (min heap)
- make sure size of two pq are same or diff by 1

[315. Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/)
- merge sort

[329. Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/)
- dfs with memorization

[379. Design Phone Directory](https://leetcode.com/problems/design-phone-directory/)
- current is the number that is currently available
- next[] to keep track of number status
- next[i] = -1 means number i is taken
- next[i] > 0 means if we take i as the number to return, the next number we should take is next[i]
- initially, next[i] = i + 1, except that last element points back to 0

[528. Random Pick with Weight](https://leetcode.com/problems/random-pick-with-weight/)
- calculate total weight and sum of previous weight to a number
- randomly generate number in range of total weight
- binary search in previous weight array to find the number

[54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/)
- just follow instructions
- traverse right, down, left up
- decrease limit after each traversal

[552. Student Attendance Record II](https://leetcode.com/problems/student-attendance-record-ii/)
- dp[2][3]
- dp[i][j]: i is number of absent days (can only be 0 or 1)
- dp[i][j]: j is number of most recent late days (can only be 0, 1, or 2)
- dp[i][j]: number of possible attendance records
- n = 1, return 3
- n = 2, manually calculate dp as base case
- start from n = 3
- newdp can be derived from dp
- return the sum of all dp[i][j]

[56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)
- sort intervals by start time asc
- put first into result
- if next interval overlaps with last interval in result, update end time of last interval in result
- otherwise, add this interval to result
- repeat for all intervals

[690. Employee Importance](https://leetcode.com/problems/employee-importance/)
- have a map <key employee id, value employee object>
- recursively run getImportance(id) on employee id and add all of his subordinates

[727. Minimum Window Subsequence](https://leetcode.com/problems/minimum-window-subsequence/)
- increment pointer to find first end of potential match
- decrease pointer to find shortest match
- increment pointer again to find next match
- decrease again to find next shortest match
- update if needed

[954. Array of Doubled Pairs](https://leetcode.com/problems/array-of-doubled-pairs/)
- sort array by abs
- pick from smallest to largest abs
- create count map <key num, value count>
- decrease x, x*2 by 1 each in map
- return false if any x*2 has zero or negative count

[97. Interleaving String](https://leetcode.com/problems/interleaving-string/)
- greedy would not work
- dp[j]: whether 0-j chars of s2 can for interleaving string
- keep updating from i:0-s1.length() and j:0-s2.length()

[68. Text Justification](https://leetcode.com/problems/text-justification/)
- add words to line
- justify line after all words are added

[149. Max Points on a Line](https://leetcode.com/problems/max-points-on-a-line/)
- for each point, calculate max points on a line by slope
- get global max
- no previous points need to be considered

[708. Insert into a Sorted Circular Linked List](https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/)
- go through linked list to find place to insert:
- prev <= insert <= next
- or insert before smallest
- or insert after largest
- edge case: list has only 0 or 1 node

[20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
- stack
- push if open parentheses
- pop if close parentheses and is a matched pair with top of stack
- stack should be empty in the end, if valid

[299. Bulls and Cows](https://leetcode.com/problems/bulls-and-cows/)
- array of 10 to store counters for each digit
- bulls++ if characters are the same at the same index for secret and guess
- otherwise, counter++ for the digit in secret, counter-- for the digit in guess
- cows++ if secret counter < 0 or guess counter > 0
- reason is previous secret or guess has already ++/-- this digit

[843. Guess the Word](https://leetcode.com/problems/guess-the-word/)
- pick any word to start with
- get match count from that word
- use this word to check against other words
- if a word has the same number of characters at the same position as this word, leave it there
- otherwise, remove from set as it cannot be the answer
- reason is, for example if the word returns match 1 with secret
- the correct word should have exact 1 match with this one, not 0, not 2

[2007. Find Original Array From Doubled Array](https://leetcode.com/problems/find-original-array-from-doubled-array/)
- sort the array
- start from smallest number min, say, there are k
- check if there are at least k numbers with value min*2
- use map <key: num, val: count>
- edge case: num is zero since num * 2 = num

[253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)
- sort all intervals by start time
- keep a priority queue of end time
- initially add first interval
- for all intervals
    - poll from pq if start time larger than top of pq
    - add interval to pq
- pq size is the answer

[45. Jump Game II](https://leetcode.com/problems/jump-game-ii/)
- greedy
- remember how far we can jump now
- calculate how far we can jump within the current jump steps
- the furthest we be our next jump start

[947. Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/)
- union find
- the observation here is that if a bunch of stones sharing the same row/col with each other
- there are always ways to remove them until there is only 1 stone left
- so the problem is reduced to how many isolated "island" are there NOT sharing row/col

[1937. Maximum Number of Points with Cost](https://leetcode.com/problems/maximum-number-of-points-with-cost/)
- dp[i] means best points you can get
- for each row, needs two runs on each col: left and right
- update dp[i] with prev dp[i-1] and next dp[i+1]

[1240. Tiling a Rectangle with the Fewest Squares](https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/)
- dp with memorization
- dp[i][j] is the minimum sqaure number to fill a rec with size i*j
- return dp[m][n]

[274. H-Index](https://leetcode.com/problems/h-index/)
- sort by citation asc
- idx initially set to 0
- if citation[end - idx] > idx, 
- meaning there are at least idx number of papers with citation number larger than idx
- increment idx and try until break

[1877. Minimize Maximum Pair Sum in Array](https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/)
- sort by number asc
- pick smallest and largest as a pair
- calculate pair sum, update if needed

[1525. Number of Good Ways to Split a String](https://leetcode.com/problems/number-of-good-ways-to-split-a-string/)
- 1 run go left to right, calculate distinct letters on each idx
- 1 run go right to left, calculate distinct letters on each idx
- compare left[i] and right[i+1] if equals, res++

[1509. Minimum Difference Between Largest and Smallest Value in Three Moves](https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/)
- find 4 largest numbers
- find 4 smallest numbers
- remove 1 number in two arrays at a time, try to minimize diff

[1293. Shortest Path in a Grid with Obstacles Elimination](https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/)
- bfs the matrix while keeping track of the number of eliminations left

[1554. Strings Differ by One Character](https://leetcode.com/problems/strings-differ-by-one-character/)
- for each word, replace one char to * and add to set
- do this to every letter in a word
- return true if a replaced word is already in set

[767. Reorganize String](https://leetcode.com/problems/reorganize-string/)
- sort letters by freq
- start fill string by using letters with most freq

[2034. Stock Price Fluctuation](https://leetcode.com/problems/stock-price-fluctuation/)
- two heaps
- don't try to delete updated records
- poll from heap if the record is updated

[1477. Find Two Non-overlapping Sub-arrays Each With Target Sum](https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/)
- two pointers, increment left and right pointer to find a subarray with sum target
- keep track of minimum length of the subarray
- mins[r] tracks minimum length of qualifying subarray ends at index r
- whenever a qualifying subarray (l, r) is found, check mins[l-1] and update combined length

[103. Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)
- bfs
- remember traversal direction, change after traversing one level

[2092. Find All People With Secret](https://leetcode.com/problems/find-all-people-with-secret/)
- sort by timestamp asc
- union find all meetings on each timestamp

[489. Robot Room Cleaner](https://leetcode.com/problems/robot-room-cleaner/)
- use a stack to keep track of moving path
- keep track of cleaned spaces
- keep track of walls

[1376. Time Needed to Inform All Employees](https://leetcode.com/problems/time-needed-to-inform-all-employees/)
- dfs
- dfs returns the time it needs to inform this employee, from boss
- if this guy is manager, return the time it takes to inform all subs
- if not, return the time plus his manager's time to get informed, then set him to boss

[772. Basic Calculator III](https://leetcode.com/problems/basic-calculator-iii/)
- a valStack for all values
- a opStack for all ops and '('
- if digit, keep appending
- if '(' push to opStack
- if ')', pop 2 vals and 1 op, do calculation and push result back to valStack
- keep doing it until opStack top is not '(', then pop it
- if '+-*/', maybe negative number, check for that
- otherwise, calculate result from 2 vals and 1 op, push result to valStack
- keep doing it until you hit '(' or pre is +/- and you're doing *//
- push current op into opStack

[366. Find Leaves of Binary Tree](https://leetcode.com/problems/find-leaves-of-binary-tree/)
- recursion
- removeLeaves(root) will remove leaves from tree at root and return a set of leaf nodes
- if root is null return empty set
- if root is leaf return itself
- recursively call removeLeaves on left and right nodes
- remove left/right nodes if necessary
- return union of left and right leaves
- call removeLeaves on root until root is in the result set

[1278. Palindrome Partitioning III](https://leetcode.com/problems/palindrome-partitioning-iii/)
- dp
- break the string into 2, check the cost of 1st by replacing letters to palindrome
- and add min cost of 2nd string breaking into k-1 parts
- run it recursively

[1931. Painting a Grid With Three Different Colors](https://leetcode.com/problems/painting-a-grid-with-three-different-colors/)
- dfs to get all permutations of color in a row, say there are n permutation
- create a n*n matrix arr to pre calculate if a row is compatible with another
- arr[i][j] = 1 means permutation i is compatible with permutation j, otherwise is 0
- create array dp with length n, initially, all values are set to 1
- for each newdp[i], all arr[i][j] = 1 can add dp[j] to newdp[i]
- keep iterating col times
- add all dp[i] to get final answer

[129. Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers/)
- dfs and keep track of the num you get so far
- when reaching a leaf node, add to sum
- otherwise, update the num to 10*num+node.val and pass to its child nodes

[369. Plus One Linked List](https://leetcode.com/problems/plus-one-linked-list/)
- reverse list, add one, and reverse again
- or
- find last node that is not 9
- add 1 to it, update all nodes after it to 0

[1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit](https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/)
- have a min queue storing smallest numbers
- have a max queue storing largest numbers
- increment hi pointer when diff <= limit
- increment lo pointer otherwise
- all elements larger than nums[hi] in min needs to be removed
- all elements smaller than nums[hi] in max needs to be removed

[278. First Bad Version](https://leetcode.com/problems/first-bad-version/)
- binary search
- have two pointers lo and hi
- if mid is bad, set hi to mid, repeat
- if mid is good, set lo to mid+1
- repeat until two pointers meet, return

[227. Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/)
- if +/-, always calculate previous first
- if *//, calculate previous if pre-op is *// as well
- otherwise, push to stack

[150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/)
- push numbers into stack
- for ops, pop 2 numbers from stack, calculate result, push back into stack
- there should be only one number in stack, which is the result

[827. Making A Large Island](https://leetcode.com/problems/making-a-large-island/)
- calculate areas for all islands, mark each island with an index
- for each cell with value 0, see how many islands it can connect
- calculate combined area, update max if needed

[1305. All Elements in Two Binary Search Trees](https://leetcode.com/problems/all-elements-in-two-binary-search-trees/)
- in order traversal to get ordered list out of bst
- sort two ordered lists

[729. My Calendar I](https://leetcode.com/problems/my-calendar-i/)
- sorted set (TreeSet) order by start time
- use set.floor() to find the task before currnet
- use set.ceiling() to find the task after current
- check if there is overlap




(359) Logger Rate Limiter
- use map to remember the next available time for a message

(1153) String Transforms Into Another String
- str2 cannot have all 26 letters
- create a str1 -> str2 character mapping per index
- character from str1 cannot have more than 1 mapping to str2

(1548) The Most Similar Path in a Graph
- build graph
- start from any node
- dfs to find min edit dist starting from that node
- find minimum edit dist
- rebuild path
- memorize visited with min dist to a node at index
- memorize nextChoiceForMin with next node to choose from a node at index

(946) Validate Stack Sequences
- have a stack push/pop along with the two arrays
- keep pushing until reaching the first element in pop
- pop that element, push until seeing the next element in pop
- repeat until reaching end of pop, see if stack is empty


