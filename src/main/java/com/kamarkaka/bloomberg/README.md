[2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)
- add nodes one by one, calculate carry, bring to next node

[3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
- two pointers
- a hashset keeping track of all characters encountered
- move right pointer until a repeating char
- move left pointer until reaching the repeating char
- keep track of longest in the process

[8. String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/)
- one pointer
- skip spaces and minus sign to the first digit, determine sign
- get one digit at a time, calculate integer
- make sure overflow is handled

[19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
1. solution 1 (2 pass less space)
   - go to tail of list
   - go back n nodes and remove
2. solution 2 (1 pass more space)
   - keep track of the index of each node
   - save pointer to node in hash map
   - get node with index (size - 1 - n) and cut next node

[33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)
- recursive binary search
- return if mid is target
- if starting number less than ending number, the range is increasing, do normal binary search
- otherwise recursively search lower half and higher half, see if result is found

[56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)
- sort all starts
- sort all ends
- add interval only when
  - reaches end of intervals
  - next start is larger than this end

[62. Unique Paths](https://leetcode.com/problems/unique-paths/)
- dp
- result of current square is the sum of the square to the top and to the left

[79. Word Search](https://leetcode.com/problems/word-search/)
- dfs

[98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)
- recursive call
- left/right child is valid
- max in left < root
- min in right > root

[117. Populating Next Right Pointers in Each Node II](https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/)
- bfs

[140. Word Break II](https://leetcode.com/problems/word-break-ii/)
- put dictionary in a set
- break input string into 2 (need to try all breaks)
- if first half is in set, recursively call on second half

[146. LRU Cache](https://leetcode.com/problems/lru-cache/)
- maintain a hash map with (key, node)
- maintain a linked list, keep track of head and tail
- for gets, move the node to tail
- for puts, evict head if reaches capacity, then insert to tail

[200. Number of Islands](https://leetcode.com/problems/number-of-islands/)
- go through all nodes in matrix
- bfs or dfs all adjacent nodes, mark them
- skip marked nodes

[226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/)
- recursively call it on right and left child nodes

[238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)
1. brute force
2. keep track of two arrays
    - one stores products of all elements to the right of curr idx
    - one stores products of all elements to the left of curr idx
    - product of idx is left * right

[253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)
- sort all intervals by start time
- keep a priority queue of end time
- initially add first interval
- for all intervals
  - poll from pq if start time larger than top of pq
  - add interval to pq
- pq size is the answer

[380. Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/)
- hash map with <value, index to list>
- list of all values
- current size
- when add, put into hashmap and append to tail
- when remove, remove from hashmap and swap index with tail value, then remove

[387. First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/)
- first loop to count freq
- second loop to find first char with freq = 1

[394. Decode String](https://leetcode.com/problems/decode-string/)
- keep one number stack and one string stack
- when seeing '[' push number into stack
- when seeing ']' push string into stack
  - then pop both str and num from stack, print whatever inside str for num times
- when seeing digits append to num
- when seeing chars append to str

[430. Flatten a Multilevel Doubly Linked List](https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/)
- recursive call
- put flattened children behind current node
- append next node at tail

[433. Minimum Genetic Mutation](https://leetcode.com/problems/minimum-genetic-mutation/)
- bfs
- at each idx, you can mutate to other 3 different gene
- check if in the bank
- if yes add to queue and continue to mutate
- meanwhile keep a hash map to remember min mutations for each gene, update along the way

[445. Add Two Numbers II](https://leetcode.com/problems/add-two-numbers-ii/)
1. reverse linked list
   - add each node to get sum and carry
   - reverse back
2. no reverse?
   - one pass to each linked list, get length
   - longer list go first, so both reach end at the same time
   - calculate sum along the way
   - create a new node with sum (could be >= 10) and add to head
   - create a new linked list with sum and carry list

[451. Sort Characters By Frequency](https://leetcode.com/problems/sort-characters-by-frequency/)
- go through string get char and freq, stored in map
- put all into list, sort list by freq, and then char
- output

[460. LFU Cache](https://leetcode.com/problems/lfu-cache/)
- keyMap with key, node
- freqMap with freq, doubly linked list of node
- minFreq
- update when get and put(update only)
  - remove node from current doubly linked list
  - increment freq and add to appropriate list
  - search freqMap for the next minFreq

[463. Island Perimeter](https://leetcode.com/problems/island-perimeter/)
- for each land
- check its 4 neighbors, if one neighbor is sea/border, perimeter add 1

[472. Concatenated Words](https://leetcode.com/problems/concatenated-words/)
- dp
- keep a set of words
- sort the words by length (short to long)
- gradually add words to minimize the set size
- for each word, try to solve it with current set (should only contain words with length shorter than itself)
- dp[0] = true
- dp[i] = true if dp[j] = true and substring(i, j) is in set
- return dp[word.length()]

[532. K-diff Pairs in an Array](https://leetcode.com/problems/k-diff-pairs-in-an-array/)
- two pointers 0 and 1 idx
- if diff > k, p0++
- if diff < k, p1++
- if diff == k
  - increment res if p0 == 0 or p0 is not equal to previous number
  - p0++
- if two pointers meet p1++
- until p1 over range

[641. Design Circular Deque](https://leetcode.com/problems/design-circular-deque/)
- doubly linked list
- keep track of head and tail
- keep track of current size

[692. Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/)
- go through all strings for freq, keep a hashmap (string, freq)
- put everything into priority queue, sort by freq desc, then string
- pop k times

[694. Number of Distinct Islands](https://leetcode.com/problems/number-of-distinct-islands/)
- very similar to number of islands
- keep track of current island in a set of land positions
- whenever done, add the set to a set of islands
- return set size

[700. Search in a Binary Search Tree](https://leetcode.com/problems/search-in-a-binary-search-tree/)
- basic recursive call

[713. Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/)
- two pointers, 0,0
- left++ if current product > k do this until product < k
- count ++ right -left + 1
- right++ until reaches end
- return count

[723. Candy Crush](https://leetcode.com/problems/candy-crush/)
- check vertically and horizontally to pop
- update board
- collapse
- repeat until no pop required

[797. All Paths From Source to Target](https://leetcode.com/problems/all-paths-from-source-to-target/)
- dfs
- add curr vertice to partial res and dfs to see vertices it can go to

[987. Vertical Order Traversal of a Binary Tree](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/)
- dfs
- hash map with <column number, Pair<row number, node val>>
- min column number and max column number
- add current node to hash map and update min/max
- recursively call node's children with updated row/col numbers
- print from min column number to max

[1029. Two City Scheduling](https://leetcode.com/problems/two-city-scheduling/)
- let everyone fly to the city with lowest cost
- meanwhile remember the price difference to change
- store price diff in priority queue, from least to most
- if evenly split, return
- for every re-distribution, add difference to cost

[1169. Invalid Transactions](https://leetcode.com/problems/invalid-transactions/)
- list of all transactions
- map with key: name, value: list of transactions
- for each transaction, make sure its valid

[1209. Remove All Adjacent Duplicates in String II](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/)
- two pointers
- one point i go through every char
- one point p 
  - keeps updating char[] from string
  - keeps updating count[]

[1244. Design A Leaderboard](https://leetcode.com/problems/design-a-leaderboard/)
- hashmap scores with <player id, score>
- treemap sorted scores with <score, number of player with this score>

[1249. Minimum Remove to Make Valid Parentheses](https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/)
- keep a stack
- go through string
- if char is '[', push stack
- if char is ']', pop stack if not empty, otherwise delete
- after loop, delete remaining things in stack

[1274. Number of Ships in a Rectangle](https://leetcode.com/problems/number-of-ships-in-a-rectangle/)
- recursive call
- divide matrix into four parts, call on each part recursively

[1396. Design Underground System](https://leetcode.com/problems/design-underground-system/)
- map checkins, key:id, value: record(id,station,time)
- map average, key:station, value: set(key:station, value:double[avg, count])

[1472. Design Browser History](https://leetcode.com/problems/design-browser-history/)
- list of string as visits
- size: total size of visits
- curr: current idx

[1583. Count Unhappy Friends](https://leetcode.com/problems/count-unhappy-friends/)
- for every person, check all his preferences (excluding current pair)
- now you have this person x, his pair y, and his preference u and u's pair v
- check if x u/v matches better than y v/u
- increment counter accordingly

[1656. Design an Ordered Stream](https://leetcode.com/problems/design-an-ordered-stream/)
- string[] buckets
- curr pointer = 0
- send id,value to buckets[id]
- if id equals current pointer, print until null or end of bucket, increment pointer