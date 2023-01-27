package com.tms.challan.service.Services;

import com.tms.challan.service.Entities.Challan;

import java.util.List;

public interface ChallanService {

    //create Challan
    Challan createChallan(Challan challan);

    //Get Challan by UserId
    List<Challan> getChallanByUserId(String userId);

    //Get Challan by User Name
    List<Challan>getChallanByUsername(String name);

    //Get Challan by VehicleNumber
//    List<Challan>getChallanByVehiclenumber(String vehiclenumber);

    //Get Challans by Tollname
    List<Challan>getChallanByTollname(String tollname);

    //Delete Challan by userId
    void deleteChallan(String challan);

    //Get All Challans
    List<Challan>getAllChallans();


}
