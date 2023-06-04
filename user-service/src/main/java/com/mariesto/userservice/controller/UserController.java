package com.mariesto.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mariesto.userservice.dto.CreateUserDTO;
import com.mariesto.userservice.dto.UserDTO;
import com.mariesto.userservice.service.UserService;

@RestController
@RequestMapping ("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    ResponseEntity<UserDTO> findUserById(@PathVariable String userId){
        UserDTO userDTO = userService.findUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createNewUser(@RequestBody CreateUserDTO dto){
        userService.createNewUser(dto);
    }
}
