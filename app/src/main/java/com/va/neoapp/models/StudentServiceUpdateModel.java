package com.va.neoapp.models;

public class StudentServiceUpdateModel {
    String service;
    String serviceAnswer;

    public StudentServiceUpdateModel(String service, String serviceAnswer) {
        this.service = service;
        this.serviceAnswer = serviceAnswer;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceAnswer() {
        return serviceAnswer;
    }

    public void setServiceAnswer(String serviceAnswer) {
        this.serviceAnswer = serviceAnswer;
    }
}
