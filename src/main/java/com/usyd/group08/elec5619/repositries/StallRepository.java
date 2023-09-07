package com.usyd.group08.elec5619.repositries;

import com.usyd.group08.elec5619.models.Stall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StallRepository extends  JpaRepository <Stall,String>{
}
