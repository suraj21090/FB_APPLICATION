package com.fb_application.controller;

import com.fb_application.entity.Comments;
import com.fb_application.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("UserAccount/{id}/posts/comments")
    public ResponseEntity<Comments> createUserComment(@PathVariable(value = "id") Long postId, @Valid @RequestBody Comments comments) {
        Comments comments1 =commentService.addUserComment(postId,comments);
        return new ResponseEntity<>(comments1,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/commentcounts")
    public ResponseEntity<Integer> getCountComments(@PathVariable Long postId) {
        Integer countComments =commentService.getCountComments(postId);
        return new ResponseEntity<>(countComments,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/commentusernames")
    public ResponseEntity<List<String>> getUserNamesByPostId(@PathVariable Long postId) {
        List<String> str =commentService.getUserNamesOfCommentsByPostId(postId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/allComments")
    public ResponseEntity<List<String>> getAllComments(@PathVariable Long postId) {
        List<String> str =commentService.getAllCommentsOnPost(postId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/allCommentsuser")
    public ResponseEntity<List<String>> getAllCommentsUsers(@PathVariable Long postId) {
        List<String> str =commentService.getAllCommentsUsersOnPost(postId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/{commentReplyId}/allCommentsoncomment")
    public ResponseEntity<List<String>> getAllCommentsOnComment(@PathVariable Long postId, @PathVariable Long commentReplyId) {
        List<String> str =commentService.getAllCommentsOnCommentOnPost(postId,commentReplyId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("UserAccount/{postId}/posts/{commentReplyId}/allusernamesCommentsoncomment")
    public ResponseEntity<List<String>> getAllUserNameCommentOnComment(@PathVariable Long postId, @PathVariable Long commentReplyId) {
        List<String> str =commentService.getAllUserNameCommentOnCommentOnPost(postId,commentReplyId);
        return new ResponseEntity<>(str,HttpStatus.OK);
    }
}
