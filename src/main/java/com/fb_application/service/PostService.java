package com.fb_application.service;

import com.fb_application.entity.UserAccount;
import com.fb_application.entity.UserPost;
import com.fb_application.exceptions.ResourceNotFoundException;
import com.fb_application.repository.UserAccountRepository;
import com.fb_application.repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserPostRepository userPostRepository;

    public UserPost addUserPost( Long id,  UserPost userPost) {
        UserAccount userAccount =userAccountRepository.getUserAccountById(id);
        userPost.setUserAccount(userAccount);
        UserPost userPost1 =userPostRepository.save(userPost);
        return  userPost1;

    }

    public UserPost updateUserPost( Long id,  Long postId,UserPost userPostRequest) {
        if (!userAccountRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not found");
        }
        UserPost userPost = userPostRepository.getPost(postId,id);
        userPost.setPostType(userPostRequest.getPostType());
        UserPost userPost1 =userPostRepository.save(userPost);
        return userPost1;
    }

    public List<UserPost> getAllUserPost() {
        List<UserPost> userPostList = (List<UserPost>) userPostRepository.findAll();
       return userPostList;
    }

    public List<UserPost> findPostByUserAccountId(Long id) {
        List<UserPost> userPostList = (List<UserPost>) userPostRepository.findByUserAccountId(id);
        return userPostList;
    }

    public String deleteUserPost( Long id,Long postId) {
        UserPost userPost =userPostRepository.findByUserIdAndPostId(postId, id);
        userPostRepository.delete(userPost);
        String str ="DELETED SUCCESFULLY";
        return  str;
    }

}
