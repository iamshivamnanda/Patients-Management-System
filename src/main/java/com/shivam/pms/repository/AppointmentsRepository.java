package com.shivam.pms.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

// import java.util.List;

import com.shivam.pms.model.Appointments;

import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentsRepository extends CrudRepository<Appointments, Integer> {
    @Query("FROM Appointments WHERE appointmentdate < adddate(CurDate() ,1) AND (status ='booked' OR status ='update') ORDER BY appointmentdate ASC")
    List<Appointments> findByAppointmentdate();

    List<Appointments> findByPid(Integer pid);

    @Query("SELECT COUNT(a) FROM Appointments a WHERE a.status=?1 AND a.appointmentdate >= ?2")
        long countByStatus(String name,Date date);

    // @Modifying 
    // @Query(value = "DELETE FROM Appointments a WHERE a.pid = :parentid",nativeQuery = true) // if want to write nativequery then mask nativeQuery  as true
    // int deleteByPidNative(@Param("parentid") Integer parentid); 

    int deleteByPid(Integer pid);

    // @Query("FROM Appointments WHERE datecreated >= CURDATE()")
    // List<Appointments> findByAppointmentdate();

    // @Query("FROM Appointments WHERE patient_id = 1")
    // List<Appointments> findByPatient_id();

    // List<Appointments> findAllAppointments();

    // List<Appointments> findByAppointmentdate();
}
