package com.backend.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomList {
    public static List<Integer> randomList(Integer min, Integer max, Integer nums) {
        List<Integer> list = new ArrayList<>();

        Random r = new Random();
        while (list.size() != nums) {
            Integer num = r.nextInt(max - min) + min;
            if(!list.contains(num)) {
                list.add(num);
            }
        }
        return list;
    }
}
