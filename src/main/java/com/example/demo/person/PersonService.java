package com.example.demo.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class PersonService.
 */
@Service
public class PersonService implements IPersonService {

	/** The person repository. */

	private PersonRespository personRepository;

	@Autowired
	public PersonService(PersonRespository personRepository) {
		this.personRepository = personRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.person.IPersonService#getAllPersons()
	 */
	@Override
	public List<Person> getAllPersons() {
		List<Person> list = new ArrayList<Person>();
		personRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.person.IPersonService#getPersonId(long)
	 */
	@Override
	public Person getPersonId(final long pid) {
		Person person = personRepository.findById(pid).get();
		return person;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demo.person.IPersonService#addPerson(com.example.demo.person.
	 * Person)
	 */
	@Override
	public boolean addPerson(final Person person) {
		Optional<Person> p = personRepository.findById(person.getPid());
		if (p.isPresent())
			return false;
		else {
			personRepository.save(person);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demo.person.IPersonService#updatePerson(com.example.demo.person.
	 * Person)
	 */
	@Override
	public void updatePerson(final Person person) {
		personRepository.save(person);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.person.IPersonService#deletePerson(long)
	 */
	@Override
	public void deletePerson(final long pid) {
		personRepository.deleteById(pid);

	}

}