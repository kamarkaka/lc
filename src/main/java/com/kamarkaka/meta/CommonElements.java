package com.kamarkaka.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CommonElements {
    public List<Integer> commonElements(List<Integer> A, List<Integer> B, List<Integer> C) {
        int i = 0, j = 0, k = 0;
        List<Integer> result = new ArrayList<>();
        while (i < A.size() && j < B.size() && k < C.size()) {
            if (A.get(i) == B.get(j) && B.get(j) == C.get(k)) {
                result.add(A.get(i));
                i++; j++; k++;
            } else if (A.get(i) < B.get(j)) {
                i++;
            } else if (B.get(j) < C.get(k)) {
                j++;
            } else {
                k++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CommonElements solution = new CommonElements();
        System.out.println(solution.commonElements(List.of(1,5,5,10,20,30), List.of(5,5,13,15,20), List.of(5,5,20)));
    }
}
