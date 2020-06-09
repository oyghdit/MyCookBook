package com.study.mycookbook.category.beans;

import java.util.List;

public class Category {
    String resultcode;
    String reason;
    List<Category1> result;

    public Category() {

    }

    public Category(String resultcode, String reason, List<Category1> result) {
        this.resultcode = resultcode;
        this.reason = reason;
        this.result = result;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Category1> getResult() {
        return result;
    }

    public void setResult(List<Category1> result) {
        this.result = result;
    }
}
