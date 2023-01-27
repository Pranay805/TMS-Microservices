package com.tms.challan.service.Impl;

import com.tms.challan.service.Entities.Challan;
import com.tms.challan.service.Repository.ChallanRepo;
import com.tms.challan.service.Services.ChallanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChallanServiceImpl implements ChallanService {

    @Autowired
    private ChallanRepo challanRepo;

    @Override
    public Challan createChallan(Challan challan) {
        String randomChallanId = UUID.randomUUID().toString();
        challan.setChallanId(randomChallanId);
        return challanRepo.save(challan);
    }

    @Override
    public List<Challan> getChallanByUserId(String userId) {
        return challanRepo.findByUserId(userId);
    }

    @Override
    public List<Challan> getChallanByUsername(String name) {
        return challanRepo.findByname(name);
    }


//
//    @Override
//    public List<Challan> getChallanByVehiclenumber(String vehiclenumber) {
//        return challanRepo.findByVehicleNumber(vehiclenumber);
//    }
//
    @Override
    public List<Challan> getChallanByTollname(String tollname) {
        return challanRepo.findByTollName(tollname);
    }

    @Override
    public void deleteChallan(String challan) {
        challanRepo.findById(challan);
        challanRepo.deleteById(challan);
    }

    @Override
    public List<Challan> getAllChallans() {

        return challanRepo.findAll();
    }
}