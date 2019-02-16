package com.example.demo.group;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.group.exception.GroupExistsException;
import com.example.demo.group.exception.GroupNotFoundException;

/**
 * The Class GroupService.
 */
@Service
public class GroupService implements IGroupService {

	/** The group repository. */
	private GroupRepository groupRepository;

	/**
	 * Instantiates a new group service.
	 *
	 * @param groupRepository the group repository
	 */
	@Autowired
	public GroupService(GroupRepository groupRepository) {

		this.groupRepository = groupRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.group.IGroupService#getAllGroups()
	 */
	@Override
	public List<Group> findAll() {

		List<Group> groups = new ArrayList<Group>();
		groupRepository.findAll().forEach(e -> groups.add(e));
		return groups;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.group.IGroupService#getGroupById(java.lang.Long)
	 */
	@Override
	public Group findById(Long id) throws GroupNotFoundException {
		Group group = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));

		return group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demo.group.IGroupService#addGroup(com.example.demo.group.Group)
	 */
	@Override
	public Group save(Group group) throws GroupExistsException {
		Optional<Group> oldGroup = groupRepository.findById(group.getId());

		if (oldGroup.isPresent()) {
			throw new GroupExistsException(oldGroup.toString());
		}

		return groupRepository.save(group);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.group.IGroupService#updateGroup(com.example.demo.group.
	 * Group)
	 */
	@Override
	public void update(Group group) {

		groupRepository.save(group);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.group.IGroupService#deleteGroup(long)
	 */
	@Override
	public void delete(long id) throws GroupNotFoundException {

		Optional<Group> group = groupRepository.findById(id);
		if (group.isPresent()) {
			groupRepository.deleteById(id);
		} else {
			throw new GroupNotFoundException(id);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.group.IGroupService#deleteAll()
	 */
	@Override
	public void deleteAll() {
		groupRepository.deleteAll();

	}

}
