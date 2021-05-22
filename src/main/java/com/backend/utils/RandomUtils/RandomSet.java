package com.backend.utils.RandomUtils;

import java.util.HashSet;

public class RandomSet {
    public static void randomSet (int max, int n, HashSet<Integer> set) {
        if (n >= max)
            return;
        for (int i = 0; i < n; i++) {
            int num = (int) (Math.random() * max);
            set.add(num);
        }
        int setSize = set.size();
        if (setSize < n) {
            randomSet(max, n - setSize, set);
        }
    }
}
