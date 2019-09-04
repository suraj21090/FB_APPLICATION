package com.fbpost.springbootFBCRUDMySql.service;

import com.fbpost.springbootFBCRUDMySql.entity.UserAccount;
import com.fbpost.springbootFBCRUDMySql.exceptions.ResourceNotFoundException;
import com.fbpost.springbootFBCRUDMySql.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    public String addUser(String userName, String passWord, String userEmail){
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName(userName);
        userAccount.setPassWord(passWord);
        userAccount.setUserEmail(userEmail);

        userAccountRepository.save(userAccount);
        String str = "user account has been added , userName = " + userName + ", userPassword = " + passWord + ", userEmail =" + userEmail;
        return str;
    }

    public String findUserById(Long id) {
        StringBuffer sb =  new StringBuffer();
        UserAccount userAccount =userAccountRepository.getUserAccountById(id);
        sb.append(userAccount.getUserName());
        sb.append(userAccount.getPassWord());
        sb.append(userAccount.getUserEmail());
        return sb.toString();
    }

    public String findUserByUserName(String userName)
    {
        List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository.findByUserName(userName);
        StringBuffer sb = new StringBuffer();
        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                sb.append("userName =");
                sb.append(userAccount.getUserName());
                sb.append(", userPassword =");
                sb.append(userAccount.getPassWord());
                sb.append(",userEmail =");
                sb.append(userAccount.getUserEmail());
            }
        }
        if (sb.length() == 0) {
            sb.append("No Record Find.");
        }
        return sb.toString();
    }

    public String findUserByUserNameAndPassword(String userName, String passWord)
    {
        List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository.findByUserNameAndPassWord(userName,passWord);
        StringBuffer sb = new StringBuffer();
        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                sb.append("userName =");
                sb.append(userAccount.getUserName());
                sb.append(", userPassword =");
                sb.append(userAccount.getPassWord());
                sb.append(",userEmail =");
                sb.append(userAccount.getUserEmail());
            }
        }
        if (sb.length() == 0) {
            sb.append("No Record Find.");
        }
        return sb.toString();
    }

    public String getAllUser()
    {
        StringBuffer sb = new StringBuffer();
        List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository.findAll();
        if (userAccountList != null) {
            for (UserAccount userAccount : userAccountList) {
                sb.append("user name = ");
                sb.append(userAccount.getUserName());
                sb.append(", password = ");
                sb.append(userAccount.getPassWord());
                sb.append(", email = ");
                sb.append(userAccount.getUserEmail());
                sb.append("\r\n");
            }
        }
        if (sb.length() == 0) {
            sb.append("No record find.");
        } else {
            sb.insert(0, "<pre>");
            sb.append("</pre>");
        }
        return sb.toString();
    }

    public String updatUser(Long id, UserAccount userRequest)
    {
        StringBuffer sb = new StringBuffer();
        return userAccountRepository.findById(id).map(user -> {
            user.setUserName(userRequest.getUserName());
            user.setPassWord(userRequest.getPassWord());
            user.setUserEmail(userRequest.getUserEmail());
            UserAccount userAccount1 = userAccountRepository.save(user);
            sb.append(userAccount1);
            sb.append("USER UPDATED SUCCESSFULLY");
            return sb.toString();
        }).orElseThrow(() -> new ResourceNotFoundException("Id " + id + " not found"));
    }

    public String deleteUser(Long id)
    {
        StringBuffer sb = new StringBuffer();
        userAccountRepository.deleteById(id);
        sb.append("deleted successfully");
        return sb.toString();
    }

    public String deleteUserByUserName( String userName) {
        StringBuffer sb = new StringBuffer();
        userAccountRepository.deleteByUserName(userName);
        sb.append("user account has been deleted succesfully");
        return sb.toString();
    }

    public String deleteByUserNameAndPassWord(String userName, String passWord) {
        StringBuffer sb = new StringBuffer();
        userAccountRepository.deleteByUserNameAndPassWord(userName, passWord);
        sb.append("user has been deleted succesfully");
        return sb.toString();
    }

}
