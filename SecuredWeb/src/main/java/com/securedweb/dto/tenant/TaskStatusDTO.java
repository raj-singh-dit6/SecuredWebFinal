package com.securedweb.dto.tenant;

import java.util.Set;

import com.securedweb.model.tenant.Task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class TaskStatusDTO {

	private Integer id;
	private String status;
	private String statusColour;
	private String tenantId;
	private Set<Task> tasks;
}
