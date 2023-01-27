package com.tms.user.service.ServiceImpl;


import com.tms.user.service.Entities.Challan;
import com.tms.user.service.Entities.Toll;
import com.tms.user.service.Entities.User;
import com.tms.user.service.Exceptions.ResourceNotFoundException;

import com.tms.user.service.External.TollService;
import com.tms.user.service.Repository.UserRepo;
import com.tms.user.service.Services.UserService;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserserviceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TollService tollService;

    private Logger logger = LoggerFactory.getLogger(UserserviceImpl.class);


    @Override
    public User saveUser(User user) {
        //generate unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        //fetch Challancharge of above user from Challan Service
        // http://localhost:8083/challans/users/39a8e144-f506-4bb6-b1e1-2b43c2ede705

        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given ID is not found on server!!:" + userId));

        Challan[] challanOfUser = restTemplate.getForObject("http://CHALLAN-SERVICE/challans/users/" + user.getUserId(), Challan[].class);


        List<Challan> challans = Arrays.stream(challanOfUser).toList();

        List<Challan> challanList = challans.stream().map(challan -> {

            //RestTemplate Type
//            ResponseEntity<Toll> forEntity = restTemplate.getForEntity("http://TOLL-SERVICE/tolls/"+ challan.getTollid() , Toll.class);
//            Toll toll = forEntity.getBody();

//            logger.info("response status code: {}", forEntity.getStatusCode());

            //OpenFeign Client Type
            Toll toll = tollService.getToll(challan.getTollid());
            challan.setToll(toll);
            return challan;
        }).collect(Collectors.toList());

        user.setChallancharge(challanList);

        return user;
    }


    @Override
    public void deleteUser(String user) {
        userRepo.findById(user);
        userRepo.deleteById(user);
    }


    @Override
    public void updateUser(User user, String userId) {
        this.userRepo.save(user);

    }

}
