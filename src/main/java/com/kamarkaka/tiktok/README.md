[33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)
- binary search
- if pivot is not in range (nums[lo] < nums[hi]), do normal binary search
- if pivot is in range (nums[lo] > nums[hi]), do binary search on 2 parts at the same time

[76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)
- sliding window
- increment right to find first match
- increment left for shorter substring possible
- increment left to invalidate current substring
- increment right to validate string again

[98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)

[146. LRU Cache](https://leetcode.com/problems/lru-cache/)

[200. Number of Islands](https://leetcode.com/problems/number-of-islands/)
- bfs
- mark visited nodes

[210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/)
- topological sort
- keep track of the number of prerequisites a course has
- only output the courses with 0 prerequisite
- bfs, when visiting a node, decrease number of prerequisites for all its neighbors

[337. House Robber III](https://leetcode.com/problems/house-robber-iii/)
- if current node is robbed, can only start robbing grand children
- if current node is not robbed, can start robbing children
- calculate max
- store results in hashmap <key: node, value: max>

[341. Flatten Nested List Iterator](https://leetcode.com/problems/flatten-nested-list-iterator/)
- nested iterator

[490. The Maze](https://leetcode.com/problems/the-maze/)
- bfs
- use while to find next positions

[528. Random Pick with Weight](https://leetcode.com/problems/random-pick-with-weight/)

[560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)
- it's going to have negatives in array
- O(n^2) with two pointers
- fix start and change end, calculate sum for each end
- increment start and change end and calculate again

[567. Permutation in String](https://leetcode.com/problems/permutation-in-string/)
- sliding window of fixed size s1.length
- use int[26] to remember count for each char in s1
- use another int[26] s2map for each char in window
- keep updating s2map when sliding window
- return true if two int[26] equal

[642. Design Search Autocomplete System](https://leetcode.com/problems/design-search-autocomplete-system/)
- trie

[670. Maximum Swap](https://leetcode.com/problems/maximum-swap/)
- store the rightmost index of each digit
- start from the most significant digit, see if there is a bigger digit on its right
- if yes, swap with that bigger digit on the rightmost index

[702. Search in a Sorted Array of Unknown Size](https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/)
- search for boundaries by doubling each time
- do regular binary search afterwards

[773. Sliding Puzzle](https://leetcode.com/problems/sliding-puzzle/)
- bfs

[855. Exam Room](https://leetcode.com/problems/exam-room/)
- priority queue by longest length
- seat: poll from pq, add two more
- leave: iterate all elements from pq, find the two intervals with p
- remove the two intervals, add a new one

[1375. Number of Times Binary String Is Prefix-Aligned](https://leetcode.com/problems/number-of-times-binary-string-is-prefix-aligned/)
- compare sum of flip and sum (1..i)

[1833. Maximum Ice Cream Bars](https://leetcode.com/problems/maximum-ice-cream-bars/)
- sort the array of costs
- pick the cheapest first

[1996. The Number of Weak Characters in the Game](https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/)
- sort array by attack asc
- for same attack, sort by defence desc
- go from list end to beginning
- keep track of max defence
- if current defence less than max, it is weak as all past elements have higher attack and at least one has max defence
