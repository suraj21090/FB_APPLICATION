package com.fbpost.springbootFBCRUDMySql.service;

import com.fbpost.springbootFBCRUDMySql.entity.Like;
import com.fbpost.springbootFBCRUDMySql.exceptions.ResourceNotFoundException;
import com.fbpost.springbootFBCRUDMySql.repository.LikeRepository;
import com.fbpost.springbootFBCRUDMySql.repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private UserPostRepository userPostRepository;
    @Autowired
    private LikeRepository likeRepository;


    public String addUserLike( Long postId, Like like) {
        StringBuffer sb = new StringBuffer();
        return userPostRepository.findById(postId).map(post -> {
            like.setUserPost(post);
            Like like1 = likeRepository.save(like);
            sb.append(like1);
            sb.append("like has been added");
            return sb.toString();
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + postId + " not found"));
    }


    public String getCountPostLikes(Long postId)
    {
        StringBuffer sb = new StringBuffer();
        Integer count = likeRepository.getCountLike(postId);
        sb.append(count);
        return sb.toString();
    }

    public String getUserNamesByPostId(Long postId)
    {
        StringBuffer sb = new StringBuffer();
        List<String> userName = likeRepository.getUserNameByPostId(postId);
        for(int i=0;i<userName.size();i++)
        {
            sb.append(userName.get(i));
        }
        return sb.toString();
    }
}
