package com.example.demo.person;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.group.Group;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * The Class Person.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "person")
public class Person implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2268562508199413550L;

	/** The pid. */
	@Id
	@NotNull
	private long pid;

	/** The name. */
	@NotNull
	@NotBlank
	@Column(name = "first_name")
	private String name;

	/** The middle name. */
	@Column(name = "middle_name")
	private String middleName;

	/** The surname. */
	@NotNull
	@NotBlank
	@Column(name = "last_name")
	private String surname;

	/** The email. */
	private String email;

	/** The phone. */
	private String phone;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "person_group", joinColumns = @JoinColumn(name = "pid", referencedColumnName = "pid"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
	@ToString.Exclude
	@JsonIgnore
	@EqualsAndHashCode.Exclude
	private Set<Group> groups;

	public Person(long pid, @NonNull String name, String middleName, @NonNull String surname, String email,
			String phone, Set<Group> groups) {
		super();
		this.pid = pid;
		this.name = name;
		this.middleName = middleName;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.groups = groups;
	}

}
