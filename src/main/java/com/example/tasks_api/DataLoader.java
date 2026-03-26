package com.example.tasks_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final TaskRepository taskRepository;
    
    public DataLoader(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) {
        if (taskRepository.count() == 0) {
            taskRepository.save(new Task("Learn Spring Boot", "in-progress"));
            taskRepository.save(new Task("Build the Tasks API", "done"));
            taskRepository.save(new Task("Add a real database", "in-progress"));
        }
        
    }   
}
