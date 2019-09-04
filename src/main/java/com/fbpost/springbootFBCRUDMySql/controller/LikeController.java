package com.fbpost.springbootFBCRUDMySql.controller;


import com.fbpost.springbootFBCRUDMySql.entity.Like;
import com.fbpost.springbootFBCRUDMySql.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LikeController {

    @Autowired
    private LikeService likeService;

   @PostMapping("UserAccount/{id}/posts/likes")
   public ResponseEntity<String> createUserLike(@PathVariable(value = "id") Long postId, @Valid @RequestBody Like like) {
       String str =likeService.addUserLike(postId,like);
       return new ResponseEntity<>(str,HttpStatus.OK);

   }

   @GetMapping("UserAccount/{postId}/posts/counts")
    public ResponseEntity<String> getCountLikes(@PathVariable Long postId) {
       String str =likeService.getCountPostLikes(postId);
       return new ResponseEntity<>(str,HttpStatus.OK);
   }

    @GetMapping("UserAccount/{postId}/posts/usernames")
    public ResponseEntity<String> getUserNamesByPostId(@PathVariable Long postId) {
        String str =likeService.getUserNamesByPostId(postId);
        return new ResponseEntity<>(str,HttpStatus.OK);
   }


}
