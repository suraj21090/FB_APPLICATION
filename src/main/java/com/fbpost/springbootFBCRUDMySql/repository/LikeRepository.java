package com.fbpost.springbootFBCRUDMySql.repository;

import com.fbpost.springbootFBCRUDMySql.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

public interface LikeRepository extends JpaRepository<Like,Long> {

    @Query(value = "select count(user_id) as totalLikes from post_like where post_id=?",nativeQuery = true)
    Integer getCountLike(Long postId);

    @Query(value = "select user_name from user_account inner join post_like on user_account.id=post_like.user_id where post_id=? ",nativeQuery = true)
    List<String> getUserNameByPostId(Long postId);



}
