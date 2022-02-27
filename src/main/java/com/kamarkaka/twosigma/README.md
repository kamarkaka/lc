[43. Multiply Strings](https://leetcode.com/problems/multiply-strings/)
- sum the products from all pairs of digits
- e.g 23*45 can be solved by calculating:
- 23*5+23*40
- (3*5+20*5)+(3*4+20*4)*10

[123. Best Time to Buy and Sell Stock III](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/)
- maximum profit in at most 2 transactions
- 1. three pass dp
- one pass from left to right, memorize max profit from left
- one pass from right to left, memorize max profit from right
- one final pass to look at profit from left and right, find max sum
- 2. one pass dp
- keep 4 variables: t1cost, t1profit, t2cost, t2profit
- t1cost = min(t1cost, price)
- t1profit = max(t1profit, price-t1cost)
- t2cost = min(t2cost, price-t1profit)
- t2profit = max(t2profit, price-t2profit)

[146. LRU Cache](https://leetcode.com/problems/lru-cache/)
- maintain a hash map with (key, node)
- maintain a linked list, keep track of head and tail
- for gets, move the node to tail
- for puts, evict head if reaches capacity, then insert to tail

[289. Game of Life](https://leetcode.com/problems/game-of-life/)
- for each cell, find its number of neighbors
- update it according to its neighbor count
- do not update right away, note how it will be updated until the whole board is traversed
- update only after all cells are looked at
- set the cell to some other value instead of 0 or 1 to indicate if it lives/dies after this round

[460. LFU Cache](https://leetcode.com/problems/lfu-cache/)
- keyMap with key, node
- freqMap with freq, doubly linked list of node
- minFreq
- update when get and put(update only)
   - remove node from current doubly linked list
   - increment freq and add to appropriate list
   - search freqMap for the next minFreq

[1013. Partition Array Into Three Parts With Equal Sum](https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/)
- two pass
- first pass to calculate total sum and the sum of one of the three parts
- second pass to see if there are 3 parts with each part sum equals to calculated sum

