package com.securedweb.model.master;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
public class TenantDbDataSource implements Serializable{

	
	@Id
	@Column( unique = true, nullable = false)
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "foreign",
	parameters = @Parameter(name = "property", value = "tenant"))
	private Integer tenantId;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Tenant tenant;
	
	@NotEmpty
	@Column(nullable=false)
	private String dbName;
	
	@NotEmpty
	@Column( nullable=false)
	private String dbUser;
	
	@NotEmpty
	@Column( nullable=false)
	private String dbPassword;
	
	@NotEmpty
	@Column( nullable=false)
	private String dbPort;
	
	@NotEmpty
	@Column( nullable=false)
	private String dbHost;
	
	@NotEmpty
	@Column(nullable=false)
	private String dbDriver;
	
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
}
