[815. Bus Routes](https://leetcode.com/problems/bus-routes/)
- graph bfs
- nodes are buses
- 2 nodes are connected if two buses share the same bus stops (intersect)

[146. LRU Cache](https://leetcode.com/problems/lru-cache/)

[253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)

[68. Text Justification](https://leetcode.com/problems/text-justification/)
- add words to line
- justify line after all words are added

[34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
- bfs

[57. Insert Interval](https://leetcode.com/problems/insert-interval/)
- go through current intervals one by one
- if current intervals has no overlap with new interval, add curr
- otherwise update new interval by merging curr and new

[212. Word Search II](https://leetcode.com/problems/word-search-ii/)
- build trie on words
- dfs from every single value in matrix

[465. Optimal Account Balancing](https://leetcode.com/problems/optimal-account-balancing/)
- calculte balances for each one based on transactions
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

[20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
- stack
- push if open parentheses
- pop if close parentheses and is a matched pair with top of stach
- stack should be empty in the end, if valid

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

[427. Construct Quad Tree](https://leetcode.com/problems/construct-quad-tree/)
- recursive calls

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