package com.tms.toll.service.Controller;


import com.tms.toll.service.Entities.Toll;
import com.tms.toll.service.Exceptions.ResourceNotFoundException;
import com.tms.toll.service.Repository.TollRepo;
import com.tms.toll.service.Services.TollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tolls")
public class TollController {

    @Autowired
    private TollService tollService;

    @Autowired
    private TollRepo tollRepo;


    //create Toll
    @PostMapping
    public ResponseEntity<Toll> createToll(@RequestBody Toll toll) {
        Toll toll1 = tollService.createToll(toll);
        return ResponseEntity.status(HttpStatus.CREATED).body(toll1);
    }

    //View Toll List
    @GetMapping
    public ResponseEntity<List<Toll>> viewTolllist() {
        List<Toll> allTolls = tollService.viewTolllist();
        return ResponseEntity.ok(allTolls);
    }

    //Update User
    @PutMapping("/update/{tollid}")
    public ResponseEntity<Toll> updateUser(@PathVariable String tollid, @RequestBody Toll toll) {
        Toll updatedToll = tollRepo.findById(tollid)
                .orElseThrow(() -> new ResourceNotFoundException("Toll not exist with id: " + tollid));
        updatedToll.setTollname(toll.getTollname());
        updatedToll.setLocation(toll.getLocation());

        tollRepo.save(updatedToll);
        return ResponseEntity.ok(updatedToll);
    }

    //Delete Toll
    @DeleteMapping("/delete/{tollid}")
    public String deleteToll(@PathVariable String tollid){
        Toll toll = tollRepo.findById(tollid).orElseThrow(()-> new ResourceNotFoundException("Toll not exist with id: " + tollid));
        this.tollRepo.delete(toll);

        return "Toll Deleted Successfully";
    }


    //View toll with Location
    @GetMapping("/search/{query}")
    public ResponseEntity<List<Toll>> viewTollwithLocation(@PathVariable("query") String query){
        return ResponseEntity.ok(tollService.viewTollwithLocation(query));
    }

    //View toll By Toll Id
    @GetMapping("/{tollid}")
    public ResponseEntity viewTollById(@PathVariable("tollid") String tollid){
        return ResponseEntity.ok(tollService.viewTollById(tollid));
    }

}
