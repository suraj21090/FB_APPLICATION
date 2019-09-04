package com.fb_application.controller;

import com.fb_application.entity.Share;
import com.fb_application.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.validation.Valid;

@RestController
public class SharesController {

    @Autowired
    private ShareService shareService;

    @PostMapping("UserAccount/{id}/posts/status")
    public ResponseEntity<Share> createUserShare(@PathVariable(value = "id") Long postId, @Valid @RequestBody Share share) {
        Share share1 =shareService.addUserShare(postId,share);
        return new ResponseEntity<>(share1,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/sharecounts")
    public ResponseEntity<Integer> getShareCount(@PathVariable Long postId) {
        Integer postShareCount =shareService.getPostShareCount(postId);
        return new ResponseEntity<>(postShareCount,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/shareusernames")
    public ResponseEntity<List<String>> getUserNamesByPostId(@PathVariable Long postId) {
        List<String> str =shareService.getUserNamesOfSharesByPostId(postId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }
}
