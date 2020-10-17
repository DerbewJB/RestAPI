package com.restapi.restapi.controller;

import java.util.List;

import com.restapi.restapi.dto.UserDto;
import com.restapi.restapi.model.User;
import com.restapi.restapi.repostory.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepo ur;

    @GetMapping("/users")
    public List<User> getUsers()
    {
        return this.ur.findAll();
    }
    @PostMapping("/Addusers")
    public void createUser(@RequestBody UserDto userDto)
    {
        User user=new User();
        user.setName(userDto.getNameDto());
        this.ur.save(user);
    }
    @GetMapping("/users/{id}")
    public User getOneUser(@PathVariable int id)
    {
        return this.ur.findById(id).orElse(null);
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
	       this.ur.deleteById(id);
    }
    @PutMapping("users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody UserDto userdto)
    {
        User user=this.ur.findById(id).orElse(null);
        user.setName(userdto.getNameDto());
        return this.ur.save(user);
    }
}