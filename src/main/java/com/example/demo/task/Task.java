package com.example.demo.task;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Task.
 */
@Entity
@NoArgsConstructor
@Table(name = "task")
@Data
public class Task {

	/** The id. */
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@SequenceGenerator(name = "task_generator", sequenceName = "task_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_generator")
	private long id;

	/** The short description. */
	@NotNull
	@NotBlank
	@Column(name = "short_desc")
	private String shortDesc;

	/** The details. */
	private String details;

	/** The start date. */
	@NotNull
	@NotBlank
	@Column(name = "start_date")
	private Date startDate;

	/** The end date. */
	@Column(name = "end_date")
	private Date endDate;
	
	

	/**
	 * Instantiates a new task.
	 *
	 * @param shortDesc the short desc
	 * @param details   the details
	 * @param startDate the start date
	 * @param endDate   the end date
	 */
	public Task(String shortDesc, String details, Date startDate, Date endDate) {
		super();
		this.shortDesc = shortDesc;
		this.details = details;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
