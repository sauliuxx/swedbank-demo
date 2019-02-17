package com.example.demo.task;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.group.Group;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Vilnius")
	@Column(name = "start_date")
	private Date startDate;

	/** The end date. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Vilnius")
	@Column(name = "end_date")
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "group_id", nullable = true)
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Group group;

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
