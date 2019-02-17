package com.example.demo.task;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface TaskRepository.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

}
