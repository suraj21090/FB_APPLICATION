package com.fbpost.springbootFBCRUDMySql.controller;

import com.fbpost.springbootFBCRUDMySql.entity.Comments;
import com.fbpost.springbootFBCRUDMySql.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("UserAccount/{id}/posts/comments")
    public ResponseEntity<String> createUserComment(@PathVariable(value = "id") Long postId, @Valid @RequestBody Comments comments) {
        String str =commentService.addUserComment(postId,comments);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/commentcounts")
    public ResponseEntity<String> getCountComments(@PathVariable Long postId) {
        String str =commentService.getCountComments(postId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/commentusernames")
    public ResponseEntity<String> getUserNamesByPostId(@PathVariable Long postId) {
        String str =commentService.getUserNamesOfCommentsByPostId(postId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/allComments")
    public ResponseEntity<String> getAllComments(@PathVariable Long postId) {
        String str =commentService.getAllCommentsOnPost(postId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/allCommentsuser")
    public ResponseEntity<String> getAllCommentsUsers(@PathVariable Long postId) {
        String str =commentService.getAllCommentsUsersOnPost(postId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/{commentReplyId}/allCommentsoncomment")
    public ResponseEntity<String> getAllCommentsOnComment(@PathVariable Long postId, @PathVariable Long commentReplyId) {
        String str =commentService.getAllCommentsOnCommentOnPost(postId,commentReplyId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/{commentReplyId}/allusernamesCommentsoncomment")
    public ResponseEntity<String> getAllUserNameCommentOnComment(@PathVariable Long postId, @PathVariable Long commentReplyId) {
        String str =commentService.getAllUserNameCommentOnCommentOnPost(postId,commentReplyId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }
}
