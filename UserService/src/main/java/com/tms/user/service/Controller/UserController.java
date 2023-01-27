package com.tms.user.service.Controller;

import com.tms.user.service.Entities.User;
import com.tms.user.service.Exceptions.ResourceNotFoundException;
import com.tms.user.service.Repository.UserRepo;
import com.tms.user.service.ServiceImpl.UserserviceImpl;
import com.tms.user.service.Services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //create User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //Get Single User
    @GetMapping("/{userId}")
    @CircuitBreaker(name="challanTollBreaker",fallbackMethod  ="challanFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //creating fallback method for circuitbreaker
    public  ResponseEntity<User>challanFallback(String userId,Exception ex){
        logger.info("Fallback is executed because service is down:",ex.getMessage());
       User user = User.builder().userId("12345").name("This is Dummy Data as some of the services are down").mobileNumber("12345").adharNumber("12345").gender("dummy").build();
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    //Get All User
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    //Update User
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
        User updatedUser = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + userId));
        updatedUser.setName(user.getName());
        updatedUser.setGender(user.getGender());
        updatedUser.setAdharNumber(user.getAdharNumber());
        updatedUser.setMobileNumber(user.getMobileNumber());
        userRepo.save(updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    //Delete User
    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable String userId){
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not exist with id: " + userId));
        this.userRepo.delete(user);

        return "User Deleted Successfully";
    }
}
