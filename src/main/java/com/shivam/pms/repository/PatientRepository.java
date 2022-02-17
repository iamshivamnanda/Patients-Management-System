package com.shivam.pms.repository;

import com.shivam.pms.model.Patient;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface PatientRepository extends CrudRepository<Patient,Integer> {
    
    // @Query("Select * FROM patient ")
    List<Patient> findByOrderByName();
}
