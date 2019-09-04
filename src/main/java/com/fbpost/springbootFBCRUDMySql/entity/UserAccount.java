package com.fbpost.springbootFBCRUDMySql.entity;


import javax.persistence.*;
import java.util.*;

@Entity(name="user_account")

public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;
    @Column
    private String passWord;
    @Column
    private String userEmail;

    @OneToMany(mappedBy = "userAccount",fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    private List<UserPost> userPost;

    public Long getId() {
        return id;
    }

    public List<UserPost> getUserPost() {
        return userPost;
    }

    public void setUserPost(List<UserPost> userPost) {
        this.userPost = userPost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
