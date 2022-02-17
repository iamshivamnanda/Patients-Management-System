package com.shivam.pms.repository;

import com.shivam.pms.model.Appointments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentsRepository extends CrudRepository<Appointments,Integer> {
    
}
