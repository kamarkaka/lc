// servers=[1,3,1,2]
// tasks=[1,10,100,1000,13,3]

// rules:
// 1. free server
// 2. least weight, same weight => least index
// 3. no free server -> wait for the 1st free one