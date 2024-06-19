package com.smart.model;

public class EmailRequest {
	private String to;
    private String subject;
    private String msg;

    public EmailRequest(String to, String subject, String msg) {
        this.to = to;
        this.subject = subject;
        this.msg = msg;
    }

    public EmailRequest() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
