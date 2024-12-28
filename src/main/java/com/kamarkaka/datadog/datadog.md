coding

- LC0518 (coin change)
- coin找零，组合一定是能greedy的，问了什么样的组合不能greedy
- minimum number of coin change. 用backtrack找combination，找到了直接return result
- 给一个target sum，和[1,5,10,25]。问使用最少的数字个数来凑齐这个target sum。解答很简单：先用大数，再用小数，直到最后用1来凑齐。
- 找钢镚，简单greedy，1,2,5,25,找33，就从大到小，return 每个 钢镚的数 [3,0,1,1]
- 给一个bucket_size和bucket_width和一堆input数字。最后返回每个bucket里装了多少个。我就用一个int array数数，每次除一除就知道是哪个bucket的了。有一个edge case，最后一个bucekt是所有大于max的加和。譬如，我有10个bucekt，每个size是1，如果我的input里有90，这个90算在最后一个bucekt里。
- put a list of integers into a list of buckets, with a specific bucket width, return counter per bucket
  for example,
  a list of integers - [1,2,11,20, 100]
  num of bucket - 3
  bucket width - 10
  0-9: 2 (1,2)
  10-19:1 (11)
  20+:2 (20, 100)
  最后一个bucket, 包含所有后面的数字
- ['apple, facebook, google', 'banana, facebook', 'facebook, google, tesla', 'intuit, google, facebook']
  然后有一个 filter list， 根据 filter list 输出这些 Tags 的补集
  比如 filter by ['apple']那么 return ['facebook', 'google'] (只有第一个里面有 APPLE）
  比如 filter by ['facebook', 'google']那么 return ['apple', 'tesla','intuit']
  需要 high performance filter
- ['apple, facebook, google', 'banana, facebook', 'facebook, google, tesla', 'intuit, google, facebook']
  然后有一个 filter list， 根据 filter list 输出这些 Tags 的补集
  比如 filter by ['apple']那么 return ['facebook', 'google'] (只有第一个里面有 APPLE）
  比如 filter by ['facebook', 'google']那么 return ['apple', 'tesla','intuit']
  我是用hashmap 做Cache。 Follow up 是怎么更好的index 这个hashmap (Key 是啥，Value 是啥）。
- filter tag
- LC408 (Valid Word Abbreviation) 就是给一个word和一个pattern, pattern里有数字，如果看到数字就match word里的几个字符，这题有3 parts
1. 如果数字只是一个digit： word: datadog, pattern: d3dog -> match
2. 数字可以是多个digit: word: accessibility, pattern: a11y -> match
3. 加分项，可以escape数字，in which case escape掉的数字就要match word里面的数字
   word: datadog, pattern: d\3dog -> NO match
   word: d3dog, pattern: d\3dog -> match.（这part可以不用写就说思路就行，但是我前两part很快写完所以很快把这个也写完了，就这样加上前后聊天还是多出来15分钟左右，最后这轮提前结束了）
- encoded string, datadog -> d3dog
- File System，input structure都提供好了，其实就是个N-ary Tree，每个tree node要么是directory with children nodes，要么是file with size value。需要输出total file sizes。解答很简单：DFS，遇到directory就继续recursion，否则就increase的total size。
  follow up了一下：如果给一个inner directory的path string，如何输出total file sizes。其实也很简单：recursion method里面carry当前的path，和一个当前是否已经进入到了目标子树的flag。
- 给一个文件夹路径 返回文件夹的大小
- 求一个文件夹里所有file的size，有的文件是file，有的是folder，他们都是基于相同的base class。如果你用java的话，你可以像我一样，用instanceof来区分（面试官允许你google）。就是一个最简单的recursion
- 给一个file system，要你统计文件大小。基本Tree traversal
  home/
  |--- me/
  |. |--- foo.txt : 231
  |. |--- abs.txt : 443
  follow up:给你一个制定的dir，你去数一下里面文件的大小，比如 /home/me/
- 二维座标给间距插入missing点.
- 给一个list 里面有坐标，按间隙补齐缺失坐标,点和点之间是直线连接，缺失的点也必须在直线上
  for example, interval=5, interpolate missing point at x-coordinate with incremental of 5 (e.g. (0,y1), (5,y2), (10,y3)....
  input = [(0,10), (10,10),(20, -10)]
  output = [(0,10), (5,20),(10,10),(15,0) ,(20,-10)]
  (5,20) 在直线(0,10)-(10,10)上, (15,0)在直线(10,10)-(20,-10)上
- 给一个list 里面有坐标，按间隙补齐缺失坐标
  输入[(0,10), (5,20),(20, -10)]
  输出[(0,10), (5,20), (10,10),(15,0) ,(20,-10)]
  我的解法: linear scan，同时补齐
- linear scan补充缺失的datapoint 注意slope不是从峰值计算 是缺失的点两端计算slope
- LC0211 (trie)
- LC0986 (Interval List Intersections)

design
- design an application which will collect and store purchases from credit and debit cards and provide the user with insight into their spending habits
- 设计个系统可以监测互联网上出现自己的脸，出现了消息提醒
- 设计一个Flight Deal Notification Service，其中Flights Crawler是个外部API，但是需要你告诉它crawl哪个或哪几个cities，而且Deal Decider是个外部API，需要你把Flight Ticket输进去，会告诉你这个flight ticket是不是好的deal。此外Notification也是个提供好的API。
- flight ticket deals email notification system, 要求 1.不能发重复的deal 2.如果有新users加入且subscribe 了他想知道的目的地的deal, 之前发过的notification也需要发给他
- streaming website like youtube
