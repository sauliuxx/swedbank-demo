package com.example.demo.person.exception;

/**
 * The Class PersonExistsException.
 */
public class PersonExistsException extends PersonException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7523365597708364100L;

	/**
	 * Instantiates a new person exists exception.
	 *
	 * @param pid the pid
	 */
	public PersonExistsException(long pid) {
		super("Person with " + pid + " exists!");

	}

}
