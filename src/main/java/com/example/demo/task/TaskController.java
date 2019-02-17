package com.example.demo.task;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.task.exception.TaskExistsException;
import com.example.demo.task.exception.TaskNotFoundException;

/**
 * The Class TaskController.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/tasks")
public class TaskController {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(TaskController.class);

	/** The taskservice. */
	ITaskService taskService;

	/**
	 * F Instantiates a new task controller.
	 *
	 * @param taskService the task service
	 */
	@Autowired
	public TaskController(ITaskService taskService) {
		this.taskService = taskService;
	}

	/**
	 * Gets the all taks.
	 *
	 * @return the all taks
	 */
	@GetMapping
	public ResponseEntity<List<Task>> getAllTaks() {

		List<Task> tasks = taskService.findAll();

		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	/**
	 * Gets the task.
	 *
	 * @param id the id
	 * @return the task
	 */
	@GetMapping("{id}")
	public ResponseEntity<Task> getTask(@PathVariable final long id) {

		try {
			Task task = taskService.findById(id);

			return new ResponseEntity<Task>(task, HttpStatus.OK);
		} catch (TaskNotFoundException e) {
			log.error("getTask", e);
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 * New task.
	 *
	 * @param task the task
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<Task> newTask(@RequestBody final Task task) {

		try {
			Task newTask = taskService.save(task);

			// Return link to the new object
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newTask.getId()).toUri();

			return ResponseEntity.created(location).build();
		} catch (TaskExistsException e) {

			log.error("newTask", e);
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}

	/**
	 * Update task.
	 *
	 * @param id   the id
	 * @param task the task
	 * @return the response entity
	 */
	@PutMapping("{id}")
	public ResponseEntity<Task> updateTask(@PathVariable final long id, @RequestBody final Task task) {

		Task existingTask;
		try {

			existingTask = taskService.findById(id);

			task.setId(existingTask.getId());

			taskService.update(task);

			return ResponseEntity.ok().build();
		} catch (TaskNotFoundException e) {
			log.error("updateTask", e);
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 * Delete task.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<Task> deleteTask(@PathVariable final long id) {
		try {
			taskService.delete(id);
			return ResponseEntity.ok().build();
		} catch (TaskNotFoundException e) {
			log.error("deleteTask", e);
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Delete all tasks.
	 *
	 * @return the response entity
	 */
	@DeleteMapping
	public ResponseEntity<?> deleteAllTasks() {
		taskService.deleteAll();
		return ResponseEntity.ok().build();
	}

}
