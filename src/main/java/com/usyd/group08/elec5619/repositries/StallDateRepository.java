package com.usyd.group08.elec5619.repositries;

import com.usyd.group08.elec5619.models.StallDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StallDateRepository extends  JpaRepository <StallDate,Integer>{
    @Query("SELECT sd from StallDate sd WHERE sd.stall.id = :stallId")
    List<StallDate> getStallDateByStallId(int stallId);
}
