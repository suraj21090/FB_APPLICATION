package com.fbpost.springbootFBCRUDMySql.repository;

import com.fbpost.springbootFBCRUDMySql.entity.UserPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface UserPostRepository extends JpaRepository<UserPost,Long> {

   public Page<UserPost> findByUserAccountId(Long id,Pageable pageable);

   //Optional<UserPost> findByUserAccountIdAndPostId(Long id, Long postId);
   @Query(value = "select * from user_post where id=? and post_id=?",nativeQuery = true)
   UserPost findByUserIdAndPostId(Long postId, Long userId);

   @Query(value ="select * from user_post  where id=?",nativeQuery = true)
   List<UserPost> findByUserAccountId(Long id);

   @Query(value="select post_id from user_post Where id=?",nativeQuery = true)
   UserPost findPostIdByUserAccountId(Long userId);

   @Query(value ="select * from user_post where post_id=? and id=?",nativeQuery = true)
   UserPost getPost(Long postId, Long id);



}
