package com.fb_application.repository;

import com.fb_application.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {

List<UserAccount> findByUserName(String userName);

List<UserAccount> findByUserNameAndPassWord(String userName, String passWord);

@Query(value="select * from user_account where id=?",nativeQuery = true)
UserAccount getUserAccountById(Long id);


@Transactional
void deleteByUserName(String userName);

    @Transactional
    void deleteByUserNameAndPassWord(String userName, String passWord);
}
