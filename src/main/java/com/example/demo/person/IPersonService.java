package com.example.demo.person;

import java.util.List;


/**
 * The Interface IPersonService.
 */
public interface IPersonService {

	/**
	 * Gets the all persons.
	 *
	 * @return the all persons
	 */
	List<Person> getAllPersons();

	/**
	 * Gets the person id.
	 *
	 * @param pid the pid
	 * @return the person id
	 */
	Person getPersonId(long pid);

	/**
	 * Adds the person.
	 *
	 * @param person the person
	 * @return true, if successful
	 */
	boolean addPerson(Person person);

	/**
	 * Update person.
	 *
	 * @param person the person
	 */
	void updatePerson(Person person);

	/**
	 * Delete person.
	 *
	 * @param pid the pid
	 */
	void deletePerson(long pid);
}
