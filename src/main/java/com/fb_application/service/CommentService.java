package com.fb_application.service;

import com.fb_application.entity.Comments;
import com.fb_application.exceptions.ResourceNotFoundException;
import com.fb_application.repository.CommentRepository;
import com.fb_application.repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private UserPostRepository userPostRepository;
    @Autowired
    private CommentRepository commentRepository;

    public Comments addUserComment(Long postId,Comments comments) {
        return userPostRepository.findById(postId).map(post -> {
            comments.setUserPost(post);
            Comments comments1 = commentRepository.save(comments);
           return comments1;
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + postId + " not found"));
    }

    public Integer getCountComments(Long postId) {
        Integer count = commentRepository.getCountComments(postId);
        return count;
    }

    public List<String> getUserNamesOfCommentsByPostId(Long postId) {
        List<String> userName = commentRepository.getCommentUserNameByPostId(postId);
       return userName;
    }

    public List<String> getAllCommentsOnPost(Long postId) {
        List<String> comments = commentRepository.getAllPostComments(postId);
        return comments;
    }

    public List<String> getAllCommentsUsersOnPost(Long postId) {
        List<String> comments = commentRepository.getCommentUserNameByPostId(postId);
       return comments;
    }

    public List<String> getAllCommentsOnCommentOnPost(Long postId,Long commentReplyId) {
        List<String> comments = commentRepository.getAllpostCommentOnComment(postId,commentReplyId);
        return comments;
    }

    public  List<String> getAllUserNameCommentOnCommentOnPost(Long postId,Long commentReplyId) {
        List<String> userNames =commentRepository.getAllCommentOnCommentUserNameByPostIdAndCommentReplyId(postId,commentReplyId);
        return userNames;
    }
}
