package com.fbpost.springbootFBCRUDMySql.controller;


import com.fbpost.springbootFBCRUDMySql.entity.UserAccount;
import com.fbpost.springbootFBCRUDMySql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/UserAccount")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/add")
    @ResponseBody
    public ResponseEntity<String> addUser(@RequestParam String userName, @RequestParam String passWord, @RequestParam String userEmail) {
        String str =userService.addUser(userName,passWord,userEmail);
        return new  ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> findById(@PathVariable Long id) {
       String str =userService.findUserById(id);
       return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping(path = "/findByName")
    @ResponseBody
    public ResponseEntity<String> findByUserName(@RequestParam String userName) {
        String str =userService.findUserByUserName(userName);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/findByUserNameAndPassWord")
    public ResponseEntity<String> findByUserNameAndPassWord(@RequestParam String userName, @RequestParam String passWord) {
        String str =userService.findUserByUserNameAndPassword(userName,passWord);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping(path = "/findAll")
    @ResponseBody
    public ResponseEntity<String> findAllUser() {
        String str =userService.getAllUser();
        return new ResponseEntity<>(str,HttpStatus.OK);

    }

    @ResponseBody
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @Valid @RequestBody UserAccount userRequest) {
        String str =userService.updatUser(id,userRequest);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping(path = "/deletById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        String str =userService.deleteUser(id);
        return new ResponseEntity<>(str,HttpStatus.OK);

    }

    @ResponseBody
    @GetMapping(path = "/deletByUserName")
    public ResponseEntity<String> deleteByUserName(@RequestParam String userName) {
        String str =userService.deleteUserByUserName(userName);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping(path = "/deleteByUserNameAndPassWord")
    public ResponseEntity<String> deleteByUserNameAndPassWord(@RequestParam String userName, @RequestParam String passWord) {
        String str =userService.deleteByUserNameAndPassWord(userName,passWord);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }
}