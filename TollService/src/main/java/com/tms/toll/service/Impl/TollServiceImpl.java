package com.tms.toll.service.Impl;

import com.tms.toll.service.Entities.Toll;
import com.tms.toll.service.Exceptions.ResourceNotFoundException;
import com.tms.toll.service.Repository.TollRepo;
import com.tms.toll.service.Services.TollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TollServiceImpl implements TollService {

    @Autowired
    private TollRepo tollRepo;

    @Autowired
    private TollService tollService;


    @Override
    public Toll createToll(Toll toll) {
        //generate unique tollId
        String randomTollId = UUID.randomUUID().toString();
        toll.setTollid(randomTollId);
        return tollRepo.save(toll);
    }

    @Override
    public List<Toll> viewTolllist() {
        return tollRepo.findAll();
    }

    @Override
    public List<Toll> viewTollwithLocation(String query) {
        return tollRepo.viewTollwithLocation(query);
    }

    @Override
    public void updateToll(Toll toll, String tollid) {
        this.tollRepo.save(toll);

    }

    @Override
    public String deleteToll(Toll toll, String tollid) {
        this.tollRepo.delete(toll);
        return "Toll Deleted Successfully";
    }

    @Override
    public Toll viewTollById(String tollid) {
        Toll toll = tollRepo.findById(tollid).orElseThrow(()-> new ResourceNotFoundException("Toll with given ID is not found on server!!:" + tollid));
        return toll;
    }

    @Override
    public Toll viewTollById(Toll toll, String tollid) {
       this.tollRepo.findById(tollid).orElseThrow(()-> new ResourceNotFoundException("Toll with given ID is not found on server!!:" + tollid));
        return toll;
    }
}
