package com.example.demo.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;


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

	/**
	 * Adds the person.
	 *
	 * @param person  the person
	 * @param builder the builder
	 * @return the response entity
	 */
	@PostMapping("person")
	public ResponseEntity<Void> addPerson(@RequestBody Person person, UriComponentsBuilder builder) {
		boolean success = personService.addPerson(person);
		if (!success) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/person/{id}").buildAndExpand(person.getPid()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * Update article.
	 *
	 * @param person the person
	 * @return the response entity
	 */
	@PutMapping("person")
	public ResponseEntity<Person> updateArticle(@RequestBody Person person) {
		personService.updatePerson(person);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	/**
	 * Delete article.
	 *
	 * @param pid the pid
	 * @return the response entity
	 */
	@DeleteMapping("person/{pid}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("pid") Long pid) {
		personService.deletePerson(pid);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
