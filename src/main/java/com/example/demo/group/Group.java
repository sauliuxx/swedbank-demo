package com.example.demo.group;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private static final long serialVersionUID = 5151443368714381887L;

	/** The id. */
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@SequenceGenerator(name = "group_generator", sequenceName = "group_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_generator")
	private long id;

	/** The name. */
	private @NonNull String name;

	/** The details. */
	private String details;

	/** The persons. */
	@ManyToMany(mappedBy = "groups")
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Person> persons;

	/**
	 * Instantiates a new group.
	 *
	 * @param name    the name
	 * @param details the details
	 * @param persons the persons
	 */
	public Group(@NonNull String name, String details, Set<Person> persons) {
		super();
		this.name = name;
		this.details = details;
		this.persons = persons;
	}

}
