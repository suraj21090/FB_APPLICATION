package com.fb_application.service;

import com.fb_application.entity.Share;
import com.fb_application.exceptions.ResourceNotFoundException;
import com.fb_application.repository.SharesRepository;
import com.fb_application.repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareService {

    @Autowired
    private UserPostRepository userPostRepository;
    @Autowired
    private SharesRepository sharesRepository;

    public Share addUserShare( Long postId, Share share) {
        return userPostRepository.findById(postId).map(post -> {
            share.setUserPost(post);
            Share share1 = sharesRepository.save(share);
            return share1;
        }).orElseThrow(() -> new ResourceNotFoundException("userId " + postId + " not found"));
    }

    public Integer getPostShareCount(Long postId) {
        Integer count = sharesRepository.getCountShare(postId);
        return count;
    }

    public List<String> getUserNamesOfSharesByPostId(Long postId) {
        StringBuffer sb = new StringBuffer();
        List<String> userName = sharesRepository.getSharesUserNameByPostId(postId);
        return userName;
    }
}
