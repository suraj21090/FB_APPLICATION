package com.fbpost.springbootFBCRUDMySql.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user_post")
public class UserPost {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private String postType;

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private UserAccount userAccount;

    @OneToMany(mappedBy = "userPost")
    private List<Like> like;

    @OneToMany(mappedBy = "userPost")
    private List<Share> status;

    @OneToMany(mappedBy = "userPost")
    private List<Comments> comments;

    public List<Like> getLike() {
        return like;
    }

    public void setLike(List<Like> like) {
        this.like = like;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }


}
