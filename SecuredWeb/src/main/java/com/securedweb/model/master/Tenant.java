package com.securedweb.model.master;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class Tenant implements Serializable {

	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Integer tenantId;
	
	@Column( nullable=false)
	private String tenantName;
	

	@OneToOne(mappedBy = "tenant")
	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private TenantDbDataSource tenantDbdataSource;
	
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
}
