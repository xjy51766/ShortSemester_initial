package com.program.rules;

import com.program.bean.ViolationRecord;

import java.util.Arrays;
import java.util.List;

public class ViolationRules {
    // 定义违规手势列表
    private static final List<String> VIOLATION_GESTURES = Arrays.asList(
            "Heart_single", "Thumb_up", "Five" // 根据实际需求添加
    );

    // 定义概率阈值
    private static final double PROBABILITY_THRESHOLD = 0.75;

    // 根据模型返回的信息和我们定义的违规手势列表，来判定手势是否违规（不关心probability是否超过0.75） 这里违规意思是classname是否违规
    public static boolean isViolation(ViolationRecord record) {
        return record != null
                && VIOLATION_GESTURES.contains(record.getGestureClass());
    }
    // 判定已经违规手势的概率值，如果小于0.75，转人工判断
    public static boolean isOverStandard(ViolationRecord record) {
        return record != null
                && record.getProbability() != null
                && record.getProbability() >= PROBABILITY_THRESHOLD;
    }






    /*// 判定违规，应该记入数据库
    public static boolean isViolation(ViolationRecord record) {
        return record != null
                && record.getProbability() != null
                && record.getProbability() >= PROBABILITY_THRESHOLD
                && VIOLATION_GESTURES.contains(record.getGestureClass());
    }
    //低概率违规，应该转人工
    public static boolean isLowViolation(ViolationRecord record) {
        return record != null
                && record.getProbability() != null
                && record.getProbability() < PROBABILITY_THRESHOLD
                && VIOLATION_GESTURES.contains(record.getGestureClass());
    }*/


}