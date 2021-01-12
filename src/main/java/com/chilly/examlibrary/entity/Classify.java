package com.chilly.examlibrary.entity;

/**
 * 分类
 * @auther ChillyLin
 * @create 2020/12/21
 */
public class Classify {
    private Long classify_id;
    private String classify_name;

    public Long getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(Long classify_id) {
        this.classify_id = classify_id;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "classify_id=" + classify_id +
                ", classify_name='" + classify_name + '\'' +
                '}';
    }
}
