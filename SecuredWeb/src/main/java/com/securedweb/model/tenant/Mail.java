package com.securedweb.model.tenant;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class Mail implements Serializable{
	
	private String from;
    private String to;
    private String subject;
    private String body;
	private Map<String, Object> model;

}
