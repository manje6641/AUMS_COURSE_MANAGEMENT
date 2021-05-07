package com.AUMS.CourseManagement.Controller;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Service.userService;
import com.AUMS.CourseManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final userService UserService;

    public UserController(userService userService) {
        UserService = userService;
    }

    @GetMapping("/all")
    public DTOResponse getAllUser()
    {
        return UserService.findAll();
    }

    @GetMapping("/allInstructor")
    public DTOResponse getAllInstructor()
    {
        return UserService.findAllInstructor();
    }

    @GetMapping("/find/{id}")
    public DTOResponse getUserById(@PathVariable("id") int id)
    {
        return UserService.findById(id);
    }

    @GetMapping("/findByEmail/{email}")
    public DTOResponse getUserByEmail(@PathVariable("email") String email)
    {
        return UserService.findByEmail(email);
    }

    @PostMapping("/add")
    public DTOResponse addUser(@RequestBody User user)
    {
        return UserService.addUser(user);
    }

    @PutMapping("/update")
    public DTOResponse updateUser(@RequestBody User user)
    {
        return UserService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public DTOResponse deleteUser(@PathVariable("id") int id)
    {
        return UserService.deleteUser(id);
    }
}
