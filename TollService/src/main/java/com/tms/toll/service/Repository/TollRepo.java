package com.tms.toll.service.Repository;

import com.tms.toll.service.Entities.Toll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TollRepo extends JpaRepository<Toll,String> {

    @Query("SELECT p FROM Toll p WHERE " +
            "p.location LIKE CONCAT('%',:query, '%')")
    List<Toll> viewTollwithLocation(String query);
}
