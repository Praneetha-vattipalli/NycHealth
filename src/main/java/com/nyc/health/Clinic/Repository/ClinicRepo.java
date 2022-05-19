package com.nyc.health.Clinic.Repository;

import com.nyc.health.Clinic.EntityClinic.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepo extends JpaRepository<Clinic,Integer> {

    @Query(value = "select * from clinic c where c.start_date=:StartDate AND c.end_date=:EndDate", nativeQuery = true)
    List<Clinic> findByStartDateAndEndDate(@Param("StartDate") String startDate, @Param("EndDate") String endDate);

}