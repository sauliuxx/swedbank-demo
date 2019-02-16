package com.example.demo.group;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.group.exception.GroupExistsException;
import com.example.demo.group.exception.GroupNotFoundException;

/**
 * The Class GroupController.
 */
@RestController
@RequestMapping("api/groups")
public final class GroupController {

	private static final Logger log = LoggerFactory.getLogger(GroupController.class);

	/** The group service. */
	private IGroupService groupService;

	/**
	 * Instantiates a new group controller.
	 *
	 * @param groupService the group service
	 */
	@Autowired
	public GroupController(IGroupService groupService) {

		this.groupService = groupService;

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

			return ResponseEntity.noContent().build();
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

			return ResponseEntity.noContent().build();
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
		return ResponseEntity.noContent().build();
	}

}
