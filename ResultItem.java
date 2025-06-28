package com.program.bean;

/**
 * 用于封装返回给前端的单个识别结果项
 */
public class ResultItem {
    private int id;
    private String classname;
    private String url;// 关键字段：违规/转人工时为图片路径，否则为空

    public ResultItem() {
    }

    public ResultItem(int id, String classname, String url) {
        this.id = id;
        this.classname = classname;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}