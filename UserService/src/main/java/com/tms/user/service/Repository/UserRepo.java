package com.tms.user.service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.user.service.Entities.User;

public interface UserRepo extends JpaRepository <User,String>{


    void deleteById(String id);
}
