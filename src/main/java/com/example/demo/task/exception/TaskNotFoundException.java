package com.example.demo.task.exception;


/**
 * The Class TaskNotFoundException.
 */
public class TaskNotFoundException extends TaskException {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7464618626072594609L;

	/**
	 * Instantiates a new task not found exception.
	 *
	 * @param id the id
	 */
	public TaskNotFoundException(long id) {
		super("Can't find task with " + id);
		
	}

}
