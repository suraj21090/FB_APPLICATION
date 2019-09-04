package com.fb_application.controller;

import com.fb_application.service.PostService;
import com.fb_application.entity.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@RequestMapping(path = "/UserPost")
@Controller
public class UserPostController {
    @Autowired
    private PostService postService;

    @PostMapping("/UserAccount/{id}/posts")
    public ResponseEntity<UserPost> createUserPost(@PathVariable(value = "id") Long id, @Valid @RequestBody UserPost userPost) {
        UserPost add =postService.addUserPost(id,userPost);
        return new ResponseEntity<>(add,HttpStatus.OK);
    }

    @PutMapping("/UserAccount/{id}/posts/{postId}")
    public ResponseEntity<UserPost> updateUserPost(@PathVariable(value = "id") Long id, @PathVariable(value = "postId") Long postId, @Valid @RequestBody UserPost userPostRequest) {
        UserPost userPost =postService.updateUserPost(id,postId,userPostRequest);
        return new ResponseEntity<>(userPost,HttpStatus.OK);
    }

    @GetMapping("/UserAccount/posts")
    public ResponseEntity<List<UserPost>> getAllPost() {
        List<UserPost> userPosts =postService.getAllUserPost();
        return new ResponseEntity<>(userPosts,HttpStatus.OK);
    }

    @GetMapping("/UserAccount/{id}/posts")
    public ResponseEntity<List<UserPost>> findByUserAccountId(@PathVariable(value = "id") Long id) {
        List<UserPost> userPost =postService.findPostByUserAccountId(id);
        return new ResponseEntity<>(userPost,HttpStatus.OK);
    }

    @DeleteMapping("/UserAccount/{id}/posts/{postId}")
    public ResponseEntity<String> deletPost(@PathVariable(value = "id") Long id, @PathVariable(value = "postId") Long postId) {
        String str =postService.deleteUserPost(postId,id);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }
}
