package com.program.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 违规记录实体类
 * 用于封装违规相关数据，对应需求文档中的违规记录信息
 */
public class ViolationRecord implements Serializable {
    /*private static int counter = 0;*/
    private int id;
    // 截图存储路径（100 以内个字符）
    private String screenshotPath;
    // 手势类别（违规手势之一，规定 5 和大拇指是违规类型等，实际按需求扩展）
    @SerializedName("classname")//用于json串解析映射
    private String gestureClass;
    // 记录创建时间
    private Date createTime;
    // 判定类型（自动 / 人工）
    private String judgeType;

    @SerializedName("probability")
    private Double probability; // 添加概率字段

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public String getJudgeType() {
        return judgeType;
    }

    public void setJudgeType(String judgeType) {
        this.judgeType = judgeType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGestureClass() {
        return gestureClass;
    }

    public void setGestureClass(String gestureClass) {
        this.gestureClass = gestureClass;
    }

    public String getScreenshotPath() {
        return screenshotPath;
    }

    public void setScreenshotPath(String screenshotPath) {
        this.screenshotPath = screenshotPath;
    }

    public ViolationRecord() {
    }

    public ViolationRecord(Double probability, String screenshotPath, String gestureClass, Date createTime, String judgeType) {
        this.probability = probability;
        this.screenshotPath = screenshotPath;
        this.gestureClass = gestureClass;
        this.createTime = createTime;
        this.judgeType = judgeType;
    }

    public ViolationRecord(String screenshotPath, String gestureClass, Date createTime, String judgeType) {
        this.screenshotPath = screenshotPath;
        this.gestureClass = gestureClass;
        this.createTime = createTime;
        this.judgeType = judgeType;
    }

    @Override
    public String toString() {
        return "ViolationRecord{" +
                "id=" + id +
                ", screenshotPath='" + screenshotPath + '\'' +
                ", gestureClass='" + gestureClass + '\'' +
                ", createTime=" + createTime +
                ", judgeType='" + judgeType + '\'' +
                ", probability=" + probability +
                '}';
    }
}