package com.kamarkaka.datadog;

import com.kamarkaka.common.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/***
 * 二维座标给间距插入missing点.
 * 给一个list，里面有坐标，按间隙补齐缺失坐标，点和点之间是直线连接，缺失的点也必须在直线上
 * For example, interval=5, interpolate missing point at x-coordinate with incremental of 5
 * (e.g. (0,y1), (5,y2), (10,y3), ...)
 * input = [(0,10), (5,20),(20, -10)]
 * output = [(0,10), (5,20), (10,10),(15,0) ,(20,-10)]
 * (10,10) 在直线(5,20)-(20,-10)上, (15,0)在直线(10,10)-(20,-10)上
 * 我的解法: linear scan，同时补齐
 * linear scan补充缺失的datapoint 注意slope不是从峰值计算 是缺失的点两端计算slope
 */
public class MissingPoints {
    List<int[]> interpolate(List<int[]> points) {
        List<int[]> result = new ArrayList<>();

        points.sort(Comparator.comparingInt(p -> p[0]));
        int index = 0;
        int currX = points.getFirst()[0];
        while (index < points.size()) {
            int[] point = points.get(index);
            if (point[0] == currX) {
                result.add(point);
                index++;
            } else {
                int[] prevPoint = points.get(index - 1);
                int dy = point[1] - prevPoint[1];
                int dx = point[0] - prevPoint[0];

                int[] currPoint = new int[2];
                currPoint[0] = currX;
                currPoint[1] = prevPoint[1] + (currX - prevPoint[0]) * dy / dx;
                result.add(currPoint);
            }
            currX += 5;
        }

        return result;
    }

    public static void main(String[] args) {
        MissingPoints solution = new MissingPoints();
        List<int[]> result = solution.interpolate(Arrays.asList(
                new int[]{0,10},
                new int[]{5,20},
                new int[]{20,-10}));
        for (int[] p : result) {
            Utilities.print(p);
        }
    }
}
