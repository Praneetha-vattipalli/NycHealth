package com.nyc.health.Clinic.Service;
import com.nyc.health.Clinic.EntityClinic.Clinic;
import com.nyc.health.Clinic.Exception.ClinicNotFoundException;
import com.nyc.health.Clinic.Repository.ClinicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@Service
public class ServiceImpl {
    @Autowired
    private ClinicRepo clinicRepo;

    //GET ALL CLINIC DETAILS
    public List<Clinic> getAllClinic() {
        return clinicRepo.findAll();
    }


    //Post the clinic Information
    public Clinic registerNewClinic(Clinic newClinic) {
        return clinicRepo.save(newClinic);
    }

    //Update Clinic Information
   /* public Optional<Clinic> updateClinic(Integer id, Clinic newClinic) {
        if (id == null) {
            System.out.println(" Clinic id is required ");
        } else {
            return Optional.of(clinicRepo.findById(id)
                    .map(n -> {
                        n.setName(newClinic.getName());
                        // n.setAddress(newClinic.getAddress());
                        // n.setEmail(newClinic.setEmail());
                        // n.setPhoneNumber(newClinic.setPhoneNumber());
                        return clinicRepo.save(n);

                    }).orElseGet(() -> {
                        return clinicRepo.save(newClinic);
                    }));
        }
        return null;
    }*/

    //New Update Request

     @PutMapping("/updateClinic/{id}")
     public Clinic updateClinicRecord(@RequestBody Clinic newClinic, @PathVariable Integer id){

                 return clinicRepo.findById(id)
                         .map(clinic -> {
                             clinic.setName(newClinic.getName());
                             clinic.setAddress(newClinic.getAddress());
                             clinic.setEmail(newClinic.getEmail());
                             clinic.setPhoneNumber(newClinic.getPhoneNumber());
                             return clinicRepo.save(clinic);
                         })
                         .orElseGet(()-> clinicRepo.save(newClinic));

     }


    //Delete Clinic information By Id
    public void deleteClinic(Integer id) {
        clinicRepo.deleteById(id);
    }

    //Get Clinic Information By Id
    public Clinic getClinicById(Integer id) {

           return clinicRepo.findById(id)
                   .orElseThrow(()-> new ClinicNotFoundException("No Clinic with id " + " id " + "is found in Record"))


    // Clinic c = clinicRepo.getById(id);
     /*if (c == null) {
     throw new ClinicNotFoundException("Clinic id is not found ");
      }
     return c*/
            ;
       /* for (Clinic c : getAllClinic().toArray(new Clinic[0])) {
            if (c.getId().equals(id)) {
                  return c;
            }
        }
       return null;
    }*/

    }
    @Transactional
    public List<Clinic> findByStartDateAndEndDate(String StartDate, String EndDate) {
        return clinicRepo.findByStartDateAndEndDate(StartDate, EndDate);
    }

    @Override
    public int setFixedFirstnameFor(String status, int id) {
        return 0;
    }

    @Override
    public int UpdateClinicByEndDate(String EndDate) {
        return 0;
    }
}
