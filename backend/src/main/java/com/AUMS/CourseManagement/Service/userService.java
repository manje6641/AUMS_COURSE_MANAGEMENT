package com.AUMS.CourseManagement.Service;

import com.AUMS.CourseManagement.DataTransferObject.DTOResponse;
import com.AUMS.CourseManagement.Repository.UserRepository;
import com.AUMS.CourseManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    private final UserRepository userRepo;

    public userService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public DTOResponse findAll()
    {
        return userRepo.findAll();
    }

    public DTOResponse findById(int id)
    {
        return userRepo.findById(id);
    }

    public DTOResponse addUser(User newUser)
    {
        return userRepo.addUser(newUser);
    }

    public DTOResponse updateUser(User newUser)
    {
        return userRepo.updateUser(newUser);
    }
    public DTOResponse deleteUser(int id)
    {
        return userRepo.deleteUser(id);
    }

    public DTOResponse findByEmail(String email)
    {
        return userRepo.findByEmail(email);
    }

    public DTOResponse findAllInstructor()
    {
        return userRepo.findAllInstructor();
    }

}
