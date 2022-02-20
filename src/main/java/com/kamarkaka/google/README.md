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

(1509) Minimum Difference Between Largest and Smallest Value in Three Moves
- find 4 largest numbers
- find 4 smallest numbers
- remove 1 number in two arrays at a time, try to minimize diff

(946) Validate Stack Sequences
- have a stack push/pop along with the two arrays
- keep pushing until reaching the first element in pop
- pop that element, push until seeing the next element in pop
- repeat until reaching end of pop, see if stack is empty

(1293) Shortest Path in a Grid with Obstacles Elimination
- bfs the matrix while keeping track of the number of eliminations left

(1438) Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
- have a min queue storing smallest numbers
- have a max queue storing largest numbers
- increment hi pointer when diff <= limit
- increment lo pointer otherwise
- all elements larger than nums[hi] in min needs to be removed
- all elements smaller than nums[hi] in max needs to be removed

