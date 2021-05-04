package com.va.neoapp.models;

public class TermsConditions {

    private String condition, sub_condition;

    public TermsConditions(String condition, String sub_condition) {
        this.condition = condition;
        this.sub_condition = sub_condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSub_condition() {
        return sub_condition;
    }

    public void setSub_condition(String sub_condition) {
        this.sub_condition = sub_condition;
    }
}
