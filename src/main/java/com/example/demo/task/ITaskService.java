package com.example.demo.task;

import java.util.List;

import com.example.demo.task.exception.TaskExistsException;
import com.example.demo.task.exception.TaskNotFoundException;

/**
 * The Interface ITaskService.
 */
public interface ITaskService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<Task> findAll();

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the task
	 * @throws TaskNotFoundException the task not found exception
	 */
	Task findById(long id) throws TaskNotFoundException;

	/**
	 * Save.
	 *
	 * @param task the task
	 * @return the task
	 * @throws TaskExistsException the task exists exception
	 */
	Task save(Task task) throws TaskExistsException;

	/**
	 * Update.
	 *
	 * @param task the task
	 * @throws TaskNotFoundException the task not found exception
	 */
	void update(Task task) throws TaskNotFoundException;

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @throws TaskNotFoundException the task not found exception
	 */
	void delete(long id) throws TaskNotFoundException;

	/**
	 * Delete all.
	 */
	void deleteAll();

}
