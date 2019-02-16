package com.example.demo.person;

import java.util.List;

import com.example.demo.person.exception.PersonNotFoundException;

/**
 * The Interface IPersonService.
 */
public interface IPersonService {

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<Person> getAll();

	/**
	 * Gets the by id.
	 *
	 * @param pid the pid
	 * @return the by id
	 * @throws PersonNotFoundException the person not found exception
	 */
	Person getById(long pid) throws PersonNotFoundException;

	/**
	 * Save.
	 *
	 * @param person the person
	 * @return true, if successful
	 */
	boolean save(Person person);

	/**
	 * Update.
	 *
	 * @param person the person
	 */
	void update(Person person);

	/**
	 * Delete.
	 *
	 * @param pid the pid
	 */
	void delete(long pid) throws PersonNotFoundException;

	/**
	 * Delete all.
	 */
	void deleteAll();

	/**
	 * Save and flush.
	 *
	 * @param person the person
	 */
	void saveAndFlush(Person person);
}
