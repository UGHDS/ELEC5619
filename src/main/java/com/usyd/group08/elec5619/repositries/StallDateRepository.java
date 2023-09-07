package com.usyd.group08.elec5619.repositries;

import com.usyd.group08.elec5619.models.StallDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StallDateRepository extends  JpaRepository <StallDate,String>{
}
