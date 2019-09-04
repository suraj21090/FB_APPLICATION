package com.fbpost.springbootFBCRUDMySql.service;

import com.fbpost.springbootFBCRUDMySql.entity.Comments;
import com.fbpost.springbootFBCRUDMySql.exceptions.ResourceNotFoundException;
import com.fbpost.springbootFBCRUDMySql.repository.CommentRepository;
import com.fbpost.springbootFBCRUDMySql.repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private UserPostRepository userPostRepository;
    @Autowired
    private CommentRepository commentRepository;

    public String addUserComment(Long postId,Comments comments) {
        StringBuffer sb = new StringBuffer();
        return userPostRepository.findById(postId).map(post -> {
            comments.setUserPost(post);
            Comments comments1 = commentRepository.save(comments);
            sb.append(comments1);
            sb.append("comment on the post succesfully");
            return sb.toString();
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + postId + " not found"));
    }

    public String getCountComments(Long postId)
    {
        StringBuffer sb = new StringBuffer();
        Integer count = commentRepository.getCountComments(postId);
        sb.append(count);
        return sb.toString();
    }

    public String getUserNamesOfCommentsByPostId(Long postId) {
        StringBuffer sb = new StringBuffer();
        List<String> userName = commentRepository.getCommentUserNameByPostId(postId);
        for(int i=0;i<userName.size();i++) {
            sb.append(userName.get(i));
        }
        return sb.toString();
    }

    public String getAllCommentsOnPost(Long postId) {
        StringBuffer sb = new StringBuffer();
        List<String> comments = commentRepository.getAllPostComments(postId);
        for(int i=0;i<comments.size();i++) {
            sb.append(comments.get(i));
            System.out.println();
        }
        return sb.toString();
    }

    public String getAllCommentsUsersOnPost(Long postId) {
        StringBuffer sb = new StringBuffer();
        List<String> comments = commentRepository.getCommentUserNameByPostId(postId);
        for(int i=0;i<comments.size();i++) {
            sb.append(comments.get(i));
            System.out.println();
        }
        return sb.toString();
    }

    public String getAllCommentsOnCommentOnPost(Long postId,Long commentReplyId) {
        StringBuffer sb = new StringBuffer();
        List<String> comments = commentRepository.getAllpostCommentOnComment(postId,commentReplyId);
        for(int i=0;i<comments.size();i++) {
            sb.append(comments.get(i));
        }
        return  sb.toString();
    }


    public String getAllUserNameCommentOnCommentOnPost(Long postId,Long commentReplyId) {
        StringBuffer sb = new StringBuffer();
        List<String> userNames =commentRepository.getAllCommentOnCommentUserNameByPostIdAndCommentReplyId(postId,commentReplyId);
        for(int i=0;i<userNames.size();i++) {
            sb.append(userNames.get(i));
        }
        return sb.toString();
    }
}
