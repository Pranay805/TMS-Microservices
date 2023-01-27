package com.tms.toll.service.Services;

import com.tms.toll.service.Entities.Toll;

import java.util.List;

public interface TollService {

    //create Toll
    Toll createToll(Toll toll);


    //View Toll List
    List<Toll> viewTolllist();

    //View single Toll with Location
    List<Toll> viewTollwithLocation(String query);


    //Update Toll
    void updateToll(Toll toll, String tollid);

    //Delete Toll
    String deleteToll(Toll toll, String tollid);

    Toll viewTollById(String tollid);

    Toll viewTollById(Toll toll, String tollid);
}
