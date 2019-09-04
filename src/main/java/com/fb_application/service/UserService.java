package com.fb_application.service;

import com.fb_application.entity.UserAccount;
import com.fb_application.exceptions.ResourceNotFoundException;
import com.fb_application.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccount addUser(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    public UserAccount findUserById(Long id) {
        UserAccount userAccount =userAccountRepository.getUserAccountById(id);
        return userAccount;
    }

    public List<UserAccount> findUserByUserName(String userName) {
        List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository.findByUserName(userName);
        return userAccountList;
    }

    public List<UserAccount> findUserByUserNameAndPassword(String userName, String passWord)
    {
        List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository.findByUserNameAndPassWord(userName,passWord);
        return userAccountList;
    }

    public  List<UserAccount> getAllUser() {
        List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository.findAll();
        return userAccountList;
    }

    public UserAccount updatUser(Long id, UserAccount userRequest)
    {

        return userAccountRepository.findById(id).map(user -> {
            user.setUserName(userRequest.getUserName());
            user.setPassWord(userRequest.getPassWord());
            user.setUserEmail(userRequest.getUserEmail());
            UserAccount userAccount1 = userAccountRepository.save(user);
            return userAccount1;
        }).orElseThrow(() -> new ResourceNotFoundException("Id " + id + " not found"));
    }

    public String deleteUser(Long id) {
        userAccountRepository.deleteById(id);
        String str ="deleted successfully";
        return str;
    }

    public String deleteUserByUserName( String userName) {
        userAccountRepository.deleteByUserName(userName);
        String str ="user account has been deleted succesfully";
        return str;
    }

    public String deleteByUserNameAndPassWord(String userName, String passWord) {
        userAccountRepository.deleteByUserNameAndPassWord(userName, passWord);
        String str ="user has been deleted succesfully";
        return str;
    }

}
