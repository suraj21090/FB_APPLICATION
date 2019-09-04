package com.fb_application.service;

import com.fb_application.entity.Like;
import com.fb_application.exceptions.ResourceNotFoundException;
import com.fb_application.repository.LikeRepository;
import com.fb_application.repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private UserPostRepository userPostRepository;
    @Autowired
    private LikeRepository likeRepository;


    public Like addUserLike( Long postId, Like like) {
        return userPostRepository.findById(postId).map(post -> {
            like.setUserPost(post);
            Like like1 = likeRepository.save(like);
           return like1;
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + postId + " not found"));
    }


    public Integer getCountPostLikes(Long postId)
    {
        Integer count = likeRepository.getCountLike(postId);
        return count;
    }

    public List<String> getUserNamesByPostId(Long postId)
    {
        List<String> userName = likeRepository.getUserNameByPostId(postId);
        return userName;
    }
}
