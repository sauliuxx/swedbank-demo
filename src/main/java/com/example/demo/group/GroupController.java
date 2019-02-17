package com.example.demo.group;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.group.exception.GroupExistsException;
import com.example.demo.group.exception.GroupNotFoundException;
import com.example.demo.task.ITaskService;
import com.example.demo.task.Task;
import com.example.demo.task.exception.TaskNotFoundException;


/**
 * The Class GroupController.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/groups")
public final class GroupController {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(GroupController.class);

	/** The group service. */
	private IGroupService groupService;

	/** The task service. */
	private ITaskService taskService;

	/**
	 * Instantiates a new group controller.
	 *
	 * @param groupService the group service
	 * @param taskService the task service
	 */
	@Autowired
	public GroupController(IGroupService groupService, ITaskService taskService) {

		this.groupService = groupService;
		this.taskService = taskService;

	}

	/**
	 * Gets the all groups.
	 *
	 * @return the all groups
	 */
	@GetMapping
	public ResponseEntity<List<Group>> getAllGroups() {

		List<Group> groups = groupService.findAll();

		return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);

	}

	/**
	 * Retrieve group.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("{id}")
	public ResponseEntity<Group> retrieveGroup(@PathVariable final long id) {

		try {
			Group group = groupService.findById(id);

			return new ResponseEntity<Group>(group, HttpStatus.OK);
		} catch (GroupNotFoundException ex) {
			log.error("On retrieveGroup", ex);
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * New group.
	 *
	 * @param group the group
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<Group> newGroup(@RequestBody final Group group) {

		try {
			Group newGroup = groupService.save(group);

			// Return link to the new object
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newGroup.getId()).toUri();

			return ResponseEntity.created(location).build();
		} catch (GroupExistsException ex) {
			log.error("On newGroup", ex);
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}

	/**
	 * Update group.
	 *
	 * @param id    the id
	 * @param group the group
	 * @return the response entity
	 */
	@PutMapping("{id}")
	public ResponseEntity<?> updateGroup(@PathVariable final long id, @RequestBody final Group group) {

		try {
			Group existingGroup = groupService.findById(id);

			group.setId(existingGroup.getId());

			groupService.update(group);

			return ResponseEntity.ok().build();
		} catch (GroupNotFoundException ex) {
			log.error("On updateGroup", ex);
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Delete group.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteGroup(@PathVariable final long id) {

		try {
			groupService.delete(id);

			return ResponseEntity.ok().build();
		} catch (GroupNotFoundException ex) {
			log.error("On deleteGroup", ex);
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Delete all groups.
	 *
	 * @return the response entity
	 */
	@DeleteMapping
	public ResponseEntity<?> deleteAllGroups() {
		groupService.deleteAll();
		return ResponseEntity.ok().build();
	}

	/**
	 * Gets the tasks.
	 *
	 * @param id the id
	 * @return the tasks
	 */
	@GetMapping("{id}/tasks")
	public ResponseEntity<Set<Task>> getTasks(@PathVariable("id") long id) {

		try {
			Group group = groupService.findById(id);

			Set<Task> taks = group.getTasks();

			return new ResponseEntity<Set<Task>>(taks, HttpStatus.OK);
		} catch (GroupNotFoundException e) {
			log.error("getTasks", e);
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Sets the task.
	 *
	 * @param groupId the group id
	 * @param taskId  the task id
	 * @return the response entity
	 */
	@PatchMapping("{id}/tasks/{task_id}")
	public ResponseEntity<?> setTask(@PathVariable("id") long groupId, @PathVariable("task_id") long taskId) {

		try {
			Group group = groupService.findById(groupId);
			Task task = taskService.findById(taskId);

			task.setGroup(group);
			taskService.update(task);
			return ResponseEntity.ok().build();

		} catch (GroupNotFoundException | TaskNotFoundException e) {
			log.error("setTask", e);
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Removes the task.
	 *
	 * @param groupId the group id
	 * @param taskId the task id
	 * @return the response entity
	 */
	@DeleteMapping("{id}/tasks/{task_id}")
	public ResponseEntity<?> removeTask(@PathVariable("id") long groupId, @PathVariable("task_id") long taskId) {
		try {
			Group group = groupService.findById(groupId);
			Task task = taskService.findById(taskId);

			if (group.getTasks().contains(task)) {
				task.setGroup(null);
				taskService.update(task);
			}
			return ResponseEntity.ok().build();

		} catch (GroupNotFoundException | TaskNotFoundException e) {
			log.error("setTask", e);
			return ResponseEntity.notFound().build();
		}
	}

}
