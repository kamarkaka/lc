(843) Guess the Word
- pick any word to start with
- get match count from that word
- use this word to check against other words
- if a word has the same number of characters at the same position as this word, leave it there
- if has less, remove from set as it cannot be the answer

(1423) Maximum Points You Can Obtain from Cards
- pick all cards from beginning
- return one card at a time, replace with one from rear, update score
- return max

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
