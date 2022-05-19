package com.nyc.health.Clinic.Service;

import com.nyc.health.Clinic.EntityClinic.Clinic;

import java.util.List;

public interface ClinicServices {

    List<Clinic> getAllClinic();
    Clinic registerNewClinic(Clinic newClinic);
    Clinic updateClinicRecord( Clinic newClinic,Integer id);
    void deleteClinic(Integer id);
    Clinic getClinicById(Integer id);
    List<Clinic> findByStartDateAndEndDate(String StartDate,String EndDate);
    int setFixedFirstnameFor(String status,int id);



    int UpdateClinicByEndDate(String EndDate);
}
