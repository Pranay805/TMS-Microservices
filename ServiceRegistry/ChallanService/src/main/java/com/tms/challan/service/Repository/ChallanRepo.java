package com.tms.challan.service.Repository;

import com.tms.challan.service.Entities.Challan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ChallanRepo extends JpaRepository<Challan,String> {

    //Custom Finder Methods
    List<Challan>findByUserId(String userId);


    List<Challan>findByname(String name);
//
//    List<Challan>findByVehicleNumber(String vehiclanumber);
//
    List<Challan>findByTollName(String tollname);

    Optional<Challan> findById(String challanId);



}
