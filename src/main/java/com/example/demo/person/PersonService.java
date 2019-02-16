package com.example.demo.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.person.exception.PersonNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonService.
 */
@Service
public class PersonService implements IPersonService {

	/** The person repository. */

	private PersonRespository personRepository;

	/**
	 * Instantiates a new person service.
	 *
	 * @param personRepository the person repository
	 */
	@Autowired
	public PersonService(PersonRespository personRepository) {
		this.personRepository = personRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.person.IPersonService#getAll()
	 */
	@Override
	public List<Person> getAll() {
		List<Person> list = new ArrayList<Person>();
		personRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.person.IPersonService#getById(long)
	 */
	@Override
	public Person getById(final long pid) throws PersonNotFoundException {
		Person person = personRepository.findById(pid).orElseThrow(() -> new PersonNotFoundException(pid));
		return person;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demo.person.IPersonService#save(com.example.demo.person.Person)
	 */
	@Override
	public boolean save(final Person person) {
		Optional<Person> p = personRepository.findById(person.getPid());
		if (p.isPresent())
			return false;
		else {
			personRepository.save(person);
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demo.person.IPersonService#update(com.example.demo.person.Person)
	 */
	@Override
	public void update(final Person person) {
		personRepository.save(person);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.person.IPersonService#delete(long)
	 */
	@Override
	public void delete(final long pid) throws PersonNotFoundException {
		Person person = personRepository.findById(pid).orElseThrow(()-> new PersonNotFoundException(pid));
		personRepository.deleteById(pid);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.person.IPersonService#deleteAll()
	 */
	@Override
	public void deleteAll() {
		personRepository.deleteAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demo.person.IPersonService#saveAndFlush(com.example.demo.person.
	 * Person)
	 */
	@Override
	public void saveAndFlush(Person person) {
		personRepository.saveAndFlush(person);

	}

}
