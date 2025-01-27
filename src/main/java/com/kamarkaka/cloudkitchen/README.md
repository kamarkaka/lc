top k largest in moving window of size m in an array

Coding。但是奇葩的是不用求写代码，就是讲思路，问复杂度，不断给follow up。
You are given a static log file containing billions of entries. Each entry contains a timestamp and the name of a food
order. The entries in the log file appear in order of increasing timestamp. Design a method getCommon(k) to determine
the k most common food orders found in the log file.
Hamburger 1595268625
Salad     1595268626
Hot Dog   1595268627
Hamburger 1595268628
...


设计一个类似S3的blob store
Parse log，就是一个log file，里面一堆entry表示一些order的信息，让实现一个方法返回最受欢迎的topK orders。中规中矩，没啥特别的。
Parse menu。就是一个stream，里面带着某个饭馆menu里的item。item可以是分类，菜，topping之类的，每个里面还带着彼此的关联。让degisn一套classes可以把这个menu表示出来，并且还能再serialize back to steam。
