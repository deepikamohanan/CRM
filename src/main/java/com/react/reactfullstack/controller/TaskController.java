package com.react.reactfullstack.controller;

import com.react.reactfullstack.model.Task;
import com.react.reactfullstack.repository.TaskRepository;
import com.react.reactfullstack.service.TaskNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskRepository userRepository;

    @PostMapping("/user")
    Task newUser(@RequestBody Task newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<Task> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/user/{id}")
    Task getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new TaskNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    Task updateUser(@RequestBody Task newUser,@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setDescription(newUser.getDescription());
                    user.setAssignedto(newUser.getAssignedto());
                    user.setDuedate(newUser.getDuedate());
                    user.setCreatedat(newUser.getCreatedat());
                    user.setCompletedat(newUser.getCompletedat());
                    user.setUpdatedat(newUser.getUpdatedat());
                    return userRepository.save(user);

                }).orElseThrow(()->new TaskNotFoundException(id));
    }

            @DeleteMapping("/user/{id}")
            String deleteUser(@PathVariable Long id){
               if(!userRepository.existsById(id)){
                   throw new TaskNotFoundException(id);
               }
               userRepository.deleteById(id);
               return "User with id "+id+" has been deleted successfully";
    }



}
