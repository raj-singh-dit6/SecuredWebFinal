package com.securedweb.model.master;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="TENANT")
public class Tenant implements Serializable {

	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TENANT_ID", unique = true, nullable = false)
	private Integer tenantId;
	
	@Column(name="TENANT_NAME", nullable=false)
	private String tenantName;
	

	@OneToOne(mappedBy = "tenant")
	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private TenantDBDataSource tenantDBdataSource;
	
	@Column(name="CREATED_ON")
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	@Column(name="UPDATED_ON")
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	
	public TenantDBDataSource getTenantDBdataSource() {
		return tenantDBdataSource;
	}

	public void setTenantDBdataSource(TenantDBDataSource tenantDBdataSource) {
		this.tenantDBdataSource = tenantDBdataSource;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
}
