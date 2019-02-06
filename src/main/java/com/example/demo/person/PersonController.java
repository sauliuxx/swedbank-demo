package com.example.demo.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class PersonController.
 */
@Controller
@RequestMapping("api")
public class PersonController {

	/** The person service. */
	private IPersonService personService;

	/**
	 * Instantiates a new person controller.
	 *
	 * @param personService the person service
	 */
	@Autowired
	public PersonController(IPersonService personService) {
		this.personService = personService;
	}

	/**
	 * Gets the person by pid.
	 *
	 * @param pid the pid
	 * @return the person by pid
	 */
	@GetMapping("person/{pid}")
	public ResponseEntity<Person> getPersonByPid(@PathVariable("pid") Long pid) {

		Person person = personService.getPersonId(pid);
		return new ResponseEntity<Person>(person, HttpStatus.OK);

	}

	/**
	 * Gets the persons.
	 *
	 * @return the persons
	 */
	@GetMapping("persons")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Person>> getPersons() {
		List<Person> list = personService.getAllPersons();
		return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
	}
}
