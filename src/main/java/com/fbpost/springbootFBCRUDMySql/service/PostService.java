package com.fbpost.springbootFBCRUDMySql.service;

import com.fbpost.springbootFBCRUDMySql.entity.UserAccount;
import com.fbpost.springbootFBCRUDMySql.entity.UserPost;
import com.fbpost.springbootFBCRUDMySql.exceptions.ResourceNotFoundException;
import com.fbpost.springbootFBCRUDMySql.repository.UserAccountRepository;
import com.fbpost.springbootFBCRUDMySql.repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserPostRepository userPostRepository;

    public String addUserPost( Long id,  UserPost userPost) {
        StringBuffer sb = new StringBuffer();
        UserAccount userAccount =userAccountRepository.getUserAccountById(id);
        userPost.setUserAccount(userAccount);
        userPostRepository.save(userPost);
        sb.append(userPost);
        sb.append("post has been added");
        return sb.toString();
    }

    public String updateUserPost( Long id,  Long postId,UserPost userPostRequest) {
        if (!userAccountRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not found");
        }
        StringBuffer sb = new StringBuffer();
        UserPost userPost = userPostRepository.getPost(postId,id);
        userPost.setPostType(userPostRequest.getPostType());
        userPostRepository.save(userPost);
        sb.append("update succesfully");
        return sb.toString();
    }

    public String getAllUserPost() {
        StringBuffer sb = new StringBuffer();
        List<UserPost> userPostList = (List<UserPost>) userPostRepository.findAll();
        if (userPostList != null) {
            for (UserPost userPost : userPostList) {
                sb.append("postId = ");
                sb.append(userPost.getPostId());
                sb.append(", postType = ");
                sb.append(userPost.getPostType());
                sb.append("\r\n");
            }
        }
        if (sb.length() == 0) {
            sb.append("No record find.");
        } else {
            sb.insert(0, "<pre>");
            sb.append("</pre>");
        }
        return sb.toString();
    }

    public String findPostByUserAccountId( Long id) {
        StringBuffer sb = new StringBuffer();
        List<UserPost> userPostList = (List<UserPost>) userPostRepository.findByUserAccountId(id);
        if (userPostList != null) {
            for (UserPost userPost : userPostList) {
                sb.append("postId = ");
                sb.append(userPost.getPostId());
                sb.append(", postType= ");
                sb.append(userPost.getPostType());
                sb.append("\r\n");
            }
        }
        if (sb.length() == 0) {
            sb.append("No record find.");
        } else {
            sb.insert(0, "<pre>");
            sb.append("</pre>");
        }
        return sb.toString();
    }

    public String deleteUserPost( Long id,Long postId) {
        StringBuffer sb = new StringBuffer();
        UserPost userPost =userPostRepository.findByUserIdAndPostId(postId, id);
        userPostRepository.delete(userPost);
        sb.append("DELETED SUCCESFULLY");
        return  sb.toString();
    }

}
