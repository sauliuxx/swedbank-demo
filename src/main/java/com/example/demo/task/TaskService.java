package com.example.demo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(e -> tasks.add(e));
        return tasks;
    }

    public boolean createTask(final Task task){
        Optional<Task> t = taskRepository.findById(task.getId());
        if(t.isPresent()){
            return false;
        } else{
            taskRepository.save(task);
            return true;
        }
    }
}
