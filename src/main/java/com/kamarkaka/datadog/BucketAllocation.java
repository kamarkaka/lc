package com.kamarkaka.datadog;

import com.kamarkaka.common.Utilities;

import java.util.Arrays;

/***
 * 给一个bucket_size和bucket_width和一堆input数字。最后返回每个bucket里装了多少个。
 * 我就用一个int array数数，每次除一除就知道是哪个bucket的了。
 * 有一个edge case，最后一个bucekt是所有大于max的和。譬如我有10个bucekt，每个size是1，如果我的input里有90，这个90算在最后一个bucekt里。
 * put a list of integers into a list of buckets, with a specific bucket width, return counter per bucket
 * for example,
 *   a list of integers - [1,2,11,20,100]
 *   num of bucket - 3
 *   bucket width - 10
 *   0-9: 2 (1,2)
 *   10-19:1 (11)
 *   20+:2 (20, 100)
 *   最后一个bucket, 包含所有后面的数字
 */
public class BucketAllocation {
    public int[] buckets(int n, int width, int[] nums) {
        int[] buckets = new int[n];

        for (int num : nums) {
            int index = num / width;
            if (index > n) index = n - 1;
            buckets[index]++;
        }
        return buckets;
    }

    public static void main(String[] args) {
        BucketAllocation solution = new BucketAllocation();
        Utilities.print(solution.buckets(3, 10, new int[]{1,2,11,20,100}));
    }
}
