package com.example.demo.group;

import java.util.List;

import com.example.demo.group.exception.GroupExistsException;
import com.example.demo.group.exception.GroupNotFoundException;



/**
 * The Interface IGroupService.
 */
public interface IGroupService {
	
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<Group> findAll();
	
	/**
	 * Gets the group by id.
	 *
	 * @param id the id
	 * @return the group by id
	 */
	Group findById(Long id) throws GroupNotFoundException;
	
	/**
	 * Adds the group.
	 *
	 * @param group the group
	 * @return true, if successful
	 */
	Group save(Group group) throws GroupExistsException ;
	
	/**
	 * Update group.
	 *
	 * @param group the group
	 */
	void update(Group group);
	
	/**
	 * Delete group.
	 *
	 * @param id the id
	 */
	void delete(long id) throws GroupNotFoundException;
	
	
	/**
	 * Delete all.
	 */
	void deleteAll();
	
	void saveAndFlush(Group group);

}
