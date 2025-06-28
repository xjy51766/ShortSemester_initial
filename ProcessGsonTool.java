package com.program.tools;

import com.google.gson.reflect.TypeToken;
import com.program.bean.ViolationRecord;

import java.lang.reflect.Type;
import java.util.List;

public class ProcessGsonTool {
    // 辅助方法：从完整JSON中提取result数组部分
    public String extractResultArray(String fullJson) {
        int start = fullJson.indexOf("[");
        int end = fullJson.lastIndexOf("]") + 1;
        return (start >= 0 && end > start) ? fullJson.substring(start, end) : null;
    }

    // 解析 JSON 为 ViolationRecord 列表     ViolationRecordServiceImpl里没有调用，又写了一遍
    public List<ViolationRecord> parseApiResponse(String apiResponse) {
        String resultJson = extractResultArray(apiResponse);
        Type type = new TypeToken<List<ViolationRecord>>() {
        }.getType();
        return GsonUtils.fromJson(resultJson, type);
    }
}