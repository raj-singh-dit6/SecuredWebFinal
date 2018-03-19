package com.securedweb.model.tenant;

import java.util.Map;

import javax.mail.Address;

public class Mail {
	private String from;
    private String to;
    private String subject;
    private String body;

	private Map<String, Object> model;

    public Mail() {

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
    
    public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
