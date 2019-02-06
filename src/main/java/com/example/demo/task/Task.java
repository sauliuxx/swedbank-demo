package com.example.demo.task;

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

	@Id
	@GeneratedValue
	private long id;
	
}
