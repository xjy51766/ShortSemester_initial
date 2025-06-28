package com.program.bean;

import java.util.List;

/**
 * 用于封装返回给前端的结果结构
 */
public class RecognitionResult {
    private int result_num;
    private List<ResultItem> result;

    public RecognitionResult() {
    }

    public RecognitionResult(int result_num, List<ResultItem> result) {
        this.result_num = result_num;
        this.result = result;
    }

    public int getResult_num() {
        return result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public List<ResultItem> getResult() {
        return result;
    }

    public void setResult(List<ResultItem> result) {
        this.result = result;
    }
}