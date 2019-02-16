package com.example.demo.group.exception;

public class GroupNotFoundException extends GroupException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GroupNotFoundException(Long id) {
		super("Could not find group " + id);
	}

}
