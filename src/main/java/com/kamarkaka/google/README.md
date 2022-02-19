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






(843) Guess the Word
- pick any word to start with
- get match count from that word
- use this word to check against other words
- if a word has the same number of characters at the same position as this word, leave it there
- if has less, remove from set as it cannot be the answer



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

(727) Minimum Window Subsequence
- increment pointer to find first end of potential match
- decrease pointer to find shortest match
- increment pointer again to find next match
- decrease again to find next shortest match
- update if needed
