[815. Bus Routes](https://leetcode.com/problems/bus-routes/)
- graph bfs
- nodes are buses
- 2 nodes are connected if two buses share the same bus stops (intersect)

[146. LRU Cache](https://leetcode.com/problems/lru-cache/)

[253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)

[34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
- binary search

[57. Insert Interval](https://leetcode.com/problems/insert-interval/)
- go through current intervals one by one
- if current intervals has no overlap with new interval, add curr
- otherwise update new interval by merging curr and new

[212. Word Search II](https://leetcode.com/problems/word-search-ii/)
- build trie on words
- dfs from every single value in matrix

[465. Optimal Account Balancing](https://leetcode.com/problems/optimal-account-balancing/)
- calculate balances for each one based on transactions
- start = 0, need to transfer this guy's balance to someone
- dfs to find who to transfer

[773. Sliding Puzzle](https://leetcode.com/problems/sliding-puzzle/)
- bfs
- memorize all encountered patterns
- try all possible moves, then advance until matches target

[347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)
- go through all elements to count freq for each
- create N+1 buckets, bucket index is freq, value is a list of elements
- go from largest freq, output k elements

[388. Longest Absolute File Path](https://leetcode.com/problems/longest-absolute-file-path/)
- stack to remember current length of path, excluding current dir/file
- go through each line (split by /n)
- count number of \t on each line to determine depth
- pop stack to match depth, peek to get length
- plus current length

[986. Interval List Intersections](https://leetcode.com/problems/interval-list-intersections/)
- two pointers
- find intersection of two intervals, increment pointers accordingly

[4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)
- binary search on shorter array
- mid1 is mid on shorter array, mid2 is (len1+len2)/2-mid so that there are half of the elements before mid1 and mid2
- check num1[mid1-1], num1[mid1], num2[mid2-1], num2[mid2]
- if they overlap, median is max or avg of num1[mid1], num2[mid2]
- otherwise increase lo or hi accordingly


[36. Valid Sudoku](https://leetcode.com/problems/valid-sudoku/)
- bit masking
- for a column/row/square, set nth bit to 1 of there is a number n
- keep setting until you find nth bit is set, duplication found, return false

[71. Simplify Path](https://leetcode.com/problems/simplify-path/)
- stacks
- split by /
- reconstruct the path by push dir to stacks, popping while ".."

[76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)
- sliding window with two pointers (start, end)
- find match by increasing end
- increase start until no longer valid
- increase end until valid
- repeat
- find shorted end-start during the process

[98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)
- both subtrees (left and right) are valid
- max in left subtree smaller than root
- min in right subtree larger than root

[101. Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)
- left and right is symmetric if:
- left.right is symmetric with right.left and
- left.left is symmetric with right.right

[126. Word Ladder II](https://leetcode.com/problems/word-ladder-ii/)
- bfs from both begin word and end word until they meet in the middle
- memorize change path in a map with each word
- dfs to find the path
- store path in result

[127. Word Ladder](https://leetcode.com/problems/word-ladder/)
- same as 126
- bfs from both ends, memorizing current depth
- return depth when found match

[153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)
- binary search
- find mid element of array
- if mid > first elemnent, get rid of left
- otherwise get rid of right

[154. Find Minimum in Rotated Sorted Array II](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/)
- binary search
- find mid element of array
- if mid > hi, get rid of right
- if mid < hi, get rid of left
- if equal, right--

[207. Course Schedule](https://leetcode.com/problems/course-schedule/)
- hashmap, key is course, value is prerequisite of this course
- for each node i, do a bfs to find all course that need to be taken before it
- see if i is in its own set, if yes, there is circular reference

[224. Basic Calculator](https://leetcode.com/problems/basic-calculator/)
- have a stack store left operand and operator
- push to stack when "("
- pop stack when ")"

[238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)
- two arrays: left and right
- left[i] is the product of nums[0] to nums[i-1]
- right[i] is the product of nums[i+1] to nums[N-1]
- product[i] = left[i] * right[i]

[239. Sliding Window Maximum]()
- use deque (double ended queue)
- left end is always the maximum
- if encounters a number greater than current max, clear deque and push the number in
- if encounters a number smaller than current max, remove all elements in queue from right that is smaller than current number

[252. Meeting Rooms](https://leetcode.com/problems/meeting-rooms/solution/)
- sort by start time
- see if there is overlap

[259. 3Sum Smaller](https://leetcode.com/problems/3sum-smaller/solution/)
- sort the array
- pick a number
- calculate 2SumSmaller for the numbers larger than current number, and target = target - num
- for 2SumSmaller
  - two pointers
  - move right until sum < target
  - increment by diff of two pointer
  - move left


[332. Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/)
- build graph
- hashmap key origin, value list of destinations
- backtrack until a route is found utilizing all tickets

[340. Longest Substring with At Most K Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/)
- two pointers and hashmap key char value rightmost index
- keep adding char into map and increment hi pointer, until map size > k
- find char with lowest index and remove from map, move lo pointer next to it
- update max len

[341. Flatten Nested List Iterator](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/)
- nested iterator

[398. Random Pick Index](https://leetcode.com/problems/random-pick-index/)
- hashmap key number, value list of indices

[427. Construct Quad Tree](https://leetcode.com/problems/construct-quad-tree/)
- recursive calls

[470. Implement Rand10() Using Rand7()](https://leetcode.com/problems/implement-rand10-using-rand7/)
- rejection sampling
- run rand7 twice to get uniform random 1-49
- discard 41-49 (try again)


[529. Minesweeper](https://leetcode.com/problems/minesweeper/)
- if click mine, die and game over
- if click has mine in surrounding 8 blocks, display number and stop
- if click is empty, bfs 8 surrounding 8 blocks

[542. 01 Matrix](https://leetcode.com/problems/01-matrix/)
- bfs
- put all 0s in queue
- update all 1s to -1
- bfs until queue is empty
- do not update cells that value is not -1

[636. Exclusive Time of Functions]()

[694. Number of Distinct Islands]()

[729. My Calendar I]()

[824. Goat Latin]()

[855. Exam Room]()

[895. Maximum Frequency Stack]()

[953. Verifying an Alien Dictionary]()

[1182. Shortest Distance to Target Color]()

[1200. Minimum Absolute Difference]()

[1515. Best Position for a Service Centre]()


[757. Set Intersection Size At Least Two](https://leetcode.com/problems/set-intersection-size-at-least-two/)
- sort array by end element asc
- get largest 2 elements from 1st interval
- go through the rest of intervals
- if not overlapping with the 2 elements picked, pick largest 2 elements, res += 2
- otherwise there is overlapping, res += 1, update largest elements accordingly
- return res

[1400. Construct K Palindrome Strings](https://leetcode.com/problems/construct-k-palindrome-strings/)
- look for chars appears only odd times
- see if it's larger than k, if yes then return false, otherwise true

[529. Minesweeper](https://leetcode.com/problems/minesweeper/)
- bfs
- if click mine, game over
- if click position has surrounding mines, display mineCount
- if click position is empty, recursively try click surround 8 positions

[174. Dungeon Game](https://leetcode.com/problems/dungeon-game/)
- dp
- go from target back all the way to start
- memorize minimum health required to enter the room
- current min health can be caluated by: current room value, min health from room to the right, and down

[305. Number of Islands II](https://leetcode.com/problems/number-of-islands-ii/)
- union find
- one land can overlap with 4 neighbors
- union the land with its neighbors, one at a time
- see how many unions are left after each add land

[332. Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/)
- backtracking
- build graph using hashmap<origin, list<dest>>
- meanwhile memorize visited nodes
- 