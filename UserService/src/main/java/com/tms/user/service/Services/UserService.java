package com.tms.user.service.Services;

import com.tms.user.service.Entities.User;

import java.util.List;


public interface UserService {


    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);


    void deleteUser(String user);


    void updateUser(User user, String userId);
}
