package com.fb_application.controller;


import com.fb_application.service.LikeService;
import com.fb_application.entity.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.validation.Valid;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

   @PostMapping("UserAccount/{id}/posts/likes")
   public ResponseEntity<Like> createUserLike(@PathVariable(value = "id") Long postId, @Valid @RequestBody Like like) {
       Like add =likeService.addUserLike(postId,like);
       return new ResponseEntity<>(add,HttpStatus.OK);

   }

   @GetMapping("UserAccount/{postId}/posts/counts")
    public ResponseEntity<Integer> getCountLikes(@PathVariable Long postId) {
       Integer countPostLikes =likeService.getCountPostLikes(postId);
       return new ResponseEntity<>(countPostLikes,HttpStatus.OK);
   }

    @GetMapping("UserAccount/{postId}/posts/usernames")
    public ResponseEntity<List<String>> getUserNamesByPostId(@PathVariable Long postId) {
        List<String> str =likeService.getUserNamesByPostId(postId);
        return new ResponseEntity<>(str,HttpStatus.OK);
   }


}
