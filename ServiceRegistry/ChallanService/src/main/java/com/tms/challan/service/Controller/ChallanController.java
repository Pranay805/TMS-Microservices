package com.tms.challan.service.Controller;

import com.tms.challan.service.Entities.Challan;
import com.tms.challan.service.Exceptions.ResourceNotFoundException;
import com.tms.challan.service.Repository.ChallanRepo;
import com.tms.challan.service.Services.ChallanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challans")
public class ChallanController {

    @Autowired
    private ChallanService challanService;

    @Autowired
    private ChallanRepo challanRepo;

    //create Challan
    @PostMapping("/add")
    public ResponseEntity<Challan>createChallan(@RequestBody Challan challan){
        return ResponseEntity.status(HttpStatus.CREATED).body(challanService.createChallan(challan));
    }

    //Get Challan By UserId
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Challan>> getChallanByUserId(@PathVariable String userId){
        return ResponseEntity.ok(challanService.getChallanByUserId(userId));
    }

    //Get Challan By User Name
    @GetMapping("/{name}")
    public ResponseEntity<List<Challan>>getChallanByUsername(@PathVariable String name){
        return ResponseEntity.ok(challanService.getChallanByUsername(name));
    }

    //Get Challans By Toll Name
    @GetMapping("/toll/{tollname}")
    public ResponseEntity<List<Challan>>getChallanByTollname(@PathVariable String tollname){
        return ResponseEntity.ok(challanService.getChallanByTollname(tollname));
    }

    //Delete Challan
    @DeleteMapping("/delete/{challanId}")
    public String deleteChallan(@PathVariable String challanId){
        Challan challan = challanRepo.findById(challanId).orElseThrow(()-> new ResourceNotFoundException("Chalaan not exist with id: " + challanId));
        this.challanRepo.delete(challan);

        return "Challan Deleted Successfully";
    }

    //Get All Challans
    @GetMapping
    public ResponseEntity<List<Challan>> getAllChallan() {
        List<Challan> getAllChallans = challanService.getAllChallans();
        return ResponseEntity.ok(getAllChallans);
    }

}
