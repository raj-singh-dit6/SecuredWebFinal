package com.securedweb.model.tenant;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Task implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name="projectId", nullable=false)
	private Project project;
	
	@Column(nullable=false)
	private String name;

	private String description;
	
	private Date statusSetDate;
	
	@ManyToOne
	@JoinColumn(name="taskStatusId", nullable=false)
	private TaskStatus taskStatus;

	@JsonIgnore
	@OneToMany(mappedBy = "task")
	private Set<UserTask> userTasks = new HashSet<UserTask>();
	
	@NotEmpty
	@Column(nullable=false)
	private String tenantId;
	

	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
}
