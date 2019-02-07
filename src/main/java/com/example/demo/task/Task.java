package com.example.demo.task;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@GeneratedValue
	private long id;

	/** The short description. */
	@Column(name = "short_desc")
	private String shortDesc;

	/** The details. */
	private String details;

	/** The start date. */
	@Column(name = "start_date")
	private Date startDate;

	/** The end date. */
	@Column(name = "end_date")
	private Date endDate;

	/**
	 * Instantiates a new task.
	 *
	 * @param id        the id
	 * @param shortDesc the short desc
	 * @param details   the details
	 * @param startDate the start date
	 * @param endDate   the end date
	 */
	public Task(long id, String shortDesc, String details, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.shortDesc = shortDesc;
		this.details = details;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
