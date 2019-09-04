package com.fbpost.springbootFBCRUDMySql.service;

import com.fbpost.springbootFBCRUDMySql.entity.Share;
import com.fbpost.springbootFBCRUDMySql.exceptions.ResourceNotFoundException;
import com.fbpost.springbootFBCRUDMySql.repository.SharesRepository;
import com.fbpost.springbootFBCRUDMySql.repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareService {

    @Autowired
    private UserPostRepository userPostRepository;
    @Autowired
    private SharesRepository sharesRepository;

    public String addUserShare( Long postId, Share share) {
        StringBuffer sb = new StringBuffer();
        return userPostRepository.findById(postId).map(post -> {
            share.setUserPost(post);
            Share share1 = sharesRepository.save(share);
            sb.append(share1);
            sb.append("post has been share");
            return sb.toString();
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + postId + " not found"));
    }

    public String getPostShareCount(Long postId) {
        StringBuffer sb = new StringBuffer();
        Integer count = sharesRepository.getCountShare(postId);
        sb.append(count);
        return sb.toString();
    }

    public String getUserNamesOfSharesByPostId(Long postId) {
        StringBuffer sb = new StringBuffer();
        List<String> userName = sharesRepository.getSharesUserNameByPostId(postId);
        for(int i=0;i<userName.size();i++) {
            sb.append(userName.get(i));
        }
        return sb.toString();
    }
}
