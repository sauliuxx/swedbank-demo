package com.example.demo.task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.task.exception.TaskExistsException;
import com.example.demo.task.exception.TaskNotFoundException;

/**
 * The Class TaskService.
 */
@Service
public class TaskService implements ITaskService {

	/** The task repository. */
	private TaskRepository taskRepository;

	/**
	 * Instantiates a new task service.
	 *
	 * @param taskRepository the task repository
	 */
	@Autowired
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.task.ITaskService#findAll()
	 */
	@Override
	public List<Task> findAll() {
		List<Task> tasks = taskRepository.findAll();
		return tasks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.task.ITaskService#findById(long)
	 */
	@Override
	public Task findById(final long id) throws TaskNotFoundException {
		Task task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException(id));
		return task;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.task.ITaskService#save(com.example.demo.task.Task)
	 */
	@Override
	public Task save(Task task) throws TaskExistsException {
		Optional<Task> oldTask = taskRepository.findById(task.getId());
		
		if(oldTask.isPresent()) {
			throw new TaskExistsException(oldTask.toString());
		}
		
		return taskRepository.save(task);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.task.ITaskService#update(com.example.demo.task.Task)
	 */
	@Override
	public void update(Task task) throws TaskNotFoundException {
		Optional<Task> oldTask = taskRepository.findById(task.getId());
		if(!oldTask.isPresent()) {
			throw new TaskNotFoundException(task.getId());
		}
		taskRepository.save(task);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.task.ITaskService#delete(long)
	 */
	@Override
	public void delete(long id) throws TaskNotFoundException {
		Optional<Task> task = taskRepository.findById(id);
		if (task.isPresent()) {
			taskRepository.deleteById(id);
		}
		else {
			throw new TaskNotFoundException(id);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.task.ITaskService#deleteAll()
	 */
	@Override
	public void deleteAll() {

		taskRepository.deleteAll();

	}

}
