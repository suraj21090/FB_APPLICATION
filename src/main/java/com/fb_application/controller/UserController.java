package com.fb_application.controller;


import com.fb_application.entity.UserAccount;
import com.fb_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path ="/UserAccount")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserAccount> findById(@PathVariable Long id) {
       UserAccount  userAccount =userService.findUserById(id);
       return new ResponseEntity<>(userAccount,HttpStatus.OK);
    }

    @GetMapping(path="/add")
    @ResponseBody
    public ResponseEntity<UserAccount> createUser(@RequestBody UserAccount userAccount) {
        UserAccount add =userService.addUser(userAccount);
        return new  ResponseEntity<>(add,HttpStatus.OK);
    }

    @GetMapping(path = "/findByName")
    @ResponseBody
    public ResponseEntity<List<UserAccount>> findByUserName(@RequestParam String userName) {
        List<UserAccount> userAccount =userService.findUserByUserName(userName);
        return new ResponseEntity<>(userAccount,HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/findByUserNameAndPassWord")
    public ResponseEntity<List<UserAccount>> findByUserNameAndPassWord(@RequestParam String userName, @RequestParam String passWord) {
        List<UserAccount> userAccounts =userService.findUserByUserNameAndPassword(userName,passWord);
        return new ResponseEntity<>(userAccounts,HttpStatus.OK);
    }

    @GetMapping(path = "/findAll")
    @ResponseBody
    public ResponseEntity<List<UserAccount>> findAllUser() {
        List<UserAccount> userAccountList =userService.getAllUser();
        return new ResponseEntity<>(userAccountList,HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserAccount> updateUser(@PathVariable Long id, @Valid @RequestBody UserAccount userRequest) {
        UserAccount userAccount =userService.updatUser(id,userRequest);
        return new ResponseEntity<>(userAccount,HttpStatus.OK);
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