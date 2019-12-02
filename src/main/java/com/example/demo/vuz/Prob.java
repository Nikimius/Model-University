package com.example.demo.vuz;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Prob {
    public static void main(String[] args) {
        Map<String, Integer> dto = new HashMap<>();
        dto.put("afdsaf", 24);
        dto.put("afdsaf1", 1224);
        dto.put("afdsaf2", 24324);
        dto.put("afdsaf3", 24);
        dto.put("afdsaf4", 124);
        dto.put("afdsaf5", 324);
        dto.put("afdsaf6", 424);
        dto.put("afdsaf7", 524);
        dto.put("afdsaf8", 724);

        if (dto.containsKey("afdsaf")) {
            Integer scheduleId = dto.get("afdsaf");
            //System.out.println(scheduleId);
        }

        //System.out.println(dto.keySet());
        Set<Map.Entry<String, Integer>> newSet = dto.entrySet();
        System.out.println(newSet);
    }
}
