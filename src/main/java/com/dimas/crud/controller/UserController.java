package com.dimas.crud.controller;

import com.dimas.crud.dto.BaseResponseDTO;
import com.dimas.crud.entity.User;
import com.dimas.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<BaseResponseDTO> getUser(@PathVariable String userId){
        return userService.getDataUser(userId);
    }

    @PostMapping("/user/save")
    public ResponseEntity<BaseResponseDTO> saveUser(@RequestBody User user){
        return userService.setDataUser(user);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<BaseResponseDTO> deleteUser(@PathVariable Integer userId){
        return userService.delDataUser(userId);
    }

}
