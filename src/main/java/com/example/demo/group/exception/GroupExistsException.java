package com.example.demo.group.exception;

/**
 * The Class GroupExistsException.
 */
public class GroupExistsException extends GroupException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -908845642664913178L;

	/**
	 * Instantiates a new group exists exception.
	 *
	 * @param message the message
	 */
	public GroupExistsException(String message) {
		super(message);
	}

}
