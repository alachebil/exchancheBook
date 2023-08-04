package com.workshop.bouali.User;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")

public class UserController {


    private final UserService userService;
    @Autowired // taamel instantance wa7adha eli f constructeur , w lezem classe nzidha @component wala service
    public UserController(UserService studentService)
    {
        this.userService= studentService;
    }
    // @GetMapping("/ala")


    @GetMapping
    public List<UserP> getStudents()
    {
        return userService.getUsers();

    }


    @PostMapping("/new")
    public void registerNewUser(@RequestBody UserP user) throws IllegalAccessException {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "/del/{userId}")
    public void deleteStudent(@PathVariable("userId") Long userId ) throws IllegalAccessException {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateStudent(@PathVariable("userId") Long userId,@RequestParam(required = false) String name,@RequestParam(required = false) String email ) throws IllegalAccessException {
        userService.updateUser(userId,name,email);
    }








}
