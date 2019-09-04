package com.fbpost.springbootFBCRUDMySql.repository;

import com.fbpost.springbootFBCRUDMySql.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments,Long> {

    @Query(value = "select count(user_id) as totalStatus from post_comments where post_id=?",nativeQuery = true)
    Integer getCountComments(Long postId);

    @Query(value = "select user_name from user_account inner join post_comments on user_account.id=post_comments.user_id where post_id=? ",nativeQuery = true)
    List<String> getCommentUserNameByPostId(Long postId);

    @Query(value="select comment from post_comments where post_id=?",nativeQuery = true)
    List<String> getAllPostComments(Long postId);

    @Query(value="select comment from post_comments where post_id=? and comment_reply_id=?",nativeQuery = true)
    List<String> getAllpostCommentOnComment(Long postId,Long commentReplyId);

    @Query(value = "select user_name from user_account inner join post_comments on user_account.id=post_comments.user_id where post_id=? and comment_reply_id=? ",nativeQuery = true)
    List<String> getAllCommentOnCommentUserNameByPostIdAndCommentReplyId(Long postId,Long commentReplyId);

    @Query(value ="select comment from post_comments where post_id=? and user_id=?",nativeQuery = true)
    Comments updateComment(Long userId,Long postId);
}
