package com.example.demo.task.exception;


/**
 * The Class TaskExistsException.
 */
public class TaskExistsException extends TaskException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5781399950325339943L;

	/**
	 * Instantiates a new task exists exception.
	 *
	 * @param message the message
	 */
	public TaskExistsException(String message) {
		super(message);
		
	}

}
