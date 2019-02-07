package com.example.demo.person;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * The Class Group.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "\"group\"")
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7622569973660694309L;

	/** The id. */
	@Id
	@GeneratedValue
	private long id;

	/** The name. */
	private @NonNull String name;

	/** The details. */
	private String details;

	/** The persons. */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "person_group", joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pid", referencedColumnName = "pid"))
	@ToString.Exclude @EqualsAndHashCode.Exclude private Set<Person> persons;

	/**
	 * Instantiates a new group.
	 *
	 * @param id      the id
	 * @param name    the name
	 * @param details the details
	 * @param persons the persons
	 */
	public Group(long id, @NonNull String name, String details, Set<Person> persons) {
		super();
		this.id = id;
		this.name = name;
		this.details = details;
		this.persons = persons;
	}

}
