package com.example.tasks_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public DataLoader(UserRepository userRepository,
                    TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User ranga = userRepository.save(new User("Ranga", "ranga@example.com"));
            User jane = userRepository.save(new User("Jane", "jane@example.com"));

            taskRepository.save(new Task("Learn Spring Boot", "in-progress", ranga));
            taskRepository.save(new Task("Build the Tasks API", "done", ranga));
            taskRepository.save(new Task("Write tests", "todo", jane));
            taskRepository.save(new Task("Add relationships", "in-progress", jane));
        }
    }
}