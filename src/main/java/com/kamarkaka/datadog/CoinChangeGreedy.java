package com.kamarkaka.datadog;

/***
 * LC0518 (coin change)
 * coin找零，组合一定是能greedy的，问了什么样的组合不能greedy (1,3,4 -> 3+3 vs. 4+1+1)
 * minimum number of coin change. 用backtrack找combination，找到了直接return result
 * 给一个target sum，和[1,5,10,25]。问使用最少的数字个数来凑齐这个target sum。解答很简单：先用大数，再用小数，直到最后用1来凑齐。
 * 找钢镚，简单greedy，1,2,5,25,找33，就从大到小，return 每个 钢镚的数 [3,0,1,1]
 */
public class CoinChangeGreedy {
    public int numOfCoins(int[] coins, int target) {
        int count = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            if (target < coins[i]) continue;

            count += target / coins[i];
            target = target % coins[i];
            if (target == 0) return count;
        }
        return count;
    }

    public static void main(String[] args) {
        CoinChangeGreedy solution = new CoinChangeGreedy();
        System.out.println(solution.numOfCoins(new int[]{1,5,10,25}, 33));
    }
}
