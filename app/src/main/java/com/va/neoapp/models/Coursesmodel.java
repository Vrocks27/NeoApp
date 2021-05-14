package com.va.neoapp.models;


import java.io.Serializable;

public class Coursesmodel implements Serializable {
    String courseName;
    String courseType;
    String coursePeriod;
    String courseTimePeriod;
    String intakePeriod;
    String eligibilityCriteria;
    String courseDescription;

    public Coursesmodel(String courseName, String courseType, String coursePeriod, String courseTimePeriod, String intakePeriod, String eligibilityCriteria, String courseDescription) {
        this.courseName = courseName;
        this.courseType = courseType;
        this.coursePeriod = coursePeriod;
        this.courseTimePeriod = courseTimePeriod;
        this.intakePeriod = intakePeriod;
        this.eligibilityCriteria = eligibilityCriteria;
        this.courseDescription = courseDescription;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCoursePeriod() {
        return coursePeriod;
    }

    public void setCoursePeriod(String coursePeriod) {
        this.coursePeriod = coursePeriod;
    }

    public String getCourseTimePeriod() {
        return courseTimePeriod;
    }

    public void setCourseTimePeriod(String courseTimePeriod) {
        this.courseTimePeriod = courseTimePeriod;
    }

    public String getIntakePeriod() {
        return intakePeriod;
    }

    public void setIntakePeriod(String intakePeriod) {
        this.intakePeriod = intakePeriod;
    }

    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public void setEligibilityCriteria(String eligibilityCriteria) {
        this.eligibilityCriteria = eligibilityCriteria;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

}
