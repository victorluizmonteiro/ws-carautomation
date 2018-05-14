package com.fiap.carautomation.service;

import com.fiap.carautomation.model.User;
import com.fiap.carautomation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(String id){

        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public User save(User user){
        return  userRepository.save(user);
    }

    public User update(User user){

        return  userRepository.save(user);
    }


    public List<User>findAll(){
        List<User>users = userRepository.findAll();
        return users;
    }
}
