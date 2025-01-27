coding
- Bucket allocation
- Buffered Writer
- Coin change (greedy)
- Count repititions
- File size
- File system implement rm -rf
- Logs and queries

- buffer file 这题需要自己写一个MockFile class 这样才可以测试
  follow up 1: 如果file.write 每次只能写最多n个bytes到disk -> flush function 里面加一个while loop
  follow up 2: 怎么ensure thread safe -> lock
- buffer write, follow up: what happen if multithread write same file
- 套了一层FileWriter的外皮，其实就是有一个空的固定size的byte array，写一个method可以往里边从左到右append bytes，再写一个method可以flush
  清空byte array里面的bytes。这个flush method可以直接被call，或者当append operation时候内部的byte array满了，就需要内部call flush之
  后再resume append operation。follow up就是每次flush operation不会全部清空所有的existing bytes，而是左边的一部分，至于具体多少呢，
  他会以数字的形式换会告诉你，你需要handle这个情况，知道完成flush的使命。
- write to a file with a buffer，followup是write可能部分成功（类似pwrite）
- Design a circular buffer/Queue with push(), pop(), peek(), size() functions
  这题注意用2个point， front， rear 然后他们移动的时候用 （front + 1）% capacity 到下一个位置防止array out of index
  这个题我pop的时候忘了用 （front + 1）% capacity 而是惯性的用了 front++ 导致有个testcase 没过
- 地里高频的Buffered File，问了follow up，如果写进buffer file的时候不一定成功怎么办，就调整了代码加一个while loop一直retry到全部成功写进
  file再flush buffer
- implement a buffered writer: 这个writer存在一个buffer 和可设置的最大buffer size，这里的buffer工作方式是
  1， 读取的数据先存在buffer里面不写入硬盘
  2， 当buffer里的数据量达到buffer size的时候，一次性清空所有buffer内的数据到硬盘中。如此往复。
- Buffered File
- Given a File class, implement a bufferedfile to simulate file writer with buffer
  follow up: optimize buffer

- 给出无序的list of points (tags, timestamp, int value), 例如
  [{"env:dev", 0, 444}, {"env:dev", 5, 300}, {"env:dev", 1, 300}, ...]
  实现query( String tag, int windowSize), 返回某一个tag, 在给定的WINDOW SIZE内， value的SUM
- sliding windows evaluate sum of last k value, filtered by tag, sorted by timestamp ascendingly
  input = [{tag: ["env:prod", "prod1"}, timestamp: [1, 10, 3, 100, 2], value: [-1, 10, -10, 100, 2]}, k = 2, tag = "prod1"
  Output
  Step 1: filter
  Step 2: sorted = [1, 2, 3, 10, 100], value = [-1, 2, -10, 10, 100]
  Step 3: output = [1, -8, 0, 110]

- 给一个target sum，和[1,5,10,25]。问使用最少的数字个数来凑齐这个target sum。解答很简单：先用大数，再用小数，直到最后用1来凑齐。
- coin找零，组合一定是能greedy的，问了什么样的组合不能greedy
- 换硬币，刷题的时候以为要用dp。结果看到题发现只用普通的遍历一遍就好了。follow up：edge case

- 在string 里面找最长的substring，里面不能有重复的字母
- 题目是飞不同城市最大化holiday天数，比利口上的标签题简单。但follow-up把我卡住了，要求在最大化同时最少化飞行次数，有个edge case一直过不去：比如
  w1: [2,0,2]
  w2: [3,0,3]
  w3: [1,0,1]
  w4: [0,0,2]
  三个坐标分别代表三个城市在这周的假日，所以需要从第一周开始就待在第三个城市。其实解法不难，但当时时间紧卡住了，小哥说没关系。我以为挂在这一轮。


system design: 
- flight ticket price notification system follow-up，关于为什么一个地方用DB存而不直接用queue。可能没答出t他想听到的
- 设计一个Flight Deal Notification Service，其中Flights Crawler是个外部API，但是需要你告诉它crawl哪个或哪几个cities，而且Deal
  Decider是个外部API，需要你把Flight Ticket输进去，会告诉你‌‌这个flight ticket是不是好的deal。此外Notification也是个提供好的API。
- 系统设计是设计打折机票通知，蛮多non functional的要求，感觉能讲完就很不容易的，有被问到数据库的设计。
- flight ticket deals email notification system, 要求 1.不能发重复的deal 2.如果有新users加入且subscribe了他想知道的目的地的deal,
  之前发过的notification也需要发给他

- design youtube, 對話要清楚解釋design原因,比如為什麼要RESTful，為什麼不用web socket等等,不要直接跳進去用一個tool/technology
- streaming website like youtube

- mint.com
- design mint，followup是怎么monitor这个系统，这轮比较轻松
- 就是设计一个类似能看到花销的系统（可以下载个mint或者花销管理软件感受一下），你需要从外部的银行拿到用户的花费条目，不能访问bank太多次数。 同时内
  部要能够生产report。 最后为了一个如何灾备的问题，比如一个data center都坏了，如何引导用户流量去其他数据中心
- design an application which will collect and store purchases from credit and debit cards and provide the user with
  insight into their spending habits

- 设计个系统可以监测互联网上出现自己的脸，出现了消息提醒
- 设计一个系统来识别用户的肖像是否出现在了ins的post上，如果识别到了要通知用户。假定：有API可以接受来自ins的event包含图片信息，有现成的算法来做
  面部识别。
- A system to detect if someone shares your photo on Instagram. Assuming you have a Instagram post firehose, and ML
  model for facial detection.
- design an alert system notify user when issue is detected

- design a web crawler system, how to scale, how to reduce resource usage.

