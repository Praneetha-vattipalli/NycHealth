package com.nyc.health.Clinic.Controller;

import com.nyc.health.Clinic.EntityClinic.Clinic;
import com.nyc.health.Clinic.Service.ServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/nycHealth")
@SecurityRequirement(name="clinicAPI")
public class ClinicRestController {

    @Autowired
     private ServiceImpl service;

      @GetMapping("/allClinic")
     public ResponseEntity<List<Clinic>> viewAllClinic(){
           String msg = "list of All Clinic";
          HttpHeaders headers = new HttpHeaders();
            headers.add("desc","it will give list of all Clinic");
         // service.getAllClinic();
          return ResponseEntity.status(HttpStatus.OK).headers(headers).body(service.getAllClinic());
     }

    @GetMapping("/clinic/{id}")
    public ResponseEntity<Clinic> getClinicById(@PathVariable(value="id") Integer id )
    {
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add( "dsc", "Record of Clinic with id :" + id);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(service.getClinicById(id));

    }


    @PostMapping("/registerClinic")
    public ResponseEntity<Clinic> saveClinic(@RequestBody @Valid Clinic newClinic){
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("desc", "It will register new CLinic");
        // service.registerNewClinic(newClinic);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(service.registerNewClinic(newClinic));
    }
    @PutMapping("/updateClinic/{id}")
    public  ResponseEntity<String> updateClinic(@PathVariable Integer id, @RequestBody @Valid Clinic newClinic){
        service.updateClinicRecord(newClinic, id);
        return new ResponseEntity<>("Clinic Record with id " + id + " has updated", HttpStatus.OK);
    }

    @DeleteMapping("/deleteClinic/{id}")
    public ResponseEntity<String> deleteClinic(@PathVariable(value="id") Integer id){
        service.deleteClinic(id);
        return new ResponseEntity<>("Clinic Record with id " + id + " has deleted", HttpStatus.OK);

    }
//    @PostMapping("/closeClinic")
//    public ResponseEntity<String> closeClinic(@PathVariable(value="id")int id){
//        service.updateClinicByid(id);
//        return new ResponseEntity<>("Clinic Record with name " + id + " has Closed", HttpStatus.OK);
//
//    }

    @PostMapping("/closeClinic")
    public ResponseEntity<String> CloseClinic(@RequestBody(required = true) Clinic clinic) {
        Clinic updateClinic = service.getClinicById(clinic.getId());
        updateClinic.setStatus("close");

        service.updateClinicRecord(updateClinic, clinic.getId());
        return new ResponseEntity<>("Clinic Record with id " + clinic.getId() + " has updated with status CLOSE", HttpStatus.OK);
    }

    @PostMapping("/closeClinic/{EndDate}")
    public ResponseEntity<List<Clinic>> closeAllClinic( ){
        String msg = "list of All Clinic";
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc","it will give list of all closed  Clinic");
        List<Clinic> c= service.getAllClinic();
        for(Clinic clinic:c){
            clinic.setStatus("close");
        }
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(c);
    }
}
