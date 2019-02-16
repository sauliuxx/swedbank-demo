package com.example.demo.person.exception;

/**
 * The Class PersonNotFoundException.
 */
public class PersonNotFoundException extends PersonException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6141904486408169266L;

	/**
	 * Instantiates a new person not found exception.
	 *
	 * @param pid the pid
	 */
	public PersonNotFoundException(long pid) {
		super("Can't find person with " + pid);

	}

}
