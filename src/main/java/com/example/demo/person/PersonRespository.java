package com.example.demo.person;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface PersonRespository.
 */
public interface PersonRespository extends JpaRepository<Person, Long> {

}
