package com.shivam.pms.repository;

import com.shivam.pms.model.Patient;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient,Integer> {
    
}
