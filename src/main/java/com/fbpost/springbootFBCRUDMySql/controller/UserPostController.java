package com.fbpost.springbootFBCRUDMySql.controller;

import com.fbpost.springbootFBCRUDMySql.entity.UserPost;
import com.fbpost.springbootFBCRUDMySql.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@RequestMapping(path = "/UserPost")
@Controller
public class UserPostController {
    @Autowired
    private PostService postService;

    @PostMapping("/UserAccount/{id}/posts")
    public ResponseEntity<String> createUserPost(@PathVariable(value = "id") Long id, @Valid @RequestBody UserPost userPost) {
        String str =postService.addUserPost(id,userPost);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PutMapping("/UserAccount/{id}/posts/{postId}")
    public ResponseEntity<String> updateUserPost(@PathVariable(value = "id") Long id, @PathVariable(value = "postId") Long postId, @Valid @RequestBody UserPost userPostRequest) {
        String str =postService.updateUserPost(id,postId,userPostRequest);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("/UserAccount/posts")
    public ResponseEntity<String> getAllPost() {
        String str =postService.getAllUserPost();
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("/UserAccount/{id}/posts")
    public ResponseEntity<String> findByUserAccountId(@PathVariable(value = "id") Long id) {
        String str =postService.findPostByUserAccountId(id);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @DeleteMapping("/UserAccount/{id}/posts/{postId}")
    public ResponseEntity<String> deletPost(@PathVariable(value = "id") Long id, @PathVariable(value = "postId") Long postId) {
        String str =postService.deleteUserPost(postId,id);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }
}
