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
- keep track of the number of prerequisies a course has
- only output the courses with 0 prerequisite
- bfs, when visiting a node, decrease number of prerequisites for all its neighbors

[227. Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/)
- if +/-, always calculate previous first
- if *//, calculate previous if pre-op is *// as well
- otherwise, push to stack

[337. House Robber III](https://leetcode.com/problems/house-robber-iii/)
- if current node is robbed, can only start robbing grand children
- if current node is not robbed, can start robbing children
- calculate max
- store results in hashmap <key: node, value: max>

[341. Flatten Nested List Iterator](https://leetcode.com/problems/flatten-nested-list-iterator/)
- nested iterator

[490. The Maze](https://leetcode.com/problems/the-maze/)

[528. Random Pick with Weight](https://leetcode.com/problems/random-pick-with-weight/)

[560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

[567. Permutation in String](https://leetcode.com/problems/permutation-in-string/)

[642. Design Search Autocomplete System](https://leetcode.com/problems/design-search-autocomplete-system/)

[670. Maximum Swap](https://leetcode.com/problems/maximum-swap/)

[702. Search in a Sorted Array of Unknown Size](https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/)

[773. Sliding Puzzle](https://leetcode.com/problems/sliding-puzzle/)

[855. Exam Room](https://leetcode.com/problems/exam-room/)

[1375. Number of Times Binary String Is Prefix-Aligned](https://leetcode.com/problems/number-of-times-binary-string-is-prefix-aligned/)

[1833. Maximum Ice Cream Bars](https://leetcode.com/problems/maximum-ice-cream-bars/)

[1996. The Number of Weak Characters in the Game](https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/)
