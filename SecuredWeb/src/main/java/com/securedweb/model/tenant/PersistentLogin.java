package com.securedweb.model.tenant;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class PersistentLogin implements Serializable{

	@Id
	private String series;
	
	@Column(unique=true, nullable=false)
	private String username;
	
	@Column(unique=true, nullable=false)
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUsed;

}
