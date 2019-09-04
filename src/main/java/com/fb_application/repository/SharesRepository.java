package com.fb_application.repository;

import com.fb_application.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SharesRepository extends JpaRepository<Share,Long> {

    @Query(value = "select count(user_id) as totalStatus from post_share where post_id=?",nativeQuery = true)
    Integer getCountShare(Long postId);

    @Query(value = "select user_name from user_account inner join post_share on user_account.id=post_share.user_id where post_id=? ",nativeQuery = true)
    List<String> getSharesUserNameByPostId(Long postId);
}
