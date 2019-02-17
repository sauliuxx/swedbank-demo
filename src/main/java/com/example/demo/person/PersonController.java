package com.example.demo.person;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.group.Group;
import com.example.demo.group.IGroupService;
import com.example.demo.group.exception.GroupNotFoundException;
import com.example.demo.person.exception.PersonNotFoundException;

/**
 * The Class PersonController.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/persons")
public class PersonController {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(PersonController.class);

	/** The person service. */
	private IPersonService personService;

	/** The group service. */
	private IGroupService groupService;

	/**
	 * Instantiates a new person controller.
	 *
	 * @param personService the person service
	 * @param groupService  the group service
	 */
	@Autowired
	public PersonController(IPersonService personService, IGroupService groupService) {
		this.personService = personService;
		this.groupService = groupService;
	}

	/**
	 * Gets the person by pid.
	 *
	 * @param pid the pid
	 * @return the person by pid
	 */
	@GetMapping("{pid}")
	public ResponseEntity<Person> getPersonByPid(@PathVariable("pid") Long pid) {

		try {
			Person person = personService.getById(pid);
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} catch (PersonNotFoundException ex) {

			log.error("getPersonByPid", ex);
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 * Gets the persons.
	 *
	 * @return the persons
	 */
	@GetMapping
	public ResponseEntity<List<Person>> getPersons() {
		List<Person> list = personService.getAll();
		return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
	}

	/**
	 * Gets the person groups.
	 *
	 * @param pid the pid
	 * @return the person groups
	 */
	@GetMapping("{pid}/groups")
	public ResponseEntity<Collection<Group>> getPersonGroups(@PathVariable("pid") Long pid) {

		try {

			Person person = personService.getById(pid);

			return new ResponseEntity<Collection<Group>>(person.getGroups(), HttpStatus.OK);
		} catch (PersonNotFoundException ex) {
			log.error("getPersonGroups", ex);
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 * Sets the group.
	 *
	 * @param pid the pid
	 * @param id  the id
	 * @return the response entity
	 */
	@PatchMapping("{pid}/groups/{id}")
	public ResponseEntity<?> setGroup(@PathVariable("pid") long pid, @PathVariable("id") long id) {

		try {
			Person person = personService.getById(pid);
			Group group = groupService.findById(id);

			Set<Group> groups = person.getGroups();

			groups.add(group);
			person.setGroups(groups);

			personService.saveAndFlush(person);

			return ResponseEntity.ok().build();

		} catch (GroupNotFoundException | PersonNotFoundException ex) {
			log.error("setGroup", ex);
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("{pid}/groups/{id}")
	public ResponseEntity<?> removeFromGroup(@PathVariable("pid") long pid, @PathVariable("id") long id) {
		try {
			Person person = personService.getById(pid);
			Group group = groupService.findById(id);

			Set<Group> groups = person.getGroups();

			if (groups.contains(group)) {
				groups.remove(group);
				person.setGroups(groups);

				personService.saveAndFlush(person);
			}

			return ResponseEntity.ok().build();

		} catch (GroupNotFoundException | PersonNotFoundException ex) {
			log.error("setGroup", ex);
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Adds the person.
	 *
	 * @param person  the person
	 * @param builder the builder
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<Void> addPerson(@RequestBody Person person, UriComponentsBuilder builder) {
		boolean success = personService.save(person);
		if (!success) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/person/{id}").buildAndExpand(person.getPid()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * Update person.
	 *
	 * @param person the person
	 * @return the response entity
	 */
	@PutMapping
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
		personService.update(person);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	/**
	 * Delete person.
	 *
	 * @param pid the pid
	 * @return the response entity
	 */
	@DeleteMapping("{pid}")
	public ResponseEntity<Void> deletePerson(@PathVariable("pid") Long pid) {
		try {
			personService.delete(pid);
			return ResponseEntity.ok().build();
		} catch (PersonNotFoundException e) {
			log.error("deletePerson", e);
			return ResponseEntity.notFound().build();
		}

	}

}
