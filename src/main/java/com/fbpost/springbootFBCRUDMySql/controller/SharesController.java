package com.fbpost.springbootFBCRUDMySql.controller;

import com.fbpost.springbootFBCRUDMySql.entity.Share;
import com.fbpost.springbootFBCRUDMySql.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SharesController {

    @Autowired
    private ShareService shareService;

    @PostMapping("UserAccount/{id}/posts/status")
    public ResponseEntity<String> createUserShare(@PathVariable(value = "id") Long postId, @Valid @RequestBody Share share) {
        String str =shareService.addUserShare(postId,share);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/sharecounts")
    public ResponseEntity<String> getShareCount(@PathVariable Long postId) {
        String str =shareService.getPostShareCount(postId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/shareusernames")
    public ResponseEntity<String> getUserNamesByPostId(@PathVariable Long postId)
    {
        String str =shareService.getUserNamesOfSharesByPostId(postId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }
}
